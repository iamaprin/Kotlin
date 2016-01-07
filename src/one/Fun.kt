package one

/**
 * /one/Fun.java
 * author: iamaprin
 * date: 2016/1/7
 * time: 21:17
 * description:
 */

class Fun {
    fun max(a: Int, b: Int): Int {
        if(a > b)
            return a
        else
            return b
    }
}

fun main(args: Array<String>) {
    val fun1 = Fun()
    println(fun1.max(4, 6))
}