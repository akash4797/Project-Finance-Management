
package ald;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

public class ALD extends JFrame implements KeyListener{
    
    
   
    JPanel intro,sisu,div,lc;
    JLabel background,closeButton,si,su,userT,
            passT,FirstNameT,emailT,phoneT,rfT,pT,cpT,nUT,introT;
    
    JTextField user,FirstName,email,phone,rf,nU;
    JPasswordField pass,p,cp;
    JButton login,signup;
    
    Font f;
    
    ImageIcon bg,cb,cb1,loginImg,introI;
    
    static ImageIcon appIcon;
    
   
    Timer lcT,lcT1,loadingT;
    
    int mouseX,mouseY;
    

   // UIManager UI;
    
    
    ALD() throws FileNotFoundException, FontFormatException, IOException{
        
        
         
                
                UILoader();
    
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
 
    setBounds((int) (screen.getWidth()/2)-150, 
            (int) (screen.getHeight()/2)-275,300,550);
    setUndecorated(true);
    setResizable(false);
    this.setLayout(null);

   
    
   appIcon = new ImageIcon(getClass().getResource("/Images/appicon.gif"));
    bg = new ImageIcon(getClass().getResource("/Images/bgp3.gif"));
    cb = new ImageIcon(getClass().getResource("/Images/closeButton.gif"));    
    cb1 = new ImageIcon(getClass().getResource("/Images/closeButton1.gif"));    
    introI = new ImageIcon(getClass().getResource("/Images/intro.gif"));    


    
    this.setIconImage(appIcon.getImage());
    


        
    f = new Font("Cambria",Font.BOLD, 14);
    
    closeButton = new JLabel(cb);
    closeButton.setBounds(300-45,15,30,30);
    this.add(closeButton);
    
    intro = new JPanel();
    intro.setBounds(3,3,293,150);
    intro.setBackground(new Color(150,150,150,110));
    this.add(intro);
    intro.setLayout(null);
    
    introT = new JLabel(introI);
    introT.setBounds(0,0,293,150);
    intro.add(introT);
    

    sisu = new JPanel();
    sisu.setBounds(3,153,294,40);
    sisu.setBackground(Color.DARK_GRAY);
    sisu.setLayout(null);
    this.add(sisu);
    
    
    //login = new JButton(loginImg);
    login = new JButton("LOGIN");
    login.setFont(f);
    login.setBorder(null);
    login.setForeground(Color.WHITE);
    login.setBackground(Color.DARK_GRAY);
    login.setBounds((this.getWidth()/2)-50,this.getHeight()-60,100,25);
    this.add(login);

    

    
    user = new JTextField();
    user.setCaretColor(Color.PINK);
    user.setFont(f);
    user.setForeground(Color.WHITE);
    user.setHorizontalAlignment(SwingConstants.CENTER);
    user.setBorder(null);
    user.setBounds((300/2)-(150/2),300,150,25);
    user.setOpaque(false);
    this.add(user);
    
    
    
     userT = new JLabel("USER NAME");
    userT.setOpaque(true);
    userT.setBackground(Color.DARK_GRAY);
    userT.setForeground(Color.white);
    userT.setFont(f);
    userT.setBounds((300/2)-(150/2),300,150,25);
    userT.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(userT);
    
        

    
    
    

    user.addFocusListener(new FocusListener(){
        @Override
        public void focusGained(FocusEvent fe) {
           
            if(user.getText().equals("")){
            
                userT.setText("");
                //userT.setVisible(false);
            
            }
            
        
        }

        @Override
        public void focusLost(FocusEvent fe) {

            
           if(user.getText().equals("")){
            
            userT.setText("USER NAME");
                
            
            }
            
        
        }
    
    
    
    });
    
    

    
    pass = new JPasswordField();
    pass.setCaretColor(Color.PINK);
    pass.setFont(f);
    pass.setForeground(Color.WHITE);
    pass.setHorizontalAlignment(SwingConstants.CENTER);
    pass.setBounds((300/2)-(150/2),350,150,25);
    pass.setBorder(null);
    pass.setBackground(Color.DARK_GRAY);
    pass.setOpaque(false);
    this.add(pass);
    
    passT = new JLabel("PASSWORD");
    passT.setOpaque(true);
    passT.setBackground(Color.DARK_GRAY);
    passT.setForeground(Color.white);
    passT.setFont(f);
    passT.setBounds((300/2)-(150/2),350,150,25);
    passT.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(passT);
    
        pass.addFocusListener(new FocusListener(){
        @Override
        public void focusGained(FocusEvent fe) {
        
                                  
                if(pass.getText().equals("")){
            
                passT.setText("");
                
            
            }
            
        
        }

        @Override
        public void focusLost(FocusEvent fe) {
            

                                              
                if(pass.getText().equals("")){
            
                passT.setText("PASSWORD");
                
            
            }
            
            
        }
    
    
    
    });
        
        
    signup = new JButton("SIGNUP");
    signup.setFont(f);
    signup.setBorder(null);
    signup.setForeground(Color.WHITE);
    signup.setBackground(Color.DARK_GRAY);
    signup.setBounds((-300/2)-50,this.getHeight()-60,100,25);
    this.add(signup);
    
    FirstName = new JTextField();
    FirstName.setBorder(null);
    FirstName.setCaretColor(Color.PINK);
    FirstName.setFont(f);
    FirstName.setForeground(Color.WHITE);
    FirstName.setHorizontalAlignment(SwingConstants.CENTER);
    FirstName.setBounds((-300/2)-((150/2)+30),240,200,25);
    FirstName.setOpaque(false);
    FirstName.setBackground(Color.DARK_GRAY);
    this.add(FirstName);
    
    FirstNameT = new JLabel("* FULL NAME");
    FirstNameT.setOpaque(true);
    FirstNameT.setBackground(Color.DARK_GRAY);
    FirstNameT.setForeground(Color.white);
    FirstNameT.setFont(f);
    FirstNameT.setBounds((-300/2)-((150/2)+30),240,200,25);
    FirstNameT.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(FirstNameT);
    
    
    
    FirstName.addFocusListener(new FocusListener(){
        @Override
        public void focusGained(FocusEvent fe) {
        
                                              
                if(FirstName.getText().equals("")){
            
                FirstNameT.setText("");
                
            
            }
            
        
        }

        @Override
        public void focusLost(FocusEvent fe) {

            
                                                          
             if(FirstName.getText().equals("")){
            
                FirstNameT.setText("* FULL NAME");
                
            
            }
            
        }
    
    
    
    });
    
    email = new JTextField();
    email.setBorder(null);
    email.setCaretColor(Color.PINK);
    email.setFont(f);
    email.setForeground(Color.WHITE);
    email.setHorizontalAlignment(SwingConstants.CENTER);
    email.setBounds((-300/2)-((150/2)+30),310,200,25);
    email.setOpaque(false);
    email.setBackground(Color.DARK_GRAY);
    this.add(email);
    
    emailT = new JLabel("EMAIL");
    emailT.setOpaque(true);
    emailT.setBackground(Color.DARK_GRAY);
    emailT.setForeground(Color.white);
    emailT.setFont(f);
    emailT.setBounds((-300/2)-((150/2)+30),310,200,25);
    emailT.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(emailT);
    
      email.addFocusListener(new FocusListener(){
        @Override
        public void focusGained(FocusEvent fe) {
        
              if(email.getText().equals("")){
            
                emailT.setText("");
                
            
            }

        
        }

        @Override
        public void focusLost(FocusEvent fe) {
            
        
              if(email.getText().equals("")){
            
                emailT.setText("EMAIL");
                
            
            }
            
            
        }
    
    
    
    });
    
    phone = new JTextField();
    phone.setBorder(null);
    phone.setCaretColor(Color.PINK);
    phone.setFont(f);
    phone.setForeground(Color.WHITE);
    phone.setHorizontalAlignment(SwingConstants.CENTER);
    phone.setBounds((-300/2)-((150/2)+30),275,200,25);
    phone.setOpaque(false);
    phone.setBackground(Color.DARK_GRAY);
    this.add(phone);
    
    phoneT = new JLabel("* CELL NUMBER");
    phoneT.setOpaque(true);
    phoneT.setBackground(Color.DARK_GRAY);
    phoneT.setForeground(Color.white);
    phoneT.setFont(f);
    phoneT.setBounds((-300/2)-((150/2)+30),275,200,25);
    phoneT.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(phoneT);
    
          phone.addFocusListener(new FocusListener(){
        @Override
        public void focusGained(FocusEvent fe) {
        

           if(phone.getText().equals("")){
            
                phoneT.setText("");
                
            
            }
            
        
        }

        @Override
        public void focusLost(FocusEvent fe) {

            
           if(phone.getText().equals("")){
            
                phoneT.setText("* CELL NUMBER");
                
            
            }
            
        }
    
    
    
    });
    
    rf = new JTextField();
    rf.setBorder(null);
    rf.setCaretColor(Color.PINK);
    rf.setFont(f);
    rf.setForeground(Color.WHITE);
    rf.setHorizontalAlignment(SwingConstants.CENTER);
    rf.setBounds((-300/2)-((150/2)+30),345,200,25);
    rf.setOpaque(false);
    rf.setBackground(Color.DARK_GRAY);
    this.add(rf);
    
    rfT = new JLabel("* REFERENCE KEY");
    rfT.setOpaque(true);
    rfT.setBackground(Color.DARK_GRAY);
    rfT.setForeground(Color.white);
    rfT.setFont(f);
    rfT.setBounds((-300/2)-((150/2)+30),345,200,25);
    rfT.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(rfT);
    
              rf.addFocusListener(new FocusListener(){
        @Override
        public void focusGained(FocusEvent fe) {
        
  
           if(rf.getText().equals("")){
            
                rfT.setText("");
                
            
            }

        
        }

        @Override
        public void focusLost(FocusEvent fe) {
            
  
              
           if(rf.getText().equals("")){
            
                rfT.setText("* REFERENCE KEY");
                
            
            }
            
        }
    
    
    
    });
          
     
    nU = new JTextField();
    nU.setBorder(null);
    nU.setCaretColor(Color.PINK);
    nU.setFont(f);
    nU.setForeground(Color.WHITE);
    nU.setHorizontalAlignment(SwingConstants.CENTER);
    nU.setBounds((-300/2)-((150/2)+30),380,200,25);
    nU.setOpaque(false);
    nU.setBackground(Color.DARK_GRAY);
    this.add(nU);
    
    nUT = new JLabel("* USERNAME");
    nUT.setOpaque(true);
    nUT.setBackground(Color.DARK_GRAY);
    nUT.setForeground(Color.white);
    nUT.setFont(f);
    nUT.setBounds((-300/2)-((150/2)+30),380,200,25);
    nUT.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(nUT);
    
              nU.addFocusListener(new FocusListener(){
        @Override
        public void focusGained(FocusEvent fe) {
        
  
           if(nU.getText().equals("")){
            
                nUT.setText("");
                
            
            }

        
        }

        @Override
        public void focusLost(FocusEvent fe) {
            
  
              
           if(nU.getText().equals("")){
            
                nUT.setText("* USERNAME");
                
            
            }
            
        }
    
    
    
    });
              
               
              
              
    
    p = new JPasswordField();
    p.setBorder(null);
    p.setCaretColor(Color.PINK);
    p.setFont(f);
    p.setForeground(Color.WHITE);
    p.setHorizontalAlignment(SwingConstants.CENTER);
    p.setBounds((-300/2)-((150/2)+30),415,200,25);
    p.setOpaque(false);
    p.setBackground(Color.DARK_GRAY);
    this.add(p);
    
    pT = new JLabel("* PASSWORD");
    pT.setOpaque(true);
    pT.setBackground(Color.DARK_GRAY);
    pT.setForeground(Color.white);
    pT.setFont(f);
    pT.setBounds((-300/2)-((150/2)+30),415,200,25);
    pT.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(pT);
    
                  p.addFocusListener(new FocusListener(){
        @Override
        public void focusGained(FocusEvent fe) {
        
        
         if(p.getText().equals("")){
            
                pT.setText("");
                
            
            }
            
        
        }

        @Override
        public void focusLost(FocusEvent fe) {
            

          if(p.getText().equals("")){
            
                pT.setText("* PASSWORD");
                
            
            }
            
        }
    
    
    
    });
    
    
    cp = new JPasswordField();
    cp.setBorder(null);
    cp.setCaretColor(Color.PINK);
    cp.setFont(f);
    cp.setForeground(Color.WHITE);
    cp.setHorizontalAlignment(SwingConstants.CENTER);
    cp.setBounds((-300/2)-((150/2)+30),450,200,25);
    cp.setOpaque(false);
    cp.setBackground(Color.DARK_GRAY);
    this.add(cp);
    
    
    cpT = new JLabel("* CONFIRM PASSWORD");
    cpT.setOpaque(true);
    cpT.setBackground(Color.DARK_GRAY);
    cpT.setForeground(Color.white);
    cpT.setFont(f);
    cpT.setBounds((-300/2)-((150/2)+30),450,200,25);
    cpT.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(cpT);
    
     cp.addFocusListener(new FocusListener(){
        @Override
        public void focusGained(FocusEvent fe) {
        
 
         if(cp.getText().equals("")){
            
                cpT.setText("");
                
            
            } 
           
        
        }

        @Override
        public void focusLost(FocusEvent fe) {
            
             
         if(cp.getText().equals("")){
            
                cpT.setText("* CONFIRM PASSWORD");
                
            
            } 
            
            
            
        }
    
    
    
    });
     
     
    
    
    si = new JLabel("SIGN IN");
    si.setOpaque(true);
    si.setBackground(Color.GRAY);
    si.setForeground(Color.WHITE);
    si.setHorizontalAlignment(SwingConstants.CENTER);
    si.setBounds(0,0,150,38);
    sisu.add(si);
    
    si.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent me) {
       
          if(lc.getX()<=0){
        }else{
        
        lcT1 = new Timer(5, (ActionEvent ae) -> {
            
            if(lc.getX()<=0){
                 su.setBackground(Color.DARK_GRAY);
                 si.setOpaque(true);
                 si.setBackground(Color.GRAY);
                lcT1.stop();
            }else{
            
            lc.setBounds(lc.getX()-15,lc.getY(),150,2);
            user.setBounds(user.getX()-30,user.getY(),150,25);
            userT.setBounds(userT.getX()-30,userT.getY(),150,25);
            pass.setBounds(pass.getX()-30,pass.getY(),150,25);
            passT.setBounds(passT.getX()-30,passT.getY(),150,25);
            FirstName.setBounds(FirstName.getX()-30,FirstName.getY(),
                    FirstName.getWidth(),FirstName.getHeight());
            
            FirstNameT.setBounds(FirstNameT.getX()-30,FirstNameT.getY(),
                    FirstNameT.getWidth(),FirstNameT.getHeight());
            
            email.setBounds(email.getX()-30,email.getY(),
                   email.getWidth(),email.getHeight());
            
            emailT.setBounds(emailT.getX()-30,emailT.getY(),
                emailT.getWidth(),emailT.getHeight());
            
            phone.setBounds(phone.getX()-30,phone.getY(),
                   phone.getWidth(),phone.getHeight());
            
             
            phoneT.setBounds(phoneT.getX()-30,phoneT.getY(),
                   phoneT.getWidth(),phoneT.getHeight());
            
            rf.setBounds(rf.getX()-30,rf.getY(),
                  rf.getWidth(),rf.getHeight());
            
             rfT.setBounds(rfT.getX()-30,rfT.getY(),
                  rfT.getWidth(),rfT.getHeight());
            
              p.setBounds(p.getX()-30,p.getY(),
                  p.getWidth(),p.getHeight());
              
               pT.setBounds(pT.getX()-30,pT.getY(),
                  pT.getWidth(),pT.getHeight());
              
             cp.setBounds(cp.getX()-30,cp.getY(),
                  cp.getWidth(),cp.getHeight());
             
               cpT.setBounds(cpT.getX()-30,cpT.getY(),
                  cpT.getWidth(),cpT.getHeight());
                             
             nU.setBounds(nU.getX()-30,nU.getY(),
                  nU.getWidth(),nU.getHeight());
             
               nUT.setBounds(nUT.getX()-30,nUT.getY(),
                  nUT.getWidth(),nUT.getHeight());
             
            login.setBounds(login.getX()-30,login.getY(),
              login.getWidth(),login.getHeight());
            
              signup.setBounds(signup.getX()-30,signup.getY(),
              signup.getWidth(),signup.getHeight());
              
              
            
            //cpoied from Background MouseListener
            
        
                FirstName.setText("");
                FirstNameT.setText("* FULL NAME");
            
                emailT.setText("EMAIL");
                email.setText("");
            
                phoneT.setText("* CELL NUMBER");
                phone.setText("");
                
            
                rfT.setText("* REFERENCE KEY");
                rf.setText("");
                
            
                pT.setText("* PASSWORD");
                p.setText("");
                
            
                cpT.setText("* CONFIRM PASSWORD");
                cp.setText("");
                
                   
                nUT.setText("* USERNAME");
                nU.setText("");
                
                
           
            
            }


        });
        
        lcT1.start();
        
        }
        
        }

    
    });
    
    div = new JPanel();
    div.setBackground(Color.WHITE);
    //div.setBounds(150,12,1,15);
    div.setBounds(150,0,1,40);
    sisu.add(div);
    
    su = new JLabel("SIGN UP");
    su.setForeground(Color.WHITE);
    su.setHorizontalAlignment(SwingConstants.CENTER);
    su.setBounds(152,0,150,38);
    sisu.add(su);
    
    
    lc = new JPanel();
    lc.setBounds(0,38,150,2);
    lc.setBackground(Color.WHITE);
    sisu.add(lc);
    
      

    
    su.addMouseListener(new MouseAdapter(){
    
        @Override
        public void mouseClicked(MouseEvent me) {
            
            su.requestFocus(true);
        
        if(lc.getX()>=150){
        }else{
        
        lcT = new Timer(5, (ActionEvent ae) -> {
            
            if(lc.getX()>=150){
                si.setBackground(Color.DARK_GRAY);
                 su.setOpaque(true);
                 su.setBackground(Color.GRAY);
                lcT.stop();
            }else{
            
            lc.setBounds(lc.getX()+15,lc.getY(),150,2);
            user.setBounds(user.getX()+30,user.getY(),150,25);
            userT.setBounds(userT.getX()+30,userT.getY(),150,25);
            pass.setBounds(pass.getX()+30,pass.getY(),150,25);
            passT.setBounds(passT.getX()+30,passT.getY(),150,25);
            FirstName.setBounds(FirstName.getX()+30,FirstName.getY(),
                    FirstName.getWidth(),FirstName.getHeight());
            
             FirstNameT.setBounds(FirstNameT.getX()+30,FirstNameT.getY(),
                    FirstNameT.getWidth(),FirstNameT.getHeight());
            
            email.setBounds(email.getX()+30,email.getY(),
                  email.getWidth(),email.getHeight());
            
            emailT.setBounds(emailT.getX()+30,emailT.getY(),
                  emailT.getWidth(),emailT.getHeight());
            
             phone.setBounds(phone.getX()+30,phone.getY(),
                  phone.getWidth(),phone.getHeight());
             
              phoneT.setBounds(phoneT.getX()+30,phoneT.getY(),
                  phoneT.getWidth(),phoneT.getHeight());
             
                         
            rf.setBounds(rf.getX()+30,rf.getY(),
                  rf.getWidth(),rf.getHeight());
            
             rfT.setBounds(rfT.getX()+30,rfT.getY(),
                  rfT.getWidth(),rfT.getHeight());
            
                p.setBounds(p.getX()+30,p.getY(),
                  p.getWidth(),p.getHeight());
                
                pT.setBounds(pT.getX()+30,pT.getY(),
                  pT.getWidth(),pT.getHeight());
                
                 cp.setBounds(cp.getX()+30,cp.getY(),
                  cp.getWidth(),cp.getHeight());
                 
                  cpT.setBounds(cpT.getX()+30,cpT.getY(),
                  cpT.getWidth(),cpT.getHeight());
                  
                  nU.setBounds(nU.getX()+30,nU.getY(),
                  nU.getWidth(),nU.getHeight());
                 
                  nUT.setBounds(nUT.getX()+30,nUT.getY(),
                  nUT.getWidth(),nUT.getHeight());
                 
              login.setBounds(login.getX()+30,login.getY(),
              login.getWidth(),login.getHeight());
              
              
               signup.setBounds(signup.getX()+30,signup.getY(),
              signup.getWidth(),signup.getHeight());
            
            //copied from background MouseListener
              
            
            
                
                userT.setText("USER NAME");
                user.setText("");

       
                passT.setText("PASSWORD");
                pass.setText("");
     
            
            
            
            }


        });
        
        lcT.start();
        
        }
        
        }

    
    });
   
    intro.addMouseListener(new MouseAdapter(){
  
        @Override
        public void mousePressed(MouseEvent me) {
        
        mouseX = me.getX();
        mouseY = me.getY();
        
        }

    
    
    });
    
    intro.addMouseMotionListener(new MouseMotionListener(){
        @Override
        public void mouseDragged(MouseEvent me) {
        
            setLocation(me.getXOnScreen()-mouseX,me.getYOnScreen()-mouseY);
        
        
        }

        @Override
        public void mouseMoved(MouseEvent me) {
        }
    });
    
    closeButton.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent me) {

            System.exit(0);
            
        }


        @Override
        public void mouseEntered(MouseEvent me) {
            
            closeButton.setIcon(cb1);

        }

        @Override
        public void mouseExited(MouseEvent me) {

            closeButton.setIcon(cb);
            
        }
        
    
        
    
    
    });
    
    
    login.addActionListener((ActionEvent ae) -> {
        
        login();

    });
    
    
    signup.addActionListener((ActionEvent ae) -> {

        
        signup();

    });
    

    background = new JLabel(bg);
    background.setBounds(0,0,300,550);
    this.add(background);
    
    background.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent me) {
            
            String f="";
            
            if(user.getText().equals(f)){
            
                userT.setText("USER NAME");
               
                
            }
            
            if(pass.getText().equals(f)){
            
                passT.setText("PASSWORD");
           
                
            }
            
            
            if(FirstName.getText().equals(f)){
            
                FirstNameT.setText("* FULL NAME");
               
                
            }
            
             if(email.getText().equals(f)){
            
                emailT.setText("EMAIL");
                
                
            }
             
             if(phone.getText().equals(f)){
            
                phoneT.setText("* CELL NUMBER");
              
                
            }
             
              if(rf.getText().equals(f)){
            
                rfT.setText("* REFERENCE KEY");
                
                
            }
              
             if(p.getText().equals(f)){
            
                pT.setText("* PASSWORD");
                
            }
             
             if(cp.getText().equals(f)){
            
                cpT.setText("* CONFIRM PASSWORD");
                
                
            }
             
             
             background.requestFocus(true);
            
            

        }

  
    
    
    
    });
    
      
        
        Keylistener();
    
    }
   

    


    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        if(ke.getSource()==user){
        
                   
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
        
                  
        login();

    
        }
            
            
        }
        

        
         if(ke.getSource()==pass){
        
                   
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
            
            login();
    
        }
            
            
        }
        if(ke.getSource()==FirstName){
        
                   
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
        
                  
        signup();

    
        }
            
            
        }
       if(ke.getSource()==email){
        
                   
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
        
                  
        signup();

    
        }
            
            
        }
       
              if(ke.getSource()==phone){
        
                   
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
        
                  
        signup();

    
        }
            
            
        }
              
         if(ke.getSource()==rf){
        
                   
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
        
                  
        signup();

    
        }
            
            
        }
         
       if(ke.getSource()==p){
        
                   
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
        
                  
        signup();

    
        }
            
            
        }
       
      if(ke.getSource()==cp){
        
                   
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
        
                  
        signup();

    
        }
            
            
        }
 
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
        
        
    public void login(){
    
    
          
        
        try {
            
           myData m = new myData();
           boolean c = m.checkUs(user.getText(), pass.getText());
            
           if(c){
           
               
                     Loading l = new Loading();
       
                     l.setVisible(true);
                  
                     dispose();
                     
                     loadingT = new Timer(3500, (ActionEvent e) -> {
    
        if(loadingT.getDelay()==3500){
        
            try {
                loadingT.stop();
                
                Home h = new Home(m.fullName,m.userid,m.deg,m.e);
                h.setVisible(true);
                
                l.dispose();
            } catch (ClassNotFoundException | SQLException | IOException ex) {
                Logger.getLogger(ALD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        
        }
        
       
    
    
    });
                     
                  loadingT.start();   
               
               
               
           
           }else{
           
                         
          

               
               JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
           
           }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ALD.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    
    }
    
    public void signup(){
  
        String f = ("* FULL NAME");
        String e = ("EMAIL");
        String c =("* CELL NUMBER");
        String r = ("* REFERENCE KEY");
        String cpa =("* CONFIRM PASSWORD");
        String pa =("* PASSWORD");
        String un =("* USERNAME");
        String b ="";
        
        
        
        
        
        
        
        
        if(FirstName.getText().equals(b)||FirstName.getText().equals(f)
                || phone.getText().equals(b) || phone.getText().equals(c)
                    || rf.getText().equals(b) ||  rf.getText().equals(r)
                        || nU.getText().equals(b) || nU.getText().equals(un)
                            || p.getText().equals(b) || p.getText().equals(pa)
                                || cp.getText().equals(b) || cp.getText().equals(cpa)){
        

            
        JOptionPane.showMessageDialog(null, "Fill the required Blanks");
        
        
        
        }else{
            
            if(p.getText().equals(cp.getText())){
                
                
           
                try {
                    myData m = new myData();
                    
                   String getId = m.regCheck(FirstName.getText(), phone.getText(),email.getText().toLowerCase()
                            , rf.getText(),nU.getText(), p.getText(), cp.getText());
                   
              
                    
                   
                   
                    
                    if(getId.equals("2")){

                        JOptionPane.showMessageDialog(null, "User already exists");
                        rfT.setText("* REFERENCE KEY");
                        rf.setText("");
                    pT.setText("* PASSWORD");
                    p.setText("");
                    cpT.setText("* CONFIRM PASSWORD");
                    cp.setText("");
                    
                    
                    
                    }else if(getId.equals("3")){
                    

                        
                    JOptionPane.showMessageDialog(null, "INVALID REFFERENCE KEY");
                    rfT.setText("* REFERENCE KEY");
                        rf.setText("");
                    pT.setText("* PASSWORD");
                    p.setText("");
                    cpT.setText("* CONFIRM PASSWORD");
                    cp.setText("");

                    
                    }else if(getId=="1"){
       
                    
                    JOptionPane.showMessageDialog(null, "<html>REGISTRATION SUCCESSFUL \nLOGIN TO ACCOUNT TO SEE YOUR PROFILE");
                    
                    FirstNameT.setText("* FULL NAME");
                    FirstName.setText("");
                    phoneT.setText("* CELL NUMBER");
                    phone.setText("");
                    emailT.setText("EMAIL");
                    email.setText("");
                    rfT.setText("* REFERENCE KEY");
                    rf.setText("");
                    pT.setText("* PASSWORD");
                    p.setText("");
                    cpT.setText("* CONFIRM PASSWORD");
                    cp.setText("");
                     nUT.setText("* USERNAME");
                    nU.setText("");
                    
   
                    
                    }
                    
                    
           
                   
                   
                    
                    
                } catch (ClassNotFoundException | SQLException ex) {

                    JOptionPane.showMessageDialog(null, ex);
                }
                
                
              
                
            
            
            
            
            }else{
      
            
            JOptionPane.showMessageDialog(null, "Password has not matched");
            
            
            }
        
             
        
        
        }
        
        

    
    
    }
    
     private void Keylistener(){
    
      user.addKeyListener(this);
        pass.addKeyListener(this);
        FirstName.addKeyListener(this);
        email.addKeyListener(this);
        phone.addKeyListener(this);
        rf.addKeyListener(this);
        p.addKeyListener(this);
        cp.addKeyListener(this);
    
    
    }
     
private void UILoader(){

     UIManager.put("OptionPane.background", Color.DARK_GRAY);
              UIManager.put("OptionPane.border", null);
              UIManager.put("OptionPane.messageForeground", Color.WHITE);
                UIManager.put("Panel.background", Color.DARK_GRAY);
                UIManager.put("Button.background", Color.DARK_GRAY);
                UIManager.put("Button.foreground", Color.WHITE);

                
                try{
                
                        Font cambria = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("src/font/calibri.ttf"));
                 GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(cambria);
                
                }catch(FontFormatException | IOException ae){
                
                 JOptionPane.showMessageDialog(null, "Could not load Fonts\nShowing Default Fonts");

                    
                }

}
    
    
}
