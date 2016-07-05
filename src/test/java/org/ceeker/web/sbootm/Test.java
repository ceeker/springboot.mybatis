package org.ceeker.web.sbootm;

import com.sun.jdi.ThreadReference;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.event.ThreadDeathEvent;
import com.sun.jdi.request.EventRequest;

public class Test {
    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        r.addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("虚拟结退出");
            }
        }));
        System.out.println("main method 结束了");
        ThreadDeathEvent event=new ThreadDeathEvent() {
            
            @Override
            public VirtualMachine virtualMachine() {
                return null;
            }
            
            @Override
            public EventRequest request() {
                // TODO Auto-generated method stub
                return null;
            }
            
            @Override
            public ThreadReference thread() {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }

}
