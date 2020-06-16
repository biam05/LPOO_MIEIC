# Resolução Testes Teóricos (2019/2020)

# Índice
1. [2016/2017](#20162017)
2. [2015/2016](#20152016)
3. [2014/2015](#20142015)

## 2016/2017

### 1.

"Árvores" geridas pelo *Git*

+ **Working Directory**: adiciona à *Staging Area*; onde é feito o *checkout* do *Git Directory*
+ **Stating Area**/Index(Stage): a partir de onde se faz o *commit* no *Git Directory*
+ **Git Directory (.git)**/HEAD: ???

Não sei explicar o porquê do *HEAD*, mas por exlucão de partes é a opção **D**.

### 2.

Princípios fundamentais de orientação por objetos:

+ **Abstração**: separação clara entre a interface pública e a sua implementação em concreto
+ **Polimorfismo**: um símbolo pode reprensentar diferentes diferentes coisas
+ **Herança**: objetos podem herdar propriedades e comportamentos de outros objetos
+ **Encapsulamento**: acesso restrito a algumas componentes de um objeto; facilidade em juntar a informação com as operações que manipulam essa informação

Como o **encapsulamento** não se refere à possibilidade de definir classes públicas dentro de outras classes, é a opção **B**.

### 3.

*Keywords* do tratamento de exceções em Java:

+ ``try``
+ ``throw``/``throws``
+ ``catch``
+ ``finally``

Por exclusão de partes, ``final`` não é uma *keyword* no tratamento de exceções em Java logo é a opção **B**.

### 4.

````java
System.out.println("c" + (a ? "a" : "b"));
````

O que esta linha de código faz é imprimir uma *string* começada por "c". Posteriormento, verifica o valor da variável a: se a == True, a *string* é "ca", caso contrário a *string* retornada é "cb".

+ A linha de código apresentada não tem erros de sintaxe;
+ Os parêntesis apresentados são **obrigatórios** para definir as prioridades das operações corretamente. 

Logo, a operação é a **D**.

### 5. 

+ utiliza-se a *keyword* ``super`` quando se quer chamar o construtor da superclasse. Como a classe *Object* é a superclasse de todas as outras classes criadas, pode-se utilizar a *keyword* **super**
+ não é necessário invocar o destrutor de uma classe graças ao *garbage collection* uma vez que este analisa a *heap* de forma a saber que objetos estão a ser usados oou não, apagando-os automaticamente caso não estejam a ser usados.
+ Se uma classe A é sub-classe de B, e B apenas tem o construtor por omissão, então A, no seu construtor, não tem obrigatoriamente de invocar o construtor de B, pois se o construtor deste é o construtor por omissão, pode ser omitido também.
+ Se uma classe A é sub-classe de B, e B tem apenas um construtor, tendo este parâmetros, então A terá obrigatoriamente, no seu construtor, de chamar o construtor de B, usando a *keyword* ``super`` uma vez que uma sub-classe tem sempre de chamar o construtor da sua superclasse quando este não é o construtor por omissão.

Assim, a opção correta é a **B**.

### 6.

+ Números em vírgula flutuande de precisão dupla: ``Double``/``double`` (dependendo da estrutura de dados)
+ Coleção ordenada sem duplicados:
  + A ``ArrayList`` não é ordenada e pode ter duplicados
  + O ``TreeSet`` é ordenado e elimina os duplicados
  + O ``HashSet`` não é ordenado e elimina os duplicados
  + O ``TreeMap`` é ordenado mas não elimina os duplicados

Logo a resposta correta é a **B**, ``TreeSet<Double>``.

### 7.

````java
public <T extends K<? super T, ? extends T>> void weirdMethod(T wierdParam){}
````

+ A linha de código apresentada não tem erros de sintaxe.
+ K pode ser uma classe ou uma interface (???)
+ O retorno deste método não poderá ser do tipo T, uma vez que a função é do tipo ``void`` logo não poderá ter qualquer retorno.
+ O metodo ``weirdMethod`` aceita como parâmetro um objeto do tipo TT, que implementa a interface K, a qual é parametrizável com um qualquer objeto super-classe de T e outro que é sub-classe de T.

Como tenho a certeza que a **D** está incorreta, a opção correta é a **D**, no entando não sei justificar a opção **B**.

### 8.

+ C4 implementa a interface I1
+ C2 é a superclasse de C4
+ C4 tem um atributo privado de C3 de nome r3

````java
class C4 implements I1 extends C2 {private HashSet<C3> r3};
````

Logo a resposta correta é a **A**.

### 9.

Diagramas de Estados UML:

+ As transições são instantâneas
+ Os estados compostos são estados que possuem subestados sequenciais
+ A utilização de regiões ortogonais permite evitar a explosão combinatória deestados. (acho que não demos isto nas teóricas)
+ Podem existir múltiplas transições com o mesmo evento e o mesmo estado de origem, só não podem existir transições com o mesmo par estado de origem e estado final.

Assim, a opção correta é a **E**.

### 10.

***Refactoring***: técnica controlada de melhorar o *design* e o código base existente, tornando-o mais fácil de compreender e modificar, sem alterar o comportamento observável do programa.

Logo é a opção **E**.

### 11.

+ Testes de caixa preta consistem em testes em que a estrutura interna do que está a ser testado é desconhecida ou não é tida em consideração (requisitos/funcionalidades do sistema) enquanto que os testes de caixa branca são testes que a o que está a ser testado é a estrutura interna do programa de forma a que se cubra o máximo possível do código.
+ *TTD* é uma prática em que se implementam os testes antes das funcionalidades
+ Fazendo um refactoring-a-priori, os testes unitários mantém-se igualmente eficazes.
+ Normalmente testar funcionalidade de API é mais simples de testar que as funcionalidades da GUI, acabando por não se testar tanto esta.

Assim, conclui-se que a opção correta é a **C**.

### 12.

***Mutation Testing***: tipo de testes de *software* onde se introduzem mutações no *source code* e verifica-se se os testes implementados são capazes de detetar e eliminar essas mutações.

Logo a opção correta é a **B**.

### 13.

Não demos esta matéria. (**A**).

### 14.

Não demos esta matéria. (**C**).

### 15.

***The Open-Closed Principle (OCP)***:

+ Um módulo deve ser aberto para extensão mas fechado para modificação
  + Os módulos devem ser escritos de forma a poderem ser extendidos sem ser necessário modifica-los.
  + Assim, se for necessários adicionar novas funcionalidades, simplesmente adiciona-se ao código e não é necessário fazer nenhuma modificação.

Ao usar este princípio dos SOLID, O código torna-se extensível, bastando acrescentar novas funcionalidades, sem ter de alterar código já existente, sendo, assim, a opção **A**.

### 16.

Boas práticas no desenvolvimento de código:

+ Desenvolver incrementalmente, sem presumir demasiado.
+ Registar tempos de desenvolvimento, para melhorar estimativas de esforço.
+ Manter um nível de *technical debt* (custo em alterações futuras) baixo.

Assim, por exclusão de partes, é a opção **D** (se o código for demasiado genérico, a sua manipulação pode torna-se mais complicada à medida que se avança no trabalho).

### 17.

+ ***Singleton***: assegurar que uma classe só tem uma instância e criar um ponto global para a acessar.

Verifica-se em:

````java
private static DatabaseConnectionManager instance;
public static DatabaseConnectionManager getInstance() {
    if (instance == null)
        instance = new DatabaseConnectionManager();
    return instance;  
}
````

Este ano não demos o *design pattern* *Object Pool*, mas por exlucão de partes é a opção **B**.

### 18.

+ ***Strategy***: definir uma família de algoritmos, encapsula-los e torna-los *interchangeable*. 

Usado quando queremos que o comportamento dos objetos possa ser alterado em *run time*, logo é a opção **E**.

### 19.

+ ***Observer***: definir uma dependência um-para-muitos entre objetos de forma a que quando um objeto altera, todas as suas dependências são notificadas e atualizadas automaticamente.
  + Permite o envio de informação para objectos de vários tipos de uma forma simples.
  + Podem ser acrescentados ou removidos observadores a qualquer altura.
  + Respeita o princípio arquitectural conhecido como "loose coupling" (*No client code has to be changed simply because an object it depends on needs to be changedto a dierent one*).
  + O objeto que observa não tem acesso ao estado interno do observado, apenas sabe se ocorreram alterações neste.

Logo é a opção **D**.

### 20.

O *Duplicated Method* pode ser melhorado utilizando vários *refactorings*:

+ *Extract Method*
+ *Extract Class* (extrações podem levar à eliminação de código duplicado)
+ *Pull Up Method*: usado para lidar com generalizações; usado quando subclasses têm métodos que têm funções semelhantes.

+ *Move Method*: usado quando um método é usado mais numa classe do que na própria classe (não tem nada a ver com código duplicado)

Logo é a opção **B**.

## 2015/2016

### 1.

Tipos primitivos de Java:

+ ``byte``
+ ``char``
+ ``short``
+ ``int``
+ ``long``
+ ``float``
+ ``double``
+ ``boolean``
+ ``void``

Por exclusão de partes, é a opção **C**.

### 2.

O método ``compareTo`` está definido para a interface ``Comparable``. Os restantes métodos estão definidos para todos os objetos, logo é a opção **B**.

### 3.

````java
public class C1 {
    public String f() {return"1";}
    public String g() {return"1";}
    public void print() {System.out.println(f() + g());}  
}
public class C2 extends C1 {
    public String f() {return"2";}
    public String g() {return"2";}  
}  
C1 obj = new C2();
obj.print();
````

Como utiliza o construtor de ``C2``, irá utilizar os métodos deste, logo irá imprimir "22", sendo, assim, a opção **E**.

### 4.

*Keywords* do tratamento de exceções em Java:

+ ``try``
+ ``throw``/``throws``
+ ``catch``
+ ``finally``

Por exclusão de partes, ``fail`` não é uma *keyword* no tratamento de exceções em Java logo é a opção **B**.

### 5. 

Boas práticas e ferramentos dos testes unitários:
+ As ferramentas de teste de mutação como PIT são úteis para avaliar a qualidade dos testes pois introduzem mutações no código e verificam se os testes são capazes de eliminar as mutações introduzidas.
+ Não demos o EclEmma
+ Em JUnit 4 os métodos de teste são anotados com ``@Test``.
+ Em JUnit 4, os valores retornados pelos métodos invocados a partir do código de teste são normalmente verificados através do método ``assertEquals``.

Por exclusão de partes, é a opção **B**.

### 6.

Lista ordenada de projetos preferidos por cada estudante

+ Estudantes não precisam de estar ordenados, mas sim os projetos preferidos por estes, logo tem de ser uma estruta que elimina duplicados em que o valor da chave estudante é uma estrutura que elimina duplicados e esta ordenada.

````java
HashMap<Estudante, TreeSet<Projeto>>
````

Logo, é a opção **E**.

### 7.

Não demos esta matéria. (**E**)

### 8.

Não demos esta matéria. (**A**)

### 9.

Não demos esta matéria. (**E**)

### 10.

Não demos esta matéria. (**E**)

### 11.

Não demos esta matéria. (**C**)

### 12.

Não demos esta matéria. (**E**)

### 13.

Não demos esta matéria. (**D**)

### 14.

+ C2 implementa a interface I1
+ C1 é a superclasse de C2
+ C2 tem um atributo privado de C4 de nome r4
+ C2 tem um atributo privado de C3 de nome r3

````java
class C2<T> implements I1 extends C1{ private Set<C3> r3, private C4 r4;}
````

Logo é a opção **B**.

### 15.

Num diagrama de estados UML, numa transição, é indicado o evento em que se encontra atualmente, uma condição que é necessário acontecer e a ação que é tomada quando essa condição se verifica, logo é a opção **B**.

### 16.

Diagramas de sequência UML:

+ Linhas de Vida
+ Axis
+ Atores
+ Mensagens
+ Fragmentos Combinados
+ *Frames*
+ *Gates*
+ Referências

Por exclusão de partes, é a opção **A**.

### 17.

Dependendo do estado em que se encontra o programa, o dragão terá diferentes ações, logo é o State Pattern (opção **C**).

### 18.

````java
public abstract class C {
    public abstract void f();
    public abstract void g();
    public void h() {f(); g();}}
````
+ ***Composite***: decompor objetos em estruturas em árvore para fazer hierarquias.
+ ***Visitor***: não demos este design pattern (mas não parece que seja isso...)
+ ***Strategy***: definir uma família de algoritmos, encapsula-los e torna-los *interchangeable*. 

O Template Method não foi abordado este ano, no entando, por exclusão de partes, é esta a opção (**B**).

### 19.

````java
public class Game{
    ...
    public boolean moveHero(Direction move){
        if(move == Direction.UP){ // move up
            int newPosX = hero.getX();
            int newPosY = hero.getY() - 1;
            hero.setCoord(newPosX, newPosY);
            return true;
        }
        if(move == Direction.LEFT){ // move left
            int newPosX = hero.getX() - 1;
            int newPosY = hero.getY();
            hero.setCoord(newPosX, newPosY);
            return true;
        }
    }
}
````

+ Comentários desnecessários (percebe-se que é mexer para cima e para a esquerda pelo código)
+ Há código duplicaodo:
````java
hero.setCoord(newPosX, newPosY);
return true;
````
+ Não existem variáveis temporarias
+ Utilizam-se mais as feature do Hero do que as do próprio Game

Logo a resposta é a **C**.

### 20.

````java
public class Game{
    ...
    public boolean equalPositions(Position p1, Position p2){
        return p1.getY() == p2.getY() && p1.getX() == p2.getX();
    }
    ...
}
````

O código apresentado devia presentar a classe Position, por exemplo dar override do método equals para comparar as duas posições, logo é a opção **D**.

## 2014/2015

### 1.

Princípios fundamentais de orientação por objetos (A PIE):
+ ***Abstraction***: possibilidade de definir classes como abstrações para conjuntos de objetos com propriedades similares.
+ ***Polymorphism***: possibilidade da implementação de um método variar de acordo com a subclasse do objeto.
+ ***Inheritance***: possibilidade das subclasses herdarem propriedades das superclasses.
+ ***Encapsulation***: possibilidade de restringir o acesso a algumas propriedades de um objeto e facilidade na compilação de informação e de operações que a manipulam.

Assim, a opção correta é a **B**.

### 2.

*Keywords* do tratamento de exceções em Java:

+ ``try``
+ ``throw``/``throws``
+ ``catch``
+ ``finally``

Por exclusão de partes, observa-se que ``onerror`` não é usado no tratamento de exceções, logo a opção correta é a **B**.

### 3.

Boas práticas e ferramentas de teste unitário:
+ *TTD* consiste em fazer os testes antes de implementas as funcionalidades a ser testadas
+ Não demos EclEmma
+ Em JUnit, os valores retornados são testados com o auxílio de asserções (por exemplo, assertEqual)
+ As ferramentas de teste por mutação como PIT são usadas para criar mutações no código e verificar se os testes são capazes de eliminar as mutações criadas

Logo a opção correta é a **E**.

### 4.

Funcionalidades suportadas pro Java 8:

+  Expressões lambda.
+  Referências para métodos.
+  Tipos e métodos genéricos (ou parametrizados).

Uma classe só pode estender uma classe, logo a opção correta é a **C**.

### 5.

+ Números em vírgula flutuande de precisão dupla: ``Double``/``double`` (dependendo da estrutura de dados)
+ Coleção ordenada sem duplicados:
  + A ``ArrayList`` não é ordenada e pode ter duplicados
  + O ``TreeSet`` é ordenado e elimina os duplicados
  + O ``HashSet`` não é ordenado e elimina os duplicados
  + O ``TreeMap`` é ordenado mas não elimina os duplicados

Logo a resposta correta é a **C**, ``TreeSet<Double>``.

### 6.

Não demos esta matéria. (**E**)

### 7.

Não demos esta matéria. (**D**)

### 8.

Não demos esta matéria. (**B**)

### 9. 

Não demos esta matéria. (**B**)

### 10.

Não demos esta matéria. (**C**)

### 11.

Associação navegável: um traço sem ser tracejado
Dependência: seta tracejada
Generalização: seta sem ser tracejada com um triângulo em branco na superclasse
Concretização: seta tracejada com um triângulo em branco

Logo é a opção **B**.

### 12.

+ C4 implementa a interface I1
+ C2 é a superclasse de C4
+ C4 tem um atributo privado de C3 com nome r3

````java
class C4 implements I1 extends C2{ private HashSet<C3> r3;}
````

Assim, é a opção **D**.

### 13.

Nos diagramas de sequência não se representam transições, logo a opção é a **B**.

### 14.

Podem existir transições com o mesmo evento e o mesmo estado de origem, só não podem existir múltiplas transações com o mesmo par estado de origem e estado final, logo é a opção **D**.

### 15.

+ ***Builder***: não demos este design pattern
+ ***Strategy***: definir uma família de algoritmos, encapsula-los e torna-los *interchangeable*.
+ ***Template Method***: não demos este design pattern
+ ***Composite***: decompor objetos em estruturas em árvore para fazer hierarquias.

Logo é a opção **B**.

### 16.

O modo do herói altera-se dependendo do estado deste, logo o padrão de desenho utilizado é o state (opção **A**).

### 17.

Está a ser criada uma hierarquia decompondo o objeto Window, logo está a ser utilizado o padrão de desenho composite (opção **D**).

### 18.

**Refactoring**: alterações feitas ao código para o tornar mais compreensível e fácil de manipular, sem se alterar as suas funionalidades.

Logo é a opção **B**.

### 19.

***Extract Method***: agrupar fragmentos de código
***Lazy Class***: classes que não fazem muito ao longo do programa

De nenhuma maneira, agrupar fragmentos de código irá tornas uma classe mais "útil", logo é a opção **D**.

### 20.

````java
public class Pessoa {
    private String n1; // primeiro nome
    private String n2; // ultimo nome
    private String aux;
    private int i; // idade

    public String toString(){
        aux = n1 + " " + n2;
        return "nome completo: " + aux + " idade: " + i;
    }

    public String getFullName(){
        aux = n1 + " " + n2;
        return aux;
    } ...
}
````

+ Existe código duplicado (a toString() podia invocar a getFullName())
+ A variável aux é desnecessária
+ Os comentários são desnecessários porque através das funções percebe-se a que é que cada variável se refere.

Por exclusão de partes é a opção **D**.
