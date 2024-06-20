
package ald;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Files;
import java.sql.*;
import javax.swing.*;



public class FileExporter extends JFrame {
    
    String url = "jdbc:sqlite:data\\Default Data File\\alddb.sql";
    Connection con;
    ResultSet rs;
    Statement st;
    PreparedStatement ps;
    boolean hasUrl;
    String existingUrl;
    Font fd,fu;
    
    JLabel bigText,urlT;
    JButton btn,crt;
    
    JPanel bigPanel;
    
    JFileChooser fc;
    String folder;
    
    FileExporter() throws SQLException, ClassNotFoundException{
        
        
    
         con= DriverManager.getConnection(url);
         st = con.createStatement();
         
         String q = "select count(*) from url";
         
         rs = st.executeQuery(q);
         
          int x = rs.getInt(1);
          
         
          
         if(x==0) hasUrl= false;
         if(x==1) hasUrl= true;
         
         if(hasUrl){
             
             String query = "select * from url";
             rs = st.executeQuery(query);
             String a = rs.getString(2);
             String existingUrl = a.replace("\\","\\\\");
             
          
             
             File f = new File(existingUrl);
             
             if(f.exists()){
             
                  myData.url = "jdbc:sqlite:"+existingUrl;
             
                ALD al = new ALD();
                al.setVisible(true);
             
             }else{
             
             
                 
                 int z = JOptionPane.showConfirmDialog(null, "Database file is missing \nDo you want to create a new database file?","File missing",JOptionPane.YES_NO_CANCEL_OPTION);
                 
                 
                 if(z==JOptionPane.YES_OPTION){
                     
                     
                     String query1 = "delete from url where serial=1";

                        ps = con.prepareStatement(query1);
        
                        int count = ps.executeUpdate();
                        
                        initcomponents();
                        this.setVisible(true);
                     
                     
                     
                 }else if(z==JOptionPane.NO_OPTION){
                 
                 JOptionPane.showMessageDialog(null, "File is missing \nCannot run program");
                 System.exit(0);
                 
                 }else{
                 
                 System.exit(0);
                 
                 }
             
             }
             
        
             
            
             
             
         }else{
             
             initcomponents();
             this.setVisible(true);
 
                     
         }
         
         
    
    }
    
    public void initcomponents(){
        
             UIManager.put("OptionPane.background", Color.DARK_GRAY);
              UIManager.put("OptionPane.border", null);
              UIManager.put("OptionPane.messageForeground", Color.WHITE);
                UIManager.put("Panel.background", Color.DARK_GRAY);
                UIManager.put("Button.background", Color.DARK_GRAY);
                UIManager.put("Button.foreground", Color.WHITE);
                
                       
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
             
             setDefaultCloseOperation(DISPOSE_ON_CLOSE);
             
               setBounds((int) (screen.getWidth()/2)-250, 
                    (int) (screen.getHeight()/2)-275,500,450);
               
               setResizable(false);
               this.setLayout(null);
               setTitle("Create Destination");
               
               fd = new Font("Segoe UI Black",Font.PLAIN, 28);
               fu = new Font("Segoe UI Black",Font.PLAIN, 18);
               
               bigPanel = new JPanel();
               bigPanel.setBounds(0,0,this.getWidth(),this.getHeight());
               bigPanel.setLayout(null);
               this.add(bigPanel);
               
               bigText = new JLabel("<html>Welcome to AREA LINE DESIGN <br /> You Have to create a new Link of your data for security.</html>");
               bigText.setForeground(Color.WHITE);
               bigText.setHorizontalAlignment(SwingConstants.CENTER);
               bigText.setBounds(15,20,500,150);
               bigText.setFont(fd);
               bigPanel.add(bigText);
               
               btn = new JButton("Choose Folder");
               btn.setBounds((this.getWidth()/2)-80,250,160,25);
               btn.setFont(fu);
               bigPanel.add(btn);
               
               urlT= new JLabel("FOLDER: NOT SELECTED");
               urlT.setForeground(Color.WHITE);
               urlT.setHorizontalAlignment(SwingConstants.CENTER);
               urlT.setBounds((this.getWidth()/2)-250,230,500,25);
               bigPanel.add(urlT);
               
               crt = new JButton("CREATE");
               crt.setHorizontalAlignment(SwingConstants.CENTER);
               crt.setFont(fu);
               crt.setBounds((this.getWidth()/2)-60,350,120,23);
               bigPanel.add(crt);
    
               
               btn.addActionListener((ActionEvent ae) -> {
                   
                 UIManager.put("Panel.background", Color.LIGHT_GRAY);


               fc = new JFileChooser();
               fc.setCurrentDirectory(new File("."));
               fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               fc.setAcceptAllFileFilterUsed(false);
               
               
               
                   if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                     
                      urlT.setText("FOLDER: "+fc.getSelectedFile());
                      String a = fc.getSelectedFile().toString();
                      folder = a.replace("\\", "\\\\");
                      
                UIManager.put("Panel.background", Color.DARK_GRAY);

                       
                 } else {
                        
                UIManager.put("Panel.background", Color.DARK_GRAY);

                       
                 }
               
               });
               
               crt.addActionListener((ActionEvent ae) -> {

                   
                   if(urlT.getText().equals("FOLDER: NOT SELECTED")){
                   
                   JOptionPane.showMessageDialog(null, "You have not select any folder");
                   
                   }else{
                   
                       
                       System.out.println(folder);
                    File sourceFile = new File("data\\Default Data File\\alddb.sql");
                   
                                
             try{
                
                 File newDir = new File(folder+"\\ALD");
                 File destination = new File(newDir+"\\alddb.sql");
                
                 
                 if(newDir.mkdir()){
                     
                  Files.copy(sourceFile.toPath(), destination.toPath());
                 JOptionPane.showMessageDialog(null, "New Location has set");
                 
                 String query ="insert into url values (?,?)";
                 ps = con.prepareStatement(query);
                 
                 
                 ps.setInt(1, 1);
                 ps.setString(2, newDir+"\\alddb.sql");
                 
                 int count1 = ps.executeUpdate();
                 ps.close();
                 con.close();
                 
              
                
                myData.url = "jdbc:sqlite:"+destination;
                
                dispose();
                ALD al = new ALD();
                al.setVisible(true);
                 
                 
                 
                 }else{
                 
                     JOptionPane.showMessageDialog(null, "Maybe you have already a folder named ALD \nchange the name of the folder");
                 
                 }
             
         
             
             }catch(Exception ue){
             
                 JOptionPane.showMessageDialog(null,ue);

             
               }
                    
                   
                   }
               
               });
    
    
    }
    
}
