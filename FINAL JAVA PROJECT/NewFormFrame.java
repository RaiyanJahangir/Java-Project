import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class NewFormFrame implements ActionListener{
    private JLabel IDlabel;
    private JLabel Name;
    private JTextField NameInput;
    private JLabel Age;
    private JLabel Gender;
    private JRadioButton malebutton;
    private JRadioButton femalebutton;
    private JLabel BG;
    private JComboBox BG_ComboBox;
    private JLabel marital_status;
    private JRadioButton unmarriedRadioButton;
    private JRadioButton marriedRadioButton;
    private JLabel Disease;
    private JTextField DiseaseName;
    private JLabel ConsultantDoctor;
    private JButton OkButton;
    private JLabel Medicines;
    private JTextField medicines;
    private JLabel AddressLabel;
    private JTextField AddressInput;
    private JLabel PhoneLabel;
    private JComboBox DoctorComboBox;
    private JFormattedTextField Phn_no_Input;
    private JFormattedTextField AgeTextField1;
    private JPanel NewForm;
    private JButton goBackButton;
    ButtonGroup bg=new ButtonGroup();
    ButtonGroup bg1=new ButtonGroup();
    patient entity=new patient();
    Set<String> doctor_list=new HashSet<String>(Main.getDoctorList());

    public NewFormFrame() {
        bg.add(malebutton);
        bg.add(femalebutton);
        malebutton.addActionListener(this);
        femalebutton.addActionListener(this);
        bg1.add(unmarriedRadioButton);
        bg1.add(marriedRadioButton);
        unmarriedRadioButton.addActionListener(this);
        marriedRadioButton.addActionListener(this);
        IDlabel.setText("ID: "+Main.getID());
        for (String element : doctor_list) {
            DoctorComboBox.addItem(element);
        }
        OkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                if (NameInput.getText().equals("") || AgeTextField1.getText().equals("") || (!malebutton.isSelected() && !femalebutton.isSelected()) || (!unmarriedRadioButton.isSelected() && !marriedRadioButton.isSelected()) || DiseaseName.getText().equals("") ||  medicines.getText().equals("") || AddressInput.getText().equals("") || Phn_no_Input.getText().equals("")) {
                    throw new IncompleteInformationException("Form not fully filled up.");
                }
                else {

                        int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
                        if (a == 0) {
                            entity.id = Main.getID();
                            entity.name = NameInput.getText().toUpperCase();
                            entity.age = Integer.parseInt(AgeTextField1.getText());
                            entity.BG = String.valueOf(BG_ComboBox.getSelectedItem());
                            entity.address = AddressInput.getText().toUpperCase();
                            entity.phn_no = Phn_no_Input.getText();
                            entity.total_admits = 1;
                            entity.Username=NameInput.getText()+"_ID"+Main.getID();
                            entity.password=NameInput.getText()+"_ID"+Main.getID();
                            entity.diseases.add(DiseaseName.getText().toUpperCase());
                            entity.medicines.add(medicines.getText().toUpperCase());
                            entity.consulted_doctor.add((String)DoctorComboBox.getSelectedItem());
                            Main.GoBackToHomeFrame(entity,DiseaseName.getText().toUpperCase());
                        }
                    }
                }catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Invalid Age. Please give the correct age");
                }catch(IncompleteInformationException g){
                    JOptionPane.showMessageDialog(null, "Please Fill-up the whole form", "Incomplete Information", JOptionPane.WARNING_MESSAGE);

                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returntoHomeFramewithoutAdd();
            }
        });
    }

    JPanel getPanel(){
        return NewForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(malebutton.isSelected())
            entity.gender="MALE";
        else
            entity.gender="FEMALE";

        if(unmarriedRadioButton.isSelected())
            entity.marital_status="UNMARRIED";
        else
            entity.marital_status="MARRIED";
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            MaskFormatter mf = new MaskFormatter("#####-######");
            //mf.setPlaceholderCharacter('o');
            Phn_no_Input=new JFormattedTextField(mf);
            //Phn_no_Input.setColumns(12);
            //System.out.println("Hello");
        }catch(ParseException e){
            e.printStackTrace();

        }

        try {
            MaskFormatter mf = new MaskFormatter("##");
            //mf.setPlaceholderCharacter(' ');
            AgeTextField1=new JFormattedTextField(mf);
            //Phn_no_Input.setColumns(12);
            //System.out.println("Hello");
        }catch(ParseException e){
            e.printStackTrace();

        }
    }
}
