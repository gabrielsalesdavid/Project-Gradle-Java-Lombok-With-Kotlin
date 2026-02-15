package main.java.br.com.dio;

import main.java.br.com.dio.model.Person;
import main.java.br.com.dio.model.RUser;
import main.java.br.com.dio.processor.SerializerProcessor;

public class Main {

    public static void main(String[] args) {

        var processor = new SerializerProcessor();
        System.out.println(processor.serializer(new Person(1, "João da Silva", 26)));
        System.out.println(processor.serializer(new RUser(2, "Maria Silva", 25, 3200.32)));
    }
}
