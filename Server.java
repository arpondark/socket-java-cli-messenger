
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {
    private static String username = "";
    private static PrintWriter clientOut;
    private static boolean isConnected = false;

    public static void main(String[] args) throws Exception {
        System.out.println("Server is starting...");
        
        try (ServerSocket ss = new ServerSocket(2020)) {
            System.out.println("Server is running and waiting for a connection...");
            
            try (Socket s = ss.accept();
                 BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                 PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
                
                clientOut = out;
                isConnected = true;
                
                System.out.println("Client connected: " + s.getInetAddress().getHostAddress());
                
                // Ask for username
                out.println("Welcome! Please enter your username:");
                username = br.readLine();
                System.out.println("User connected: " + username);
                out.println("Hello " + username + "! You can now send messages. Type 'quit' to exit.");
                
                // Start server input thread
                Thread serverInputThread = new Thread(() -> {
                    try (Scanner scanner = new Scanner(System.in)) {
                        System.out.println("Server can now send messages. Type 'quit' to stop server.");
                        
                        while (isConnected) {
                            System.out.print("Server: ");
                            String serverMessage = scanner.nextLine();
                            
                            if (serverMessage.equalsIgnoreCase("quit")) {
                                if (clientOut != null) {
                                    clientOut.println("Server: Server is shutting down. Goodbye!");
                                }
                                isConnected = false;
                                break;
                            }
                            
                            if (clientOut != null && isConnected) {
                                clientOut.println("Server: " + serverMessage);
                            }
                        }
                    }
                });
                
                serverInputThread.start();
                
                // Handle client messages
                String message;
                while ((message = br.readLine()) != null && isConnected) {
                    if (message.equalsIgnoreCase("quit")) {
                        out.println("Server: Goodbye " + username + "!");
                        System.out.println("\n" + username + " has disconnected.");
                        isConnected = false;
                        break;
                    }
                    System.out.println("\n" + username + ": " + message);
                }
                
                isConnected = false;
                serverInputThread.interrupt();
            }
        }
        
        System.out.println("Server has closed the connection.");
    }
}
