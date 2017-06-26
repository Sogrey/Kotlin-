package org.sogrey.kotlin

/**
 * for循环
 * Created by Sogrey on 2017/6/26.
 */
fun main(args: Array<String>) {
    //定义一个集合
    val items = listOf(1, 2, 3, 4)
    //循环遍历集合元素输出
    for(i in items){
        println(i)
    }

    //循环遍历区间输出
    for(i in 11..13){
        println(i)
    }

}