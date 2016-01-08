package one

/**
 * /one/hello2.java
 * author: iamaprin
 * date: 2016/1/4
 * time: 21:26
 * description:
 */

public class CustomerKotlin(var name: String, var email: String, var country: String)
/*
fun main(args: Array<String>) {
    //var customer1 = CustomerKotlin("Joe", "joe@gmail.com", "US")

    //println(customer1)
    println(max(3, 5))
}
*/

fun max(a: Int, b: Int): Int {
    if (a > b)
        return a
    else
return b
}