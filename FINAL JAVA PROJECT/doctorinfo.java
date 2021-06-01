import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;

public class doctorinfo implements ActionListener{
    private JPanel Mainpanel;
    private JTextField idtext;
    private JTextField nametext;
    private JTextField agetext;
    private JComboBox comboBox1;
    private JRadioButton maleradio;
    private JTextField addresstext;
    private JTextField jointext;
    private JRadioButton femaleradio;
    private JLabel marital;
    private JRadioButton marriedRadioButton;
    private JRadioButton unmarriedRadioButton;
    private JTextField citytext;
    private JTextField phonetext;
    private JButton backButton;
    private JButton addButton;
    private JTextField passwordtextField1;
    private JTextField usernametextField2;
    private JFormattedTextField ageformattedTextField1;
    private JFormattedTextField phoneformattedTextField1;
    private JFormattedTextField joinformattedTextField;
    private JButton setButton;
    private JPanel jCalender;

    ButtonGroup gender=new ButtonGroup();
    ButtonGroup married=new ButtonGroup();
    Doctor one=new Doctor();

    Calendar cld=Calendar.getInstance();
    JDateChooser data=new JDateChooser(cld.getTime());
    String dates;

    public doctorinfo() {
        gender.add(maleradio);
        gender.add(femaleradio);
        maleradio.addActionListener(this);
        femaleradio.addActionListener(this);
        married.add(marriedRadioButton);
        married.add(unmarriedRadioButton);
        marriedRadioButton.addActionListener(this);
        unmarriedRadioButton.addActionListener(this);
        idtext.setText(String.valueOf(Main.getdocID()));

        data.setDateFormatString("dd/MM/yyyy");
        jCalender.add(data);

        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdfmt=new SimpleDateFormat("dd/MM/yyyy");
                dates=sdfmt.format(data.getDate());
                //System.out.println(dates);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returnfromdoctorinfotohome();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (nametext.getText().equals("")) {
                        throw new IncompleteInformationException("Incomplete Information");
                    } else if (ageformattedTextField1.getText().equals("")) {
                        throw new IncompleteInformationException("Incomplete Information");
                    } else if (addresstext.getText().equals("")) {
                        throw new IncompleteInformationException("Incomplete Information");
                    } else if (phoneformattedTextField1.getText().equals("")) {
                        throw new IncompleteInformationException("Incomplete Information");
                    } else if (citytext.getText().equals("")) {
                        throw new IncompleteInformationException("Incomplete Information");
                    }
//                else if(workingtext.getText().equals("")){
//                    JOptionPane.showMessageDialog(null,"Fill up the Gender","Incomplete Information",JOptionPane.WARNING_MESSAGE);
//                }
                    else if (!femaleradio.isSelected() && !maleradio.isSelected()) {
                        throw new IncompleteInformationException("Incomplete Information");
                    } else if (!marriedRadioButton.isSelected() && !unmarriedRadioButton.isSelected()) {
                        throw new IncompleteInformationException("Incomplete Information");
                    } else {

                        int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
                        if (a == 0) {
                            one.id = Main.getdocID();
                            one.name = nametext.getText().toUpperCase();
                            one.age = Integer.parseInt(ageformattedTextField1.getText());
                            one.blood = String.valueOf(comboBox1.getSelectedItem());
                            one.address = addresstext.getText().toUpperCase();
                            one.phone = phoneformattedTextField1.getText();
                            one.city = citytext.getText().toUpperCase();
                            one.joiningdate =dates;
                            one.password = String.valueOf(passwordtextField1.getText());
                            one.username = usernametextField2.getText();
                            Main.returntohomefrominputframe(one);
                        }


                    }
                }catch(IncompleteInformationException p){
                    JOptionPane.showMessageDialog(null, "Fill up the Form", "Incomplete Information", JOptionPane.WARNING_MESSAGE);

                }catch (NumberFormatException q){
                    JOptionPane.showMessageDialog(null, "Invalid Age", "Incomplete Information", JOptionPane.WARNING_MESSAGE);

                }
            }
        });

    }

    JPanel getPanel(){
        return Mainpanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(maleradio.isSelected()){
            one.gender="Male";
        }
        else{
            one.gender="Female";
        }
        if(marriedRadioButton.isSelected()){
            one.marital="Married";
        }
        else{
            one.marital="Unmarried";
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            MaskFormatter mf = new MaskFormatter("#####-######");
            //mf.setPlaceholderCharacter('o');
            phoneformattedTextField1=new JFormattedTextField(mf);
            //Phn_no_Input.setColumns(12);
            //System.out.println("Hello");
        }catch(ParseException e){
            e.printStackTrace();

        }

        try {
            MaskFormatter mf = new MaskFormatter("##");
            //mf.setPlaceholderCharacter(' ');
            ageformattedTextField1=new JFormattedTextField(mf);
            //Phn_no_Input.setColumns(12);
            //System.out.println("Hello");
        }catch(ParseException e){
            e.printStackTrace();

        }

        /*try {
            MaskFormatter mf = new MaskFormatter("##/##/####");
            //mf.setPlaceholderCharacter(' ');
            joinformattedTextField=new JFormattedTextField(mf);
            //Phn_no_Input.setColumns(12);
            //System.out.println("Hello");
        }catch(Exception e){
            e.printStackTrace();

        }*/
    }
}
