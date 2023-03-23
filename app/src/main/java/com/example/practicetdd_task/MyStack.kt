package com.example.practicetdd_task

interface MyStack<T> {

    fun pop(): T
    fun push(item: T)

    class Base<T: Any?>(private val maxCount: Int) : MyStack<T> {

        private val array: Array<Any?>
        private var position: Int = 0

        init {
            if (maxCount <= 0) {
                throw IllegalStateException("max count should be positive!")
            }
            array = Array<Any?>(maxCount){null}
        }

        override fun pop(): T {
            if (position == 0){
                throw IllegalStateException("array is empty")
            }

            val item = array[position -1] as T
            array[position -1] = null
            position--
            return item
        }

        override fun push(item: T) {
            if (position == maxCount) {
                throw IllegalStateException("stack overflow exception, maximum is $maxCount")
            }
            array[position] = item
            position++
        }

    }
}