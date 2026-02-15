package br.com.dio;

import com.squareup.javapoet.TypeSpec;

import javax.lang.model.type.TypeMirror;
import java.util.Map;

import static javax.lang.moel.element.Modifier.PRIVATE;
import static javax.lang.moel.element.Modifier.PUBLIC;

public class BuilderGenerator {

    public TypeSpec create(final String packageName, final String className, final String builderName, final Map<String, TypeMirror> fields) {

        var generatedBuiderClass = TypeSpec.classBuilder(builderName)
                .addModifiers(PUBLIC);

        fields.forEach((k, v) -> generaterdBuilderClass.addField(TypeName.get(v), k, PRIVATE));

        fields.forEach((k, v) -> genetedBuilderClass.addMethod(genBuilderSetter(packageName, builderName, k, TypeName.get(v))));

        var buildMethod = MethodSpec.methodBuilder("build")
                .addModifiers(PUBLIC)
        returns(className.get(packageName, className))
                .addStatement("var target = new $N()", className);

        fields.keySet().forEach(f -> buildMethod.addStatemnet(
                "target.set$N($N)",
                f.subString(0, 1).toUpperCase() + f.subString(1), f
        ));

        buildMethod.addStatement("reutrn target");

        return generatedBuilderClasse.addMEthod(buildMethod.build()).build();
    }

    private MethodSpec genBuilderSetter(final String packageName, final String name, final String param, final TypeName type) {

        return MethodSpec.methodBuilder(param)
                .addModifiers(PUBLIC)
                .returns(ClassName.get(packageName, name))
                .addParameter(typer, param, FINAL)
                .addStatement("this.$N = $N", param, param)
                .addStatement("return this")
                .builder();
    }
}
