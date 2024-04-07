package org.example.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class FactorialServiceTest {

    private FactorialService service;

    @BeforeEach
    void before() {
        this.service = new FactorialService();
    }

    @Test
    void testFactorialZeroAndOne() {
        BigInteger ans1 = service.getFactorial(0);
        BigInteger ans2 = service.getFactorial(1);
        Assertions.assertEquals(ans1, BigInteger.ONE);
        Assertions.assertEquals(ans2, BigInteger.ONE);
    }

    @Test
    void testFactorialTen() {
        BigInteger ans = service.getFactorial(10);
        Assertions.assertEquals(ans, BigInteger.valueOf(3628800));
    }

    @Test
    void testFactorialThousand() {
        BigInteger ans = service.getFactorial(1000);

        BigInteger expectedAns = BigInteger.ONE;

        for (int i = 1; i <= 1000; ++i) {
            expectedAns = expectedAns.multiply(BigInteger.valueOf(i));
        }

        Assertions.assertEquals(ans, expectedAns);
    }
}
