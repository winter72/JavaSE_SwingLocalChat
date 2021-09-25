
package clientlogic;

import clientconnectioninterface.Connection;
import clientconnectioninterface.ConnectionListener;
import clientconnectioninterface.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ClientConnect implements Connection,Runnable{
    
        private Socket client;
    private ConnectionListener connectionListener;
    private boolean checkthread;
     private OutputStream outs;
     private boolean client_access;     
    
    public ClientConnect(Socket client,ConnectionListener l){
        this.client=client;
        connectionListener=l;
        checkthread=true;
        
           try {
                outs=client.getOutputStream();
            } catch (IOException ex) {
                Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
           MainMessage();
       Thread clints=new Thread(this);
        clints.setPriority(Thread.NORM_PRIORITY);
        clints.start();
    }
    @Override
    public void sendMessage(Message m) { 
         ObjectOutputStream  obj=null;
          
        try {
               String s =m.toString();
          obj=new ObjectOutputStream(outs);   
           obj.writeUTF(s);
           obj.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, null, ex);
        }/*finally{
            if(obj!=null){
                try {
                    obj.close();
                } catch (IOException ex) {
                    Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }*/
        
    }

    @Override
    public void closed() {
      checkthread=false;  
    }  
    
      private ClientMessage Parse_String(String s){
           int n=s.length()-1;//s.indexOf(" Message : ");
        int n1=s.indexOf(";");
        int cons=s.indexOf("content;");
        int n2=s.indexOf("whom-");
        int m=s.indexOf(":");
         int w=s.indexOf("-");
         int w1=s.indexOf("*");
         
         String wh=s.substring(w+1,w1);
        String nk=s.substring(m+1,cons);
        String ct=s.substring(n1+1, n2);
        int num=Integer.parseInt(s.substring(n));
        return new ClientMessage(nk,ct,wh,num);
    }
      @Override
    public  void run(){
            while(checkthread){
                
                 try {
                    Thread.sleep(21);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                 MainMessage();
             /*   try {
                    InputStream in=client.getInputStream();
                    int amount1=in.available();
                    ObjectInputStream objin=null;
                    if(amount1!=0){
                         objin=new ObjectInputStream(in);
                         String s = objin.readUTF();                   
                            Message msg=Parse_String(s);
                            connectionListener.receiveContent(msg);
                           
                         
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }*/
                
               
            }
        }
       private synchronized void MainMessage(){
              try {
                    InputStream in=client.getInputStream();
                    int amount1=in.available();
                    ObjectInputStream objin=null;
                    if(amount1!=0){
                         objin=new ObjectInputStream(in);
                         String s = objin.readUTF();                   
                            Message msg=Parse_String(s);                       
                        connectionListener.receiveContent(msg);            
                                                                          
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                   // Logger.getLogger(ClientConnect.class.getName()).log(Level.SEVERE, null, ex);
                }
       }
    }