package com.airparking.schd

import com.airparking.schd.annotations.APTaskHandler
import com.airparking.schd.data.RedisTaskDataStore
import com.airparking.schd.data.TaskData
import com.alibaba.fastjson.JSON
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

/**
 * Created by ryan on 10/20/17
 */
open class TaskWorkerTests {
    @Test
    open fun testProcess() {
        val jedisPoolConfig: JedisPoolConfig = JedisPoolConfig()
        jedisPoolConfig.testOnBorrow = true
        jedisPoolConfig.testOnReturn = true
        jedisPoolConfig.maxTotal = 20
        jedisPoolConfig.maxIdle = 10
        jedisPoolConfig.maxWaitMillis = 1000

        val jedisPool:JedisPool = JedisPool(jedisPoolConfig, "120.76.153.169", 6380)

        val taskDataStore: RedisTaskDataStore = RedisTaskDataStore(jedisPool)

        val dataKey: String = "test:task"
        taskDataStore.dataKey = dataKey

        val taskProducer: TaskProducer = TaskProducer(taskDataStore)

        val arrays: Array<TaskData> = Array(20, {i -> TaskData("ORDER_CANCEL", JSON.toJSONBytes(i)) })
        taskProducer.add(*arrays)

        val pipeline: TaskPipeline = TaskPipeline()
        pipeline.addLast("log", LogHandler()).addLast("log2", Log2Handler())
        val taskWorker: TaskWorker = TaskWorker(taskDataStore, pipeline)

//        taskWorker.process()
    }
}

@APTaskHandler(name = "log", tag = "ORDER_CANCEL")
open class LogHandler: AbstractTaskHandler() {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(LogHandler::class.java)
    }


    override fun doHandle(taskData: TaskData?) {
        LOGGER.info("Handle {} message {}", taskData?.tag, JSON.parse(taskData?.data))
    }
}

@APTaskHandler(name = "log2", tag = "SPACE")
open class Log2Handler: AbstractTaskHandler() {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(LogHandler::class.java)
    }


    override fun doHandle(taskData: TaskData?) {
        LOGGER.info("Handle {} message {}", taskData?.tag, JSON.parse(taskData?.data))
    }
}