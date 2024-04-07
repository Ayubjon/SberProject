package org.example.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;

@Setter
@Getter
public class FactorialResponse extends BaseResponse {

    private BigInteger result;

    public FactorialResponse(HttpStatus status, BigInteger result) {
        super(status);
        this.result = result;
    }
}
