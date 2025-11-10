import java.net.*;
public class Client {
    Socket sock;
    String hostName;
    int portNum;

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

    public String request(int num) {
        
    }
}