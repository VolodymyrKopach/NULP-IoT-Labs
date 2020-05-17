package com.db.lab5.utils;

import com.db.lab5.constants.RedisConstants;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisUtil {
    Jedis jedis;

    public void startConnection() {
        boolean useSsl = true;
        String cacheHostname = "cache hostname from redis";
        String cachekey = "cache key from redis";

        JedisShardInfo shardInfo = new JedisShardInfo(cacheHostname, 6380, useSsl);
        shardInfo.setPassword(cachekey);
        jedis = new Jedis(shardInfo);
    }

    public void addValueToRedis(String fileName, String key, String value) {
        String currentReadStatus = getValueFromRedis(fileName, RedisConstants.READ_STATUS);
        if (currentReadStatus != null && currentReadStatus.equals(RedisConstants.COMPLETED)) {
            jedis.hset(fileName, RedisConstants.ATTEMPT_TO_REWRITE_FILE, "true");
        } else {
            jedis.hset(fileName, key, value);
        }
    }

    public String getValueFromRedis(String fileName, String key) {
        String value = jedis.hget(fileName, key);
        if (value != null) {
            return value;
        } else return "";
    }

    public void deleteFileData(String fileName) {
        jedis.del(fileName);
    }

    public void closeConnection() {
        System.out.println("All data: " + jedis.hgetAll("File 1").toString());

        jedis.close();
    }
}
