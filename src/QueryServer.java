
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class QueryServer {
    //static int newPort=50504;



    public boolean genSRV(int port, int nextPort){
        try (ServerSocket listener = new ServerSocket(port)) {
            System.out.println("Querry Server waiting at: " + port);
            try(Socket socket = listener.accept()){
                PrintWriter transferPort = new PrintWriter(socket.getOutputStream(),true);
                transferPort.println(nextPort);
                print("Send "+nextPort);
                listener.close();
                return true;
            }
        }catch(Exception e){
            print(e.getMessage().toString());
        }
        return false;
    }
    public static void print(String s) {
        System.out.println(s);
    }
}