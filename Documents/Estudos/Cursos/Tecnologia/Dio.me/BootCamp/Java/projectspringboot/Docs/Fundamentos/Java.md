# Fundamentos de Java

## 1. Introdução

Java é uma linguagem de programação orientada a objetos, multiplataforma e amplamente usada no desenvolvimento de aplicações empresariais. Desenvolvida pela Sun Microsystems em 1995, Java permite que os programadores "escrevam uma vez, execute em qualquer lugar" (Write Once, Run Anywhere - WORA).

### Características Principais
- **Orientação a Objetos**: Tudo em Java é um objeto
- **Multiplataforma**: Funciona em qualquer sistema operacional com JVM
- **Segura**: Gerenciamento automático de memória e verificação de tipos
- **Robusta**: Tratamento de exceções e verificação de erros em tempo de compilação
- **Compilada e Interpretada**: Compilada para bytecode, interpretada pela JVM

## 2. Ambiente de Desenvolvimento

### Requisitos
- **JDK (Java Development Kit)**: Necessário para desenvolver aplicações Java
- **IDE**: IntelliJ IDEA, Eclipse, VS Code com extensões
- **Gerenciador de Dependências**: Maven ou Gradle

### Configuração do JDK
```bash
# Verificar versão do Java instalada
java -version
javac -version

# Variáveis de ambiente
JAVA_HOME=/caminho/para/jdk
PATH=$JAVA_HOME/bin:$PATH
```

## 3. Tipos de Dados

Java possui dois tipos principais de dados:

### Tipos Primitivos
- **Numéricos Inteiros**: `byte`, `short`, `int`, `long`
- **Numéricos Decimais**: `float`, `double`
- **Caractere**: `char`
- **Booleano**: `boolean`

### Tipos de Referência
- **Strings**: `String`
- **Arrays**: `int[]`, `String[]`
- **Classes**: Qualquer classe criada
- **Interfaces**: Contrato entre classes

## 4. Variáveis e Escopo

### Declaração de Variáveis
```java
// Tipo nome = valor;
int idade = 25;
String nome = "João";
double salario = 3500.50;
boolean ativo = true;
```

### Escopo de Variáveis
- **Local**: Dentro de métodos ou blocos
- **Instância**: Pertence a um objeto específico
- **Classe**: Compartilhada por todas as instâncias (static)

## 5. Operadores

### Operadores Aritméticos
```java
+ - * / %  // Adição, Subtração, Multiplicação, Divisão, Resto
```

### Operadores de Comparação
```java
== != < > <= >=  // Igualdade, Desigualdade, Comparações
```

### Operadores Lógicos
```java
&& || !  // E, OU, NÃO
```

## 6. Estruturas de Controle

### Condicional IF-ELSE
```java
if (condicao) {
    // Código executado se verdadeiro
} else if (outraCondicao) {
    // Código executado se outra condição é verdadeira
} else {
    // Código executado se nenhuma condição é verdadeira
}
```

### Switch
```java
switch (valor) {
    case 1:
        // Código
        break;
    case 2:
        // Código
        break;
    default:
        // Código padrão
}
```

### Loops

#### FOR
```java
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}

// For-Each
for (String elemento : lista) {
    System.out.println(elemento);
}
```

#### WHILE
```java
while (condicao) {
    // Código repetido enquanto a condição é verdadeira
}
```

#### DO-WHILE
```java
do {
    // Código executado pelo menos uma vez
} while (condicao);
```

## 7. Orientação a Objetos

### Classes e Objetos
```java
public class Pessoa {
    // Atributos (propriedades)
    private String nome;
    private int idade;
    
    // Construtor
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    // Métodos (comportamentos)
    public void apresentar() {
        System.out.println("Olá, meu nome é " + nome);
    }
    
    // Getters e Setters
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
}
```

### Pilares da OOP

#### Encapsulamento
Proteger dados da classe usando modificadores de acesso:
- `public`: Acessível de qualquer lugar
- `private`: Acessível apenas na classe
- `protected`: Acessível na classe e subclasses
- `default` (sem modificador): Acessível no mesmo pacote

#### Herança
```java
public class Funcionario extends Pessoa {
    private double salario;
    
    // Construtor
    public Funcionario(String nome, int idade, double salario) {
        super(nome, idade); // Chama construtor da classe pai
        this.salario = salario;
    }
}
```

#### Polimorfismo
```java
// Sobrescrita de método
@Override
public void apresentar() {
    System.out.println("Sou um funcionário");
}

// Sobrecarga de método
public void calcularBonus(double percentual) { }
public void calcularBonus(int dias) { }
```

#### Abstração
```java
public abstract class Animal {
    abstract void fazer_som();
    
    public void dormir() {
        System.out.println("Zzzzz");
    }
}
```

## 8. Métodos

### Estrutura de um Método
```java
[modificadores] [tipo_retorno] [nome_metodo] ([parâmetros]) {
    // Corpo do método
    return [valor]; // Se tipo_retorno não for void
}
```

### Exemplo
```java
public double calcularMedia(double nota1, double nota2) {
    return (nota1 + nota2) / 2;
}
```

## 9. Arrays e Coleções

### Arrays
```java
// Declaração e inicialização
int[] numeros = new int[5];
String[] nomes = {"João", "Maria", "Pedro"};

// Acessar elementos
System.out.println(nomes[0]); // João
```

### Coleções (Java Collections Framework)
```java
// List
List<String> lista = new ArrayList<>();
lista.add("elemento");

// Set
Set<String> conjunto = new HashSet<>();
conjunto.add("elemento");

// Map
Map<String, Integer> mapa = new HashMap<>();
mapa.put("chave", 10);
```

## 10. Tratamento de Exceções

```java
try {
    // Código que pode gerar exceção
    int resultado = 10 / 0;
} catch (ArithmeticException e) {
    // Trata a exceção específica
    System.out.println("Erro: divisão por zero");
} catch (Exception e) {
    // Trata qualquer outra exceção
    System.out.println("Erro genérico: " + e.getMessage());
} finally {
    // Sempre executado
    System.out.println("Finalizando");
}
```

## 11. Pacotes e Imports

### Organização com Pacotes
```java
package com.exemplo.minhaAplicacao;

import java.util.ArrayList;
import java.util.List;
```

### Convenção de Nomes
- Pacotes: `com.empresa.projeto.modulo` (com letras minúsculas)
- Classes: `MinhaClasse` (CamelCase)
- Métodos: `meuMetodo()` (camelCase)
- Constantes: `MINHA_CONSTANTE` (MAIÚSCULAS)

## 12. Boas Práticas

1. **Legibilidade**: Código claro e bem comentado
2. **DRY (Don't Repeat Yourself)**: Evitar duplicação de código
3. **SOLID**: Princípios de design de software
4. **Nomeação**: Nomes significativos para variáveis, métodos e classes
5. **Tratamento de Erros**: Sempre tratar exceções apropriadamente
6. **Documentação**: Usar JavaDoc para documentar classes e métodos
7. **Testes**: Escrever testes unitários para validar o código

## Conclusão

Os fundamentos de Java fornecem a base necessária para desenvolver aplicações robustas e escaláveis. Compreender esses conceitos é essencial para progredir em frameworks como Spring Boot e em arquiteturas mais complexas.

## Referências

- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [Java Language Specification](https://docs.oracle.com/javase/specs/)
