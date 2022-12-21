package com.codestates.coffee.mapper;

import com.codestates.coffee.dto.CoffeePatchDto;
import com.codestates.coffee.dto.CoffeePostDto;
import com.codestates.coffee.dto.CoffeeResponseDto;
import com.codestates.coffee.entity.Coffee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-21T15:08:16+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class CoffeeMapperImpl implements CoffeeMapper {

    @Override
    public Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto) {
        if ( coffeePostDto == null ) {
            return null;
        }

        String korName = null;
        String engName = null;
        Integer price = null;

        korName = coffeePostDto.getKorName();
        engName = coffeePostDto.getEngName();
        price = coffeePostDto.getPrice();

        long coffeeId = 0L;

        Coffee coffee = new Coffee( coffeeId, korName, engName, price );

        return coffee;
    }

    @Override
    public Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto) {
        if ( coffeePatchDto == null ) {
            return null;
        }

        long coffeeId = 0L;
        String korName = null;
        String engName = null;
        Integer price = null;

        coffeeId = coffeePatchDto.getCoffeeId();
        korName = coffeePatchDto.getKorName();
        engName = coffeePatchDto.getEngName();
        price = coffeePatchDto.getPrice();

        Coffee coffee = new Coffee( coffeeId, korName, engName, price );

        return coffee;
    }

    @Override
    public CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee) {
        if ( coffee == null ) {
            return null;
        }

        long coffeeId = 0L;
        String korName = null;
        String engName = null;
        Integer price = null;

        coffeeId = coffee.getCoffeeId();
        korName = coffee.getKorName();
        engName = coffee.getEngName();
        price = coffee.getPrice();

        CoffeeResponseDto coffeeResponseDto = new CoffeeResponseDto( coffeeId, korName, engName, price );

        return coffeeResponseDto;
    }

    @Override
    public List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees) {
        if ( coffees == null ) {
            return null;
        }

        List<CoffeeResponseDto> list = new ArrayList<CoffeeResponseDto>( coffees.size() );
        for ( Coffee coffee : coffees ) {
            list.add( coffeeToCoffeeResponseDto( coffee ) );
        }

        return list;
    }
}
