import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class delete {
    private JTextField idtextField1;
    private JButton deleteButton;
    private JButton goBackButton;
    private JPanel MainPanel;

    public delete() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int a=Integer.parseInt(idtextField1.getText());
                    ArrayList<Doctor> doctorlist=new ArrayList<Doctor>(Main.getDoctors());
                    int flag=0;
                    int i;
                    for(i=0;i<doctorlist.size();i++){
                        if(a==doctorlist.get(i).id){
                            flag=1;
                            break;
                        }
                    }
                    if(flag==1){
                        int p=JOptionPane.showConfirmDialog(null,"Delete Id?");
                        if(p==0)
                        {
                            JOptionPane.showMessageDialog(null,"Deleted ID successfully");
                            Main.Deleteid(i);
                            Main.returnHomefromdelete();
                        }


                    }
                    else{
                        throw new IDNotFoundException("ID is not present");
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
                Main.gotoHomefromdeletedoctor();
            }
        });
    }

    JPanel getpanel(){
        return MainPanel;
    }
}
