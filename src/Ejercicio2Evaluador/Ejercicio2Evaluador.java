package Ejercicio2Evaluador;

import java.util.Locale;

public class Ejercicio2Evaluador {
    enum Estado {
        APROBADO, RECUPERACION, REPROBADO
    }

    public static void main(String[] args) {
        String[] estudiantes = {"Ana", "Luis", "Sofia", "Carlos", "Valentina", "Mateo"};
        String[] evaluaciones = {"Quiz 1", "Quiz 2", "Taller", "Proyecto"};
        double[][] notas = {
            {4.5, 4.9, 3.8, 4.2},
            {3.5, 4.0, 4.9, 4.0},
            {2.5, 3.0, 2.8, 2.6},
            {3.0, 2.5, 3.2, 3.4},
            {5.0, 4.8, 4.6, 4.9},
            {2.0, 2.5, 1.8, 2.2}
        };
        double[] pesos = {0.20, 0.20, 0.25, 0.35};
        double[] asistencia = {0.95, 0.70, 0.88, 0.80, 1.00, 0.76};

        double[] promedios = new double[estudiantes.length];
        Estado[] estados = new Estado[estudiantes.length];

        System.out.println("=== RESULTADOS DEL CURSO ===");
        System.out.printf("%-15s %10s %12s %15s%n", "Estudiante", "Promedio", "Asistencia", "Estado");
        for (int i = 0; i < estudiantes.length; i++) {
            promedios[i] = promedioPonderado(notas[i], pesos);
            estados[i] = evaluar(promedios[i], asistencia[i]);
            System.out.printf(
                    Locale.US,
                    "%-15s %10.2f %10.0f%% %15s%n",
                    estudiantes[i],
                    promedios[i],
                    asistencia[i] * 100,
                    estados[i]);
        }

        int aprobados = 0;
        int recuperacion = 0;
        int reprobados = 0;
        for (Estado estado : estados) {
            switch (estado) {
                case APROBADO:
                    aprobados++;
                    break;
                case RECUPERACION:
                    recuperacion++;
                    break;
                case REPROBADO:
                    reprobados++;
                    break;
            }
        }
        System.out.printf(
                "Aprobados: %d | Recuperacion: %d | Reprobados: %d%n",
                aprobados,
                recuperacion,
                reprobados);

        System.out.println("=== ANALISIS POR EVALUACION ===");
        for (int j = 0; j < evaluaciones.length; j++) {
            double total = 0.0;
            int indiceMayor = 0;
            for (int i = 0; i < estudiantes.length; i++) {
                total += notas[i][j];
                if (notas[i][j] > notas[indiceMayor][j]) {
                    indiceMayor = i;
                }
            }
            double promedioEvaluacion = total / estudiantes.length;
            System.out.printf(
                    Locale.US,
                    "%s promedio %.2f | mejor: %s (%.1f)%n",
                    evaluaciones[j],
                    promedioEvaluacion,
                    estudiantes[indiceMayor],
                    notas[indiceMayor][j]);
        }

        int[] orden = new int[estudiantes.length];
        for (int i = 0; i < orden.length; i++) {
            orden[i] = i;
        }

        for (int i = 0; i < orden.length - 1; i++) {
            for (int j = 0; j < orden.length - 1 - i; j++) {
                if (promedios[orden[j]] < promedios[orden[j + 1]]) {
                    int temp = orden[j];
                    orden[j] = orden[j + 1];
                    orden[j + 1] = temp;
                }
            }
        }

        System.out.println("=== RANKING ===");
        for (int i = 0; i < orden.length; i++) {
            System.out.printf(
                    Locale.US,
                    "%d. %s %.2f%n",
                    i + 1,
                    estudiantes[orden[i]],
                    promedios[orden[i]]);
        }
    }

    static double promedioPonderado(double[] notasEst, double[] pesos) {
        double promedio = 0.0;
        for (int i = 0; i < notasEst.length; i++) {
            promedio += notasEst[i] * pesos[i];
        }
        return promedio;
    }

    static Estado evaluar(double promedio, double asistencia) {
        if (asistencia < 0.75) {
            return Estado.REPROBADO;
        } else if (promedio >= 3.0) {
            return Estado.APROBADO;
        } else if (promedio >= 2.5) {
            return Estado.RECUPERACION;
        }
        return Estado.REPROBADO;
    }
}
