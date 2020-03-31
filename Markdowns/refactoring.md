# LPOO - REFACTORING
### Aula dada na Plataforma Google Meets por André Restivo - 25-03-2020

## Refactoring

+ Testes são importantes em Refactoring

### 1a Parte - Code Smells

+ Leva a um problema maior e mais complexo no sistema
+ *Sniffable* - fácil de encontrar
+ Meros indicadores de erros

1. **Bloaters**

Código e métodos demasiado grandes e complexos para trabalhar

+ **Long Method** - demasiadas linhas de código
+ **Large Class** - uma classe com demasiados campos/métodos/linhas de código
+ **Primitive Obsession** - primitivas em vez de objetos pequenos para tarefas simples
+ **Long Parameter List** -  mais do que 3 ou 4 parâmetros num método
+ **Data Clumps** - partes diferentes de código que contém grupos de variáveis semelhantes

2. **Object-Orientation Abusers**

Aplicação incorreta de OOP

+ **Switch Statements** - switch/if complexos
+ **Temporary Field** - campos que têm valores dependendo das circunstâncias
+ **Refused Bequest** - se uma subclasse usa apenas um dos métodos e propriedades das classes pai
+ **Alternative Classes with Different Interfaces** - duas classes que desenpenham funções semelhantes mas com métodos com nomes diferentes

3. **Change Preventers**

Tornam a mudança de código mais complexa

+ **Divergent Change** - mudança de determinados métodos quando se aplica uma pequena alteração numa classe
+ **Shotgun Surgery** - demasiadas mudanças pequenas em demasiadas classes diferentes
+ **Parallel Inheritance Hierarchies** - ao criar uma subclasse para uma classe, acaba-se por criar mais subclasses para outras classes

4. **Dispensables**

Tornam o código mais _clean_ se não existirem

+ **Comments**
+ **Duplicate Code**
+ **Lazy Class** - classes que não fazem muito
+ **Data Class** - classes que têm campos e métodos que apenas servem para ter acesso a uma determinada data
+ **Dead Code** - classes, parâmetros, etc. que já não são usados
+ **Speculative Generality** - classes que já não são usadas que foram criadas para antecipar mudanças futuras

5. **Couplers**

Classes que estão demasiadamente acopladas uma à outra

+ **Feature Envy** - um método que acede a data de outro objeto do que à sua prórpia data
+ **Inappropriate Intimacy** - uma classe que usa _internal fields_ e métodos de outra classe
+ **Message Chains** - a->b()->c()->d()
+ **Middle Man** - de uma classe serve apenas para chamar outra classe

### 2a Parte - Refactoring

1. **Composiong** Methods
2. **Moving** Features Between Objects
3. **Organizing** Data
4. **Simplifying** Conditional Expressions
5. Making Methods Calls **Simpler**
6. Dealing With **Generalization
7. **Big** Refactorings

#### Estrutura

+ Nome
+ Sumário
+ Motivação
+ Mecânicas
+ Exemplo

1. **Composing Methods**

_**Streamlining** methods, removing **code duplication** and making **future improvements** easier._

Lidam com métodos e variáveis temporais:
+ **Inline Temp** - variáveis temporais que estão _assigned_ a resultados de expressões simples
+ **Extract Variable** - 