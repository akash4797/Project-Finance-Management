
package ald;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class CreateUser extends JDialog {
    
    JLabel refL,degL;
    JTextField refT,degT;
    JButton crt,gen;
    Font fn;
    
    CreateUser(){
    
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
 
    setTitle("Create User");
    this.setModal(true);
    setBounds((int) (screen.getWidth()/2)-150, 
            (int) (screen.getHeight()/2)-275,300,450);
    setResizable(false);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    this.setLayout(null);
    
    fn = new Font("Segoe UI Black",Font.PLAIN, 16);
    
    
    refL = new JLabel("New Reference Key");
    refL.setHorizontalAlignment(SwingConstants.CENTER);
    refL.setFont(fn);
    refL.setBounds((this.getWidth()/2)-90,30,180,25);
    this.add(refL);
    
   
    
    refT = new JTextField();
    //refT.setEditable(false);
    refT.setHorizontalAlignment(SwingConstants.CENTER);
    refT.setFont(fn);
    refT.setBounds((this.getWidth()/2)-90,60,180,25);
    this.add(refT);
    
    refT.setFocusable(false);
    
    gen = new JButton("GENARATE");
    gen.setFont(fn);
    gen.setBounds((this.getWidth()/2)-70,90,140,25);
    this.add(gen);
    
    
    degL = new JLabel("Designation ");
    degL.setHorizontalAlignment(SwingConstants.CENTER);
    degL.setFont(fn);
    degL.setBounds((this.getWidth()/2)-90,140,180,25);
    this.add(degL);
    
    degT = new JTextField();
    degT.setHorizontalAlignment(SwingConstants.CENTER);
    degT.setFont(fn);
    degT.setBounds((this.getWidth()/2)-90,170,180,25);
    this.add(degT);
    
    crt = new JButton("CREATE");
    crt.setHorizontalAlignment(SwingConstants.CENTER);
    crt.setFont(fn);
    crt.setBounds((this.getWidth()/2)-60,270,120,23);
    this.add(crt);
    
    
    gen .addActionListener((ActionEvent e) ->{
    
    
        try {
            myData m = new myData();
            int n = m.refKC;
            
            refT.setText("ARE"+n);
            
            m.rs.close();
            m.st.close();
            m.con.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    });
    
    crt.addActionListener((ActionEvent ae) -> {
        
//                   String currentDirectory = System.getProperty("user.dir");
//                String dirWithDS = currentDirectory.replace("\\","\\\\");
//                               try{
//                    
//               Runtime.getRuntime().exec("cmd /c cd "+dirWithDS+"\\src && cacls "+dirWithDS+"\\src\\data /E /P everyone:f");
//              
//               
//                }catch(Exception aa){
//                
//                JOptionPane.showMessageDialog(null, aa);
//                
//                }
//                               
//                               sleep(2000);

          
        if(refT.getText().equals("")){
        
        JOptionPane.showMessageDialog(null, "Please genarate a reference code");
            
        
        }else if(degT.getText().equals("")){
            
            
                
           JOptionPane.showMessageDialog(null, "Please fill the designation");
            
            
            }else {
            
                 try {
                               
       
                                                 
            myData m = new myData();
            
            boolean c = m.createUser(refT.getText(), degT.getText());
            
            m.rs.close();
            m.st.close();
            m.ps.close();
            
             
            
            if(c){
            
                JOptionPane.showMessageDialog(null, "New Reference Code has created.");
  
            }else{
            
            JOptionPane.showMessageDialog(null, "reference code already exists.");
            
   
            }
            
            
      
            
           
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
        

        
        
        }       
        
 

            

    });
    
    
    
    
    
    }
    
    
   
    
}
