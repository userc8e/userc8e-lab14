import java.net.*;
public class Client {
    private Socket sock;
    private String hostName;
    private int portNum;

    public Client(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }
    
    public Socket getSocket() {
        return sock;
    }

    public void handshake() {

    }

    public void disconnect() {

    }

    public String request(String s) {

    }
}