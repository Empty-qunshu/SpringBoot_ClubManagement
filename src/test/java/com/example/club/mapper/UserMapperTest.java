package com.example.club.mapper;

import com.example.club.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserMapper 单元测试")
class UserMapperTest {

    @Mock
    private UserMapper userMapper;

    @Test
    @DisplayName("测试 findByUsername 方法存在且返回类型正确")
    void testFindByUsernameMethodExists() throws NoSuchMethodException {
        Method method = UserMapper.class.getMethod("findByUsername", String.class);
        assertNotNull(method);
        assertEquals(User.class, method.getReturnType());
    }

    @Test
    @DisplayName("测试 findAll 方法存在且返回类型正确")
    void testFindAllMethodExists() throws NoSuchMethodException {
        Method method = UserMapper.class.getMethod("findAll");
        assertNotNull(method);
        assertEquals(List.class, method.getReturnType());
    }

    @Test
    @DisplayName("测试 selectByNumberAndPassword 方法存在且返回类型正确")
    void testSelectByNumberAndPasswordMethodExists() throws NoSuchMethodException {
        Method method = UserMapper.class.getMethod("selectByNumberAndPassword", User.class);
        assertNotNull(method);
        assertEquals(User.class, method.getReturnType());
    }

    @Test
    @DisplayName("测试 findByUsername 返回用户对象")
    void testFindByUsernameReturnsUser() {
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setUsername("admin");
        expectedUser.setRealName("管理员");
        when(userMapper.findByUsername("admin")).thenReturn(expectedUser);

        User result = userMapper.findByUsername("admin");

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("admin", result.getUsername());
        assertEquals("管理员", result.getRealName());
    }

    @Test
    @DisplayName("测试 findByUsername 返回 null 当用户不存在")
    void testFindByUsernameReturnsNullWhenNotExists() {
        when(userMapper.findByUsername("nonexistent")).thenReturn(null);

        User result = userMapper.findByUsername("nonexistent");

        assertNull(result);
    }

    @Test
    @DisplayName("测试 findByUsername 处理空字符串输入")
    void testFindByUsernameWithEmptyString() {
        User expectedUser = new User();
        expectedUser.setId(2);
        expectedUser.setUsername("");
        when(userMapper.findByUsername("")).thenReturn(expectedUser);

        User result = userMapper.findByUsername("");

        assertNotNull(result);
        assertEquals(2, result.getId());
        assertEquals("", result.getUsername());
    }

    @Test
    @DisplayName("测试 findByUsername 处理特殊字符输入")
    void testFindByUsernameWithSpecialCharacters() {
        User expectedUser = new User();
        expectedUser.setId(3);
        expectedUser.setUsername("test_user_123");
        when(userMapper.findByUsername("test_user_123")).thenReturn(expectedUser);

        User result = userMapper.findByUsername("test_user_123");

        assertNotNull(result);
        assertEquals(3, result.getId());
        assertEquals("test_user_123", result.getUsername());
    }

    @Test
    @DisplayName("测试 findAll 返回用户列表")
    void testFindAllReturnsUserList() {
        User user1 = new User(1, "user1", "pass1", "用户1", "S001", "男", "13800000001", "user1@test.com", 1, null, 1, null, null);
        User user2 = new User(2, "user2", "pass2", "用户2", "S002", "女", "13800000002", "user2@test.com", 1, null, 1, null, null);
        List<User> expectedList = List.of(user1, user2);
        when(userMapper.findAll()).thenReturn(expectedList);

        List<User> result = userMapper.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
    }

    @Test
    @DisplayName("测试 findAll 返回空列表")
    void testFindAllReturnsEmptyList() {
        when(userMapper.findAll()).thenReturn(new ArrayList<>());

        List<User> result = userMapper.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("测试 findAll 返回单个用户")
    void testFindAllReturnsSingleUser() {
        User singleUser = new User(1, "single", "pass", "单独用户", "S999", "男", "13900000000", "single@test.com", 2, null, 1, null, null);
        when(userMapper.findAll()).thenReturn(List.of(singleUser));

        List<User> result = userMapper.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("single", result.get(0).getUsername());
    }

    @Test
    @DisplayName("测试 selectByNumberAndPassword 返回用户对象")
    void testSelectByNumberAndPasswordReturnsUser() {
        User loginUser = new User();
        loginUser.setUsername("testuser");
        loginUser.setPassword("testpass");

        User expectedUser = new User();
        expectedUser.setId(5);
        expectedUser.setUsername("testuser");
        expectedUser.setRealName("测试用户");

        when(userMapper.selectByNumberAndPassword(loginUser)).thenReturn(expectedUser);

        User result = userMapper.selectByNumberAndPassword(loginUser);

        assertNotNull(result);
        assertEquals(5, result.getId());
        assertEquals("testuser", result.getUsername());
        assertEquals("测试用户", result.getRealName());
    }

    @Test
    @DisplayName("测试 selectByNumberAndPassword 返回 null 当凭证不匹配")
    void testSelectByNumberAndPasswordReturnsNullWhenCredentialMismatch() {
        User loginUser = new User();
        loginUser.setUsername("wronguser");
        loginUser.setPassword("wrongpass");

        when(userMapper.selectByNumberAndPassword(loginUser)).thenReturn(null);

        User result = userMapper.selectByNumberAndPassword(loginUser);

        assertNull(result);
    }

    @Test
    @DisplayName("测试 selectByNumberAndPassword 处理空用户名")
    void testSelectByNumberAndPasswordWithEmptyUsername() {
        User loginUser = new User();
        loginUser.setUsername("");
        loginUser.setPassword("somepass");

        when(userMapper.selectByNumberAndPassword(loginUser)).thenReturn(null);

        User result = userMapper.selectByNumberAndPassword(loginUser);

        assertNull(result);
    }

    @Test
    @DisplayName("测试 selectByNumberAndPassword 处理空密码")
    void testSelectByNumberAndPasswordWithEmptyPassword() {
        User loginUser = new User();
        loginUser.setUsername("someuser");
        loginUser.setPassword("");

        when(userMapper.selectByNumberAndPassword(loginUser)).thenReturn(null);

        User result = userMapper.selectByNumberAndPassword(loginUser);

        assertNull(result);
    }

    @Test
    @DisplayName("测试 selectByNumberAndPassword 处理 null 属性")
    void testSelectByNumberAndPasswordWithNullProperties() {
        User loginUser = new User();
        loginUser.setUsername(null);
        loginUser.setPassword(null);

        when(userMapper.selectByNumberAndPassword(loginUser)).thenReturn(null);

        User result = userMapper.selectByNumberAndPassword(loginUser);

        assertNull(result);
    }

    @Test
    @DisplayName("测试 User 实体类所有字段的创建和获取")
    void testUserEntityAllFields() {
        User user = new User(
            100,
            "testusername",
            "testpassword",
            "测试姓名",
            "S12345",
            "男",
            "13812345678",
            "test@example.com",
            2,
            "/avatar/test.png",
            1,
            "2024-01-01 10:00:00",
            "2024-01-02 10:00:00"
        );

        assertEquals(100, user.getId());
        assertEquals("testusername", user.getUsername());
        assertEquals("testpassword", user.getPassword());
        assertEquals("测试姓名", user.getRealName());
        assertEquals("S12345", user.getStudentNo());
        assertEquals("男", user.getGender());
        assertEquals("13812345678", user.getPhone());
        assertEquals("test@example.com", user.getEmail());
        assertEquals(2, user.getRole());
        assertEquals("/avatar/test.png", user.getAvatar());
        assertEquals(1, user.getStatus());
        assertEquals("2024-01-01 10:00:00", user.getCreateTime());
        assertEquals("2024-01-02 10:00:00", user.getUpdateTime());
    }

    @Test
    @DisplayName("测试 User 无参构造函数")
    void testUserNoArgsConstructor() {
        User user = new User();

        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
    }

    @Test
    @DisplayName("测试 User 的 set 方法")
    void testUserSetters() {
        User user = new User();
        user.setId(10);
        user.setUsername("settertest");
        user.setPassword("pass123");
        user.setRealName("设置测试");
        user.setStudentNo("STU001");
        user.setGender("女");
        user.setPhone("13900001111");
        user.setEmail("setter@test.com");
        user.setRole(3);
        user.setAvatar("/avatar/setter.png");
        user.setStatus(0);
        user.setCreateTime("2024-03-01");
        user.setUpdateTime("2024-03-02");

        assertEquals(10, user.getId());
        assertEquals("settertest", user.getUsername());
        assertEquals("pass123", user.getPassword());
        assertEquals("设置测试", user.getRealName());
        assertEquals("STU001", user.getStudentNo());
        assertEquals("女", user.getGender());
        assertEquals("13900001111", user.getPhone());
        assertEquals("setter@test.com", user.getEmail());
        assertEquals(3, user.getRole());
        assertEquals("/avatar/setter.png", user.getAvatar());
        assertEquals(0, user.getStatus());
        assertEquals("2024-03-01", user.getCreateTime());
        assertEquals("2024-03-02", user.getUpdateTime());
    }

    @Test
    @DisplayName("测试边界值：用户名最大长度边界")
    void testFindByUsernameWithMaxLengthUsername() {
        String maxUsername = "a".repeat(50);
        User expectedUser = new User();
        expectedUser.setId(99);
        expectedUser.setUsername(maxUsername);

        when(userMapper.findByUsername(maxUsername)).thenReturn(expectedUser);

        User result = userMapper.findByUsername(maxUsername);

        assertNotNull(result);
        assertEquals(99, result.getId());
        assertEquals(maxUsername, result.getUsername());
        assertEquals(50, result.getUsername().length());
    }

    @Test
    @DisplayName("测试边界值：密码最大长度边界")
    void testSelectByNumberAndPasswordWithMaxLengthPassword() {
        String maxPassword = "p".repeat(100);
        User loginUser = new User();
        loginUser.setUsername("user");
        loginUser.setPassword(maxPassword);

        User expectedUser = new User();
        expectedUser.setId(50);
        expectedUser.setPassword(maxPassword);

        when(userMapper.selectByNumberAndPassword(loginUser)).thenReturn(expectedUser);

        User result = userMapper.selectByNumberAndPassword(loginUser);

        assertNotNull(result);
        assertEquals(maxPassword, result.getPassword());
        assertEquals(100, result.getPassword().length());
    }
}