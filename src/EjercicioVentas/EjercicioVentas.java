package EjercicioVentas;

import java.util.Locale;

public class EjercicioVentas {
    
    
    public EjercicioVentas(String[] dias,double[] ventas, double meta_semanal){
        System.out.println("=== REPORTE DE VENTAS SEMANAL ===");
        System.out.printf("%-40s %12s %12s%n", "Dia", "Venta", "Nivel");
        

        if (dias == null || ventas == null || dias.length == 0 || dias.length != ventas.length) {
            return;
        }

        double promedio = calcularPromedio(ventas);
        for (int i = 0; i < dias.length; i++) {
            String nivel = clasificar(ventas[i], promedio);
            System.out.printf("%-40s %12.2f %12s%n", dias[i], ventas[i], nivel);
        }
        System.out.printf(
                "%nTotal semanal: %12.2f%n" +
                "Promedio diario: %12.2f%n" +
                "Mejor dia: %s (%.2f)%n" +
                "Peor dia: %s (%.2f)%n" +
                "Dias por encima del promedio: %d%n" +
                "Meta semanal: ",
                calcularTotal(ventas),
                promedio,
                dias[indiceMayor(ventas)], ventas[indiceMayor(ventas)],
                dias[indiceMenor(ventas)], ventas[indiceMenor(ventas)],
                indicePromedio(ventas, "EncimaPromedio"));
                metaSemanal(calcularTotal(ventas), meta_semanal);

    }

    void metaSemanal(double total, double meta_semanal){
        if (total >= meta_semanal) {
            System.out.printf(Locale.US, "Meta cumplida. Superaste la meta por %.2f%n",total - meta_semanal);
        } else {
            System.out.printf(Locale.US, "Meta no cumplida. Faltaron %.2f%n",meta_semanal - total);
        }
    }


    int indicePromedio(double[] v, String opcion) {
        if (v == null || v.length == 0 || opcion == null) {
            return 0;
        }

        double promedio = calcularPromedio(v);
        int cantidad = 0;
        switch (opcion) {
            case "EncimaPromedio":
                for (double valor : v) {
                    if (valor > promedio) {
                        cantidad++;
                    }
                }
                break;
            case "MenosPromedio":
                for (double valor : v) {
                    if (valor < promedio) {
                        cantidad++;
                    }
                }
                break;
            default:
                return 0;
        }
        return cantidad;
    } 


    double calcularTotal(double[] v) {
        double total = 0.0;
        for (double valor : v) {
            total += valor;
        }
        return total;
    }

    double calcularPromedio(double[] v) {
        if (v == null || v.length == 0) {
            return 0.0;
        }
        return calcularTotal(v) / v.length;
    }

    int indiceMayor(double[] v) {
        if (v == null || v.length == 0) {
            return -1;
        }
        int indiceMayor = 0;
        for (int i = 1; i < v.length; i++) {
            if (v[i] > v[indiceMayor]) {
                indiceMayor = i;
            }
        }
        return indiceMayor;
    }

    int indiceMenor(double[] v) {
        if (v == null || v.length == 0) {
            return -1;
        }
        int indiceMenor = 0;
        for (int i = 1; i < v.length; i++) {
            if (v[i] < v[indiceMenor]) {
                indiceMenor = i;
            }
        }
        return indiceMenor;
    }

    String clasificar(double ventas, double promedio) {
        if (ventas >= (promedio * 1.2)) {
            return "ALTA";
        } else if (ventas < (promedio * 0.8)) {
            return "BAJA";
        }
        return "NORMAL";
    }
}
