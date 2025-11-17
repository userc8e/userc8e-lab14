import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class Server {
    private int portNum;
    private ServerSocket serverSocket;
    private ArrayList<LocalDateTime> connectedTimes = new ArrayList<>();
    private ArrayList<Thread> clientThreads = new ArrayList<>();


    public Server(int portNum) throws IOException {
        this.portNum = portNum;
        serverSocket = new ServerSocket(portNum);
    }

    public void serve() {
       Thread t = new Thread(new Runnable() {
            public void run() {
                handleClient();
            }
        });
        clientThreads.add(t);
        t.start();
    }

    public void serve(int n) {
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    handleClient();
                }
            });
            clientThreads.add(t);
            t.start();
        }

    }

    public void disconnect() {
        try {
            for (Thread t : clientThreads) {
                t.join();
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (Exception e) {
            System.err.println("Error during disconnect:");
            e.printStackTrace();
        }
    }

    public ArrayList<LocalDateTime> getConnectedTimes() {
        return connectedTimes;
    }

    private void handleClient() {
        try {
            Socket clientSocket = serverSocket.accept();
            connectedTimes.add(LocalDateTime.now());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String handshake = in.readLine();
            if (!"12345".equals(handshake)) {
                out.println("couldn't handshake");
                in.close();
                out.close();
                clientSocket.close();
                return;
            }


            String input = in.readLine();
            if (input != null) {
                try {
                    long num = Long.parseLong(input);
                    long count = 0;

                    for (long i = 1; i * i <= num; i++) {
                        if (num % i == 0) {
                            count += (i * i == num) ? 1 : 2; // i and num/i are both factors
                        }
                    }


                    out.println("The number " + num + " has " + count + " factors");
                } catch (NumberFormatException e) {
                    out.println("Invalid number format: " + input);
                }
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error handling client:");
            e.printStackTrace();
        }
    }
}
