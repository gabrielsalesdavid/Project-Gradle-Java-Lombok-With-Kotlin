# Conceitos Avançados de Java

## 1. Programação Orientada a Objetos (POO)

### 1.1 SOLID: Princípios de Design

#### Single Responsibility Principle (SRP)
Uma classe deve ter uma única responsabilidade:
```java
// ❌ Ruim: Classe faz múltiplas coisas
public class Usuario {
    public void salvarUsuario() { }
    public void enviarEmail() { }
    public void gerarRelatorio() { }
}

// ✓ Bom: Separação de responsabilidades
public class Usuario {
    public void salvarUsuario() { }
}

public class EmailService {
    public void enviarEmail(Usuario usuario) { }
}

public class RelatorioService {
    public void gerarRelatorio(Usuario usuario) { }
}
```

#### Open/Closed Principle (OCP)
Aberto para extensão, fechado para modificação:
```java
// ❌ Ruim: Precisa modificar a classe existente
public class CalculadoraDesconto {
    public double calcular(String tipo, double preco) {
        if (tipo.equals("blackfriday")) {
            return preco * 0.5;
        } else if (tipo.equals("junior")) {
            return preco * 0.2;
        }
        return preco;
    }
}

// ✓ Bom: Extensível sem modificação
public interface DescontoStrategy {
    double aplicarDesconto(double preco);
}

public class DescontoBlackFriday implements DescontoStrategy {
    @Override
    public double aplicarDesconto(double preco) {
        return preco * 0.5;
    }
}

public class CalculadoraDesconto {
    private DescontoStrategy strategy;
    
    public CalculadoraDesconto(DescontoStrategy strategy) {
        this.strategy = strategy;
    }
    
    public double calcular(double preco) {
        return strategy.aplicarDesconto(preco);
    }
}
```

#### Liskov Substitution Principle (LSP)
Objetos de classes derivadas podem substituir objetos da classe base:
```java
// ✓ Correto: Subclasse pode ser usada no lugar da superclasse
public class Ave {
    public void fazer_som() {
        System.out.println("Som de ave");
    }
}

public class Passaro extends Ave {
    @Override
    public void fazer_som() {
        System.out.println("Piu piu");
    }
}

Ave ave = new Passaro();
ave.fazer_som(); // Funciona perfeitamente
```

#### Interface Segregation Principle (ISP)
Muitas interfaces específicas são melhores que uma genérica:
```java
// ❌ Ruim: Interface gorda
public interface Funcionario {
    void trabalhar();
    void dirigir();
    void pilotar();
}

// ✓ Bom: Interfaces segregadas
public interface Trabalhador {
    void trabalhar();
}

public interface Motorista {
    void dirigir();
}

public interface Piloto {
    void pilotar();
}

public class Taxista implements Trabalhador, Motorista {
    @Override
    public void trabalhar() { }
    @Override
    public void dirigir() { }
}
```

#### Dependency Inversion Principle (DIP)
Dependa de abstrações, não de implementações concretas:
```java
// ❌ Ruim: Acoplamento alto
public class UsuarioService {
    private BancoDados bd = new BancoDados();
    
    public void salvar(Usuario usuario) {
        bd.inserir(usuario);
    }
}

// ✓ Bom: Desacoplado com injeção de dependência
public interface RepositorioUsuario {
    void inserir(Usuario usuario);
}

public class UsuarioService {
    private RepositorioUsuario repositorio;
    
    public UsuarioService(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }
    
    public void salvar(Usuario usuario) {
        repositorio.inserir(usuario);
    }
}
```

### 1.2 Padrões de Design (Design Patterns)

#### Singleton
Garante que apenas uma instância de uma classe existe:
```java
public class Configuracao {
    private static Configuracao instancia;
    
    private Configuracao() {}
    
    public static synchronized Configuracao getInstance() {
        if (instancia == null) {
            instancia = new Configuracao();
        }
        return instancia;
    }
}

// Uso
Configuracao config = Configuracao.getInstance();
```

#### Factory
Cria objetos sem especificar suas classes exatas:
```java
public interface Documento {
    void abrir();
}

public class DocumentoPDF implements Documento {
    @Override
    public void abrir() {
        System.out.println("Abrindo PDF");
    }
}

public class DocumentoWord implements Documento {
    @Override
    public void abrir() {
        System.out.println("Abrindo Word");
    }
}

public class DocumentoFactory {
    public static Documento criar(String tipo) {
        switch (tipo) {
            case "PDF":
                return new DocumentoPDF();
            case "WORD":
                return new DocumentoWord();
            default:
                throw new IllegalArgumentException("Tipo inválido");
        }
    }
}

// Uso
Documento doc = DocumentoFactory.criar("PDF");
doc.abrir();
```

#### Observer
Define uma relação um-para-muitos entre objetos:
```java
public interface Observador {
    void atualizar(String mensagem);
}

public class Assinante implements Observador {
    private String nome;
    
    public Assinante(String nome) {
        this.nome = nome;
    }
    
    @Override
    public void atualizar(String mensagem) {
        System.out.println(nome + " recebeu: " + mensagem);
    }
}

public class Publicador {
    private List<Observador> observadores = new ArrayList<>();
    
    public void inscrever(Observador obs) {
        observadores.add(obs);
    }
    
    public void notificar(String mensagem) {
        for (Observador obs : observadores) {
            obs.atualizar(mensagem);
        }
    }
}

// Uso
Publicador pub = new Publicador();
pub.inscrever(new Assinante("João"));
pub.inscrever(new Assinante("Maria"));
pub.notificar("Novo conteúdo!");
```

#### Strategy
Encapsula algoritmos em classes:
```java
public interface EstrategiaOrdencao {
    void ordenar(int[] array);
}

public class OrdencaoBubbleSort implements EstrategiaOrdencao {
    @Override
    public void ordenar(int[] array) {
        // Implementação do Bubble Sort
    }
}

public class OrdencaoQuickSort implements EstrategiaOrdencao {
    @Override
    public void ordenar(int[] array) {
        // Implementação do Quick Sort
    }
}

public class Sorter {
    private EstrategiaOrdencao estrategia;
    
    public Sorter(EstrategiaOrdencao estrategia) {
        this.estrategia = estrategia;
    }
    
    public void executar(int[] array) {
        estrategia.ordenar(array);
    }
}
```

## 2. Generics (Genéricos)

### 2.1 Definição Básica
```java
// Classe genérica
public class Caixa<T> {
    private T item;
    
    public void colocar(T item) {
        this.item = item;
    }
    
    public T tirar() {
        return item;
    }
}

// Uso
Caixa<String> caixaString = new Caixa<>();
caixaString.colocar("Olá");
String valor = caixaString.tirar();

Caixa<Integer> caixaInt = new Caixa<>();
caixaInt.colocar(42);
int num = caixaInt.tirar();
```

### 2.2 Wildcard
```java
// ? estende: aceita tipos que herdam de X
public void processar(List<? extends Number> lista) {
    for (Number num : lista) {
        System.out.println(num);
    }
}

// ? super: aceita tipos que são superclasses de X
public void adicionar(List<? super Integer> lista) {
    lista.add(10);
}
```

## 3. Anotações (Annotations)

### 3.1 Anotações Padrão
```java
public class Pessoa {
    @Deprecated
    public void metodoAntigo() { }
    
    @Override
    public String toString() {
        return "Pessoa";
    }
    
    @SuppressWarnings("unchecked")
    public void metodoComWarning() { }
}
```

### 3.2 Anotações Customizadas
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Validar {
    String valor() default "padrão";
}

public class MinhaClasse {
    @Validar("importante")
    public void meuMetodo() { }
}
```

## 4. Reflexão (Reflection)

```java
// Obtendo informações sobre a classe em tempo de execução
Class<?> classe = Pessoa.class;

// Ou
Class<?> classe = Class.forName("com.exemplo.Pessoa");

// Obtendo atributos
Field[] atributos = classe.getDeclaredFields();
for (Field field : atributos) {
    System.out.println(field.getName());
}

// Obtendo métodos
Method[] metodos = classe.getDeclaredMethods();
for (Method method : metodos) {
    System.out.println(method.getName());
}

// Criando instância dinamicamente
Constructor<?> construtor = classe.getConstructor(String.class, int.class);
Object instancia = construtor.newInstance("João", 30);

// Invocando método dinamicamente
Method metodo = classe.getMethod("apresentar");
metodo.invoke(instancia);
```

## 5. Streams e Programação Funcional

```java
List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

// Map: transformar
List<Integer> dobrados = numeros.stream()
    .map(n -> n * 2)
    .collect(Collectors.toList());

// Filter: filtrar
List<Integer> pares = numeros.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());

// Reduce: agregar
int soma = numeros.stream()
    .reduce(0, (a, b) -> a + b);

// ForEach: iterar
numeros.stream()
    .forEach(System.out::println);

// FindFirst: primeiro elemento
Optional<Integer> primeiro = numeros.stream()
    .filter(n -> n > 3)
    .findFirst();

// AllMatch, AnyMatch, NoneMatch
boolean todosPositivos = numeros.stream()
    .allMatch(n -> n > 0);
```

## 6. Optional

```java
// Evitar NullPointerException
Optional<String> nome = Optional.ofNullable(obterNome());

// Se presente, fazer algo
nome.ifPresent(System.out::println);

// Se presente ou gerar padrão
String resultado = nome.orElse("Desconhecido");

// Se presente ou lançar exceção
String valor = nome.orElseThrow(() -> new Exception("Nome não encontrado"));

// Map e flatMap com Optional
Optional<Integer> tamanho = nome.map(String::length);
```

## 7. Concorrência

### 7.1 Thread
```java
public class MinhaThread extends Thread {
    @Override
    public void run() {
        System.out.println("Executando em thread");
    }
}

// Ou usando Runnable
Runnable tarefa = () -> System.out.println("Executando tarefa");
Thread thread = new Thread(tarefa);
thread.start();
```

### 7.2 Synchronized
```java
public synchronized void metodoSeguro() {
    // Apenas uma thread executa por vez
}

// Ou
public void metodo() {
    synchronized (this) {
        // Seção crítica
    }
}
```

### 7.3 ExecutorService
```java
ExecutorService executor = Executors.newFixedThreadPool(3);

for (int i = 0; i < 10; i++) {
    executor.submit(() -> System.out.println("Tarefa"));
}

executor.shutdown();
executor.awaitTermination(10, TimeUnit.SECONDS);
```

## 8. Tratamento de Exceções Avançado

```java
// Try-with-resources (AutoCloseable)
try (FileReader fr = new FileReader("arquivo.txt")) {
    // Arquivo será fechado automaticamente
} catch (FileNotFoundException e) {
    System.err.println("Arquivo não encontrado");
} catch (IOException e) {
    System.err.println("Erro ao ler arquivo");
}

// Multi-catch
try {
    // código
} catch (IOException | SQLException e) {
    System.err.println("Erro: " + e.getMessage());
}
```

## 9. Lambda Expressions

```java
// Função simples
Comparator<String> comparador = (s1, s2) -> s1.compareTo(s2);

// Com múltiplas linhas
Runnable r = () -> {
    System.out.println("Linha 1");
    System.out.println("Linha 2");
};

// Method reference
List<String> lista = Arrays.asList("a", "b", "c");
lista.forEach(System.out::println);

// Expressões funcionais
Function<Integer, Integer> quadrado = x -> x * x;
Integer resultado = quadrado.apply(5); // 25
```

## 10. Module System (Java 9+)

```java
// module-info.java
module com.exemplo {
    exports com.exemplo.api;
    requires java.base;
    requires com.outromodulo;
}
```

## Conclusão

Esses conceitos formam a base para escrever código Java profissional, escalável e mantenível. A prática e o estudo contínuo são essenciais para dominar esses tópicos.
