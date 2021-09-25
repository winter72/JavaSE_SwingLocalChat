package allformsclients;

import clientconnectioninterface.Connection;
import clientconnectioninterface.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Сергей Зима
 */
public class CenterPanel extends JPanel{
    
       private JTextArea textarea;
    public JTextArea textfield;
    private JButton btn;
      private JButton btn1;
       private JButton nic;
       private JButton btnAdd;
    private JPanel panel;
      private JPanel panel1;
      private  Connection cL;
       private JPanel paneln1;
       private JPanel allcontacts;
       private JPanel allmessages;
       private JList jlist;
       private TopPanel topPanel;
       private   DefaultListModel <String> l1;
      
    public CenterPanel(){
         super();
        setLayout(new GridLayout(1,2,3,3));
        l1= new  DefaultListModel <> ();
        jlist=new JList<>(l1);
          jlist.setBounds ( 70 , 50 ,  55 , 55 ); 
          jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          
        jlist.setSelectedIndex(0);
       // jlist.addListSelectionListener(sLL);

        allmessages=new JPanel();
        allcontacts=new JPanel();
        topPanel=new TopPanel("Contacts");
        
        panel=new JPanel();
          panel1=new JPanel();
            setTextArea();
        textfield=new JTextArea();
        textfield.setBackground(Color.YELLOW);
        textfield.setForeground(Color.BLUE);
       // btn=new JButton("Send");
        nic=new JButton("Nick");
        panel.setLayout(new GridLayout(1,2));
           panel1.setLayout(new GridLayout(1,2)); 
         panel.add(new JScrollPane(textfield));
        panel.add(panel1);
       
       allmessages. setLayout(new BorderLayout());
         allmessages.add(new JScrollPane(textarea),BorderLayout.CENTER);
          allmessages.add(panel,BorderLayout.SOUTH);
          allmessages.add(new TopPanel("Messages"),BorderLayout.NORTH);
          allcontacts.setLayout(new BorderLayout());
          allcontacts.add(new JScrollPane(jlist),BorderLayout.CENTER);
          allcontacts.add(topPanel,BorderLayout.NORTH);
          
      
          
          add(allcontacts);
          add( allmessages);
           jlist.setForeground(Color.BLUE);
            jlist.setFont(new Font("Verdana",Font.ITALIC,15));
              
         /*  Dimension dim=getPreferredSize();
             System.out.println(dim);
        dim.width=900;
        dim.height=470;
        setPreferredSize(dim);*/
    }
public void setBtn(JButton ntb,JButton btnc,JButton btnA,boolean n){
    
    btn=ntb;
    btn1=btnc;
    btnAdd=btnA;
   // btn.setText("Send");
      btn.setBackground(Color.BLUE);
        btn.setForeground(Color.CYAN);
        if(n){
          panel1.add(btn);
            panel1.add(btn1);
                allcontacts.add(btnAdd,BorderLayout.SOUTH);
        }
        
}

    public void setTextArea() {
           textarea=new JTextArea();
              textarea.setForeground(Color.BLACK);
              textarea.setEnabled(false);
           textarea.setDisabledTextColor(Color.BLACK);
    }

    public void receiveContents(Message m) {
       textarea.setEnabled(true);
        textarea.append("\n"+"  "+  m.getNick()+"\n");
        textarea.append(m.getContent());
         textarea.setEnabled(false);
         textfield.setText("");
         //textarea.setText("");
    }
   
    public void changePar(JButton btn){
      topPanel.setTextFieldTexts(btn);
    }
    
    public String getFieldContact(){
        return topPanel.getCont();
    }
    
    public synchronized void addListContact(ArrayList<String> arrs){
        int count=0;
             jlist.setForeground(Color.BLUE);
            jlist.setFont(new Font("Verdana",Font.ITALIC,15));
            for(String j:arrs){
              //  jlist.add(j, new JButton("Add"));
            l1.add(count,j);//.addElement(count,j);
            count++;
          } 
       
    }
     public void addListNotContact(String s){
         // Thread t=new Thread(() -> {
          //  setDefList();
      /*  });
        t.start();
           try {
               t.join();
           } catch (InterruptedException ex) {
               Logger.getLogger(CenterPanel.class.getName()).log(Level.SEVERE, null, ex);
           } */ 
          l1.addElement(s);
            jlist.setForeground(Color.RED);
            jlist.setFont(new Font("Verdana",Font.ITALIC,25));
     }
    public void setDefList(){
       /* for(int i=0;i<l1.getSize();i++)
           l1.remove(i);*/
      // jlist.setListData(new String[0]);
      l1.removeAllElements();
    }
    
    public String getListVal(){
        if(!jlist.isSelectionEmpty()){
          String val=l1.getElementAt(jlist.getSelectedIndex());
        l1.remove(jlist.getSelectedIndex());
       return val;
        }
     return "";
    }
    
   public void setVisNotif(boolean n){
       topPanel.setVisNot(n);
   } 

   public void setVisTextBtn(String t) {
       topPanel.setVisBtnText(t);
   }  
   
   public void notReadClick(JButton btn){
       topPanel.NotifClick(btn);
   }
}
