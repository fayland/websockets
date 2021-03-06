import java.net.URI;
import java.io.IOException;
import java.lang.InterruptedException;
import javax.websocket.*;

@ClientEndpoint
public class WSClient  {

    @OnOpen
    public void onOpen(Session session) throws java.io.IOException
    {
        session.getBasicRemote().sendText("{\"ticks\": \"R_100\"}");
    }

    @OnMessage
    public void onMessage(String message)
    {
        System.out.println("ticks update: " + message);
    }

    public static void main(String[] args)
        throws IOException, DeploymentException, InterruptedException
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        URI apiUri = URI.create("wss://www.binary.com/websockets/v2");
        Session session = container.connectToServer(WSClient.class, apiUri);
        Thread.sleep(10000);
    }
}
