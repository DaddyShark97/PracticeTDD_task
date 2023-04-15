package com.example.practicetdd_task

interface Constrain {

    fun constrain(collection: List<String>, item: String): Boolean

    class Base(private val forWrapper: For) : Constrain {

        override fun constrain(collection: List<String>, item: String): Boolean {
            var result = false
            forWrapper.repeat(collection.size) { index ->
                result = collection[index] == item
                return@repeat result
            }
            return result
        }
    }
}

interface For {

    fun repeat(max: Int, block: (Int) -> Boolean) =
        repeat(max, 0, block = block)

    fun repeat(max: Int, start: Int, block: (Int) -> Boolean)

    class Base : For {

        override fun repeat(max: Int, block: (Int) -> Boolean) {
            for (i in 0 until max) {
                if (block.invoke(i))
                    break
            }
        }

        override fun repeat(max: Int, start: Int, block: (Int) -> Boolean) {
            TODO("Not yet implemented")
        }
    }
}