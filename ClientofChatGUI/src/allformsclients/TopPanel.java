package allformsclients;

import clientlogic.UsersCollections;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Сергей Зима
 */
public class TopPanel extends JPanel{
    
    private JLabel allmessages;
    private JTextField  requestContact;
    private JButton btnFind;
    private JButton btnNot;
   // private JFrameOffersContacts ocf;
    int counter;
    public TopPanel(String t){
        super();
        counter=0;
        allmessages=new JLabel(t);
        setLayout(new GridBagLayout());
        Font font=new Font("Verdana",Font.ITALIC,31);
        allmessages.setFont(font);
        allmessages.setForeground(Color.BLUE);
        add(allmessages);
    }
    
    public void setTextFieldTexts(JButton btn){
      
        btnFind=btn;
         requestContact=new JTextField();
        requestContact.setPreferredSize(new Dimension(170,31));
        requestContact.setFont(new Font(Font.SERIF, Font.BOLD,17));
        Font font=new Font("Verdana",Font.ITALIC,0);
        Font fonts=new Font(Font.SERIF, Font.PLAIN,13);//("SERIF",Font.BOLD,15);
        btnFind.setPreferredSize(new Dimension(30,30));
        btnNot=new NotificationLabel("");
        btnNot.setPreferredSize(new Dimension(47,31));
        btnNot.setFont(fonts);
        btnNot.setForeground(Color.CYAN);
        btnNot.setVisible(false);
        btn.setFont(font);
        JPanel pnl=new JPanel();
        pnl.setLayout(new FlowLayout (FlowLayout.TRAILING));
        if(counter<1){
        pnl.add(requestContact);  
         pnl.add(btnFind);
         pnl.add(btnNot);
         add(pnl);
       //  NotifClick();
           ++counter;
        }
    }
    
    public String getCont(){
        return requestContact.getText();
    }
    
    public void setVisNot(boolean n){
        btnNot.setVisible(n);
    }
    
    public void setVisBtnText(String t){
        btnNot.setText(t);
    }
    
     public void NotifClick(JButton btn){
            JFrameOffersContacts ocf=new JFrameOffersContacts();
            ocf.setBnt(btn);
      btnNot.addActionListener((ActionEvent e) -> {              
      for(Map.Entry<String, String> entry:UsersCollections.getUsCon().entrySet()){
          ocf.addItemsJLists(entry.getKey(),entry.getValue());
      }
         // btnNot.setVisible(false);
         ocf.setVisible(true);
      });
  }
}
