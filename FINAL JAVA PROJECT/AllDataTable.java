//OPENING THIS FILE AFTER 8 MONTH
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AllDataTable implements ActionListener{
    private JPanel AllDataTable;
    private JPanel ButtonPanel;
    private JRadioButton showAllRadioButton;
    private JRadioButton genderRadioButton;
    private JComboBox gendercomboBox;
    private JRadioButton bloodGroupRadioButton;
    private JComboBox BGComboBox;
    private JRadioButton diseaseRadioButton;
    private JComboBox diseasecomboBox;
    private JRadioButton consultedDoctorRadioButton;
    private JComboBox DoctorComboBox;
    private JButton ConfirmButton;
    private JButton goBackButton;
    private JLabel ExceptionLabel;
    private  static JFrame frame;
    private JTable table1;
    ButtonGroup show=new ButtonGroup();
    ArrayList<patient> patient_list=new ArrayList<patient>(Main.getPatientList());
    Set<String> disease_list=new HashSet<String>(Main.getDiseaseList());
    Set<String>doctor_list=new HashSet<String>(Main.getDoctorList());

    public static void disposeInfo(){
        frame.dispose();
    }
    public AllDataTable() {
        show.add(showAllRadioButton);
        show.add(genderRadioButton);
        show.add(bloodGroupRadioButton);
        show.add(diseaseRadioButton);
        show.add(consultedDoctorRadioButton);
        showAllRadioButton.addActionListener(this);
        genderRadioButton.addActionListener(this);
        bloodGroupRadioButton.addActionListener(this);
        diseaseRadioButton.addActionListener(this);
        consultedDoctorRadioButton.addActionListener(this);
        for (String element : disease_list) {
            diseasecomboBox.addItem(element);
        }
        for (String element : doctor_list) {
            DoctorComboBox.addItem(element);
        }
        ConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (showAllRadioButton.isSelected()) {
                        if(patient_list.size()==0){
                            throw new RecordsEmptyException("Record Empty");
                        }
                        ExceptionLabel.setText("");
                        JFrame Database = new JFrame("Patient List");
                        table1 = new JTable(patient_list.size() + 1, 8);
                        table1.setValueAt("Patient_ID", 0, 0);
                        table1.setValueAt("NAME", 0, 1);
                        table1.setValueAt("AGE", 0, 2);
                        table1.setValueAt("GENDER", 0, 3);
                        table1.setValueAt("BLOOD GROUP", 0, 4);
                        table1.setValueAt("ADDRESS", 0, 5);
                        table1.setValueAt("PHONE NUMBER", 0, 6);
                        table1.setValueAt("MARITAL STATUS", 0, 7);
                        for (int i = 0; i < patient_list.size(); i++) {
                            table1.setValueAt(patient_list.get(i).id, i + 1, 0);
                            table1.setValueAt(patient_list.get(i).name, i + 1, 1);
                            table1.setValueAt(patient_list.get(i).age, i + 1, 2);
                            table1.setValueAt(patient_list.get(i).gender, i + 1, 3);
                            table1.setValueAt(patient_list.get(i).BG, i + 1, 4);
                            table1.setValueAt(patient_list.get(i).address, i + 1, 5);
                            table1.setValueAt(patient_list.get(i).phn_no, i + 1, 6);
                            table1.setValueAt(patient_list.get(i).marital_status, i + 1, 7);
                        }
                        Color color = UIManager.getColor("Table.gridColor");
                        MatteBorder border = new MatteBorder(1, 1, 1, 1, color);
                        table1.setBorder(border);
                        JScrollPane sp = new JScrollPane(table1);
                        Database.add(sp);
                        //Database.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        //Database.setSize(900, 500);
                        Database.setBounds(450,100,1000,500);

                        Database.setResizable(false);
                        Database.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        Database.setVisible(true);

                        table1.setCellSelectionEnabled(true);
                        ListSelectionModel select = table1.getSelectionModel();
                        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        select.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent e) {
                                int row = table1.getSelectedRow();
                                int columns = table1.getSelectedColumn();
                                if (row > 0 && e.getValueIsAdjusting()) {
                                    int value = Integer.parseInt(String.valueOf(table1.getValueAt(row, 0)));
                                    frame = new JFrame("Patient Information");
                                    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                                    //TableFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
                                    //frame.setSize(800, 800);
                                    frame.setBounds(450,35,1000,800);

                                    frame.add(new Patient_Info2(value).getPanel());
                                    frame.setResizable(false);
                                    frame.setVisible(true);
                                }
                            }
                        });
                    } else if (genderRadioButton.isSelected()) {
                        String gen = (String) gendercomboBox.getSelectedItem();
                        ArrayList<patient> SortByGender = new ArrayList<patient>();
                        for (int i = 0; i < patient_list.size(); i++) {
                            if (patient_list.get(i).gender.equals(gen)) {
                                SortByGender.add(patient_list.get(i));
                            }
                        }
                        if(SortByGender.size()==0){
                            throw new RecordsEmptyException("Record Empty");
                        }
                        ExceptionLabel.setText("");
                        JFrame Database = new JFrame("Patient List");
                        table1 = new JTable(SortByGender.size() + 1, 8);
                        table1.setValueAt("Patient_ID", 0, 0);
                        table1.setValueAt("NAME", 0, 1);
                        table1.setValueAt("AGE", 0, 2);
                        table1.setValueAt("GENDER", 0, 3);
                        table1.setValueAt("BLOOD GROUP", 0, 4);
                        table1.setValueAt("ADDRESS", 0, 5);
                        table1.setValueAt("PHONE NUMBER", 0, 6);
                        table1.setValueAt("MARITAL STATUS", 0, 7);
                        for (int i = 0; i < SortByGender.size(); i++) {
                            table1.setValueAt(SortByGender.get(i).id, i + 1, 0);
                            table1.setValueAt(SortByGender.get(i).name, i + 1, 1);
                            table1.setValueAt(SortByGender.get(i).age, i + 1, 2);
                            table1.setValueAt(SortByGender.get(i).gender, i + 1, 3);
                            table1.setValueAt(SortByGender.get(i).BG, i + 1, 4);
                            table1.setValueAt(SortByGender.get(i).address, i + 1, 5);
                            table1.setValueAt(SortByGender.get(i).phn_no, i + 1, 6);
                            table1.setValueAt(SortByGender.get(i).marital_status, i + 1, 7);
                        }
                        Color color = UIManager.getColor("Table.gridColor");
                        MatteBorder border = new MatteBorder(1, 1, 1, 1, color);
                        table1.setBorder(border);
                        Database.add(table1);
                        //Database.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        //Database.setSize(900, 500);
                        Database.setBounds(450,100,1000,500);

                        Database.setResizable(false);
                        Database.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        Database.setVisible(true);

                        table1.setCellSelectionEnabled(true);
                        ListSelectionModel select = table1.getSelectionModel();
                        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        select.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent e) {
                                int row = table1.getSelectedRow();
                                int columns = table1.getSelectedColumn();
                                if (row > 0 && e.getValueIsAdjusting()) {
                                    int value = Integer.parseInt(String.valueOf(table1.getValueAt(row, 0)));
                                    frame = new JFrame("Patient Information");
                                    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                                    //TableFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
                                    //frame.setSize(800, 800);
                                    frame.setBounds(450,35,1000,800);

                                    frame.add(new Patient_Info2(value).getPanel());
                                    frame.setResizable(false);
                                    frame.setVisible(true);
                                }
                            }
                        });
                    } else if (bloodGroupRadioButton.isSelected()) {
                        String BG = (String) BGComboBox.getSelectedItem();
                        ArrayList<patient> SortByBG = new ArrayList<patient>();
                        for (int i = 0; i < patient_list.size(); i++){
                            if (patient_list.get(i).BG.equals(BG)) {
                                SortByBG.add(patient_list.get(i));
                            }
                        }

                        if(SortByBG.size()==0){
                            throw new RecordsEmptyException("Record Empty");
                        }
                        ExceptionLabel.setText("");
                        JFrame Database = new JFrame("Patient List");
                        table1 = new JTable(SortByBG.size() + 1, 8);
                        table1.setValueAt("Patient_ID", 0, 0);
                        table1.setValueAt("NAME", 0, 1);
                        table1.setValueAt("AGE", 0, 2);
                        table1.setValueAt("GENDER", 0, 3);
                        table1.setValueAt("BLOOD GROUP", 0, 4);
                        table1.setValueAt("ADDRESS", 0, 5);
                        table1.setValueAt("PHONE NUMBER", 0, 6);
                        table1.setValueAt("MARITAL STATUS", 0, 7);
                        for (int i = 0; i < SortByBG.size(); i++) {
                            table1.setValueAt(SortByBG.get(i).id, i + 1, 0);
                            table1.setValueAt(SortByBG.get(i).name, i + 1, 1);
                            table1.setValueAt(SortByBG.get(i).age, i + 1, 2);
                            table1.setValueAt(SortByBG.get(i).gender, i + 1, 3);
                            table1.setValueAt(SortByBG.get(i).BG, i + 1, 4);
                            table1.setValueAt(SortByBG.get(i).address, i + 1, 5);
                            table1.setValueAt(SortByBG.get(i).phn_no, i + 1, 6);
                            table1.setValueAt(SortByBG.get(i).marital_status, i + 1, 7);
                        }
                        Color color = UIManager.getColor("Table.gridColor");
                        MatteBorder border = new MatteBorder(1, 1, 1, 1, color);
                        table1.setBorder(border);
                        Database.add(table1);
                        //Database.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        //Database.setSize(900, 500);
                        Database.setBounds(450,100,1000,500);

                        Database.setResizable(false);
                        Database.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        Database.setVisible(true);

                        table1.setCellSelectionEnabled(true);
                        ListSelectionModel select = table1.getSelectionModel();
                        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        select.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent e) {
                                int row = table1.getSelectedRow();
                                int columns = table1.getSelectedColumn();
                                if (row > 0 && e.getValueIsAdjusting()) {
                                    int value = Integer.parseInt(String.valueOf(table1.getValueAt(row, 0)));
                                    frame = new JFrame("Patient Information");
                                    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                                    //TableFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
                                    //frame.setSize(800, 800);
                                    frame.setBounds(450,35,1000,800);

                                    frame.add(new Patient_Info2(value).getPanel());
                                    frame.setResizable(false);
                                    frame.setVisible(true);
                                }
                            }
                        });
                    } else if (diseaseRadioButton.isSelected()) {
                        String dis = (String) diseasecomboBox.getSelectedItem();
                        ArrayList<patient> SortByDisease = new ArrayList<patient>();
                        for (int i = 0; i < patient_list.size(); i++)
                            if (patient_list.get(i).diseases.contains(dis)) {
                                SortByDisease.add(patient_list.get(i));
                            }
                        if(SortByDisease.size()==0){
                            throw new RecordsEmptyException("Record Empty");
                        }
                        ExceptionLabel.setText("");
                        JFrame Database = new JFrame("Patient List");
                        table1 = new JTable(SortByDisease.size() + 1, 8);
                        table1.setValueAt("Patient_ID", 0, 0);
                        table1.setValueAt("NAME", 0, 1);
                        table1.setValueAt("AGE", 0, 2);
                        table1.setValueAt("GENDER", 0, 3);
                        table1.setValueAt("BLOOD GROUP", 0, 4);
                        table1.setValueAt("ADDRESS", 0, 5);
                        table1.setValueAt("PHONE NUMBER", 0, 6);
                        table1.setValueAt("MARITAL STATUS", 0, 7);
                        for (int i = 0; i < SortByDisease.size(); i++) {
                            table1.setValueAt(SortByDisease.get(i).id, i + 1, 0);
                            table1.setValueAt(SortByDisease.get(i).name, i + 1, 1);
                            table1.setValueAt(SortByDisease.get(i).age, i + 1, 2);
                            table1.setValueAt(SortByDisease.get(i).gender, i + 1, 3);
                            table1.setValueAt(SortByDisease.get(i).BG, i + 1, 4);
                            table1.setValueAt(SortByDisease.get(i).address, i + 1, 5);
                            table1.setValueAt(SortByDisease.get(i).phn_no, i + 1, 6);
                            table1.setValueAt(SortByDisease.get(i).marital_status, i + 1, 7);
                        }
                        Color color = UIManager.getColor("Table.gridColor");
                        MatteBorder border = new MatteBorder(1, 1, 1, 1, color);
                        table1.setBorder(border);
                        Database.add(table1);
                        //Database.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        //Database.setSize(900, 500);
                        Database.setBounds(450,100,1000,500);

                        Database.setResizable(false);
                        Database.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        Database.setVisible(true);

                        table1.setCellSelectionEnabled(true);
                        ListSelectionModel select = table1.getSelectionModel();
                        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        select.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent e) {
                                int row = table1.getSelectedRow();
                                int columns = table1.getSelectedColumn();
                                if (row > 0 && e.getValueIsAdjusting()) {
                                    int value = Integer.parseInt(String.valueOf(table1.getValueAt(row, 0)));
                                    frame = new JFrame("Patient Information");
                                    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                                    //TableFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
                                    //frame.setSize(800, 800);
                                    frame.setBounds(450,35,1000,800);

                                    frame.add(new Patient_Info2(value).getPanel());
                                    frame.setResizable(false);
                                    frame.setVisible(true);
                                }
                            }
                        });
                    } else if (consultedDoctorRadioButton.isSelected()) {
                        String doc = (String) DoctorComboBox.getSelectedItem();
                        ArrayList<patient> SortByDoctor = new ArrayList<patient>();
                        for (int i = 0; i < patient_list.size(); i++)
                            if (patient_list.get(i).consulted_doctor.contains(doc)) {
                                SortByDoctor.add(patient_list.get(i));
                            }
                        if(SortByDoctor.size()==0){
                            throw new RecordsEmptyException("Record Empty");
                        }
                        ExceptionLabel.setText("");
                        JFrame Database = new JFrame("Patient List");
                        table1 = new JTable(SortByDoctor.size() + 1, 8);
                        table1.setValueAt("Patient_ID", 0, 0);
                        table1.setValueAt("NAME", 0, 1);
                        table1.setValueAt("AGE", 0, 2);
                        table1.setValueAt("GENDER", 0, 3);
                        table1.setValueAt("BLOOD GROUP", 0, 4);
                        table1.setValueAt("ADDRESS", 0, 5);
                        table1.setValueAt("PHONE NUMBER", 0, 6);
                        table1.setValueAt("MARITAL STATUS", 0, 7);
                        for (int i = 0; i < SortByDoctor.size(); i++) {
                            table1.setValueAt(SortByDoctor.get(i).id, i + 1, 0);
                            table1.setValueAt(SortByDoctor.get(i).name, i + 1, 1);
                            table1.setValueAt(SortByDoctor.get(i).age, i + 1, 2);
                            table1.setValueAt(SortByDoctor.get(i).gender, i + 1, 3);
                            table1.setValueAt(SortByDoctor.get(i).BG, i + 1, 4);
                            table1.setValueAt(SortByDoctor.get(i).address, i + 1, 5);
                            table1.setValueAt(SortByDoctor.get(i).phn_no, i + 1, 6);
                            table1.setValueAt(SortByDoctor.get(i).marital_status, i + 1, 7);
                        }
                        Color color = UIManager.getColor("Table.gridColor");
                        MatteBorder border = new MatteBorder(1, 1, 1, 1, color);
                        table1.setBorder(border);
                        Database.add(table1);
                        //Database.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        //Database.setSize(900, 500);
                        Database.setBounds(450,100,1000,500);

                        Database.setResizable(false);
                        Database.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        Database.setVisible(true);

                        table1.setCellSelectionEnabled(true);
                        ListSelectionModel select = table1.getSelectionModel();
                        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        select.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent e) {
                                int row = table1.getSelectedRow();
                                int columns = table1.getSelectedColumn();
                                if (row > 0 && e.getValueIsAdjusting()) {
                                    int value = Integer.parseInt(String.valueOf(table1.getValueAt(row, 0)));
                                    frame = new JFrame("Patient Information");
                                    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                                    //TableFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
                                    //frame.setSize(800, 800);
                                    frame.setBounds(450,35,1000,800);

                                    frame.add(new Patient_Info2(value).getPanel());
                                    frame.setResizable(false);
                                    frame.setVisible(true);
                                }
                            }
                        });
                    }
                }catch(RecordsEmptyException f){
                    ExceptionLabel.setText("Nothing to Show");
                }
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returntoHomeFromTable();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(showAllRadioButton.isSelected()){
            gendercomboBox.setEnabled(false);
            BGComboBox.setEnabled(false);
            diseasecomboBox.setEnabled(false);
            DoctorComboBox.setEnabled(false);
        }
        else if(genderRadioButton.isSelected()){
            gendercomboBox.setEnabled(true);
            BGComboBox.setEnabled(false);
            diseasecomboBox.setEnabled(false);
            DoctorComboBox.setEnabled(false);
        }
        else if(bloodGroupRadioButton.isSelected()){
            gendercomboBox.setEnabled(false);
            BGComboBox.setEnabled(true);
            diseasecomboBox.setEnabled(false);
            DoctorComboBox.setEnabled(false);
        }
        else if(diseaseRadioButton.isSelected()){
            gendercomboBox.setEnabled(false);
            BGComboBox.setEnabled(false);
            diseasecomboBox.setEnabled(true);
            DoctorComboBox.setEnabled(false);
        }
        else if(consultedDoctorRadioButton.isSelected()){
            gendercomboBox.setEnabled(false);
            BGComboBox.setEnabled(false);
            diseasecomboBox.setEnabled(false);
            DoctorComboBox.setEnabled(true);
        }
    }
    JPanel getPanel(){
        return AllDataTable;
    }
}
