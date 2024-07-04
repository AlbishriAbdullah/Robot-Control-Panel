
package robot;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.*;


public class Robot extends JFrame implements ActionListener {
private JButton b1,b2,b3,b4,b5;
private JPanel p1,p2,p3,p4,p5;
    public Robot()
    {
        this.setTitle("Robot Control Panel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 400);
        
        b1 = new JButton("LEFT");
        b2 = new JButton("RIGHT");
        b3 = new JButton("STOP");
        b4 = new JButton("FORWARD");
        b5 = new JButton("BACKWARD");

        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1.add(b4); // FORWARD

        p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2.add(b1); // LEFT

        p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p3.add(b3); // STOP

        p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p4.add(b2); // RIGHT

        p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p5.add(b5); // BACKWARD

        JPanel p = (JPanel) this.getContentPane();
        p.setLayout(new GridLayout(3, 3));
        
        p.add(new JPanel()); 
        p.add(p1); // FORWARD
        p.add(new JPanel()); 
        p.add(p2); // LEFT
        p.add(p3); // STOP
        p.add(p4); // RIGHT
        p.add(new JPanel());
        p.add(p5); // BACKWARD
        p.add(new JPanel()); 
         
         b1.addActionListener(this);
         b2.addActionListener(this);
         b3.addActionListener(this);
         b4.addActionListener(this);
         b5.addActionListener(this);
        this.pack();
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new Robot();
    }
    private void saveToDatabase(String direction)
    {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/robotdb", "root", "");

        PreparedStatement pstmt = con.prepareStatement("INSERT INTO robot_direction(direction) VALUES(?)");

        pstmt.setString(1, direction);

        pstmt.executeUpdate();

        pstmt.close();
        con.close();

        System.out.println("Robot direction has been saved to the database.");
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving to database", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1)
        {
            String left = "LEFT";
            saveToDatabase(left); 
        } else if (e.getSource()== b2)
        {
            String right = "RIGHT";
            saveToDatabase(right);
        } else if (e.getSource()== b3)
        {
            String stop = "STOP";
            saveToDatabase(stop);
        } else if (e.getSource()== b4)
        {
            String forward = "FORWARD";
            saveToDatabase(forward);
        } else if (e.getSource()== b5)
        {
            String backward = "BACKWARD";
            saveToDatabase(backward);
        }
       
        }
    
}
