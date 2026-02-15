# Fundamentos de Java

## 1. Introdução

Java é uma linguagem de programação orientada a objetos, compilada para bytecode e executada pela Java Virtual Machine (JVM). Criada por James Gosling em 1995, é amplamente utilizada em aplicações empresariais e possui o princípio "Write Once, Run Anywhere" (WORA).

## 2. Características Principais

### 2.1 Paradigma Orientado a Objetos
- **Classes e Objetos**: Estruturas que encapsulam dados (atributos) e comportamentos (métodos)
- **Herança**: Permite que uma classe herde atributos e métodos de outra
- **Polimorfismo**: Capacidade de um objeto assumir múltiplas formas
- **Encapsulamento**: Dados privados com acesso controlado por métodos públicos

### 2.2 Tipagem Forte
Java é uma linguagem fortemente tipada, requerendo declaração explícita de tipos:
```java
int numero = 10;
String texto = "Olá";
double valor = 3.14;
```

### 2.3 Gerenciamento de Memória
- Coleta automática de lixo (Garbage Collection)
- Não necessita liberar memória manualmente
- Melhora segurança e reduz bugs

## 3. Tipos de Dados

### 3.1 Primitivos
```java
byte b = 127;           // 8 bits
short s = 32767;        // 16 bits
int i = 2147483647;     // 32 bits
long l = 9223372036L;   // 64 bits
float f = 3.14f;        // 32 bits decimais
double d = 3.14;        // 64 bits decimais
boolean bool = true;    // true/false
char c = 'A';           // 16 bits Unicode
```

### 3.2 Objetos (Referência)
```java
String str = "Texto";
Integer intObj = 10;
List<String> lista = new ArrayList<>();
```

## 4. Estruturas de Controle

### 4.1 Condicionais
```java
// if-else
if (idade >= 18) {
    System.out.println("Maior de idade");
} else {
    System.out.println("Menor de idade");
}

// switch
switch (dia) {
    case 1:
        System.out.println("Segunda");
        break;
    case 2:
        System.out.println("Terça");
        break;
    default:
        System.out.println("Outro dia");
}
```

### 4.2 Loops
```java
// for tradicional
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}

// while
int contador = 0;
while (contador < 5) {
    System.out.println(contador);
    contador++;
}

// do-while
do {
    System.out.println("Executado");
} while (false);

// for-each
String[] nomes = {"Ana", "Bruno", "Carlos"};
for (String nome : nomes) {
    System.out.println(nome);
}
```

## 5. Classes e Objetos

### 5.1 Definição de Classe
```java
public class Pessoa {
    // Atributos (variáveis de instância)
    private String nome;
    private int idade;
    
    // Construtor
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    // Métodos
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

### 5.2 Criando Objetos
```java
Pessoa pessoa = new Pessoa("João", 30);
pessoa.apresentar();
```

## 6. Herança e Polimorfismo

### 6.1 Herança
```java
// Classe pai
public class Animal {
    public void fazer_som() {
        System.out.println("Som genérico");
    }
}

// Classe filha
public class Cachorro extends Animal {
    @Override
    public void fazer_som() {
        System.out.println("Au au!");
    }
}
```

### 6.2 Polimorfismo
```java
Animal animal = new Cachorro();
animal.fazer_som(); // Imprime: Au au!
```

## 7. Interfaces e Classes Abstratas

### 7.1 Interface
```java
public interface Veiculo {
    void acelerar();
    void frear();
}

public class Carro implements Veiculo {
    @Override
    public void acelerar() {
        System.out.println("Carro acelerando");
    }
    
    @Override
    public void frear() {
        System.out.println("Carro freando");
    }
}
```

### 7.2 Classe Abstrata
```java
public abstract class Forma {
    abstract double calcularArea();
    
    public void exibir() {
        System.out.println("Exibindo forma");
    }
}
```

## 8. Exceções

### 8.1 Tratamento de Exceções
```java
try {
    int resultado = 10 / 0; // Lança ArithmeticException
} catch (ArithmeticException e) {
    System.out.println("Erro: " + e.getMessage());
} catch (Exception e) {
    System.out.println("Erro genérico");
} finally {
    System.out.println("Executado sempre");
}
```

### 8.2 Lançando Exceções
```java
public void validarIdade(int idade) throws IllegalArgumentException {
    if (idade < 0) {
        throw new IllegalArgumentException("Idade não pode ser negativa");
    }
}
```

## 9. Coleções

### 9.1 ArrayList
```java
List<String> lista = new ArrayList<>();
lista.add("Item 1");
lista.add("Item 2");
lista.remove(0);
```

### 9.2 HashMap
```java
Map<String, Integer> mapa = new HashMap<>();
mapa.put("Ana", 25);
mapa.put("Bruno", 30);
int idade = mapa.get("Ana"); // 25
```

### 9.3 Set
```java
Set<String> conjunto = new HashSet<>();
conjunto.add("A");
conjunto.add("B");
conjunto.add("A"); // Não será adicionado (duplicata)
```

## 10. Métodos Úteis

### 10.1 String
```java
String texto = "Olá Mundo";
String maiuscula = texto.toUpperCase();      // OLÁ MUNDO
String minuscula = texto.toLowerCase();      // olá mundo
int tamanho = texto.length();                // 9
boolean contem = texto.contains("Mundo");    // true
String[] partes = texto.split(" ");          // ["Olá", "Mundo"]
```

### 10.2 Array
```java
int[] numeros = {1, 2, 3, 4, 5};
Arrays.sort(numeros);
boolean existe = Arrays.asList(numeros).contains(3);
```

## 11. Modificadores de Acesso

| Modificador | Classe | Pacote | Subclasse | Mundo |
|------------|--------|--------|-----------|-------|
| public     | ✓      | ✓      | ✓         | ✓     |
| protected  | ✓      | ✓      | ✓         | ✗     |
| package    | ✓      | ✓      | ✗         | ✗     |
| private    | ✓      | ✗      | ✗         | ✗     |

## 12. Principais Boas Práticas

1. **Nomenclatura**: Use camelCase para variáveis e métodos, PascalCase para classes
2. **Encapsulamento**: Mantenha atributos privados com getters/setters
3. **DRY**: Evite repetição de código (Don't Repeat Yourself)
4. **SOLID**: Siga princípios de design orientado a objetos
5. **Documentação**: Use JavaDoc para documentar classes e métodos
6. **Testes**: Escreva testes unitários para validar funcionalidades

## 13. Referências Úteis

- [Documentação Oficial Java](https://docs.oracle.com/javase/)
- [Java SE 17 API Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Java Language Specification](https://docs.oracle.com/javase/specs/jls/se17/html/index.html)
