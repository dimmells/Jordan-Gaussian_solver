package GUI_form;

import system_jordan_solver.JordanEq;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by dimic on 20.03.2017.
 */
public class JordGUI extends JFrame {
    public static File input;
    public static File output;
    private JButton inputButton = new JButton("Відкрити файл");
    private JButton outputButton = new JButton("Зберегти у файл");
    private JLabel label = new JLabel("Операцію успішно завершенно");


    public JordGUI() {
        super("Жорданові перетворення");
        this.setBounds(100, 100, 250, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2));
        container.add(inputButton);
        container.add(outputButton);
        container.add(label);

        label.setVisible(false);
        label.setHorizontalAlignment(JLabel.CENTER);
        outputButton.addActionListener(new OutputButton());
        outputButton.setVisible(false);
        inputButton.addActionListener(new InputButton());
    }

    

    class InputButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            getFile(fileopen);
            inputButton.setVisible(false);
            outputButton.setVisible(true);
            label.setVisible(false);
        }

        public void getFile (JFileChooser fileopen) {
            int ret = fileopen.showDialog(null, "Вибрати файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                input = fileopen.getSelectedFile();

                System.out.println(input);
            }
        }
    }

    class OutputButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Зберегти у файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                output = fileopen.getSelectedFile();

                System.out.println(output);
            }
            JordanEq jEq = new JordanEq();
            try {
                jEq.getArr();
                jEq.processMatrix();
            }
            catch (Exception e2){}

            outputButton.setVisible(false);
            inputButton.setVisible(true);
            label.setVisible(true);
            try {
                wait(5 * 10000);
                label.setVisible(false);
            }
            catch (Exception exp) {}
        }
    }

}
