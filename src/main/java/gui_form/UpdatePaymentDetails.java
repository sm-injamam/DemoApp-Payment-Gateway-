package gui_form;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class UpdatePaymentDetails extends JFrame {
    private JLabel lblMessage,lblVisa_accNo, lblIssueDate,lblPin, lplCvv,lblAmount;
    protected static JPasswordField pin;
    protected static JTextField txt_mob_accNo,txtCvv,txtAmount;
    private Container cont;
    private JPanel panel2;
    protected static JDateChooser date_Chooser;
    private JButton bAdd;
    protected static SimpleDateFormat sdf;

    UpdatePaymentDetails() {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(400, 90, 550, 600);
        cont = this.getContentPane();
        cont.setLayout(null);
//        con.setBackground(Color.black);



        panel2 = new JPanel();
        panel2.setBounds(0,155,550,545);
//        panel2.setBackground(Color.CYAN);
        panel2.setLayout(null);
        cont.add(panel2);

        lblMessage = new JLabel("Please fill required field for update your transaction");
        lblMessage.setFont(new Font("Arial",Font.BOLD,19));
        lblMessage.setBounds(20,110,500,40);
        cont.add(lblMessage);

        panel2.setBackground(Color.white);
        lblVisa_accNo = new JLabel("Mobile/Account No:");
        lblVisa_accNo.setBounds(15,50,150,40);
        lblVisa_accNo.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(lblVisa_accNo);

        txt_mob_accNo = new JTextField();
        txt_mob_accNo.setBounds(170,50,150,40);
        txt_mob_accNo.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(txt_mob_accNo);

        lblIssueDate = new JLabel("Issue Date:");
        lblIssueDate.setBounds(15,100,100,30);
        lblIssueDate.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(lblIssueDate);

        date_Chooser = new JDateChooser();
        date_Chooser.setBounds(170,100,150,40);
        date_Chooser.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(date_Chooser);

        lblPin = new JLabel("Pin:");
        lblPin.setBounds(15,150,100,30);
        lblPin.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(lblPin);

        pin = new JPasswordField();
        pin.setBounds(170,150,150,40);
        pin.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(pin);

        lplCvv = new JLabel("CVV:");
        lplCvv.setBounds(15,200,100,30);
        lplCvv.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(lplCvv);

        txtCvv = new JPasswordField();
        txtCvv.setBounds(170,200,150,40);
        txtCvv.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(txtCvv);

        lblAmount = new JLabel("Amount");
        lblAmount.setBounds(15,250,100,30);
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(170,250,150,40);
        txtAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(txtAmount);

        bAdd = new JButton("Update Payment");
        bAdd.setBounds(170,300,150,30);
        bAdd.setFont(new Font("Arial", Font.PLAIN, 14));
        panel2.add(bAdd);

        bAdd.addActionListener(e -> {
            try {
                newUpdates();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
//
        });

 /*       bAdd.addActionListener(e -> {

            String bMobileNo = txt_mob_accNo.getText();
            Date date = dateChooser.getDate();
            String pinn = pin.getText();
            String balance = txtAmount.getText();

            if (bMobileNo.isEmpty() || date.equals(null) || pinn.isEmpty() || balance.isEmpty() ){
                JOptionPane.showMessageDialog(null,"Field is empty");
            }else {
                try {
                    add_Card_PaymentsDetails();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
*/


        setVisible(true);

    }

    public void newUpdates() throws SQLException, ClassNotFoundException {

        int rowsNum = DashBoard.tbl_payment.getSelectedRow();
        try{
            Class.forName("org.postgresql.Driver"); // loading driver
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/payment_gateway", "postgres", "1234");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//            var value = (DashBoard.tbl_payment.getModel().getValueAt(rowsNum,0).toString());
            var query = "UPDATE public.payment_details SET issue_date=?, pin=?, cvv=?, balance=? WHERE mobile_acc_no=? ";
            var stmt = conn.prepareStatement(query);
//        var resultSet = stmt.executeUpdate();


            stmt.setString(1,sdf.format(date_Chooser.getDate()));
            stmt.setString(2,new String (pin.getPassword()));
            stmt.setString(3,txtCvv.getText());
            stmt.setDouble(4, Double.parseDouble(txtAmount.getText()));
            stmt.setString(5,txt_mob_accNo.getText());

            stmt.executeUpdate();
            DashBoard.model = (DefaultTableModel) DashBoard.tbl_payment.getModel();
            DashBoard.model.setRowCount(0);





            JOptionPane.showMessageDialog(null,"Update successfully");


//

        }  catch (SQLException | RuntimeException | ClassNotFoundException ex) {
            ex.printStackTrace();

        }
        PaymentDetails.tblDetails();
    }

    /*public void add_Card_PaymentsDetails() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver"); // loading driver
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/payment_gateway", "postgres", "1234");) {

            var query = "INSERT INTO public.payment_details( mobile_acc_no, issue_date, pin,cvv, balance) VALUES (?, ?, ?, ?, ?);";
            var pstmt = conn.prepareStatement(query);
            pstmt.setString(1, txt_mob_accNo.getText());
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
    }*/

//    public static void main(String[] args) {
//        new UpdatePaymentDetails();
//    }

}
