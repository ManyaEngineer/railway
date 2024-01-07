import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Name, email,gender,mobile,from,to,doj,payment,bookingId
        JFrame frame = new JFrame("Railway Reservation System");



        // Jlabel lb Jtextfield tf Jbutton bt

        JLabel namelb = new JLabel("Name");
        JLabel emaillb = new JLabel("Email");
        JLabel genderlb = new JLabel("Gender");
        JLabel mobilelb = new JLabel("Mobile No: ");
        JLabel fromlb = new JLabel("From : ");
        JLabel tolb = new JLabel("To : ");
        JLabel dojlb = new JLabel("Date Of Journey :");
        JLabel amountlb = new JLabel("Amount :");
        JLabel bookingIdlb = new JLabel();

        JTextField nametf = new JTextField();
        JTextField emailtf = new JTextField();
        JTextField gendertf = new JTextField();
        JTextField mobiletf = new JTextField();
        JTextField fromtf = new JTextField();
        JTextField totf = new JTextField();
        JTextField dojtf = new JTextField();
        JTextField amounttf = new JTextField();


        JButton closebt = new JButton("Close");
        JButton bookNowbt = new JButton("Book Ticket");
        JButton clearbt = new JButton("Clear");

        // to set the size and position of components
        //Jlabel
        namelb.setBounds(20,30,100,40);
        emaillb.setBounds(20,70,100,40);
        genderlb.setBounds(20,110,100,40);
        mobilelb.setBounds(20,150,100,40);
        dojlb.setBounds(300,110,100,40);
        fromlb.setBounds(300,30,100,40);
        tolb.setBounds(300,70,100,40);
        amountlb.setBounds(300,150,100,40);
        clearbt.setBounds(50,250,150,45);
        bookNowbt.setBounds(250,250,150,45);
        closebt.setBounds(450,250,100,45);
        bookingIdlb.setBounds(50,400,400,40);

        //JTextField
        nametf.setBounds(110,40,150,25);
        emailtf.setBounds(110,80,150,25);
        gendertf.setBounds(110,120,150,25);
        mobiletf.setBounds(110,160,150,25);
        dojtf.setBounds(400,120,100,25);
        fromtf.setBounds(400,40,100,25);
        totf.setBounds(400,80,100,25);
        amounttf.setBounds(400,160,100,25);


        frame.add(namelb);
        frame.add(nametf);

        frame.add(emaillb);
        frame.add(emailtf);

        frame.add(genderlb);
        frame.add(gendertf);

        frame.add(mobilelb);
        frame.add(mobiletf);

        frame.add(dojlb);
        frame.add(dojtf);

        frame.add(fromlb);
        frame.add(fromtf);

        frame.add(tolb);
        frame.add(totf);

        frame.add(amountlb);
        frame.add(amounttf);

        frame.add(clearbt);
        frame.add(bookNowbt);
        frame.add(closebt);

        frame.add(bookingIdlb);







        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(600,500);
        frame.setVisible(true);

        //to click on clear button
        clearbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nametf.setText("");
                emailtf.setText("");
                gendertf.setText("");
                mobiletf.setText("");
                dojtf.setText("");
                fromtf.setText("");
                totf.setText("");
                amounttf.setText("");
            }
        });

        closebt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }
        });

        bookNowbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // to validate the form can't be empty

                if (nametf.getText().toString().isEmpty() ||
                        emailtf.getText().toString().isEmpty() ||
                        gendertf.getText().toString().isEmpty() ||
                        mobiletf.getText().toString().isEmpty() ||
                        fromtf.getText().toString().isEmpty() ||
                        totf.getText().toString().isEmpty() ||
                        dojtf.getText().toString().isEmpty() ||
                        amounttf.getText().toString().isEmpty()){

                    bookingIdlb.setText("Please Fill the details");

                } else {
                    Random random = new Random();
                    int id = random.nextInt(999999);
                    bookingIdlb.setText("Your Ticket is booked and your booking id is " + id);


                    String url = "jdbc:mysql://localhost:3306/railwaybooking";
                    String username = "root";
                    String password = "";
                    try{
                        Connection connection = DriverManager.getConnection(url,username,password);
                        System.out.println("Connection Done");

                        Statement statement = (Statement) connection.createStatement();


                        String name = nametf.getText().toString();
                        String email = emailtf.getText().toString();
                        String gender = gendertf.getText().toString();
                        String mobile = mobiletf.getText().toString();
                        String from1 = fromtf.getText().toString();
                        String to1 = totf.getText().toString();
                        String doj = dojtf.getText().toString();
                        String amount = amounttf.getText().toString();

                        String query1 = "INSERT INTO booking " + "VALUES (null ,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(query1);
                        preparedStatement.setString(1,name);
                        preparedStatement.setString(2,email);
                        preparedStatement.setString(3,gender);
                        preparedStatement.setString(4,mobile);
                        preparedStatement.setString(5,from1);
                        preparedStatement.setString(6,to1);
                        preparedStatement.setString(7,doj);
                        preparedStatement.setString(8,amount);
                        preparedStatement.setString(9,String.valueOf(id));
                        preparedStatement.execute();

                    }catch (Exception exception){
                        throw new RuntimeException(exception+"Connection Failed");
                    }
                }





            }
        });


    }
}