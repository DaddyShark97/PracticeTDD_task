package com.example.practicetdd_task

import junit.framework.Assert.assertEquals
import org.junit.Test

class LegacyTest{

    @Test
    fun test() {
        val actual: LegacyObject = Legacy(text = "test", interaction = FakeInteraction).map()
        val lambda: () -> Unit = HandleInteraction(text = "test", interaction = FakeInteraction)
        val expected = LegacyObject(text = "test", lambda = lambda)
        assertEquals(expected, actual)

        actual.go()
        assertEquals("test", FakeInteraction.printCalledWithValue)
    }
}

private object FakeInteraction : Interaction {
    var printCalledWithValue = ""

    override fun print(value: String) {
        printCalledWithValue = value
    }
}