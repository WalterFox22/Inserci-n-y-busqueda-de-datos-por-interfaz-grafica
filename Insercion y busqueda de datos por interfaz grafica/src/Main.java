import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Estudiantes");
        frame.setContentPane(new from().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.pack();
        frame.setVisible(true);
    }
}