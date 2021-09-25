package clientofchatgui;

import allformsclients.ClientGUI;
import java.io.IOException;
import javax.swing.SwingUtilities;

/**
 *
 * @author Сергей Зима
 */
public class StartClient {
    
    public static void main(String args[]) throws IOException{
         
         SwingUtilities.invokeLater(() -> {
            new ClientGUI();     
        });
    }
}
