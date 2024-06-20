
package ald;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;



public class Loading extends JFrame {
    
    Timer tm;
    JLabel lbl,bg;
    Font f;
    
    ImageIcon img,introI;
    
    Loading(){
    
    initcomponent();
        
    }
    
    
    
    public void initcomponent(){
        
        
    
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
 
    img = new ImageIcon(getClass().getResource("/Images/loading.gif"));
      introI = new ImageIcon(getClass().getResource("/Images/intro.gif"));  
    
    setBounds((int) (screen.getWidth()/2)-300, 
       (int) (screen.getHeight()/2)-200,600,400);
    setResizable(false);
    
    setUndecorated(true);
    
    this.setLayout(null);
    
    f = new Font("Segoe UI Black",Font.PLAIN, 18);

       lbl = new JLabel(introI);
    lbl.setBounds((600/2)-150,(400/2)-200,300,150);
    this.add(lbl);

        
    bg = new JLabel(img);
    bg.setBounds(0,0,600,400);
    this.add(bg);
    
 
    
    
    
    }
    
 
    
    
}
