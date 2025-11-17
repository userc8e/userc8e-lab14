import java.io.*;
import java.net.*;
public class Client {
    private Socket sock;
    private String hostName;
    private int portNum;

    public Client(String hostName, int portNum) throws IOException {
        this.hostName = hostName;
        this.portNum = portNum;
        this.sock = new Socket(hostName, portNum);
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