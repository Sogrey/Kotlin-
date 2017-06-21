package org.sogrey.kotlin

import org.w3c.dom.Text

/**
 * 定义变量
 * Created by Sogrey on 2017/6/21.
 */
fun main(args: Array<String>) {
    //常量
    val a: Int = 1 // 立即初始化
    val b = 2 // 编译器可推导出Int型，可不声明数据类型
    val c: Int // 当没有初始化值时必须声明类型
    c = 3 // 赋值
    println("a = $a, b = $b, c = $c")

    //变量
    var x: String? = null// 定义时声明数据类型，可空时用 "?"

    if ("".equals(x) || null.equals(x)){
        println("x is empty.")
        x = "Hello Kotlin."
    }
    println("x = $x")
}