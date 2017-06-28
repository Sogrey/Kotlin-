package org.sogrey.kotlin

/**
 * when
 * Created by Sogrey on 2017/6/28.
 */
fun main(args: Array<String>) {
    val score = 85
    when(score){
        in 90..100 -> println("A")
        in 75..89 -> println("B")
        in 60..74 -> println("C")
        in 0..60 -> println("D")
    }

    var level = when(score){
        in 90..100 -> 'A'
        in 75..89 -> 'B'
        in 60..74 -> 'C'
        else -> 'D'
    }
    println(level)


}