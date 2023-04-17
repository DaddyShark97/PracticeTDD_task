package com.example.practicetdd_task

import kotlinx.coroutines.delay

interface DelayResponse {

    suspend fun <T> delayAfter(
        delayInMillis: Long,
        block: suspend () -> T
    ): T

    class Base(private val now: Now) : DelayResponse {
        override suspend fun <T> delayAfter(
            delayInMillis: Long,
            block: suspend () -> T
        ): T {
            val start = now.time()
            val result = block.invoke()
            if (delayInMillis > 0) {
                val durationOfBlock = now.time() - start
                if (durationOfBlock < delayInMillis)
                    delay(delayInMillis - durationOfBlock)
            }
            return result
        }
    }
}