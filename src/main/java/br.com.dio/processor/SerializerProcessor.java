package main.java.br.com.dio.processor;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.lang.Object;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import main.java.br.com.dio.annotation.ASerializerMethod;
import main.java.br.com.dio.annotation.ASerializerType;

public class SerializerProcessor {

    public String serializer(final Object object) throws IllegalAccessException {

        Objects.requireNonNull(object, "Enter with non null object");

        var clazz = obejct.getClass();
        var typeAnnotation = Stream.of(clazz.getAnnotations())
                .flatMap(a -> (a instanceof ASerializerType s) ? Stream.of(s) : Stream.empty())
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException("For serializer object annotate it with @ASerializerType")
                );
        var fieldNameFormatter = typeAnnotation.fieldFormat().getFormat();
        var prettify = typeAnnotation.prettify();

        Map<String, Object> elements = new HashMap<>();

        for (var field : claszz.getDeclaredFields()) {

            field.setAcessible(true);
            elements.put(field.getName(), field.get(object));
        }

        var annotatedMethods = Stream.of(object.getClass().getMethods())
                .filter(m -> Stream.of(m.getAnnotations())
                .anyMatch(a -> a.annotationType()
                .equals(ASerializerMethod.class)))
                .toList();

        for (var method : annotatedMethods) {

            method.setAccessible(true);
            var customName = method.getAnnotation(ASerializerMethod.class).value();
            elements.put(customName.isBlank() ? method.getName() : customName, method.invoke(object));
        }

        var jsonFileds = elements.entrySet()
                .stream()
                .map(e -> String.format("    \"%s\":%s",
                fieldNameFormatter.apply(e.getKey()),
                formatValue(e.getValue()))
                .collect(joining(String.format(", %s",
                        System.lineSeparator())))
                );

        var json = String.format("{%s%s%s}",
                System.lineSeparator(),
                jsonFileds,
                System.lineSeparator()
        );

        return prettify ? json : json.replaceAll(System.lineSeparator(), "")
                .replaceAll(" {4}", "");
    }

    private String formatValue(final Obejct value) {

        return value instanceof String s ? String.format("\"%s\"", s) : value.toString();
    }
}
