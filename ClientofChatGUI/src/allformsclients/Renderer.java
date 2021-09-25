package allformsclients;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Сергей Зима
 */
public class Renderer extends JPanel implements ListCellRenderer<Object> {
    //DefaultListCellRenderer
    private JLabel L1;
     private JLabel L2;
     public Renderer(){
         super();
        L1=new JLabel();
         L2=new JLabel();
         L1.setFont(new Font("Verdana",Font.ITALIC,15));
         L1.setForeground(Color.BLUE);
         L2.setFont(new Font("Verdana",Font.ITALIC,15));
            L2.setForeground(Color.GREEN);
         setPreferredSize(new Dimension(400,35));
         setLayout(new FlowLayout());
         add(L1);
          add(L2);
     }
    @Override
   public Component getListCellRendererComponent(JList<?> list, Object value,int index, boolean isSelected, boolean cellHasFocus) {
       PanelForJlist pl=(PanelForJlist) value;
       //this.add(pl.getLab().getText(),pl.getLab1());
       this.L1.setText(pl.getLab().getText());
       this.L2.setText(pl.getLab1().getText());
      
       if(isSelected){
           this.setBackground(list.getSelectionBackground());
           this.setForeground(list.getSelectionForeground());
       }else{
              this.setBackground(Color.WHITE);
           this.setForeground(list.getSelectionForeground());
       }
       
       this.setEnabled(true);
       this.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
       this.setFont(list.getFont());
        return this;
    }
}
