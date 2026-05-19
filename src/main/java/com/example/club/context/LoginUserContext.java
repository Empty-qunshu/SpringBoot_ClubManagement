package com.example.club.context;

public class LoginUserContext {
    private static final ThreadLocal<LoginUser> HOLDER = new ThreadLocal<>();

    private LoginUserContext() {
    }

    public static void set(LoginUser loginUser) {
        HOLDER.set(loginUser);
    }

    public static LoginUser get() {
        return HOLDER.get();
    }

    public static Integer getUserId() {
        LoginUser loginUser = get();
        return loginUser == null ? null : loginUser.getId();
    }

    public static void clear() {
        HOLDER.remove();
    }
}
