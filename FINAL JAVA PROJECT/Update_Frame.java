import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Update_Frame {
    private JLabel UpdateLabel;
    private JTextField textField1;
    private JButton OKButton;
    private JButton goBackButton;
    private JPanel Update_Form;

    public Update_Frame() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Write an ID to search");
                }
                else{
                    try{
                        int a=Integer.parseInt(textField1.getText());
                        ArrayList<patient> patient_list=new ArrayList<patient>(Main.getPatientList());
                        boolean flag=false;
                        int i;
                        for(i = 0; i<patient_list.size(); i++){
                            if(a==patient_list.get(i).id){
                                flag=true;
                                break;
                            }
                        }
                        if(!flag){
                            JOptionPane.showMessageDialog(null,"ID doesn't exist");
                        }
                        else{
                            Main.showUpdateForm(i);
                        }
                    }catch(Exception f){
                        JOptionPane.showMessageDialog(null,"Not a valid ID");
                    }
                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returntoHomeFromUpdate();
            }
        });
    }

    JPanel getPanel(){
        return Update_Form;
    }
}
