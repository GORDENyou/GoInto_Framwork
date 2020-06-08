package p.gordenyou.gointoframwork.kotlin

fun main() {
    printArray()
}

fun printArray() {
    val array1 = Array(5) { i -> i * i }
    println(array1[3])

    val array2 = IntArray(5){it}

    for(item in array1){
        println(item)
    }

    for((index, item) in array2.withIndex()){
        println("$index -> $item")
    }

    array1.forEach { i -> println(i) }

    array2.forEachIndexed { index, item -> println("$index -> $item") }
}
