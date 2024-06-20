
package ald;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class DeleteUser extends JFrame implements KeyListener {
    
    JTextField src;
    JButton srcB,showAll,dlt;
    
    JScrollPane sp;
    
    JTable tbl;
    
    JLabel txt;
    
    Font fn,fd,fu,ft;
    
    String[] col={"Serial","User Name","Full Name","Designation"};
    String[] [] row;
    
    String user;
    String serial;
    
    
    DeleteUser() throws ClassNotFoundException, SQLException{
    
    try{
        
   
        
    myData m = new myData();
    row = m.loadUser(); 
    

    
       m.rs.close();
         m.st.close();
         m.ps.close();
            
    
    }catch(Exception aa){}
        

       
        
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    
    ft = new Font("Segoe UI ",Font.PLAIN, 14);
    fn = new Font("Segoe UI ",Font.PLAIN, 16);
    fu = new Font("Segoe UI Black",Font.PLAIN, 18);
    fd = new Font("Segoe UI Black",Font.HANGING_BASELINE, 28);
 
    setTitle("Delete Users");
    
    setBounds((int) (screen.getWidth()/2)-250, 
       (int) (screen.getHeight()/2)-275,500,450);
    setResizable(false);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    this.setLayout(null);
    
    src = new JTextField();
    src.setFont(fn);
    src.setBounds(5,5,this.getWidth()-200,25);
    this.add(src);
    
    src.addKeyListener(this);
    
    srcB = new JButton("SEARCH");
    srcB.setFont(fn);
    srcB.setBounds(src.getWidth()+50,5,120,25);
    this.add(srcB);
    
    showAll = new JButton("SHOW ALL");
    showAll.setFont(fn);
    showAll.setBounds(src.getWidth()+50,srcB.getHeight()+20,120,25);
    this.add(showAll);
    
    dlt = new JButton("DELETE");
    dlt.setFont(fn);
    dlt.setBounds(src.getWidth()+50,srcB.getHeight()+60,120,25);
    this.add(dlt);
    
    txt = new JLabel("USERS:");
    txt.setFont(fd);
    txt.setBounds(src.getX(),src.getHeight()+50,150,50);
    this.add(txt);
    
    
    
    tbl = new JTable(){
    
    @Override
    public boolean isCellEditable(int rowIndex,int colIndex){
    
    return false;
    
    }
    
    };
    
    
    
    DefaultTableModel dm= new DefaultTableModel(row,col);
    
    tbl.setModel(dm);
    tbl.setFont(ft);
    
    
    sp = new JScrollPane(tbl);
    sp.setBounds(src.getX(),src.getHeight()+100,this.getWidth()-15,150);
    this.add(sp);
    
   
    
    srcB.addActionListener((ActionEvent ae)->{
    
    String a = src.getText().toLowerCase();
    
    DefaultTableModel table = (DefaultTableModel)tbl.getModel();
    TableRowSorter<DefaultTableModel>tr = new TableRowSorter<DefaultTableModel>(table);
    tbl.setRowSorter(tr);
    tr.setRowFilter(RowFilter.regexFilter(a));
    
    
    
    
    });
    
    
    showAll.addActionListener((ActionEvent ae)->{
    
      DefaultTableModel table = (DefaultTableModel)tbl.getModel();
    TableRowSorter<DefaultTableModel>tr = new TableRowSorter<DefaultTableModel>(table);
    tbl.setRowSorter(tr);
    tr.setRowFilter(RowFilter.regexFilter(""));
        
    
    
    });
    
     dlt.addActionListener((ActionEvent ae)->{
         

    
         try{
   
          DefaultTableModel table = (DefaultTableModel)tbl.getModel();
          int SelectedRowIndex = tbl.getSelectedRow();
           user = tbl.getValueAt(SelectedRowIndex, 1).toString();
              dm.removeRow(SelectedRowIndex);
           
       
             
           
         
         }catch(Exception ex){
         
             JOptionPane.showMessageDialog(null, "No user is selected");
         
         }
         
   
        try {
            myData m = new myData();
           
            m.deleteUser(user);
            
   
            
            m.con.close();
            m.ps.close();
            
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DeleteUser.class.getName()).log(Level.SEVERE, null, ex);
        }
           
         
     

      
            
    });
    
     
     

    
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {

        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
        
            
    String a = src.getText().toLowerCase();
    
    DefaultTableModel table = (DefaultTableModel)tbl.getModel();
    TableRowSorter<DefaultTableModel>tr = new TableRowSorter<DefaultTableModel>(table);
    tbl.setRowSorter(tr);
    tr.setRowFilter(RowFilter.regexFilter(a));
    
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
        public static void sleep(int time){
    
    
    
        try{
        
        Thread.sleep(time);
        
        }catch(Exception c){
        
        
        }
    
    }

}
