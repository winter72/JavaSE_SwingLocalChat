package clientconnectioninterface;

/**
 *
 * @author  Сергей Зима
 */
public interface ConnectionListener {
    
    public void connectionCreate(Connection n);
    
    public void connectionClose(Connection n);
    
    public void receiveContent(Message m);
    
 
}
