
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReciveServer {
    int serverPort;
    Socket filesocket;
    BufferedReader br;
    OutputStream outputStream;
    DataOutputStream dos;
    String in;

    public void create(int port){
        serverPort=port;
        try (ServerSocket filelistener = new ServerSocket(port)) {
            System.out.println("Server running... at " + port);
            filesocket = filelistener.accept();

            //input stream from client
            br = new BufferedReader(new InputStreamReader(filesocket.getInputStream()));

            //for string like messages to client
            outputStream = filesocket.getOutputStream();
            dos = new DataOutputStream(outputStream);
            dos.writeBytes("You are connected"+"\n");
            while (true) {
                while((in = br.readLine())!=null){
                    print(port+": "+in);
                }
                //return true;
            }


        } catch (Exception e) {
            System.out.println(port+": "+e.getMessage().toString());
        }
        //return false;


    }
    public void sendToClient(String msg){
        try {
            dos.writeBytes(msg+"\n");
            print("MSG TO:"+serverPort+": "+msg);
        }catch (Exception e){
            print(e.getMessage());
        }
    }
    public static void print(String s) {
        System.out.println(s);
    }
    public int get_port(){
        return serverPort;
    }
}