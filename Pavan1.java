import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;

public class Pavan1 implements ActionListener
{

    int tt1,tt2,tt3,tt4,tt5,tt6,tt7,tt8,tt9,tt10,tt11;
    String log;

    public  JFrame home()
    {
        JFrame f = new JFrame("Home");
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        try {
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/SAI RAMYA/Downloads/bg1.jpg")))));
        } catch (IOException e1) {
           
            e1.printStackTrace();
        }
    
        JLabel ll1 = new JLabel(" LOGIN PAGE");
        ll1.setBounds(150, 90, 90, 30);
        ll1.setForeground(Color.RED);
        ll1.setBackground(Color.white); 
        ll1.setOpaque(true);
        f.add(ll1);
        JLabel l1 = new JLabel(" Mobile_number:");
        l1.setBounds(50, 150, 100, 30);
        l1.setForeground(Color.black);
        l1.setBackground(Color.white); 
        l1.setOpaque(true);
        f.add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(200, 150, 100, 30);
        f.add(t1);

        JButton b1 = new JButton("Login");
        b1.setBounds(50, 250, 100, 30);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            
              
                try
                {
                    Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/kiran","root","kiran#123"); 
                    System.out.println("conected");
                    Statement s=conn.createStatement();

                    String mbno = t1.getText();
                    String sql = "SELECT * FROM customersdetails WHERE mobileno = ?";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, mbno);
                    ResultSet result = statement.executeQuery();
                    if (result.next())
                    {
                        f.dispose();
                        JFrame frame = homepage();
                        frame.setVisible(true);
                        log = t1.getText();
                       
                        
                      

                    }
                    else
                    {
                        String a=t1.getText();
                        if (a.equals(""))
                        {
                            JOptionPane.showMessageDialog(f,"please Enter your number and login"); 
                        }
                        else{

                            System.out.println("user not found plz register"); 
                            JOptionPane.showMessageDialog(f,"user not found plz register");  
                            t1.setText("");
                        }
                         
                    }
                }
                catch(SQLException a)
                {
                    System.out.println(a);
                }
                      
            }
        });
        f.add(b1);
        f.setResizable(false);
        f.setVisible(true);

        JButton b2 = new JButton("Register");
        b2.setBounds(200, 250, 100, 30);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                JFrame frame = Register();
                frame.setVisible(true);
                

            }
        });
        f.add(b2);
        f.setResizable(false);
        f.setVisible(true);
    
        return f;
    }
    
     JFrame Register()
    {
        JFrame f = new JFrame("Register"+ SwingConstants.CENTER);
        try {
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/SAI RAMYA/Downloads/bg1.jpg")))));
        } catch (IOException e1) {
           
            e1.printStackTrace();
        }
    
        JLabel ll2 = new JLabel("SIGNUP PAGE");
        ll2.setBounds(140, 70, 90, 30);
        ll2.setForeground(Color.red);
        ll2.setBackground(Color.white);
        ll2.setOpaque(true); 
        f.setResizable(false);
        f.add(ll2);

        JLabel l2 = new JLabel("Enter your Name:");
        l2.setBounds(50, 120, 100, 30);
        l2.setForeground(Color.black);
        l2.setBackground(Color.white);
        l2.setOpaque(true); 
        f.setResizable(false);
        f.add(l2);

        JTextField t2 = new JTextField();
        t2.setBounds(200, 120, 100, 30);
        f.add(t2);

        JLabel l3 = new JLabel("Enter Mobile_number:");
        l3.setBounds(50, 170, 130, 30);
        l3.setForeground(Color.black);
        l3.setBackground(Color.white); 
        l3.setOpaque(true);
        f.setResizable(false);
        f.add(l3);

        JTextField t3 = new JTextField();
        t3.setBounds(200, 170, 100, 30);
        f.add(t3);

        JButton b3 = new JButton("Register");
        b3.setBounds(150, 250, 100, 30);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                
                try
                {
                    Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/kiran","root","kiran#123");
                    System.out.println("conected");
                    Statement s=conn.createStatement();
                    //s.execute("create table customersdetails(name varchar(20),mobileno varchar(20))");
                    String name = t2.getText();
                    String mbno = t3.getText();
                    //check for null values if any stay in same page
                    if(name.equals("") || mbno.equals(""))
                    {
                        System.out.println("null values");
                        JOptionPane.showMessageDialog(f,"please fill the details"); 


                        }
                    else

                    {
                        String sql = "INSERT INTO customersdetails (name, mobileno) VALUES (?, ?)";
                        PreparedStatement statement = conn.prepareStatement(sql);
                        statement.setString(1, name);
                        statement.setString(2, mbno);
                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("A new user was inserted successfully!");
                        }
                        f.dispose();
                        JFrame frame = home();
                        frame.setVisible(true);
                        JOptionPane.showMessageDialog(f,"user successfully registered"); 
                    }
                }
                catch(SQLException b)
                {
                    System.out.println(b);
                }

                
                

            }
        });
        f.add(b3);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return f;
    }
    
     JFrame homepage()
    {
              

      JFrame f = new JFrame("homepage");
      
      JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.setPreferredSize(new Dimension(440,2500)); // set preferred size to enable scrolling
  
      
  
      // add all components to the panel
      JLabel l55 = new JLabel("Welcome to the homepage!");
      l55.setBounds(100, 5, 300, 30);
      panel.add(l55);

      JLabel ll = new JLabel("----------------------------------------------------------------------------------------------------------");
        ll.setBounds(0, 20, 450, 10);
        panel.add(ll);
        //product 1 details.      
        ImageIcon icon = new ImageIcon("C:/Users/SAI RAMYA/Downloads/rice.jpg");
        JLabel li1 = new JLabel(icon);
        li1.setBounds(50, 50, icon.getIconWidth(), icon.getIconHeight());
        panel.add(li1);
        JLabel l4 = new JLabel("Biryani Rice");
        l4.setBounds(250, 110, 100, 50);
        panel.add(l4);
        JLabel l5 = new JLabel("₹450  18%off");
        l5.setBounds(250, 130, 100, 50);
        panel.add(l5);
        JLabel l6 = new JLabel("5 kg");
        l6.setBounds(250, 160, 100, 30);
        panel.add(l6);
        JTextField t4 = new JTextField("0");    
        t4.setBounds(300, 200, 15, 20);
        t4.setEditable(false);
        panel.add(t4);

        JButton b4 = new JButton("-");
        b4.setBounds(250, 200, 40, 20);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t4.getText());
                if(x>0)
                {
                    x--;
                    t4.setText(String.valueOf(x));
                    
                }
            }});    
        panel.add(b4);
        
        JButton b5 = new JButton("+");
        b5.setBounds(325, 200, 50, 20);
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t4.getText());
                x++;
                t4.setText(String.valueOf(x));
                
   
            }});
            panel.add(b5);      
        JLabel ll1 = new JLabel("----------------------------------------------------------------------------------------------------------");
        ll1.setBounds(0, 270, 450, 10);
        panel.add(ll1);  
    
        //product 2 details.
        ImageIcon icon1 = new ImageIcon("C:/Users/SAI RAMYA/Downloads/dates.jpg");
        JLabel li2 = new JLabel(icon1);
        li2.setBounds(50, 300, icon1.getIconWidth(), icon1.getIconHeight());
        panel.add(li2);
        JLabel l7 = new JLabel("Lion Dates");
        l7.setBounds(250, 320, 100, 50);
        panel.add(l7);
        JLabel l8 = new JLabel("₹200  5%off");
        l8.setBounds(250, 340, 100, 50);
        panel.add(l8);
        JLabel l9 = new JLabel("500 gms");
        l9.setBounds(250, 360, 100, 50);
        panel.add(l9);
        JTextField t5 = new JTextField("0");
        t5.setBounds(300, 400, 15, 20);
        t5.setEditable(false);
        panel.add(t5);
        JButton b6 = new JButton("-");
        b6.setBounds(250, 400, 40, 20);
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t5.getText());
                if(x>0)
                {
                    x--;
                    t5.setText(String.valueOf(x));
                }

            }});
        panel.add(b6);

        JButton b7 = new JButton("+");
        b7.setBounds(325, 400, 50, 20);
        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t5.getText());
                x++;
                t5.setText(String.valueOf(x));
    
            }});
        panel.add(b7); 

        JLabel ll2 = new JLabel("----------------------------------------------------------------------------------------------------------");
        ll2.setBounds(0, 460, 450, 10);
        panel.add(ll2);
         
        //product 3 details.
        ImageIcon icon2 = new ImageIcon("C:/Users/SAI RAMYA/Downloads/cashew.jpg");
        JLabel li3 = new JLabel(icon2);
        li3.setBounds(50, 480, icon2.getIconWidth(), icon2.getIconHeight());
        panel.add(li3);
        JLabel l10 = new JLabel("cashew");
        l10.setBounds(250, 520, 100, 50);
        panel.add(l10);
        JLabel l11 = new JLabel("₹800  12%off");
        l11.setBounds(250, 540, 100, 50);
        panel.add(l11);
        JLabel l12 = new JLabel("1 kg");
        l12.setBounds(250, 560, 100, 50);
        panel.add(l12);
        JTextField t6 = new JTextField("0");
        t6.setBounds(300, 600, 15, 20);
        t6.setEditable(false);
        panel.add(t6);
        JButton b8 = new JButton("-");
        b8.setBounds(250, 600, 40, 20);
        b8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t6.getText());
                if(x>0)
                {
                    x--;
                    t6.setText(String.valueOf(x));
                }

            }});
        panel.add(b8);

        JButton b9 = new JButton("+");
        b9.setBounds(325, 600, 50, 20);
        b9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t6.getText());
                x++;
                t6.setText(String.valueOf(x));
  
            }});
        panel.add(b9); 
        JLabel ll3 = new JLabel("----------------------------------------------------------------------------------------------------------");
        ll3.setBounds(0, 670, 450, 10);
        panel.add(ll3);

      
    
        
        //product 4 details.
        ImageIcon icon3 = new ImageIcon("C:/Users/SAI RAMYA/Downloads/pista (1).jpg");
        JLabel li4 = new JLabel(icon3);
        li4.setBounds(50, 690, icon3.getIconWidth(), icon3.getIconHeight());
        panel.add(li4);
        JLabel l13 = new JLabel("pista");
        l13.setBounds(250, 730, 100, 50);
        panel.add(l13);
        JLabel l14 = new JLabel("₹1200  12%off");
        l14.setBounds(250, 750, 100, 50);
        panel.add(l14);
        JLabel l15 = new JLabel("1 kg");
        l15.setBounds(250, 770, 100, 50);
        panel.add(l15);
        JTextField t7 = new JTextField("0");
        t7.setBounds(300, 810, 15, 20);
        t7.setEditable(false);
        panel.add(t7);
        JButton b10 = new JButton("-");
        b10.setBounds(250, 810, 40, 20);
        b10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t7.getText());
                if(x>0)
                {
                    x--;
                    t7.setText(String.valueOf(x));
                }

            }});
        panel.add(b10);

        JButton b11 = new JButton("+");
        b11.setBounds(325, 810, 50, 20);
        b11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t7.getText());
                x++;
                t7.setText(String.valueOf(x));
    
                
            }});
        panel.add(b11); 
        JLabel ll4 = new JLabel("----------------------------------------------------------------------------------------------------------");
        ll4.setBounds(0, 900, 450, 10);
        panel.add(ll4);


        //product 5 details.
        ImageIcon icon4 = new ImageIcon("C:/Users/SAI RAMYA/Downloads/copper (3).jpeg");
        JLabel li5 = new JLabel(icon4);
        li5.setBounds(30, 920, icon4.getIconWidth(), icon4.getIconHeight());
        panel.add(li5);
        JLabel l16 = new JLabel("copper bottle");
        l16.setBounds(250, 940, 100, 50);
        panel.add(l16);
        JLabel l17 = new JLabel("₹900  12%off");
        l17.setBounds(250, 960, 100, 50);
        panel.add(l17);
        JLabel l18 = new JLabel("1000ml");
        l18.setBounds(250, 980, 100, 50);
        panel.add(l18);
        JTextField t8 = new JTextField("0");
        t8.setBounds(300, 1020, 15, 20);
        t8.setEditable(false);
        panel.add(t8);
        JButton b12 = new JButton("-");
        b12.setBounds(250, 1020, 40, 20);
        b12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t8.getText());
                if(x>0)
                {
                    x--;
                    t8.setText(String.valueOf(x));
                }

            }});
        panel.add(b12);

        JButton b13 = new JButton("+");
        b13.setBounds(325, 1020, 50, 20);
        b13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t8.getText());
                x++;
                t8.setText(String.valueOf(x));
    
                
            }});
        panel.add(b13); 
        JLabel ll9 = new JLabel("----------------------------------------------------------------------------------------------------------");
        ll9.setBounds(0, 1110, 450, 10);
        panel.add(ll9);


            //product 6 details.
        ImageIcon icon5 = new ImageIcon("C:/Users/SAI RAMYA/Downloads/sweater (1).jpg");
        JLabel li6 = new JLabel(icon5);
        li6.setBounds(50, 1150, icon5.getIconWidth(), icon5.getIconHeight());
        panel.add(li6);
        JLabel l20 = new JLabel("sweater");
        l20.setBounds(250, 1150, 100, 50);
        panel.add(l20);
        JLabel l21 = new JLabel("₹700  12%off");
        l21.setBounds(250, 1170, 100, 50);
        panel.add(l21);
        JLabel l22 = new JLabel("size M");
        l22.setBounds(250, 1190, 100, 50);
        panel.add(l22);
        JTextField t9 = new JTextField("0");
        t9.setBounds(300, 1240, 15, 20);
        t9.setEditable(false);
        panel.add(t9);
        JButton b14 = new JButton("-");
        b14.setBounds(250, 1240, 40, 20);
        b14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t9.getText());
                if(x>0)
                {
                    x--;
                    t9.setText(String.valueOf(x));
                }

            }});
        panel.add(b14);
    
        JButton b15 = new JButton("+");
        b15.setBounds(325, 1240, 50, 20);
        b15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t9.getText());
                x++;
                t9.setText(String.valueOf(x));
    
                
            }});
        panel.add(b15); 
        JLabel l23 = new JLabel("----------------------------------------------------------------------------------------------------------");
        l23.setBounds(0, 1290, 450, 10);
        panel.add(l23);



            
        //product 7 details.
        ImageIcon icon6 = new ImageIcon("C:/Users/SAI RAMYA/Downloads/fogg (1).jpg");
        JLabel li7 = new JLabel(icon6);
        li7.setBounds(50, 1310, icon6.getIconWidth(), icon6.getIconHeight());
        panel.add(li7);
        JLabel l24 = new JLabel("fogg perfume");
        l24.setBounds(250, 1330, 100, 50);
        panel.add(l24);
        JLabel l25 = new JLabel("₹1100  12%off");
        l25.setBounds(250, 1350, 100, 50);
        panel.add(l25);
        JLabel l26 = new JLabel("pack of 4");
        l26.setBounds(250, 1370, 100, 50);
        panel.add(l26);
        JTextField t10 = new JTextField("0");
        t10.setBounds(300, 1430, 15, 20);
        t10.setEditable(false);
        panel.add(t10);
        JButton b16 = new JButton("-");
        b16.setBounds(250, 1430, 40, 20);
        b16.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t10.getText());
                if(x>0)
                {
                    x--;
                    t10.setText(String.valueOf(x));
                }

            }});
        panel.add(b16);
    
        JButton b17 = new JButton("+");
        b17.setBounds(325, 1430, 50, 20);
        b17.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t10.getText());
                x++;
                t10.setText(String.valueOf(x));
    
                
            }});
        panel.add(b17); 
        JLabel l27 = new JLabel("----------------------------------------------------------------------------------------------------------");
        l27.setBounds(0, 1490, 450, 10);
        panel.add(l27);



        //product 8 details.
        ImageIcon icon7 = new ImageIcon("C:/Users/SAI RAMYA/Downloads/shoes (1).jpg");
        JLabel li8 = new JLabel(icon7);
        li8.setBounds(50, 1510, icon7.getIconWidth(), icon7.getIconHeight());
        panel.add(li8);
        JLabel l28 = new JLabel("adidas shoe");
        l28.setBounds(250, 1530, 100, 50);
        panel.add(l28);
        JLabel l29 = new JLabel("₹800  12%off");
        l29.setBounds(250, 1550, 100, 50);
        panel.add(l29);
        JLabel l30 = new JLabel("size 8");
        l30.setBounds(250, 1570, 100, 50);
        panel.add(l30);
        JTextField t11 = new JTextField("0");
        t11.setBounds(300, 1630, 15, 20);
        t11.setEditable(false);
        panel.add(t11);
        JButton b18 = new JButton("-");
        b18.setBounds(250, 1630, 40, 20);
        b18.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t11.getText());
                if(x>0)
                {
                    x--;
                    t11.setText(String.valueOf(x));
                }

            }});
        panel.add(b18);

        JButton b19 = new JButton("+");
        b19.setBounds(325, 1630, 50, 20);
        b19.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t11.getText());
                x++;
                t11.setText(String.valueOf(x));
    
                
            }});
        panel.add(b19); 
        JLabel l31 = new JLabel("----------------------------------------------------------------------------------------------------------");
        l31.setBounds(0, 1720, 450, 10);
        panel.add(l31);



        
        //product 9 details.
        ImageIcon icon8 = new ImageIcon("C:/Users/SAI RAMYA/Downloads/Butterfly-Electric-Kettle (1).jpg");
        JLabel li9 = new JLabel(icon8);
        li9.setBounds(50, 1740, icon8.getIconWidth(), icon8.getIconHeight());
        panel.add(li9);
        JLabel l32 = new JLabel("kettle");
        l32.setBounds(250, 1760, 100, 50);
        panel.add(l32);
        JLabel l33 = new JLabel("₹1100  12%off");
        l33.setBounds(250, 1780, 100, 50);
        panel.add(l33);
        JLabel l34 = new JLabel("4l");
        l34.setBounds(250, 1800, 100, 50);
        panel.add(l34);
        JTextField t12 = new JTextField("0");
        t12.setBounds(300, 1870, 15, 20);
        t12.setEditable(false);
        panel.add(t12);
        JButton b20 = new JButton("-");
        b20.setBounds(250, 1870, 40, 20);
        b20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t12.getText());
                if(x>0)
                {
                    x--;
                    t12.setText(String.valueOf(x));
                }

            }});
        panel.add(b20);

        JButton b21 = new JButton("+");
        b21.setBounds(325, 1870, 50, 20);
        b21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t12.getText());
                x++;
                t12.setText(String.valueOf(x));
    
                
            }});
        panel.add(b21); 
        JLabel l35 = new JLabel("----------------------------------------------------------------------------------------------------------");
        l35.setBounds(0, 1910, 450, 10);
        panel.add(l35);



            
        //product 10 details.
        ImageIcon icon9 = new ImageIcon("C:/Users/SAI RAMYA/Downloads/safaribag.jpg");
        JLabel li10 = new JLabel(icon9);
        li10.setBounds(50, 1950, icon9.getIconWidth(), icon9.getIconHeight());
        panel.add(li10);
        JLabel l36 = new JLabel("safari bag");
        l36.setBounds(250, 1960, 100, 50);
        panel.add(l36);
        JLabel l37 = new JLabel("₹1800  12%off");
        l37.setBounds(250, 1980, 100, 50);
        panel.add(l37);
        JLabel l38 = new JLabel("size 55x75");
        l38.setBounds(250, 2000, 100, 50);
        panel.add(l38);
        JTextField t13 = new JTextField("0");
        t13.setBounds(300, 2070, 15, 20);
        t13.setEditable(false);
        panel.add(t13);
        JButton b22 = new JButton("-");
        b22.setBounds(250, 2070, 40, 20);
        b22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t13.getText());
                if(x>0)
                {
                    x--;
                    t13.setText(String.valueOf(x));
                }

            }});
        panel.add(b22);

        JButton b23 = new JButton("+");
        b23.setBounds(325, 2070, 50, 20);
        b23.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t13.getText());
                x++;
                t13.setText(String.valueOf(x));
    
                
            }});
        panel.add(b23); 

        JLabel l39 = new JLabel("----------------------------------------------------------------------------------------------------------");
        l39.setBounds(0, 2100, 450, 10);
        panel.add(l39);

        //product 11 details.
        ImageIcon icon10 = new ImageIcon("C:/Users/SAI RAMYA/Downloads/stastionarykit.jpg");
        JLabel li11 = new JLabel(icon10);
        li11.setBounds(50, 2120, icon9.getIconWidth(), icon9.getIconHeight());
        panel.add(li11);
        JLabel l40 = new JLabel("stationary kit");
        l40.setBounds(250, 2140, 100, 50);
        panel.add(l40);
        JLabel l41 = new JLabel("₹500  14%off");
        l41.setBounds(250, 2160, 100, 50);
        panel.add(l41);
        JLabel l42 = new JLabel("10items");
        l42.setBounds(250, 2180, 100, 50);
        panel.add(l42);
        JTextField t14 = new JTextField("0");
        t14.setBounds(300, 2220, 15, 20);
        t14.setEditable(false);
        panel.add(t14);
        JButton b24 = new JButton("-");
        b24.setBounds(250, 2220, 40, 20);
        b24.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t14.getText());
                if(x>0)
                {
                    x--;
                    t14.setText(String.valueOf(x));
                }

            }});
        panel.add(b24);

        JButton b25 = new JButton("+");
        b25.setBounds(325, 2220, 50, 20);
        b25.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                int x = Integer.parseInt(t14.getText());
                x++;
                t14.setText(String.valueOf(x));
    
                
            }});
        panel.add(b25); 
        JButton b26 = new JButton("Buy");
                b26.setBounds(180, 2290, 100, 40);
                b26.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    { 
                        int tt1 = Integer.parseInt(t4.getText());
                        int tt2 = Integer.parseInt(t5.getText());
                        int tt3 = Integer.parseInt(t6.getText());
                        int tt4 = Integer.parseInt(t7.getText());
                        int tt5 = Integer.parseInt(t8.getText());
                        int tt6 = Integer.parseInt(t9.getText());
                        int tt7 = Integer.parseInt(t10.getText());
                        int tt8 = Integer.parseInt(t11.getText());
                        int tt9 = Integer.parseInt(t12.getText());
                        int tt10 = Integer.parseInt(t13.getText());
                        int tt11 = Integer.parseInt(t14.getText());
                        System.out.println(tt1); 
                       
                       if(tt1!=0||tt2!=0||tt3!=0||tt4!=0||tt5!=0||tt6!=0||tt7!=0||tt8!=0||tt9!=0||tt10!=0||tt11!=0)
                       {
                        f.dispose(); 
                       JFrame f=billpage( tt1,tt2,tt3,tt4,tt5,tt6,tt7,tt8,tt9,tt10,tt11,log);
                       f.setVisible(true);
                        

                       }
                       else{
                        JOptionPane.showMessageDialog(f,"please select the items"); 
                       
                       }

              
                          
                    }});
                  panel.add(b26);

       
                
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(1, 1, 440, 900); // set bounds of scroll pane
        f.getContentPane().add(scrollPane); // add scroll pane to the content pane of the frame
        //add allthe components to the panel
        
        
        panel.setBackground(Color.  WHITE);
        f.setSize(450,2500 );
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        return f;
    }
   
    JFrame billpage(int tt1,int tt2,int tt3,int tt4, int tt5,int tt6,int tt7,int tt8,int tt9,int tt10,int tt11,String log) {
        JFrame f = new JFrame("billpage");
        JPanel panel = new JPanel();
        ImageIcon icon10 = new ImageIcon("C:/Users/pavan/OneDrive/Pictures/java images/bg.jpg");
        JLabel li11 = new JLabel(icon10);
        li11.setBounds(50, 2120, icon10.getIconWidth(), icon10.getIconHeight());
        panel.add(li11);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(740,2000));
        this.tt1=tt1;
        this.tt2=tt2;
        this.tt3=tt3;
        this.tt4=tt4;
        this.tt5=tt5;
        this.tt6=tt6;
        this.tt7=tt7;
        this.tt8=tt8;
        this.tt9=tt9;
        this.tt10=tt10;
        this.tt11=tt11;
        this.log=log;
        int arr[] = new int[12];
        arr[0]=0;arr[1]=tt1;arr[2]=tt2;arr[3]=tt3;arr[4]=tt4;arr[5]=tt5;arr[6]=tt6;arr[7]=tt7;arr[8]=tt8;arr[9]=tt9;arr[10]=tt10;arr[11]=tt11;

        JLabel l1 = new JLabel("Verto_mart");
        l1.setBounds(250, 25,300,100);
        panel.add(l1);
        JLabel l2 = new JLabel("LOVELY SUPERMARTS LTD");
        l2.setBounds(200, 50,500,100);
        panel.add(l2);
        JLabel l3 = new JLabel("<html><u><bold><b1>Batch_no</u></bold></b1></html>");
        
        l3.setBounds(25, 200,100,40);
        panel.add(l3);
        JLabel l4 = new JLabel("<html><u><bold><b1>Products</u></bold></b1></html>");
        l4.setBounds(125, 200,100,40);
        panel.add(l4);
        JLabel l5 = new JLabel("<html><u><bold><b1>Wt</u></bold></b1></html>");
        l5.setBounds(255, 200,100,40);
        panel.add(l5);
        JLabel l6 = new JLabel(" Qty");
        l6.setBounds(325, 200,100,40);
        panel.add(l6);
        JLabel l7 = new JLabel("<html><u><bold><b1>Price</u></bold></b1></html>");
        l7.setBounds(425, 200,100,40);
        panel.add(l7);
        JLabel l8 = new JLabel("<html><u><bold><b1>Value</u></bold></b1></html>");
        l8.setBounds(675, 200,100,40);
        panel.add(l8);
        JLabel l9 = new JLabel("<html><u><bold><b1>Cgst(₹)</u></bold></b1></html>");
        l9.setBounds(525, 200,100,40);
        panel.add(l9);
        JLabel l10 = new JLabel("<html><u><bold><b1>Sgst(₹)</u></bold></b1></html>");
        l10.setBounds(600, 200,100,40);
        panel.add(l10);
        System.out.println("data="+log);

            String dt;
            int sum=0;
            float c=0;
            float s=0;
            float t=0;
            int i;
            int l=0;
            int items=0;
            float dis=0;
   
            for(i=1;i<=11;i++)
            {
                if(arr[i]!=0)
                {
                    String x2=Integer.toString(arr[i]);
                    JLabel ll1 = new JLabel(x2);
                    ll1.setBounds(330, 200+i*50,100,30);
                    panel.add(ll1);
                    int it=Integer.parseInt(x2);
                    items=items+it;

                    try{
                        //connect to database
                        Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/kiran","root","kiran#123");
                        Statement stmt = conn.createStatement();
                        String sql = "SELECT * FROM productsd where text_no ="+i;
                        ResultSet rs = stmt.executeQuery(sql);
                        
                        while(rs.next())
                        {
                            String x1=rs.getString("Batch_no");
                            JLabel lll1 = new JLabel(x1);
                            lll1.setBounds(25, 200+i*50,100,30);
                            panel.add(lll1);
                            String x3=rs.getString("pro_name");
                            JLabel ll2 = new JLabel(x3);
                            ll2.setBounds(125, 200+i*50,100,30);
                            panel.add(ll2);
                            String x4=rs.getString("pro_qty");
                            JLabel ll3 = new JLabel(x4);
                            ll3.setBounds(255, 200+i*50,100,30);
                            panel.add(ll3);
                            String x5=rs.getString("pro_price");
                            JLabel ll4 = new JLabel(x5);
                            ll4.setBounds(425, 200+i*50,100,30);
                            panel.add(ll4);
                            int x6=Integer.parseInt(x5);
                            int x7=x6*arr[i];
                            String x8=Integer.toString(x7);
                            JLabel ll5 = new JLabel(x8);
                            ll5.setBounds(675, 200+i*50,100,30);
                            panel.add(ll5);

                            String x9=rs.getString("cgst");
                            float x10=Float.parseFloat(x9);
                            float xx1=(x10*x7)/100;
                            System.out.println(xx1);
                            String x11=Float.toString(xx1);
                            JLabel ll6 = new JLabel(x11);
                            ll6.setBounds(525, 200+i*50,100,30);
                            panel.add(ll6);

                            String x12=rs.getString("sgst");
                            float x13=Float.parseFloat(x12);
                            float xx2=(x13*x7)/100;
                            String x14=Float.toString(xx2);
                            JLabel ll7 = new JLabel(x14);
                            ll7.setBounds(600, 200+i*50,100,30);
                            panel.add(ll7);

                            //total
                            sum=sum+x7;
                           // c central gst
                            c=c+xx2;
                            //s state gst
                            s=s+xx2;
                            // after adding gst
                            float to=c+s;
                            t=sum-to;
                            //copying  i value
                            l=i;
                            //discount
                            
                           
                        }
                        rs.close();
                 
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                          
                }
               
            }
            

            String x15=Integer.toString(sum);
            JLabel ll8 = new JLabel(x15);
            ll8.setBounds(675, 300+l*50,100,30);
            panel.add(ll8);

            String x16=Float.toString(c);
            JLabel ll9 = new JLabel(x16);
            ll9.setBounds(525, 300+l*50,100,30);
            panel.add(ll9);

            String x17=Float.toString(s);
            JLabel ll10 = new JLabel(x17);
            ll10.setBounds(600, 300+l*50,100,30);
            panel.add(ll10);
            System.out.println("this is out side"+(i*50));
            System.out.println("the value of count"+l);

            JLabel ll13 = new JLabel("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            ll13.setBounds(0, 275+l*50, 750, 10);
            panel.add(ll13);
            String x18=Float.toString(t);
            JLabel ll11 = new JLabel("total:");
            ll11.setBounds(200, 300+l*50,100,30);
            panel.add(ll11);
            JLabel ll12 = new JLabel(x18);
            ll12.setBounds(275, 300+l*50,100,30);
            panel.add(ll12);
            System.out.println(sum);

            String x19=Integer.toString(items);
            JLabel ll14 = new JLabel("items:");
            ll14.setBounds(50, 300+l*50,100,30);
            panel.add(ll14);
            JLabel ll15 = new JLabel(x19);
            ll15.setBounds(100, 300+l*50,100,30);
            panel.add(ll15);

            if(sum>=5000)
            {

             dis=(sum/100)*90;
             System.out.println(dis);
            }
            else if(sum>=1000)
            {
             dis=(sum/100)*95;
             System.out.println(dis);

            }
            else{
                dis=sum;
            }
            String x20=Float.toString(dis);
            JLabel ll16 = new JLabel("total after getting discount:");
            ll16.setBounds(100, 350+l*75,300,30);
            panel.add(ll16);

            JLabel ll17 = new JLabel(x20);
            ll17.setBounds(275, 350+l*75,100,30);
            panel.add(ll17);
            
            JLabel ll18 = new JLabel("->if customer purchase more than 1000 they will get 5% dicount or 5000 they will get 10% & below 1000 no discount");
            ll18.setBounds(10, 350+l*50,650,30);
            panel.add(ll18);



            ResultSet rs1;
            try{
                //connect to database
                Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/kiran","root","kiran#123");
                Statement stmt = conn.createStatement();
                
                String sql1 = "SELECT * FROM customersdetails WHERE mobileno = "+log;
                rs1 = stmt.executeQuery(sql1);
                while(rs1.next())
                {
                    JLabel lll2 = new JLabel("customername:");
                    lll2.setBounds(50, 120,100,30);
                    panel.add(lll2);
                    String n1=rs1.getString("name");
                 
                    System.out.println(n1);
                    JLabel lll3 = new JLabel(n1);
                    lll3.setBounds(150, 120,100,30);
                    panel.add(lll3); 
                    
                    JLabel lll4 = new JLabel("mobileno:");
                    lll4.setBounds(50, 150,100,30);
                     panel.add(lll4);
                 
                    JLabel lll5 = new JLabel(log);
                    lll5.setBounds(125, 150,100,30);
                    panel.add(lll5);
                      
                 }
                 rs1.close();
                }
                catch (SQLException e) {
                    System.out.println(e);
                } 
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd  HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();
                dt=dtf.format(now);  
                System.out.println(dt);
                JLabel lll6 = new JLabel(dt);
                lll6.setBounds(425, 150,120,30);
                panel.add(lll6);    
            
            JButton b1 = new JButton("Print");    
            b1.setBounds(350, 350+l*100, 100, 30);
            b1.addActionListener(e ->
            {  

                try
                {
                    Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/kiran","root","kiran#123");
                    System.out.println("conected");
                    Statement statement=conn.createStatement();
                    statement.execute("create table customersdata(mobile_no varchar(20),cgst varchar(20),sgst varchar(20),itemss varchar(20),sums varchar(20))");
                    
                    String mbno = log.toString();
                    String cgst=x16.toString();
                    String sgst=x17.toString();
                    String itemss = x19.toString();
                    String sums = x15.toString();
                    
                   

                    //check for null values if any stay in same page
                    if( mbno.equals("")||cgst.equals("") ||sgst.equals("")||itemss.equals("")||sums.equals(""))
                    {
                        System.out.println("null values");

                        }
                    else

                   {
                        String sql = "INSERT INTO customersdata ( mobile_no,cgst,sgst,itemss,sums) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement statement1 = conn.prepareStatement(sql);
                        
                        statement1.setString(1, mbno);
                        statement1.setString(2, cgst);
                        statement1.setString(3, sgst);
                        statement1.setString(4, itemss);
                        statement1.setString(5, sums);

                        int rowsInserted = statement1.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("A new user was inserted successfully!");
                        }
                    }
                }
                catch(SQLException b)
                {
                    System.out.println(b);
                }
                
             f.dispose();
            
           });
           panel.add(b1);


            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setBounds(1, 1, 740, 900); // set bounds of scroll pane

            f.getContentPane().add(scrollPane);
           //panel.setBackground(Color.  orange);  
           panel.setBackground(Color.  pink);  
        f.setResizable(false);      
        f.setSize(750,2000 );
        f.setLayout(null);
        f.setVisible(true);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        return f;
    }

    public void actionPerformed(ActionEvent e) 
    {
        
    }

    public static <home> void main(String[] args) 
    {
        try
        {
            Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/kiran","root","kiran#123");
            char[] e;
            System.out.println("conected");
         }
        catch(SQLException e)
        {
            System.out.println(e);
        }

       Pavan1 h=new Pavan1();
       h.home();

}


}