### 继承
Kotlin中所有的类有个公共父类_Any_，对于没有超类声明的类它是默认父类：
```java
class Example // Implicitly inherits from any
```
_Any_不是_java.lang.Object_；特别的是，它除了_equals()_，_hashcode()_和_toString()_以外没有其他的成员。有关更多细节，请查阅[Java interoperability？](127.0.0.1)  
要声明一个显式超类，我们将父类放在类头的冒号后面：
```java
open class Base(p: Int)
class Derived(p: Int) : Base(p)
```
如果类有主构造函数，基类可以（和必须）使用主构造函数的参数被正确的初始化。  
如果类没有主构造函数，那么每个次构造函数必须使用_super_关键字初始化基类，或者将其委托给如上的其他构造函数。注意在这种情况下，不同的次构造函数调用基类不同的构造函数：
```java
class MyView : View {
    constructor(ctx: Context) : super(ctx) {
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
    }
}
```
类中的_open_注释和java的_final_的作用相反：它允许其他类继承此类。默认情况下，Kotlin中的类是final，根据Effective Java，Item 17：要么为继承专门设计，并给出文档，要不然就禁止它。 
***

 
**重写成员**  
如我们之前提到的一样，在Kotlin中我们坚持使事情变得明确。和Java不一样，对于可重写成员（我们称它_open_）和重写本身，Kotlin要求明确的注释：
```java
open class Base {
    open fun v() {}
    fun nv() {}
}
class Derived() : Base() {
    override fun v() {}
}
```
_override_注释对于`Derived.v()`是需要的。如果它丢失，编译器将会抱怨。如果一个方法没有_open_注释，就像`Base.nv()`，在子类中声明一个具有相同特征的方法是非法的，要么加上_override_，要么去掉它。在最终类（例如一个不带有_open_注释的类）中，成员被声明为open是禁止的。  
一个标记有_override_的类本身是_open_，它可以被子类重写，如果你想禁止再被重写，可以使用_final_：
```java
open class AnotherDerived() : Base() {
    final override fun v() {}
}
```
等等，那我现在怎么接入我的库？  
我们对于重写的观点（类和方法默认是final）的讨论是你使用的库中的一些子类去重写库设计者重写不是预期的方法是困难的，在这里介绍一些令人厌恶的接入。（待修改）  
我们认为这并不是一个缺点，理由如下：  
——最佳实践证明你不应允许这些hacks（？）  
——人们成功使用了其他有类似方式的语言（C++、C#）  
——如果人们真的需要这样做，仍然有其他的方法：你可以总是用Java写你的接入（hack），然后从Kotlin调用它（查阅java interop？），方面框架总是以这种方式工作。  

***

**重写规则**  
在Kotlin中，继承由下列规则控制：如果类从它的直接父类继承相同成员的多种实现，它必须重写这个成员，并且提供它自己的实现（偶尔使用继承中的某一个）。为了区分继承实现所使用的父类，我们使用_super_<超类名>的形式：例如`super<Base>`：
```java
open class A {
    open fun f() { print("A") }
    fun a() { print("a") }
}
interface B {
    fun f() { print("B") } // interface members are 'open' by default
    fun b() { print("b") }
}
class C() : A(), B {
    // The compiler requires f() to be overridden:
    override fun f() {
        super<A>.f() // call to A.f()
        super<B>.f() // call to B.f()
    }
}
```
这样从A和B都能很好的继承，并且对于a()和b()我们都没有疑虑，尽管C仅继承这些方法的各一个实现。但是对于f()，我们有C继承的两个实现，因此我们不得不在C中重写f()，并且提供自己没有歧义的实现。