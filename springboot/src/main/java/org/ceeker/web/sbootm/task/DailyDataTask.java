package org.ceeker.web.sbootm.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 使用注解：@EnableScheduling 无法启动task的原因在于我当前的jdk为1.7
 * 而spring官方说明至少为1.8，因此只能使用加载配置文件的方式。
 * http://spring.io/guides/gs/scheduling-tasks/
 * 
 * 以上的说法是错误的，正确的理解是：
 * 所有的组件，比如controller，service，dao都必须位于启动类同包或者子包下面
 * 否则将不会被加载（经过验证确实如此）
 * 
 */
@Component
public class DailyDataTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //    @Scheduled(cron = "*/20 * * * * ?") // 每20秒执行一次
    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }

}
