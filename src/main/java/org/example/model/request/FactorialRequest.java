package org.example.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactorialRequest {
    @NotNull(message = "factorial_num is required")
    Integer factorial_num;
}
