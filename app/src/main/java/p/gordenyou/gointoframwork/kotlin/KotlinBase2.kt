package p.gordenyou.gointoframwork.kotlin

fun main() {
//    createCollection()

//    collectionSort()

    collectionCompare()
}

private fun collectionCompare() {
    /**
     * 集合之间的比较
     */
    val mulMap: MutableMap<String, Int> = mutableMapOf("monday" to 1, "tuesday" to 2, "wednesday" to 3)
    if ("wednesday" in mulMap) println("1.mulMap contains wednesday")
    if (3 in mulMap.values) println("2.mulMap contains wednesday")
    if (mulMap.containsKey("wednesday")) println("3.mulMap contains wednesday")

    val mulList: MutableList<Int> = mutableListOf(1, 2, 3)
    val mulList2: MutableList<Int> = mutableListOf(3, 2, 1)
    if (mulList == mulList2) println("mulList == muList2") // list中的顺序不同，代表的是不同的含义
    if (mulList.equals(mulList2)) println("mulList == muList2") // list中的顺序不同，代表的是不同的含义
}

private fun collectionSort() {
    /**
     * 集合排序
     */
    data class Language(var name: String, var score: Int)

    val listLanguage: MutableList<Language> = mutableListOf()
    listLanguage.add(Language("Java", 90))
    listLanguage.add(Language("python", 90))
    listLanguage.add(Language("Kotlin", 93))
    listLanguage.add(Language("C", 90))

    listLanguage.sortBy { it.score }
    println(listLanguage)

    listLanguage.sortWith(compareBy({ it.score }, { it.name }))
    println(listLanguage)
}

private fun createCollection() {
    /*
    Kotlin 中的集合主要分为可变集合和不可变集合
    集合的定义
    */
    val list = listOf("monday", "tuesday", "monday")
//    of[0] = "thursday" // 不可修改。
    println(list)

    val set = setOf("monday", "tuesday", "monday")
    println(set)

    val mulList = mutableListOf("monday", "tuesday", "monday")
    mulList[0] = "wednesday"
    println(mulList)
}