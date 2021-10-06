import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Creates a Client that sends an object to a Server
 * @version 10-6-2021
 */
public class Client {
    /**
     * Creates a new testObject to be sent to the server
     * @return new testObject
     */
    public TestObject createObject() {
        String word;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word to send to the server: ");
        word = scanner.nextLine();

        TestObject testObject = new TestObject(word);
        scanner.close();
        return testObject;
    }

    /**
     * Creates a client to connect to a given host address on a specified port
     * @param args (host address) (port number)
     */
    public static void main(String[] args) {
        Client client = new Client();
        int port = Integer.parseInt(args[1]);

        try {
            InetAddress host = InetAddress.getByName(args[0]);
            Socket socket = new Socket(host.getHostAddress(), port);

            // Creates an object to be sent to the Server
            TestObject testObject = client.createObject();

            OutputStream outputStream = socket.getOutputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // Sends the object to the Server
            objectOutputStream.writeObject(testObject);

            objectOutputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
