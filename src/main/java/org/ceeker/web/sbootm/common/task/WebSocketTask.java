//package org.ceeker.web.sbootm.common.task;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WebSocketTask {
//	@Autowired
//	private SimpMessagingTemplate template;
//
//	@Scheduled(fixedRate = 2000)
//	public void echo2send() {
//		String time = new Date().toLocaleString();
//		System.out.println(time);
//		template.convertAndSend("/topic/echo_service", "测试内容，" + time);
//	}
//}