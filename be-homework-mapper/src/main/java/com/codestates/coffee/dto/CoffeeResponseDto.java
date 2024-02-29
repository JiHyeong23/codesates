package com.codestates.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoffeeResponseDto {
    private long coffeeId;
    private String korName;
    private String engName;
    private Integer price;
}
