# 💬 Java Terminal Messenger

A real-time, bidirectional messaging application built with Java sockets that enables 2-way communication between a server and client through terminal interfaces.

## 🚀 Features

- **Real-time Messaging**: Instant message delivery between server and client
- **Bidirectional Communication**: Both server and client can send messages simultaneously
- **Multi-threaded Architecture**: Separate threads for sending and receiving messages
- **User Authentication**: Username-based identification system
- **Graceful Termination**: Clean shutdown with "quit" command
- **Terminal-based Interface**: Simple and intuitive command-line interaction

## 📁 Project Structure

```
📦 Java Terminal Messenger
├── 📄 Server.java      # Server-side application
├── 📄 Client.java      # Client-side application
└── 📄 Readme.md        # Project documentation
```

## 🔧 Requirements

- **Java**: JDK 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **Network**: Local network connectivity

## 🏃‍♂️ How to Run

### Step 1: Start the Server
```bash
java Server.java
java Client.java
```
**Output:**
```
Server is starting...
Server is running and waiting for a connection...
```

### Step 3: Start the Client (in a new terminal)
```bash
java Client
```

### Step 4: Follow the Prompts
1. Enter your username when prompted
2. Start chatting!
3. Type `quit` to disconnect

## 💻 Usage Example

### Server Terminal:
```
Server is starting...
Server is running and waiting for a connection...
Client connected: 127.0.0.1
User connected: Arpon
Server can now send messages. Type 'quit' to stop server.

Server: Welcome to the chat!
Arpon: Hello! Thanks for the welcome.
Server: How can I help you today?
Arpon: Just testing the messenger system.
Server: Great! It's working perfectly.
```

### Client Terminal:
```
Welcome! Please enter your username:
Enter your username: Arpon
Hello Arpon! You can now send messages. Type 'quit' to exit.

You can now send messages. Type 'quit' to exit.

Server: Welcome to the chat!
Arpon: Hello! Thanks for the welcome.

Server: How can I help you today?
Arpon: Just testing the messenger system.

Server: Great! It's working perfectly.
Arpon: quit
Server: Goodbye Arpon!
Client disconnected.
```

## 🏗️ Technical Implementation

### Server Architecture
- **Socket Binding**: Listens on port 2020
- **Client Acceptance**: Accepts incoming connections
- **Username Handling**: Manages user identification
- **Message Broadcasting**: Sends messages to connected clients
- **Resource Management**: Automatic cleanup with try-with-resources

### Client Architecture
- **Socket Connection**: Connects to server on localhost:2020
- **Message Listener**: Background thread for receiving server messages
- **User Input Handler**: Main thread for sending user messages
- **Real-time Display**: Immediate message rendering

### Key Classes and Methods

#### Server.java
```java
- main(String[] args): Entry point and connection handling
- ServerSocket(2020): Creates server on port 2020
- Thread serverInputThread: Handles server-side message input
```

#### Client.java
```java
- main(String[] args): Entry point and connection management
- Thread serverListener: Listens for incoming server messages
- Scanner: Handles user input from terminal
```

## 🔐 Network Configuration

- **Protocol**: TCP/IP
- **Port**: 2020
- **Host**: localhost (127.0.0.1)
- **Connection Type**: Single client per server instance

## 🛠️ Customization Options

### Change Port Number
In both `Server.java` and `Client.java`:
```java
int port = 2020; // Change to your desired port
```

### Change Server Address
In `Client.java`:
```java
String ip = "localhost"; // Change to server IP address
```

### Modify Welcome Messages
In `Server.java`:
```java
out.println("Your custom welcome message");
```

## 🐛 Troubleshooting

### Common Issues:

1. **"Connection refused"**
   - Ensure the server is running before starting the client
   - Check if port 2020 is available

2. **"Address already in use"**
   - Wait a few seconds and try again
   - Or change the port number

3. **Messages not appearing**
   - Check network connectivity
   - Ensure both applications are using the same port

4. **Program hangs**
   - Use Ctrl+C to terminate
   - Check for infinite loops in message handling


## 👨‍💻 Author

**MD Shazan Mahmud Arpon** - Java Developer

## 📝 License

This project is open source and available under the [MIT License](LICENSE).
