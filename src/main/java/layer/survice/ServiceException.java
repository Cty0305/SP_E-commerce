package layer.survice;

public class ServiceException extends Exception{

    public ServiceException(String msg){
        super(msg);
    }

    public ServiceException(String msg, Throwable e){
        super(msg,e);
    }
}
