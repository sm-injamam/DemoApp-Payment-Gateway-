package db.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;
import java.util.regex.Pattern;

public class RegistrationForm extends JFrame {

    private JLabel userL, passL, emailL, full_NameL, confirmPassL, msgL;

    private JTextField email_Id, email_textF;
    private static JTextField userTextField;
    private static JTextField full_name_TextF;
    private JPasswordField passwordField, confirm_passField;
    private JButton regB, clearB,loginB;
    private Container cont;
    private Font font1, font2;
    private Connection conn;

    RegistrationForm() {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 60, 600, 700);
        this.setTitle("Your Registration form ");


        font1 = new Font("Arial", Font.BOLD, 16);
        font2 = new Font("MV Boli", Font.BOLD, 14);

        cont = this.getContentPane();
        cont.setLayout(null);
        cont.setBackground(Color.GRAY);

        // Email
        emailL = new JLabel("Email: ");
        emailL.setBounds(50, 50, 160, 50);
        emailL.setFont(font1);
        cont.add(emailL);

        email_textF = new JTextField();
        email_textF.setBounds(170, 50, 200, 40);
        email_textF.setFont(font2);
        cont.add(email_textF);

        //Full Name
        full_NameL = new JLabel("Full Name: ");
        full_NameL.setBounds(50, 100, 160, 50);
        full_NameL.setFont(font1);
        cont.add(full_NameL);

        full_name_TextF = new JTextField();
        full_name_TextF.setBounds(170, 100, 200, 40);
        full_name_TextF.setFont(font2);
        cont.add(full_name_TextF);

        //UserName
        userL = new JLabel("username: ");
        userL.setBounds(50, 150, 160, 50);
        userL.setFont(font1);
        cont.add(userL);

        userTextField = new JTextField();
        userTextField.setBounds(170, 150, 200, 40);
        userTextField.setFont(font2);
        cont.add(userTextField);

        //password
        passL = new JLabel("password: ");
        passL.setBounds(50, 210, 160, 50);
        passL.setFont(font1);
        cont.add(passL);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 210, 200, 40);
        passwordField.setEchoChar('*');
        passwordField.setFont(new Font("MV Boli", Font.PLAIN, 16));
        cont.add(passwordField);

        //confirm password
        confirmPassL = new JLabel("Confirm");
        confirmPassL.setBounds(50, 270, 160, 50);
        confirmPassL.setFont(font1);
        cont.add(confirmPassL);

        confirm_passField = new JPasswordField();
        confirm_passField.setBounds(170, 270, 200, 40);
        confirm_passField.setEchoChar('*');
        confirm_passField.setFont(new Font("MV Boli", Font.PLAIN, 16));
        cont.add(confirm_passField);

        // Registration Button
        regB = new JButton("Submit");
        regB.setBounds(180, 320, 80, 30);
        regB.setFont(new Font("Arial", Font.PLAIN, 14));
        cont.add(regB);



        regB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//
//                JOptionPane.showMessageDialog(null,"Your Registration complete ");
//                dispose();

                String email_value = email_textF.getText();
                String user_value = userTextField.getText();
                String fName_value = full_name_TextF.getText();
                String pass_value = passwordField.getText();
                String cPass_value = confirm_passField.getText();

                if (email_value.isEmpty() || user_value.isEmpty() || fName_value.isEmpty() || pass_value.isEmpty() || cPass_value.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Field is empty");

                }else {
                    try {
                        checkEmailUname();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        doRegister();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }



            }
        });

        //clear Button
        clearB = new JButton("clear");
        clearB.setBounds(280, 320, 80, 30);
        clearB.setFont(new Font("Arial", Font.PLAIN, 14));
        cont.add(clearB);

        clearB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                email_textF.setText("");
                full_name_TextF.setText("");
                userTextField.setText("");
                passwordField.setText("");
                confirm_passField.setText("");

            }
        });

        msgL = new JLabel("If you are registered person then just login from here.");
        msgL.setBounds(70, 450, 550, 50);
        msgL.setForeground(Color.ORANGE);
        msgL.setFont(new Font("MV Boli", Font.BOLD, 16));
        cont.add(msgL);

        loginB = new JButton("Login");
        loginB.setBounds(230, 500, 80, 30);
        loginB.setFont(new Font("Arial", Font.PLAIN, 14));
        cont.add(loginB);

        loginB.addActionListener(e -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });

        this.setVisible(true);


    }

    private void doRegister() throws ClassNotFoundException {
        System.out.println("Register");


//        Connection conn;
//        String dbManager = "postgresql";
//        String host = "localhost";
//        String port = "5432";
//        String db = "payment_gateway";
//        String username = "postgres";
//        String password = "1234";

        Class.forName("org.postgresql.Driver"); // loading driver
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/payment_gateway", "postgres", "1234");) {
            var password = new String(passwordField.getPassword());
            var confirmPass = new String(confirm_passField.getPassword());
            if (!password.equals(confirmPass) || email_uname_exist == true ) {
                throw new RuntimeException("Password mismatch or email already exist");

            }else {
                JOptionPane.showMessageDialog(null, "Registration complete");
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);

            }
            var query = "INSERT INTO public.tbl_user( id, username, password, full_name, email) VALUES (?, ?, ?, ?, ?);";
            var pstmt = conn.prepareStatement(query);
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, userTextField.getText());
            pstmt.setString(3, new String(passwordField.getPassword()));
            pstmt.setString(4, full_name_TextF.getText());
            pstmt.setString(5, email_textF.getText());



            pstmt.executeUpdate();
        } catch (SQLException | RuntimeException ex) {
            ex.printStackTrace();

        }
        //establishing connection.

    }

    boolean email_uname_exist = false;

    private void checkEmailUname() throws SQLException {
//        boolean email_exist = false;



        try {
            Class.forName("org.postgresql.Driver"); // loading driver
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/payment_gateway", "postgres", "1234"); //establishing connection.

            var query = "select * from tbl_user WHERE email = ? or username = ? ;";

            var stmt = conn.prepareStatement(query);
            stmt.setString(1, email_textF.getText());
            stmt.setString(2, userTextField.getText());


            var resultSet = stmt.executeQuery(); // return result set.

           /* // email validation

            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";
            // or = ^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}
            //[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$

            Pattern pattern = Pattern.compile(emailRegex);
            if (pattern.matcher(email_textF).matches()){
                System.out.println("Valid");
            }else {
                System.out.println("Invalid");
            }
*/

                // to get data from resultSet
            if (resultSet.next()) {
                email_uname_exist = true;
                JOptionPane.showMessageDialog(null, "Already exist");
            }




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




//    public static void main(String[] args) {
//
//        RegistrationForm frame = new RegistrationForm();
//        frame.setVisible(true);
//
//    }
    }
}
