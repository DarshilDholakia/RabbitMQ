import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

public class Send {
    // naming the queue...
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        // want to connect to a RabbitMQ node on the local machine hence "localhost"...
        factory.setHost("localhost");
        try (
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()
        ) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null); // declaring a queue to send to
            String message = "Hello World!"; // specifying a message to publish
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes()); // message content is a byte array (.getBytes() encodes the String message)
            System.out.println(" [x] Sent '" + message + "'");
        }

    }
}
