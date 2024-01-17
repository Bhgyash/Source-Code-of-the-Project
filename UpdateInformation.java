
package electicity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateInformation extends JFrame implements ActionListener{
    
    JTextField tfaddress,tfcity,tfstate,tfemail,tfphone;
    JButton update,cancel;
    JLabel name;
    String meter;
    
    UpdateInformation (String meter){
        this.meter=meter;
        setBounds(300,150,900,450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
    JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
    heading.setBounds(110,20,400, 30);
    heading.setFont(new Font("Tahoma", Font.PLAIN,20));
    add(heading);
    
     JLabel lblname= new JLabel("Name");
    lblname.setBounds(30,80,100, 20);
    add(lblname);
    
    
     name= new JLabel("");
    name.setBounds(230,80,140, 20);
    add(name);
    
    JLabel lblmeternumber= new JLabel("Meter Number");
    lblmeternumber.setBounds(30,120,100, 20);
    add(lblmeternumber);
    
    
    JLabel meternumber= new JLabel("");
    meternumber.setBounds(230,120,100, 20);
    add(meternumber);
    
    
    JLabel lbladdress= new JLabel("Address");
     lbladdress.setBounds(30,160,100, 20);
    add( lbladdress);
    
    tfaddress= new JTextField();
     tfaddress.setBounds(230,160,140, 20);
    add(tfaddress);
    
     JLabel lblcity= new JLabel("City");
     lblcity.setBounds(30,200,100, 20);
    add( lblcity);
    
    tfcity = new JTextField();
     tfcity.setBounds(230,200,140, 20);
    add(tfcity);
    
    JLabel lblstate= new JLabel("State");
     lblstate.setBounds(30,240,100, 20);
    add( lblstate);
    
     tfstate= new JTextField();
     tfstate.setBounds(230,240,140, 20);
    add( tfstate);
    
     JLabel lblemail= new JLabel("Email");
     lblemail.setBounds(30,280,100, 20);
    add( lblemail);
    
    tfemail= new JTextField();
     tfemail.setBounds(230,280,140, 20);
    add(tfemail);
    
    JLabel lblphone= new JLabel("Phone");
     lblphone.setBounds(30,320,100, 20);
    add( lblphone);
    
    tfphone= new JTextField();
     tfphone.setBounds(230,320,140, 20);
    add(tfphone);
    
    try{
    Conn c=new Conn();
    ResultSet rs=c.s.executeQuery("select * from client where meter_no= '"+meter+"'");
    while(rs.next()){
    name.setText(rs.getString("name"));
      tfaddress.setText(rs.getString("address"));
      tfcity.setText(rs.getString("city"));
      tfstate.setText(rs.getString("state"));
   tfemail.setText(rs.getString("email"));
   tfphone.setText(rs.getString("phone"));
    meternumber.setText(rs.getString("meter_no"));
    }
    
    }catch (Exception e){
     e.printStackTrace();
   }
    
    
    update= new JButton("Update");
  update.setBackground(Color.BLACK);
 update.setForeground(Color.WHITE);
 update.setBounds(70,360,100,25);
  add(update);
  update.addActionListener(this);
    
  cancel= new JButton("Cancel");
  cancel.setBackground(Color.BLACK);
 cancel.setForeground(Color.WHITE);
 cancel.setBounds(230,360,100,25);
 add(cancel);
  cancel.addActionListener(this);
    
    
    ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
    Image i2=i1.getImage().getScaledInstance(300,200,Image.SCALE_DEFAULT);
    ImageIcon i3=new ImageIcon(i2);
    JLabel image=new JLabel(i3);
    image.setBounds(500,100,300,200);
    add(image);
    
    
        
        setVisible(true);
        
     }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==update){
            String labelname=name.getText();
            String address= tfaddress.getText();
            String city=tfcity.getText();
            String state=tfstate.getText();
            String email=tfemail.getText();
            String phone=tfphone.getText();
            
           try{
               Conn c=new Conn();
               c.s.executeUpdate("update client set address='"+address+"',city= '"+city+"', state='"+state+"', email='"+email+"', phone='"+phone+"' where meter_no='"+meter+"'");
           
           JOptionPane.showMessageDialog(null,"User Information Updated Successfully");
           setVisible(false);
           } catch(Exception e){
               e.printStackTrace();
           }
            
            
        }else{
           setVisible(true); 

        }
    }
   
    public static void main(String args[]){
        new UpdateInformation("");
    }
}
