package org.sogrey.kotlin

/**
 * 条件表达式 if-else
 * Created by Sogrey on 2017/6/23.
 */
fun main(args: Array<String>) {
    val a = 3

    if(a in 1 .. 10){
        println("has a")
    }else{
        println("has't a")
    }
}