package org.example.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseResponse {

    private final HttpStatus status;

    public BaseResponse(HttpStatus status) {
        this.status = status;
    }
}
