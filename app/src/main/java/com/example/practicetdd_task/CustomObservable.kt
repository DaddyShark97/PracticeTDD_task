package com.example.practicetdd_task

interface CustomObservable<T : Any, E: CustomObserver<T>> : Update<T> {

    fun addObserver(observer: E)
    fun removeObserver(observer: E)

    class Base<T: Any, E: CustomObserver<T>> : CustomObservable<T, E> {

        private val observerList: MutableList<E> = mutableListOf()
        override fun addObserver(observer: E) {
            observerList.add(observer)
        }

        override fun removeObserver(observer: E) {
            observerList.remove(observer)
        }

        override fun update(argument: T) {
            observerList.forEach{
                it.update(argument)
            }
        }

    }
}

interface CustomObserver<T : Any> : Update<T> {

}

interface Update<T : Any> {
    fun update(argument: T)
}