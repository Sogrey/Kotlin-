package org.sogrey.kotlin

/**
 * 条件表达式 if-else
 * Created by Sogrey on 2017/6/23.
 */
fun main(args: Array<String>) {
    val a=3
    val b=4

    // 传统用法
    var max = a
    if (a < b) max = b
    println(max)

    // With else
    var max1: Int
    if (a > b) {
        max1 = a
    } else {
        max1 = b
    }
    println(max1)

    // 作为表达式
    val max2 = if (a > b) a else b
    println(max2)
}