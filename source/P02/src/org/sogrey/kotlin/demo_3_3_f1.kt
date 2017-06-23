package org.sogrey.kotlin

/**
 * 条件表达式 if-else
 * Created by Sogrey on 2017/6/23.
 */
fun main(args: Array<String>) {
    val a=3
    val b=4

    val max = if (a > b) {
        println("Choose a")
        a
    } else {
        println("Choose b")
        b
    }
    println(max)
}