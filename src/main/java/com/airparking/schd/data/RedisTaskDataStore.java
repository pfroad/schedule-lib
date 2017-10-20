package com.airparking.schd.data;

import com.alibaba.fastjson.JSON;
import kotlin.text.Charsets;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RedisTaskDataStore implements TaskDataStore<TaskData> {
    private JedisPool jedisPool;

    private String dataKey;


    public TaskData take() {
        try (Jedis jedis = jedisPool.getResource()) {
            byte[] res = jedis.spop(dataKey.getBytes(Charsets.UTF_8));
            return res == null ? null : JSON.parseObject(res, TaskData.class);
        }
    }

    public List<TaskData> take(Integer count) {
        try (Jedis jedis = jedisPool.getResource()) {
            Set<byte[]> res = jedis.spop(dataKey.getBytes(Charsets.UTF_8), count);

            return res == null ? null : res.stream().map(it -> (TaskData) JSON.parseObject(it, TaskData.class)).collect(Collectors.toList());
        }
    }

    public void add(TaskData... objs) {
        if (objs == null || objs.length == 0)
            return;

        try (Jedis jedis = jedisPool.getResource()) {
            byte[][] members = new byte[objs.length][];
            jedis.sadd(dataKey.getBytes(Charsets.UTF_8), Arrays.stream(objs).map(it -> JSON.toJSONBytes(it)).collect(Collectors.toList()).toArray(members));
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }
}
