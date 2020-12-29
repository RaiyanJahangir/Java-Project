import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Home {
    private JPanel mainpanel;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton addButton;
    private JButton tableButton;
    private JButton logOutButton;
    private JLabel exceptionlabel;
    private JTable table1;
    private static JFrame frame;

    public Home() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.calldoctorinfo();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.callSearch();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.callDelete();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.callupdate();
            }
        });
        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Doctor> doctorlist = new ArrayList<>(Main.getDoctors());
                    if(doctorlist.size()==0){
                        throw new RecordsEmptyException("Incomplete Table");
                    }

                    JFrame database = new JFrame("Doctor List");
                    table1 = new JTable(doctorlist.size() + 1, 10);
                    table1.setValueAt("ID", 0, 0);
                    table1.setValueAt("NAME", 0, 1);
                    table1.setValueAt("AGE", 0, 2);
                    table1.setValueAt("GENDER", 0, 3);
                    table1.setValueAt("MARITAL", 0, 4);
                    table1.setValueAt("BLOOD", 0, 5);
                    table1.setValueAt("ADDRESS", 0, 6);
                    table1.setValueAt("PHONE", 0, 7);
                    table1.setValueAt("CITY", 0, 8);
                    table1.setValueAt("JOINING DATE", 0, 9);

                    for (int i = 0; i < doctorlist.size(); i++) {
                        table1.setValueAt(doctorlist.get(i).id, i + 1, 0);
                        table1.setValueAt(doctorlist.get(i).name, i + 1, 1);
                        table1.setValueAt(doctorlist.get(i).age, i + 1, 2);
                        table1.setValueAt(doctorlist.get(i).gender, i + 1, 3);
                        table1.setValueAt(doctorlist.get(i).marital, i + 1, 4);
                        table1.setValueAt(doctorlist.get(i).blood, i + 1, 5);
                        table1.setValueAt(doctorlist.get(i).address, i + 1, 6);
                        table1.setValueAt(doctorlist.get(i).phone, i + 1, 7);
                        table1.setValueAt(doctorlist.get(i).city, i + 1, 8);
                        table1.setValueAt(doctorlist.get(i).joiningdate, i + 1, 9);
                    }
                    Color color = UIManager.getColor("Table.gridColor");
                    MatteBorder border = new MatteBorder(1, 1, 1, 1, color);
                    table1.setBorder(border);
                    database.add(table1);
                    //database.setSize(1000, 800);
                    database.setBounds(500,100,900,870);
                    database.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    database.setVisible(true);

                    table1.setCellSelectionEnabled(true);
                    ListSelectionModel select = table1.getSelectionModel();
                    select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    select.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent e) {
                            int row = table1.getSelectedRow();
                            int columns = table1.getSelectedColumn();
                            //System.out.println(e.getValueIsAdjusting());
                            if (row > 0 && e.getValueIsAdjusting()) {
                                int value = Integer.parseInt(String.valueOf(table1.getValueAt(row, 0)));
                                frame = new JFrame("Patient Information");
                                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                                //TableFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
                                //frame.setSize(800, 800);
                                frame.setBounds(500,100,900,870);
                                frame.add(new view_2(value).getpanel());
                                frame.setResizable(false);
                                frame.setVisible(true);
                            }
                        }
                    });
                }catch (RecordsEmptyException r){
                    exceptionlabel.setText("Record is not found");
                }
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gotointrofromHome();
            }
        });
    }
    static void disposeview_2()
    {
        frame.dispose();
    }


    JPanel getpanel(){
        return mainpanel;
    }
}
