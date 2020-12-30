import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class loginasdoctor {
    private JPanel mainpanel;
    private JButton closeButton;
    private JLabel username;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JTextField textField1;
    private JLabel password;
    private JButton changePasswordButton;

    public loginasdoctor() {
        ArrayList<Doctor> doctorlist=new ArrayList<Doctor>(Main.getDoctors());
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag=0;
                String a=textField1.getText();
                String b=String.valueOf(passwordField1.getPassword());
                int i;
                for(i=0;i<doctorlist.size();i++)
                {
                    if(a.equals(doctorlist.get(i).username) && b.equals(doctorlist.get(i).password)){
                        flag=1;
                        break;
                    }
                }
                try {
                    if (flag == 1) {
                        Main.gotodoctorinfofromloginasdoctor(i);
                    }
                    else {
                        throw new InvalidLoginException("Not Valid");
                    }
                }catch(InvalidLoginException p){
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password.Try Again!!!", "Incorrect Information", JOptionPane.WARNING_MESSAGE);

                }
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotologinformloginasdoctor();
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.CallChangePasswordFrame(1);
            }
        });
    }

    JPanel getpanel(){
        return mainpanel;
    }
}
