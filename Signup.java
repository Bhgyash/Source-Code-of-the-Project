
package electicity.billing.system;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ItemListener;
import java.sql.*;

public class Signup extends JFrame implements ActionListener{
    JButton create,back;
    Choice accountType;
    JTextField meter, username,name, password;
    Signup(){
    
    setBounds(450,150,700,400);
    getContentPane().setBackground(Color.WHITE);
    setLayout(null);
    
    JPanel panel=new JPanel();
    panel.setBounds(30,5,650,350);
    panel.setBorder(new TitledBorder(new LineBorder(Color.BLACK),"Create Account"));
    panel.setLayout(null);
    panel.setForeground(new Color(34,139,34));
    add(panel);
    
      JLabel heading= new JLabel("CREATE  ACCOUNT AS");
      heading.setBounds(100,50,140,20);
      heading.setFont(new Font("Tahoma",Font.BOLD, 11));
      panel.add(heading);
      
      JLabel lblmeter= new JLabel("METER NUMBER");
      lblmeter.setBounds(100,90,140,20);
      lblmeter.setFont(new Font("Tahoma",Font.BOLD, 11));
      lblmeter.setVisible(false);
      panel.add(lblmeter);
      
      meter=new JTextField();
      meter.setBounds(260,90,150,20);
      meter.setVisible(false);
      panel.add(meter);
      
      
      JLabel lbluser= new JLabel("USERNAME");
      lbluser.setBounds(100,130,140,20);
      lbluser.setFont(new Font("Tahoma",Font.BOLD, 11));
      panel.add(lbluser);
      
      username=new JTextField();
      username.setBounds(260,130,150,20);
      panel.add(username);
    
      JLabel lblname= new JLabel("NAME");
      lblname.setBounds(100,170,140,20);
      lblname.setFont(new Font("Tahoma",Font.BOLD, 11));
      panel.add(lblname);
      
      name=new JTextField();
      name.setBounds(260,170,150,20);
      panel.add(name);
      
      meter.addFocusListener(new FocusListener(){
          @Override
          public void focusGained(FocusEvent fe){}
              
          @Override
          public void focusLost(FocusEvent fe){
          try{
              Conn c=new Conn();
              ResultSet rs= c.s.executeQuery("select * from login where meter_no= '"+meter.getText()+"'");
         while(rs.next()){
             
             name.setText(rs.getString("name"));
         }
              
              
          }catch(Exception e){
              e.printStackTrace();
          }
          }
          
          
      });
      
      
      JLabel lblpassword= new JLabel("PASSWORD");
      lblpassword.setBounds(100,210,140,20);
      lblpassword.setFont(new Font("Tahoma",Font.BOLD, 11));
      panel.add(lblpassword);
      
      password=new JTextField();
      password.setBounds(260,210,150,20);
      panel.add(password);
      
      accountType=new Choice();
    accountType.add("Admin");
    accountType.add("Customer");
    accountType.setBounds(260,50,150,20);
    panel.add(accountType);
      
      
      accountType.addItemListener(new ItemListener(){
          public void itemStateChanged(ItemEvent ae){
              String user=accountType.getSelectedItem();
              if(user.equals("Customer")){
                  lblmeter.setVisible(true);
                  meter.setVisible(true);
                  name.setEditable(false);
              }else{
                  lblmeter.setVisible(false);
                  meter.setVisible(false);
                  name.setEditable(true);
              }
          }
      });
      
      
      create=new JButton("CREATE");
      create.setBackground(Color.BLACK);
      create.setForeground(Color.WHITE);
     create.setBounds(140,260,120,25);
     create.addActionListener(this);
     panel.add(create);
      
     
     back=new JButton("BACK");
      back.setBackground(Color.BLACK);
      back.setForeground(Color.WHITE);
     back.setBounds(300,260,120,25);
     back.addActionListener(this);
     panel.add(back);
      
      
    ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
    Image i2=i1.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT);
    ImageIcon i3=new ImageIcon(i2);
    JLabel image=new JLabel(i3);
    image.setBounds(415,30,250,250);
    panel.add(image);
      
      
      
    
    
    
   
    
    setVisible(true);
    }
           
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==create){
            String atype=accountType.getSelectedItem();
            String susername=username.getText();
            String sname=name.getText();
            String spassword= password.getText();
            String smeter= meter.getText();
            
            
            try{
                Conn  c=new Conn();
                
                String query= null;
                if(atype.equals("Admin")){
                query="insert into login values('"+smeter+"' ,'"+susername+"' ,'"+sname+"','"+spassword+"' ,'"+atype+"')";
                }else{
                    query="update login set username= '"+susername+"', password='"+spassword+"', user='"+atype+"' where meter_no='"+smeter+"'";
                }
                c.s.executeUpdate(query);
             
             JOptionPane.showMessageDialog(null,"Account Created Successfully");
             
             setVisible(false);
             new Login();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==back){
            setVisible(false);  
            
            new Login();
        }
    }
            
public static void main(String args[]) {
        new Signup();
}
}
