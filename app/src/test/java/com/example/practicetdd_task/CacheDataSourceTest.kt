package com.example.practicetdd_task

import junit.framework.Assert.assertEquals
import org.junit.Test

class CacheDataSourceTest {

    @Test
    fun test() {
        val now = FakeNow()
        val cacheDataSource: CacheDataSource =
            CacheDataSource.Timed(now = now, lifeTimeMillis = 1000)

        cacheDataSource.add(SimpleData("one"))

        now.time = 990

        val actual: List<SimpleData> = cacheDataSource.data()
        val expected = SimpleData("one")
        assertEquals(expected, actual[0])
        assertEquals(1, actual.size)

        now.time = 1010

        assertEquals(0, cacheDataSource.data().size)
    }


    private class FakeNow : Now {

        var time: Long = 0

        override fun now(): Long {
            return time
        }
    }
}