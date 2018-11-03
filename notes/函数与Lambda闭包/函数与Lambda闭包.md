# 函数与Lambda闭包

## 函数的特性语法

kotlin中函数申明：

``` kotlin 
fun echo(name:String){
    println("$name")
}
```

带有默认值参数的函数：

``` kotlin
fun echo(name:String="默认值"){
    println("$name")
}
```

函数体仅有一行时简略写法：

``` kotlin
fun echo(name：String)=println("$name")
```



## 嵌套函数

``` kotlin
fun function(){
    val str = "hello"
    fun say(count:Int=10){
        println(str)
        if(count>0){
            say(count-1)
        }
    }
    say()
}
```

嵌套函数和java中内部类有点类似。

用于递归处理或不希望被外部函数访问到。

## 扩展函数

给现有的类扩展额外的成员方法，函数依然是以`fun`关键字在开头：

``` kotlin
fun File.readText(charset:Charset=Charset.UTF_8):String
	=readBytes().toString(charset)
```

``` kotlon
//kotlin调用
val file = File()
val content = file.readText()
```

``` java
//java中调用
String content= FileKt.readText(file,Charset.UTF_8);
//注意：java调用kotlin扩展函数时，会多一参数是类对象本身
```

扩展函数编译时会被编译到被扩展的类中去。

## Lambda闭包语法

``` java 
//java
Thread thread = new Thread(new Runnable(){
    public void run(){
        //...
    }
})；
thread.start();
//java 8中lambda写法：
Thread thread = new Thread(()->{
	//...
})；
thread.start();
```

``` kotlin
//kotlin
val thread = Thread({->Unit})
thread.start()
//无参数时
val thread = Thread({Unit})
thread.start()
//如果函数只有一个参数且这个参数是Lambda，则可以省略小括号
val thread = Thread{}
thread.start()
```

### 申明闭包

``` kotlin
val echo={name:String->
    println(name)
}
//调用时
echo("LiLei")
```

> kotlin 参数上限为22，最多有22个参数
>
> java.lang.ClassNotFoundException: kotlin.Function23

``` kotlin
package top.sogrey.kotlin.demo.demo4

fun echo(p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int, p9: Int, p10: Int, p11: Int, p12: Int, p13: Int, p14: Int, p15: Int, p16: Int, p17: Int, p18: Int, p19: Int, p20: Int, p21: Int, p22: Int, p23: Int) {
    println("$p1$p3$p1$p4 $p5$p2$p1")
}
val echoVal={p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int, p9: Int, p10: Int, p11: Int, p12: Int, p13: Int, p14: Int, p15: Int, p16: Int, p17: Int, p18: Int, p19: Int, p20: Int, p21: Int, p22: Int, p23: Int->
    println("$p1$p3$p1$p4 $p5$p2$p1")
}

fun main(args: Array<String>) {
    echo(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23)

    echoVal(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23)//这里会报错 java.lang.ClassNotFoundException: kotlin.Function23
}
```

解决方法是利用java参数不限来解决：在kotlin包下新建java `interface`文件Function23.java:

``` java
package kotlin;

public interface Function23<P1,P2,P3,P4,P5,P6,P7,P8,P9,P10,P11,P12,P13,P14,P15,P16,P17,P18,P19,P20,P21,P22,P23,R> extends Function<R>{
    R invoke(P1 p1,P2 p2,P3 p3,P4 p4,P5 p5,P6 p6,P7 p7,P8 p8,P9 p9,P10 p10,P11 p11,P12 p12,P13 p13,P14 p14,P15 p15,P16 p16,P17 p17,P18 p18,P19 p19,P20 p20,P21 p21,P22 p22,P23 p23);
}
```

再次执行就不报错了。

## 高阶函数

函数的参数是函数。

``` kotlin
fun onlyif(isDebug:Boolean,block:()->Unit){
    if(isDebug)block()
}
fun main(args:Array<String>) {
    onlyif(true){
        println("打印日志")
    }
}
```

**重点：函数是一级公民 **

``` kotlin
fun onlyif(isDebug:Boolean,block:()->Unit){
    if(isDebug)block()
}
fun main(args:Array<String>) {
    val runnable=Runnable{
        println("Runnable-run")
    }
    val function :()->Unit
    function=runnable::run
    onlyif(true,function)//输出 ：Runnable-run
}
```



## 内联函数

* kotlin中Lambda是一个匿名对象
* 可以使用`inline`修饰方法，这样当方法在编译时就会拆解方法的调用为语句嗲用，进而减少不必要的对象

如上例子可改写：

``` kotlin 
inline fun onlyif(isDebug:Boolean,block:()->Unit){
    if(isDebug)block()
}
fun main(args:Array<String>) {
    val runnable=Runnable{
        println("Runnable-run")
    }
    val function :()->Unit
    function=runnable::run
    onlyif(true,function)//输出 ：Runnable-run
}
```

这样编译后：

``` java
public static final void onlyif(boolean isDebug,Function0<Unit>block){
    if(isDebug){
        block.invoke();
    }
}
public static final void main(String[] args){
    boolean isDebug = true;
    if(isDebug){
        String str = "Runnable-run";
        System.out.println(str);
    }
}
```

> 注意：过度使用 inline 关键字会增加编译器负担，同时是代码块变得很庞大，不易查找问题，因此 inline 主要用于修饰高阶函数。

