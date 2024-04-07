package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.model.request.FactorialRequest;
import org.example.model.response.FactorialResponse;
import org.example.service.FactorialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FactorialController {

    private final FactorialService service;

    @GetMapping("/get-factorial")
    @ResponseBody
    public FactorialResponse getFactorial(@Valid @RequestBody FactorialRequest request) {
        BigInteger ans = service.getFactorial(request.getFactorial_num());
        return new FactorialResponse(HttpStatus.OK, ans);
    }
}
