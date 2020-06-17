package p.gordenyou.gointoframwork.kotlin

fun main() {
    println(normalFunction(1))
    println(Test().classNormalFun(2)) // 成员方法
    println(Test.classFun(3))         // 类方法（静态方法）

    ClassUtil.printUtil()
    println(single(233))
    println(append('a', 'f', 'u'))
    println(partFun(233))
}

//普通方法
fun normalFunction(num: Int): String {
    return "普通方法$num"
}

/**
 * todo 普通类
 */
class Test {
    fun classNormalFun(num: Int): String {
        return "这是一个类的成员方法$num"
    }

    companion object {
        fun classFun(num: Int): String {
            return "这是一个类的类方法，类似于Java的静态方法$num"
        }
    }
}

/**
 * todo 工具类, object 修饰的类里面的方法全部都是静态方法
 */
object ClassUtil {
    fun printUtil() {
        println("工具类中的方法，默认为静态方法")
    }
}

/*
    todo 单表达式方法：方法体内的代码只有一行
 */
fun single(num: Int): String = "单表达式方法：结果为" + num * 2

/*
    todo 方法的参数可以有默认值，如果我们调用方法的时候不进行赋值，那么就会使用默认值。
    与Java相比，减少了方法重载。
 */
fun read(b: Array<Byte>, off: Int = 0, size: Int = b.size) {

}

/*
    todo 可变参数：用 vararg 来修饰，相当于Java中的 ...
 */
fun append(vararg char: Char): String {
    val result = StringBuffer()
    for (c in char) {
        result.append(c)
    }
    return "可变参数方法：$result"
}

/*
    todo 局部方法
 */
fun partFun(num: Int): String {
    fun part(num: Int): Int {
        return num * 2;
    }
    return "局部方法：" + part(num) * 10
}