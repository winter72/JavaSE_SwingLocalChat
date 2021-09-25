package clientconnectioninterface;

import java.io.Serializable;

/**
 *
 * @author Сергей Зима
 */
public interface Message extends Serializable{
    
    public int CLOSE_TYPE=0;
    public int CONTENT_TYPE=1;
    public int CONTENT_LOGIN=2;
    public int CONTENT_REGISTRY=3;
    public int CONTENT_INCORRECT=4;
    public int CONTENT_CONTACTS=5;
    public int CONTENT_NOTFOUND=6;
    public int CONTENT_ADDCONTACT=7;
    public int CONTENT_NOTREAD=8;
    public int CONTENT_AGREE=9;
    public String getNick();
    
    public String getContent();
    
    public int getTypes();
    
    public String getWhom();
}
