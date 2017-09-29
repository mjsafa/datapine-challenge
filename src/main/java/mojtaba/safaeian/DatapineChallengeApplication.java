package mojtaba.safaeian;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatapineChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatapineChallengeApplication.class, args);
    }

}
