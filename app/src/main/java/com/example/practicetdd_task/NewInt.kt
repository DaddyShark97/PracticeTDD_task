package com.example.practicetdd_task

interface NewInt {

    fun isValid(number: Int): Boolean

    class Empty : NewInt {
        override fun isValid(number: Int): Boolean {
            return true
        }

    }

    abstract class Abstract(private val newInt: NewInt) : NewInt {

        protected abstract fun isValidInner(number: Int) : Boolean

        override fun isValid(number: Int): Boolean {
            return isValidInner(number) && newInt.isValid(number)
        }
    }

    class Positive(newInt: NewInt = Empty()) : Abstract(newInt) {
        override fun isValidInner(number: Int): Boolean {
            return number > 0
        }
    }

    class Negative(newInt: NewInt = Empty()) : Abstract(newInt) {
        override fun isValidInner(number: Int): Boolean {
            return number < 0
        }
    }

    class Odd(newInt: NewInt = Empty()) : Abstract(newInt) {
        override fun isValidInner(number: Int): Boolean {
            return number % 2 != 0
        }
    }

    class Even(newInt: NewInt = Empty()) : Abstract(newInt) {
        override fun isValidInner(number: Int): Boolean {
            return number %2  == 0
        }
    }

    class Less(private val limit : Int) : NewInt {
        override fun isValid(number: Int): Boolean {
            return number < limit
        }
    }

}