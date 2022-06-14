import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // creating the exchange
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            // defining what the message to be published will be
            String message = argv.length < 1 ? "info: Hello World!" :
                    String.join(" ", argv);

            // publishing to our named fanout exchange
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}