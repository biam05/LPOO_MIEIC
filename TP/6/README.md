# Exercise 1

## Code Smell (Order): 
+ Duplicate code - _Two code fragments look almost identical._
  
## Refactoring: 
+ Extract Method - _You have a code fragment that can be grouped together._
+ Inline Method - _When a method body is more obvious than the method itself._

_______________________________________


## Code Smell (Order Line):
+ Inappropriate Intimacy - _One class uses the internal fields and methods of another class._

## Refactoring:
+ Encapsulate Field - _You have a public field._

_______________________________________

## Code Smell (Order Line):
+ Lazy Class - _Classes that don't do much_

## Code Smell (Order):
+ Feature Envy (de Order Line) - _A method accesses the data of another object more than its own data._

## Solution:
+ _Uma OrderLine devia saber imprimir-se a si própria... Ou pelo menos retornar uma representação dela enquanto string_
+ toString() method

## Final Class Order:
![imgid](img/new_order_class.png)

# Exercise 2

## Code Smell (Related to Shape Generalization):
+ Divergent Change - _ Changing many unrelated methods when you make changes to a class._

## Refactoring:
+ Extract Subclass - _ A class has features that are used only in certain cases._

_______________________________________

## Code Smell:
+ Switch Statements -  Complex switch/if operators

## Refactoring:
+ Replace Conditional with Polymorphism - _You have a conditional that performs various actions depending on object type or properties._


