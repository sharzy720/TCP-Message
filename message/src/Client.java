import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Creates a simple Client to talk with a server
 * @version 10-6-21
 */
public class Client {
    /**
     * Creates the client and connects to a given host address on the specified port
     * @param args (host address) (port number)
     */
    public static void main(String[] args) {
        boolean autoFlush = true;
        try {
            InetAddress host = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);

            System.out.println("Attempting to connect to address: " + host.getHostAddress() + " " +
                    "on port: " + port);

            Socket socket = new Socket(host.getHostAddress(), port);

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), autoFlush);

            Scanner input = new Scanner(System.in);

            //Gets a user inputted word to be sent to a server
            System.out.println("Enter a word: ");
            String userWord = input.next();

            // Word is sent to the server
            printWriter.println(userWord);

            // Word is received from the server
            String ServerWord = bufferedReader.readLine();

            System.out.println("Word from server is: " + ServerWord);

            input.close();
            printWriter.close();
            bufferedReader.close();
            inputStreamReader.close();
            socket.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}