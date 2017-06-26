package org.sogrey.kotlin

/**
 * 运算符 in,is,as
 * Created by Sogrey on 2017/6/26.
 */
fun main(args: Array<String>) {
    // in and !in
    println(3 in 1 .. 10)

    val items = listOf(1, 2, 3, 4)
    println(items.toString())
    println(3 in items)

    //is
    var a = "Hello"
    if(a is String){
        println("a is String")
    }else{
        println("a is not String")
    }

    //as
//    val y:String? = null
//    val x: String = y as String
//    println(x)

    val y1:String? = null
    val x1: String? = y1 as String?
    println(x1)
}