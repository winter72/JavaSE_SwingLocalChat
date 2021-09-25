package clientconnectioninterface;

/**
 *
 * @author Сергей Зима
 */
public interface Connection{
    
    public void sendMessage(Message m);
    
    public void closed();
    
}
