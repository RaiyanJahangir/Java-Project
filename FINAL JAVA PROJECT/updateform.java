import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class updateform {
    private JLabel address;
    private JLabel ID;
    private JLabel blood;
    private JLabel age;
    private JLabel name;
    private JLabel password;
    private JLabel phone;
    private JLabel username;
    private JLabel working;
    private JLabel city;
    private JLabel join;
    private JLabel marital;
    private JTextField usernametextField2;
    private JTextField passwordtextField3;
    private JTextField idtextfield;
    private JTextField nametextField4;
    private JTextField bgtextField6;
    private JTextField maritaltextField7;
    private JTextField addresstextField8;
    private JTextField jointextField11;
    private JTextField citytextField12;
    private JTextField gendertextField10;
    private JButton UPDATEButton;
    private JButton goBackButton;
    private JFormattedTextField phoneformattedTextField1;
    private JFormattedTextField ageformattedTextField2;
    private JPanel MainPanel;
    private JFormattedTextField joinformattedTextField1;


    public updateform(int i) {

        ArrayList<Doctor> doctorlist=new ArrayList<Doctor>(Main.getDoctors());

        idtextfield.setText(String.valueOf(doctorlist.get(i).id));
        ageformattedTextField2.setText(String.valueOf(doctorlist.get(i).age));
        addresstextField8.setText(String.valueOf(doctorlist.get(i).address));
        phoneformattedTextField1.setText(String.valueOf(doctorlist.get(i).phone));
        citytextField12.setText(doctorlist.get(i).city);
        usernametextField2.setText(doctorlist.get(i).username);
        passwordtextField3.setText(doctorlist.get(i).password);
        nametextField4.setText(doctorlist.get(i).name);
        gendertextField10.setText(doctorlist.get(i).gender);
        bgtextField6.setText(doctorlist.get(i).blood);
        joinformattedTextField1.setText(doctorlist.get(i).joiningdate);
        maritaltextField7.setText(doctorlist.get(i).marital);

        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                if(addresstextField8.getText().equals("")|| citytextField12.getText().equals("")|| passwordtextField3.getText().equals("")||usernametextField2.getText().equals("")){
                    throw new IncompleteInformationException("Incomplete information");
                }
                else{

                        int age=Integer.parseInt(ageformattedTextField2.getText());//Ekhane Integer.parseint er por kn allow korlo
                        String address=addresstextField8.getText().toUpperCase();
                        String city=citytextField12.getText().toUpperCase();
                        String phone=phoneformattedTextField1.getText().toUpperCase();
                        String username=usernametextField2.getText();
                        String password=String.valueOf(passwordtextField3.getText());
                        int a=JOptionPane.showConfirmDialog(null,"confirm changes?");
                        if(a==0){
                            Main.updateindex(i,age,address,city,phone,username,password);
                            JOptionPane.showMessageDialog(null,"Updated successfully");
                            Main.returnHomefromupdateform();
                        }
                    }
                }catch(NumberFormatException f){
                    JOptionPane.showMessageDialog(null, "Invalid Age. Please give the correct age");
                }catch(IncompleteInformationException g){
                    JOptionPane.showMessageDialog(null, "Please Fill-up the required fields", "Incomplete Information", JOptionPane.WARNING_MESSAGE);

                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returnfromupdateformtoupdate();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try{
            MaskFormatter mf=new MaskFormatter("##");
            ageformattedTextField2=new JFormattedTextField(mf);
        }catch(ParseException l){
            System.out.println(l);
        }
        try {
            MaskFormatter mh = new MaskFormatter("#####-######");
            phoneformattedTextField1 = new JFormattedTextField(mh);
        }catch (ParseException p){
            System.out.println(p);
        }

        try {
            MaskFormatter mh = new MaskFormatter("##/##/####");
            joinformattedTextField1 = new JFormattedTextField(mh);
        }catch (ParseException p){
            System.out.println(p);
        }
    }

    JPanel getPanel(){
        return MainPanel;
    }
}
