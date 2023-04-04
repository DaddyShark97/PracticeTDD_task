package com.example.practicetdd_task

interface Validation {

    fun isValid(text: String): Result

    sealed class Result {

        object Valid : Result()

        data class MinLengthInsufficient(private val minLength: Int) : Result()

        data class UpperCaseLettersCountInsufficient(
            private val upperCaseLettersCount: Int
        ) : Result()

        data class LowerCaseLettersCountInsufficient(
            private val lowerCaseLettersCount: Int
        ) : Result()

        data class NumbersCountInsufficient(private val numberCount: Int) : Result()

        data class SpecialSignsInsufficient(private val specialSignsCount: Int) : Result()
    }

    class Password(
        private val minLength: Int = 1,
        private val upperCaseLettersCount: Int = 0,
        private val lowerCaseLettersCount: Int = 0,
        private val numbersCount: Int = 0,
        private val specialSignsCount: Int = 0,
    ) : Validation {

        init {
            if (minLength < 1)
                throw IllegalStateException("minLength should be positive!")
            if (upperCaseLettersCount < 0)
                throw IllegalStateException("upperCaseLettersCount should be non-negative!")
            if (lowerCaseLettersCount < 0)
                throw IllegalStateException("lowerCaseLettersCount should be non-negative!")
            if (numbersCount < 0)
                throw IllegalStateException("numbersCount should be non-negative!")
            if (specialSignsCount < 0)
                throw IllegalStateException("specialSignsCount should be non-negative!")
        }

        override fun isValid(text: String): Result {
            if (text.length < minLength)
                return Result.MinLengthInsufficient(minLength)

            var upperCaseLettersCountActualCount = 0
            var lowerCaseLettersCountActualCount = 0
            var numberCountActualCount = 0
            var specialSignsActualCount = 0

            for (ch in text) {
                when {
                    ch.isUpperCase() -> upperCaseLettersCountActualCount++
                    ch.isLowerCase() -> lowerCaseLettersCountActualCount++
                    ch.isDigit() -> numberCountActualCount++
                    else -> specialSignsActualCount++
                }
            }

            if (upperCaseLettersCountActualCount > 0 && upperCaseLettersCountActualCount < upperCaseLettersCount)
                return Result.UpperCaseLettersCountInsufficient(upperCaseLettersCount)

            if (lowerCaseLettersCountActualCount > 0 && lowerCaseLettersCountActualCount < lowerCaseLettersCount)
                return Result.LowerCaseLettersCountInsufficient(lowerCaseLettersCount)

            if (numbersCount > 0 && numberCountActualCount < numbersCount)
                return Result.NumbersCountInsufficient(numbersCount)

            if (specialSignsCount > 0 && specialSignsActualCount < specialSignsCount)
                return Result.SpecialSignsInsufficient(specialSignsCount)

            return Result.Valid
        }
    }
}