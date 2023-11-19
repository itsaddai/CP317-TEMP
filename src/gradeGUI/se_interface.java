package gradeGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class se_interface implements ActionListener {
	
	JTextField output;
    JFrame window;
    JPanel panel1;
    JPanel panel2;
    JPanel panelF;
    JPanel panelOut;
    JLabel file_1;
    JLabel file_2;
    JLabel folder_1;
    JLabel out_1;
    JPanel panel3;
    JPanel filePanel;
    JPanel submitPanel;
    Holder h = Holder.getHolder();

    public se_interface() {
        window = new JFrame();// CREATE THE WINDOW
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

        file_1 = new JLabel("Course File:");
        file_2 = new JLabel("Name File:");
        folder_1 = new JLabel("Folder:");
        out_1 = new JLabel("Output:");

        JButton open = new JButton("Open Course File");
        open.setPreferredSize(new Dimension(150, 25));
        open.addActionListener(this);

        JButton open2 = new JButton("Open Name File");
        open2.setPreferredSize(new Dimension(150, 25));
        open2.addActionListener(this);
        
        JButton close = new JButton("Choose Output Folder");
        close.setPreferredSize(new Dimension(150, 25));
        close.addActionListener(this);
        
        output = new JTextField("Output", 40);
        output.setPreferredSize(new Dimension(100, 25));

        JButton submit = new JButton("Submit");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.setPreferredSize(new Dimension(150, 25));
        submit.addActionListener(this);
        filePanel = new JPanel(new GridLayout(1, 3, 10, 10));

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel1.add(file_1);
        panel1.add(open);

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.add(file_2);
        panel2.add(open2);
        
        panelF = new JPanel();
        panelF.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelF.add(folder_1);
        panelF.add(close);

        filePanel.add(panel1);
        filePanel.add(panel2);
        filePanel.add(panelF);

        submitPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        
        panelOut = new JPanel();
        panelOut.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelOut.add(out_1);
        panelOut.add(output);
        
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel3.add(submit);

        submitPanel.add(panelOut);
        submitPanel.add(panel3);
        
        window.add(filePanel);
        window.add(submitPanel);
        

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
    
            if (clickedButton.getText().equals("Open Course File")) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(window);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Course file selected: " + selectedFile.getAbsolutePath());
                    h.setCourseFile(selectedFile.getAbsolutePath());
                }
            } else if (clickedButton.getText().equals("Open Name File")) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(window);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Name file selected: " + selectedFile.getAbsolutePath());
                    h.setNameFile(selectedFile.getAbsolutePath());
                }
            } else if (clickedButton.getText().equals("Choose Output Folder")) {
                JFileChooser folderChooser = new JFileChooser();
                folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = folderChooser.showOpenDialog(window);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFolder = folderChooser.getSelectedFile();
                    System.out.println("Output folder selected: " + selectedFolder.getAbsolutePath());
                    h.setOutputFolderPath(selectedFolder.getAbsolutePath());
                }
            } else if (clickedButton.getText().equals("Submit")) {
                System.out.println("Submit button clicked");
                h.setOutputFileName(output.getText());
                System.out.println("Course File: " + h.getCourseFileLoc());
                System.out.println("Name File: " + h.getNameFileLoc());
                System.out.println("Output Folder: " +  h.getOutputFolderPath());
                System.out.println("Output File: " +  h.getOutputFileName());
                System.out.println("Preparing Files...");
                
                //Make the output
                h.generateOutput();
                
                System.out.println("Output created!");
            }
        }
    }
}