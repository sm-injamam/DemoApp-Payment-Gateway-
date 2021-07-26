package db.practice;

import gui_form.DashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class LoginFrame extends JFrame {
    private JLabel userL, passL;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton submitB, clearB;
    private Container con;
    private static Connection conn;
    private Cursor cursor;
//    private Font font;

//    String dbManager = "postgresql";
//    String host = "localhost";
//    String port = "5432";
//    String db = "payment_gateway";
//    String db = "payment_gateway";

    LoginFrame() {


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(300, 90, 600, 400);
        this.setTitle("Your Login option ");

        con = this.getContentPane();
        con.setLayout(null);
        con.setBackground(Color.black);

//        font =new Font("Arial",Font.BOLD,19);

        userL = new JLabel("username: ");
        userL.setBounds(50, 50, 160, 50);
        userL.setFont(new Font("Arial", Font.BOLD, 16));
        userL.setForeground(Color.LIGHT_GRAY);
        con.add(userL);

        textField = new JTextField();
        textField.setBounds(170, 50, 200, 40);
        textField.setFont(new Font("MV Boli", Font.BOLD, 14));
//      textField.setBounds(170,100,200,50);
        con.add(textField);

        passL = new JLabel("password: ");
        passL.setBounds(50, 100, 160, 50);
//        passL.setFont(font);
        passL.setFont(new Font("Arial", Font.BOLD, 16));
        passL.setForeground(Color.LIGHT_GRAY);
        con.add(passL);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 100, 200, 40);
        passwordField.setEchoChar('*');
        passwordField.setFont(new Font("Arial", Font.BOLD, 16));
        con.add(passwordField);

        cursor =new Cursor(Cursor.HAND_CURSOR);

        submitB = new JButton("Login");
        submitB.setBounds(180, 150, 80, 30);
        submitB.setFont(new Font("Arial", Font.PLAIN, 14));
        submitB.setCursor(cursor);
        submitB.setBackground(Color.GREEN);
        con.add(submitB);

        clearB = new JButton("clear");
        clearB.setBounds(280, 150, 80, 30);
        clearB.setFont(new Font("Arial", Font.PLAIN, 14));
        clearB.setCursor(cursor);
        clearB.setBackground(Color.CYAN);
        con.add(clearB);


        submitB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                String userName = textField.getText();
                String password = passwordField.getText();
//                boolean user_exist = false;
                try {
                    Class.forName("org.postgresql.Driver"); // loading driver
                    conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/payment_gateway","postgres","1234"); //establishing connection.


                    checkUsers();

                }
                catch (ClassNotFoundException | SQLException ce){
                ce.printStackTrace();
                }

            }

        });






        clearB.addActionListener(e -> clearData());




        //or : we can write this lambda expression (functional program)
        // beside the anonymous inner class

        /*clearB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                textField.setText("");
                passwordField.setText("");

            }
        });*/


    }

    private  void checkUsers() throws SQLException, ClassNotFoundException {
//        boolean user_exist = false; // no need
        var query = "select * from tbl_user WHERE username = ? and password = ?;";
        var statement = conn.prepareStatement(query);
        statement.setString(1,textField.getText());
        statement.setString(2,new String(passwordField.getPassword()));
        var resultSet = statement.executeQuery(); // return result set.

        // to get data from resultSet
       if (resultSet.next()){
//           user_exist = true; // no need
           JOptionPane.showMessageDialog(null,"Login successfully done");

           new DashBoard();
           tblDetails();
        }else
            JOptionPane.showMessageDialog(null,"not exist");


    }

    public void tblDetails() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver"); // loading driver
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/payment_gateway", "postgres", "1234");){
            var query = "SELECT mobile_acc_no, issue_date, balance FROM public.payment_details;";
            var stmt = conn.prepareStatement(query);
//        stmt.setString(1,txtBkMobile_No.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        stmt.setString(2, sdf.format(dateChooser.getDate()));
//        stmt.setDouble(3, Double.parseDouble(txtAmount.getText()));
            var resultSet = stmt.executeQuery();
            while (resultSet.next()){



                DashBoard.rows[0] = resultSet.getString("mobile_acc_no");
                DashBoard.rows[1] = resultSet.getString("issue_date");
                DashBoard.rows[2] = resultSet.getString("balance");
                DashBoard.model.addRow(DashBoard.rows);

            }
        }catch (SQLException | RuntimeException ex) {
            ex.printStackTrace();

        }



    }


    private void clearData() {
        textField.setText("");
        passwordField.setText("");
    }

    public static void main(String[] args) {

        LoginFrame frame = new LoginFrame();
        frame.setVisible(true);


    }
}
