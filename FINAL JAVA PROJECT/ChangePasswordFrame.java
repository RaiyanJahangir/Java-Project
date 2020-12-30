import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class ChangePasswordFrame {
    private JPanel MainPanel;
    private JTextField UserNameTextField;
    private JLabel UserName_Label;
    private JLabel Phone_Label;
    private JFormattedTextField PhoneNoTextField;
    private JLabel Password_Label;
    private JPasswordField passwordField1;
    private JLabel ConfirmPassword_Label;
    private JPasswordField ConfirmPasswordField2;
    private JButton ConfirmChangeButton;
    private JButton goBackButton;

    public ChangePasswordFrame(int i) {
        ConfirmChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(i==1){
                    int j;
                    ArrayList<Doctor>doctor_list=new ArrayList<Doctor>(Main.getDoctors());
                    boolean flag=false;
                    String a= String.valueOf(passwordField1.getPassword());
                    String b=String.valueOf(ConfirmPasswordField2.getPassword());
                    String c=PhoneNoTextField.getText();
                    String d=UserNameTextField.getText();
                    if(a.equals("") || b.equals("") || c.equals("") || d.equals("")){
                        JOptionPane.showMessageDialog(null,"Please Fill Up The Whole Form");
                    }
                    else if(a.equals(b)){
                        for(j=0;j<doctor_list.size();j++){
                            if(c.equals(doctor_list.get(j).phone) && d.equals(doctor_list.get(j).username)){
                                flag=true;
                                break;
                            }
                        }
                        if(flag){
                            int n=JOptionPane.showConfirmDialog(null,"Confirm Change to Password?");
                            if(n==0){
                                JOptionPane.showMessageDialog(null,"Password Changed Successfully");
                                Main.ChangePass(i,j,a);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Failed to change Password");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Password Confirmation Failed");
                    }
                }
                else if(i==2){
                    int j;
                    ArrayList<patient>patient_list=new ArrayList<patient>(Main.getPatientList());
                    boolean flag=false;
                    String a= String.valueOf(passwordField1.getPassword());
                    String b=String.valueOf(ConfirmPasswordField2.getPassword());
                    String c=PhoneNoTextField.getText();
                    String d=UserNameTextField.getText();
                    if(a.equals("") || b.equals("") || c.equals("") || d.equals("")){
                        JOptionPane.showMessageDialog(null,"Please Fill Up The Whole Form");
                    }
                    else if(a.equals(b)){
                        for(j=0;j<patient_list.size();j++){
                            if(c.equals(patient_list.get(j).phn_no) && d.equals(patient_list.get(j).Username)){
                                flag=true;
                                break;
                            }
                        }
                        if(flag){
                            int n=JOptionPane.showConfirmDialog(null,"Confirm Change to Password?");
                            if(n==0){
                                JOptionPane.showMessageDialog(null,"Password Changed Successfully");
                                Main.ChangePass(i,j,a);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Failed to change Password");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Password Confirmation Failed");
                    }
                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(i==1)
                    Main.ChangePass(0,-1,"0");
                else
                    Main.ChangePass(0,-2,"0");
            }
        });
    }

    JPanel getPanel(){
        return MainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            MaskFormatter mf = new MaskFormatter("#####-######");
            PhoneNoTextField=new JFormattedTextField(mf);
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
}
