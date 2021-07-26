package gui_form;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class DashBoard extends JFrame {
    private JLabel lblPayDetails,lblBeneficiaries;
    private JTextField searchbar;
    private JButton btnSearch, btnPayAdd, btnUpdate;
    private JRadioButton rBtn_Mobile,rBtn_Acc;
    private ButtonGroup btnGrp;
    protected static JTable tbl_payment,tbl_beneficiaries;
    public static DefaultTableModel model;
    private JScrollPane scrollPane;
    private String[] columns = {"Mobile/Acc.No","Issue Date","Amount"};
    public static String[] rows= new String[3];
    private Container cont;
    private Connection conn;
    private JDateChooser date_Chooser;


    public DashBoard(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 60, 600, 550);
        this.setTitle("DashBoard ");
        this.setVisible(true);


        cont = this.getContentPane();
        cont.setLayout(null);
        cont.setBackground(Color.GRAY);

        // Search Bar
        searchbar  = new JTextField();
        searchbar.setBounds(50,20,400,30);
        cont.add(searchbar);

        btnSearch = new JButton("search");
        btnSearch.setBounds(460,20,100,30);
        cont.add(btnSearch);

        //Radio Button
        btnGrp = new ButtonGroup();

        rBtn_Mobile = new JRadioButton("Mobile No.");
        rBtn_Mobile.setBounds(30,60,200,30);
        rBtn_Mobile.setBackground(Color.gray);
        cont.add(rBtn_Mobile);

        rBtn_Acc = new JRadioButton("Account No.");
        rBtn_Acc.setBounds(330,60,200,30);
        rBtn_Acc.setBackground(Color.gray);
        cont.add(rBtn_Acc);

        btnGrp.add(rBtn_Mobile);
        btnGrp.add(rBtn_Acc);

        //Payment details label.
        lblPayDetails = new JLabel("Payment Details");
        lblPayDetails.setBounds(15,140,300,30);
        lblPayDetails.setFont(new Font("Arial", Font.BOLD, 16));
        cont.add(lblPayDetails);

        //payment details tbl & add btn
        tbl_payment = new JTable();

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        tbl_payment.setModel(model);
        tbl_payment.setFont(new Font("Arial", Font.PLAIN,13));
        tbl_payment.setSelectionBackground(Color.yellow);
        tbl_payment.setBackground(Color.WHITE);
        tbl_payment.setRowHeight(30);

        scrollPane = new JScrollPane(tbl_payment);
        scrollPane.setBounds(15,175,400,135);
        cont.add(scrollPane);

        btnPayAdd = new JButton("Add");
        btnPayAdd.setBounds(460,175,100,30);
        cont.add(btnPayAdd);

        btnPayAdd.addActionListener(e -> {
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setVisible(true);
        });

        //Update Button
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(460,215,100,30);
        cont.add(btnUpdate);


        //Beneficiaries lbl,tbl & btn
        lblBeneficiaries = new JLabel("Your Beneficiaries");
        lblBeneficiaries.setBounds(15,315,300,30);
        lblBeneficiaries.setFont(new Font("Arial", Font.BOLD, 16));
        cont.add(lblBeneficiaries);

        tbl_beneficiaries = new JTable();

//        model = new DefaultTableModel();
//        model.setColumnIdentifiers();
//        tbl_beneficiaries.setModel(model);
//        tbl_beneficiaries.setFont(new Font("Arial", Font.PLAIN,13));
        tbl_beneficiaries.setSelectionBackground(Color.yellow);
        tbl_beneficiaries.setBackground(Color.WHITE);
        tbl_beneficiaries.setRowHeight(30);

        scrollPane = new JScrollPane(tbl_beneficiaries);
        scrollPane.setBounds(15,350,400,135);
        cont.add(scrollPane);

        tbl_payment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
//                super.mouseClicked(me);
                try {
                    table_update();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }


        });
    }
public void table_update() throws SQLException {
    new UpdatePaymentDetails();

        int rowsNum = tbl_payment.getSelectedRow();

        String new_mob_acc_no = model.getValueAt(rowsNum,0).toString();
    UpdatePaymentDetails.txt_mob_accNo.setText(new_mob_acc_no);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String issueDate = model.getValueAt(rowsNum,1).toString();
//  String newAmount = model.getValueAt(rowsNum,2).toString();
//    UpdatePaymentDetails.txtAmount.setText(newAmount);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        UpdatePaymentDetails.date_Chooser.setDate(issueDate);

   /* try{
        Class.forName("org.postgresql.Driver"); // loading driver
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/payment_gateway", "postgres", "1234");


        var value = (tbl_payment.getModel().getValueAt(rowsNum,0).toString());
        var query = "UPDATE public.payment_details SET issue_date=?, pin=?, cvv=?, balance=? WHERE mobile_acc_no=;"+value;
        var stmt = conn.prepareStatement(query);
//        var resultSet = stmt.executeUpdate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stmt.setString(1,sdf.format(UpdatePaymentDetails.date_Chooser.getDate()));
        stmt.setString(2,new String (UpdatePaymentDetails.pin.getPassword()));
        stmt.setString(3,UpdatePaymentDetails.txtCvv.getText());
        stmt.setDouble(4, Double.parseDouble(UpdatePaymentDetails.txtAmount.getText()));

//        stmt.executeUpdate();
        model = (DefaultTableModel) tbl_payment.getModel();
        model.setRowCount(0);

        JOptionPane.showMessageDialog(null,"Update successfully");

//

    }  catch (SQLException | RuntimeException | ClassNotFoundException ex) {
        ex.printStackTrace();

    }*/



}
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
      new DashBoard();
//        PaymentDetails.tblDetails();
    }
}
