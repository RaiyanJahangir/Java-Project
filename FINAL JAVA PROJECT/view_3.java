import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class view_3 {
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
    private JTextField phonetextField9;
    private JTextField jointextField11;
    private JTextField citytextField12;
    private JTextField gendertextField10;
    private JButton OKButton;
    private JComboBox comboBox1;
    private JButton reviewButton;
    private JPanel Mainpanel;
    private JButton checkPatientDetailsButton;
    private JLabel exceptionlabel;
    private JComboBox comboBox2;
    private JButton doneButton;
    private JLabel exceptionlabel2;


    public view_3(int i) {
        ArrayList<patient> patient_list=new ArrayList<patient>(Main.getPatientList());
        ArrayList<Doctor> doctorlist=new ArrayList<Doctor>(Main.getDoctors());


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
        for(int j=0;j<patient_list.size();j++){
            if(patient_list.get(j).consulted_doctor.contains(doctorlist.get(i).name)){
                String ID= String.valueOf(patient_list.get(j).id);
                String element=patient_list.get(j).name;
                comboBox1.addItem(ID+" "+element);
            }
        }
        for(int k=0;k<doctorlist.get(i).appointments_no;k++){
            String p=doctorlist.get(i).appointment_list.get(k).patient_name;
            String t=doctorlist.get(i).appointment_list.get(k).time;
            String d=doctorlist.get(i).appointment_list.get(k).date;
            comboBox2.addItem(p+" "+t+" "+" "+d);

        }
        String a=doctorlist.get(i).name;
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotologinfromdoctorinfoview();
            }
        });
        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=1;
                Main.gotoRecord(i,a);

            }
        });

        checkPatientDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(comboBox1.getItemCount()==0)
                    {
                        throw new RecordsEmptyException("No patient");
                    }
                    String text = String.valueOf(comboBox1.getSelectedItem());
                    text = text.replaceAll("[^\\d]", " ");
                    text = text.trim();
                    int textid = Integer.parseInt(text);

                    Main.gotopatientinfo3fromview3(textid);
                }catch (RecordsEmptyException r){
                    exceptionlabel.setText("No patient Found");
                }
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int g = comboBox2.getSelectedIndex();
                    comboBox2.remove(g);
                    for(int k=0;k<doctorlist.get(i).appointments_no;k++)
                    {
                        if(g==k){
                            Main.deleteappointment(i,k);

                        }
                    }
                    comboBox2.removeAllItems();
                    for(int k=0;k<doctorlist.get(i).appointments_no;k++){
                        String p=doctorlist.get(i).appointment_list.get(k).patient_name;
                        String t=doctorlist.get(i).appointment_list.get(k).time;
                        String d=doctorlist.get(i).appointment_list.get(k).date;
                        comboBox2.addItem(p+" "+t+" "+" "+d);

                    }
                }catch (ArrayIndexOutOfBoundsException r){
                    exceptionlabel2.setText("No Appointment");
                }

            }
        });
    }

    JPanel getpanel(){
        return Mainpanel;
    }
}
