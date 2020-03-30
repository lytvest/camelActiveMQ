import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.impl.DefaultCamelContext;

public class Start {

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addComponent("active", createComponent());
        context.addRoutes(new RouteBuilder() {
            public void configure() {

                from("active:Queue 1")
                        .process(new Base64Processor())
                        .to("active:Queue 2");
            }
        });

        context.start();
    }

    private static JmsComponent createComponent() {
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        JmsConfiguration configuration = new JmsConfiguration();
        configuration.setConnectionFactory(connectionFactory);

        return new JmsComponent(configuration);
    }
}
