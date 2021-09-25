package allformsclients;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Сергей Зима
 */
public class ClientGUI extends JFrame{
   private JPanel panel;
   private JMenuBar menubar;
   private JMenu menu;
   private JMenu menu1;
   private JMenu menu2;
   private JPanel panel1;
   private CenterPanel cp;
   private LoginPanel lp;
    public ClientGUI(){
        
        panel=new PanelClient();
        menubar=new JMenuBar();
        menu=new JMenu("Contacts");
         menu.setForeground(Color.BLUE);
        menu1=new JMenu("Registration");
          menu1.setForeground(Color.BLUE);
        menu2=new JMenu("Video");
          menu2.setForeground(Color.BLUE);
        menubar.add(menu);
        menubar.add(menu2);
         menubar.add(menu1);
        menubar.setBackground(Color.YELLOW);
       
        
       // lp.setVisible(false);
     cp=new CenterPanel();
         lp=new LoginPanel(cp);
          panel. add(lp,new GridBagConstraints(2,2,1,1,0.5f,0.5f,
             GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(100,250,100,250),0,0));
          
         cp.setVisible(false);
     panel. add(cp,new GridBagConstraints(2,2,1,1,0.5f,0.5f,
             GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(70,50,70,50),0,0)); //contacts
              
     setTitle("Client for Chat"); 
     setSize(1000,700);
     setLocationRelativeTo(null);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setMaximumSize(new Dimension(1000,700));
     setMinimumSize(new Dimension(500,300));
     setJMenuBar(menubar);
     add(panel);
     setVisible(true);
     
     this.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    int i=JOptionPane.showConfirmDialog(ClientGUI.this, "Are you want exit?","Confirm Exit",JOptionPane.OK_OPTION);
                    if(i==JOptionPane.OK_OPTION){
                        System.exit(0);//
                    }else{
                        ClientGUI.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                }
            });
    }
   
}
