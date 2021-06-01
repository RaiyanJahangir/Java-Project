import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Search_Frame {
    private JLabel SearchLabel;
    private JTextField textField1;
    private JButton OKButton;
    private JButton GoBackButton;
    private JPanel Main_Panel;

    public Search_Frame() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textField1.getText().equals("")) {
                        throw new IncompleteInformationException("Incomplete Information");
                    } else {
                        int a = Integer.parseInt(textField1.getText());
                        ArrayList<patient> patient_list = new ArrayList<patient>(Main.getPatientList());
                        boolean flag = false;
                        int i;
                        for (i = 0; i < patient_list.size(); i++) {
                            if (a == patient_list.get(i).id) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            throw new IDNotFoundException("ID Not Found");
                        } else {
                            Main.showPatientinfo(i);
                        }
                    }
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Not a valid ID");
                }catch(IncompleteInformationException g){
                    JOptionPane.showMessageDialog(null, "Write an ID to search");
                }catch(IDNotFoundException h){
                    JOptionPane.showMessageDialog(null, "ID doesn't exist");
                }
            }
        });
        GoBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returntoHomeFromSearch();
            }
        });
    }

    JPanel getPanel(){
        return Main_Panel;
    }
}
