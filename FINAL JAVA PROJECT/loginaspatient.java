import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class loginaspatient {
    private JPanel mainpanel;
    private JButton closeButton;
    private JLabel username;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JLabel password;
    private JTextField textField1;
    private JButton changePasswordButton;

    public loginaspatient() {
        ArrayList<patient> patient_list=new ArrayList<>(Main.getPatientList());
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    /*int k=0;
                File patient_ac=new File("patient_account.txt");
                Scanner filereader=null;
                try {
                    filereader=new Scanner(patient_ac);
                } catch (FileNotFoundException f) {
                    System.out.println("File could not be found");
                    System.exit(0);
                }*/
                boolean flag=false;
                String a=textField1.getText();
                String b= String.valueOf(passwordField1.getPassword());
                int i;
                for(i=0;i<patient_list.size();i++)
                {
                    if(a.equals(patient_list.get(i).Username) && b.equals(patient_list.get(i).password)){
                        flag=true;
                        break;
                    }
                }
                /*while(filereader.hasNextLine()){
                    a=filereader.nextLine();
                    b=filereader.nextLine();
                    if(a.equals(textField1.getText())&& b.equals(String.valueOf(passwordField1.getPassword()))){
                        a=a.replaceAll("[^\\d]", " ");
                        a=a.trim();
                        k=Integer.parseInt(a);
                        for(int i=0;i<patient_list.size();i++)
                        {
                            if(k==patient_list.get(i).id){
                                flag=true;
                                break;
                            }
                        }
                        if(flag)
                            break;
                    }
                }
                filereader.close();*/
                try {
                    if (flag) {
                        Main.gotoPatient4Fromloginaspatient(patient_list.get(i).id);
                    } else {
                        throw new InvalidLoginException("Not Valid");
                    }
                }catch(InvalidLoginException p){
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Incorrect Information", JOptionPane.WARNING_MESSAGE);

                }

            }
            //Anika's Part
                /*int k=0;
                File patient_ac=new File("patient_account.txt");
                Scanner filereader=null;
                try {
                    filereader=new Scanner(patient_ac);
                } catch (FileNotFoundException f) {
                    System.out.println("File could not be found");
                    System.exit(0);
                }
                boolean flag=false;
                String a=new String();
                String b=new String();
                while(filereader.hasNextLine()){
                    a=filereader.nextLine();
                    b=filereader.nextLine();
                    if(a.equals(textField1.getText())&& b.equals(String.valueOf(passwordField1.getPassword()))){
                        a=a.replaceAll("[^\\d]", " ");
                        a=a.trim();
                        k=Integer.parseInt(a);
                        for(int i=0;i<patient_list.size();i++)
                        {
                            if(k==patient_list.get(i).id){
                                flag=true;
                                break;
                            }
                        }
                        if(flag)
                            break;
                    }
                }
                filereader.close();
                try {
                    if (flag) {
                        Main.gotoPatient4Fromloginaspatient(k);
                    } else {
                        throw new InvalidLoginException("Not Valid");
                    }
                }catch(InvalidLoginException p){
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Incorrect Information", JOptionPane.WARNING_MESSAGE);

                }
            }*/
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotologinfromloginaspatient();
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.CallChangePasswordFrame(2);
            }
        });
    }

    JPanel getpanel(){
        return mainpanel;
    }
}
