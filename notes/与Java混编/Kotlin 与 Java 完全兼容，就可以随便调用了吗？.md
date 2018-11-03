# Kotlin 与 Java 完全兼容，就可以随便调用了吗？

### Kotlin 与 Java交互语法变化

1、kotlin的方法是可以直接写在文件里，而不像Java必须写在类（class）里。其实Kotlin编译后依旧是class,其中的方法和变量都会编译成`public static`修饰的方法和变量。

java调用Kotlin方法时：`Kotlin文件名Kt.方法名(参数...)`

比如有个Kotlin文件 `Utils.kt`其中有个方法：

``` kotlin
fun echo(name:String,age:Int){
    println("$name is $age years old.")
}
```

那么，Java在调用这个Kotlin方法时应该这样：

``` java
UtilsKt.echo("Sogrey",25);
```

2、在Kotlin中以`object`关键字开头后面跟一个类的申明，这样的写法是申明一个匿名内部类的写法，

``` kotlin
//申明匿名内部类
object Clazz{
    fun sayHello(str:String){
        println(str)
    }
}
```

调用时kotlin与java写法有所区别：

``` kotlin
//kotlin
Clazz.sayHello("Hello world.")
```

``` java
//java
Clazz.INSTANCE.sayHello("Hello world.");
```

3、类的class的调用。

``` java
//Kotlin调用kotlin的class
Clazz::class
```

``` kotlin
//kotlin调用java的class
Clazz::class.java
```

### 关键字冲突处理

4、kotlin与java在关键字上的冲突

``` java
//java
class CLazz{
    public staic final String in = "in";
}
```

`in`在kotlin中是关键字，那么在调用这个java类中的 in 变量需要加上反引号：

``` kotlin
CLazz.`in`
```

### kotlin没有封装类

比如kotlin中有Int没有Integer（封装类）。

### kotlin类型空值敏感

kotlin在接受一个java类对象时，如果不能确定是否为空一定要将他赋值为可空类型，比如`String?`才能保证安全执行。

### kotlin没有静态变量与静态方法

kotlin中借助注解`@JvmStatic`让方法变成一个`public static`的方法，这样kotlin与java调用写法一样了。

``` kotlin
//申明匿名内部类
object Clazz{
    @JvmStatic
    fun sayHello(str:String){
        println(str)
    }
}
```

调用时kotlin与java写法有所区别：

``` kotlin
//kotlin调用
Clazz.sayHello("Hello world.")
```

``` java
//java调用
Clazz.sayHello("Hello world.");
```

