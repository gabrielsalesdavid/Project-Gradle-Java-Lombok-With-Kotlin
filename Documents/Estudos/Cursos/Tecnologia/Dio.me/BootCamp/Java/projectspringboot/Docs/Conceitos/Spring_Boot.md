# Conceitos Avançados de Spring Boot

## 1. Injeção de Dependência Avançada

### 1.1 Qualificadores e Nomes de Beans

```java
// Múltiplas implementações da mesma interface
@Service("usuarioServiceV1")
public class UsuarioServiceV1 implements UsuarioService {
    // Implementação
}

@Service("usuarioServiceV2")
public class UsuarioServiceV2 implements UsuarioService {
    // Implementação
}

// Injetar a versão específica
@RestController
public class UsuarioController {
    @Autowired
    @Qualifier("usuarioServiceV1")
    private UsuarioService usuarioService;
}
```

### 1.2 Condições de Injeção

```java
@Configuration
public class CondicionalConfig {
    
    @Bean
    @ConditionalOnProperty(name = "app.cache.enabled", havingValue = "true")
    public CacheManager cacheManager() {
        return new CacheManager();
    }
    
    @Bean
    @ConditionalOnClass(name = "redis.clients.jedis.Jedis")
    public RedisTemplate<String, String> redisTemplate() {
        return new RedisTemplate<>();
    }
    
    @Bean
    @ConditionalOnMissingBean
    public UsuarioService usuarioService() {
        return new UsuarioServiceDefault();
    }
}
```

### 1.3 Escopos de Beans

```java
@Service
@Scope(value = "singleton") // Padrão: uma instância para toda a aplicação
public class SingletonService { }

@Service
@Scope(value = "prototype") // Nova instância a cada injeção
public class PrototypeService { }

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestService { } // Uma instância por requisição HTTP

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionService { } // Uma instância por sessão HTTP
```

## 2. Configuração Avançada

### 2.1 @Configuration e @Bean

```java
@Configuration
public class ApplicationConfig {
    
    // Registrar um bean manualmente
    @Bean
    public UsuarioService usuarioService(UsuarioRepository repository) {
        return new UsuarioService(repository);
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    // Bean condicional
    @Bean
    @ConditionalOnProperty(name = "app.features.cache", havingValue = "true")
    public CacheManager cacheManager() {
        return new CacheManager();
    }
}
```

### 2.2 Perfis (Profiles)

```properties
# application.properties
spring.profiles.active=development

# application-development.properties
server.port=8080
logging.level.root=DEBUG
app.debug=true

# application-production.properties
server.port=8443
logging.level.root=WARN
app.debug=false
```

```java
@Configuration
@Profile("development")
public class DevelopmentConfig {
    @Bean
    public Logger logger() {
        return new ConsoleLogger();
    }
}

@Configuration
@Profile("production")
public class ProductionConfig {
    @Bean
    public Logger logger() {
        return new FileLogger();
    }
}

@Service
public class MinhaServico {
    @Autowired
    private Logger logger;
}
```

## 3. Aspectos e AOP (Aspect-Oriented Programming)

### 3.1 Logs com Aspectos

```java
@Aspect
@Component
public class LoggingAspect {
    
    @Before("execution(* com.exemplo.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Iniciando método: " + joinPoint.getSignature());
    }
    
    @After("execution(* com.exemplo.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Finalizando método: " + joinPoint.getSignature());
    }
    
    @AfterReturning(pointcut = "execution(* com.exemplo.service.*.*(..))", 
                   returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Resultado: " + result);
    }
    
    @AfterThrowing(pointcut = "execution(* com.exemplo.service.*.*(..))",
                  throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        System.out.println("Exceção: " + exception.getMessage());
    }
    
    @Around("execution(* com.exemplo.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long inicio = System.currentTimeMillis();
        
        Object result = joinPoint.proceed(); // Executar o método
        
        long tempo = System.currentTimeMillis() - inicio;
        System.out.println("Tempo de execução: " + tempo + "ms");
        
        return result;
    }
}
```

### 3.2 Validação com Aspectos

```java
@Aspect
@Component
public class ValidationAspect {
    
    @Before("@annotation(validated)")
    public void validateInput(JoinPoint joinPoint, ValidateInput validated) {
        Object[] args = joinPoint.getArgs();
        
        for (Object arg : args) {
            if (arg == null) {
                throw new IllegalArgumentException("Argumento não pode ser null");
            }
        }
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateInput { }

@Service
public class UsuarioService {
    @ValidateInput
    public void criar(Usuario usuario) {
        // Método será validado automaticamente
    }
}
```

## 4. Segurança com Spring Security

### 4.1 Configuração Básica

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) 
        throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/user/**").hasRole("USER")
                .anyRequest().authenticated()
            .and()
            .httpBasic();
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### 4.2 Autenticação Customizada

```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) 
        throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                "Usuário não encontrado: " + username));
        
        return new org.springframework.security.core.userdetails.User(
            usuario.getEmail(),
            usuario.getSenha(),
            getAuthorities(usuario)
        );
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
        return usuario.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNome()))
            .collect(Collectors.toList());
    }
}
```

## 5. Paginação e Sorting

### 5.1 Usando Pageable

```java
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
}

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;
    
    @GetMapping
    public ResponseEntity<Page<Usuario>> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id,desc") String[] sort) {
        
        Sort.Order[] orders = new Sort.Order[sort.length];
        for (int i = 0; i < sort.length; i++) {
            String[] sortParts = sort[i].split(",");
            Sort.Direction direction = "desc".equalsIgnoreCase(sortParts[1]) 
                ? Sort.Direction.DESC 
                : Sort.Direction.ASC;
            orders[i] = new Sort.Order(direction, sortParts[0]);
        }
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        Page<Usuario> usuarios = repository.findAll(pageable);
        
        return ResponseEntity.ok(usuarios);
    }
}
```

### 5.2 Resposta com Paginação

```java
@GetMapping
public ResponseEntity<PageResponse<UsuarioDTO>> listar(
    @PageableDefault(size = 20, page = 0, sort = "id", direction = Sort.Direction.ASC)
    Pageable pageable) {
    
    Page<Usuario> page = usuarioRepository.findAll(pageable);
    
    List<UsuarioDTO> dtos = page.getContent().stream()
        .map(UsuarioDTO::fromEntity)
        .collect(Collectors.toList());
    
    PageResponse<UsuarioDTO> response = new PageResponse<>(
        dtos,
        page.getNumber(),
        page.getSize(),
        page.getTotalElements(),
        page.getTotalPages()
    );
    
    return ResponseEntity.ok(response);
}
```

## 6. Cache com Spring

### 6.1 Anotações de Cache

```java
@Service
@EnableCaching
public class UsuarioService {
    
    @Cacheable(value = "usuarios", key = "#id")
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    
    @CachePut(value = "usuarios", key = "#usuario.id")
    public Usuario atualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    @CacheEvict(value = "usuarios", key = "#id")
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    @CacheEvict(value = "usuarios", allEntries = true)
    public void limparCache() {
        // Limpa todo o cache de usuários
    }
}
```

### 6.2 Configuração de Cache

```java
@Configuration
@EnableCaching
public class CacheConfig {
    
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("usuarios", "produtos", "pedidos");
    }
}

// Em application.properties
spring.cache.type=simple # simple, redis, etc.
```

## 7. Tratamento de Erros Avançado

### 7.1 Exceções Customizadas

```java
public class UsuarioException extends RuntimeException {
    private int code;
    
    public UsuarioException(String message, int code) {
        super(message);
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
}

public class UsuarioNaoEncontradoException extends UsuarioException {
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário com ID " + id + " não encontrado", 404);
    }
}
```

### 7.2 ControllerAdvice Avançado

```java
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ApiError> handleUsuarioNaoEncontrado(
        UsuarioNaoEncontradoException ex, 
        HttpServletRequest request) {
        
        ApiError apiError = ApiError.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error("Não Encontrado")
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .build();
        
        log.error("Erro: {}", apiError);
        
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(
        MethodArgumentNotValidException ex,
        HttpServletRequest request) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(error -> errors.put(
                error.getField(), 
                error.getDefaultMessage()
            ));
        
        ApiError apiError = ApiError.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Erro de Validação")
            .message("Dados inválidos na requisição")
            .validationErrors(errors)
            .path(request.getRequestURI())
            .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(
        Exception ex,
        HttpServletRequest request) {
        
        ApiError apiError = ApiError.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error("Erro Interno do Servidor")
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .build();
        
        log.error("Erro não tratado: ", ex);
        
        return new ResponseEntity<>(apiError, 
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

## 8. Integração com APIs Externas

### 8.1 RestTemplate

```java
@Service
public class ViaCepService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    public EnderecoDTO buscarEndereco(String cep) {
        String url = "https://viacep.com.br/ws/{cep}/json/";
        
        try {
            EnderecoDTO endereco = restTemplate.getForObject(
                url, 
                EnderecoDTO.class, 
                cep
            );
            return endereco;
        } catch (RestClientException e) {
            throw new ApiExterna Exception("Erro ao buscar CEP", e);
        }
    }
}

@Configuration
public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = 
            new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        return new RestTemplate(factory);
    }
}
```

### 8.2 WebClient (Recomendado)

```java
@Service
public class ApiService {
    
    @Autowired
    private WebClient webClient;
    
    public Mono<Usuario> buscarUsuario(Long id) {
        return webClient.get()
            .uri("/usuarios/{id}", id)
            .retrieve()
            .onStatus(HttpStatus::isError, clientResponse -> 
                Mono.error(new ApiException("Erro na API"))
            )
            .bodyToMono(Usuario.class);
    }
}

@Configuration
public class WebClientConfig {
    
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
            .baseUrl("https://api.exemplo.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}
```

## 9. Agendamento de Tarefas

### 9.1 @Scheduled

```java
@Service
@EnableScheduling
public class TaskScheduler {
    
    @Scheduled(fixedRate = 5000) // A cada 5 segundos
    public void tarefaPeriodica() {
        System.out.println("Executando tarefa periódica");
    }
    
    @Scheduled(cron = "0 0 * * * *") // A cada hora
    public void limpezaDados() {
        System.out.println("Limpando dados antigos");
    }
    
    @Scheduled(initialDelay = 2000, fixedRate = 10000)
    public void tarefaComDelay() {
        System.out.println("Executando após 2s de delay");
    }
}
```

## 10. Observabilidade e Monitoramento

### 10.1 Actuator

```properties
# application.properties
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.endpoint.health.show-details=always
management.metrics.enable.jvm=true
management.metrics.enable.process=true
```

```java
@RestController
@RequestMapping("/api/health")
public class HealthController {
    
    @GetMapping
    public ResponseEntity<HealthStatus> health() {
        return ResponseEntity.ok(new HealthStatus("UP"));
    }
}
```

## Conclusão

Os conceitos avançados de Spring Boot permitem construir aplicações empresariais robustas, escaláveis e mantíveis. O domínio dessas técnicas é essencial para desenvolvimento profissional em produção.

## Referências

- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [Spring AOP Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)
