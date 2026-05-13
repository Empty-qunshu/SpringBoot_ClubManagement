package com.example.club.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultTest {

    // ==================== Constructor Tests ====================

    @Test
    void testDefaultConstructor() {
        Result result = new Result();
        assertNotNull(result);
        assertNull(result.getCode());
        assertNull(result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void testParameterizedConstructor() {
        Result result = new Result(200, "success", "testData");

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals("testData", result.getData());
    }

    @Test
    void testParameterizedConstructorWithNullData() {
        Result result = new Result(404, "not found", null);

        assertEquals(404, result.getCode());
        assertEquals("not found", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void testParameterizedConstructorWithIntegerData() {
        Result result = new Result(200, "success", 12345);

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals(12345, result.getData());
    }

    @Test
    void testParameterizedConstructorWithObjectData() {
        Result result = new Result(500, "error", new Object());

        assertEquals(500, result.getCode());
        assertEquals("error", result.getMsg());
        assertNotNull(result.getData());
    }

    // ==================== success() Static Method Tests ====================

    @Test
    void testSuccessNoArgReturnsCorrectValues() {
        Result result = Result.success();

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void testSuccessNoArgIsNotNull() {
        Result result = Result.success();
        assertNotNull(result);
    }

    @Test
    void testSuccessWithObjectReturnsCorrectValues() {
        Result result = Result.success("testData");

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals("testData", result.getData());
    }

    @Test
    void testSuccessWithObjectNull() {
        Result result = Result.success(null);

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void testSuccessWithIntegerObject() {
        Result result = Result.success(42);

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals(42, result.getData());
    }

    @Test
    void testSuccessWithListObject() {
        java.util.List<String> list = java.util.Arrays.asList("a", "b", "c");
        Result result = Result.success(list);

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals(list, result.getData());
    }

    @Test
    void testSuccessWithMapObject() {
        java.util.Map<String, Object> map = java.util.Map.of("key", "value");
        Result result = Result.success(map);

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals(map, result.getData());
    }

    // ==================== error() Static Method Tests ====================

    @Test
    void testErrorWithMessage() {
        Result result = Result.error("Something went wrong");

        assertEquals(500, result.getCode());
        assertEquals("Something went wrong", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void testErrorWithEmptyMessage() {
        Result result = Result.error("");

        assertEquals(500, result.getCode());
        assertEquals("", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void testErrorWithNullMessage() {
        Result result = Result.error(null);

        assertEquals(500, result.getCode());
        assertNull(result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void testErrorWithCustomMessage() {
        Result result = Result.error("Database connection failed");

        assertEquals(500, result.getCode());
        assertEquals("Database connection failed", result.getMsg());
    }

    // ==================== getter/setter Tests ====================

    @Test
    void testGetDataReturnsCorrectValue() {
        Result result = new Result();
        result.setData("test");

        assertEquals("test", result.getData());
    }

    @Test
    void testSetDataUpdatesValue() {
        Result result = new Result();

        result.setData("first");
        assertEquals("first", result.getData());

        result.setData("second");
        assertEquals("second", result.getData());
    }

    @Test
    void testSetDataToNull() {
        Result result = new Result();
        result.setData("test");
        result.setData(null);

        assertNull(result.getData());
    }

    // ==================== Code Getter/Setter Tests ====================

    @Test
    void testCodeGetterAndSetter() {
        Result result = new Result();
        result.setCode(201);

        assertEquals(201, result.getCode());
    }

    @Test
    void testCodeSetterUpdatesValue() {
        Result result = new Result();

        result.setCode(200);
        assertEquals(200, result.getCode());

        result.setCode(400);
        assertEquals(400, result.getCode());

        result.setCode(500);
        assertEquals(500, result.getCode());
    }

    @Test
    void testCodeCanBeNull() {
        Result result = new Result();

        assertNull(result.getCode());

        result.setCode(null);
        assertNull(result.getCode());
    }

    // ==================== Msg Getter/Setter Tests ====================

    @Test
    void testMsgGetterAndSetter() {
        Result result = new Result();
        result.setMsg("test message");

        assertEquals("test message", result.getMsg());
    }

    @Test
    void testMsgSetterUpdatesValue() {
        Result result = new Result();

        result.setMsg("first message");
        assertEquals("first message", result.getMsg());

        result.setMsg("second message");
        assertEquals("second message", result.getMsg());
    }

    @Test
    void testMsgCanBeNull() {
        Result result = new Result();

        assertNull(result.getMsg());

        result.setMsg(null);
        assertNull(result.getMsg());
    }

    // ==================== Edge Cases and Boundary Tests ====================

    @Test
    void testSuccessWithLargeCode() {
        Result result = Result.success();
        result.setCode(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, result.getCode());
    }

    @Test
    void testSuccessWithNegativeCode() {
        Result result = Result.success();
        result.setCode(-1);

        assertEquals(-1, result.getCode());
    }

    @Test
    void testSuccessWithZeroCode() {
        Result result = Result.success();
        result.setCode(0);

        assertEquals(0, result.getCode());
    }

    @Test
    void testErrorWithVeryLongMessage() {
        String longMessage = "a".repeat(10000);
        Result result = Result.error(longMessage);

        assertEquals(500, result.getCode());
        assertEquals(longMessage, result.getMsg());
        assertEquals(10000, result.getMsg().length());
    }

    @Test
    void testResultWithSpecialCharactersInMsg() {
        Result result = new Result();
        result.setMsg("Special chars: !@#$%^&*()_+-=[]{}|;':\",./<>?");

        assertEquals("Special chars: !@#$%^&*()_+-=[]{}|;':\",./<>?", result.getMsg());
    }

    @Test
    void testResultWithUnicodeInMsg() {
        Result result = new Result();
        result.setMsg("中文消息 Emoji ");

        assertEquals("中文消息 Emoji ", result.getMsg());
    }

    @Test
    void testResultWithWhitespaceMessage() {
        Result result = new Result();
        result.setMsg("   ");

        assertEquals("   ", result.getMsg());
    }

    // ==================== Data Type Variation Tests ====================

    @Test
    void testResultWithStringData() {
        Result result = new Result(200, "ok", "string data");

        assertEquals("string data", result.getData());
        assertTrue(result.getData() instanceof String);
    }

    @Test
    void testResultWithIntegerData() {
        Result result = new Result(200, "ok", 42);

        assertEquals(42, result.getData());
        assertTrue(result.getData() instanceof Integer);
    }

    @Test
    void testResultWithBooleanData() {
        Result result = new Result(200, "ok", true);

        assertEquals(true, result.getData());
        assertTrue(result.getData() instanceof Boolean);
    }

    @Test
    void testResultWithListData() {
        Result result = new Result(200, "ok", java.util.Arrays.asList(1, 2, 3));

        assertNotNull(result.getData());
        assertTrue(result.getData() instanceof java.util.List);
    }

    // ==================== Fluent API Style Tests ====================

    @Test
    void testChainedSetters() {
        Result result = new Result();
        result.setCode(201);
        result.setMsg("Created");
        result.setData("new resource");

        assertEquals(201, result.getCode());
        assertEquals("Created", result.getMsg());
        assertEquals("new resource", result.getData());
    }

    // ==================== Integration Scenario Tests ====================

    @Test
    void testApiSuccessResponse() {
        Result result = Result.success();
        result.setData(java.util.Map.of("id", 1, "name", "Test Club"));

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertNotNull(result.getData());
    }

    @Test
    void testApiErrorResponse() {
        Result result = Result.error("Invalid input");
        result.setData(java.util.Map.of("field", "name", "reason", "required"));

        assertEquals(500, result.getCode());
        assertEquals("Invalid input", result.getMsg());
        assertNotNull(result.getData());
    }

    @Test
    void testNotFoundResponse() {
        Result result = new Result(404, "Resource not found", null);

        assertEquals(404, result.getCode());
        assertEquals("Resource not found", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void testUnauthorizedResponse() {
        Result result = new Result(401, "Unauthorized access", null);

        assertEquals(401, result.getCode());
        assertEquals("Unauthorized access", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void testCreatedResponse() {
        Result result = new Result(201, "Created", "new resource");

        assertEquals(201, result.getCode());
        assertEquals("Created", result.getMsg());
        assertEquals("new resource", result.getData());
    }

    @Test
    void testBadRequestResponse() {
        Result result = Result.error("Bad Request: missing parameter");

        assertEquals(500, result.getCode());
        assertEquals("Bad Request: missing parameter", result.getMsg());
    }

    @Test
    void testSuccessWithEmptyStringData() {
        Result result = Result.success("");

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals("", result.getData());
    }

    @Test
    void testSuccessWithBooleanFalseData() {
        Result result = Result.success(false);

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals(false, result.getData());
    }

    @Test
    void testSuccessWithZeroData() {
        Result result = Result.success(0);

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals(0, result.getData());
    }
}