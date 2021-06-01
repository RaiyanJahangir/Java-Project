import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class update {
    private JButton okButton;
    private JButton goBackButton;
    private JTextField idtextField1;
    private JPanel MainPanel;
    ArrayList<Doctor> doctorlist=new ArrayList<Doctor>(Main.getDoctors());

    public update() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                if(idtextField1.getText().equals("")){
                    throw new IncompleteInformationException("Incomplete");
                }
                else{
                        int a=Integer.parseInt(idtextField1.getText());
                        int i,flag=0;
                        for(i=0;i<doctorlist.size();i++)
                        {
                            if(a==doctorlist.get(i).id){
                                flag=1;
                                break;
                            }
                        }
                        if(flag==1){
                            Main.callupdateform(i);
                        }
                        else{
                            throw new IDNotFoundException("ID does not exist");
                        }

                    }

                }catch (NumberFormatException p){
                    //p.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Not a valid ID");
                }catch (IDNotFoundException r){
                    JOptionPane.showMessageDialog(null,"ID is not present");

                }catch (IncompleteInformationException t){
                    JOptionPane.showMessageDialog(null,"Enter an ID to update","Incomplete information",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returnHomeformupdate();
            }
        });
    }

    JPanel getpanel(){
        return MainPanel;
    }
}
