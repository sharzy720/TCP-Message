import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Creates a simple TCP server that receives a message from a client and sends it back
 * @version 10-6-21
 */
public class Server {
    /**
     * The main method of the server does all the needed operations of the server.
     * @param args No commandline arguments are expected
     */
    public static void main(String[] args) {
        int port = 1234; // Port number to be used when receiving and sending messages
        boolean autoFlush = true; // To turn autoFlush on or off

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            /* Keeps the server open till manually closed*/
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), autoFlush);

                /* Reads the string that is sent by the client.
                * bufferedReader returns a String but the value can be parsed to any data type
                * needed*/
                String input = bufferedReader.readLine();

                /* Sending the string back to the Client*/
                printWriter.println(input);

                printWriter.close();
                bufferedReader.close();
                inputStreamReader.close();
                socket.close();
            }

            serverSocket.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}