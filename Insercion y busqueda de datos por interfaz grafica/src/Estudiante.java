public class Estudiante {
    String nombre;
    String cedula;
    double b1;
    double b2;

    public Estudiante(String nombre, String cedula, double b1, double b2) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.b1 = b1;
        this.b2 = b2;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public double getB1() {
        return b1;
    }

    public double getB2() {
        return b2;
    }
}
