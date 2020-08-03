
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReciveServer {

    public void create(int port){
        try (ServerSocket filelistener = new ServerSocket(port)) {
            System.out.println("Server running... at " + port);
            Socket filesocket = filelistener.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(filesocket.getInputStream()));
            String in;
            OutputStream outputStream = filesocket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outputStream);
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
    public static void print(String s) {
        System.out.println(s);
    }
}