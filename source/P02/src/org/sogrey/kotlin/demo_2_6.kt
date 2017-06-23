package org.sogrey.kotlin

/**
 * 空安全类型
 * Created by Sogrey on 2017/6/22.
 */

fun main(args: Array<String>) {

    var a: String = "abc"//不能为空

    var b: String? = "abc"//可以为空
    //第二个就比第一个多了一个`?`

    //空值检查
    val l = if (b != null) b.length else -1
    println("b.length = " + l) //b.length = 3

    var l2 = b?.length
    println("b.length = " + l2) //b.length = 3

    b = null

    l2 = b?.length
    println("b.length = " + l2) //b.length = null


    val l3 = b?.length ?: -1
    println("b.length = " + l3) //b.length = -1



    val c: String? = null
    val lc = c!!.length //Exception in thread "main" kotlin.KotlinNullPointerException
    println("c.length = " + lc)
}