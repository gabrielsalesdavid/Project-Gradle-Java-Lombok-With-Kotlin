package main.java.br.com.dio.model;

//import java.time.LocalDateTime;
import main.java.br.com.dio.annotation.ASerializerType.*;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@SerializerType
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {

    private long id;
    private String name;
    private int age;

    @SerializerMethod("FirstPersonName")
    public String firName() {

        return name.split(" ")[0];
    }
}