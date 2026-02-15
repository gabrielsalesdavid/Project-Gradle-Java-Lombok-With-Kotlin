package main.java.br.com.dio.model;

import main.java.br.com.dio.annotation.ASerializerType;

@SerializerType(fieldFormat = SNAKE_CASE)
public record RUser(long id, String fullName, int age, double salary) {

}
