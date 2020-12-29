import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Comment {
    private JButton postButton;
    private JTextArea textArea1;
    private JTextArea writeNewThingsTextArea;
    private JLabel label;
    private JPanel mainpanel;
    private JButton goBackButton;
    private JFrame Patient_Info4 ;
    private JFrame view_3;

    public Comment(int i,String a) {
        File fw=new File("comment.txt");
        Scanner scan=null;
        try{
            scan=new Scanner(fw);
        }catch(FileNotFoundException s){
            System.out.println(s);
        }
        while(scan.hasNextLine()){
            if(textArea1.getText().equals("")) {

                textArea1.setText(scan.nextLine());
            }else{
                textArea1.setText(textArea1.getText() + "\n" + scan.nextLine());

            }
        }
        scan.close();

        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textArea1.getText().equals("")){
                    textArea1.setText(a+" :"+"\n"+"\n"+writeNewThingsTextArea.getText());
                }
                else {
                    textArea1.setText(textArea1.getText() + "\n" + "\n" + "\n" + a + " :" + "\n" + "\n" + writeNewThingsTextArea.getText());
                }
                try {
                    FileWriter file = new FileWriter("comment.txt");
                    file.write(textArea1.getText());
                    file.close();
                }catch(IOException r){
                    System.out.println(r);
                }

            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.backfromreview(i);
            }
        });
    }

    JPanel getpanel(){
        return mainpanel;
    }
}
