import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static boolean isConnected = false;
    
    public static void main(String[] args) throws Exception {
        String ip = "localhost";
        int port = 2020;
        
        try (Socket s = new Socket(ip, port);
             PrintWriter out = new PrintWriter(s.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            
            isConnected = true;
            
            // Read server's welcome message
            String serverMessage = in.readLine();
            System.out.println(serverMessage);
            
            // Send username
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            out.println(username);
            
            // Read server's greeting
            serverMessage = in.readLine();
            System.out.println(serverMessage);
            
            System.out.println("\nYou can now send messages. Type 'quit' to exit.");
            
            // Thread to listen for server messages
            Thread serverListener = new Thread(() -> {
                try {
                    String message;
                    while (isConnected && (message = in.readLine()) != null) {
                        System.out.println("\n" + message);
                        if (isConnected) {
                            System.out.print(username + ": ");
                        }
                    }
                } catch (Exception e) {
                    if (isConnected) {
                        System.out.println("\nConnection lost.");
                    }
                }
            });
            
            serverListener.start();
            
            // Handle user input
            String userInput;
            while (isConnected) {
                System.out.print(username + ": ");
                userInput = scanner.nextLine();
                
                if (userInput.equalsIgnoreCase("quit")) {
                    out.println(userInput);
                    isConnected = false;
                    break;
                }
                
                out.println(userInput);
            }
            
            serverListener.interrupt();
        }
        
        System.out.println("Client disconnected.");
    }
}
