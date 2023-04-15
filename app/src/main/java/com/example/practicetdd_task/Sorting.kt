package com.example.practicetdd_task

interface Sorting {

    fun sort(list: List<Int>): List<Int>

    class Base(
        private val forOut: For,
        private val forIn: For

    ) : Sorting {

        override fun sort(list: List<Int>): List<Int> {
            if (list.size < 2) return list

            val copy = list.toMutableList()

            forOut.repeat(copy.size, 0) {
                var changesCount = 0
                forIn.repeat(copy.size, 1) { j ->
                    if (copy[j] < copy[j - 1]) {
                        changesCount++
                        val temp = copy[j - 1]
                        copy[j - 1] = copy[j]
                        copy[j] = temp
                    }
                    false
                }
                changesCount == 0
            }
            return copy
        }
    }
}