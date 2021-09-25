package allformsclients;

import javax.swing.JLabel;

/**
 *
 * @author Сергей Зима
 */
public class PanelForJlist  {

    private JLabel lab;
    private JLabel lab1;
    public PanelForJlist(String n,String m) {
      lab=new JLabel(n);
      lab1=new JLabel(m);
    }

    public JLabel getLab1() {
        return lab1;
    }
    public JLabel getLab() {
        return lab;
    }
    
}
