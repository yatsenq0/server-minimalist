import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

class Client implements Runnable {
    Socket socket;
    List<Socket> clients;

    public Client(Socket socket, List<Socket> clients){

        this.socket = socket;
        this.clients = clients;
    }

    public void run() {
        try {
            // получаем потоки ввода и вывода
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // создаем удобные средства ввода и вывода
            Scanner in = new Scanner(is);
            PrintStream out = new PrintStream(os);

            // читаем из сети и пишем в сеть
            out.println("Welcome to mountains!");

            while (true) {
                String input = in.nextLine();
                if (input.equals("bye")) {
                    clients.remove(socket);
                    socket.close();
                    break;
                }

                for (Socket client : clients) {
                    PrintStream clientOut = new PrintStream(client.getOutputStream());
                    clientOut.println(input);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}