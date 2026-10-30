import EjercicioVentas.EjercicioVentas;

public class App {
    public static void main(String[] args) throws Exception {
        double META_SEMANAL = 10000.0;
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        double[] ventas = {1250.5, 980.0, 1430.75, 760.25, 2100.0, 2650.5, 1890.0};
        EjercicioVentas ejercicio = new EjercicioVentas(dias,ventas,META_SEMANAL);
    }
}
