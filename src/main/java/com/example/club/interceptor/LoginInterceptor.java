package com.example.club.interceptor;

import com.example.club.annotation.AnonymousAccess;
import com.example.club.annotation.RequireRole;
import com.example.club.context.LoginUser;
import com.example.club.context.LoginUserContext;
import com.example.club.entity.Result;
import com.example.club.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        if (hasAnonymousAccess(handlerMethod)) {
            return true;
        }

        String token = resolveToken(request);
        if (!StringUtils.hasText(token)) {
            writeJson(response, HttpServletResponse.SC_UNAUTHORIZED, Result.unauthorized("请先登录"));
            return false;
        }

        try {
            Claims claims = JwtUtil.parse(token);
            LoginUser loginUser = new LoginUser(
                    JwtUtil.getUserId(claims),
                    claims.get("username", String.class),
                    claims.get("role", Integer.class)
            );
            LoginUserContext.set(loginUser);
            request.setAttribute("loginUser", loginUser);
            request.setAttribute("loginUserId", loginUser.getId());
            request.setAttribute("loginUsername", loginUser.getUsername());
            request.setAttribute("loginRole", loginUser.getRole());

            RequireRole requireRole = getRequireRole(handlerMethod);
            if (requireRole != null && !hasRole(loginUser, requireRole.value())) {
                LoginUserContext.clear();
                writeJson(response, HttpServletResponse.SC_FORBIDDEN, Result.error(403, "没有权限访问该资源"));
                return false;
            }
            return true;
        } catch (Exception e) {
            writeJson(response, HttpServletResponse.SC_UNAUTHORIZED, Result.unauthorized("登录已过期，请重新登录"));
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LoginUserContext.clear();
    }

    private String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    private boolean hasAnonymousAccess(HandlerMethod handlerMethod) {
        return AnnotatedElementUtils.hasAnnotation(handlerMethod.getMethod(), AnonymousAccess.class)
                || AnnotatedElementUtils.hasAnnotation(handlerMethod.getBeanType(), AnonymousAccess.class);
    }

    private RequireRole getRequireRole(HandlerMethod handlerMethod) {
        RequireRole methodRole = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), RequireRole.class);
        if (methodRole != null) {
            return methodRole;
        }
        return AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getBeanType(), RequireRole.class);
    }

    private boolean hasRole(LoginUser loginUser, int[] roles) {
        return loginUser != null && Arrays.stream(roles).anyMatch(role -> Objects.equals(role, loginUser.getRole()));
    }

    private void writeJson(HttpServletResponse response, int status, Result result) throws Exception {
        response.setStatus(status);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":" + result.getCode() + ",\"msg\":\"" + escape(result.getMsg()) + "\"}");
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
