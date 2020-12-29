import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login {
    private JPanel mainpanel;
    private JLabel adminpic;
    private JLabel doctorpic;
    private JButton adminButton;
    private JButton doctorButton;
    private JButton patientButton;

    public login() {
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotologinasadmin();
            }
        });
        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotologinasdoctor();
            }
        });
        patientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotologinaspatientfromlogin();
            }
        });
    }

    JPanel getpanel(){
        return mainpanel;
    }
}
