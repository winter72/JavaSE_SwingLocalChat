package allformsclients;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;

/**
 *
 * @author Сергей Зима
 */
public class NotificationLabel extends JButton{
    Shape shape;
    public NotificationLabel(String l){
        super(l);
        setContentAreaFilled(false); 
               setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    @Override
    protected void paintComponent(Graphics g){
          
           int w = getWidth();
        int h = getHeight();
        Graphics2D g2d = (Graphics2D) g;
              g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);   
        Color color1 = Color.ORANGE;
        Color color2 = Color.RED;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        if (getModel().isArmed()) {
        g2d.setPaint(gp);
    
        }else{
          g2d.setPaint(gp);
        }
        g.fillOval(0, 0, w, h);
         super.paintComponent(g);
    }
    
     @Override
      protected void paintBorder(Graphics g) {
      g.setColor(Color.GREEN);
      g.drawOval(0, 0, getSize().width, 
      getSize().height);
     }
        
  public boolean contains(int x, int y) {
    if (shape == null || 
      !shape.getBounds().equals(getBounds())) {
      shape = new Ellipse2D.Float(0, 0, 
        getWidth(), getHeight());
    }
    return shape.contains(x, y);
  }
   
}
