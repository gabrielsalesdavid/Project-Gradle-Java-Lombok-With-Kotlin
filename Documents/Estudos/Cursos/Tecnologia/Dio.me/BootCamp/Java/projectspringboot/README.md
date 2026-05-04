# 🚀 Spring Boot Project Documentation

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-green?logo=spring)
![Maven](https://img.shields.io/badge/Maven-3.9-blue?logo=maven)
![License](https://img.shields.io/badge/License-MIT-yellow)

> Um projeto educacional abrangente de Spring Boot com documentações detalhadas sobre Java e frameworks relacionados.

---

## 📋 Sumário

- [Visão Geral](#-visão-geral)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação](#-instalação)
- [Documentações](#-documentações)
- [Como Usar](#-como-usar)
- [Estrutura de Diretórios](#-estrutura-de-diretórios)
- [Contribuindo](#-contribuindo)
- [Licença](#-licença)

---

## 👀 Visão Geral

Este é um projeto completo de **Spring Boot** desenvolvido como parte do BootCamp de Java da DIO.me. O projeto inclui:

✅ **Aplicação Spring Boot funcional** com Rest APIs
✅ **Documentações abrangentes** sobre Java e Spring Boot
✅ **Exemplos práticos** de código
✅ **Boas práticas** profissionais
✅ **Componentes principais** (Controllers, Services, Repositories)

### Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 17 | Linguagem de programação |
| **Spring Boot** | 4.0.6 | Framework Web |
| **Maven** | 3.9+ | Gerenciador de Dependências |
| **Lombok** | 1.18+ | Redução de Boilerplate |
| **JPA/Hibernate** | - | ORM (Object-Relational Mapping) |

---

## 📁 Estrutura do Projeto

```
projectspringboot/
├── 📄 README.md                          # Este arquivo
├── 📄 pom.xml                            # Configuração Maven
├── 📄 HELP.md                            # Ajuda do projeto
├── 📄 mvnw / mvnw.cmd                    # Maven Wrapper
│
├── 📂 src/
│   ├── main/
│   │   ├── 📂 java/dio/
│   │   │   ├── 📂 projectspringboot/
│   │   │   │   ├── 🔵 ProjectspringbootApplication.java  # Classe principal
│   │   │   │   ├── 🟦 Calculadora.java
│   │   │   │   ├── 🟦 MyApp.java
│   │   │   │   │
│   │   │   │   └── 📂 beanscomponents/
│   │   │   │       ├── 🟦 Beans.java
│   │   │   │       ├── 🟦 CommandLine.java
│   │   │   │       └── 🟦 ViaCepResponse.java
│   │   │   │
│   │   │   ├── 📂 properties/
│   │   │   │   ├── 🟦 Remetente.java
│   │   │   │   ├── 🟦 SistemaMensag.java
│   │   │   │   └── 🟦 SMList.java
│   │   │   │
│   │   │   ├── 📂 scopes/
│   │   │   │   ├── 🟦 BeanSM.java
│   │   │   │   ├── 🟦 Remetente.java
│   │   │   │   └── 🟦 SistemaMensagem.java
│   │   │   │
│   │   │   ├── 🟦 CommandLineSM.java
│   │   │   └── 🟦 ConversorJSON.java
│   │   │
│   │   └── 📂 resources/
│   │       └── 📄 application.properties  # Configurações da aplicação
│   │
│   └── test/
│       └── 📂 java/dio/projectspringboot/
│           └── 🧪 ProjectspringbootApplicationTests.java
│
├── 📂 Docs/
│   ├── 📄 README.md                       # Índice de documentações
│   │
│   ├── 📂 Fundamentos/
│   │   ├── 📖 Java.md
│   │   └── 📖 Spring_Boot.md
│   │
│   └── 📂 Conceitos/
│       ├── 📖 Java.md
│       └── 📖 Spring_Boot.md
│
└── 📂 target/                             # Arquivos compilados (gerado)
    ├── classes/
    ├── test-classes/
    └── ...
```

---

## 🛠️ Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina:

### Obrigatórios
- **Java JDK 17+** ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.9+** ([Download](https://maven.apache.org/download.cgi))
- **Git** ([Download](https://git-scm.com/))

### Opcionais (Recomendados)
- **IDE**: IntelliJ IDEA, Eclipse ou VS Code com extensões
- **Postman ou Insomnia**: Para testar APIs REST
- **Docker**: Para containerização (opcional)

### Verificar Instalação

```bash
# Verificar Java
java -version
javac -version

# Verificar Maven
mvn -version

# Verificar Git
git --version
```

---

## 💾 Instalação

### 1. Clonar o Repositório

```bash
git clone https://github.com/seu-usuario/projectspringboot.git
cd projectspringboot
```

### 2. Instalar Dependências

```bash
# Com Maven
mvn clean install

# Ou use o Maven Wrapper (funciona sem Maven instalado)
./mvnw clean install
```

### 3. Compilar o Projeto

```bash
mvn clean compile
```

### 4. Executar Testes

```bash
mvn test
```

### 5. Empacotar a Aplicação

```bash
mvn clean package
```

---

## 🚀 Como Usar

### Executar a Aplicação

#### Opção 1: Com Maven
```bash
mvn spring-boot:run
```

#### Opção 2: Executar o JAR
```bash
java -jar target/projectspringboot-0.0.1-SNAPSHOT.jar
```

### Acessar a Aplicação

A aplicação estará disponível em:
- **URL Base**: `http://localhost:8080`
- **Contexto**: `/` (configurável em application.properties)

---

## 📚 Documentações

Este projeto inclui documentações completas e detalhadas. Consulte o [índice de documentações](./Docs/README.md) para mais informações.

### 📖 Documentações Disponíveis

#### Java
- **[Fundamentos de Java](./Docs/Fundamentos/Java.md)**
  - Tipos de dados, variáveis, operadores
  - Estruturas de controle
  - Orientação a Objetos
  - Coleções e tratamento de exceções
  - Boas práticas

- **[Conceitos Avançados de Java](./Docs/Conceitos/Java.md)**
  - Design Patterns
  - Programação Funcional (Lambdas, Streams)
  - Genéricos
  - Concorrência
  - Princípios SOLID

#### Spring Boot
- **[Fundamentos de Spring Boot](./Docs/Fundamentos/Spring_Boot.md)**
  - Estrutura de projeto
  - Anotações essenciais
  - Injeção de dependência
  - REST APIs (CRUD)
  - Bancos de dados
  - Validação

- **[Conceitos Avançados de Spring Boot](./Docs/Conceitos/Spring_Boot.md)**
  - AOP (Aspect-Oriented Programming)
  - Spring Security
  - Cache
  - Paginação e Sorting
  - APIs externas
  - Agendamento
  - Monitoramento

---

## 📊 Estrutura de Pacotes

### `dio.projectspringboot`
Pacote principal da aplicação contendo:
- **ProjectspringbootApplication.java**: Classe principal (@SpringBootApplication)
- **Calculadora.java**: Exemplo de componente de lógica
- **MyApp.java**: Aplicação auxiliar

### `dio.projectspringboot.beanscomponents`
Componentes e Beans do Spring:
- **Beans.java**: Definições de beans
- **CommandLine.java**: Implementação de CommandLineRunner
- **ViaCepResponse.java**: DTO para integração com API ViaCep

### `dio.properties`
Configurações e propriedades:
- **Remetente.java**: Configurações de remetente
- **SistemaMensag.java**: Sistema de mensagens
- **SMList.java**: Lista de sistema de mensagens

### `dio.scopes`
Exemplos de escopos de componentes:
- **BeanSM.java**: Bean com escopo customizado
- **SistemaMensagem.java**: Implementação de sistema de mensagem
- **Remetente.java**: Remetente com escopo específico

---

## 🔧 Configuração

### application.properties

```properties
# Servidor
server.port=8080
server.servlet.context-path=/

# Logging
logging.level.root=INFO
logging.level.dio=DEBUG

# Aplicação
spring.application.name=projectspringboot
```

Para diferentes ambientes, crie:
- `application-development.properties`
- `application-production.properties`

---

## ✨ Funcionalidades Principais

### 1. **Spring Application Context**
- Inicialização automática do Spring
- Gerenciamento de componentes
- Injeção de dependências

### 2. **Beans e Components**
- Definição de beans
- CommandLineRunner
- ApplicationListener

### 3. **Integração com APIs Externas**
- Consumo da API ViaCep
- Serialização/Desserialização JSON
- RestTemplate

### 4. **Componentes Customizados**
- Calculadora
- Sistema de Mensagens
- Configurações via Properties

---

## 🧪 Testes

### Executar Testes

```bash
# Todos os testes
mvn test

# Teste específico
mvn test -Dtest=ProjectspringbootApplicationTests

# Com cobertura
mvn clean test jacoco:report
```

### Estrutura de Testes

```
src/test/java/dio/projectspringboot/
└── ProjectspringbootApplicationTests.java
```

---

## 📈 Próximos Passos

Sugestões para expandir o projeto:

### Curto Prazo
- [ ] Adicionar REST Controller com CRUD
- [ ] Implementar JPA Repository
- [ ] Adicionar validação de dados
- [ ] Criar testes unitários

### Médio Prazo
- [ ] Adicionar banco de dados (MySQL/PostgreSQL)
- [ ] Implementar Spring Security
- [ ] Criar camada de serviços
- [ ] Adicionar documentação OpenAPI/Swagger

### Longo Prazo
- [ ] Implementar Cache (Redis)
- [ ] Adicionar integração contínua (CI/CD)
- [ ] Containerizar com Docker
- [ ] Deploy em nuvem (AWS/Azure)
- [ ] Microserviços

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Por favor:

1. **Crie uma branch** para sua feature
   ```bash
   git checkout -b feature/MinhaFeature
   ```

2. **Commit suas mudanças**
   ```bash
   git commit -m "feat: Descrição da feature"
   ```

3. **Push para a branch**
   ```bash
   git push origin feature/MinhaFeature
   ```

4. **Abra um Pull Request** com descrição detalhada

### Convenção de Commits

```
feat: nova funcionalidade
fix: correção de bug
docs: mudanças na documentação
style: formatação de código
refactor: refatoração de código
test: adição/modificação de testes
chore: atualizações de build, dependências, etc
```

---

## 🐛 Troubleshooting

### Problema: Maven não encontrado
**Solução**: Use o Maven Wrapper incluído
```bash
./mvnw clean install  # Linux/Mac
mvnw.cmd clean install  # Windows
```

### Problema: Java não encontrado
**Solução**: Configure JAVA_HOME
```bash
# Windows
set JAVA_HOME=C:\caminho\para\jdk17
```

### Problema: Porta 8080 em uso
**Solução**: Altere em application.properties
```properties
server.port=8081
```

### Problema: Dependências não baixadas
**Solução**: Limpe o cache Maven
```bash
mvn clean
rm -rf ~/.m2/repository  # Linux/Mac
```

---

## 📞 Suporte

Para problemas ou dúvidas:

1. **Consulte a documentação**: Veja `Docs/README.md`
2. **Abra uma issue**: No repositório do GitHub
3. **Verifique o log de erros**: `target/projectspringboot.log`

---

## 📄 Referências

### Documentação Oficial
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Java Documentation](https://docs.oracle.com/en/java/)
- [Maven Documentation](https://maven.apache.org/)

### Recursos Educacionais
- [Spring.io Guides](https://spring.io/guides)
- [Baeldung - Spring Boot Tutorials](https://www.baeldung.com/spring-boot)
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)

### Comunidade
- [Stack Overflow - Spring](https://stackoverflow.com/questions/tagged/spring)
- [Spring Community Forums](https://spring.io/community)
- [GitHub Discussions](https://github.com/spring-projects/spring-boot/discussions)

---

## 📝 Histórico de Versões

### v0.0.1 (Atual)
- ✅ Projeto Spring Boot inicial criado
- ✅ Documentações de Java adicionadas
- ✅ Documentações de Spring Boot adicionadas
- ✅ Exemplos de código inclusos
- ✅ README abrangente criado

### v0.0.2 (Planejado)
- 🔜 Adicionar REST APIs
- 🔜 Implementar JPA
- 🔜 Adicionar Spring Security
- 🔜 Testes automatizados

---

## 📋 Checklist de Desenvolvimento

### Configuração Inicial
- [x] Projeto Maven criado
- [x] Dependências do Spring Boot adicionadas
- [x] Documentações criadas
- [ ] Banco de dados configurado
- [ ] Spring Security implementado

### Funcionalidades
- [ ] Controllers REST criados
- [ ] Services implementados
- [ ] Repositories configurados
- [ ] Validação implementada
- [ ] Exception handling personalizado

### Qualidade
- [ ] Testes unitários escritos
- [ ] Testes de integração escritos
- [ ] Cobertura de testes >80%
- [ ] Código revisado
- [ ] Documentação atualizada

---

## 🎓 Sobre o Projeto

Este projeto foi desenvolvido como parte do **BootCamp de Java da DIO.me**, focando em:

✨ **Educação**: Aprender conceitos de Java e Spring Boot
📚 **Documentação**: Criar materiais educacionais de qualidade
💻 **Prática**: Implementar exemplos reais
🚀 **Profissionalismo**: Seguir boas práticas da indústria

---

## ⭐ Como Contribuir para Melhorar Este Projeto

Sua ajuda é valiosa! Você pode:

- **⭐ Dar uma estrela** no repositório
- **💬 Reportar bugs** abertos issues
- **📝 Sugerir melhorias** em Discussions
- **🔀 Contribuir com código** via Pull Requests
- **📖 Melhorar documentação** corrigindo erros

---

## 📄 Licença

Este projeto é licenciado sob a **Licença MIT**.

```
MIT License

Copyright (c) 2026 Gabriel Sales David

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```

Veja [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

**Gabriel Sales David**
- GitHub: [@gabrielsalesdavid](https://github.com/gabrielsalesdavid)
- Email: gabriel.sales.david@gmail.com
- DIO.me: [BootCamp Java](https://www.dio.me)

---

## 🙏 Agradecimentos

Agradecimentos especiais a:

- 🎓 **DIO.me** - Pela excelente plataforma de aprendizado
- 🌟 **Spring Team** - Pelo incrível framework
- 👥 **Comunidade Open Source** - Pelo suporte e contribuições
- ❤️ **Você** - Por usar e apoiar este projeto

---

## 📅 Última Atualização

**Data**: 4 de maio de 2026
**Versão do Projeto**: 0.0.1
**Status**: 🟢 Em Desenvolvimento

---

<div align="center">

### 🎉 Obrigado por usar este projeto!

Esperamos que as documentações e exemplos sejam úteis em sua jornada de aprendizado.

⭐ **Se este projeto ajudou você, considere dar uma estrela!** ⭐

[⬆ Voltar ao Topo](#-spring-boot-project-documentation)

</div>

