package com.codestates.coffee;

import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CoffeePostDto {
    @NotBlank
    private String korName;
    @NotBlank
    @Pattern(regexp = "^([a-zA-Z]+)(\\s?[a-zA-Z])*$", message = "영문만 허용됩니다")
    private String engName;
    @Range(min = 100, max = 50000)
    private int price;

    public String getKorName() {
        return korName;
    }

    public String getEngName() {
        return engName;
    }

    public int getPrice() {
        return price;
    }
}
