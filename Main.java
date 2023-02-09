package project.files;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.files.configs.SpringProjectConfig;
import project.files.requests.Request;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Get context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringProjectConfig.class);

        Request request = context.getBean("request", Request.class);

        request.getRequest();

    }
}
