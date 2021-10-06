import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Creates a TCP Server that receives an object from a client
 * @version 10-6-21
 */
public class Server {
    /**
     * The main method of the server does all the needed operations of the server.
     * @param args no commandline arguments expected
     */
    public static void main(String[] args) {
        int port = 1234; // Port number to be used when receiving and sending messages

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (!serverSocket.isClosed()) {
                Socket socket = new Socket();
                socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                // Receive Object from the client
                TestObject testObject = (TestObject) objectInputStream.readObject();

                // Using Object's method to print the clients word
                System.out.println("Clients word is: " + testObject.getWord());

                objectInputStream.close();
                inputStream.close();
                socket.close();
            }

            serverSocket.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println(classNotFoundException.getMessage());
        }
    }
}
