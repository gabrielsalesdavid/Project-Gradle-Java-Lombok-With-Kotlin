# Documentação do Projeto - Índice Geral

## 📚 Visão Geral

Este diretório contém documentações completas sobre as linguagens e frameworks utilizados neste projeto. As documentações estão organizadas em duas categorias principais: **Fundamentos** e **Conceitos Avançados**.

---

## 🎯 Estrutura de Documentação

### 📖 Fundamentos
Documentações que cobrem os conceitos básicos e essenciais de cada linguagem/framework, ideais para iniciantes ou para revisar fundamentos.

### 🚀 Conceitos Avançados
Documentações que exploram tópicos avançados, padrões de design, otimizações e boas práticas profissionais.

---

## 📋 Linguagens e Frameworks Documentados

### 1. **Java**

#### 📖 [Fundamentos de Java](./Fundamentos/Java.md)
- Introdução e características principais
- Ambiente de desenvolvimento (JDK)
- Tipos de dados e variáveis
- Operadores e estruturas de controle
- Orientação a Objetos (Encapsulamento, Herança, Polimorfismo, Abstração)
- Métodos, arrays e coleções
- Tratamento de exceções
- Pacotes e importações
- Boas práticas

#### 🚀 [Conceitos Avançados de Java](./Conceitos/Java.md)
- OOP avançada (classes abstratas vs interfaces)
- Composição vs Herança
- Design Patterns (Singleton, Factory, Builder, Adapter, Decorator, Strategy, Observer)
- Programação Funcional (Lambda, Streams)
- Genéricos
- Anotações customizadas
- Reflection
- Concorrência (Threads, sincronização, ExecutorService)
- Tratamento avançado de exceções
- Imutabilidade
- Princípios SOLID

---

### 2. **Spring Boot**

#### 📖 [Fundamentos de Spring Boot](./Fundamentos/Spring_Boot.md)
- Introdução ao Spring Boot
- Estrutura de um projeto Spring Boot
- Anotações essenciais (@SpringBootApplication, @Component, @Service, @Repository, @Controller, @RestController)
- Injeção de dependência (@Autowired, @Qualifier)
- Controllers e requisições HTTP (GET, POST, PUT, DELETE)
- Services e lógica de negócio
- Bancos de dados com JPA
- Repositories e consultas customizadas
- Configuração (application.properties e YAML)
- Validação de dados (Bean Validation)
- Exception handling
- Executando a aplicação

#### 🚀 [Conceitos Avançados de Spring Boot](./Conceitos/Spring_Boot.md)
- Injeção de dependência avançada (Qualificadores, condições)
- Escopos de Beans (Singleton, Prototype, Request, Session)
- Configuração avançada (@Configuration, @Bean, Profiles)
- Aspectos e AOP (Aspect-Oriented Programming)
- Spring Security (Autenticação e autorização)
- Paginação e Sorting
- Cache com Spring (@Cacheable, @CachePut, @CacheEvict)
- Tratamento de erros avançado (ControllerAdvice customizado)
- Integração com APIs externas (RestTemplate, WebClient)
- Agendamento de tarefas (@Scheduled)
- Observabilidade e monitoramento (Actuator)

---

## 🔍 Como Usar Esta Documentação

### Para Iniciantes
1. Comece com [Fundamentos de Java](./Fundamentos/Java.md)
2. Prossiga para [Fundamentos de Spring Boot](./Fundamentos/Spring_Boot.md)
3. Pratique os conceitos em seu projeto

### Para Desenvolvimento Intermediário
1. Revise os tópicos de fundamentos conforme necessário
2. Estude [Conceitos Avançados de Java](./Conceitos/Java.md)
3. Estude [Conceitos Avançados de Spring Boot](./Conceitos/Spring_Boot.md)
4. Implemente padrões de design e boas práticas

### Para Produção e Arquitetura
1. Foque em SOLID (em [Conceitos Avançados de Java](./Conceitos/Java.md))
2. Estude Design Patterns relevantes
3. Implemente segurança (Spring Security)
4. Configure cache, observabilidade e monitoramento

---

## 📚 Tópicos Principais por Arquivo

### Java - Fundamentos
| Tópico | Descrição |
|--------|-----------|
| Tipos de Dados | byte, int, float, double, boolean, String |
| Estruturas de Controle | if/else, switch, for, while, do-while |
| OOP Básica | Classes, objetos, herança, interfaces |
| Coleções | List, Set, Map, Arrays |
| Exceções | try-catch-finally, throw, throws |

### Java - Conceitos Avançados
| Tópico | Descrição |
|--------|-----------|
| Design Patterns | 7 padrões principais explicados |
| Functional Programming | Lambdas, Streams, operações funcionais |
| Concorrência | Threads, sincronização, ExecutorService |
| Reflection | Inspeção e manipulação dinâmica de classes |
| SOLID | 5 princípios de design |

### Spring Boot - Fundamentos
| Tópico | Descrição |
|--------|-----------|
| Anotações | @RestController, @Service, @Repository |
| Injeção de Dependências | Como o Spring gerencia beans |
| REST APIs | Endpoints HTTP CRUD |
| JPA/Hibernate | Mapeamento objeto-relacional |
| Validação | Bean Validation |

### Spring Boot - Conceitos Avançados
| Tópico | Descrição |
|--------|-----------|
| AOP | Programação orientada a aspecto |
| Security | Autenticação e autorização |
| Cache | Estratégias de caching |
| Async | Processamento assíncrono |
| Monitoring | Actuator e métricas |

---

## 💡 Exemplos Práticos

Cada documento contém exemplos de código práticos e prontos para uso. Os exemplos incluem:

✅ Sintaxe correta
✅ Boas práticas
✅ Casos de uso reais
✅ Explicações detalhadas

---

## 🎓 Recomendações de Aprendizado

### Semana 1-2: Fundamentos
- [ ] Java Fundamentos (Tipos de dados até OOP)
- [ ] Spring Boot Fundamentos (Estrutura até Controllers)

### Semana 3-4: Intermediário
- [ ] Java Fundamentos (Coleções até Boas Práticas)
- [ ] Spring Boot Fundamentos (Services até Exception Handling)

### Semana 5-6: Avançado
- [ ] Java Conceitos Avançados (OOP até Design Patterns)
- [ ] Spring Boot Conceitos Avançados (Injeção até Security)

### Semana 7-8: Especializado
- [ ] Java Conceitos Avançados (Functional Programming até SOLID)
- [ ] Spring Boot Conceitos Avançados (AOP até Monitoring)

---

## 🔗 Referências Oficiais

- [Java Documentation](https://docs.oracle.com/en/java/)
- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Maven Documentation](https://maven.apache.org/)
- [JPA/Hibernate Documentation](https://hibernate.org/orm/documentation/)

---

## 📝 Notas Importantes

### Versões Utilizadas no Projeto
- **Java**: 17
- **Spring Boot**: 4.0.6
- **Build Tool**: Maven

### Dependências Principais
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

---

## 🚀 Próximos Passos

Após estudar estas documentações:

1. **Implemente projetos práticos** usando os conceitos aprendidos
2. **Contribua com o projeto** aplicando as boas práticas
3. **Mantenha estas docs atualizadas** conforme o projeto evolui
4. **Estude frameworks extras** como Spring Data, Spring Security, Spring Cloud (conforme necessário)

---

## 📧 Sugestões e Melhorias

Se encontrar:
- ❌ Erros ou imprecisões
- ❓ Tópicos pouco claros
- ✨ Melhorias sugeridas

Considere atualizar esta documentação para beneficiar outros desenvolvedores!

---

**Última atualização**: 4 de maio de 2026
**Mantido por**: Documentação do Projeto

