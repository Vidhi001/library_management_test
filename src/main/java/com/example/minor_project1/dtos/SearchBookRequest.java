package com.example.minor_project1.dtos;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchBookRequest {

    @NotBlank
    private String key;
    @NotBlank
    private String value;
}
