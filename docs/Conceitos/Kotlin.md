# Conceitos Avançados de Kotlin

## 1. Generics (Genéricos)

### 1.1 Declaração de Genéricos
```kotlin
// Classe genérica
class Caixa<T>(val item: T) {
    fun obter(): T = item
}

// Uso
val caixaString = Caixa("Kotlin")
val stringItem = caixaString.obter()

val caixaInt = Caixa(42)
val intItem = caixaInt.obter()
```

### 1.2 Restrições de Tipo
```kotlin
// Restrição: só aceita subclasses de Number
class Somador<T : Number> {
    fun somar(a: T, b: T): Double {
        return a.toDouble() + b.toDouble()
    }
}

// Restrição múltipla
class Comparador<T> where T : Comparable<T>, T : Any {
    fun comparar(a: T, b: T): Boolean = a > b
}
```

### 1.3 Variância

#### Covariância (out)
```kotlin
// Producer<out T> - só produz T, não consome
interface Producer<out T> {
    fun produzir(): T
}

// List<out Number> pode ser assinalada de List<Int>
val inteiros: List<Int> = listOf(1, 2, 3)
val numeros: List<out Number> = inteiros
```

#### Contravariância (in)
```kotlin
// Consumer<in T> - só consome T
interface Consumer<in T> {
    fun consumir(item: T)
}

// Função que consome Number pode ser usada para int
fun imprimirNumber(consumer: Consumer<in Int>) {
    consumer.consumir(42)
}
```

## 2. Type Aliases

```kotlin
// Simplificar tipos complexos
typealias IntList = MutableList<Int>
val lista: IntList = mutableListOf(1, 2, 3)

// Lambdas complexas
typealias OperacaoMatematica = (Int, Int) -> Int
val operacao: OperacaoMatematica = { a, b -> a + b }

// Mapping complexo
typealias Usuarios = Map<String, String>
val usuarios: Usuarios = mapOf("admin" to "senha123")
```

## 3. Delegação (Delegation)

### 3.1 Delegação de Interface
```kotlin
interface Logger {
    fun log(mensagem: String)
}

class LoggerImpl : Logger {
    override fun log(mensagem: String) {
        println(mensagem)
    }
}

// Delegar Logger para LoggerImpl
class Aplicacao(logger: Logger) : Logger by logger

// Uso
val app = Aplicacao(LoggerImpl())
app.log("Teste")
```

### 3.2 Lazy Delegation
```kotlin
val config: String by lazy {
    println("Inicializando config")
    "configuração pesada"
}

println(config)  // Printa "Inicializando config" e retorna valor
println(config)  // Retorna valor cached, sem reinicializar
```

### 3.3 Observable Delegation
```kotlin
var nome: String by Delegates.observable("inicial") { _, old, new ->
    println("Nome mudou de '$old' para '$new'")
}

nome = "João"       // Printa: Nome mudou de 'inicial' para 'João'
```

### 3.4 Vetoable (com validação)
```kotlin
var idade: Int by Delegates.vetoable(0) { _, old, new ->
    new > 0 && new < 150
}

idade = 25          // OK
idade = -5          // Rejeitado, mantém 25
```

## 4. Destructuring

### 4.1 Desestruturação de Dados
```kotlin
data class Ponto(val x: Int, val y: Int)

val ponto = Ponto(3, 4)
val (x, y) = ponto
println("$x, $y")

// Em loops
val pontos = listOf(Ponto(1, 2), Ponto(3, 4))
for ((x, y) in pontos) {
    println("($x, $y)")
}

// Em lambdas
val mapa = mapOf("a" to 1, "b" to 2)
mapa.forEach { (chave, valor) -> println("$chave: $valor") }
```

### 4.2 Underscore para Ignorar Valores
```kotlin
val (x, _, z) = Triple(1, 2, 3)
println("$x, $z")                      // 1, 3

// Em lambdas
mapa.forEach { (_, valor) -> println(valor) }
```

## 5. Inlining de Funções

### 5.1 Inline Functions
```kotlin
// Sem inline
fun <T> operacao(valor: T, operador: (T) -> T): T {
    return operador(valor)
}

// Com inline (cópia do corpo no local de chamada)
inline fun <T> operacaoInline(valor: T, operador: (T) -> T): T {
    return operador(valor)
}
```

### 5.2 Noinline e Crossinline
```kotlin
inline fun executar(
    noinline1: () -> Unit,          // Não será inlined
    crossinline acao: () -> Unit    // Pode ser usado em lambdas
) {
    val runnable = Runnable { acao() }
}
```

## 6. Reified Type Parameters

```kotlin
// Sem reified: type information é perdido (type erasure)
fun <T> obterTipo(valor: T) {
    // Não podemos fazer if (T is String) {}
}

// Com reified: tipo está disponível em runtime
inline fun <reified T> obterTipo(valor: Any): Boolean {
    return valor is T
}

// Uso
val numero = 42
println(obterTipo<Int>(numero))        // true
println(obterTipo<String>(numero))     // false
```

## 7. Context Receivers (Kotlin 1.6+)

```kotlin
context(StringBuilder)
fun adicionarLinha(texto: String) {
    this.append(texto).append("\n")
}

// Uso
val sb = StringBuilder()
with(sb) {
    adicionarLinha("Linha 1")
    adicionarLinha("Linha 2")
}
println(sb)
```

## 8. DSL (Domain Specific Language)

### 8.1 Construindo um DSL
```kotlin
class HtmlBuilder {
    private val elementos = mutableListOf<String>()
    
    fun div(conteudo: String) {
        elementos.add("<div>$conteudo</div>")
    }
    
    fun p(conteudo: String) {
        elementos.add("<p>$conteudo</p>")
    }
    
    fun build(): String = elementos.joinToString("\n")
}

// DSL com lambda extension
fun html(bloco: HtmlBuilder.() -> Unit): String {
    val builder = HtmlBuilder()
    builder.bloco()
    return builder.build()
}

// Uso
val pagina = html {
    div("Título")
    p("Parágrafo")
}
```

### 8.2 DSL com Lambda com Receiver
```kotlin
// DSL para criando lista de compras
fun listadeCompras(item: MutableList<String>.() -> Unit): List<String> {
    val lista = mutableListOf<String>()
    lista.item()
    return lista
}

// Uso
val compras = listadeCompras {
    add("Pão")
    add("Leite")
    add("Ovos")
}
```

## 9. Corrotinas (Simplified)

```kotlin
// Importar: import kotlinx.coroutines.*

// Criar corrotina simples
GlobalScope.launch {
    println("Antes")
    delay(1000)
    println("Depois de 1 segundo")
}

// runBlocking: bloqueia thread até terminar
runBlocking {
    launch {
        println("Opção 1")
    }
    launch {
        println("Opção 2")
    }
}

// async para retornar valor
val defer = GlobalScope.async {
    calcularValor()
}

val resultado = runBlocking {
    defer.await()
}
```

## 10. Anotações Customizadas

### 10.1 Criando Anotações
```kotlin
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Validacao(
    val regra: String,
    val mensagem: String = "Validação falhou"
)

// Usando
@Validacao("emAi", "Email inválido")
class Usuario(val email: String)
```

### 10.2 Processamento de Anotações
```kotlin
fun procesarAnotacoes(obj: Any) {
    val classe = obj::class
    val anotacoes = classe.annotations
    
    for (anotacao in anotacoes) {
        if (anotacao is Validacao) {
            println("Regra: ${anotacao.regra}")
            println("Mensagem: ${anotacao.mensagem}")
        }
    }
}
```

## 11. Reflexão (Reflection)

```kotlin
// Importar: import kotlin.reflect.*

val classe = Usuario::class
val propriedades = classe.memberProperties

for (prop in propriedades) {
    println("${prop.name}: ${prop.returnType}")
}

// Acessar propriedades
val usuario = Usuario("João", 30)
val prop = Usuario::class.memberProperties.find { it.name == "nome" }
val valor = prop?.getter?.call(usuario)

// Chamar métodos
val metodo = Usuario::class.memberFunctions.find { it.name == "apresentar" }
metodo?.call(usuario)
```

## 12. Programação Funcional Avançada

### 12.1 Currying
```kotlin
fun somar(a: Int, b: Int): Int = a + b

// Transformar em função curried
fun somar(a: Int) = { b: Int -> a + b }

// Uso
val somar5 = somar(5)
println(somar5(3))                      // 8
```

### 12.2 Função Compose
```kotlin
fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { a -> f(g(a)) }
}

val adiciona1 = { x: Int -> x + 1 }
val multiplica2 = { x: Int -> x * 2 }

val adiciona1Multiplica2 = compose(multiplica2, adiciona1)
println(adiciona1Multiplica2(5))        // (5+1)*2 = 12
```

### 12.3 Memoization
```kotlin
fun <T, R> memoize(funcao: (T) -> R): (T) -> R {
    val cache = mutableMapOf<T, R>()
    return { parametro ->
        cache.getOrPut(parametro) {
            funcao(parametro)
        }
    }
}

val fib: (Int) -> Long = { n ->
    if (n <= 1) n.toLong() else fib(n-1) + fib(n-2)
}

val fibMemoized = memoize({ n ->
    if (n <= 1) n.toLong() else fib(n-1) + fib(n-2)
})
```

## 13. Sealed Classes e Pattern Matching

```kotlin
sealed class Resultado<T> {
    data class Sucesso<T>(val dados: T) : Resultado<T>()
    data class Erro<T>(val excecao: Exception) : Resultado<T>()
    class Carregando<T> : Resultado<T>()
}

// Pattern matching exhaustivo
fun <T> processar(resultado: Resultado<T>) {
    when (resultado) {
        is Resultado.Sucesso -> println("Sucesso: ${resultado.dados}")
        is Resultado.Erro -> println("Erro: ${resultado.excecao.message}")
        is Resultado.Carregando -> println("Carregando...")
    }
}
```

## 14. Scope Functions Avançado

### 14.1 apply vs also
```kotlin
// apply retorna o próprio objeto
data class Configuracao(var porta: Int = 8080)

val config = Configuracao().apply {
    porta = 9090
}
println(config.porta)                   // 9090

// also faz efeito colateral, retorna o objeto
"arquivo.txt"
    .also { println("Processando: $it") }
    .apply { /* abrir arquivo */ }
```

### 14.2 run vs let
```kotlin
// run: this context
"Texto".run {
    println("Comprimento: $length")
    uppercase()
}

// let: parameter
"Texto".let { texto ->
    println("Comprimento: ${texto.length}")
    texto.uppercase()
}
```

## 15. Multiplatform Programming

```kotlin
// Expect (interface compartilhada)
expect fun obterPlataforma(): String

// Actual (implementação específica - common)
actual fun obterPlataforma(): String = "JVM"

// Actual (implementação específica - JS)
// actual fun obterPlataforma(): String = "JavaScript"
```

## 16. Builder Pattern com DSL

```kotlin
class SQLBuilder {
    private val clauses = mutableListOf<String>()
    
    fun select(vararg colunas: String) {
        clauses.add("SELECT ${colunas.joinToString(", ")}")
    }
    
    fun from(tabela: String) {
        clauses.add("FROM $tabela")
    }
    
    fun where(condicao: String) {
        clauses.add("WHERE $condicao")
    }
    
    fun build(): String = clauses.joinToString(" ")
}

fun sql(bloco: SQLBuilder.() -> Unit): String {
    val builder = SQLBuilder()
    builder.bloco()
    return builder.build()
}

// Uso
val query = sql {
    select("id", "nome", "email")
    from("usuarios")
    where("idade > 18")
}
println(query)
// SELECT id, nome, email FROM usuarios WHERE idade > 18
```

## 17. Flow para Programação Reativa

```kotlin
// Importar: import kotlinx.coroutines.flow.*

fun contarAte(max: Int) = flow {
    for (i in 1..max) {
        delay(100)
        emit(i)
    }
}

// Coletar valores
runBlocking {
    contarAte(5).collect { valor ->
        println(valor)
    }
}

// Transformações
runBlocking {
    contarAte(5)
        .map { it * 2 }
        .filter { it > 4 }
        .collect { println(it) }
}
```

## 18. Boas Práticas Kotlin Avançadas

1. **Prefira type-safe DSLs**: Crie DSLs para reduzir boilerplate
2. **Use inline functions**: Em bibliotecas para melhor performance
3. **Reified type parameters**: Quando precisar de informação de tipo em runtime
4. **Lazy initialization**: Para valores custosos
5. **Delegação**: Para compartilhar implementação
6. **Pattern matching**: Use `when` ao máximo
7. **Corrotinas**: Para operações assíncronas
8. **Extension functions**: Para adicionar funcionalidade sem herança

## Conclusão

Kotlin oferece recursos poderosos que vão além do que Java oferece. Dominar estes conceitos avançados torna você capaz de escrever código mais limpo, seguro e eficiente.

## Referências

- [Documentação Kotlin Oficial](https://kotlinlang.org/docs)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Kotlin Reflection](https://kotlinlang.org/docs/reflection.html)
