package com.demo.log.ruuner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName: LogRunner
 * @Description: TODO 类描述
 * @Author: th_legend
 * @Date: 2021/9/2
 **/
@Component
@Slf4j
public class LogRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        while (true){
          //  log.error("log-error....");
           // log.info("log-info....");
           // log.debug("log-debug....");
            try {
                System.out.println(1/0);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(),e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
