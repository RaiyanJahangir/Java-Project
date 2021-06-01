import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Patient_Info2 {
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
    private JLabel Page_Title;
    private JTextField idtextField1;
    private JTextField nametextField2;
    private JTextField agetextField3;
    private JTextField gendertextField4;
    private JTextField bloodtextField5;
    private JTextField addresstextField6;
    private JTextField phonetextField7;
    private JTextField maritaltextField8;
    private JTextField totaladmissiontextField9;
    private JPanel MainPanel;

    public Patient_Info2(int value) {
        ArrayList<patient> patient_list=new ArrayList<patient>(Main.getPatientList());
        for(int i=0;i<patient_list.size();i++)
        {
            if(value==patient_list.get(i).id){
                idtextField1.setText(String.valueOf(patient_list.get(i).id));
                nametextField2.setText(patient_list.get(i).name);
                agetextField3.setText(String.valueOf(patient_list.get(i).age));
                gendertextField4.setText(patient_list.get(i).gender);
                bloodtextField5.setText(patient_list.get(i).BG);
                addresstextField6.setText(patient_list.get(i).address);
                phonetextField7.setText(patient_list.get(i).phn_no);
                maritaltextField8.setText(patient_list.get(i).marital_status);
                totaladmissiontextField9.setText(String.valueOf(patient_list.get(i).total_admits));
                for(int j=0;j<patient_list.get(i).diseases.size();j++)
                {
                    String element=new String(patient_list.get(i).diseases.get(j)+"-> "+" Medicines: "+patient_list.get(i).medicines.get(j)+" Consulted Doctor: "+patient_list.get(i).consulted_doctor.get(j));
                    case_list.addItem(element);
                }
                break;
            }
        }
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllDataTable.disposeInfo();
            }
        });
    }

    JPanel getPanel(){
        return MainPanel;
    }
}
