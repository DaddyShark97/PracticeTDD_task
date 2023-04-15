package com.example.practicetdd_task

import org.junit.Assert
import org.junit.Test

class SortingTest {

    @Test
    fun test() {
        val forWrapperOut = TestForWrapper()
        val sorting: Sorting = Sorting.Base(forOut = forWrapperOut, forIn = TestForWrapper())
        val actual = sorting.sort(list = listOf(2, 5, 3, 1, 4))
        val expected = listOf(1, 2, 3, 4, 5)
        Assert.assertEquals(expected, actual)
        Assert.assertEquals(4, forWrapperOut.count)
    }

    @Test
    fun test_2() {
        val forWrapperOut = TestForWrapper()
        val sorting: Sorting = Sorting.Base(forOut = forWrapperOut, forIn = TestForWrapper())
        val actual = sorting.sort(list = listOf(9, 7, 3, 0))
        val expected = listOf(0, 3, 7, 9)
        Assert.assertEquals(expected, actual)
        Assert.assertEquals(4, forWrapperOut.count)
    }

    @Test
    fun test_sorting() {
        val forWrapperOut = TestForWrapper()
        val sorting: Sorting = Sorting.Base(forOut = forWrapperOut, forIn = TestForWrapper())
        val actual = sorting.sort(list = listOf(1, 2, 3, 4, 5, 7, 6))
        val expected = listOf(1, 2, 3, 4, 5, 6, 7)
        Assert.assertEquals(expected, actual)
        Assert.assertEquals(2, forWrapperOut.count)
    }
}

private class TestForWrapper(private val forWrapper: For = For.Base()) : For {

    var count = 0

    override fun repeat(max: Int, block: (Int) -> Boolean) {
        count = 0
        forWrapper.repeat(max) {
            count++
            block.invoke(it)
        }
    }

    override fun repeat(max: Int, start: Int, block: (Int) -> Boolean) {
        TODO("Not yet implemented")
    }
}