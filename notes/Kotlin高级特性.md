# Kotlin高级特性

## 解构

``` kotlin
fun main(args:Array<String>){
    val user = User(12,"Sogrey")
    val (age,name) = user//解构 可以将一个对象user直接拆解后的赋值
    println(age)
    println(name)
}
class User(var age:Int,var name:String){
    operator fun component1() = age //component 是固定的，后面数字是序号
    operator fun component2() = name
}
```

> operator :将一个函数标记为重载一个操作符或者实现一个约定。



## 循环与集合操作符

for 循环：

``` kotlin
for(i in 1..10){
    //1到10闭区间迭代循环
}
for(i in 1 until 10){
    //until 取代上面的 ..
}
for(i in 10 downTo 1){
    //与第一个顺序相反的递减循环
}
for(i in 1..10 step 2){
    //1到10的步长为2循环
}
repeat(10){//闭包
    //重复执行
    println(it)//it是当前执行次数
}
```

## 集合操作符

rxJava中有的操作符，kotlin中都有相关的解释

``` kotin
val list  = arratListOf<char>('a','b','c','d')
val a = list.map{it-'a'} //将数据做改变
        .fillter{it>0} // 按条件过滤
        .find{it>1} //查找满足条件的值并返回
```

常用操作符：

元素操作类

* contains - 判断是否有指定元素
* elementAt - 返回对应的元素，越界也会抛出 IndexOutOfBoundsException
* fristOrNull - 返回符合条件的第一个元素，没有则给返回null
* lastOrNull - 返回符合条件的最后一个元素，没有则给返回null
* indexOf - 返回指定元素的下标，没有返回-1
* singleOrNull - 返回符合条件的单个元素，如果没有或超过一个，返回null

判断类

* any - 判断集合中是否有满足条件的元素
* all - 判断集合中的元素是否都满足条件
* none - 判断集合中是否都不满足条件，实则返回true
* count - 查村集合中满足条件元素个数
* reduce - 从第一项到最后一项进行累计

过滤类

* filter - 过滤掉所有满足条件的元素
* filterNot - 过滤掉所有不满足条件的元素
* filterNotNull - 过滤null
* take - 返回前n个元素

转换类

* map - 转换成另一个集合
* mapIndexed - 除了转换成另一个集合，还可以拿到Index(下标)
* mapNotNull - 执行转换前过滤掉null元素
* flatMap - 自定义逻辑合并两个集合
* groupBy - 按照某个条件分组，返回Map

排序类

* reversed - 反序
* sorted - 升序
* sortedBy - 自定义排序
* sortedDescending - 降序

## 作用域函数

``` kotlin
run{...}
with(T){...}
let{...}
apply{...}
also{...}
```

> 作用于函数是kotlin内置函数，可对数据做一系列变幻的操作，与集合操作符很相似，但集合操作符作用于集合，作用域函数可作用于是所有对象。

``` kotlin
    val user = User("ZhangTao")

    //let与run都会返回闭包的执行结果，区别在于let有闭包参数，而run没有闭包参数
    val letResult = user.let { "let::${it.javaClass}" }
    println(letResult)
    val runResult = user.run { "run::${this.javaClass}" }
    println(runResult)

    //also与apply都不返回闭包的执行结果，区别在于also有闭包参数，而apply没有闭包参数
    user.also {
        println("also::${it.javaClass}")
    }.apply {
        println("apply::${this.javaClass}")
    }.name = "hello"

    //takeIf 的闭包返回一个判断结果，为false时，takeIf函数会返回空
    //takeUnless 与 takeIf 刚好相反， 闭包的判断结果，为true时函数会返回空
    user.takeIf { it.name.length > 0 }?.also { println("姓名为${it.name}") } ?: println("姓名为空")
    user.takeUnless { it.name.length > 0 }?.also { println("姓名为空") } ?: println("姓名为${user.name}")

   //重复执行当前闭包
    repeat(5) {
        println(user.name)
        print(it)
    }

    //with比较特殊，不是以扩展方法的形式存在的，而是一个顶级函数
    with(user) {
        this.name = "with"
    }
    
//    user.apply{ this.name = "with"}
```

## 运算符重载

``` kotlin
fun main(args:Array<String>){
    for(i in 1..100 step 20){// 其中 .. 是通过运算符重载变成区间值，step 中辍表达式
        println("$i")
    }
}
```

kotlin算符重载关键字：`operator`


## 中辍表达式

``` kotlin
fun Int.vs(num:Int):Int{
    return when{
        this>num -> 1
        this==num -> 0
        this<num -> -1
    }
}
```

为Int扩展一函数 a(),可如下调用：

``` kotlin
5 vs 6
```

## Kotlin的反引号

* 反引号:键盘左上键与`~`一起的符号
* 在Kotlin中，可使用反引号解决关键字冲突
* 可以前行将一个不合法的命名变成合法

> ``` kotlin
> fun `123`{
>     //纯数字命名的函数
> }
> fun ` `{
>     //空格命名的函数
> }
> fun main(args:Array<String>){
>     `123`()
>     ` `()//调用函数，kotlin可以调用，java不能调用
> }
> ```
>
>

## Kotlin中比较对象

java中比较对象用到了`equals`方法，kotlin不需要：

| Kotlin | Java        |
| ------ | ----------- |
| a==b   | a.equals(b) |
| a===b  | a==b        |

## 类型链接 typealias - 用于跨平台兼容性

``` kotlin
public typealias A = File//用A替代File类,A就是File的一个别称
fun main(args:Array<String>){
    var b:File = A("filePath");
}
```

kotlin中很多类都是通过映射而来，比如Map是映射Java的Map:

``` kotlin
public typealias HashMap<K,V> = java.util.HaskMap<K,V>
```

## DSL - Domain Specific Language领域专用语言

| 外部DSL(不依赖于其他语言) | 内部DSL(依赖于其他语言)           |
| ------------------------- | --------------------------------- |
| JSON                      | Anko(依赖于Kotlin)                |
| xml                       | Kolley(依赖于Volley)              |
| css                       | build.gradle(依赖于Groovy/Kotlin) |
| makefile                  | ...                               |
| ...                       |                                   |

优点：

* 提高开发效率
* 减少沟通成本

缺点：

* 好的DSL很难设计

## 构建DSL常用方式

可能需要用到：

* Lambda语法
* 高阶函数
* 扩展函数
* 运算符重载
* 中缀表达式

举例：[Http://t.cn/RdJDEKA](Http://t.cn/RdJDEKA)

``` kotlin
/**
 * This is an example of a Type-Safe Groovy-style Builder
 *
 * Builders are good for declaratively describing data in your code.
 * In this example we show how to describe an HTML page in Kotlin.
 *
 * See this page for details:
 * http://kotlinlang.org/docs/reference/type-safe-builders.html
 */
package html

fun main(args: Array<String>) {
    val result =
            html {
                head {
                    title { +"HTML encoding with Kotlin" }
                }
                body {
                    h1 { +"HTML encoding with Kotlin" }
                    p { +"this format can be used as an alternative markup to HTML" }

                    // an element with attributes and text content
                    a(href = "http://jetbrains.com/kotlin") { +"Kotlin" }

                    // mixed content
                    p {
                        +"This is some"
                        b { +"mixed" }
                        +"text. For more see the"
                        a(href = "http://jetbrains.com/kotlin") { +"Kotlin" }
                        +"project"
                    }
                    p { +"some text" }

                    // content generated from command-line arguments
                    p {
                        +"Command line arguments were:"
                        ul {
                            for (arg in args)
                                li { +arg }
            }
                    }
                }
            }
    println(result)
}

interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

abstract class Tag(val name: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, indent + "  ")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String? {
        val builder = StringBuilder()
        for (a in attributes.keys) {
            builder.append(" $a=\"${attributes[a]}\"")
    }
        return builder.toString()
    }


    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

abstract class TagWithText(name: String) : Tag(name) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}

class HTML() : TagWithText("html") {
    fun head(init: Head.() -> Unit) = initTag(Head(), init)

    fun body(init: Body.() -> Unit) = initTag(Body(), init)
}

class Head() : TagWithText("head") {
    fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

class Title() : TagWithText("title")

abstract class BodyTag(name: String) : TagWithText(name) {
    fun b(init: B.() -> Unit) = initTag(B(), init)
    fun p(init: P.() -> Unit) = initTag(P(), init)
    fun h1(init: H1.() -> Unit) = initTag(H1(), init)
    fun ul(init: UL.() -> Unit) = initTag(UL(), init)
    fun a(href: String, init: A.() -> Unit) {
        val a = initTag(A(), init)
        a.href = href
    }
}

class Body() : BodyTag("body")
class UL() : BodyTag("ul") {
    fun li(init: LI.() -> Unit) = initTag(LI(), init)
}

class B() : BodyTag("b")
class LI() : BodyTag("li")
class P() : BodyTag("p")
class H1() : BodyTag("h1")

class A() : BodyTag("a") {
    public var href: String
        get() = attributes["href"]!!
        set(value) {
            attributes["href"] = value
        }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}
```

## 自定义DSL



