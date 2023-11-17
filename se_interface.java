import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class se_interface implements ActionListener {

    JFrame window;
    JPanel panel1;
    JPanel panel2;
    JLabel file_1;
    JLabel file_2;
    JPanel panel3;
    JPanel filePanel;

    public se_interface() {
        window = new JFrame();// CREATE THE WINDOW
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

        file_1 = new JLabel("File 1:");
        file_2 = new JLabel("File 2:");

        JButton open = new JButton("Open File");
        open.setPreferredSize(new Dimension(150, 25));
        open.addActionListener(this);

        JButton open2 = new JButton("Open Second File");
        open2.setPreferredSize(new Dimension(150, 25));
        open2.addActionListener(this);

        JButton submit = new JButton("Submit");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.setPreferredSize(new Dimension(150, 25));
        submit.addActionListener(this);
        filePanel = new JPanel(new GridLayout(1, 2, 10, 10));

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel1.add(file_1);
        panel1.add(open);

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.add(file_2);
        panel2.add(open2);

        filePanel.add(panel1);
        filePanel.add(panel2);

        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel3.add(submit);

        window.add(filePanel);
        window.add(panel3);
        

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("CP317 Software Engineering");
        window.setSize(600, 400);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        new se_interface();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JButton) {
            JButton clickedButton = (JButton) source;
    
            if (clickedButton.getText().equals("Open File")) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(window);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("First file selected: " + selectedFile.getAbsolutePath());
                }
            } else if (clickedButton.getText().equals("Open Second File")) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(window);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Second file selected: " + selectedFile.getAbsolutePath());
                }
            } else if (clickedButton.getText().equals("Submit")) {
                System.out.println("Submit button clicked");
            }
        }
    }
}