//import sun.text.UCompactIntArray;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class patient{
    int id;
    String name;
    int age;
    String gender;
    String BG;
    String marital_status;
    String address;
    String phn_no;
    int total_admits;
    String Username;
    String password;
    ArrayList<String> diseases=new ArrayList<String>();
    ArrayList<String>medicines=new ArrayList<String>();
    ArrayList<String>consulted_doctor=new ArrayList<String>();
}
class appoint{
    String patient_name;
    String date;
    String time;
}
class Doctor{
    int id;
    String name;
    int age;
    String gender;
    String blood;
    String marital;
    String address;
    String phone;
    String city;
    String username;
    String password;
    String joiningdate;
    int appointments_no;
    ArrayList<appoint>appointment_list=new ArrayList<appoint>();
}
public class Main {
    static ArrayList<patient>patient_list=new ArrayList<patient>();   //
    static ArrayList<Doctor>doctors=new ArrayList<Doctor>();
    static Set<String> disease_list=new HashSet<String>();
    static Set<String>doctor_list=new HashSet<String>();
    static int patient_no;
    static int doctor_no;
    static JFrame HomeFrame;
    static JFrame FormFrame;
    static JFrame TableFrame;
    static JFrame DeleteFrame;
    static JFrame SearchFrame;
    static JFrame Patient_Info_Frame;
    static JFrame UpdateFrame;
    static JFrame UpdateFormFrame;
    static JFrame loginasadmin;
    static JFrame login;
    static JFrame loginasdoctor;
    static JFrame loginaspatient;
    static JFrame intro;
    static JFrame Home;
    static JFrame doctorinfo;
    static JFrame search;
    static JFrame delete;
    static JFrame update;
    static JFrame view;
    static JFrame updateform;
    static JFrame view_3;
    static JFrame Patient_Info3;
    static JFrame Patient_Info4;
    static JFrame Comment;
    static JFrame appointment;
    static JFrame ChangePasswordFrame;

    public static void main(String[] args) {
        CreateFiles();
        StoreInArray();
        ReadtheLatestID();
        login=new JFrame("NEWLIFE HOSPITAL | LOGIN");
        //login.setSize(800,600);
        login.setBounds(400,100,800,600);
        login.add(new login().getpanel());
        login.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        login.setResizable(false);
        login.setVisible(true);
        WriteInFile();
    }

    public static void CallChangePasswordFrame(int i){
        if(i==1){
            loginasdoctor.setVisible(false);
            ChangePasswordFrame=new JFrame("Password Change Option");
            ChangePasswordFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            ChangePasswordFrame.add(new ChangePasswordFrame(i).getPanel());
            ChangePasswordFrame.setBounds(400,100,600,600);
            ChangePasswordFrame.setResizable(false);
            ChangePasswordFrame.setVisible(true);
        }
        if(i==2){
            loginaspatient.setVisible(false);
            ChangePasswordFrame=new JFrame("Password Change Option");
            ChangePasswordFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            ChangePasswordFrame.add(new ChangePasswordFrame(i).getPanel());
            ChangePasswordFrame.setBounds(400,100,600,600);
            ChangePasswordFrame.setResizable(false);
            ChangePasswordFrame.setVisible(true);
        }
    }

    public static void ChangePass(int i,int j,String a){
        if(i==1){
            doctors.get(j).password=a;
            WriteInFile();
            ChangePasswordFrame.dispose();
            loginasdoctor.setVisible(true);
        }
        else if(i==2){
            patient_list.get(j).password=a;
            WriteInFile();
            ChangePasswordFrame.dispose();
            loginaspatient.setVisible(true);
        }
        else if(j==-1){
            ChangePasswordFrame.dispose();
            loginasdoctor.setVisible(true);
        }
        else if(j==-2){
            ChangePasswordFrame.dispose();
            loginaspatient.setVisible(true);
        }
    }
    
    public static void gotointrofromHome(){
        Home.dispose();
        intro.setVisible(true);
    }

    public static void gotoPatientHomePageFromIntro(){
        intro.setVisible(false);
        HomeFrame=new JFrame("Home Page");
        HomeFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //HomeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
        HomeFrame.add(new HomeFrame().getPanel());
        //HomeFrame.setSize(700,800);
        HomeFrame.setBounds(400,35,700,800);
        HomeFrame.setResizable(false);
        HomeFrame.setVisible(true);
    }

    public static void returntoIntroFromPatientHome(){
        HomeFrame.dispose();
        intro.setVisible(true);
    }

    public static void returntoHomeFramewithoutAdd(){
        patient_no--;
        FormFrame.dispose();
        HomeFrame.setVisible(true);
    }

    public static void CreateFiles(){
        File patient_record=new File("patient_record.txt");
        File total=new File("patient_no.txt");
        File doctor_record=new File("doctor_record.txt");
        File total_doc=new File("doctorno.txt");
        File signin=new File("signin.txt");
        File patient_ac=new File("patient_account.txt");
        File comment=new File("comment.txt");
        try{
            patient_record.createNewFile();
            patient_ac.createNewFile();
            comment.createNewFile();
            if(total.createNewFile()){
                FileWriter fw=new FileWriter("patient_no.txt");
                fw.write("0");
                fw.close();
            }
            if(doctor_record.createNewFile()){
                FileWriter fw=new FileWriter("doctor_record.txt");
                fw.write("1"+"\n"+"DR. JOHN DOE"+"\n"+"45"+"\n"+"MALE"+"\n"+"O+"+"\n"+"MARRIED"+"\n"+"DHANMONDI"+"\n"+"11111-111111"+"\n"+"DHAKA"+"\n"+"doctor1"+"\n"+"doctor1"+"\n"+"01/04/2017"+"\n"+"0"+"\n");
                fw.write("2"+"\n"+"DR. TURNER GRAY"+"\n"+"50"+"\n"+"MALE"+"\n"+"A+"+"\n"+"MARRIED"+"\n"+"MIRPUR"+"\n"+"11111-122222"+"\n"+"DHAKA"+"\n"+"doctor2"+"\n"+"doctor2"+"\n"+"01/08/2016"+"\n"+"0"+"\n");
                fw.write("3"+"\n"+"DR. PAL MERAKTIS"+"\n"+"40"+"\n"+"MALE"+"\n"+"AB+"+"\n"+"MARRIED"+"\n"+"SHYAMOLI"+"\n"+"22221-111111"+"\n"+"DHAKA"+"\n"+"doctor3"+"\n"+"doctor3"+"\n"+"01/06/2017"+"\n"+"0"+"\n");
                fw.write("4"+"\n"+"DR. PIERCE NICHODY"+"\n"+"30"+"\n"+"MALE"+"\n"+"O+"+"\n"+"UNMARRIED"+"\n"+"MOHAMMADPUR"+"\n"+"44444-111111"+"\n"+"DHAKA"+"\n"+"doctor4"+"\n"+"doctor4"+"\n"+"01/10/2019"+"\n"+"0"+"\n");
                fw.write("5"+"\n"+"DR. ALISA TIALA"+"\n"+"25"+"\n"+"FEMALE"+"\n"+"B+"+"\n"+"UNMARRIED"+"\n"+"GULSHAN"+"\n"+"11111-133333"+"\n"+"DHAKA"+"\n"+"doctor5"+"\n"+"doctor5"+"\n"+"01/04/2017"+"\n"+"0"+"\n");
                fw.close();
            }
            if(total_doc.createNewFile()){
                FileWriter scan=new FileWriter("doctorno.txt");
                scan.write("5");/*connect kivabe kora holo*/
                scan.close();
            }
            if(signin.createNewFile())
            {
                FileWriter f=new FileWriter("signin.txt");
                f.write("admin"+"\n"+"admin"+"\n"+"Anika"+"\n"+"201914051"+"\n"+"Raiyan"+"\n"+"201914039"+"\n"+"admin2"+"\n"+"12345678"+"\n"+"admin3"+"\n"+"87654321"+"\n");
                f.close();
            }
        }catch(IOException e){
            System.out.println("Error creating File");
            System.exit(0);
        }
    }

    public static void StoreInArray(){
        File patient_record=new File("patient_record.txt");
        Scanner filereader=null;
        try {
            filereader=new Scanner(patient_record);
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
            System.exit(0);
        }
        while(filereader.hasNextLine()){
            patient entity=new patient();
            entity.id=Integer.parseInt(filereader.nextLine());
            entity.name=filereader.nextLine();
            entity.age=Integer.parseInt(filereader.nextLine());
            entity.gender=filereader.nextLine();
            entity.BG=filereader.nextLine();
            entity.marital_status=filereader.nextLine();
            entity.address=filereader.nextLine();
            entity.phn_no=filereader.nextLine();
            entity.total_admits=Integer.parseInt(filereader.nextLine());
            entity.Username=filereader.nextLine();
            entity.password=filereader.nextLine();
            for(int j=1;j<=entity.total_admits;j++){
                String disease_name=filereader.nextLine();
                entity.diseases.add(disease_name);
                disease_list.add(disease_name);
            }
            for(int j=1;j<=entity.total_admits;j++){
                String Medicine=filereader.nextLine();
                entity.medicines.add(Medicine);
            }
            for(int j=1;j<=entity.total_admits;j++){
                String doctor=filereader.nextLine();
                entity.consulted_doctor.add(doctor);
                doctor_list.add(doctor);
            }
            patient_list.add(entity);
        }
        filereader.close();

        File doctor_record=new File("doctor_record.txt");
        filereader=null;
        try {
            filereader=new Scanner(doctor_record);
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
            System.exit(0);
        }
        while(filereader.hasNextLine()){
            Doctor entity=new Doctor();
            entity.id=Integer.parseInt(filereader.nextLine());
            entity.name=filereader.nextLine();
            doctor_list.add(entity.name);
            entity.age=Integer.parseInt(String.valueOf(filereader.nextLine()));
            entity.gender=filereader.nextLine();
            entity.blood=filereader.nextLine();
            entity.marital=filereader.nextLine();
            entity.address=filereader.nextLine();
            entity.phone=filereader.nextLine();
            entity.city=filereader.nextLine();
            entity.username=filereader.nextLine();
            entity.password=filereader.nextLine();
            entity.joiningdate=filereader.nextLine();
            entity.appointments_no=Integer.parseInt(filereader.nextLine());
            for(int j=0;j< entity.appointments_no;j++){
                String name=filereader.nextLine();
                String t=filereader.nextLine();
                String d=filereader.nextLine();
                appoint app=new appoint();
                app.patient_name=name;
                app.date=d;
                app.time=t;
                entity.appointment_list.add(app);
            }
            doctors.add(entity);
        }
        filereader.close();
    }

    public static void ReadtheLatestID(){
        File total=new File("patient_no.txt");
        Scanner totalreader= null;
        try {
            totalreader = new Scanner(total);
        } catch (FileNotFoundException e) {
            System.out.println("An Error Occurred");
            System.exit(0);
        }
        patient_no=totalreader.nextInt();
        totalreader.close();

        File total_doc=new File("doctorno.txt");
        Scanner totalreader2= null;
        try {
            totalreader2 = new Scanner(total_doc);
        } catch (FileNotFoundException e) {
            System.out.println("An Error Occurred");
            System.exit(0);
        }
        doctor_no=totalreader2.nextInt();
        totalreader2.close();
    }

    public static void WriteInFile(){
        try {
            FileWriter fw = new FileWriter("patient_record.txt");
            for(int i=0;i<patient_list.size();i++){
                fw.write(patient_list.get(i).id+"\n"+patient_list.get(i).name+"\n"+patient_list.get(i).age+"\n"+patient_list.get(i).gender+"\n"+patient_list.get(i).BG+"\n"+patient_list.get(i).marital_status+"\n"+patient_list.get(i).address+"\n"+patient_list.get(i).phn_no+"\n"+patient_list.get(i).total_admits+"\n"+patient_list.get(i).Username+"\n"+patient_list.get(i).password+"\n");
                for(int j=0;j<patient_list.get(i).diseases.size();j++)
                    fw.write(patient_list.get(i).diseases.get(j)+"\n");
                for(int j=0;j<patient_list.get(i).diseases.size();j++)
                    fw.write(patient_list.get(i).medicines.get(j)+"\n");
                for(int j=0;j<patient_list.get(i).diseases.size();j++)
                    fw.write(patient_list.get(i).consulted_doctor.get(j)+"\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing in file");
        }
        try{
            FileWriter fw = new FileWriter("patient_no.txt",false);
            fw.write(Integer.toString(patient_no));
            fw.close();
        }catch(IOException e){
            System.out.println("Error writing in file");
        }

        try{
            FileWriter fw = new FileWriter("doctorno.txt",false);
            fw.write(Integer.toString(doctor_no));
            fw.close();
        }catch(IOException e){
            System.out.println("Error writing in file");
        }


        try{
            FileWriter fw = new FileWriter("doctor_record.txt");
            for(int i=0;i<doctors.size();i++){
                fw.write(doctors.get(i).id+"\n"+doctors.get(i).name+"\n"+doctors.get(i).age+"\n"+doctors.get(i).gender+"\n"+doctors.get(i).blood+"\n"+doctors.get(i).marital+"\n"+doctors.get(i).address+"\n"+doctors.get(i).phone+"\n"+doctors.get(i).city+"\n"+doctors.get(i).username+"\n"+doctors.get(i).password+"\n"+doctors.get(i).joiningdate+"\n"+doctors.get(i).appointments_no+"\n");
                for(int j=0;j<doctors.get(i).appointments_no;j++){
                    fw.write(doctors.get(i).appointment_list.get(j).patient_name+"\n"+doctors.get(i).appointment_list.get(j).time+"\n"+doctors.get(i).appointment_list.get(j).date+"\n");

                }

            }
            fw.close();
        }catch(IOException e){
            System.out.println("Error writing in file");
        }
    }

    public static int getID(){
        return patient_no;
    }

    public static int getdocID(){
        return doctor_no;
    }

    public static void DeleteIndex(int i){
        patient_list.remove(i);
    }

    public static ArrayList<patient> getPatientList(){
        return patient_list;
    }

    public static Set<String> getDiseaseList(){
        return disease_list;
    }

    public static Set<String> getDoctorList(){
        return doctor_list;
    }

    public static ArrayList<Doctor> getDoctors(){
        return doctors;
    }

    public static void gotologinasadmin(){
        login.dispose();
        loginasadmin=new JFrame("Log in as Admin");
        loginasadmin.setVisible(true);
        //loginasadmin.setSize(700,600);
        loginasadmin.setBounds(400,100,700,600);
        loginasadmin.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        loginasadmin.add(new loginasadmin().getpanel());
        loginasadmin.setVisible(true);
    }

    public static void gotologinasdoctor(){
        login.dispose();
        loginasdoctor=new JFrame("Log in as a Doctor");
        loginasdoctor.setVisible(true);
        //loginasdoctor.setSize(650,600);
        loginasdoctor.setBounds(400,100,650,600);

        loginasdoctor.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        loginasdoctor.add(new loginasdoctor().getpanel());
        loginasdoctor.setVisible(true);
    }

    public static void UpdateIndex(int i,String disease,String medicine,String doctor,int age,String address,String phn_no,int j){
        patient_list.get(i).diseases.add(disease);
        patient_list.get(i).medicines.add(medicine);
        patient_list.get(i).consulted_doctor.add(doctor);
        patient_list.get(i).total_admits=j;
        patient_list.get(i).age=age;
        patient_list.get(i).address=address;
        patient_list.get(i).phn_no=phn_no;
        WriteInFile();
    }

    public static void UpdateIndex(int i,int age,String address,String phn_no){
        patient_list.get(i).age=age;
        patient_list.get(i).address=address;
        patient_list.get(i).phn_no=phn_no;
        WriteInFile();
    }

    public static void callNewFormFrame(){
        patient_no++;
        HomeFrame.setVisible(false);
        FormFrame = new JFrame("Form Page");
        FormFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //FormFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
        //FormFrame.setSize(500,500);
        FormFrame.setBounds(400,100,700,600);

        FormFrame.add(new NewFormFrame().getPanel());
        FormFrame.setResizable(false);
        FormFrame.setVisible(true);
    }

    public static void GoBackToHomeFrame(patient entity,String disease_name){
        patient_list.add(entity);
        disease_list.add(disease_name);
        try{
            FileWriter fw=new FileWriter("patient_account.txt");
            fw.write(entity.name+"-"+entity.id+"\n"+entity.name+"-"+entity.id);
            fw.close();
        }catch(IOException e){
            System.out.println("Error writing in File");
        }
        WriteInFile();
        JOptionPane.showMessageDialog(null,"New Record Created");
        FormFrame.dispose();
        HomeFrame.setVisible(true);
    }

    public static void seeDatabase(){
        HomeFrame.setVisible(false);
        TableFrame=new JFrame("Check List");
        TableFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //TableFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
        //TableFrame.setSize(1000,500);
        TableFrame.setBounds(450,100,1000,500);

        TableFrame.add(new AllDataTable().getPanel());
        TableFrame.setResizable(false);
        TableFrame.setVisible(true);
    }

    public static void returntoHomeFromTable(){
        TableFrame.dispose();
        HomeFrame.setVisible(true);
    }

    public static void callDeleteFrame(){
        HomeFrame.setVisible(false);
        DeleteFrame = new JFrame("Delete Form");
        DeleteFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //DeleteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
        //DeleteFrame.setSize(300,200);
        DeleteFrame.setBounds(450,100,500,500);

        DeleteFrame.add(new Delete_Frame().getPanel());
        DeleteFrame.setResizable(false);
        DeleteFrame.setVisible(true);
    }

    public static void returntoHomeFromDelete(){
        DeleteFrame.dispose();
        WriteInFile();
        HomeFrame.setVisible(true);
    }

    public static void callSearchFrame(){
        HomeFrame.setVisible(false);
        SearchFrame = new JFrame("Search");
        SearchFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //DeleteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
        //SearchFrame.setSize(300,200);
        SearchFrame.setBounds(450,100,500,500);

        SearchFrame.add(new Search_Frame().getPanel());
        SearchFrame.setResizable(false);
        SearchFrame.setVisible(true);
    }

    public static void returntoHomeFromSearch(){
        SearchFrame.dispose();
        HomeFrame.setVisible(true);
    }

    public static void showPatientinfo(int i){
        SearchFrame.setVisible(false);
        Patient_Info_Frame=new JFrame("Patient Information");
        Patient_Info_Frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //TableFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
        //Patient_Info_Frame.setSize(800,800);
        Patient_Info_Frame.setBounds(450,35,850,800);

        Patient_Info_Frame.add(new Patient_Info(i).getPanel());
        Patient_Info_Frame.setResizable(false);
        Patient_Info_Frame.setVisible(true);
    }

    public static void returnToSearchFromPatientInfo(){
        Patient_Info_Frame.dispose();
        SearchFrame.setVisible(true);
    }

    public static void callUpdateFrame(){
        HomeFrame.setVisible(false);
        UpdateFrame = new JFrame("Update");
        UpdateFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //DeleteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
        //UpdateFrame.setSize(300,200);
        UpdateFrame.setBounds(450,100,500,500);

        UpdateFrame.add(new Update_Frame().getPanel());
        UpdateFrame.setResizable(false);
        UpdateFrame.setVisible(true);
    }

    public static void returntoHomeFromUpdate(){
        UpdateFrame.dispose();
        HomeFrame.setVisible(true);
    }

    public static void showUpdateForm(int i){
        UpdateFrame.setVisible(false);
        UpdateFormFrame = new JFrame("Update Form");
        UpdateFormFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //DeleteFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //Gives you full screen gui
        //UpdateFormFrame.setSize(600,500);
        UpdateFormFrame.setBounds(450,100,650,500);

        UpdateFormFrame.add(new Update_Form(i).getPanel());
        UpdateFormFrame.setResizable(false);
        UpdateFormFrame.setVisible(true);
    }
    public static void returntoUpdateFromUpdateForm(){
        UpdateFormFrame.dispose();
        UpdateFrame.setVisible(true);
    }

    public static void returntoHomeFromUpdateFrame(){
        UpdateFormFrame.dispose();
        UpdateFrame.dispose();
        HomeFrame.setVisible(true);
    }
///DOCTOR PART

    public static void gotologinaspatientfromlogin(){
        login.dispose();
        loginaspatient=new JFrame("Log in as patient");
        loginaspatient.setVisible(true);
        loginaspatient.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //loginaspatient.setSize(870,750);
        loginaspatient.setBounds(450,50,870,750);

        loginaspatient.add(new loginaspatient().getpanel());
        loginaspatient.setVisible(true);
    }

    public static void gotointro(){
        loginasadmin.dispose();
        intro=new JFrame("Introduction");
        intro.setVisible(true);
        intro.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //intro.setSize(850,550);
        intro.setBounds(450,100,850,550);

        intro.add(new intro().getpanel());
        intro.setVisible(true);

    }

    public static void gotoHomefromintro(){
        intro.setVisible(false);
        Home=new JFrame("Home");
        Home.setVisible(true);
        //Home.setSize(730,870);
        Home.setBounds(450,0,730,850);
        Home.setResizable(false);
        Home.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Home.add(new Home().getpanel());
        Home.setVisible(true);
    }

    public static void gotologinfromloginasadmin(){
        loginasadmin.dispose();
        login=new JFrame("Starting Frame");
        login.setVisible(true);
        //login.setSize(800,600);
        login.setBounds(450,100,800,600);

        login.add(new login().getpanel());
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.setVisible(true);
    }

    public static void gotologinfromintro(){
        intro.dispose();
        login=new JFrame("Log in");
        login.setVisible(true);
        //login.setSize(800,600);
        login.setBounds(450,100,800,600);

        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.add(new login().getpanel());
        login.setVisible(true);
    }

    public static void calldoctorinfo() {
        doctor_no++;
        Home.setVisible(false);
        doctorinfo=new JFrame("Form Page");
        doctorinfo.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //doctorinfo.setSize(700,600);
        doctorinfo.setBounds(450,50,700,600);

        doctorinfo.add(new doctorinfo().getPanel());
        doctorinfo.setVisible(true);
    }

    public static void callSearch(){
        Home.setVisible(false);
        search=new JFrame("Search Any Doctor");
        //search.setSize(500,500);
        search.setBounds(450,100,500,500);

        search.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        search.add(new search().getpanel());
        search.setVisible(true);
    }

    public static void callDelete(){
        Home.setVisible(false);
        delete=new JFrame("Delete Doctor Info");
        //delete.setSize(500,500);
        delete.setBounds(450,100,500,500);

        delete.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        delete.add(new delete().getpanel());
        delete.setVisible(true);
    }

    public static void callupdate(){
        Home.dispose();
        update=new JFrame("Update Id");
        update.setVisible(true);
        update.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //update.setSize(500,500);
        update.setBounds(450,100,500,500);

        update.add(new update().getpanel());
        update.setVisible(true);
    }

    public static void gotologinfromHome(){
        Home.dispose();
        login=new JFrame("Log in");
        login.setVisible(true);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //login.setSize(800,600);
        login.setBounds(450,100,800,600);

        login.add(new login().getpanel());
        login.setVisible(true);
    }

    public static void returntohomefrominputframe(Doctor one)
    {
        doctors.add(one);
        WriteInFile();
        /*try{
            FileWriter file=new FileWriter("doctorsignin.txt");
            file.write("D"+one.id+"\n"+"D"+one.id+"\n");
        }catch(Exception e){
            System.out.println(e);
        }*/
        doctorinfo.dispose();
        Home.setVisible(true);
    }

    public static void returnfromdoctorinfotohome(){
        doctor_no--;
        doctorinfo.dispose();
        Home.setVisible(true);
    }

    public static void showdoctordetails(int i){
        search.setVisible(false);
        view=new JFrame("View the Details of Doctor");
        //view.setSize(1000,800);
        view.setBounds(450,100,1000,800);

        view.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        view.add(new view(i).getPanel());
        view.setVisible(true);
    }

    public static void returnHomefromSearch(){
        search.dispose();
        Home.setVisible(true);
    }

    public static void returnSearchFromview(){
        view.dispose();
        search.setVisible(true);
    }

    public static void returnHomeformupdate(){
        update.dispose();
        Home.setVisible(true);
    }

    public static void callupdateform(int i){
        update.dispose();
        updateform=new JFrame("Update form of doctor");
        updateform.setVisible(true);
        updateform.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //updateform.setSize(1000,800);
        updateform.setBounds(450,100,1000,800);

        updateform.add(new updateform(i).getPanel());
        updateform.setVisible(true);
    }

    public static void updateindex(int i,int age,String address,String city,String phone,String username,String password){
        doctors.get(i).age=age;
        doctors.get(i).address=address;
        doctors.get(i).city=city;
        doctors.get(i).phone=phone;
        doctors.get(i).username=username;
        doctors.get(i).password=password;
    }

    public static void returnHomefromupdateform(){
        update.dispose();
        updateform.dispose();
        Home.setVisible(true);
    }

    public static void returnfromupdateformtoupdate(){
        updateform.dispose();
        update.setVisible(true);
    }

    public static void Deleteid(int i) {
        doctors.remove(i);
    }

    public static void returnHomefromdelete(){
        delete.dispose();
        WriteInFile();
        Home.setVisible(true);
    }

    public static void gotodoctorinfofromloginasdoctor(int i){
        loginasdoctor.dispose();
        view_3=new JFrame("Doctor Home Frame");
        view_3.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //view_3.setSize(1000,800);
        view_3.setBounds(450,100,1000,800);

        view_3.add(new view_3(i).getpanel());
        view_3.setVisible(true);
    }

    public static void gotologinformloginasdoctor(){
        loginasdoctor.dispose();
        login=new JFrame("Starting Frame");
        //login.setSize(800,600);
        login.setBounds(450,100,800,600);

        login.add(new login().getpanel());
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.setVisible(true);
    }

    public static void gotologinfromdoctorinfoview(){
        view_3.dispose();
        login=new JFrame("Starting Frame");
        //login.setSize(800,600);
        login.setBounds(450,100,800,600);

        login.add(new login().getpanel());
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.setVisible(true);
    }

    public static void gotologinfromloginaspatient(){
        loginaspatient.dispose();
        login=new JFrame("Log in");
        login.setVisible(true);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //login.setSize(800,600);
        login.setBounds(450,100,800,600);

        login.add(new login().getpanel());
        login.setVisible(true);
    }
    public static void gotoHomefromdeletedoctor(){
        delete.dispose();
        Home=new JFrame("Home");
        Home.setVisible(true);
       // Home.setSize(730,870);
        Home.setBounds(450,100,730,870);

        Home.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Home.add(new Home().getpanel());
        Home.setVisible(true);
    }
    public static void gotoview3frompatientinfo3(){
        Patient_Info3.dispose();
        view_3.setVisible(true);
    }
    public static void gotopatientinfo3fromview3(int k){
        view_3.setVisible(false);
        Patient_Info3=new JFrame("Patient Information");

        Patient_Info3.setVisible(true);
        //Patient_Info3.setSize(800,800);
        Patient_Info3.setBounds(450,100,800,800);

        Patient_Info3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Patient_Info3.add(new Patient_Info3(k).getpanel());
        Patient_Info3.setVisible(true);
    }

    public static void gotoPatient4Fromloginaspatient(int k){
        loginaspatient.dispose();
        Patient_Info4=new JFrame("Patient Information");
        Patient_Info4.setVisible(true);
        //Patient_Info4.setSize(800,800);
        Patient_Info4.setBounds(450,35,850,800);

        Patient_Info4.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Patient_Info4.add(new Patient_Info4(k).getpanel());
        Patient_Info4.setVisible(true);
    }

    public static void gotologinfrompatientinfo4(){
        Patient_Info4.dispose();
        login=new JFrame("Log in");
        login.setVisible(true);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //login.setSize(800,600);
        login.setBounds(450,100,800,600);

        login.add(new login().getpanel());
        login.setVisible(true);
    }
    public static void gotoRecord(int i,String a){
        if(i==1){
            view_3.setVisible(false);
        }else if(i==2){
            intro.setVisible(false);
        } else{
            Patient_Info4.setVisible(false);
        }
        Comment=new JFrame("Comment Box");
        Comment.setVisible(true);
        Comment.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //Comment.setSize(800,600);
        Comment.setBounds(450,100,800,600);

        Comment.add(new Comment(i,a).getpanel());
        Comment.setVisible(true);
    }

   public static void backfromreview(int i){
        if(i==0){
            Comment.setVisible(false);
            Patient_Info4.setVisible(true);
        }
        else if(i==2){
            Comment.setVisible(false);
            intro.setVisible(true);
        }
        else
        {
            Comment.setVisible(false);
            view_3.setVisible(true);
        }
   }
   public static void gotoappointmentfromintro(){
       intro.setVisible(false);
       appointment=new JFrame("Appoinment Frame");
       //appointment.setSize(800,600);
       appointment.setBounds(450,100,800,600);

       appointment.add(new appointment().getpanel());
       appointment.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       appointment.setVisible(true);
   }
   public static void returntointrofromappoinment(){
        appointment.dispose();
        intro.setVisible(true);
   }
   public static void addAppointment(String d,String p,String t,String da){
        for(int i=0;i<doctors.size();i++){
            if(doctors.get(i).name.equals(d)){
                doctors.get(i).appointments_no++;
                appoint app=new appoint();
                app.patient_name=p;
                app.date=da;
                app.time=t;
                doctors.get(i).appointment_list.add(app);
            }
        }
   }
   public static void deleteappointment(int i,int k){
        doctors.get(i).appointment_list.remove(k);
        doctors.get(i).appointments_no--;
   }
   public static void gotointrofromappointment(){
        appointment.dispose();
       intro=new JFrame("Introduction");
       intro.setVisible(true);
       intro.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
       //intro.setSize(850,550);
       intro.setBounds(450,100,850,550);

       intro.add(new intro().getpanel());
       intro.setVisible(true);
    }
}
