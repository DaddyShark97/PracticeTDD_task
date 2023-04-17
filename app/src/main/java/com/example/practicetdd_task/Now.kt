package com.example.practicetdd_task

interface Now {

    fun time(): Long

    class Base : Now {
        override fun time(): Long = System.currentTimeMillis()


    }
}