package Exception;

public class SQLException extends Exception{
    public SQLException(String msg, Throwable e){
        super(msg,e);
    }
}
