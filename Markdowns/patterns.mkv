# LPOO - PATTERNS

## Design Patterns

### Slide 1

Hoje vamos finalmente começar a falar sobre uma das componentes mais importantes desta unidade curricular: os **design patterns**.

### Slide 5

Antes de começarmos a falar dos padrões propriamente ditos, um bocado de história.

Em 1977, o arquitecto **Christopher Alexander** escreveu um livro (“A Pattern Language: Towns, Buildings, Construction”) contendo um conjunto de padrões sobre **arquitectura de edíficios e cidades, e design urbano**.

Mas mais do que o conteúdo em si, inventou um novo conceito: o de uma **linguagem de padrões**.

Neste livro, cada padrão apresenta um problema comum em arquitectura e uma **solução genérica** para esse problema.

Cada padrão tem um **nome** e um **número**, e está **interligado** com vários outros padrões.

O livro **não** é só um conjunto de receitas, mas uma nova linguagem. Cada padrão é apresentado **não** como a solução definitiva para o problema mas como uma **template** que tem as suas vantagens e desvantagens — **forças** que têm de ser tomadas em consideração, cada qual a puxar para o seu lado — e tem de ser **adaptada** a um **problema específico**.

Mas vamos ao que interessa :-)

### Slide 6

Em 1994, surge um novo livro, com **forte inspiração** no livro do Christopher Alexander, com o título **“Design Patterns: Elements of Reusable Object-Oriented Software”**.

Este livro, que é considerado um livro fundamental para qualquer engenheiro informático, contém 23 padrões clássicos e foi escrito pelos:

+ [Erich Gamma](https://en.wikipedia.org/wiki/Erich_Gamma) (um dos criadores do JUnit que já usaram para implementar testes unitários)
+ Richard Helm
+ [Ralph Johnson](https://en.wikipedia.org/wiki/Ralph_Johnson_(computer_scientist))
+ [John Vlissides](https://en.wikipedia.org/wiki/John_Vlissides)
+ E com um prefácio do [Grady Booch](https://en.wikipedia.org/wiki/Grady_Booch) (um dos criadores do UML).

Juntos os 4 autores são conhecidos como o “Gang of Four” ou apenas **GoF**.

### Slide 7

É fundamental perceber, que estes padrões **não são receitas, bibliotecas**, ou **código** que possa ser simplesmente copiado para os vossos projectos. Mas antes, **sugestões** de como resolver problemas **comuns** e **recorrentes**.

Design Pattern: “_A **general, reusable** solution to a **commonly occurring** problem within a given **context** in software design._”

É também importante perceber que, normalmente, quem escreve o padrão, não é quem inventou a solução (_a solução já foi reinventada várias vezes, e os padrões servem exactamente para deixarmos de ter de reinventar a roda_). Apenas se apercebeu que a solução que está a descrever é uma solução recorrente para um problema comum e que pode, e deve, ser considerada um padrão.

Estes são os 23 padrões descritos pelo GoF. Como podem ver, dividem-se em 3 categorias:
+ **Creacionais**, que se preocupam com diferentes formas de **criar objectos**, que não a instanciação directa.
+ **Estruturais**, que se preocupam com a forma como os objectos são **organizados** e **compostos** de maneira a criar **estruturas** úteis.
+ E **comportamentais**, que se preocupam com a forma como os objectos **interagem** e comunicam.

### Slide 9

A descrição de cada um destes padrões contém os mesmos elementos:
+ **Nome do Padrão**, um elemento fundamental que permite que o padrão se torne parte da linguagem corrente.
+ **Intenção**, de um forma muito concisa, qual a intenção do padrão.
+ **Também conhecido como**, nomes alternativos pelo qual o padrão é conhecido.
+ **Motivação**, um exemplo motivacional, simples mas concreto que nos ajuda a perceber em que casos o padrão é útil.
+ **Aplicabilidade**, em que situações é que o padrão pode ser implementado.
+ **Estrutura**, a estrutura genérica do padrão de uma forma abstracta (ou seja sem se focar num problema específico).
+ **Participantes**, uma descrição mais detalhada das classes participantes no padrão.
+ **Classificação**, qual o tipo de padrão.
+ **Colaboração**, uma descrição de como as classes ou objectos colaboram entre si de forma a realizar o padrão.
+ **Consequências**, quais as vantagens e desvantagens de se usar este padrão. O que é que estamos a ganhar e o que é que estamos a perder. Quais os efeitos secundários. Quais são as forças.
+ **Implementação**, a solução, ou como implementar o padrão.
+ **Código Exemplo**, algum código para exemplificar a implementação.
+ **Usos Conhecidos**, casos reais de utilização do padrão.
+ **Padrões Relacionados**, padrões que podem ser usados em alternativa ou em colaboração com este padrão.


## Factory Method

### Slide 10

Durante esta “_aula_”, vamos explorar 5 padrões diferentes, começando pelo padrão **Factory Method**.

### Slide 11

Podem ver nesse slide a intenção e a motivação de um padrão (ambos resumidos).

De vez em quando queremos que uma superclasse permita instanciar objectos de **um certo tipo** (ou seja, que implementem uma certa interface), mas queremos que sejam as **subclasses a decidir qual o tipo específico de objectos** que queremos que seja instanciado.

Como exemplo **motivacional**, vamos imaginar uma _framework_ para **edição de documentos** que pode ser **_extendida_** para ser usada com **qualquer tipo** de documentos.

Na parte superior do diagrama, podemos ver que a **framework** é composta por **duas classes abstractas**: a _aplicação_ e o _documento_.

Do lado direito temos uma **anotação** UML (papel com o canto dobrado) que mostra o código do método **_NewDocument()_**. Como podemos ver, este método **chama** o método **_CreateDocument()_** para criar um **novo documento**.

Mas a classe abstracta _Application_ **não sabe que tipo de documento deve criar** (depende da aplicação concreta). Por esse motivo o método _CreateDocument()_ é **abstracto**. As classes derivadas vão ser obrigadas a implementar esse método.

A parte de baixo do diagrama mostra uma aplicação (_MyApplication_) baseada nesta framework, e que **implementa** o método _CreateDocument()_ de forma a que um documento do tipo _MyDocument_ seja criado.

Desta forma, a framework **não tem de ser modificada** sempre que um novo tipo de aplicação é adicionado. Por exemplo, com métodos diferentes para cada aplicação criada, ou um _switch case_ que escolhe o tipo de documento a criar.

Reparem que a parte superior do diagrama seria uma framework complexa que iria permitir fazer montes de coisas.

A ideia é ser muito simples eu conseguir _extender_ essa framework para um novo tipo de documentos.

E para isso deixo o método de como criar um novo documento como _abstract_ e quem for extender a framework apenas tem de fazer override a esse método.

### Slide 12

Devemos **usar este padrão** sempre que:

+ Uma classe **não tem forma de saber** que tipo de objecto instanciar.
+ Uma classe **quer que sejam** as subclasses a decidir que tipo de objecto instanciar.
+ Uma classe delega a decisão a classes _helper_ mas são as subclasses que sabem **que classe** _helper_ **deve ser usada**.

Como **consequência**, deixamos de ter de introduzir conhecimento sobre classes específicas a cada caso de uso, em código que não precisa de as conhecer.

### Slide 13

Isto é a estrutura do padrão tal como está no livro do GoF.

A estrutura abstracta do padrão é a mesma do exemplo mas em vez de chamarmos às classes _Application_ e _Document_, chamamos **_Creator_** e **_Product_**.

As subclasses concretas são a **_ConcreteCreator_** e **_ConcreteProduct_**. Estes nomes representam o **papel** que cada uma das classes do vosso código, caso usem este padrão, vai tomar.

Reparem que no vosso código, não têm de usar estes nome. Embora muitas vezes seja isso que acontece para ser mais fácil de comunicar o que estamos a tentar fazer.

Quando digo usar estes nomes, seria algo do género HeroCreator (por exemplo).

Assim, quem vir o código pode ter uma ideia de que ali está a ser usado um padrão.

### Slide 14

Algumas variações do padrão (só algumas):

+ A classe _Creator_ pode **não ser abstracta** e definir um tipo de objectos como sendo o tipo criado por omissão.
+ O método que cria objectos pode receber um **parâmetro** que especifica o tipo de objectos a criar (ou outro tipo de informação).
+ Há a possibilidade de usar **genéricos** (o mesmo que templates de C++) para evitar ter de criar subclasses da classe _Creator_. Vamos ver isto mais tarde...

Isto vai fazendo mais sentido à medida que os formos utilizando. A ideia não é andar a forçar o uso de padrões só porque sim. É ficarem com uma ideia dos que existem e quando se deparam com um problema conseguirem perceber que padrões poderiam eventualmente ser usados para o resolver.

## Composite

### Slide 15

Vamos passar agora ao segundo padrão: o Composite. Este acho que é mais fácil de entender.

### Slide 16

Muitas vezes precisamos não só que um objecto **contenha vários objectos** de um determinado tipo, mas que também possa ter **objectos do seu próprio tipo**.

Como exemplo **motivacional**, imaginemos uma aplicação de **desenho vectorial** (tipo o [draw.io](http://draw.io/), o inkscape ou o illustrator).

Já vimos várias vezes o exemplo de termos uma classe **_Shape_ abstracta** que tem várias subclasses que representam **_shapes_ concrectas** (rectângulo, círculo, triângulo, …).

A maior parte destas aplicações permite que a gente faça grupos com estas shapes que depois podem ser **alterados em conjunto** (translação, alteração da cor, rotação, …).

Para isso podemos criar uma **class _Group_** que é uma **agregação** (♢) de _Shapes_.

Mas agora temos **dois problemas**:

1. As **operações** que se pode fazer a um grupo **são as mesmas** que se pode fazer a uma _shape_.
2. Se calhar gostaríamos de poder fazer **grupos de grupos**, tal como fazemos grupos de shapes.

Isto tudo indica que se calhar a classe **_Group_** também devia ser **subclasse da classe _Shape_**.

Dessa forma, tudo o que **posso fazer** com uma _Shape_, **também posso fazer** com um _Group_.

E qualquer método que **receba** uma _Shape_ **pode receber** um _Group_.

Para além disso, como um _Group_ é uma agregação de _Shapes_, e um _Group_ **é** uma _Shape_, os _Groups_ passam a poder ter _Groups_ lá dentro. Ficamos assim com uma **árvore de Shapes**.

Vamos imaginar como poderia ser o método **_move(deltax, deltay)_** de um Group.

O código move de um círculo seria:
```` java
public void move(int deltax, int deltay) {
  this.cx += deltax;
  tihs.cy += deltay;
}
````
Algo deste género, certo:
```` java
public void move(int deltax, int deltay) {
  for (Shape shape : shapes)
    shape.move(deltax, deltay);
}
````

### Slide 17

Devemos **usar** este padrão quando:

+ Queremos estabelecer uma **hierarquia de objectos** em árvore.
+ Queremos que o **cliente** dessa hierarquia possa **ignorar** se está a trabalhar com **um só elemento** da hierarquia, ou com uma **composição** de elementos.

As **consequências** de usar este padrão são:
+ Podemos criar **objectos** mais **complexos** criando grupos de objectos.
+ Os **clientes**, ou seja, quem usa os objectos, ficam mais **simples** porque **não** se têm de **preocupar** com saber se estão a trabalhar com um grupo ou com um objecto isolado.
+ Torna-se **simples** adicionar **novos tipos** de objectos.

### Slide 18

A estrutura abstrata do padrão é similar à do exemplo. A classe **_Client_** representa **qualquer classe** que use a árvore de objectos.

Em vez de _Shape_, dado que isto pode ser usado em outros casos que não aplicações de desenho, temos a class **_Component_**.

A cada um dos componentes chamamos **_Leaf_**. E ao grupo chamamos **_Composite_** (que é o nome do padrão).

Mas isto são só nomes para nos abstrairmos um bocado de um exemplo em particular.

### Slide 19

Podemos ainda considerar várias **variações** a este padrão:
+ Podemos querer que as _Leafs_ saibam **de que grupo** fazem parte. Pode dar jeito em alguns casos.
+ Podemos querer que grupos (_Composites_) **partilhem** _Components_.
+ Pode ser necessário saber a **ordem** dos filhos de um _Composite_.
+ E em alguns casos, pode ser útil poder fazer **_cache_** do resultado de operações. Por exemplo, a classe _Shape_ pode saber calcular a área, e a área de um _Group_ ser a soma das áreas das _Shapes_. Se tivermos uma árvore muito profunda, este cálculo pode tornar-se lento. Mas os _Groups_ podem **guardar** a área das _Shapes_ e só **recalcular** se as _Shapes_ mudarem.

## Command

### Slide 20 

Vamos agora ver o próximo padrão que se chama **Command**. Este padrão é extremamente simples mas abre a porta a muitas possibilidades.

### Slide 21

Este padrão usa-se quando queremos encapsular um pedido num objecto de forma a que este possa ser **parametrizável**, que um objecto possa ser parametrizado com um pedido, que os pedidos possam estar numa **lista de espera**, possam ser **repetíveis** ou **desfazíveis** (operação de _undo_), …

Reparem que quando falo em **pedido**, estou a falar de, tipicamente, uma **chamada a uma função**.

Se forem ver os meus slides iniciais sobre Java, o [Alan Kay](https://en.wikipedia.org/wiki/Alan_Kay) até chamava aos pedidos, **mensagens**. 

Imaginem que têm uma classe com um método _execute()_ e que querem que quando esse método for chamado, algo aconteça, mas querem poder ser vocês, como **cliente** da classe, a **decidir** o que **acontece** quando o método é chamado.

Isto fica mais claro com um exemplo...

Como exemplo **motivacional**, vamos imaginar uma aplicação de _desktop_ com um menu (classe _Menu_). A esse ménu podemos adicionar vários **items** (classe _MenuItem_) e queremos que cada um desses items faça algo **diferente** quando a sua função _clicked()_ é **executada**.

Podemos, obviamente, ter várias subclasses de _MenuItem_, cada uma com a sua **implementação** do método _clicked()_.

Mas agora imaginemos que queremos que a operação que o _MenuItem_ executa possa ser executada de **outra forma**. Por exemplo, através de um _shortcut_, ou noutro sítio da interface gráfica.

Ou imaginemos que queremos criar comandos que não são mais do que uma **composição** de dois comandos. Por exemplo: _Open = New + Load_, ou _Save and Quit = Save + Quit_, …

Ou que queremos que os utilizadores possam fazer **_undo_** das operações. **Onde** é que guardamos cada uma das operações que já foi feita?

Imaginem por exemplo no Hero, como é que implementavam o Undo?

Quando fazem moveHeroLeft() não estão a guardar que movimentos foram feitos.

A solução para isto é criar uma classe _abstract_ chamada **_Command_**, que possa ser **extendida** de forma a criarmos os vários comandos que a aplicação aceita como **subclasses**.

Cada _MenuItem_ depois pode ter um ou vários **_Commands_** associados, e executar cada um deles.

Podemos guardar a **lista** de _Commands_ que foi **executada** numa _Stack_ e deixar que cada comando saiba como fazer **_undo_** da sua operação.

Pergunta: Como é que eu posso fazer comandos complexos? Em que cada comando pode ser um conjunto de outros comandos. E que pode haver comandos que são conjuntos de conjuntos de comandos.

Podemos ter comandos que são uma **composição** de vários comandos. Podemos até juntar este padrão ao padrão **_Composite_** e ter comandos que são **composições de composições** de comandos. As hipóteses são imensas.

### Slide 22

Devemos **usar** este padrão quando:

+ Queremos **parametrizar** os **objectos** com uma acção a ser executada.
+ Especificar a acção num determinado sítio mas só a executar **mais tarde**. Por exemplo, colocando as acções numa **fila de processamento**.
+ Suportar operações de **undo** ou **redo**.
+ Ter um **log** das alterações efectuadas de forma a que as possámos **voltar a fazer**. Útil por exemplo caso algo corra mal num comando e tenhamos perdido as alterações efectuadas. Vários sistemas tolerantes a falhas usam sistemas deste género.
+ Implementar sistemas baseados em operações de baixo-nível muito **simples** que depois são **compostas** em operações mais **complexas** (padrão _Composite_).

As **consequências** de usarmos este padrão são:

+ **Separamos as responsabilidades de saber** quando executar um comando e como executar o comando (_Single Responsibility Principle_).
+ Os comandos podem ser **extendidos** e manipulados como qualquer outro objecto. Por exemplo o comando _SaveAs_ pode ser derivado do comando _Save_. Ou um comando pode ser parametrizado com o nome do ficheiro, …
+ Torna-se fácil ter comandos **compostos**.
+ Torna-se fácil **adicionar** novos comandos.

Queria também que percebessem que há uma ligação forte entre estes padrões e os princípios SOLID.

Os princípio são só isso, princípios.

De vez em quando o nosso problema é que não sabemos como não violar um princípio. E pode ser aí que nos lembramos de um padrão que dá jeito.

### Slide 23

A **estrutura** abstracta deste padrão pode parecer um pouco mais complexa, mas não é. Vamos partir isto em bocados mais simples.

Do lado direito temos a classe abstracta **_Command_** e um **_ConcreteCommand_** que é uma implementação de um comando **específico** (por exemplo _SaveCommand_). Reparem que o comando concreto pode ter um estado interno (por exemplo para ajudar a fazer _undo_).

É normal que cada comando tenha de saber **em que objecto vai actuar**. Por essa razão, é normal os comandos estarem associados a um **_Receiver_**. É também normal que esse receiver seja passado ao comando no **construtor** (por exemplo: _new CloseCommand(document)_).

No caso do Hero, o Receiver de um MoveHeroLeftCommand seria provavelmente a Arena.

O **_Client_** é a class que **cria** o comando, e o **_Invoker_** é a classe que depois o vai **executar**.

Que até podem ser a mesma.

Reparem que **nenhuma** destas classes (_Client_, _Invoker_ e _Receiver_) tem de **existir**. São só representações **abstractas** de classes que vão existir no vosso código (por exemplo, _MenuItem_ ou _Application_)

### Slide 24

Algumas (só algumas) da variações possíveis para este padrão:

+ O comando **não** ser o **responsável** por executar realmente a acção mas apenas **delegar** no método correto do _Receiver_. Ou seja, o método _execute()_ de cada comando realmente ter apenas **uma linha**.
+ Suportar operações de _undo_ e _redo_, acrescentando um método _undo()_ ao comando.
+ Garantir que erros (_bugs_) em undos múltiplos **não** se **propagam** pelos vários undos a serem executados (guardando por exemplo o **estado** do _Receiver_ e recusando-se a fazer _undo_ se o estado for diferente).

## Observer

### Slide 25

Vamos ver agora um dos padrões mais usados em linguagens orientadas a objectos, o padrão **Observer**. Também conhecido como **Listener** ou **Publisher/Subscriber**.

Por exemplo, em Java, quando estamos a fazer aplicações gráficas vão ver montes de métodos do tipo 

````java
addClickListener(ClickListener listener).
````

### Slide 26

Para este padrão vamos ter uma **motivação** um bocado diferente. Em vez de termos um exemplo em UML, imaginem apenas que têm alguns dados (guardados algures numa ou mais classes) e que querem mostrar os **dados** de **formas diferentes** na interface gráfica.

Será que são as classes que **guardam os dados** que devem ser **responsáveis** por **avisar** as classes responsáveis pela **visualização** sempre que os dados **mudarem**?

O que acontece se tivermos de acrescentar uma nova classe de visualização. Lá vamos nós ter de alterar a classe que guarda os dados...

Pergunta: Que princípio SOLID está a ser violado neste caso?
**_Open Close_**

### Slide 28

Como o nosso exemplo não tinha um diagrama UML, desta vez vamos ver primeiro a estrutura do padrão.

Em relação ao nosso exemplo, as vistas são os **_Observers_** e a estrutura de dados é o **_Subject_**.

Começamos por declarar duas classes **abstractas**: _Subject_ e _Observer_.

Ao _Subject_ podem ser **adicionados** _Observers_, para além disso, um _Subject_ tem um método concreto (**_Notify()_**) que **notifica** todos os _Observers_.

Um _Observer_ tem um método (**_Update()_**) que é chamado pelo _Subject_ sempre que este for alterado.

Para usar estas duas classes, basta _extendê-las_. No **_ConcrectSubject_**, **sempre** que o estado é alterado (por exemplo, porque um método **_set…()_** foi chamado) basta chamar o **_Notify()_**. O Update() do **_ConcreteObserver_** é automaticamente chamado.

Isto fica mais claro com uns exemplos:

````java
ConcreteSubject subject = new ConcreteSubject();
ConcreteObserver observer = new ConcreteObserver();
subject.attach(observer);
subject.changeSomething();
````

Quando o método **_attach()_** é chamado, o _observer_ é adicionado a uma lista de _observers_.
Quando o método **_changeSomething()_** é chamado, o método **_notify()_** é invocado de forma a que o método **_update()_** de todos os observers também seja chamado.

Em Java isto é comum:

````java
Button button = new Button("OK");
button.addClickListener(this);
````

E depois fazemos a nossa classe implementar uma interface _ClickListener_ que tem um método chamado por exemplo _onClick(Event event)_.

Como somos obrigados a fazer override desse método depois podemos decidir aí o que fazer quando o botão é _clickado_.

### Slide 27

Este padrão **usa-se** quando:
+ Uma abstração tem **dois aspectos** em que um **depende** do outro.
+ Quando uma **mudança** num objecto implica **alterar** outros.
+ Quando um objecto pretende **notificar** outros de alterações **sem ter de saber** que tipo de objectos está a **notificar**.

As **consequências** da utilização deste padrão são:
+ O **acoplamento** entre o sujeito e o observador passa a ser **abstracto**. Ou seja, não implica que saibam qual o tipo concreto um do outro.
+ Passa a ser possível ter comunicação por **broadcast**, ou seja, um sujeito notificar facilmente **vários** observadores.
+ Como os observadores não têm conhecimento um dos outros, não existe a noção do **custo/resultado** de actualizar um objecto.

### Slide 29

Variações a este padrão incluem:
+ Observadores que conseguem observar **mais do que um** _subject_.
+ Quem é **responsável** por fazer chamar o método **update()**, o próprio _subject_? Ou deve ser o **cliente** (que o modificou) a **decidir** se deve ou não **notificar** todos os _subjects_.
+ O _subject_ deve enviar as modificações para o observador (**push**), ou deve ser o observador a pedir ao _subject_ qual o novo estado (**pull**)?
+ O observador poder declarar em que tipos de alterações está **interessado**. Útil quando o _subject_ pode ser modificado de **variadas formas** mas só nos interessa **algumas** delas.

Como podem ver este padrão tem um monte de alternativas. Provavelmente porque era um dos mais usados e de variadas formas.

## Strategy

### Slide 30

Finalmente o último padrão de hoje: o **Strategy**.

### Slide 31

O padrão **Strategy** permite **encapsular algoritmos** dentro de objectos. Isto permite que o algoritmo usado pelo cliente possa ser **parametrizado** sem ser necessário alterá-lo ou _extendê-lo_.

Como **motivação**, vamos imaginar que temos uma classe **_Order_** que representa uma **encomenda**. E que a encomenda pode ter **várias formas de pagamento** (e.g., dinheiro, transferência bancária ou por cartão de crédito).

Podemos criar uma classe abstracta que representa uma estratégia de pagamento (**Payment**) que depois é especializada nos **vários tipos de pagamentos** possíveis. Desta forma, basta-nos passar à encomenda (por exemplo no constuctor) qual a estratégia que deve ser utilizada, e depois, quando o método **_pay()_** da encomenda é invocado, basta-nos chamar o método **_pay()_** da estratégia.

Exemplo de código:

````java
Order order = new Order(new CashPayment());
order.pay();
````
Quando o método **_pay()_** da encomenda é chamado, a encomenda **delega** a execução à sua **estratégia** de pagamento (neste caso ao **_CashPayment_**).

O código do pay() do order será só:

````java
public void pay() {
  paymentStrategy.pay(this);
}
````

Por exemplo...

Quando o método **_pay()_** da encomenda é chamado, a encomenda **delega** a execução à sua **estratégia** de pagamento (neste caso ao **_CashPayment_**).

Pergunta: Porque é que não podemos simplesmente extender a classe Order e criar 3 tipos de Order?
Single Responsibility Principle, Open Closed, ...

Mas há **mais razões**. Imaginem que para além de pagamentos diferentes, a encomenda tinha outro método **_shipping()_** que tratava da **forma de entrega** da encomenda. E que podia haver **dois tipos de entrega** (recolha na loja e entrega por correio). Agora em vez de termos **três**subclasses de Order, precisamos de **seis**!!!

E com mais parametrização, isto podia explodir.

Por exemplo, no caso do Hero, podiamos ter inimigos parametrizados com estratégias: MoveStrategy, AttackStrategy, ...

### Slide 32

Este padrão deve ser **usado** quando:
+ Muitas classes relacionadas **diferem apenas no algoritmo**.
+ Pode haver **várias variantes** de um algoritmo. 
+ Um algoritmo usa **dados** que **não queremos** que o cliente **conheça**.
+ Uma classe define **vários comportamentos** que aparecem em vários **_switch cases_**.

As **consequências** de usar este padrão são:
+ Pode ser usado como uma alternativa a criar subclasses de uma classe cliente.
+ Elimina _switch cases_ complexos.
+ Permite ter várias implementações alternativas de um algoritmo.
+ Os clientes têm de saber que quais as estratégias que existem.

Esse último ponto é um ponto negativo. Obriga ao cliente saber as estratégias internas.

### Slide 33

A **estrutura** abstracta deste padrão, dá o nome de **_Context_** à classe cliente da estratégia.
Essa classe terá uma forma de lhe ser **passada** a estratégia (por exemplo no constructor) a ser usada e um, ou mais, métodos que **chamam essa estratégia (_ContextInterface()_)**.
A **estratégia** será uma **classe abstracta** com um método que permite **invocar a estratégia (_AlgorithmInterface()_) e várias subclasses concretas.**

### Slide 34

Em termos de **variações**, a mais importante prende-se com o facto de podermos ter uma **estratégia por omissão** (por exemplo quando é usado o construtor vazio).

Aqui vão alguns links para se quiserem saber mais sobre cada um destes padrões: 

+ (https://refactoring.guru/design-patterns/factory-method)
+ (https://refactoring.guru/design-patterns/composite)
+ (https://refactoring.guru/design-patterns/observer)
+ (https://refactoring.guru/design-patterns/command)
+ (https://refactoring.guru/design-patterns/strategy)