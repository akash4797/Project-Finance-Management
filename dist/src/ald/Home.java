
package ald;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Home extends JFrame implements FocusListener , MouseListener , KeyListener {
    
    JScrollPane sp;
    JPanel info,createProject,workpnl,darkui,searchpnl,darkui2 ;
    static JLabel userPic,profinT,name,uid,deg,email,addProT,rwvT
            ,rewvT,colCashT,comDebT,comCreT,accountsT;
    JButton edit,logout,delU,crU,branchOP,createPro,changeDeg;
    Font f,fn,fid;
    
    static double rwv,rewv,colCash, comDeb,comCre;
    
    static JTextField proName,branchName,proAdd,comBankName,projectSearch;
    static JDateChooser date;
    
    ImageIcon datePicker,searchURL;
    
    JTable projectTable;
    
    boolean admin=false;
    int branchOption=1;
    
    double[] originArray;
    
       
   
    
    String[] col={"Sl","Project Name","Company/Bank Name"};
    String[] [] row;
    
   //static String full,u,d,iema;
    
    Home(String fullname,String userName,String ideg,String iemail) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
        
  
    
        UILoader();
                
    if(ideg.equals("C.E.O.") || ideg.equals("Editor")){
    
        admin=true;

    }
    
 
        
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    
    datePicker = new ImageIcon(getClass().getResource("/Images/rsz_datepicker.gif"));
    

    
    f = new Font("Segoe UI Black",Font.PLAIN, 18);
    fn = new Font("Segoe UI Black",Font.PLAIN, 16);  
    fid = new Font("Segoe UI",Font.PLAIN, 15);  

    this.setIconImage(ALD.appIcon.getImage());
    setBounds(0,0,(int)screen.getWidth(),(int)screen.getHeight());
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setExtendedState(MAXIMIZED_BOTH);
    setTitle("ALD:/>"+userName+":/>"+"dashboard");
    this.setLayout(null);
    this.setMinimumSize(new Dimension(500,500));
    
    
    homePageLoader();

    
    
    info = new JPanel();
    info.setBounds(0,0,(int)screen.getWidth(),186);
    info.setOpaque(true);
    info.setBackground(Color.DARK_GRAY);
    info.setLayout(null);
    this.add(info);
    
    
    
    logout = new JButton("LOGOUT");
    logout.setBorder(null);
    logout.setForeground(Color.BLACK);
    logout.setFont(fn);
    logout.setBounds(info.getWidth()-100,25,80,23);
    info.add(logout);
    
    delU = new JButton("DELETE USER");
    delU.setBorder(null);
    delU.setForeground(Color.BLACK);
    delU.setFont(fn);
    delU.setBounds(info.getWidth()-135,info.getHeight()-40,120,23);
    info.add(delU);
    

    
    crU = new JButton("CREATE USER");
    crU.setBorder(null);
    crU.setForeground(Color.BLACK);
    crU.setFont(fn);
    crU.setBounds(info.getWidth()-280,info.getHeight()-40,120,23);
    info.add(crU);
    

    changeDeg = new JButton("Change Designation");
    changeDeg.setBounds(info.getWidth()-475,info.getHeight()-40,170,23);
        changeDeg.setBorder(null);
    changeDeg.setForeground(Color.BLACK);
    changeDeg.setFont(fn);
    info.add(changeDeg);
    
    
    profinT = new JLabel("PROFILE");
    profinT.setOpaque(true);
    profinT.setBackground(Color.BLACK);
    profinT.setForeground(Color.WHITE);
    profinT.setFont(f);
    profinT.setHorizontalAlignment(SwingConstants.CENTER);
    profinT.setBounds(20,(info.getHeight()/2)-80,125,25);
    info.add(profinT);


    userPic = new JLabel();
    userPic.setOpaque(true);
    userPic.setBackground(Color.BLUE);
    userPic.setBounds(20,(info.getHeight()/2)-55,125,135);
    info.add(userPic);
    
    
    name = new JLabel(fullname);
    name.setForeground(Color.WHITE);
    name.setFont(fn);
    name.setBounds(userPic.getWidth()+40,(info.getHeight()/2)-60,295,25);
    info.add(name);
    
    
    uid = new JLabel(userName);
    uid.setForeground(Color.WHITE);
    uid.setFont(fid);
    uid.setBounds(userPic.getWidth()+40,(info.getHeight()/2)-30,150,23);
    info.add(uid);
    
   
    picLoad();
    
    
    
    deg = new JLabel(ideg);
    deg.setForeground(Color.WHITE);
    deg.setFont(fid);
    deg.setBounds(userPic.getWidth()+40,(info.getHeight()/2),200,23);
    info.add(deg);
    
    email = new JLabel(iemail);
    email.setForeground(Color.WHITE);
    email.setFont(fid);
    email.setBounds(userPic.getWidth()+40,(info.getHeight()/2)+30,290,23);
    info.add(email);
    
    edit = new JButton("EDIT");
    edit.setBorder(null);
    edit.setForeground(Color.BLACK);
    edit.setFont(fn);
    edit.setBounds(userPic.getWidth()+40,(info.getHeight()/2)+60,80,23);
    info.add(edit);
    
    createProject = new JPanel();
    createProject.setBackground(Color.LIGHT_GRAY);
    createProject.setBounds(this.getWidth()-370,info.getHeight(),this.getWidth()-((this.getWidth()-370)),this.getHeight()-info.getHeight());
    this.add(createProject);
    
    createProject.setLayout(null);
    
    addProT= new JLabel("Create New Project");
    addProT.setFont(f);
    addProT.setBounds((createProject.getWidth()/2)-140,10,240,25);
    createProject.add(addProT);
    
    proName = new JTextField("Project Name");
    proName.setBounds((createProject.getWidth()/2)-140,40,240,25);
    createProject.add(proName);
    
    comBankName = new JTextField("Company / Bank Name");
    comBankName.setBounds((createProject.getWidth()/2)-140,proName.getY()+30,240,25);
    createProject.add(comBankName);
    
      branchName = new JTextField("Branch Name");
    branchName.setBounds((createProject.getWidth()/2)-140,comBankName.getY()+30,240,25);
    createProject.add(branchName);
    
    branchOP = new JButton("Off");
    branchOP.setBounds(branchName.getWidth()+56,comBankName.getY()+30,55,25);
    createProject.add(branchOP);
    
 
    
      proAdd = new JTextField("Project Address");
    proAdd.setBounds((createProject.getWidth()/2)-140,branchName.getY()+30,240,25);
    createProject.add(proAdd);
    

    
      date = new JDateChooser();
      date.setDate(Date.valueOf(LocalDate.now()));
      date.setIcon(datePicker);
    date.setBounds((createProject.getWidth()/2)-110,proAdd.getY()+30,180,30);
    date.setDateFormatString("dd-MM-yyyy");
    createProject.add(date);
    
        
    
    createPro = new JButton("Create");
    createPro.setBounds((createProject.getWidth()/2)-50,date.getY()+35,100,25);
    createProject.add(createPro);
    
    workpnl= new JPanel();
    workpnl.setLayout(null);
    workpnl.setBounds(0,info.getHeight(),createProject.getX(),this.getHeight()-info.getHeight());
    workpnl.setBackground(Color.WHITE);
    this.add(workpnl);
    
    
    rwvT = new JLabel("Running works value = "+ rwv+" /- ");
    rwvT.setBounds(proName.getX(),createProject.getHeight()-220,proName.getWidth()+30,30);
    rwvT.setFont(fid);
    createProject.add(rwvT);
    
    rewvT = new JLabel("Remaining value = "+ rewv+" /- ");
    rewvT.setBounds(proName.getX(),rwvT.getY()+createPro.getHeight()+10,proName.getWidth()+30,30);
    rewvT.setFont(rwvT.getFont());
    createProject.add(rewvT);
    
    
    colCashT = new JLabel("Collected Cash = "+ colCash+" /- ");
    colCashT.setBounds(proName.getX(),rewvT.getY()+createPro.getHeight()+10,proName.getWidth()+30,30);
    colCashT.setFont(rwvT.getFont());
    createProject.add(colCashT);
    
    comDebT = new JLabel("Company Debit = "+ comDeb+" /- ");
    comDebT.setBounds(proName.getX(),colCashT.getY()+createPro.getHeight()+10,proName.getWidth()+30,30);
    comDebT.setFont(rwvT.getFont());
    createProject.add(comDebT);
    
    comCreT = new JLabel("Company Credit = "+ comCre+" /- ");
    comCreT.setBounds(proName.getX(),comDebT.getY()+createPro.getHeight()+10,proName.getWidth()+30,30);
    comCreT.setFont(rwvT.getFont());
    createProject.add(comCreT);
    
    accountsT = new JLabel("ACCOUNTS");
    accountsT.setBounds(rwvT.getX(),rwvT.getY()-(10+addProT.getHeight()),addProT.getWidth(),addProT.getHeight());
    accountsT.setFont(f);
    createProject.add(accountsT);
    
 
    
    searchpnl = new JPanel();
    searchpnl.setBounds(0,30,20,workpnl.getHeight()-120);
    searchpnl.setBackground(Color.DARK_GRAY);
    workpnl.add(searchpnl);
    
    searchpnl.setLayout(null);
    

    projectSearch = new JTextField("SEARCH");
    projectSearch.setHorizontalAlignment(SwingConstants.CENTER);
    projectSearch.setCaretColor(Color.WHITE);
    projectSearch.setForeground(Color.WHITE);
    projectSearch.setFont(f);
    projectSearch.setBorder(LineBorder.createBlackLineBorder());
    projectSearch.setOpaque(true);
    projectSearch.setBackground(Color.DARK_GRAY);
    searchpnl.add(projectSearch);
    projectSearch.setVisible(false);
    
    
    darkui2 = new JPanel();
    darkui2.setBackground(Color.BLACK);
    searchpnl.add(darkui2);
    darkui2.setVisible(false);
    
   

    darkui = new JPanel();
    darkui.setBounds(searchpnl.getX()+5,searchpnl.getY()+5,
            searchpnl.getWidth(),searchpnl.getHeight());
    darkui.setBackground(Color.BLACK);
    workpnl.add(darkui);
    
     projectTable = new JTable(){
    
    @Override
    public boolean isCellEditable(int rowIndex,int colIndex){
    
    return false;
    
    }
    
    };
    
    

    
    DefaultTableModel dm= new DefaultTableModel(row,col);
    
  
    projectTable.setModel(dm);
    
    
   projectTable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(0);
  
     
    sp = new JScrollPane(projectTable);

     
    sp.setOpaque(false);
   
    
    searchpnl.add(sp);
    
    projectTable.getTableHeader().setBackground(Color.DARK_GRAY);
    projectTable.getTableHeader().setForeground(Color.WHITE);
    projectTable.getTableHeader().setFont(fid);
    projectTable.getTableHeader().setBorder(LineBorder.createBlackLineBorder());
    projectTable.setFont(fid);
    projectTable.setForeground(Color.WHITE);
   
   
            
   projectTable.setRowHeight(20);
   projectTable.setGridColor(Color.DARK_GRAY);
   projectTable.setSelectionBackground(Color.LIGHT_GRAY);
   
    ((DefaultTableCellRenderer)projectTable.getDefaultRenderer(Object.class)).setBackground(Color.DARK_GRAY);
    
    

    sp.getViewport().setOpaque(false);
    
    
    listener();
    

    
      branchOP.addActionListener((ActionEvent ae) -> {

      branchOption=branchOption+1;
      if(branchOption%2==0){
          
      branchOP.setText("ON");
          branchName.setFocusable(false);
          branchName.setBackground(Color.GRAY);
          branchName.setText("Branch Name");
      
      }else{
       branchOP.setText("Off");
      branchName.setFocusable(true);
      branchName.setBackground(Color.WHITE);
      }
      
      });
    
    
    if(admin==false){
        
        delU.setVisible(false);
        crU.setVisible(false);
        changeDeg.setVisible(false);
        
    }
    
    logout.addActionListener((ActionEvent ae) -> {

         ALD frame;
                  try {
                      frame = new ALD();
                       frame.setVisible(true);
                  } catch (FontFormatException | IOException ex) {
                      Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                  }
        
         
         dispose();
            

    });
    
       crU.addActionListener((ActionEvent ae) -> {

         
            
             new CreateUser().show();
            

    });
       
       
       
   delU.addActionListener((ActionEvent ae) -> {

           
        try {
          
            
            new DeleteUser().show();
            
        } catch ( ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
   
            

    });
   
          
   edit.addActionListener((ActionEvent ae) -> {

       
       
           
                  try {
                   
                       new Edit(uid.getText()).show();
                       
                       
                  } catch (ClassNotFoundException | SQLException ex) {
                      Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                  }
           
   
            

    });
   
   
   
           
     createPro.addActionListener((ActionEvent ae) -> {

         DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
         String branchNa;
         if (!branchName.isFocusable()|| branchName.getText().equals("Branch Name")){
         
        branchNa = "NULL";
         
         }else{
         
         branchNa = branchName.getText();
         
         } 
                  try {
                      
                      if(proName.getText().equals("Project Name") || comBankName.getText().equals("Company / Bank Name") || proAdd.getText().equals("Project Address")){
                      
                      JOptionPane.showMessageDialog(null, "Please fill up required Fields");
                      
                      }else{
                      
                                   myData r = new myData();
                      r.projectAdding(proName.getText(), comBankName.getText(), branchNa, proAdd.getText()
                              ,df.format(date.getDate()));
                      
                      projectListLoader();
                       DefaultTableModel dm1= new DefaultTableModel(row,col);
                       projectTable.setModel(dm1);
                      
                      
                      
                      
                      }
         
                      
                      
                  } catch (ClassNotFoundException | SQLException ex) {
                      Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                  }
           
   
            

    });
    
    
    }

 
    @Override
    public void focusGained(FocusEvent fe) {
      
    if(fe.getSource()==projectSearch){
        
            
            if("SEARCH".equals(projectSearch.getText())){
            
            projectSearch.setText("");
                
            }
            
        
        }
    
        if(fe.getSource()==proName){
        
            
            if("Project Name".equals(proName.getText())){
            
            proName.setText("");
                
            }
            
        
        }
        
            if(fe.getSource()==branchName){
        
            
            if("Branch Name".equals(branchName.getText())){
            
            branchName.setText("");
                
            }
            
        
        }
      if(fe.getSource()==proAdd){
        
            
            if("Project Address".equals(proAdd.getText())){
            
            proAdd.setText("");
                
            }
            
        
        }
      
              if(fe.getSource()==comBankName){
        
            
            if("Company / Bank Name".equals(comBankName.getText())){
            
            comBankName.setText("");
                
            }
            
        
        }
              
              
    
      

    
    }

    @Override
    public void focusLost(FocusEvent fe) {
        
              if(fe.getSource()==projectSearch){
        
            
            if("".equals(projectSearch.getText())){
            
            projectSearch.setText("SEARCH");
                
            }
            
        
        }
        
      
        
        if(fe.getSource()==proName){
        
        if("".equals(proName.getText())){
        
        proName.setText("Project Name");
        
        }
        

        
        }
        
      if(fe.getSource()==branchName){
        
            
            if("".equals(branchName.getText())){
            
            branchName.setText("Branch Name");
                
            }
            
        
        }
      
            if(fe.getSource()==proAdd){
        
            
            if("".equals(proAdd.getText())){
            
            proAdd.setText("Project Address");
                
            }
            
        
        }
            
                          if(fe.getSource()==comBankName){
        
            
            if("".equals(comBankName.getText())){
            
            comBankName.setText("Company / Bank Name");
                
            }
            
        
        }
    
        
    }
    
    private void picLoad() throws ClassNotFoundException, SQLException   {
            
        myData o = new myData();
        boolean c = o.loadImage(uid.getText());
        if(c==false){
        
            userPic.setBackground(Color.BLUE);

        
        }
        
    }
    
    private void homePageLoader() throws ClassNotFoundException, SQLException{
    
    originArray = new double[3];
        
    myData  c = new myData();
    originArray = c.homePageLoaderForAccount();

    rwv = originArray[0];
    
    
    colCash = originArray[1];
    rewv = rwv-colCash;
    
    comDeb=originArray[2];
    comCre = colCash-comDeb;
    
    projectListLoader();
    
    }
    
    
    public void projectListLoader(){
    
        try {
            myData co = new myData();
            row = co.projectListLoader();
            
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
    
    }

    @Override
    public void mouseClicked(MouseEvent me) {

                if(me.getSource()==workpnl){
       
           
            workpnl.requestFocus(true);
            
        
        }
                
                
               
             if(me.getSource()==createProject){
       
           
            createProject.requestFocus(true);
            
        
        }   
             
      if(me.getSource()==searchpnl){
       
           
            searchpnl.requestFocus(true);
            
        
        } 
      
       if(me.getSource()==sp){
       
           
            searchpnl.requestFocus(true);
            
        
        }
       
       if(me.getSource()==projectTable){
       
           
            projectTable.requestFocus(true);
            
        
        }
                
        

    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
        if(me.getSource()==searchpnl || me.getSource()==projectSearch
                || me.getSource()==sp || me.getSource()==projectTable || me.getSource()==projectTable.getTableHeader()){
        
   
            
              extended();
        
        
        }    
        
    
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
    

        
           if(me.getSource()==searchpnl){
        
               
             if(sp.getMousePosition()==null || projectTable.getMousePosition()==null || projectTable.getTableHeader().getMousePosition()==null || projectSearch.getMousePosition()==null){
               
                unextended();

                   
               
               }

            
        
        
        }
           
           if(me.getSource()==sp || me.getSource()==projectTable || me.getSource()==projectTable.getTableHeader() || me.getSource()==projectSearch){
           
               if(searchpnl.getMousePosition()==null){
               
                unextended();

                   
               
               }

 
           
           }
           

    
        
    
    }
    
    public void unextended(){
    
            
            searchpnl.setBounds(0,30,20,workpnl.getHeight()-120);

                darkui.setBounds(searchpnl.getX()+5,searchpnl.getY()+5,
            searchpnl.getWidth(),searchpnl.getHeight());
                
    
    projectSearch.setBounds(-(projectSearch.getWidth()+6),projectSearch.getY(),projectSearch.getWidth(),projectSearch.getHeight());
    
        darkui2.setBounds(projectSearch.getX()+5,projectSearch.getY()+5,
            projectSearch.getWidth(),projectSearch.getHeight());
        
                   sp.setBounds(-(sp.getWidth()+30),darkui2.getY()+darkui2.getHeight()+30,
                           searchpnl.getWidth(),
            searchpnl.getHeight()-100);
                   
                
    
                projectTable.getTableHeader().setBounds(-(projectTable.getTableHeader().getWidth()+30),projectTable.getTableHeader().getY(),
                           projectTable.getTableHeader().getWidth(),
            projectTable.getTableHeader().getHeight()-100);
                   
    
    }
    
   public void extended(){
   
       
          
          searchpnl.setBounds(0,30,280,workpnl.getHeight()-120);

              darkui.setBounds(searchpnl.getX()+5,searchpnl.getY()+5,
            searchpnl.getWidth(),searchpnl.getHeight());
                
       
    projectSearch.setVisible(true); 
    darkui2.setVisible(true);
    sp.setVisible(true);
    
   projectSearch.setBounds(4,4,searchpnl.getWidth()-20,35);
   
       darkui2.setBounds(projectSearch.getX()+5,projectSearch.getY()+5,
            projectSearch.getWidth(),projectSearch.getHeight());
       
           sp.setBounds(2,darkui2.getY()+darkui2.getHeight()+30,searchpnl.getWidth(),
            searchpnl.getHeight()-100);
           
                        projectTable.getTableHeader().setBounds(0,projectTable.getTableHeader().getY(),
                           projectTable.getTableHeader().getWidth(),
            projectTable.getTableHeader().getHeight()-100);
   
   
   
   }
   
   private void listener(){
   
       projectTable.getTableHeader().addMouseListener(this);
       projectTable.addMouseListener(this);
     sp.addMouseListener(this);
   projectSearch.addMouseListener(this);
    searchpnl.addMouseListener(this);
    workpnl.addMouseListener(this);
    createProject.addMouseListener(this);
    sp.addMouseListener(this);
    
    
    
    projectSearch.addFocusListener(this);
    proName.addFocusListener(this);
    branchName.addFocusListener(this);
    proAdd.addFocusListener(this);
    createProject.addFocusListener(this);
    comBankName.addFocusListener(this);
    
    
    projectSearch.addKeyListener(this);
     
   
   }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getSource()==projectSearch){
        
         String a = projectSearch.getText().toLowerCase();
            
    DefaultTableModel table = (DefaultTableModel)projectTable.getModel();
    TableRowSorter<DefaultTableModel>tr = new TableRowSorter<>(table);
    projectTable.setRowSorter(tr);
    tr.setRowFilter(RowFilter.regexFilter(a));
        
        }
    
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
   

private void UILoader(){

    
               
              UIManager.put("OptionPane.background", Color.WHITE);
              UIManager.put("OptionPane.border", null);
              UIManager.put("OptionPane.messageForeground", Color.BLACK);
                UIManager.put("Panel.background", Color.WHITE);
                UIManager.put("Button.background", Color.WHITE);
                UIManager.put("Button.foreground", Color.BLACK);
                
                
                try{
                
                    Font segoeui = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("src/font/SEGOEUI.TTF"));
                    Font segoeuiblack = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("src/font/seguibl.ttf"));

                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(segoeui);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(segoeuiblack);
                
                }catch(FontFormatException | IOException ae){
                
                 JOptionPane.showMessageDialog(null, "Could not load Fonts\nShowing Default Fonts");

                
                }

}


    
}
