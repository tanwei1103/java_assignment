import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;

class set implements ActionListener {

    JFrame cal = new JFrame();
    JButton done = new JButton("login");
    JTextField num = new JTextField(10);
    JLabel km = new JLabel("Enter username: ");
    JLabel ps = new JLabel("Your Password pls: ");
    JTextField pw = new JTextField(10);
    JButton reg = new JButton("Register");

    set() {
        prepareGUI();
        buttonProperties();
        others();
    }

    public void prepareGUI() {
        cal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cal.getContentPane().setLayout(null);
        cal.setBounds(200, 200, 400, 400);
        cal.setVisible(true);
        cal.setTitle("calculate");
    }

    public void buttonProperties() {
        done.setBounds(130, 300, 100, 40);
        cal.add(done);
        done.addActionListener(this);
        reg.setBounds(250, 300, 100, 40);
        cal.add(reg);
        reg.addActionListener(this);
    }

    public void others() {
        km.setBounds(30, 100, 200, 40);
        cal.add(km);
        num.setBounds(210, 100, 150, 40);
        cal.add(num);
        ps.setBounds(30, 150, 200, 40);
        cal.add(ps);
        pw.setBounds(210, 150, 150, 40);
        cal.add(pw);
    }

    public void t(String[] args) {
        String record = null;
        FileReader in = null;
        int flag = 1;
        try {
            in = new FileReader("login.txt");
            BufferedReader br = new BufferedReader(in);
            // Scanner keyboard = new Scanner(System.in);
            String username = num.getText();
            String pass = pw.getText();

            while ((record = br.readLine()) != null) {

                String[] split = record.split("/");
                if (username.equals(split[0]) && pass.equals(split[1])) {
                    // l.setText("done");
                    flag = 0;
                    new customer_homepage(username);
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            e.getCause();
        }
        if (flag == 1) {
            JDialog d = new JDialog();
            d.setSize(200, 200);
            d.setVisible(true);
            d.setTitle("try");

            JLabel l = new JLabel();
            l.setSize(100, 100);
            l.setText("unsuccessful");

            d.add(l);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // action for login.
        if (e.getSource() == done) {
            t(null);
        } else if (e.getSource() == reg) {
            new register_page();
        }
    }

}

class register_page implements ActionListener {

    JFrame register = new JFrame();
    JTextField un = new JTextField(20);
    JTextField ps = new JTextField(20);
    JTextField ps2 = new JTextField(20);
    JButton done = new JButton("done");

    register_page() {
        prepare_GUI();
        buttonProperties();
        label();
        Textarea();
    }

    public void prepare_GUI() {
        // register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        register.setBounds(200, 200, 600, 600);
        register.setResizable(false);
        register.setVisible(true);
        register.setTitle("register");
        register.getContentPane().setLayout(null);
    }

    public void buttonProperties() {
        done.setBounds(210, 450, 100, 40);
        register.add(done);
        done.addActionListener(this);
    }

    public void label() {
        JLabel user = new JLabel("Username: ");
        user.setBounds(30, 50, 200, 40);
        JLabel pass = new JLabel("Password: ");
        pass.setBounds(30, 150, 200, 40);
        JLabel pass2 = new JLabel("Password again: ");
        pass2.setBounds(30, 250, 200, 40);
        JLabel va = new JLabel("your username must atleast have six character and can only include a-z, numbers and _");
        va.setBounds(30, 100, 400, 30);
        JLabel v = new JLabel(
                "your password must have more than 9 character and must include");
        v.setBounds(30, 170, 400, 30);
        JLabel c = new JLabel("at least one upperletter, numbers and one symbol");
        c.setBounds(30, 200, 400, 30);
        register.add(c);
        register.add(v);
        register.add(user);
        register.add(pass);
        register.add(pass2);
        register.add(va);
    }

    public void Textarea() {
        un.setBounds(210, 50, 150, 40);
        ps.setBounds(210, 150, 150, 40);
        ps2.setBounds(210, 250, 150, 40);
        register.add(un);
        register.add(ps);
        register.add(ps2);
    }

    public void validation() {
        String name = un.getText();
        String pass = ps.getText();
        String pass2 = ps2.getText();
        String regex = "^[A-Za-z]\\w{5,29}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        if (pass.equals(pass2)) {
            if (m.matches() == true) {
                String ps = "^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$";
                Pattern valid = Pattern.compile(ps);
                Matcher n = valid.matcher(pass);
                if (n.matches() == true) {
                    register(name, pass);
                }

            }
        } else {
            JDialog d = new JDialog();
            d.setSize(250, 250);
            d.setVisible(true);
            d.setTitle("try again");
            JLabel us = new JLabel("pls try again.");
            us.setSize(150, 40);
            d.add(us);
        }
    }

    public void register(String name, String pass) {
        try {
            FileWriter my_Writer = new FileWriter("login.txt", true);
            my_Writer.write("\n" + name + "/" + pass + "Customer");
            my_Writer.close();
            JDialog j = new JDialog();
            j.setSize(250, 250);
            j.setVisible(true);
            j.setTitle("done");
            JLabel l = new JLabel("register successfully");
            l.setSize(150, 40);
            j.add(l);
        } catch (IOException e) {
            JDialog m = new JDialog();
            m.setSize(250, 250);
            m.setVisible(true);
            m.setTitle("error");
            JLabel n = new JLabel("An error occurred.");
            n.setSize(150, 40);
            m.add(n);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        validation();
    }

}

class customer_homepage implements ActionListener {

    JFrame cus_home = new JFrame();
    JLabel wlc = new JLabel();
    JButton order = new JButton("Order");
    JButton cus_pro = new JButton("Profile");
    JButton com = new JButton("Review");

    customer_homepage(String user) {
        prepare_cus_homepage();
        label(user);
        button(user);
    }

    public void prepare_cus_homepage() {

        cus_home.setTitle("Customer homepage");
        cus_home.setResizable(false);
        cus_home.setVisible(true);
        cus_home.setBounds(200, 200, 600, 600);
        cus_home.getContentPane().setLayout(null);

    }

    public void label(String user) {
        wlc.setText("Wellcome back, " + user + "!!!");
        wlc.setBounds(200, 100, 250, 40);
        wlc.setFont(new Font("Arial", Font.PLAIN, 20));
        cus_home.add(wlc);
    }

    public void button(String user) {
        order.setBounds(250, 200, 100, 40);
        cus_home.add(order);
        cus_pro.setBounds(250, 250, 100, 40);
        cus_home.add(cus_pro);
        com.setBounds(250, 300, 100, 40);
        cus_home.add(com);
        // bg.setBounds(250, 350, 100, 40);
        // cus_home.add(bg);

        // bg.addActionListener(this);
        order.addActionListener(this);
        cus_pro.addActionListener(this);
        com.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}

class cus_profile implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}

public class learn {

    public static void main(String[] args) {
        new set();
    }
}
