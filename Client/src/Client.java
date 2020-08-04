
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.Socket;
import java.util.Scanner;


public class Client {
    static int port = 50505;
    //static String ip = "185.193.115.34";
    static String ip= "0.0.0.0";
    static BufferedReader br;
    public static void main(String[] args){

        if(args.length>0){
            for(int i=0;i<=Integer.valueOf(args[0]);i++){
                clientThread();
            }
        }
        else
            clientThread();
    }

    public static void clientThread(){
        Thread thread = new Thread(()->{
            start_client();
        });
        thread.start();
    }

    public static void start_client(){
        //wait for port from query server
        try(Socket filesocket = new Socket(ip,port)){

            Scanner tranferPort = new Scanner(filesocket.getInputStream());

            String newPort= tranferPort.nextLine();
            tranferPort.close();
            connection(newPort);

        }catch(Exception e){
            print("Another try");
            clientThread();
            print(e.getMessage().toString());
        }
    }
    //connecting to the server at given port
    public static void connection(String newPort){
        String input="";
        Scanner scanner = new Scanner(System.in);

        try(Socket socket = new Socket(ip,Integer.valueOf(newPort))){
            OutputStream outputStream = socket.getOutputStream();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //InputStream inputStream = socket.getInputStream();
            DataOutputStream dos = new DataOutputStream(outputStream);
            System.out.println("Trying to connect at "+ip+":"+newPort);


            if(socket.isConnected()){
                System.out.println("Connected at: "+newPort);
                readThread();
            }
            while(!input.equals("Stop")){
                input=scanner.nextLine();
                if(!input.equals("Stop")){

                    dos.writeBytes(input+"\n");
                }
            }

        }catch (Exception e){

            scanner.close();
            System.out.println(e.getMessage().toString());
        }
    }
    public static void writeThread(int ThreadPort){
        Thread thread = new Thread(()->{

        });
        thread.start();
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void readThread(){
        Thread thread = new Thread(()->{
            String in;

            try{
                while(true){
                    while((in = br.readLine())!=null){
                        print(in);
                    }
                }
            }catch(Exception e){
                print(e.getMessage());
            }
            clientThread();
        });
        thread.start();
    }


}