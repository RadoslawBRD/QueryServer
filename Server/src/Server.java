import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    static int port = 50505;
    static int newPort=50506;
    static List<ReciveServer> activeConnections = new ArrayList<ReciveServer>();
    static List<Integer> portsList=new ArrayList<Integer>();

    public static void main(final String[] args) {

        try {
            writeThread();
            QueryServer queryServer = new QueryServer();
            PortHolder portHolder = new PortHolder(newPort);

            while(true)
                if(queryServer.genSRV(port, portHolder.get_next_port()))
                    queryThread(portHolder.get_cur_port());

        }catch(Exception e){
            print(e.getMessage().toString());
        }

    }
    public static void writeThread(){
        Thread thread = new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            String ss;
            while(true) {
                print("Ready for writing");
                ss = scanner.nextLine();
                for(ReciveServer test : activeConnections)
                    if(ss.matches("([0-9]){5}[ ].+")) {
                        if (test.get_port() == Integer.parseInt(ss.substring(0, 5)))
                            test.sendToClient(ss.substring(6));
                    }
                    else
                        print("Wrong message format, try: <port msg>");
            }
        });
        thread.start();
    }


    public static boolean queryThread(int ThreadPort){
        Thread thread = new Thread(()->{

            ReciveServer rc = new ReciveServer();
            activeConnections.add(rc);
            portsList.add(rc.get_port());
            rc.create(ThreadPort);
            //activeConnections.remove(rc);
            //portsList.remove(rc.get_port());
            //print(activeConnections.size()+" "+portsList.size());

        });

        if(thread.isAlive())
            return false;
        else{
            thread.start();
            return true;
        }
    }

    public static void print(String s) {
        System.out.println(s);
    }

}


