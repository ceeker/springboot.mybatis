package org.ceeker.web.sbootm;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * redis 订阅
 * @author zhangxiaoling01
 * @date  2016年6月7日 下午9:23:00
 * @see
 */
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

    protected static Logger logger = LoggerFactory.getLogger(MyJedisPubSub.class);  
    
    // 取得订阅的消息后的处理    
    public void onMessage(String channel, String message) {  
        logger.info("取得订阅的消息后的处理 : " + channel + "=" + message);  
    }  
  
    // 初始化订阅时候的处理    
    public void onSubscribe(String channel, int subscribedChannels) {  
        logger.info("初始化订阅时候的处理 : " + channel + "=" + subscribedChannels);  
    }  
  
    // 取消订阅时候的处理    
    public void onUnsubscribe(String channel, int subscribedChannels) {  
        logger.info("取消订阅时候的处理 : " + channel + "=" + subscribedChannels);  
    }  
  
    // 初始化按表达式的方式订阅时候的处理    
    public void onPSubscribe(String pattern, int subscribedChannels) {  
        logger.info("初始化按表达式的方式订阅时候的处理 : " + pattern + "=" + subscribedChannels);  
    }  
  
    // 取消按表达式的方式订阅时候的处理    
    public void onPUnsubscribe(String pattern, int subscribedChannels) {  
        logger.info(" 取消按表达式的方式订阅时候的处理 : " + pattern + "=" + subscribedChannels);  
    }  
  
    // 取得按表达式的方式订阅的消息后的处理    
    public void onPMessage(String pattern, String channel, String message) {  
        logger.info("取得按表达式的方式订阅的消息后的处理 :" + pattern + "=" + channel + "=" + message);  
    }  

}