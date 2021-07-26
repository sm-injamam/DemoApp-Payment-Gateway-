package gui_form;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentDetails extends JFrame {
    private JLabel lblBkMobile_no, lblCard_accNo, lblIssueDate,lblPin,lblAmount;
    private JLabel lplCvv;
    private JPasswordField pin;
    private JTextField txtBkMobile_No,txtCard_accNo, txtAmount;
    private JTextField txtCvv;

    private Container cont;
    private JComboBox cb;
    private JLabel lblPaymentWith,lblMessage;
    private JPanel panel1,panel2,pnlInfo;
    private JDateChooser dateChooser;
    private JButton bAdd;
    private String[] payment_methods = {"Choose any","Bkash","Visa","Master Card","AMEX","PayPal"};
//    private JTabbedPane tabbedPane;
    private static Connection conn;


    PaymentDetails(){

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(400, 90, 550, 600);
        this.setTitle("Your payment methods");

        cont = this.getContentPane();
        cont.setLayout(null);
//        con.setBackground(Color.black);

//        tabbedPane = new JTabbedPane();
//        tabbedPane.setBounds(0,155,550,545);
//        cont.add(tabbedPane);

        panel1 = new JPanel();
        panel2 = new JPanel();
//        tabbedPane.add(panel1);
//        tabbedPane.add(panel2);


        lblPaymentWith = new JLabel("Payment With");
        lblPaymentWith.setBounds(180,10,300,50);
        lblPaymentWith.setFont(new Font("Arial", Font.BOLD, 26));
        cont.add(lblPaymentWith);

        cb = new JComboBox(payment_methods);
        cb.setBounds(170,70,200,45);
        cont.add(cb);



        lblMessage = new JLabel();
        lblMessage.setBounds(110,115,400,40);
        cont.add(lblMessage);

        pnlInfo = new JPanel();
        pnlInfo.setLayout(null);
        pnlInfo.setBounds(0, 155, 550, 545);

        cont.add(pnlInfo);




//        lblBkMobile_no = new JLabel("Mobile No. ");
//        panel1.add(lblBkMobile_no);
//        lblBkMobile_no.setBounds(15,50,150,40);
//        lblBkMobile_no.setFont(new Font("Arial", Font.PLAIN, 16));
//
//        txtBkMobile_No = new JTextField();
//        txtBkMobile_No.setBounds(170,50,150,40);
//        txtBkMobile_No.setFont(new Font("Arial", Font.PLAIN, 16));
//        panel1.add(txtBkMobile_No);
//
//        lblVisa_accNo = new JLabel("Account No:");
//        panel2.add(txtVisa_accNo);
//        txtVisa_accNo.setBounds(15,50,150,40);
//        lblVisa_accNo.setFont(new Font("Arial", Font.PLAIN, 16));
//
//        txtVisa_accNo = new JTextField();
//        txtVisa_accNo.setBounds(170,50,150,40);
//        txtVisa_accNo.setFont(new Font("Arial", Font.PLAIN, 16));
//        panel2.add(txtVisa_accNo);

        cb.addActionListener(e -> takeDetails());

    }



    public void takeDetails(){
        String msg = cb.getSelectedItem().toString();
        lblMessage.setText("Please fill required field for your " + msg + " transaction");

//            try {

                switch (msg) { //cb.getSelectedItem().toString()
                    case "Choose any":
//                        panel1.setVisible(false);
//                        panel2.setVisible(false);
                        pnlInfo.removeAll();

                        break;
                    case "Bkash":

//                        panel2.setVisible(false);

                        panel1 = new JPanel();
                        panel1.setBounds(0, 0, 550, 545);
                        //        panel1.setBackground(Color.GREEN);
                        panel1.setLayout(null);
                        pnlInfo.removeAll();
                        pnlInfo.add(panel1);



                        panel1.setBackground(Color.MAGENTA);
                        lblBkMobile_no = new JLabel("Mobile No. ");
                        panel1.add(lblBkMobile_no);
                        lblBkMobile_no.setBounds(15, 50, 150, 40);
                        lblBkMobile_no.setFont(new Font("Arial", Font.PLAIN, 16));

                        txtBkMobile_No = new JTextField();
                        txtBkMobile_No.setBounds(170, 50, 150, 40);
                        txtBkMobile_No.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel1.add(txtBkMobile_No);

                        lblIssueDate = new JLabel("Issue Date:");
                        lblIssueDate.setBounds(15, 100, 100, 30);
                        lblIssueDate.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel1.add(lblIssueDate);

                        dateChooser = new JDateChooser();
                        dateChooser.setBounds(170, 100, 150, 40);
                        dateChooser.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel1.add(dateChooser);

                        lblPin = new JLabel("Pin:");
                        lblPin.setBounds(15, 150, 100, 30);
                        lblPin.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel1.add(lblPin);

                        pin = new JPasswordField();
                        pin.setBounds(170, 150, 150, 40);
                        pin.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel1.add(pin);

                        lblAmount = new JLabel("Amount");
                        lblAmount.setBounds(15, 200, 100, 30);
                        lblAmount.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel1.add(lblAmount);

                        txtAmount = new JTextField();
                        txtAmount.setBounds(170, 200, 150, 40);
                        txtAmount.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel1.add(txtAmount);


                        bAdd = new JButton("Add Payment");
                        bAdd.setBounds(170, 300, 150, 30);
                        bAdd.setFont(new Font("Arial", Font.PLAIN, 14));
                        panel1.add(bAdd);




                        bAdd.addActionListener(e -> {

                            String bMobileNo = txtBkMobile_No.getText();
                            Date date = dateChooser.getDate();
                            String pinn = pin.getText();
                            String balance = txtAmount.getText();

                            if (bMobileNo.isEmpty() || date.equals(null) || pinn.isEmpty() || balance.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Field is empty");
                            } else {
                                try {
                                    add_Mobile_PaymentsDetails();
                                    tblDetails();
                                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                                    classNotFoundException.printStackTrace();
                                }
                            }
                        });

                        break;

                    default:

//                        panel1.setVisible(false);

                        panel2 = new JPanel();
                        panel2.setBounds(0, 0, 550, 545);
//        panel2.setBackground(Color.CYAN);
                        panel2.setLayout(null);
                        pnlInfo.removeAll();
                        pnlInfo.add(panel2);

                        panel2.setBackground(Color.ORANGE);
                        lblCard_accNo = new JLabel("Account No:");
                        lblCard_accNo.setBounds(15, 50, 150, 40);
                        lblCard_accNo.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel2.add(lblCard_accNo);

                        txtCard_accNo = new JTextField();
                        txtCard_accNo.setBounds(170, 50, 150, 40);
                        txtCard_accNo.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel2.add(txtCard_accNo);

                        lblIssueDate = new JLabel("Issue Date:");
                        lblIssueDate.setBounds(15, 100, 100, 30);
                        lblIssueDate.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel2.add(lblIssueDate);

                        dateChooser = new JDateChooser();
                        dateChooser.setBounds(170, 100, 150, 40);
                        dateChooser.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel2.add(dateChooser);

                        lblPin = new JLabel("Pin:");
                        lblPin.setBounds(15, 150, 100, 30);
                        lblPin.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel2.add(lblPin);

                        pin = new JPasswordField();
                        pin.setBounds(170, 150, 150, 40);
                        pin.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel2.add(pin);

                        lplCvv = new JLabel("CVV:");
                        lplCvv.setBounds(15, 200, 100, 30);
                        lplCvv.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel2.add(lplCvv);

                        txtCvv = new JPasswordField();
                        txtCvv.setBounds(170, 200, 150, 40);
                        txtCvv.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel2.add(txtCvv);

                        lblAmount = new JLabel("Amount");
                        lblAmount.setBounds(15, 250, 100, 30);
                        lblAmount.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel2.add(lblAmount);

                        txtAmount = new JTextField();
                        txtAmount.setBounds(170, 250, 150, 40);
                        txtAmount.setFont(new Font("Arial", Font.PLAIN, 16));
                        panel2.add(txtAmount);

                        bAdd = new JButton("Add Payment");
                        bAdd.setBounds(170, 300, 150, 30);
                        bAdd.setFont(new Font("Arial", Font.PLAIN, 14));
                        panel2.add(bAdd);

                        bAdd.addActionListener(e -> {

                            String cardNo = txtCard_accNo.getText();
                            Date date = dateChooser.getDate();
                            String pinn = pin.getText();
                            String balance = txtAmount.getText();

                            if (cardNo.isEmpty() || date.equals(" ") || pinn.isEmpty() || balance.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Field is empty");
                            } else {
                                try {
                                    add_Card_PaymentsDetails();
                                    tblDetails();
                                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                                    classNotFoundException.printStackTrace();
                                }
                            }
                        });

                        //                    new VisaPayments();

                        break;

                }
        cont.repaint();

//            } catch (RuntimeException e) {
//                e.printStackTrace();
//            }



//        panel1.repaint();
//        panel2.repaint();
    }

    public void add_Mobile_PaymentsDetails() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver"); // loading driver
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/payment_gateway", "postgres", "1234");) {

            var query = "INSERT INTO public.payment_details( mobile_acc_no, issue_date, pin, balance) VALUES (?, ?, ?, ?);";
            var pstmt = conn.prepareStatement(query);
            pstmt.setString(1, txtBkMobile_No.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pstmt.setString(2, sdf.format(dateChooser.getDate()));
            pstmt.setString(3, new String(pin.getPassword()));
            pstmt.setDouble(4, Double.parseDouble(txtAmount.getText()));

            pstmt.executeUpdate();
            System.out.println("Done");
            JOptionPane.showMessageDialog(null,"Done");
        } catch (SQLException | RuntimeException ex) {
            ex.printStackTrace();

        }
    }

    public void add_Card_PaymentsDetails() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver"); // loading driver
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/payment_gateway", "postgres", "1234");) {

            var query = "INSERT INTO public.payment_details( mobile_acc_no, issue_date, pin,cvv, balance) VALUES (?, ?, ?, ?, ?);";
            var pstmt = conn.prepareStatement(query);
            pstmt.setString(1, txtCard_accNo.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pstmt.setString(2, sdf.format(dateChooser.getDate()));
            pstmt.setString(3, new String(pin.getPassword()));
            pstmt.setString(4,txtCvv.getText());
            pstmt.setDouble(5, Double.parseDouble(txtAmount.getText()));

            pstmt.executeUpdate();
            System.out.println("Done");
            JOptionPane.showMessageDialog(null,"Done");
        } catch (SQLException | RuntimeException ex) {
            ex.printStackTrace();

        }
    }

    public static void tblDetails() throws SQLException, ClassNotFoundException {

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

    public static void main(String[] args) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setVisible(true);
    }


}
