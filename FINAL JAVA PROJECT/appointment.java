import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class appointment{
    private JPanel mainpanel;
    private JButton goBackButton;
    private JButton checkButton;
    private JComboBox doctorcombobox;
    private JComboBox patientcombobox;
    private JButton confirmAppoinmentButton;
    private JFormattedTextField timeformattedTextField1;
    private JFormattedTextField dateformattedTextField1;
    private JLabel exceptionlevel;
    private JPanel jcalender;
    private JLabel labelinpanel;
    private JLabel datelabel;
    private JButton setButton;
    private JTextField defaultdate;

    Calendar cld=Calendar.getInstance();
    JDateChooser data=new JDateChooser(cld.getTime());
    String dates;


    public appointment()  {

        data.setDateFormatString("dd/MM/yyyy");
        jcalender.add(data);
        //SimpleDateFormat sdfmt=new SimpleDateFormat("dd/MM/yyyy");
        //String date=sdfmt.format(data.getDate());
        //datelabel.setText(date);



        ArrayList<Doctor>doctorlist=new ArrayList<Doctor>(Main.getDoctors());
        ArrayList<patient>patientlist=new ArrayList<patient>(Main.getPatientList());
        for(int i=0;i<doctorlist.size();i++){
            doctorcombobox.addItem(doctorlist.get(i).name);
        }
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdfmt=new SimpleDateFormat("dd/MM/yyyy");
                dates=sdfmt.format(data.getDate());
                System.out.println(dates);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returntointrofromappoinment();
            }
        });
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String  a= String.valueOf(doctorcombobox.getSelectedItem());
                patientcombobox.setEnabled(true);
                //System.out.println(a);
                patientcombobox.removeAllItems();
                exceptionlevel.setText("");

                for(int i=0;i<patientlist.size();i++){
                    if(patientlist.get(i).consulted_doctor.contains(a)){
                        patientcombobox.addItem(patientlist.get(i).name);
                    }
                }
            }
        });
        confirmAppoinmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(patientcombobox.getItemCount()==0){
                        throw new RecordsEmptyException("Record is empty");
                    }
                    Main.addAppointment(String.valueOf(doctorcombobox.getSelectedItem()),String.valueOf(patientcombobox.getSelectedItem()),timeformattedTextField1.getText(),dates);
                    int p=JOptionPane.showConfirmDialog(null,"Confirm Appointment");
                    if(p==0){
                        Main.gotointrofromappointment();

                    }

                }catch (RecordsEmptyException r){
                    exceptionlevel.setText("No Patient is appointed");
                }


            }
        });

    }

    JPanel getpanel(){
        return mainpanel;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            MaskFormatter mt = new MaskFormatter("##:##");
            timeformattedTextField1=new JFormattedTextField(mt);
        }catch(ParseException p){
            System.out.println(p);
        }
        try{
            MaskFormatter md=new MaskFormatter("##.##.####");
            dateformattedTextField1=new JFormattedTextField(md);
        }catch (ParseException t){
            System.out.println(t);
        }
    }
}
