package org.ceeker.web.sbootm;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;


/**
 * redis 发布
 * @author zhangxiaoling01
 * @date  2016年6月7日 下午9:23:00
 * @see
 */
public class RedisPublish {

    private Jedis jedis = null;

    @Before
    public void init() {
        jedis = new Jedis("10.0.2.15", 6379);
    }

    @Test
    public void publish() {
        int i=0;
        while (true) {
            //发布消息
            jedis.publish("channel1", "hello");
            i++;
            System.out.println("i="+i);
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
