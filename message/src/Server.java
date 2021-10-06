import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 1234;
        boolean autoFlush = true;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (serverSocket.isClosed() != true) {
                Socket socket = new Socket();
                socket = serverSocket.accept();

                InputStreamReader inputStreamReader =
                        new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), autoFlush);

                String input = bufferedReader.readLine();
                int output = Integer.parseInt(input);

                printWriter.println(output);

                printWriter.close();
                bufferedReader.close();
                inputStreamReader.close();
                socket.close();
            }

            serverSocket.close();
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }
}