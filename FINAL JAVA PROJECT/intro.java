import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class intro {
    private JPanel mainpanel;
    private JLabel INTRO;
    private JButton doctorInfoButton;
    private JButton patientInfoButton;
    private JLabel image;
    private JButton logOutButton;
    private JButton commentBoxButton;
    private JButton appointmentButton;

    public intro() {
        patientInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotoPatientHomePageFromIntro();
            }
        });
        doctorInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotoHomefromintro();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotologinfromintro();
            }
        });
        commentBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotoRecord(2,"ADMIN");
            }
        });
        appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotoappointmentfromintro();
            }
        });
    }

    JPanel getpanel(){
        return mainpanel;
    }
}
