package com.example.practicetdd_task

interface RangeLimits {
    fun pair(number: Int) : RangePair

    class Base(private val list: List<Int>) : RangeLimits {
        override fun pair(number: Int) : RangePair {

            var left = Int.MIN_VALUE
            var right = Int.MAX_VALUE

                for (item in list) {

                    if (item < number){
                        left = item
                    } else if (item > number){
                        right = item
                        break
                    }
                }

            return RangePair(left, right)
        }
    }
}

data class RangePair (private val left: Int, private val right: Int)