package one

/**
 * /one/Test1.java
 * author: iamaprin
 * date: 2016/1/8
 * time: 22:00
 * description:
 */

open class A {
    open fun f() {
        print("A")
    }
    fun a() {
        print("a")
    }
}

interface B {
    fun f() {
        print("B")
    }
    fun b() {
        print("b")
    }
}

class C() : A(), B {
    override fun f() {
        super<A>.f()
        super<B>.f()
    }
}

fun main(args: Array<String>) {
    val test = C()
    test.f()
}
