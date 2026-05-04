# Fundamentos de Spring Boot

## 1. Introdução ao Spring Boot

### 1.1 O que é Spring Boot?

Spring Boot é um framework que simplifica o desenvolvimento de aplicações com o Spring Framework. Ele elimina grande parte da configuração boilerplate necessária para usar o Spring, permitindo que você crie aplicações standalone e pronta para produção com o mínimo de configuração.

### 1.2 Características Principais

- **Auto-configuração**: Configuração automática baseada no classpath
- **Aplicações Standalone**: Inclui um servidor web incorporado (Tomcat)
- **Sem XML**: Configuração através de anotações Java e arquivos properties
- **Gerenciamento de Dependências**: Via Maven ou Gradle
- **Métricas e Health Checks**: Monitoramento integrado
- **Embedded Servers**: Tomcat, Jetty ou Undertow incorporados

## 2. Estrutura de um Projeto Spring Boot

### 2.1 Estrutura de Diretórios

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── exemplo/
│   │           ├── Application.java
│   │           ├── controller/
│   │           ├── service/
│   │           ├── repository/
│   │           ├── model/
│   │           └── exception/
│   └── resources/
│       ├── application.properties
│       ├── application.yml
│       ├── static/
│       └── templates/
└── test/
    └── java/
```

### 2.2 Arquivos Importantes

#### POM.XML (Maven)
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>4.0.6</version>
</parent>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

#### Classe Principal
```java
@SpringBootApplication
public class MinhaAplicacao {
    public static void main(String[] args) {
        SpringApplication.run(MinhaAplicacao.class, args);
    }
}
```

#### Application Properties
```properties
# Servidor
server.port=8080
server.servlet.context-path=/api

# Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/minhadb
spring.datasource.username=root
spring.datasource.password=senha

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 3. Anotações Essenciais

### 3.1 Anotações de Aplicação

#### @SpringBootApplication
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
Combina `@Configuration`, `@EnableAutoConfiguration` e `@ComponentScan`.

### 3.2 Anotações de Componentes

#### @Component
```java
@Component
public class MinhaClasse {
    // Será gerenciado pelo Spring
}
```
Define um bean genérico do Spring.

#### @Service
```java
@Service
public class UsuarioService {
    // Lógica de negócio
}
```
Especialização de `@Component` para a camada de serviço.

#### @Repository
```java
@Repository
public class UsuarioRepository {
    // Acesso a dados
}
```
Especialização de `@Component` para a camada de persistência.

#### @Controller
```java
@Controller
public class UsuarioController {
    // Controladores da aplicação
}
```

#### @RestController
```java
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
    // Endpoints REST
}
```
Combina `@Controller` e `@ResponseBody`.

### 3.3 Anotações de Injeção de Dependência

#### @Autowired
```java
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    
    // Ou no construtor (preferido)
    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
}
```

#### @Qualifier
```java
@Autowired
@Qualifier("usuarioRepositoryMySQL")
private UsuarioRepository repository;
```

## 4. Controllers e Requisições HTTP

### 4.1 Mapeamento de Requisições

```java
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @GetMapping // GET /api/usuarios
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/{id}") // GET /api/usuarios/1
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(new Usuario());
    }
    
    @PostMapping // POST /api/usuarios
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(usuarioSalvo);
    }
    
    @PutMapping("/{id}") // PUT /api/usuarios/1
    public ResponseEntity<Usuario> atualizar(
        @PathVariable Long id,
        @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioAtualizado);
    }
    
    @DeleteMapping("/{id}") // DELETE /api/usuarios/1
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
```

### 4.2 Anotações de Parâmetros

```java
@GetMapping("/buscar")
public ResponseEntity<List<Usuario>> buscar(
    @RequestParam String nome,
    @RequestParam(defaultValue = "0") int pagina,
    @RequestParam(required = false) String email) {
    // nome é obrigatório
    // pagina tem valor padrão de 0
    // email é opcional
}

@GetMapping("/{id}")
public ResponseEntity<Usuario> buscarPorId(
    @PathVariable Long id) {
    // id vem da URL
}

@PostMapping
public ResponseEntity<Usuario> criar(
    @RequestBody Usuario usuario,
    @RequestHeader("Authorization") String token) {
    // usuario vem do corpo da requisição
    // token vem do header
}
```

## 5. Services e Lógica de Negócio

### 5.1 Estrutura de um Service

```java
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;
    
    public Usuario criar(Usuario usuario) {
        // Validações
        if (usuario.getEmail() == null) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        
        // Lógica de negócio
        usuario.setDataCriacao(LocalDateTime.now());
        
        // Persistir
        return repository.save(usuario);
    }
    
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }
    
    public List<Usuario> listar() {
        return repository.findAll();
    }
    
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        return repository.save(usuario);
    }
    
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
```

## 6. Bancos de Dados com JPA

### 6.1 Entidades JPA

```java
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    
    @Transient
    private String senhaTemporaria; // Não persiste no BD
    
    // Construtores, getters, setters
}
```

### 6.2 Repositories

#### CrudRepository
```java
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    // Métodos CRUD já inclusos
    // save(), findById(), findAll(), delete(), etc.
}
```

#### JpaRepository
```java
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Inclui CrudRepository + métodos adicionais
    // findAll(Pageable), flush(), saveAndFlush(), etc.
}
```

#### Consultas Customizadas
```java
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario findByEmail(String email);
    
    @Query("SELECT u FROM Usuario u WHERE u.nome LIKE %?1%")
    List<Usuario> buscarPorNome(String nome);
    
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
    
    Usuario findByEmailAndSenha(String email, String senha);
}
```

## 7. Configuração e Properties

### 7.1 Application Properties

```properties
# Perfis de aplicação
spring.profiles.active=development

# Server
server.port=8080
server.servlet.context-path=/api/v1

# DataSource
spring.datasource.url=jdbc:mysql://localhost:3306/db
spring.datasource.username=root
spring.datasource.password=senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.root=INFO
logging.level.com.exemplo=DEBUG

# Aplicação customizada
app.name=Minha Aplicação
app.version=1.0.0
```

### 7.2 Application YAML

```yaml
spring:
  profiles:
    active: development
  datasource:
    url: jdbc:mysql://localhost:3306/db
    username: root
    password: senha
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false

server:
  port: 8080
  servlet:
    context-path: /api/v1

logging:
  level:
    root: INFO
    com.exemplo: DEBUG
```

## 8. Validação de Dados

### 8.1 Bean Validation

```java
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome não pode estar vazio")
    @Size(min = 3, max = 100)
    private String nome;
    
    @Email(message = "Email inválido")
    private String email;
    
    @Min(18)
    @Max(120)
    private Integer idade;
    
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")
    private String senha;
}
```

### 8.2 Validação em Controllers

```java
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @PostMapping
    public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Tratar erros de validação
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuarioService.criar(usuario));
    }
}
```

## 9. Exception Handling

### 9.1 ControllerAdvice Global

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleUsuarioNaoEncontrado(
        UsuarioNaoEncontradoException ex) {
        ErroResponse erro = new ErroResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidationException(
        MethodArgumentNotValidException ex) {
        ErroResponse erro = new ErroResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Dados inválidos",
            System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
```

## 10. Executando a Aplicação

### 10.1 Maven

```bash
# Compilar e empacotar
mvn clean package

# Executar a aplicação
mvn spring-boot:run

# Executar o JAR gerado
java -jar target/minha-aplicacao-1.0.0.jar
```

### 10.2 Gradle

```bash
# Compilar e empacotar
gradle clean build

# Executar a aplicação
gradle bootRun
```

## Conclusão

Spring Boot fornece um ambiente poderoso e fácil de usar para desenvolver aplicações Java modernas. Dominar esses fundamentos é essencial para criar aplicações profissionais e escaláveis.

## Referências

- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)
