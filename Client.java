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
        try {
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            out.println("12345");
        } catch (IOException e) {
            System.err.println("Error during handshake:");
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (sock != null && !sock.isClosed()) {
                sock.close();
            }
        } catch (IOException e) {
            System.err.println("Error while disconnecting:");
            e.printStackTrace();
        }
    }

    public String request(String s) {
        String response = null;
        try {
            PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            
            writer.println(s);
            response = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error during request:");
            e.printStackTrace();
        }
        return response;
    }
}