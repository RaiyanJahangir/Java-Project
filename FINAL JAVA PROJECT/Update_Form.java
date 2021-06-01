import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Update_Form implements ActionListener{
    private JPanel Update_Form;
    private JLabel Page_Title;
    private JLabel ID_Label;
    private JLabel Name_Label;
    private JLabel Age_Label;
    private JLabel Gender_Label;
    private JLabel BG_Label;
    private JLabel Address_Label;
    private JLabel phn_no_label;
    private JLabel marital_status_label;
    private JLabel total_admits_label;
    private JLabel cases_label;
    private JComboBox case_list;
    private JButton OKButton;
    private JTextField Address_textField;
    private JRadioButton addNewCaseRadioButton;
    private JTextField DiseaseTextField;
    private JLabel NewDiseaseLabel;
    private JLabel MedicineLabel;
    private JTextField MedicineTextField;
    private JLabel DoctorLabel;
    private JButton goBackButton;
    private JComboBox DoctorComboBox;
    private JFormattedTextField AgeTextField1;
    private JFormattedTextField Phn_no_TextField1;

    public Update_Form(int i) {
        addNewCaseRadioButton.addActionListener(this);
        ArrayList<patient> patient_list=new ArrayList<patient>(Main.getPatientList());
        Set<String> doctor_list=new HashSet<>(Main.getDoctorList());
        ID_Label.setText("ID :"+patient_list.get(i).id);
        Name_Label.setText("Name :"+patient_list.get(i).name);
        AgeTextField1.setText(String.valueOf(patient_list.get(i).age));
        Address_textField.setText(patient_list.get(i).address);
        Phn_no_TextField1.setText(patient_list.get(i).phn_no);
        Gender_Label.setText("Gender :"+patient_list.get(i).gender);
        BG_Label.setText("Blood Group :"+patient_list.get(i).BG);
        marital_status_label.setText("Marital Status :"+patient_list.get(i).marital_status);
        total_admits_label.setText("Total Admissions :"+patient_list.get(i).total_admits);
        for (String element : doctor_list) {
            DoctorComboBox.addItem(element);
        }
        for(int j=0;j<patient_list.get(i).diseases.size();j++)
        {
            String element=new String(patient_list.get(i).diseases.get(j)+"-> "+" Medicines: "+patient_list.get(i).medicines.get(j)+" Consulted Doctor: "+patient_list.get(i).consulted_doctor.get(j));
            case_list.addItem(element);
        }
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (addNewCaseRadioButton.isSelected()) {
                        if (DiseaseTextField.getText().equals("") || MedicineTextField.getText().equals("") || AgeTextField1.getText().equals("") || Address_textField.getText().equals("") || Phn_no_TextField1.getText().equals("")) {
                            throw new IncompleteInformationException("Incomplete Information");
                        } else {
                                int age = Integer.parseInt(AgeTextField1.getText());
                                String address = Address_textField.getText().toUpperCase();
                                String phn_no = Phn_no_TextField1.getText().toUpperCase();
                                String disease = DiseaseTextField.getText().toUpperCase();
                                String medicine = MedicineTextField.getText().toUpperCase();
                                String doctor = (String) (DoctorComboBox.getSelectedItem());
                                int a = JOptionPane.showConfirmDialog(null, "Confirm Changes ?");
                                if (a == 0) {
                                    Main.UpdateIndex(i, disease, medicine, doctor, age, address, phn_no, patient_list.get(i).total_admits + 1);
                                    JOptionPane.showMessageDialog(null, "Update Successful");
                                    Main.returntoHomeFromUpdateFrame();
                                }
                        }
                    } else {
                        if (AgeTextField1.getText().equals("") || Address_textField.getText().equals("") || Phn_no_TextField1.getText().equals("")) {
                            throw new IncompleteInformationException("Incomplete Information");
                        } else {

                                int age = Integer.parseInt(AgeTextField1.getText());
                                String address = Address_textField.getText().toUpperCase();
                                String phn_no = Phn_no_TextField1.getText().toUpperCase();
                                int a = JOptionPane.showConfirmDialog(null, "Confirm Changes ?");
                                if (a == 0) {
                                    Main.UpdateIndex(i, age, address, phn_no);
                                    JOptionPane.showMessageDialog(null, "Update Successful");
                                    Main.returntoHomeFromUpdateFrame();
                                }

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
                Main.returntoUpdateFromUpdateForm();
            }
        });
    }
    JPanel getPanel(){
        return Update_Form;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            MaskFormatter mf = new MaskFormatter("##");
            //mf.setPlaceholderCharacter(' ');
            AgeTextField1=new JFormattedTextField(mf);
            //Phn_no_Input.setColumns(12);
            //System.out.println("Hello");
        }catch(Exception e){
            e.printStackTrace();

        }

        try {
            MaskFormatter mf = new MaskFormatter("######-#####");
            //mf.setPlaceholderCharacter(' ');
            Phn_no_TextField1=new JFormattedTextField(mf);
            //Phn_no_Input.setColumns(12);
            //System.out.println("Hello");
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(addNewCaseRadioButton.isSelected()){
            DiseaseTextField.setEnabled(true);
            MedicineTextField.setEnabled(true);
            DoctorComboBox.setEnabled(true);
        }
        else{
            DiseaseTextField.setEnabled(false);
            MedicineTextField.setEnabled(false);
            DoctorComboBox.setEnabled(false);
        }
    }
}
