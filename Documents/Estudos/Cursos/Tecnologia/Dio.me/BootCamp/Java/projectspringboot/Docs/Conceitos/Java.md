# Conceitos Avançados de Java

## 1. Programação Orientada a Objetos (OOP)

### 1.1 Conceitos Fundamentais

A Programação Orientada a Objetos é um paradigma que organiza o software como uma coleção de objetos que contêm dados (estado) e código (comportamento).

### 1.2 Classes Abstratas vs Interfaces

#### Classes Abstratas
```java
public abstract class Veiculo {
    // Atributos
    protected String marca;
    
    // Método abstrato
    public abstract void acelerar();
    
    // Método concreto
    public void parar() {
        System.out.println("Veículo parado");
    }
}
```

**Características**:
- Não podem ser instanciadas diretamente
- Podem ter atributos e métodos com implementação
- Podem ter construtores
- Usadas para herança com especialização

#### Interfaces
```java
public interface Transportavel {
    void mover();
    void parar();
}

public class Carro implements Transportavel {
    @Override
    public void mover() {
        System.out.println("Carro movendo");
    }
    
    @Override
    public void parar() {
        System.out.println("Carro parado");
    }
}
```

**Características**:
- Contêm apenas assinaturas de métodos (Java 8+)
- Definem um contrato que classes precisam implementar
- Permitem múltiplas implementações (implementação múltipla de interface)
- A partir do Java 8, podem ter métodos default e static

### 1.3 Composição vs Herança

#### Herança
```java
public class Animal {
    public void fazer_som() {}
}

public class Cachorro extends Animal {
    @Override
    public void fazer_som() {
        System.out.println("Au au!");
    }
}
```

#### Composição (Preferida em muitos casos)
```java
public class Cachorro {
    private Rabo rabo;
    private Orelha orelha;
    
    public void fazer_som() {
        System.out.println("Au au!");
    }
}
```

**Vantagens da Composição**:
- Mais flexível que herança
- Evita problemas de múltipla herança
- Melhor para aplicar o Princípio de Substituição de Liskov

## 2. Design Patterns

### 2.1 Padrões Criacionais

#### Singleton
```java
public class ConexaoDB {
    private static ConexaoDB instancia;
    
    private ConexaoDB() {}
    
    public static ConexaoDB getInstance() {
        if (instancia == null) {
            instancia = new ConexaoDB();
        }
        return instancia;
    }
}

// Singleton Eager
public class ConexaoDBEager {
    private static final ConexaoDB instancia = new ConexaoDB();
    
    public static ConexaoDB getInstance() {
        return instancia;
    }
}
```

#### Factory
```java
public interface Banco {
    void conectar();
}

public class BancoFactory {
    public static Banco criarBanco(String tipo) {
        if (tipo.equals("MySQL")) {
            return new MySQL();
        } else if (tipo.equals("PostgreSQL")) {
            return new PostgreSQL();
        }
        return null;
    }
}
```

#### Builder
```java
public class Usuario {
    private String nome;
    private String email;
    private int idade;
    
    private Usuario(UsuarioBuilder builder) {
        this.nome = builder.nome;
        this.email = builder.email;
        this.idade = builder.idade;
    }
    
    public static class UsuarioBuilder {
        private String nome;
        private String email;
        private int idade;
        
        public UsuarioBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }
        
        public UsuarioBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public UsuarioBuilder idade(int idade) {
            this.idade = idade;
            return this;
        }
        
        public Usuario build() {
            return new Usuario(this);
        }
    }
}

// Uso
Usuario usuario = new Usuario.UsuarioBuilder()
    .nome("João")
    .email("joao@example.com")
    .idade(25)
    .build();
```

### 2.2 Padrões Estruturais

#### Adapter
```java
// Interface esperada
public interface BancoModerno {
    void conectarSeguro();
}

// Classe legada
public class BancoAntigo {
    public void conectar() {
        System.out.println("Conexão insegura");
    }
}

// Adapter
public class AdaptadorBanco implements BancoModerno {
    private BancoAntigo bancoAntigo;
    
    public AdaptadorBanco(BancoAntigo bancoAntigo) {
        this.bancoAntigo = bancoAntigo;
    }
    
    @Override
    public void conectarSeguro() {
        // Adiciona segurança
        bancoAntigo.conectar();
    }
}
```

#### Decorator
```java
public interface Componente {
    String operacao();
}

public class ComponenteConcreto implements Componente {
    @Override
    public String operacao() {
        return "Componente básico";
    }
}

public abstract class Decorador implements Componente {
    protected Componente componente;
    
    public Decorador(Componente componente) {
        this.componente = componente;
    }
}

public class DecoradorConcreto extends Decorador {
    public DecoradorConcreto(Componente componente) {
        super(componente);
    }
    
    @Override
    public String operacao() {
        return componente.operacao() + " + Decoração";
    }
}
```

### 2.3 Padrões Comportamentais

#### Strategy
```java
public interface EstrategiaOrdenacao {
    void ordenar(int[] array);
}

public class QuickSort implements EstrategiaOrdenacao {
    @Override
    public void ordenar(int[] array) {
        System.out.println("Ordenando com QuickSort");
    }
}

public class MergeSort implements EstrategiaOrdenacao {
    @Override
    public void ordenar(int[] array) {
        System.out.println("Ordenando com MergeSort");
    }
}

public class Classificador {
    private EstrategiaOrdenacao estrategia;
    
    public Classificador(EstrategiaOrdenacao estrategia) {
        this.estrategia = estrategia;
    }
    
    public void classificar(int[] array) {
        estrategia.ordenar(array);
    }
}
```

#### Observer
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
    
    public void adicionar(Observador obs) {
        observadores.add(obs);
    }
    
    public void remover(Observador obs) {
        observadores.remove(obs);
    }
    
    public void notificar(String mensagem) {
        for (Observador obs : observadores) {
            obs.atualizar(mensagem);
        }
    }
}
```

## 3. Programação Funcional

### 3.1 Expressões Lambda (Java 8+)

```java
// Sintaxe básica: (parâmetros) -> { corpo }

List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

// Sem Lambda
numeros.forEach(new Consumer<Integer>() {
    @Override
    public void accept(Integer num) {
        System.out.println(num);
    }
});

// Com Lambda
numeros.forEach(num -> System.out.println(num));
```

### 3.2 Streams

```java
List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

// Map
List<Integer> dobrados = numeros.stream()
    .map(n -> n * 2)
    .collect(Collectors.toList());

// Filter
List<Integer> pares = numeros.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());

// Reduce
Integer soma = numeros.stream()
    .reduce(0, Integer::sum);

// Operações encadeadas
List<String> nomes = Arrays.asList("João", "Maria", "Pedro");
List<String> maiusculas = nomes.stream()
    .filter(nome -> nome.length() > 4)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

### 3.3 Interfaces Funcionais

```java
@FunctionalInterface
public interface Calculadora {
    int executar(int a, int b);
}

// Uso
Calculadora soma = (a, b) -> a + b;
Calculadora subtracao = (a, b) -> a - b;
```

## 4. Genéricos

### 4.1 Classes Genéricas

```java
public class Caixa<T> {
    private T item;
    
    public void guardar(T item) {
        this.item = item;
    }
    
    public T obter() {
        return item;
    }
}

// Uso
Caixa<String> caixaString = new Caixa<>();
caixaString.guardar("Olá");
String mensagem = caixaString.obter();

Caixa<Integer> caixaInteira = new Caixa<>();
caixaInteira.guardar(42);
Integer numero = caixaInteira.obter();
```

### 4.2 Wildcard e Bounds

```java
// Covariance (? extends)
public void processar(List<? extends Numero> numeros) {
    for (Numero num : numeros) {
        System.out.println(num);
    }
}

// Contravariance (? super)
public void preencher(List<? super Integer> lista) {
    lista.add(10);
    lista.add(20);
}

// Unbounded
public <T> void imprimir(T elemento) {
    System.out.println(elemento);
}
```

## 5. Anotações

### 5.1 Anotações Integradas

```java
@Deprecated // Indica que o código está obsoleto
public void metodoAntigo() { }

@SuppressWarnings("unchecked") // Suprime avisos do compilador
public List getMinha Lista() {
    return new ArrayList(); // Aviso de tipo bruto
}

@Override // Valida que está sobrescrevendo um método
public String toString() {
    return "Minha classe";
}
```

### 5.2 Anotações Customizadas

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MinhaAnotacao {
    String valor() default "";
    int numero() default 0;
}

public class MinhaClasse {
    @MinhaAnotacao(valor = "teste", numero = 42)
    public void meuMetodo() { }
}
```

## 6. Reflection

```java
public class ReflectionExemplo {
    public static void main(String[] args) throws Exception {
        // Obter a classe
        Class<?> classe = Class.forName("com.exemplo.Pessoa");
        
        // Obter construtores
        Constructor<?>[] construtores = classe.getDeclaredConstructors();
        
        // Obter métodos
        Method[] metodos = classe.getDeclaredMethods();
        
        // Obter atributos
        Field[] atributos = classe.getDeclaredFields();
        
        // Invocar método
        Object instancia = construtores[0].newInstance();
        Method metodo = classe.getMethod("getNome");
        String resultado = (String) metodo.invoke(instancia);
    }
}
```

## 7. Concorrência

### 7.1 Threads

```java
// Implementando Runnable (Preferido)
public class MinhaThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread executando");
    }
}

// Uso
Thread thread = new Thread(new MinhaThread());
thread.start();

// Com Lambda
new Thread(() -> {
    System.out.println("Executando com Lambda");
}).start();
```

### 7.2 Sincronização

```java
public class ContadorSeguro {
    private int contador = 0;
    
    // Método sincronizado
    public synchronized void incrementar() {
        contador++;
    }
    
    // Bloco sincronizado
    public void decrementar() {
        synchronized(this) {
            contador--;
        }
    }
}
```

### 7.3 ExecutorService

```java
ExecutorService executor = Executors.newFixedThreadPool(3);

// Submeter tarefas
executor.submit(() -> System.out.println("Tarefa 1"));
executor.submit(() -> System.out.println("Tarefa 2"));

// Desligar o executor
executor.shutdown();
executor.awaitTermination(1, TimeUnit.MINUTES);
```

## 8. Tratamento Avançado de Exceções

### 8.1 Exceções Customizadas

```java
public class ValidacaoException extends Exception {
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
    
    public ValidacaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

public class Validador {
    public void validarEmail(String email) throws ValidacaoException {
        if (!email.contains("@")) {
            throw new ValidacaoException("Email inválido");
        }
    }
}
```

### 8.2 Try-with-resources

```java
// Gerenciamento automático de recursos
try (FileReader reader = new FileReader("arquivo.txt");
     BufferedReader buffered = new BufferedReader(reader)) {
    String linha;
    while ((linha = buffered.readLine()) != null) {
        System.out.println(linha);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

## 9. Imutabilidade

### 9.1 Criando Classes Imutáveis

```java
public final class Pessoa {
    private final String nome;
    private final int idade;
    private final List<String> hobbies;
    
    public Pessoa(String nome, int idade, List<String> hobbies) {
        this.nome = nome;
        this.idade = idade;
        // Copiar para evitar modificações externas
        this.hobbies = new ArrayList<>(hobbies);
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getIdade() {
        return idade;
    }
    
    public List<String> getHobbies() {
        // Retornar cópia imutável
        return Collections.unmodifiableList(hobbies);
    }
}
```

## 10. Princípios SOLID

### 10.1 Single Responsibility Principle (SRP)
Uma classe deve ter uma única razão para mudar.

### 10.2 Open/Closed Principle (OCP)
Aberta para extensão, fechada para modificação.

### 10.3 Liskov Substitution Principle (LSP)
Subclasses devem ser substituíveis por suas superclasses.

### 10.4 Interface Segregation Principle (ISP)
Muitas interfaces específicas são melhores que uma interface genérica.

### 10.5 Dependency Inversion Principle (DIP)
Dependa de abstrações, não de implementações concretas.

## Conclusão

Estes conceitos avançados de Java fornecem as ferramentas necessárias para escrever código robusto, escalável e mantível. O domínio desses padrões e técnicas é essencial para o desenvolvimento profissional de aplicações empresariais.

## Referências

- [Java Documentation](https://docs.oracle.com/en/java/)
- [Design Patterns: Elements of Reusable Object-Oriented Software](https://en.wikipedia.org/wiki/Design_Patterns)
- [Effective Java](https://www.oreilly.com/library/view/effective-java-3rd/9780134685991/)
