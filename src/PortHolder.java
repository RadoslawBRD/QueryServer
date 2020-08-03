
public class PortHolder{
    int port;
    public PortHolder(int port){
        this.port=port;

    }

    public int get_next_port(){
        port=port+1;
        return port;
    }
    public int get_cur_port(){
        return port;
    }



}