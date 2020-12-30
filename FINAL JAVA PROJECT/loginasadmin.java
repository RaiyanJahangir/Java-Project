import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class loginasadmin {
    private JButton closeButton;
    private JLabel username;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JTextField textField1;
    private JLabel password;
    private JPanel mainpanel;

    public loginasadmin() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file=new File("signin.txt");
                Scanner f=null;
                String a=new String();
                String b=new String();
                try{
                    int flag=0;
                    f=new Scanner(file);
                    while(f.hasNext()) {
                        a = f.next();
                        b = f.next();

                        if (a.equals(textField1.getText()) && b.equals(String.valueOf(passwordField1.getPassword()))) {
                            flag=1;
                            break;
                        }
                    }
                    if(flag==1){
                        Main.gotointro();
                    }
                    else{
                       throw new InvalidLoginException("Not Valid");
                    }

                    f.close();
                }catch(InvalidLoginException p){
                    JOptionPane.showMessageDialog(null, "Incorrect UserName or Password. Try Again", "Incorrect Information", JOptionPane.WARNING_MESSAGE);
                }catch(FileNotFoundException g){
                    System.out.println("File Not Found");
                }
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotologinfromloginasadmin();
            }
        });
    }

    JPanel getpanel(){
        return mainpanel;
    }
}
