package main.java.br.com.dio.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.EFieldFormatedEnum.CAMEL_CASE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ASerializerType {

    EFieldFormatEnum fieldFormat() default CAMEL_CASE;

    boolean prettify() default true;
}