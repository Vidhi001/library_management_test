package com.example.minor_project1.dtos;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePaymentRequest {

    @NotNull
    private Integer amount;
    @NotBlank
    private String txnId;
//    @NotNull
//    private Integer studentId;
}
