import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.net.util.Base64;



public class Base64Processor implements Processor {

    public void process(Exchange exchange) throws Exception {
        String data = exchange.getIn().getBody().toString();
        String decode = new String(Base64.decodeBase64(data.getBytes()));
        exchange.getMessage().setBody(decode);
    }
}
