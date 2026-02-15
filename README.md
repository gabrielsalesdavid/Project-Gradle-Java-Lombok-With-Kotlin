# Project-Gradle-Java-Lombok-With-Kotlin

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9-purple?style=flat-square&logo=kotlin)
![Gradle](https://img.shields.io/badge/Gradle-8.13-02303A?style=flat-square&logo=gradle)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

Um projeto de aprendizado demonstrando conceitos avançados de Java e Kotlin, incluindo processadores de anotações, Lombok, padrão Builder e Gradle com Kotlin DSL.

---

## 📑 Índice

1. [Estrutura do Projeto](#estrutura-do-projeto)
2. [Características Principais](#características-principais)
3. [Tecnologias Utilizadas](#tecnologias-utilizadas)
4. [Como Usar](#como-usar)
5. [Java - Fundamentos e Conceitos](#java---fundamentos-e-conceitos)
6. [Kotlin - Fundamentos e Conceitos](#kotlin---fundamentos-e-conceitos)
7. [Processamento em Tempo de Compilação](#processamento-em-tempo-de-compilação)
8. [Recursos Adicionais](#recursos-adicionais)
9. [Licença e Contribuições](#licença-e-contribuições)

---

## 🏗️ Estrutura do Projeto

```
.
├── annotation/              # Módulo de anotações customizadas
│   ├── build.gradle.kts
│   └── src/
│       └── main/java/br/com/dio/
│           └── IBuilder.java
│
├── processor/               # Módulo processador de anotações
│   ├── build.gradle.kts
│   └── src/
│       └── main/java/br/com/dio/
│           ├── BuilderProcessor.java
│           └── BuilderGenerator.java
│
├── app/                     # Módulo principal da aplicação
│   ├── build.gradle.kts
│   └── src/
│       ├── main/java/org/example/App.java
│       └── test/java/org/example/AppTest.java
│
├── sample/                  # Módulo com exemplos
│   ├── build.gradle.kts
│   └── src/
│       └── main/java/br/com/dio/
│           ├── Main.java
│           └── model/Person.java
│
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
│
├── settings.gradle.kts      # Configuração multi-projeto
├── build.gradle.kts         # Build config do projeto raiz
└── docs/                    # Documentação adicional
```

## ✨ Características Principais

### 1. **Anotações Customizadas**
- `@IBuilder`: Marca classes para geração automática de builders
- `@ASerializerType`: Anotação tipo para serialização
- `@ASerializerMethod`: Anotação método para serialização
- `EFieldFormatEnum`: Enum para formatos de campos

### 2. **Processador de Anotações**
- `SerializerProcessor`: Processa anotações em tempo de compilação
- `BuilderProcessor`: Gera automaticamente classes Builder
- `BuilderGenerator`: Lógica de geração do padrão Builder

### 3. **Lombok Integration**
```java
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;
    private String name;
}
```

### 4. **Padrão Builder**
```java
var person = new PersonBuilder()
    .id(1)
    .name("João")
    .build();
```

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| Java | 17 LTS | Linguagem de programação principal |
| Kotlin | 1.9.10+ | DSL para Gradle |
| Gradle | 8.13 | Sistema de build |
| Lombok | Última | Redução de boilerplate |
| JUnit | 5.11.3 | Framework de testes |
| Guava | 33.3.1-jre | Utilitários do Google |

## 📦 Dependências

### Principais
```gradle
dependencies {
    implementation("com.google.guava:guava:33.3.1-jre")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
}
```

### Desenvolvidas Localmente
- **annotation**: Definição de anotações customizadas
- **processor**: Processador de anotações
- **app**: Aplicação principal
- **sample**: Exemplos de uso

## 🚀 Como Usando

### Pré-requisitos
- Java 17 ou superior
- Gradle 8.13 ou superior (incluído via wrapper)

### Compilação

```bash
# Compilar o projeto inteiro
./gradlew build

# Compilar apenas um módulo
./gradlew :app:build
./gradlew :sample:build

# Limpararquivos compilados
./gradlew clean
```

### Execução

```bash
# Executar aplicação principal
./gradlew :app:run

# Executar exemplo (sample)
./gradlew :sample:run

# Com argumentos
./gradlew :sample:run --args="arg1 arg2"
```

### Testes

```bash
# Executar todos os testes
./gradlew test

# Executar testes de um módulo específico
./gradlew :app:test

# Testes com detalhes
./gradlew test --info
```

## 🧪 Exemplos de Uso

### Usar Anotação @IBuilder

1. **Marcar a classe**
```java
@IBuilder
public class Product {
    private Long id;
    private String name;
    private Double price;
}
```

2. **Compilar** (o processador gera ProductBuilder)
```bash
./gradlew build
```

3. **Usar o Builder gerado**
```java
Product product = new ProductBuilder()
    .id(1L)
    .name("Notebook")
    .price(3000.0)
    .build();
```

### Usar Lombok

```java
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
}

// Lombok gera automaticamente:
// - Getters para todas as propriedades
// - Setters para todas as propriedades
// - Método toString()
// - Construtor com todos os parâmetros
```

---

# Java - Fundamentos e Conceitos

## 📚 Desenvolvimento em Java

Este projeto utiliza **Java 17** como linguagem principal de desenvolvimento. Java é uma linguagem de programação orientada a objetos, robusta e altamente utilizada em aplicações empresariais.

## 🎯 Conceitos Principais do Java

### 1. **Programação Orientada a Objetos (POO)**

Java é uma linguagem puramente orientada a objetos que segue os princípios fundamentais:

- **Encapsulamento**: Proteção de dados internos da classe
- **Herança**: Reutilização de código através de hierarquia de classes
- **Polimorfismo**: Múltiplas formas de objetos
- **Abstração**: Trabalhar com abstrações ao invés de detalhes complexos

```java
// Exemplo: Classe com anotações Lombok
@Getter
@Setter
@ToString(callSuper = true)
public class Person {
    private int id;
    private String name;
}
```

### 2. **Anotações (Annotations)**

Anotações são metadados que fornecem informações sobre o programa sem fazer parte da lógica de execução.

```java
@Target(TYPE)
@Retention(SOURCE)
public @interface IBuilder {
}
```

**Características:**
- `@Target`: Define onde a anotação pode ser aplicada (TYPE, METHOD, FIELD, etc.)
- `@Retention`: Define por quanto tempo a anotação é retida (SOURCE, CLASS, RUNTIME)

### 3. **Processadores de Anotações (Annotation Processors)**

Permitem processar anotações em tempo de compilação para gerar código automaticamente.

```java
public class SerializerProcessor extends AbstractProcessor {
    // Processa anotações @ASerializerType e gera código
}
```

**Vantagens:**
- Reduz código boilerplate
- Valida código em tempo de compilação
- Gera código automaticamente

### 4. **Lombok - Redução de Boilerplate**

Lombok é uma biblioteca Java que utiliza anotações para reduzir código repetitivo.

**Anotações principais:**
- `@Getter`: Gera métodos getter automaticamente
- `@Setter`: Gera métodos setter automaticamente
- `@ToString`: Gera método toString()
- `@EqualsAndHashCode`: Gera métodos equals() e hashCode()
- `@AllArgsConstructor`: Gera construtor com todos os parâmetros
- `@NoArgsConstructor`: Gera construtor sem parâmetros

### 5. **Padrão Builder**

Pattern utilizado para criar objetos complexos passo a passo.

```java
var person = new PersonBuilder()
    .id(1)
    .name("João")
    .build();
```

**Benefícios:**
- Código mais legível
- Flexibilidade na criação de objetos
- Parametrização opcional
- Imutabilidade

### 6. **Genéricos**

Permitem tipos parametrizados para maior segurança e reutilização de código.

```java
List<String> names = new ArrayList<>();
```

### 7. **Streams e Expressões Lambda**

Programação funcional em Java para trabalhar com coleções.

```java
List<Person> adults = persons.stream()
    .filter(p -> p.getAge() >= 18)
    .collect(Collectors.toList());
```

## 🔧 Estrutura do Projeto Java

### Módulos Principais

1. **app**: Módulo da aplicação principal
2. **annotation**: Define anotações customizadas (`@IBuilder`)
3. **processor**: Processa anotações e gera builders
4. **sample**: Exemplos de uso das anotações

### Arquitetura

```
src/main/java/br/com/dio/
├── annotation/          # Anotações customizadas
│   ├── ASerializerType.java
│   ├── ASerializerMethod.java
│   └── EFieldFormatEnum.java
├── model/               # Modelos de dados
│   ├── Person.java
│   └── RUser.java
└── processor/           # Processadores de anotações
    └── SerializerProcessor.java
```

## ✨ Fundamentos Essenciais do Java

### Tipos de Dados

| Tipo | Tamanho | Descrição |
|------|--------|-----------|
| `int` | 4 bytes | Número inteiro |
| `long` | 8 bytes | Número inteiro grande |
| `float` | 4 bytes | Ponto flutuante |
| `double` | 8 bytes | Ponto flutuante duplo |
| `boolean` | 1 bit | Verdadeiro/Falso |
| `String` | Variável | Sequência de caracteres |

### Controle de Fluxo

```java
// If/Else
if (condition) {
    // código
} else {
    // código alternativo
}

// For loop
for (int i = 0; i < 10; i++) {
    // código
}

// While loop
while (condition) {
    // código
}

// Switch
switch (value) {
    case 1:
        // código
        break;
    default:
        // código padrão
}
```

### Tratamento de Exceções

```java
try {
    // código que pode lançar exceção
} catch (IOException e) {
    // tratar exceção
} finally {
    // executa sempre
}
```

## 🧪 Testes com JUnit 5

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    void testPersonCreation() {
        Person person = new Person();
        person.setId(1);
        person.setName("João");
        
        assertEquals(1, person.getId());
        assertEquals("João", person.getName());
    }
}
```

## 🎓 Exemplos Práticos de Java

### Criar uma classe com Lombok

```java
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
}
```

### Usar anotações personalizadas

```java
@IBuilder
public class Product {
    private String name;
    private Double price;
}

// Compilador gera automaticamente ProductBuilder
Product product = new ProductBuilder()
    .name("Notebook")
    .price(3000.0)
    .build();
```

### Stream com Lambda

```java
List<String> names = users.stream()
    .filter(user -> user.getAge() > 18)
    .map(User::getName)
    .sorted()
    .collect(Collectors.toList());
```

## 📖 Recursos Java Adicionais

- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [Project Lombok](https://projectlombok.org/)
- [Annotation Processors](https://docs.oracle.com/en/java/javase/17/docs/api/java.compiler/javax/annotation/processing/Processor.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Google Guava Library](https://github.com/google/guava)

## 📝 Melhores Práticas em Java

1. **Nomes significativos**: Use nomes claros para classes, métodos e variáveis
2. **Encapsulamento**: Proteja o estado interno das classes
3. **Reutilização de código**: Use herança e composição apropriadamente
4. **Tratamento de exceções**: Sempre trate exceções apropriadamente
5. **Testes automatizados**: Escreva testes para validar sua lógica
6. **Documentação**: Mantenha código e documentação atualizados
7. **Performance**: Considere complexidade de tempo e espaço

---

# Kotlin - Fundamentos e Conceitos

## 📚 Desenvolvimento com Kotlin

Kotlin é uma linguagem de programação moderna que roda na JVM (Java Virtual Machine) e é 100% interoperável com Java. Neste projeto, Kotlin é utilizado principalmente através do **Kotlin DSL (Domain Specific Language)** do Gradle para configuração de builds.

## 🎯 Conceitos Principais do Kotlin

### 1. **Kotlin DSL para Build (Gradle)**

Kotlin DSL oferece uma alternativa mais segura e expressiva ao Groovy DSL tradicional do Gradle.

```kotlin
plugins {
    application
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:33.3.1-jre")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
```

**Vantagens do Kotlin DSL:**
- **Segurança de tipo**: Erros detectados em tempo de compilação
- **Autocompletar**: IDEs oferecem melhor suporte
- **Refatoração**: Ferramentas conseguem refatorar código mais precisamente
- **Documentação**: Vinculada aos tipos reais

### 2. **Variáveis e Tipos**

Kotlin oferece inferência de tipos e suporta tipos explícitos.

```kotlin
// Inferência de tipo
val name = "João"              // String
var age = 18                   // Int (variável mutável)
val pi = 3.14159              // Double

// Tipos explícitos
val count: Int = 42
val message: String = "Hello"
val items: List<String> = listOf("a", "b", "c")
```

**Diferenças:**
- `val`: Variável imutável (read-only)
- `var`: Variável mutável

### 3. **Funções em Kotlin**

Kotlin simplifica a sintaxe de funções.

```kotlin
// Função simples
fun greet(name: String): String {
    return "Hello, $name"
}

// Expressão de função
fun greet(name: String) = "Hello, $name"

// Função com valores padrão
fun greet(name: String = "World"): String {
    return "Hello, $name"
}

// Argumentos nomeados
greet(name = "João")

// Função extension
fun String.isEmail(): Boolean {
    return this.contains("@")
}
```

### 4. **Lambda e Funções de Ordem Superior**

Funções que recebem ou retornam outras funções.

```kotlin
// Lambda simples
val add = { x: Int, y: Int -> x + y }

// Função de ordem superior
fun execute(operation: (Int, Int) -> Int, a: Int, b: Int): Int {
    return operation(a, b)
}

execute(add, 5, 3)  // 8

// Usando with() com lambda
with(person) {
    println("Nome: $name")
    println("Idade: $age")
}
```

### 5. **Classes e Objetos**

Kotlin oferece sintaxe mais concisa para classes.

```kotlin
// Classe com propriedades
class Person(val name: String, var age: Int) {
    fun greet() = "Olá, $name"
}

// Data class - para trabalhar com dados
data class User(val id: Int, val name: String, val email: String)

// Companion object - equivalente a static em Java
class MathUtils {
    companion object {
        fun add(a: Int, b: Int) = a + b
    }
}

MathUtils.add(5, 3)
```

**Vantagens:**
- Menos boilerplate que Java
- Getters/Setters gerados automaticamente
- Method names com properties

### 6. **Nulabilidade**

Kotlin diferencia tipos nulos de não-nulos (evita NullPointerException).

```kotlin
// Não pode ser nulo
val name: String = "João"

// Pode ser nulo
val nickname: String? = null

// Safe call
val length = nickname?.length

// Elvis operator
val nameToUse = nickname ?: "Desconhecido"

// Not null assertion (cuidado!)
val forcedLength = nickname!!.length
```

### 7. **Extension Functions**

Permitir adicionar funções a classes existentes sem herança.

```kotlin
// Adicionar método a String
fun String.isValidEmail(): Boolean {
    return this.matches(Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
}

"user@example.com".isValidEmail()  // true

// Property extension
val String.initials: String
    get() = split(" ").map { it.first() }.joinToString("")

"João Silva".initials  // JS
```

### 8. **Scope Functions**

Funções que executam um bloco de código no contexto de um objeto.

```kotlin
// let - trabalhar com nullable
person?.let {
    println("Nome: ${it.name}")
}

// also - referência this, para efeitos colaterais
val person = Person("João").also { 
    println("Criado: $it")
}

// apply - configurar objeto
val config = Config().apply {
    host = "localhost"
    port = 8080
}

// with - agrupar operações
with(person) {
    println("Nome: $name")
    println("Idade: $age")
}

// run - executar e retornar resultado
val result = run {
    val temp = calculateValue()
    temp * 2
}
```

## 🔧 Estrutura do Projeto Kotlin

### Configuração Gradle com Kotlin DSL

```kotlin
// settings.gradle.kts
rootProject.name = "Project-Gradle-Java-Lombok-With-Kotlin"
include("app")
include("annotation")
include("processor")
include("sample")

// build.gradle.kts (no diretório raiz)
plugins {
    id("java")
}

group = "br.com.di"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:33.1.0-jre")
}
```

### Estrutura de Versionamento

```kotlin
// gradle/libs.versions.toml
[versions]
guava = "33.3.1-jre"
junit-jupiter = "5.11.3"

[libraries]
guava = { module = "com.google.guava:guava", version.ref = "guava" }
junit-jupiter = { module = "org.junit.jupiter:junit-jupiter", version.ref = "junit-jupiter" }
```

## 🎓 Fundamentos Essenciais do Kotlin

### Coleções

```kotlin
// Lista (imutável)
val items = listOf(1, 2, 3)

// Lista mutável
val mutableItems = mutableListOf(1, 2, 3)
mutableItems.add(4)

// Mapa
val map = mapOf("a" to 1, "b" to 2)

// Set
val set = setOf(1, 2, 3, 2)  // {1, 2, 3}

// Operações úteis
items.filter { it > 1 }
items.map { it * 2 }
items.forEach { println(it) }
items.find { it == 2 }
```

### Control Flow

```kotlin
// If expression (retorna valor)
val max = if (a > b) a else b

// When expression (switch melhorado)
val result = when (value) {
    1 -> "Um"
    2 -> "Dois"
    3, 4 -> "Três ou Quatro"
    else -> "Outro"
}

// When com Range
val description = when (age) {
    in 0..12 -> "Criança"
    in 13..19 -> "Adolescente"
    else -> "Adulto"
}

// For loop
for (i in 1..10) { println(i) }
for (i in 1 until 10) { println(i) }  // 1-9
for (i in 10 downTo 1) { println(i) }
for (i in 1..10 step 2) { println(i) }
```

### String Templates

```kotlin
val name = "João"
val age = 18

println("Olá, $name")
println("Próximo ano terá ${age + 1}")
println("""
    Nome: $name
    Idade: $age
""".trimIndent())
```

## 🚀 Usando Kotlin com Gradle

### Compilar Kotlin com Gradle

```bash
# Compila tanto Java quanto Kotlin
./gradlew build

# Executa a aplicação
./gradlew :app:run

# Build apenas
./gradlew assemble

# Limpar build
./gradlew clean
```

## 📖 Diferenças Java vs Kotlin

| Aspecto | Java | Kotlin |
|--------|------|--------|
| Nulabilidade | Opcional | Parte do tipo |
| Propriedades | Getter/Setter | Automático |
| Extension Functions | Não | Sim |
| Lambdas | Verbose | Conciso |
| Data Classes | Boilerplate | Automático |
| Immutabilidade | val/var | Padrão com `val` |
| Strings | Simples | Templates |

## 🎯 Melhores Práticas em Kotlin

1. **Prefer val over var**: Use variáveis imutáveis quando possível
2. **Use type inference**: Deixe o compilador deduzir tipos
3. **Extension functions**: Escreva código mais expressivo
4. **Null safety**: Aproveite o sistema de nulabilidade
5. **Data classes**: Para trabalhar com dados
6. **Scope functions**: Para operações contextuais
7. **Documentação KDoc**: Similar a JavaDoc

## 📚 Recursos Kotlin Adicionais

- [Kotlin Official Documentation](https://kotlinlang.org/docs/)
- [Kotlin Playground](https://play.kotlinlang.org/)
- [Gradle Kotlin DSL Documentation](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- [Kotlin Koans - Exercícios Interativos](https://kotlinlang.org/docs/koans.html)
- [Google's Android Kotlin Style Guide](https://android.github.io/kotlin-guides/style.html)

## 🔗 Interoperabilidade Java-Kotlin

Uma das maiores forças do Kotlin é sua perfeita integração com Java.

```kotlin
// Chamar código Java do Kotlin
val guavaSet = Sets.newHashSet<String>()

// Classe Kotlin que pode ser usada em Java
class KotlinClass(val name: String) {
    fun greet() = "Hello, $name"
}

// Java chamar Kotlin
KotlinClass kotlin = new KotlinClass("João");
String greeting = kotlin.greet();
```

---

## 🔄 Processamento em Tempo de Compilação

Este projeto demonstra como funcionam processadores de anotações:

```
┌─────────────────────────────────────────────┐
│ Código Fonte com @Anotações               │
└─────────────────+───────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────┐
│ Javac inicia compilação                    │
└─────────────────+───────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────┐
│ Processadores de Anotações executam        │
│ - Analisam anotações                       │
│ - Geram novo código                        │
└─────────────────+───────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────┐
│ Compilação continua com código gerado      │
└─────────────────+───────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────┐
│ .class files gerados com tudo              │
└─────────────────────────────────────────────┘
```

## 🎓 Conceitos Aprendidos

### Java
- ✅ Programação orientada a objetos
- ✅ Anotações personalizadas
- ✅ Processadores de anotações
- ✅ Padrão Builder
- ✅ Genéricos e Type Erasure
- ✅ Streams e Lambdas
- ✅ Testes com JUnit 5

### Kotlin
- ✅ Interoperabilidade Java-Kotlin
- ✅ Kotlin DSL para Gradle
- ✅ Extension Functions
- ✅ Scope Functions
- ✅ Null Safety
- ✅ Data Classes
- ✅ Lambdas e Funções de Ordem Superior

### Gradle
- ✅ Build multi-projeto
- ✅ Kotlin DSL (Type-safe)
- ✅ Gerenciamento de dependências
- ✅ Versionamento com toml
- ✅ Toolchains Java

## 📖 Recursos Adicionais

- [Documentação Java Oficial](https://docs.oracle.com/en/java/)
- [Documentação Kotlin](https://kotlinlang.org/docs/)
- [Guia Gradle](https://docs.gradle.org/)
- [Project Lombok](https://projectlombok.org/)
- [Annotation Processing](https://docs.oracle.com/en/java/javase/17/docs/api/java.compiler/javax/annotation/processing/Processor.html)

## 💡 Melhores Práticas Gerais

1. **Usar `val` por padrão em Kotlin** - Prefira imutabilidade
2. **Type-safe builds** - Use Kotlin DSL ao invés de Groovy
3. **Extension Functions** - Adicione comportamento sem herança
4. **Null Safety** - Diferencie tipos nulos de não nulos
5. **Scope Functions** - Use `let`, `with`, `apply` apropriadamente
6. **Data Classes** - Para POJOs e DTOs
7. **Processadores de Anotações** - Reduza boilerplate

## 🔐 Licença

Este projeto está licenciado sob a MIT License - veja o arquivo LICENSE para detalhes.

## 👥 Contribuições

Contribuições são bem-vindas! Por favor:

1. Faça fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📧 Contato

Gabriel Sales David - [@gabrielsalesdavid](https://github.com/gabrielsalesdavid)

Projeto URL: [https://github.com/gabrielsalesdavid/Project-Gradle-Java-Lombok-With-Kotlin](https://github.com/gabrielsalesdavid/Project-Gradle-Java-Lombok-With-Kotlin)

---

<div align="center">

**Desenvolvido com ❤️ para aprendizado**

Última atualização: 15 de fevereiro de 2026

</div>
