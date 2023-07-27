package com.example.minor_project1.dtos;


import com.example.minor_project1.models.*;
import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionRequest {

    @NotNull
    private TransactionsType transType;

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer bookId;

//    @NotNull
//    private Integer adminId;

}
