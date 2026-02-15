package br.com.dio;

import main.java.br.com.dio.IBuilder;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@IBuilder
@ToString(callSuper = true)
@Getter
@Setter
public class Person {

    private int id;
    private String name;
}