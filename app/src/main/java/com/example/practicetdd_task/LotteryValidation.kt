package com.example.practicetdd_task

interface LotteryValidation {

    fun isBingo(number: Int) : Boolean

    class Base : LotteryValidation {
        override fun isBingo(number: Int): Boolean {
            if (number < 10 || number > 9999_9999)
                throw IllegalStateException()

            val text = number.toString()
            val length = text.length
            if (length % 2 != 0)
                throw IllegalStateException()

            val left = text.substring(0, length / 2)
            val right = text.substring(length / 2)

            var sumLeft = 0
            var sumRight = 0

            left.forEach {
                sumLeft += it.toString().toInt()
            }

            right.forEach {
                sumRight += it.toString().toInt()
            }

            return sumLeft == sumRight
        }
    }
}