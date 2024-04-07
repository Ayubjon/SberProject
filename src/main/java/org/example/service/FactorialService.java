package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;

@Service
@Slf4j
public class FactorialService {

    private final static int MAX_NUM = 100_000;

    @Cacheable("number")
    public BigInteger getFactorial(Integer number) throws ResponseStatusException {
        log.info("Started calculating the factorial of {}", number);
        if (0 > number || number > MAX_NUM) {
            log.debug("Got number {}. Expected number between 0 and {}", number, MAX_NUM);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Number should be between 0 and %s.", MAX_NUM));
        }

        BigInteger ans = BigInteger.ONE;

        for (int i = 1; i <= number; ++i) {
            ans = ans.multiply(BigInteger.valueOf(i));
        }

        log.info("Ended calculating. Factorial of {} is {}", number, ans);

        return ans;
    }
}
