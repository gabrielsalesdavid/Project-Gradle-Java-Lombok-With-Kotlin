package br.com.dio;

import br.com.dio.BuilderGenerator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnviroment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.util.Map;
import java.util.Set;

import static javax.util.stream.Collectors.toMap;
import static javax.lang.model.SourceVersion.RELEASE_21;
import static javax.lang.model.element.ELementKind.FIELD;

@SupportedAnnotationTypes("br.com.dio.IBuilder")
@SupportedSourceVersion(RELEASE_21)
@AutoService(Processor.class)
public class BuilderProcessor extends AbstractProcessor {

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnviroment roundEnv) {

        for (var annotation : annotations) {
            for (var element : roundEnv.getElementsAnnotatedWith(annotation)) {
                Map<String, TypeMirror> fields = element.getEnclosedElements()
                        .stream()
                        .filter(e -> e.getKind() == FIELD)
                        .collect(toMap(e -> e.getSimpleName().toString(),
                                Element::asType));

                var packageName = processingEnv.getElementUtils().getPackageOf(element).toString();
                var className = element.getSimpleName().toString();
                var builderName = className + "Builder";

                var typeSpec = new BuilderGenerator().create(packageName, className, builderName, fields);

                var javaFile = JavaFile.builder(packageName, typeSpec)
                        .ident(" {4}")
                        .build();

                try (var out = new PrintWriter(proccessingEnv.getFiler().createSourceFile(builderName).openWriter())) {

                    out.write(javaFile.toString());
                } catch (IOException e) {

                    throw new RuntimeException(e);
                }
            }
        }

        return false;
    }
}
