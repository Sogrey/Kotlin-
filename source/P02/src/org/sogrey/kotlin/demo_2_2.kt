package org.sogrey.kotlin

/**
 * 函数
 * Created by Sogrey on 2017/6/21.
 */
//主函数
fun main(args: Array<String>) {
    println("3和4最大的是"+max(3,4))
    println("3和4最小的是"+Util().min(3,4))
}
//定义函数(求两个数里最大值)
fun max(x:Int,y:Int):Int{
    //函数体

    //通常写法 if控制流
    //return if(x>y) x else y

    //在Kotlin开发语言中，if是一个表达式，即：它返回一个值。
    //由于在此规则下普通if运行的很好，因此没有三元运算符（？：else）
    //return (x>y)?x:y //is wrong

    //也可以调用Math函数
    return Math.max(x,y)
}

class Util(){
    //求最小值
    fun min(x:Int,y:Int):Int{
        return if(x<y) x else y
    }
}