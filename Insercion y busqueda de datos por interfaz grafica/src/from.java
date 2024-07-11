import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class from {
    public JTextArea Titulo;
    public JTextField ingresoNombre;
    public JTextField ingresoCedula;
    public JTextField b1;
    public JTextField b2;
    public JButton registrar;
    public JButton buscar;
    public JTextArea ingreseElNombreDelTextArea;
    public JTextArea ingreseLaCedulaDelTextArea;
    public JTextArea ingreseLaNotaDelTextArea;
    public JTextArea ingreseLaNotaDelTextArea1;
    public JPanel panel1;
    public JLabel mostrar;
    public JTextField buscarCedula;


    public from() {
        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreStr = ingresoNombre.getText();
                String cedulaStr = ingresoCedula.getText();
                double b1Val = Double.parseDouble(b1.getText());
                double b2Val = Double.parseDouble(b2.getText());

                Estudiante estudiante = new Estudiante(nombreStr, cedulaStr, b1Val, b2Val);

                String url = "jdbc:mysql://localhost:3306/ESTUDIANTE";
                String user = "root";
                String password = "123456";

                String sql = "INSERT INTO estudiante (nombre, cedula, b1, b2) VALUES (?, ?, ?, ?)";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    PreparedStatement cadenaPreparada = connection.prepareStatement(sql);
                    cadenaPreparada.setString(1, estudiante.getNombre());
                    cadenaPreparada.setString(2, estudiante.getCedula());
                    cadenaPreparada.setDouble(3, estudiante.getB1());
                    cadenaPreparada.setDouble(4, estudiante.getB2());
                    cadenaPreparada.executeUpdate();

                    JOptionPane.showMessageDialog(panel1, "Se ha registrado el estudiante");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel1, "Error al registrar el estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedulaStr = buscarCedula.getText();

                String url = "jdbc:mysql://localhost:3306/ESTUDIANTE";
                String user = "root";
                String password = "123456";

                String sql = "SELECT * FROM estudiante WHERE cedula = ?";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    PreparedStatement cadenaPreparada = connection.prepareStatement(sql);
                    cadenaPreparada.setString(1, cedulaStr);
                    ResultSet resultSet = cadenaPreparada.executeQuery();

                    if (resultSet.next()) {
                        String nombre = resultSet.getString("nombre");
                        double b1 = resultSet.getDouble("b1");
                        double b2 = resultSet.getDouble("b2");
                        mostrar.setText("Nombre: " + nombre + "\nNota B1: " + b1 + "\nNota B2: " + b2);
                    } else {
                        mostrar.setText("Estudiante no encontrado");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel1, "Error al buscar el estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
