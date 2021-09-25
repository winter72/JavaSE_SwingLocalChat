package allformsclients;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Сергей Зима
 */
public class JFrameOffersContacts extends JFrame{
    
    private JPanel panel;
    private JList jlist;
    private   DefaultListModel l1;
    private JButton bnt;
       // private   DefaultListModel <String> l1;
    private boolean checkListSize;
    private int adCon=0;
    public JFrameOffersContacts(){
        super("Offers about contacts");
        panel=new PanelMessage();
             setSize(500,400);
             l1=new DefaultListModel<>();
              jlist=new JList<PanelForJlist>();
              jlist.setModel(l1);
              jlist.setLayoutOrientation(JList.VERTICAL);
              // jlist.setForeground(Color.BLUE);
           // jlist.setFont(new Font("Verdana",Font.ITALIC,15));
         // jlist.setBounds ( 17 , 17 ,  50 , 55 ); 
          jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          jlist.setSelectedIndex(0);
          jlist.setCellRenderer(new Renderer());
           
           // jpanel.setPreferredSize(new Dimension(300,250));
     setLocationRelativeTo(null);
     setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    // panel.add(jpanel,BorderLayout.CENTER);
      panel. add(new JScrollPane(jlist),new GridBagConstraints(0,0,1,1,0.5f,0.5f,
             GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(20,20,10,20),0,0));
   
     add(panel);
     //setVisible(true);
    }

    public void setBnt(JButton bnt) {
        this.bnt = bnt;
           panel. add(this.bnt,new GridBagConstraints(0,1,0,0,0.5f,0.1f,
             GridBagConstraints.SOUTH,GridBagConstraints.BOTH,new Insets(0,20,20,20),0,0));
    }
    
   public boolean isCheckListSize() {
        return checkListSize;
    }

    public void setCheckListSize(boolean checkListSize) {
        this.checkListSize = checkListSize;
    }

    public synchronized void addItemsJLists(String n,String m){
       PanelForJlist panelj=new PanelForJlist(n,m);
        l1.addElement(panelj);
    }
    
    

}
