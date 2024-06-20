
package ald;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



public class Edit extends JFrame {
    
    Font ft,fn,fu,fd;
    
    JTextField fullName,phone,email,username;
    JLabel fullNameT,phoneT,emailT,usernameT,file,userPic;
    
    JButton passChange,upPro,update;
    
    JFileChooser fc;
    
    static ImageIcon proPic;
    
    String[] load = new String[6];
    
    String imagePath;
    
    
    Edit(String userName) throws ClassNotFoundException, SQLException{
        
        myData m = new myData();
        load = m.loadForUpdate(userName);
           m.rs.close();
           m.st.close();
        
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    
    ft = new Font("Segoe UI ",Font.PLAIN, 14);
    fn = new Font("Segoe UI ",Font.PLAIN, 16);
    fu = new Font("Segoe UI Black",Font.PLAIN, 16);
    fd = new Font("Segoe UI Black",Font.HANGING_BASELINE, 28);
 
    setTitle("EDIT");
    
    setBounds((int) (screen.getWidth()/2)-250, 
       (int) (screen.getHeight()/2)-275,500,450);
    setResizable(false);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    this.setLayout(null);
    
    
    fullNameT = new JLabel("Full Name:");
    fullNameT.setBounds(20,20,120,25);
    fullNameT.setFont(fu);
    this.add(fullNameT);
    
    fullName = new JTextField(load[1]);
    fullName.setFont(ft);
    fullName.setBounds(fullNameT.getX(),fullNameT.getY()+fullNameT.getHeight()+10,200,25);
    this.add(fullName);
    
    phoneT = new JLabel("Cell Number:");
    phoneT.setBounds(fullNameT.getX(),fullName.getY()+fullName.getHeight()+10,120,25);
    phoneT.setFont(fu);
    this.add(phoneT);
    
    phone = new JTextField(load[2]);
    phone.setFont(ft);
    phone.setBounds(fullNameT.getX(),phoneT.getY()+phoneT.getHeight()+10,200,25);
    this.add(phone);
    
    
    emailT = new JLabel("Email:");
    emailT.setBounds(fullNameT.getX(),phone.getY()+phone.getHeight()+10,120,25);
    emailT.setFont(fu);
    this.add(emailT);
    
    email = new JTextField(load[3]);
    email.setFont(ft);
    email.setBounds(fullNameT.getX(),emailT.getY()+emailT.getHeight()+10,200,25);
    this.add(email);
    
    usernameT = new JLabel("User Name:");
    usernameT.setBounds(fullNameT.getX(),email.getY()+email.getHeight()+10,120,25);
    usernameT.setFont(fu);
    this.add(usernameT);
    
    username = new JTextField(load[4]);
    username.setFont(ft);
    username.setBounds(fullNameT.getX(),usernameT.getY()+usernameT.getHeight()+10,200,25);
    this.add(username);
    
    passChange = new JButton("Change Password");
    passChange.setBounds(fullNameT.getX(),username.getY()+username.getHeight()+20,150,25);
    this.add(passChange);
    
    upPro = new JButton("Update Profile Picture");
    upPro.setBounds(fullNameT.getX(),passChange.getY()+passChange.getHeight()+20,200,25);
    this.add(upPro);
    
    
    file = new JLabel("File: No file selected");
    file.setBounds(fullNameT.getX(),upPro.getY()+upPro.getHeight()+5,200,25);
    this.add(file);
    

    
    userPic = new JLabel();
    userPic.setBounds(fullName.getWidth()+100,phone.getY(),125,135);
    userPic.setOpaque(true);
    userPic.setBackground(Color.DARK_GRAY);
    this.add(userPic);
    
     update = new JButton("UPDATE");
    update.setBounds(userPic.getX(),upPro.getY(),150,25);
    this.add(update);
    
        update .addActionListener((ActionEvent e) ->{
    
            try {
                
                
                myData z = new myData();
                
                z.update(fullName.getText(), phone.getText(), email.getText(), username.getText(),load[0],imagePath,Home.uid.getText());
         
                dispose();
                
                
            } catch (SQLException | ClassNotFoundException | FileNotFoundException ex) {
                Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    });
        
                passChange .addActionListener((ActionEvent e) ->{
                    
                    
    
             String s = JOptionPane.showInputDialog( "Write the current Password");

              
            try {
                if(s!=null){
                
                             myData c = new myData();
                boolean what = c.checkUs(userName, s);
                
                if(what){
                
                     String x = JOptionPane.showInputDialog(null, "Write the new Password");
                     String y = JOptionPane.showInputDialog(null, "Confirm Password");
                     
                     if(x.equals(y)){
                         
                         
                         c.changePass(userName, y);
                     
                     }else{
                     
                    JOptionPane.showMessageDialog(null, "Password did not match");

                     
                     }
                
                }else{
                
                   JOptionPane.showMessageDialog(null, "Incorrect password");

                }
                
                c.con.close();
                c.rs.close();
                c.st.close();
                
                
                }
   
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    });
                
    
                
        upPro.addActionListener((ActionEvent e) ->{
    
           fc = new JFileChooser();
           fc.setCurrentDirectory(new File("."));
           FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png","gif","jpeg");    
           fc.addChoosableFileFilter(filter);
           int result = fc.showSaveDialog(null);
           if(result == JFileChooser.APPROVE_OPTION){
           
               
           file.setText("File: "+ fc.getSelectedFile());
           String a = fc.getSelectedFile().toString().replace("\\", "\\\\");
           imagePath = fc.getSelectedFile().getAbsolutePath();
               System.out.println(imagePath);
           userPic.setIcon(resizeImage(a));
               
           
           }
            
            
            
    });               
    
    }
    
    
    public ImageIcon resizeImage(String path){
    
      ImageIcon myImg = new ImageIcon(path);
     Image img = myImg.getImage();
     Image newImg = img.getScaledInstance(userPic.getWidth(), userPic.getHeight(), Image.SCALE_SMOOTH);
    proPic = new ImageIcon(newImg);
    return proPic;
    
    }
    
}
