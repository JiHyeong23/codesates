package com.codestates.coffee;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@Getter
@Setter
public class CoffeePatchDto {
    private Optional<@NotBlank String> korName;

    private Optional<@NotBlank @Pattern(regexp = "^([a-zA-Z]+)(\\s?[a-zA-Z])*$") String> engName;

    private Optional<@Range(min= 100, max= 50000) Integer> price;
}
