package org.jacekkowalczyk82.example.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add((byte) 2, (byte) 3));
        assertEquals(-1, calculator.add((byte) -2, (byte) 1));
        assertEquals(0, calculator.add((byte) 0, (byte) 0));
    }

    @Test
    public void testSubtract() {
        assertEquals(1, calculator.subtract((byte) 3, (byte) 2));
        assertEquals(-3, calculator.subtract((byte) -2, (byte) 1));
        assertEquals(0, calculator.subtract((byte) 0, (byte) 0));
    }

    @Test
    public void testMultiply() {
        assertEquals(6, calculator.multiply((byte) 2, (byte) 3));
        assertEquals(-2, calculator.multiply((byte) -2, (byte) 1));
        assertEquals(0, calculator.multiply((byte) 0, (byte) 3));
    }

    @Test
    public void testDivide() {
        assertEquals(2, calculator.divide((byte) 6, (byte) 3));
        assertEquals(-2, calculator.divide((byte) -6, (byte) 3));
        assertThrows(ArithmeticException.class, () -> calculator.divide((byte) 1, (byte) 0));
    }

    @Test
    public void testModulo() {
        assertEquals(1, calculator.modulo((byte) 7, (byte) 3));
        assertEquals(-1, calculator.modulo((byte) -7, (byte) 3));
        assertThrows(ArithmeticException.class, () -> calculator.modulo((byte) 1, (byte) 0));
    }

    @Test
    public void testPower() {
        assertEquals(8, calculator.power((byte) 2, (byte) 3));
        assertEquals(1, calculator.power((byte) 2, (byte) 0));
        assertEquals(0, calculator.power((byte) 0, (byte) 3));

        // The range of byte is -128 to 127
        assertEquals(-128, calculator.power((byte) 2, (byte) 7), "The results of 2 power 7 in the randge of int will be 128 but in the range of byte it will be -128");
        assertEquals(0, calculator.power((byte) 2, (byte) 8), "The results of 2 power 8 in the randge of int will be 256 but in the range of byte it will be 0");

    }


    @ParameterizedTest
    @CsvSource({
        "2, 3, 8",
        "2, 0, 1",
        "0, 3, 0",
        "2, 7, -128", // The result of 2^7 in the range of byte will be -128
        "2, 8, 0"     // The result of 2^8 in the range of byte will be 0
    })
    public void testPowerParameterized(byte base, byte exponent, int expected) {
        assertEquals(expected, calculator.power(base, exponent));
    }

    static Stream<Arguments> providePowerTestArguments() {
        return Stream.of(
            //base, exponent, expected
            Arguments.of((byte) 2, (byte) 3, 8),
            Arguments.of((byte) 2, (byte) 0, 1),
            Arguments.of((byte) 0, (byte) 3, 0),
            Arguments.of((byte) 2, (byte) 7, -128), // The result of 2^7 in the range of byte will be -128
            Arguments.of((byte) 2, (byte) 8, 0)     // The result of 2^8 in the range of byte will be 0
        );
    }

    @ParameterizedTest
    @MethodSource("providePowerTestArguments")
    public void testPowerParameterizedV2(byte base, byte exponent, int expected) {
        assertEquals(expected, calculator.power(base, exponent));
    }





    @Test
    public void testSquareRoot() {
        assertEquals(2, calculator.squareRoot((byte) 4));
        assertEquals(0, calculator.squareRoot((byte) 0));
        assertThrows(ArithmeticException.class, () -> calculator.squareRoot((byte) -4));
        // System.out.println("Result of calculator.squareRoot((byte) -4) is: " + calculator.squareRoot((byte) -4));
        // //why Result of calculator.squareRoot((byte) -4) is: 0

    }

    @Test
    public void testCubeRoot() {
        assertEquals(2, calculator.cubeRoot((byte) 8));
        assertEquals(0, calculator.cubeRoot((byte) 0));
        assertEquals(-2, calculator.cubeRoot((byte) -8));
    }

    @Test
    public void testFactorialByte() {
        assertEquals(120, calculator.factorial((byte) 5));
        assertEquals(1, calculator.factorial((byte) 0));
        assertThrows(IllegalArgumentException.class, () -> calculator.factorial((byte) -1));
    }

    @Test
    public void testFactorialInt() {
        assertEquals(120, calculator.factorial(5));
        assertEquals(1, calculator.factorial(0));
        assertThrows(IllegalArgumentException.class, () -> calculator.factorial(-1));
    }

    @Test
    public void testAbsolute() {
        assertEquals(5, calculator.absolute((byte) 5));
        assertEquals(5, calculator.absolute((byte) -5));
        assertEquals(0, calculator.absolute((byte) 0));
    }

    @Test
    public void testNegate() {
        assertEquals(-5, calculator.negate((byte) 5));
        assertEquals(5, calculator.negate((byte) -5));
        assertEquals(0, calculator.negate((byte) 0));
    }

    @Test
    public void testMax() {
        assertEquals(5, calculator.max((byte) 5, (byte) 3));
        assertEquals(5, calculator.max((byte) 5, (byte) 5));
        assertEquals(3, calculator.max((byte) 3, (byte) 3));
    }

    @Test
    public void testMin() {
        assertEquals(3, calculator.min((byte) 5, (byte) 3));
        assertEquals(5, calculator.min((byte) 5, (byte) 5));
        assertEquals(3, calculator.min((byte) 3, (byte) 3));
    }

    @Test
    public void testAverage() {
        assertEquals(4, calculator.average((byte) 5, (byte) 3));
        assertEquals(5, calculator.average((byte) 5, (byte) 5));
        assertEquals(3, calculator.average((byte) 3, (byte) 3));
    }

    @Test
    public void testSum() {
        assertEquals(5, calculator.sum((byte) 2, (byte) 3));
        assertEquals(-1, calculator.sum((byte) -2, (byte) 1));
        assertEquals(0, calculator.sum((byte) 0, (byte) 0));
    }

    @Test
    public void testDifference() {
        assertEquals(1, calculator.difference((byte) 3, (byte) 2));
        assertEquals(-3, calculator.difference((byte) -2, (byte) 1));
        assertEquals(0, calculator.difference((byte) 0, (byte) 0));
    }

    @Test
    public void testProduct() {
        assertEquals(6, calculator.product((byte) 2, (byte) 3));
        assertEquals(-2, calculator.product((byte) -2, (byte) 1));
        assertEquals(0, calculator.product((byte) 0, (byte) 3));
    }

    @Test
    public void testQuotient() {
        assertEquals(2, calculator.quotient((byte) 6, (byte) 3));
        assertEquals(-2, calculator.quotient((byte) -6, (byte) 3));
        assertThrows(ArithmeticException.class, () -> calculator.quotient((byte) 1, (byte) 0));
    }

    @Test
    public void testRemainder() {
        assertEquals(1, calculator.remainder((byte) 7, (byte) 3));
        assertEquals(-1, calculator.remainder((byte) -7, (byte) 3));
        assertThrows(ArithmeticException.class, () -> calculator.remainder((byte) 1, (byte) 0));
    }


     @Test
    public void testDoubleForMoney() {
        double value1 = 0.1;
        double value2 = 0.2;
        double sum = value1 + value2;

        // This will fail due to precision issues with double
        assertEquals(0.3, sum, "Sum using double is not accurate for monetary values");
    }

    @Test
    public void testBigDecimalForMoney() {
        BigDecimal value1 = new BigDecimal("0.1");
        BigDecimal value2 = new BigDecimal("0.2");
        BigDecimal sum = value1.add(value2);

        // This will pass as BigDecimal provides accurate precision
        assertEquals(new BigDecimal("0.3"), sum, "Sum using BigDecimal is accurate for monetary values");
    }


}