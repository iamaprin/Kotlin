
# 类和对象
## 类和继承
### 类
Kotlin中的类用关键字_class_声明
```java
class Invoice {
}
```
类的声明由类的名称、类头（指定类型参数，主构造函数等等）和类体组成，由大括号包围。头和体都是可选的；如果类没有主体，大括号可以省略。
```java
class Empty
```
**构造函数**  
Kotlin中的类可以有一个**主构造函数**和一个或多个**次构造函数**。主构造函数是类头的一部分：它跟在类名之后（和可选的参数类型）。
```java
class Person constructor(firstName: String) {
}
```
如果主构造函数没有注释或可见修饰符，_constructor_关键字可以被忽略：
```java
class Person(firstName: String) {
}
```
主构造函数不能包含任何代码。初始化代码可以被放在_init_关键字修饰的**初始块中**：
```java
class Customer(name: String) {
    init {
        logger.info("Customer initialized with value ${name}")
    }
}
```
注意：主构造函数中的参数可以在初始块中使用。他们也可以在类体声明的property initializers（属性初始化）中使用：？
```java
class Customer(name: String) {
    val customerKey = name.toUpperCase()
}
```
实际上，对于在主构造函数声明属性和初始化他们，Kotlin有个简洁的语法：
```java
class Person(val firstName: String, val lastName: String, var age: Int) {
  // ...
}
和常规属性相同，声明在主构造函数中的属性既可以是可变的（_var_）也可以是只读的（_val_）。

如果构造函数有注释或者可见修饰符，_constructor_关键字不可省略，修饰符在前面：
```java
class Customer public @Inject constructor(name: String) { //... }
}
```
更多的细节，见可见修饰符（link）？

***

次构造函数  
类也可以声明由_constructor_修饰的次构造函数：
```java
class Person {
    constructor(parent: Person) {
        parent.children.add(this)
    }
}
```
如果这个类有主构造函数，每个次构造函数需要委托给（delegate to？）主构造函数，或者直接或间接通过另一个次构造函数，委托相同类另一个构造函数使用关键字_this_：
```java
class Person(val name: String) {
    constructor(name: String, parent: Person): this(name) {
    //iamaprin}
}
```
如果一个非抽象类没有声明任何构造函数（主构造函数或次构造函数），它会拥有一个已经生成的无参主构造函数。这个构造函数的可见性为public，如果你不想你的类有public构造函数，你需要声明一个带有非默认可见性的空主构造函数：
```java
class DontCreateme private constructor() {
}
```
**NOTE**:在JVM中，如果主构造函数的所有参数都有默认值，编译器会另生成一个使用默认值的无参构造函数。这使Koylin调用诸如Jackson和JPAke用无参构造函数创建类对象的库变得更加容易。
```java
class Customer(val customerName: String = "")
```
**创建类对象**  
创建一个类对象，我们可以像调用普通方法一样调用构造函数：
```java
val invoice = Invoice()
val customer = Customer("Joe Smith")
```
注意Kotlin没有_new_关键词

**类成员**  
一个类包括：  
--构造函数和初始化块  
--方法  
--属性  
--嵌套和内部类  
--对象声明  