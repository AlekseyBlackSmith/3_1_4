import app.config.WebConfig;
import app.controller.RestTemplateClient;
import app.model.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        RestTemplateClient client = context.getBean("restTemplateClient", RestTemplateClient.class);

        User addUser = new User(3L, "James", "Brown", (byte)64);
        User updateUser = new User(3L, "Thomas", "Shelby", (byte)64);
        User userDelete = new User();
        userDelete.setId(3L);

        String sessionId = client.getAllUsers().getHeaders().getValuesAsList("Set-Cookie").get(0);
        String secret1 = client.addUser(addUser, sessionId).getBody();
        String secret2 = client.updateUser(updateUser, sessionId).getBody();
        String secret3 = client.deleteUser(userDelete, sessionId).getBody();

        System.out.println(secret1 + secret2 + secret3);
    }
}
