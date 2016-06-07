package org.ceeker.web.sbootm.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

/**
 * 统计切面，并将信息上报给admin server
 * @author zhangxiaoling01
 * @date  2016年5月29日 上午1:42:31
 * @see
 */
@Aspect
@Component
public class ServiceMonitor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeService;

    /**
     * 统计方法调用次数
     * @param joinPoint
     */
    @Before("execution(* org.ceeker.web.sbootm.controller.*.*(..))")
    public void countServiceInvoke(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("------ the 【{}】 method will be 【process】-----", methodName);
        startTime.set(System.currentTimeMillis());
        counterService.increment(methodName);
    }

    /**
     * 统计方法调用时长
     * @param pjp
     * @throws Throwable
     */
    @AfterReturning(pointcut = "execution(* org.ceeker.web.sbootm.controller.*.*(..))", returning = "result")
    public void latencyService(JoinPoint joinPoint, Object result) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        long costTime = System.currentTimeMillis() - startTime.get();
        gaugeService.submit(methodName, costTime);
        logger.info("------ the 【{}】 method will be 【return】,cost {} millis-----", methodName, costTime);
    }
}
