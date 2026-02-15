# Fundamentos de Kotlin

## 1. Introdução

Kotlin é uma linguagem de programação compilada para a JVM, desenvolvida pela JetBrains. Projetada para ser mais concisa e segura que Java, Kotlin é totalmente interoperável com Java e é a linguagem oficial para desenvolvimento Android.

## 2. Características Principais

### 2.1 Concisão Sintática
Kotlin permite escrever código mais limpo e expressivo:
```kotlin
// Menos boilerplate
val nome: String = "João"
val idade = 30
```

### 2.2 Segurança contra Nulo
Sistema de tipos diferencia referências nulas:
```kotlin
val texto: String = "Obrigatório"      // Nunca nulo
val textoOpcional: String? = null      // Pode ser nulo
```

### 2.3 Funções são Cidadãos de Primeira Classe
```kotlin
val saudacao = { nome: String -> "Olá, $nome" }
val resultado = saudacao("Maria")
```

## 3. Tipos de Dados

### 3.1 Tipos Primitivos
```kotlin
val inteiro: Int = 42
val longo: Long = 123456789L
val flutuante: Float = 3.14f
val duplo: Double = 3.14159
val booleano: Boolean = true
val caractere: Char = 'A'
val byte: Byte = 127
val curto: Short = 32767
```

### 3.2 Strings
```kotlin
val nome = "Mundo"
val mensagem = "Olá, $nome"           // String template
val multi = """
    Linha 1
    Linha 2
    Linha 3
""".trimIndent()                        // String multilinha

// String template com expressões
val valor = 10
val texto = "O valor é ${valor * 2}"   // O valor é 20
```

### 3.3 Arrays
```kotlin
val numeros = intArrayOf(1, 2, 3, 4, 5)
val array = arrayOf("a", "b", "c")
val lista = mutableListOf(1, 2, 3)      // Lista mutável
val setConj = setOf(1, 2, 2, 3)         // Conjunto {1, 2, 3}
val mapa = mapOf("chave1" to 1, "chave2" to 2)
```

## 4. Variáveis e Constantes

### 4.1 val vs var
```kotlin
val constante = 10          // Imutável (recomendado)
var variavel = 10           // Mutável
variavel = 20               // Permitido
// constante = 20           // Erro!
```

### 4.2 Inferência de Tipo
```kotlin
val numero = 42             // Tipo Int inferido
val texto = "Kotlin"        // Tipo String inferido
```

## 5. Funções

### 5.1 Funções Básicas
```kotlin
fun saudacao(nome: String): String {
    return "Olá, $nome"
}

// Função com corpo único (one-liner)
fun quadrado(x: Int) = x * x

// Função sem retorno
fun imprimirNome(nome: String) {
    println(nome)
}

// Parâmetros com valores padrão
fun cumprimento(nome: String, prefixo: String = "Sr.") {
    println("$prefixo $nome")
}
```

### 5.2 Funções com Múltiplos Parâmetros
```kotlin
fun calcular(a: Int, b: Int, operacao: (Int, Int) -> Int): Int {
    return operacao(a, b)
}

// Chamando
val resultado = calcular(5, 3) { x, y -> x + y }
```

### 5.3 Variadic Arguments
```kotlin
fun somaTodos(vararg numeros: Int): Int {
    return numeros.sum()
}

// Uso
somaTodos(1, 2, 3, 4, 5)       // 15
```

## 6. Controle de Fluxo

### 6.1 If-Else
```kotlin
val idade = 20

// If-else tradicional
if (idade >= 18) {
    println("Maior de idade")
} else {
    println("Menor de idade")
}

// If como expressão
val status = if (idade >= 18) "Maior" else "Menor"

// When (switch em Java)
when (idade) {
    in 0..12 -> println("Criança")
    in 13..17 -> println("Adolescente")
    in 18..65 -> println("Adulto")
    else -> println("Idoso")
}
```

### 6.2 Loops
```kotlin
// For com range
for (i in 1..5) {
    println(i)
}

// For com step
for (i in 1..10 step 2) {
    println(i)      // 1, 3, 5, 7, 9
}

// For com downTo
for (i in 5 downTo 1) {
    println(i)      // 5, 4, 3, 2, 1
}

// While
var contador = 0
while (contador < 5) {
    println(contador)
    contador++
}

// Iteração sobre collections
val nomes = listOf("Ana", "Bruno", "Carlos")
for (nome in nomes) {
    println(nome)
}

// With index
for ((index, nome) in nomes.withIndex()) {
    println("$index: $nome")
}
```

## 7. Classes e Objetos

### 7.1 Definição de Classe
```kotlin
class Pessoa(
    val nome: String,           // Propriedade imutável
    var idade: Int
) {
    fun apresentar() {
        println("Olá, sou $nome com $idade anos")
    }
}

// Instanciação (sem 'new')
val pessoa = Pessoa("João", 30)
```

### 7.2 Construtores
```kotlin
class Usuario(
    val nome: String,
    val email: String = "desconhecido@mail.com"    // Valor padrão
) {
    // Construtor secundário
    constructor(nome: String) : this(nome, "")
}

// Uso
val user1 = Usuario("Maria", "maria@mail.com")
val user2 = Usuario("João")
```

### 7.3 Propriedades e Getters/Setters
```kotlin
class Conta {
    var saldo: Double = 0.0
        get() = field
        set(value) {
            if (value >= 0) {
                field = value
            }
        }
    
    private val historico = mutableListOf<String>()
    
    val ultimaOperacao: String?
        get() = historico.lastOrNull()
}
```

### 7.4 Classe Data
```kotlin
data class Ponto(val x: Int, val y: Int)

// A classe data gera automaticamente:
// - equals() e hashCode()
// - toString()
// - copy()
// - Destructuring

val p1 = Ponto(1, 2)
val p2 = Ponto(1, 2)

println(p1 == p2)               // true (igualdade por valor)

// Desempacotamento
val (x, y) = p1
println("$x, $y")               // 1, 2

// Cópia com modificação
val p3 = p1.copy(y = 5)         // Ponto(1, 5)
```

## 8. Herança

### 8.1 Herança de Classes
```kotlin
// Classe aberta para herança
open class Animal(val nome: String) {
    open fun fazer_som() {
        println("Som genérico")
    }
}

class Cachorro(nome: String) : Animal(nome) {
    override fun fazer_som() {
        println("Au au!")
    }
}

val dog = Cachorro("Rex")
dog.fazer_som()                 // Au au!
```

### 8.2 Interfaces
```kotlin
interface Veiculo {
    fun acelerar()
    fun frear()
}

class Carro : Veiculo {
    override fun acelerar() {
        println("Carro acelerando")
    }
    
    override fun frear() {
        println("Carro freando")
    }
}
```

## 9. Extensões (Extension Functions)

```kotlin
// Adicionar métodos a classes existentes
fun String.contaPalavras(): Int {
    return this.split(" ").size
}

val texto = "Kotlin é incrível"
println(texto.contaPalavras())  // 3

// Extensão de propriedade
val String.primeiraLetra: Char?
    get() = this.firstOrNull()

println("Kotlin".primeiraLetra)  // K
```

## 10. Tratamento de Exceções

### 10.1 Try-Catch
```kotlin
try {
    val numero = "abc".toInt()
} catch (e: NumberFormatException) {
    println("Erro: número inválido")
} catch (e: Exception) {
    println("Erro genérico")
} finally {
    println("Finalizado")
}

// Try como expressão
val resultado = try {
    "123".toInt()
} catch (e: NumberFormatException) {
    0
}
```

### 10.2 Throw
```kotlin
fun validarIdade(idade: Int) {
    if (idade < 0) {
        throw IllegalArgumentException("Idade não pode ser negativa")
    }
}
```

## 11. Coleções

### 11.1 Listas
```kotlin
val lista = listOf(1, 2, 3)         // Imutável
val listaM = mutableListOf(1, 2, 3) // Mutável

listaM.add(4)
listaM.remove(1)

// Operações funcionais
listOf(1, 2, 3, 4, 5)
    .filter { it > 2 }              // [3, 4, 5]
    .map { it * 2 }                 // [6, 8, 10]
    .forEach { println(it) }
```

### 11.2 Mapas
```kotlin
val mapa = mapOf(
    "Ana" to 25,
    "Bruno" to 30
)

val mapaM = mutableMapOf(
    "chave1" to "valor1"
)

// Adicionar
mapaM["chave2"] = "valor2"

// Acessar
println(mapa["Ana"])                // 25
```

### 11.3 Conjuntos
```kotlin
val conjunto = setOf(1, 2, 2, 3)    // {1, 2, 3}
val conjuntoM = mutableSetOf(1, 2, 3)

conjuntoM.add(4)
conjuntoM.remove(2)
```

## 12. Lambdas e Higher-Order Functions

### 12.1 Lambdas
```kotlin
val quadrado: (Int) -> Int = { x -> x * x }
println(quadrado(5))                // 25

// Sintaxe sem tipo
val dobra = { x: Int -> x * 2 }

// Com múltiplos argumentos
val soma = { a: Int, b: Int -> a + b }
println(soma(3, 4))                 // 7

// Acesso ao parâmetro implícito (it)
val pares = listOf(1, 2, 3, 4, 5)
    .filter { it % 2 == 0 }         // [2, 4]
```

### 12.2 Funções de Ordem Superior
```kotlin
fun aplicarOperacao(a: Int, b: Int, operacao: (Int, Int) -> Int): Int {
    return operacao(a, b)
}

println(aplicarOperacao(5, 3) { x, y -> x + y })          // 8
println(aplicarOperacao(5, 3) { x, y -> x * y })          // 15
```

## 13. Scope Functions

### 13.1 let, run, with, apply, also
```kotlin
// let - usar o resultado em um novo escopo
val nome = "Kotlin"
val tamanho = nome.let { it.length }

// run - executar bloco e retornar resultado
val resultado = "texto".run {
    println(this)
    this.length
}

// with - chamar múltiplos métodos
data class Pessoa(var nome: String, var idade: Int)
val p = Pessoa("", 0)
with(p) {
    nome = "João"
    idade = 30
}

// apply - configurar objeto e retornulo
val lista = mutableListOf<Int>().apply {
    add(1)
    add(2)
    add(3)
}

// also - efeito colateral
"teste".also { println(it) }
    .length
```

## 14. Ranges

```kotlin
// Ranges
val range = 1..10               // 1 a 10 (inclusivo)
val range2 = 1 until 10         // 1 a 9 (exclusivo)
val range3 = 10 downTo 1        // Regressivo
val range4 = 1..20 step 2       // Com passo

for (i in range) {
    println(i)
}

val numero = 5
if (numero in range) {
    println("Está no range")
}
```

## 15. Null Safety

```kotlin
val texto: String? = null      // Pode ser nulo

// Safe call (?.)
println(texto?.length)         // null (sem erro)

// Elvis operator (?:)
val comprimento = texto?.length ?: 0   // 0 se null

// Not-null assertion (!!)
val tamanho = texto!!.length   // Lança exception se null

// Let com null check
texto?.let {
    println(it.length)
}
```

## 16. Sealed Classes

```kotlin
sealed class Resultado {
    data class Sucesso(val dados: String) : Resultado()
    data class Erro(val mensagem: String) : Resultado()
}

fun processar(resultado: Resultado) {
    when (resultado) {
        is Resultado.Sucesso -> println(resultado.dados)
        is Resultado.Erro -> println(resultado.mensagem)
    }
}
```

## Boas Práticas Kotlin

1. **Use val em vez de var**: Prefira imutabilidade
2. **Lambdas curtas**: Use `it` para lambdas de um parâmetro
3. **Scope functions**: Use `let`, `apply`, `also` apropriadamente
4. **Data classes**: Para simples modelos de dados
5. **Extension functions**: Para adicionar funcionalidade sem herança
6. **Null safety**: Aproveite o sistema de tipos
7. **Type inference**: Deixe o Kotlin inferir tipos quando possível

## Referências

- [Documentação Oficial Kotlin](https://kotlinlang.org/)
- [Guia Kotlin Style](https://kotlinlang.org/docs/coding-conventions.html)
- [Kotlin Playground](https://play.kotlinlang.org/)
