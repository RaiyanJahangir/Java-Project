import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class search {
    private JButton OKButton;
    private JTextField textField1;
    private JButton goBackButton;
    private JPanel MainPanel;

    public search() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int a=Integer.parseInt(textField1.getText());
                    //System.out.println(a);
                    ArrayList<Doctor> doctorlist=new ArrayList<Doctor>(Main.getDoctors());
                    int flag=0;
                    int i;
                    for(i=0;i<doctorlist.size();i++){
                        //System.out.println("Wrong");
                        //System.out.println(i);
                        if(a==(doctorlist.get(i).id)){
                            //System.out.println("Right");
                            //System.out.println(i);
                            flag=1;
                            break;
                        }
                    }
                    System.out.println(flag);
                    if(flag==1){
                        System.out.println(i);
                        Main.showdoctordetails(i);
                    }
                    else
                    {
                        throw new IDNotFoundException("Id is not present");
                    }
                }catch (NumberFormatException p){
                    //p.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Not a valid ID");
                }catch (IDNotFoundException r){
                    JOptionPane.showMessageDialog(null,"ID is not present");

                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returnHomefromSearch();
            }
        });
    }

    JPanel getpanel()
    {
        return MainPanel;
    }
}
