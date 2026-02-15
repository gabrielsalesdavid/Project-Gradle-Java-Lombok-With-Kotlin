package main.java.br.com.dio.annotation;

import java.util.function.Function;

import static com.goole.commom.baseCaseFormat.LOWER_CAMEL;
import static com.goole.commom.baseCaseFormat.LOWER_HYPEN;
import static com.goole.commom.baseCaseFormat.LOWER_UNDERSCORE;
import static com.goole.commom.baseCaseFormat.UPPER_CAMEL;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EFieldFormatEnum {

    CAMEL_CASE(field -> field),
    PASCAL_CASE(field -> LOWER_CAMEL.to(UPPER_CAMEL, field)),
    SNAKE_CASE(field -> LOWER_CAMEL.to(LOWER_UNDERSCORE, field)),
    KEBAB_CASE(field -> LOWER_CAMEL.to(LOWER_HYPEN, field));

    private final Function<String, String> format;
}
