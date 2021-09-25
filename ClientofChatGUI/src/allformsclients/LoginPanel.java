package allformsclients;

import clientconnectioninterface.Connection;
import clientconnectioninterface.ConnectionListener;
import clientconnectioninterface.Message;
import clientlogic.ClientConnect;
import clientlogic.ClientMessage;
import clientlogic.UsersCollections;
import clientscontacts.ContactClient;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Cursor.HAND_CURSOR;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author Сергей Зима
 */
public class LoginPanel extends JPanel implements ConnectionListener{
    
    private JLabel login;
     private JLabel pass;
     private JLabel infMess;
     private JLabel changeForm;
     private JTextField fieldLog;
     private JPasswordField fieldPass;
     private JButton btnLog;
     private JButton btnClose;
     private JButton btnSend;
     private JButton btnAgree;
        private JButton btnSearch;
        private JButton btnList;
     private ClientConnect clientLoader;
     private  Connection cL;
     private Socket socket;
     private boolean acc;
     private CenterPanel cpp;
     private String nicknames;
     private boolean step;
     private boolean steps=false;
     
    public LoginPanel(CenterPanel cp){
        super();
          mainConnect(cp);
          // mainClose();
          // sendButtons();
    }
           @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.LIGHT_GRAY;
        Color color2 = Color.WHITE;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
    private void clickChangeForm(){
     changeForm.addMouseListener(new MouseListener(){
         @Override
         public void mouseClicked(MouseEvent e) {
          setTextReg();
         }

         @Override
         public void mousePressed(MouseEvent e) {

         }

         @Override
         public void mouseReleased(MouseEvent e) {

         }

         @Override
         public void mouseEntered(MouseEvent e) {
         
         }

         @Override
         public void mouseExited(MouseEvent e) {
   
         }
     });
    }
    
    private void setTextReg(){
        if(btnLog.getText().equalsIgnoreCase("login")){
              btnLog.setText("Registration");
            login.setText("Nickname");
             changeForm.setText("Login");
              infMess.setVisible(false);
        }else{
              btnLog.setText("Login");
            login.setText("Login");
            changeForm.setText("Registration");
             infMess.setVisible(false);
        }
    }

    @Override
    public void connectionCreate(Connection n) {
      System.out.println("Client connected");
      this.cL=n;
    }

    @Override
    public void connectionClose(Connection n) {
       n.closed();
    }

    @Override
    public synchronized void receiveContent(Message m) {
        infMess.setText("");
        switch(m.getTypes()){
            case Message.CONTENT_INCORRECT:
                infMess.setMinimumSize(new Dimension(500,20));
                infMess.setText(m.getContent());
                infMess.setForeground(Color.RED);
                infMess.setVisible(true);
                connectionClose(cL);
                break;
            case Message.CONTENT_LOGIN :
                setRegistryOrLogin(m);
                cpp.notReadClick(btnAgree);
                break;
            case Message.CONTENT_REGISTRY:
                setRegistryOrLogin(m);
                break;
            case Message.CONTENT_TYPE:
                   cpp.receiveContents(m);
                break;
            case Message.CONTENT_CONTACTS:
                    ArrayList<String>users =new ArrayList<>(Arrays.asList(m.getContent().split(","))); 
                    if(users.size()>0)
                   cpp.addListContact(users);
                break;
            case Message.CONTENT_NOTFOUND:
                  cpp.addListNotContact(m.getContent());
                break;
            case Message.CONTENT_ADDCONTACT:
                cpp.setVisNotif(true);
                break;
            case Message.CONTENT_NOTREAD:
                   if(m.getContent().equalsIgnoreCase("Please will add me in your contacts")){
                       cpp.setVisNotif(true);
                       UsersCollections.addUsContact(m.getNick(),m.getContent());
                   cpp.setVisTextBtn(String.valueOf(UsersCollections.getUsCon().size())); 
                   }else{
                   UsersCollections.addValuesInMap(m.getNick(),m.getContent());
                   }
                    
                break;
            default:
                break;
        }
    }
    
    private void setRegistryOrLogin(Message m){
             cpp.changePar(btnSearch);
            cpp.setBtn(btnSend,btnClose,btnList,step);
           step=false;
        cpp.setVisible(true);
          cpp.receiveContents(m);
        this.setVisible(false);
    }
    private void mainConnect(CenterPanel cp){
        try {
            step=true;
            cpp=cp;
            setLayout(new GridBagLayout());
            JPanel pan=new JPanel();
            pan.setLayout(new GridBagLayout());
            Font font=new Font("SERIF",Font.BOLD,17);
            Font font1=new Font("SERIF",Font.BOLD,15);
            login=new JLabel("Login:");
            pass=new JLabel("Password:");
            login.setFont(font);
            pass.setFont(font);
            infMess=new JLabel("Info");
            changeForm=new JLabel("Registration");
            changeForm.setFont(font1);
            changeForm.setForeground(Color.BLUE);
            Cursor cur=new Cursor(HAND_CURSOR);
            changeForm.setCursor(cur);
            pan.add(changeForm);
            fieldLog=new JTextField();
            fieldLog.setFont(font);
            fieldPass=new JPasswordField(20);
            fieldPass.setFont(font);
            btnSend=new UniversalButton("Send",this);
            btnLog=new UniversalButton("Login",this);
            btnSearch=new UniversalButton("Search",this);
            btnList=new UniversalButton("Delete",this);
            btnAgree=new UniversalButton("Agree",this);
            Image imageFile=ImageIO.read(new File("src/image/search-button.jpg"));
            Image imageFL=imageFile.getScaledInstance(33, 34,Image.SCALE_SMOOTH);
            ImageIcon icon1 =new ImageIcon();
             icon1.setImage(imageFL);
            btnSearch.setIcon(icon1);
            btnLog.setCursor(cur);
            btnLog.setFont(font);
            btnLog.setForeground(Color.GREEN);
            btnClose=new UniversalButton("Close",this);
            btnClose.setCursor(cur);
            btnClose.setBackground(Color.GREEN);
            btnClose.setForeground(Color.BLUE);
            btnAgree.setCursor(cur);
           btnAgree.setBackground(Color.CYAN);
            btnAgree.setForeground(Color.MAGENTA);
            GridBagConstraints con=new GridBagConstraints();
            con.gridwidth=2;
            con.gridheight=3;
            //setSizeForLabel();
            clickChangeForm();
            add(login,new GridBagConstraints(1,1,3,1,0.1,0.1,GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL,new Insets(20,50,20,50),2,2));
            add(fieldLog,new GridBagConstraints(2,1,2,1,0.1,0.1,GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL,new Insets(20,150,20,50),2,2));
            
            add(pass,new GridBagConstraints(1,2,3,1,0.1,0.1,GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL,new Insets(0,50,20,350),2,2));
            add(fieldPass,new GridBagConstraints(2,2,2,1,0.1,0.1,GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL,new Insets(0,150,20,50),2,2));
            
            add(btnLog,new GridBagConstraints(3,4,3,1,0.1,0.1,GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL,new Insets(0,180,20,180),2,2));
            add(infMess,new GridBagConstraints(3,6,3,1,0.1,0.1,GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL,new Insets(0,150,20,150),3,3));
            infMess.setVisible(false);
            add(pan,new GridBagConstraints(3,8,3,1,0.1,0.1,GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL,new Insets(0,200,20,200),3,3));
        } catch (IOException ex) {
            Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   /*  private void mainClose(){
          btnClose.addActionListener((ActionEvent e)-> {
                  if(cL!=null){
                    Message msg=new ClientMessage(nicknames,"Close","Server",Message.CLOSE_TYPE);
                     cL.sendMessage(msg);
                  }
                cL.closed();
               mainReload();      
          });
     }*/
     
    private void setSizeForLabel(){
        Dimension dim=getPreferredSize();
         dim.width=400;
         dim.height=20;
         infMess.setPreferredSize(dim);
    }
    
    public ClientConnect returnClient(){
        return clientLoader;
    }
    
    private void mainReload(){
          cpp.setVisible(false);
          cpp.setTextArea();
          fieldLog.setText("");
          fieldPass.setText("");
          this.setVisible(true);
    }
    
   /* private void sendButtons(){
         btnSend.addActionListener((ActionEvent e)-> { 
              if(cL!=null){
                   String mm=cpp.textfield.getText();
                   Message msg=new ClientMessage(nicknames,mm,Message.CONTENT_TYPE);
                   cL.sendMessage(msg);
              }
         });
    }*/
       private class UniversalButton extends JButton{
           public UniversalButton(String s,ConnectionListener c){
               super(s);
               addActionListener((ActionEvent e)-> {
                 Message msg=null;
                   char[]p=fieldPass.getPassword();
                          String pss= String.valueOf(p);
                  // if (!this.getText().equalsIgnoreCase("Send")) {
                      try {
                          Socket socket=new Socket("127.0.0.1",11000);
                          clientLoader= new ClientConnect(socket,c);
                          cL=clientLoader;
                        
                          System.out.println(pss);
                            System.out.println(fieldLog.getText());
                      
                          //cL.sendMessage(msg);
                        
                      }catch (IOException ex) {
                            setInfMessage("Server is not available");
                         // Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
                      }  
                   
                      switch(this.getText()){
                          case "Close":
                              msg=new ClientMessage(nicknames,"Close","Server",Message.CLOSE_TYPE);
                                cL.closed();
                                cpp.setDefList();
                                mainReload(); 
                              break;
                          case "Login":
                              if(p.length>0&&fieldLog.getText().length()>0){
                             nicknames=fieldLog.getText();
                              msg=new ClientMessage(fieldLog.getText(),pss,"Server",Message.CONTENT_LOGIN);
                             }else{
                                  setInfMessage("Fields are not empty");
                              }
                              break;
                          case "Registration":
                              if(fieldLog.getText().length()>0&&p.length>0){
                                   nicknames=fieldLog.getText();
                                   msg=new ClientMessage(fieldLog.getText(),pss,"Server",Message.CONTENT_REGISTRY); 
                              }else{
                                   setInfMessage("Fields are not empty");
                              }
                              break;
                          case "Send":
                               String mm=cpp.textfield.getText();
                                String mp=cpp.getListVal();
                               if(mm.length()>0&&mp.length()>0){
                            msg=new ClientMessage(nicknames,mm,mp,Message.CONTENT_TYPE);
                            cpp.receiveContents(msg);
                               }
                              break;
                          case "Search":
                          String pm=cpp.getFieldContact();
                          if(pm.length()>0){
                              cpp.setDefList();
                              btnList.setText("Add");
                            msg=new ClientMessage(nicknames,pm,"Server",Message.CONTENT_CONTACTS); 
                          }
                              break;
                          case "Add":
                              String mp1=cpp.getListVal();
                            //  ContactClient.writeNewContact("First-Add;");
                              if(mp1.length()>0){
                                  mp1=mp1+";";
                                 ContactClient.writeNewContact(mp1,"new_contacts.txt");
                                 mp1=mp1.replace(";","");
                              msg=new ClientMessage(nicknames,"Please will add me in your contacts",mp1,Message.CONTENT_ADDCONTACT); 
                              }
                              break;
                          case "Agree":
                              System.out.println("Agree");
                              break;
                          default:
                              break;
                      }
              
                      if(cL!=null&&msg!=null){
                         connectionCreate(cL);
                       cL.sendMessage(msg);
                      }
          });
              
           }
              private void setInfMessage(String inf){
                    infMess.setText(inf);
                       infMess.setForeground(Color.RED);
                       infMess.setVisible(true);
               }
        }
}
