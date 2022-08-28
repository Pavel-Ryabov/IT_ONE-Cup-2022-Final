package xyz.pary.it_one.cup2022final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import xyz.pary.it_one.cup2022final.controller.StartController;

@SpringBootApplication
public class ItOneCup2022FinalApplication {

    public static void main(String[] args) {
        Process api = null;
        if (System.getenv().get("rs.endpoint") == null) {
            System.setProperty("rs.endpoint", "http://0.0.0.0:9080");
            try {
                api = Runtime.getRuntime().exec("java "
                        + "-jar C:/Users/1234/Documents/NetBeansProjects/IT_ONECup2022/target/cup2022-1.0.jar");
                Thread.sleep(3000);
            } catch (Exception e) {
            }
        }
        ApplicationContext ctx = SpringApplication.run(ItOneCup2022FinalApplication.class, args);
        if (System.getenv().get("rs.endpoint") == null) {
            ((StartController) ctx.getBean("startController")).start();
            if (api != null) {
                api.destroy();
            }
            System.exit(SpringApplication.exit(ctx));
        }
    }

}
