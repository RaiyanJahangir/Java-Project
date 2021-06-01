import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame {
    private JPanel Home_Page;
    private JButton WriteButton;
    private JButton ReadButton;
    private JButton UpdateButton;
    private JButton DeleteButton;
    private JButton SearchButton;
    private JButton GoBack;

    public HomeFrame() {
        WriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.callNewFormFrame();
            }
        });
        ReadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.seeDatabase();
            }
        });
        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.callUpdateFrame();
            }
        });
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.callDeleteFrame();
            }
        });
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.callSearchFrame();
            }
        });
        GoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.returntoIntroFromPatientHome();
            }
        });
    }

    JPanel getPanel(){
        return Home_Page;
    }
}
