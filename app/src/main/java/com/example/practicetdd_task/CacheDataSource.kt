package com.example.practicetdd_task


interface CacheDataSource {

    fun add(data: SimpleData)

    fun data(): List<SimpleData>

    class Timed(
        private val now: Now,
        private val lifeTimeMillis: Long,
        private val mapToCache: SimpleData.Mapper<SimpleCached> = SimpleData.Mapper.ToCache(now),
        private val notOldThan: SimpleCached.Mapper<Boolean> = SimpleCached.Mapper.NotOldThan(
            now,
            lifeTimeMillis
        ),
        private val toData: SimpleCached.Mapper<SimpleData> = SimpleCached.Mapper.ToData()
    ) : CacheDataSource {

        private val list: MutableList<SimpleCached> = mutableListOf()

        override fun add(data: SimpleData) {
            synchronized(lock) {
                list.add(data.map(mapToCache))
            }
        }

        override fun data(): List<SimpleData> {
            synchronized(lock) {
                val newList = list.filter { it.map(notOldThan) }
                list.clear()
                list.addAll(newList)
                return newList.map { it.map(toData) }
            }
        }

        companion object {
            private val lock = Object()
        }
    }
}

interface Now {

    fun now(): Long

    class Base : Now {

        override fun now(): Long {
            return System.currentTimeMillis()
        }
    }
}

data class SimpleData(
    private val name: String
) {

    fun <T> map(mapper: Mapper<T>): T = mapper.map(name)

    interface Mapper<T> {
        fun map(name: String): T

        class ToCache(private val now: Now) : Mapper<SimpleCached> {

            override fun map(name: String): SimpleCached {
                return SimpleCached(name, now.now())
            }
        }
    }
}


data class SimpleCached(
    private val name: String,
    private val timeCreated: Long

) {

    interface Mapper<T> {
        fun map(name: String, timeCreated: Long): T

        class NotOldThan(
            private val now: Now,
            private val lifeTimeMillis: Long
        ) : Mapper<Boolean> {

            override fun map(name: String, timeCreated: Long): Boolean {
                return now.now() - timeCreated < lifeTimeMillis
            }
        }

        class ToData : Mapper<SimpleData> {
            override fun map(name: String, timeCreated: Long): SimpleData {
                return SimpleData(name)
            }

        }
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(name, timeCreated)
}