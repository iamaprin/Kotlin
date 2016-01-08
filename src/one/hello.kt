package one

/**
 * /one/hello.java
 * author: iamaprin
 * date: 2016/1/4
 * time: 22:17
 * description:
 */

/*
fun main(args: Array<String>) {
    println("Hello kotlin!")
    val customer = Customer()
    print(customer.getEmail())
}
*/

fun String.last(): Char {
    return this[length - 1]
}