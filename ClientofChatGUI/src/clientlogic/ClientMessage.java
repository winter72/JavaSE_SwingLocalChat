package clientlogic;

import clientconnectioninterface.Message;

/**
 *
 * @author User
 */
public class ClientMessage implements Message{
     private final String nick;
    private final String content;
    private final int typess;
    private final String whom;
    public ClientMessage(String nick,String content,String whom,int typess){
        this.nick=nick;
        this.content=content;
        this.typess=typess;
        this.whom=whom;
    }

    @Override
    public String getNick() {
     return nick;
    }

    @Override
    public String getContent() {
       return content;
    }

    @Override
    public int getTypes() {
       return typess;
    }
    
     @Override
    public String getWhom(){
        return whom;
    }
    
    @Override
    public String toString(){
        return " Message : "+nick+"content;"+content+"whom-"+whom+"*"+"type:"+typess;
    }
    
}
