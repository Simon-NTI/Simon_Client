import java.io.*;
import java.net.*;
public class Simon_Client {
    public static void main(String[] args) {
        Socket client = null;

        // Default portnumber
        int portnumber = 8080;

        for (int i = 0; i < 10; i++) {
            try {
                String message = "";

                // Create a client socket
                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket is created " + client);

                // Create an output stream, of the client socket
                OutputStream clientOut = client.getOutputStream();
                PrintWriter printWriter = new PrintWriter(clientOut, true);

                // Create an input stream of the client socket
                InputStream clientIn = client.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientIn));

                // Create bufferedReader for standard input
                BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Enter an expression. Type Bye to exit");

                // Read data from the standard input devices and write it
                // to the output stream of the client socket
                message = standardInput.readLine().trim();
                printWriter.println(message);

                // Stop the operation
                if (message.equalsIgnoreCase("Bye"))
                {
                    printWriter.close();
                    bufferedReader.close();
                    client.close();
                    break;
                }

                // Response from server
                System.out.println(bufferedReader.readLine());

                printWriter.close();
                bufferedReader.close();
                client.close();

            } catch (IOException e) {
                System.out.println("I/O error " + e);
            }
        }
    }
}