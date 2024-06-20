
package ald;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class myData {
    
    static String url = "";
     Connection con;
     ResultSet rs;
    Statement st;
     PreparedStatement ps;
    int regS ;
    int refKC;
    
    String fullName;
    String userid;
    String deg;
    String e;
    
    String[] [] r;
    
    String[][] tempArrayForPro;
    
    String[] dltUserC;
    
    double[] tempArrayForAcc;
    
    boolean picuser=false;
    
    ;

    
    myData() throws ClassNotFoundException, SQLException{
        
       
        
    con= DriverManager.getConnection(url);
   
    st = con.createStatement();
    String q = "select count(*) from reg";
    String q1 = "select count(*) from refK";
   
    rs = st.executeQuery(q);
    
    regS = rs.getInt(1);
    
    rs = st.executeQuery(q1);
    
    refKC = rs.getInt(1);
    
    //rs.close();
    //con.close();

 
    }
    
    public boolean checkUs(String us,String ps) throws SQLException{
        
        
    
      boolean check=false;
      
      String query ="select * from users";
     
      
      rs = st.executeQuery(query);
      
         String usname;
         String password;
        
           while(rs.next()){
      
       usname = rs.getString(2);
       password = rs.getString(3);
       
            

       if(us.equals((usname))&& ps.equals(password)){
           check=true;
           String query1="select * from reg where (username="+"'"+usname+"'"+")";
           rs = st.executeQuery(query1);
           fullName = rs.getString(2);
           userid =rs.getString(6);
           deg = rs.getString(9);
           e = rs.getString(4);
       
       }
       
      }
           
     rs.close();
    st.close();
   
        
        
    return  check;
    
    }
    
    
    public String regCheck(String f,String ph,String e,String r,String u,String pa,String conp) throws SQLException{
    
         
        
        String returnM ="1";
        boolean sameUser=false;
        boolean vaRefKey=false;
        
        String max ="select MAX(serial) from reg"; 
         rs = st.executeQuery(max);
         
         int maxi = rs.getInt(1);
        
        
        String Inquery ="select * from users";
        String Inquery1 ="select * from refK";
        
      rs = st.executeQuery(Inquery);
      
         String usname;
        
        
           while(rs.next()){
      
       usname = rs.getString(2);
       
       if(usname.equals(u)){
       
           sameUser =true;
          
       
       }else{
       
           sameUser =false;
       
       }
       
       
       
      }
           
       rs = st.executeQuery(Inquery1);
       
       String refKF;
       String deg="";
       
         while(rs.next()){
      
       refKF = rs.getString(2);
       String x = rs.getString(3);
       
       if(refKF.equals(r)){
       
           vaRefKey =true;
           deg =x;
           
       
       }else{
       
           vaRefKey =false;
       
       
       }
       
       
       
      }
       
           
           if(sameUser){
           
           returnM ="2";
           
           }else{
               
               if(vaRefKey){
               
              String email="email";  
              
              int userid;
                   
               if(deg.equals("C.E.O.") || deg.equals("Editor")){
               
               userid=maxi;
               
               }else{
               
               userid= maxi+1;
               
               }
                                   
        
        
        
        if(e.equals("")|| e.equals("EMAIL") ){
        
          email = "Nill";
            
        }else{
        
            email = e;
        
        }
        

        
       
        
        String query ="insert into reg values (?,?,?,?,?,?,?,?,?)";
        
        ps = con.prepareStatement(query);
        ps.setInt(1, userid);
        ps.setString(2, f);
        ps.setString(3, ph);
        ps.setString(4, email);
        ps.setString(5, r);
        ps.setString(6, u);
        ps.setString(7, pa);
        ps.setString(8, conp);
        ps.setString(9, deg);
        
        int count1 = ps.executeUpdate();
        
        
       
        String query1 ="insert into users values (?,?,?)";
        
        ps = con.prepareStatement(query1);
        ps.setInt(1, userid);
        ps.setString(2, u);
        ps.setString(3, conp);
        
        int count = ps.executeUpdate();
        
        
        
      
        
        String regDlt = "delete from refK where refK="+"'"+r+"'";
        
        ps = con.prepareStatement(regDlt);
        
        int count2 = ps.executeUpdate();
        
    
        con.close();
        ps.close();
               
               }else{
               
               returnM ="3";
               
               
               }
           
           
 
           
           }
        
        
           
    rs.close();
   
    st.close();
   
        
    
    
        
    return returnM;
    
    
    }
    

    
    public boolean createUser(String nRefK,String nDeg) throws SQLException{
    
       
        
        
      boolean check=true;  
      
      
        String query ="select * from refK";
    
       ResultSet  r = st.executeQuery(query);
      
         String refKey;
         
        
           while(r.next()){
      
       refKey = r.getString(2);
       
       if(refKey.equals(nRefK)){
       
           check= false;
       
       }
       
       
      }
    
           if(check){
               
               
               
           
                   
        String query1 ="insert into refK values (?,?,?)";
        ps = con.prepareStatement(query1);
        ps.setInt(1,refKC );
        ps.setString(2,nRefK);
        ps.setString(3,nDeg );
        
        int count = ps.executeUpdate();
        
        
        
      
           
           
           
           }

           
     r.close();
    st.close();
    ps.close();
    
    
   
    
    return check;
    
    }
    
    public void deleteUser(String selUN) throws SQLException{
        
       
    
        String query = "delete from users where username="+"'"+selUN+"'";

        ps = con.prepareStatement(query);
        
        int count = ps.executeUpdate();
        
        
         
        String query1 = "delete from reg where username="+"'"+selUN+"'";
        ps = con.prepareStatement(query1);
        
        int count1 = ps.executeUpdate();
        
        ps.close();
        con.close();
        
        
 
    
    }
    
    public String[][] loadUser() throws SQLException{
    
    
    r = new String [regS-1][4];
    
    int i =0;
    
    String query = "select * from reg";
    
    rs = st.executeQuery(query);
    
    rs.next();
    
    while(rs.next()){
    
    r [i][0]= rs.getString(1);
    r [i][1]= rs.getString(6);
    r [i][2]= rs.getString(2);
    r [i][3]= rs.getString(9);
        
    i= i+1;
    
    }
    
 
       rs.close(); 
       st.close();
    
    
    
    return r;
    
    }
    
    public String[] loadForUpdate(String u) throws SQLException{
        
        String[] inreturn = new String[6];

    String usname;
        
    String query = "select * from reg";
    
    rs = st.executeQuery(query);
    
    while(rs.next()){
    
    usname = rs.getString(6);        
    
    if(u.equals(usname)){
    
    inreturn[0]=  Integer.toString(rs.getInt(1));
    inreturn[1]= rs.getString(2);
    inreturn[2]= rs.getString(3);
    inreturn[3]= rs.getString(4);
    inreturn[4]= rs.getString(6);
    inreturn[5]= rs.getString(8);
    
    }
    
    }
    
    rs.close();
    st.close();
   
    return inreturn;
    
    }
    
 
  public void update(String fullname,String cell,String email,String username,String ser,String imagePath,String Ouser) throws SQLException, FileNotFoundException, IOException{
  
   boolean checkUs=true; 
   
 
   
   String q ="select * from users where serial="+ser;
   
   rs = st.executeQuery(q);
   
   String ass = rs.getString(2);
  
   String usname;
        
    String query = "select * from reg";
    
    rs = st.executeQuery(query);
    
    while(rs.next()){
    
    usname = rs.getString(6);        
    
    if(username.equals(usname)){
        
        if(username.equals(ass)){
        
        checkUs=true;
            
        }else{
        
            checkUs = false;

            
        }
    

    }
    
    }
   
   
    if(checkUs==true){
    
        String query1 = "update reg set fullname='"+fullname+"' , phone ='"+cell+"', email='"+email+"', username='"+username+"' where serial="+ser;
        String query2 = "update users set username='"+username+"' where serial="+ser;
        String query3 = "select * from reg where serial="+ser;
        String query4 = "select * from userpic";
        String query5 = "update userpic set username='"+username+"' where username='"+Ouser+"'";

        
        ps = con.prepareStatement(query1);
        ps.executeUpdate();
        
        ps = con.prepareStatement(query2);
        ps.executeUpdate();
        
                
        rs = st.executeQuery(query4);
        
        
        
        while(rs.next()){
        
        
           if(rs.getString(2).equals(Ouser)){
           
            ps = con.prepareStatement(query5);
            ps.executeUpdate();    
               
           picuser=true;
           
           
           }
        
        }
        

        
        JOptionPane.showMessageDialog(null, "UPDATE COMPLETE");
        

        
        rs = st.executeQuery(query3);
        
        Home.name.setText(rs.getString(2));
        Home.uid.setText(rs.getString(6));
        Home.deg.setText(rs.getString(9));
        Home.email.setText(rs.getString(4));
        
        
        
        if(imagePath!=null){
        
        insertImage(username,imagePath,picuser);
        
        Home.userPic.setIcon(Edit.proPic);
          
            
        }
        
        
        
    
        
    }else{
    
        JOptionPane.showMessageDialog(null, "Username alreday exists");
    
    }
    
     
    
    ps.close();
    con.close();
    rs.close();
    st.close();
    
    
    
  }
  
  
  public void changePass(String u,String p) throws SQLException{
  
  String query1="update reg set pass='"+p+"' where username='"+u+"'";
  String query2="update reg set cPass='"+p+"' where username='"+u+"'";
  String query3 = "update users set password='"+p+"' where username='"+u+"'";
  
       ps = con.prepareStatement(query1);
       ps.executeUpdate();
  
       ps = con.prepareStatement(query2);
       ps.executeUpdate();
      
       ps = con.prepareStatement(query3);
       ps.executeUpdate();
       
       JOptionPane.showMessageDialog(null, "New Password has updated");
       
       ps.close();
       con.close();
  }
  
  public void insertImage(String u,String imagePath, boolean p) throws SQLException, FileNotFoundException, IOException{
  
      File f = new File(imagePath);
      //FileInputStream fis =  new FileInputStream(f);
     
      
    
      
      if(p==false){ 
      
        String q1 = "select count(*) from userpic";
        int size;
        rs = st.executeQuery(q1);
        size= rs.getInt(1);
        
        String query="insert into userPic values(?,?,?)";
        ps = con.prepareStatement(query);
        ps.setInt(1, size+1);
        ps.setString(2, u);
        ps.setBytes(3,readFile(f.toString()));
       
        
        int rc = ps.executeUpdate();
        
        if(rc==0){
        
            JOptionPane.showMessageDialog(null, "Image not stored");
            
        
        }
      
      }else{
      
      
          String query2 = "update userpic set propic = (?) where username='"+u+"'";
           ps = con.prepareStatement(query2);
           ps.setBytes(1,readFile(f.toString()));
      
      int rc1 = ps.executeUpdate();
      
      if(rc1==0){
      
      JOptionPane.showMessageDialog(null, "problem");
      
      }
           
      }
  
  
             ps.close();
        con.close();
        rs.close();
        st.close();
      
  }
  
  public boolean loadImage(String n) throws SQLException{
  
      boolean check = false;
      String query = "select * from userpic";
  
      rs = st.executeQuery(query);
      
      while(rs.next()){
      
          if(rs.getString(2).equals(n)){
          
              check= true;
          
          }
      
      }
      
      if(check){
      
          String query1 = "select * from userpic where username='"+n+"'";
          
          byte[] bb =new byte[1024]; 
          
          rs = st.executeQuery(query1);
          
          bb = rs.getBytes(3);
          
          Image img = Toolkit.getDefaultToolkit().createImage(bb);
          
          Image iimg = img.getScaledInstance(Home.userPic.getWidth(), Home.userPic.getHeight(), Image.SCALE_SMOOTH);
          
          ImageIcon newImg = new ImageIcon(iimg);
          
          Home.userPic.setIcon( newImg);
      
      }
      
      rs.close();
      st.close();
      
      return check;
  
  }
  
  
  public double[] homePageLoaderForAccount() throws SQLException{
      
     
      
        tempArrayForAcc = new double[3];
  
      String query = "select * from companyAccounts";
  
      rs = st.executeQuery(query);
      tempArrayForAcc[0] = rs.getDouble(1);
      tempArrayForAcc[1] = rs.getDouble(2);
      tempArrayForAcc[2] = rs.getDouble(3);


      rs.close();
      st.close();
      

      return tempArrayForAcc;
  
  }
  
  public String[][] projectListLoader() throws SQLException{
  
     
          String query1= "select count (*) from projectList";
          String query2= "select * from projectList";
          
         rs = st.executeQuery(query1);
         int ser = rs.getInt(1);
          
          tempArrayForPro = new String [ser][3];
          
           int i =0;
 
        rs = st.executeQuery(query2);
    
        while(rs.next()){
    
        tempArrayForPro [i][0]= rs.getString(1);
        tempArrayForPro [i][1]= rs.getString(2);
        tempArrayForPro [i][2]= rs.getString(3);
       
        
        i= i+1;
    
    }
    
        rs.close();
        st.close();
       
  return tempArrayForPro;
  
  }
  
  public void projectAdding(String pN, String cOb,String b, String pA, String d) throws SQLException{
  
  
     String query = "select count (*) from projectList";
      
     rs = st.executeQuery(query);
     int ser = rs.getInt(1);
     
     String query1 = "insert into projectList values (?,?,?)";
     
     ps = con.prepareStatement(query1);
     
     ps.setInt(1, ser+1);
     ps.setString(2, pN);
     ps.setString(3,cOb);
     
     
       int rc = ps.executeUpdate();
        
        if(rc==0){
        
            JOptionPane.showMessageDialog(null, "Could not entry the project in the List");
            
        
        }
     
     String temp = Integer.toString(ser+1);
     String query2 = "create table '"+pN.replaceAll("\\s","").toLowerCase()+temp+"' (cOb varchar(5000), branch varchar (5000), projectAddress varchar(5000), date varchar(5000));";
     
     st = con.createStatement();
     
     boolean rc1 = st.execute(query2);
     
    
        
        if(rc1==true){
        
            JOptionPane.showMessageDialog(null, "Could not create the project");
            
        
        }
    
     String query3 = "insert into '"+pN.replaceAll("\\s","").toLowerCase()+temp+"' values (?, ?, ?, ?)";
     
     ps = con.prepareStatement(query3);
     ps.setString(1,cOb);
     ps.setString(2,b );
     ps.setString(3,pA);
     ps.setString(4, d);
     
     
            int rc12 = ps.executeUpdate();
        
        if(rc12==0){
        
            JOptionPane.showMessageDialog(null, "Could not insert values in the current project");
            
        
        }else{
        
             JOptionPane.showMessageDialog(null, "Project has Added Succesfully");

        
        
        }
        
        ps.close();
        con.close();
        rs.close();
        st.close();
        
  
        Home.proName.setText("Project Name");
        Home.comBankName.setText("Company / Bank Name");
        Home.branchName.setText("Branch Name");
        Home.proAdd.setText("Project Address");
        Home.date.setDate(Date.valueOf(LocalDate.now()));

        
  }
  
    public byte[] readFile(String file) throws IOException {
        
        
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException ae) {
            System.err.println(ae.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }
 
}