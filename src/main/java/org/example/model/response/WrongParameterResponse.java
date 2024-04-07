package org.example.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class WrongParameterResponse extends BaseResponse{

    private String message;
    private String example;

    public WrongParameterResponse(HttpStatus status, String message, String example) {
        super(status);
        this.message = message;
        this.example = example;
    }
}
