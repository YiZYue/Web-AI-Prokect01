package com.surui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class UserServiceAI {

    private final UserService us = new UserService();
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Test
    public void testGetAge2() {
        Integer age = us.getAge("110002200505091218");
        Assertions.assertNotEquals(18, age, "两个值相等");
    }

    // ==================== getAge 正常及边界值测试 ====================

    /**
     * 覆盖年龄的各种边界：出生当天、恰好满N岁、未满N岁、高龄
     */
    @ParameterizedTest(name = "生日={0}，期望年龄={1}")
    @MethodSource("ageProvider")
    public void testGetAgeNormal(LocalDate birthday, int expectedAge) {
        Assertions.assertEquals(expectedAge, us.getAge(buildIdCard(birthday)));
    }

    static Stream<Arguments> ageProvider() {
        LocalDate today = LocalDate.now();
        return Stream.of(
                Arguments.of(today, 0),                                   // 今天出生 -> 0 岁
                Arguments.of(today.minusDays(1), 0),                      // 昨天出生 -> 0 岁
                Arguments.of(today.minusYears(1), 1),                     // 恰好满 1 岁
                Arguments.of(today.minusYears(1).plusDays(1), 0),         // 还差 1 天满 1 岁 -> 未满，得 0
                Arguments.of(today.minusYears(17), 17),                   // 恰好满 17 岁
                Arguments.of(today.minusYears(18), 18),                   // 恰好满 18 岁（成年边界）
                Arguments.of(today.minusYears(18).plusDays(1), 17),       // 差 1 天满 18 岁 -> 未满，得 17
                Arguments.of(today.minusYears(100), 100)                  // 高龄边界 100 岁
        );
    }

    // ==================== getAge 非法参数/非法日期测试 ====================

    /**
     * null、长度不等于18 时抛 IllegalArgumentException
     */
    @ParameterizedTest
    @ValueSource(strings = {"", "1234567890123456", "1234567890123456789"})
    public void testGetAgeInvalidLength(String idCard) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> us.getAge(idCard));
    }

    @Test
    public void testGetAgeNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> us.getAge(null));
    }

    /**
     * 生日字段为不存在的日期（如 20261301）时抛 DateTimeParseException
     */
    @Test
    public void testGetAgeInvalidDate() {
        String idCard = "110002" + "20261301" + "0912"; // 13月不存在
        Assertions.assertThrows(java.time.format.DateTimeParseException.class, () -> us.getAge(idCard));
    }

    // ==================== getGender 正常及边界值测试 ====================

    /**
     * 第17位奇数 -> 男；偶数 -> 女；覆盖最小偶数0和最大奇数9两个边界
     */
    @ParameterizedTest(name = "第17位={0}，期望={1}")
    @MethodSource("genderProvider")
    public void testGetGenderNormal(int digit, String expected) {
        String idCard = "11000220050509" + digit + "18"; // 第17位即中间数字
        Assertions.assertEquals(expected, us.getGender(idCard));
    }

    static Stream<Arguments> genderProvider() {
        return Stream.of(
                Arguments.of(0, "女"),   // 最小偶数边界
                Arguments.of(1, "男"),   // 最小奇数
                Arguments.of(2, "女"),
                Arguments.of(3, "男"),
                Arguments.of(8, "女"),   // 最大偶数
                Arguments.of(9, "男")    // 最大奇数边界
        );
    }

    // ==================== getGender 非法参数测试 ====================

    @ParameterizedTest
    @ValueSource(strings = {"", "1234567890123456", "1234567890123456789"})
    public void testGetGenderInvalidLength(String idCard) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> us.getGender(idCard));
    }

    @Test
    public void testGetGenderNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> us.getGender(null));
    }

    // ==================== 工具方法 ====================

    /**
     * 根据生日构造长度为 18 的身份证号：前6位前缀 + 8位生日 + 后4位后缀
     */
    private static String buildIdCard(LocalDate birthday) {
        return "110002" + birthday.format(FMT) + "0918";
    }
}