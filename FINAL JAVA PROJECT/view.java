import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class view {
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
    private JTextField agetextField5;
    private JTextField bgtextField6;
    private JTextField maritaltextField7;
    private JTextField addresstextField8;
    private JTextField jointextField11;
    private JTextField citytextField12;
    private JTextField gendertextField10;
    private JButton OKButton;
    private JTextField phonetextField9;
    private JPanel MainPanel;

    public view(int i) {
        ArrayList<Doctor> doctorlist=new ArrayList<Doctor>(Main.getDoctors());
        System.out.println("view:"+i);


        idtextfield.setText(String.valueOf(doctorlist.get(i).id));
        nametextField4.setText(doctorlist.get(i).name);
        agetextField5.setText(String.valueOf(doctorlist.get(i).age));
        bgtextField6.setText(doctorlist.get(i).blood);
        maritaltextField7.setText(doctorlist.get(i).marital);
        addresstextField8.setText(doctorlist.get(i).address);
        usernametextField2.setText(doctorlist.get(i).username);
        passwordtextField3.setText(doctorlist.get(i).password);
        citytextField12.setText(doctorlist.get(i).city);
        phonetextField9.setText(String.valueOf(doctorlist.get(i).phone));
        gendertextField10.setText(doctorlist.get(i).gender);
        jointextField11.setText(doctorlist.get(i).joiningdate);
        usernametextField2.setText(doctorlist.get(i).username);
        passwordtextField3.setText(doctorlist.get(i).password);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returnSearchFromview();
            }
        });
    }

    JPanel getPanel(){
        return MainPanel;
    }
}
