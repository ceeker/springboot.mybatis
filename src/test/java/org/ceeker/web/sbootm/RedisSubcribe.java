package org.ceeker.web.sbootm;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisSubcribe {
    private Jedis jedis = null;

    @Before
    public void init() {
        jedis = new Jedis("10.0.2.15", 6379);
    }

    @Test
    public void subcribe() {
        JedisPubSub jedisPubSub = new MyJedisPubSub();
        //监听管道,消息处理在jedisPubSub去实现
        jedis.subscribe(jedisPubSub, "channel1");
    }
}

class MyJedisPubSub extends JedisPubSub {

    @Override
    public void proceed(Client client, String... channels) {
        super.proceed(client, channels);
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(message);
        //消息处理函数
        super.onMessage(channel, message);
    }

}