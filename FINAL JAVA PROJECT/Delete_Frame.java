import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Delete_Frame {
    private JTextField textField1;
    private JButton OKButton;
    private JButton go_BackButton;
    private JPanel DeletePanel;

    public Delete_Frame() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<patient> patient_list=new ArrayList<patient>(Main.getPatientList());
                boolean flag=false;
                try{
                    int a=Integer.parseInt(textField1.getText());
                    int b=JOptionPane.showConfirmDialog(null, "Delete the ID?");
                    if(b==0) {
                        for (int i = 0; i < patient_list.size(); i++) {
                            if (a == patient_list.get(i).id) {
                                flag=true;
                                Main.DeleteIndex(i);
                                JOptionPane.showMessageDialog(null,"ID Successfully Deleted");
                                Main.returntoHomeFromDelete();
                            }
                        }
                        if(!flag){
                           throw new IDNotFoundException("ID doesn't Exist");
                        }

                    }
                }catch(NumberFormatException f){
                    JOptionPane.showMessageDialog(null,"Invalid ID given. Give a Proper one");
                }catch(IDNotFoundException g){
                    JOptionPane.showMessageDialog(null,"ID doesn't exist");
                }
            }
        });
        go_BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returntoHomeFromDelete();
            }
        });
    }

    JPanel getPanel(){
        return DeletePanel;
    }
}
