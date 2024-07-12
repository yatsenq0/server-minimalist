import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // создаем серверный сокет на порту 1234
        ServerSocket server = new ServerSocket(1234);
        List<Socket> clients = new ArrayList<>();
        while(true) {
            System.out.println("Waiting...");

            // ждем клиента
            Socket socket = server.accept();
            clients.add(socket);

            System.out.println("Client connected!");

            new Thread(new Client(socket, clients)).start();
        }
    }
}