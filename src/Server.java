

public class Server {
    static int port = 50505;
    static int newPort=50506;

    public static void main(final String[] args) {

        try {
            QueryServer queryServer = new QueryServer();
            PortHolder nextPort = new PortHolder(newPort);
            while(true){
                if(queryServer.genSRV(port, nextPort.get_next_port())){
                    queryThread(nextPort.get_cur_port());
                    queryServer = new QueryServer();
                }
            }
        }catch(Exception e){
            print(e.getMessage().toString());
        }
    }

    public static boolean queryThread(int ThreadPort){
        Thread thread = new Thread(()->{
            ReciveServer rc = new ReciveServer();
            rc.create(ThreadPort);
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


