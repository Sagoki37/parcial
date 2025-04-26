import java.util.InputMismatchException;
import java.util.Scanner;

public class RegistroVisitasInteractivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Variables del visitante
        int cedula = 0;
        byte edad = 0;
        long telefono = 0;
        char genero = ' ';
        boolean tieneCita = false;
        float temperatura = 0;
        double latitud = 0;

        // Captura de datos con manejo de errores
        try {
            System.out.println("Ingrese su número de cédula (int):");
            cedula = sc.nextInt();

            System.out.println("Ingrese su edad (byte):");
            edad = sc.nextByte();

            System.out.println("Ingrese su número de teléfono (long):");
            telefono = sc.nextLong();

            System.out.println("Ingrese su género (char):");
            genero = sc.next().charAt(0);

            System.out.println("¿Tiene cita previa? (true/false):");
            tieneCita = sc.nextBoolean();

            System.out.println("Ingrese su temperatura corporal (float):");
            temperatura = sc.nextFloat();

            System.out.println("Ingrese latitud de ubicación (double):");
            latitud = sc.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("⚠️ Error de entrada. Por favor, ingrese los datos correctamente.");
            return;
        }

        // Registro de visitas
        int[] tiemposEstadia = new int[7]; // Máximo 7 días
        short totalVisitas = 0;
        int acumuladorMinutos = 0;

        while (true) {
            System.out.println("\n=== Menú de Opciones ===");
            System.out.println("1. Registrar nueva visita");
            System.out.println("2. Mostrar estadísticas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Debe ingresar un número válido.");
                sc.next(); // limpiar el buffer
                continue;
            }

            if (opcion == 1) {
                if (totalVisitas >= 7) {
                    System.out.println("⚠️ Ya se ha alcanzado el máximo de visitas registradas.");
                    continue;
                }

                try {
                    System.out.println("Ingrese el número del departamento (short):");
                    short depa = sc.nextShort();

                    System.out.println("Ingrese el piso al que se dirige (byte):");
                    byte piso = sc.nextByte();

                    System.out.println("Ingrese el tiempo de estadía en minutos (int):");
                    int tiempo = sc.nextInt();

                    tiemposEstadia[totalVisitas] = tiempo;
                    acumuladorMinutos += tiempo;
                    totalVisitas++;

                    System.out.println("✅ Visita registrada correctamente.");

                } catch (InputMismatchException e) {
                    System.out.println("⚠️ Error al registrar visita. Asegúrese de ingresar los datos correctamente.");
                    sc.next(); // limpiar el buffer
                }

            } else if (opcion == 2) {
                if (totalVisitas == 0) {
                    System.out.println("⚠️ Aún no se ha registrado ninguna visita.");
                    continue;
                }

                float promedio = (float) acumuladorMinutos / totalVisitas;
                boolean esMayorEdad = edad >= 18;

                System.out.println("\n📊 Estadísticas del Visitante:");
                System.out.println("Cédula: " + cedula);
                System.out.println("Edad: " + edad + " (" + (esMayorEdad ? "Mayor de edad" : "Menor de edad") + ")");
                System.out.println("Teléfono: " + telefono);
                System.out.println("Género: " + genero);
                System.out.println("Temperatura: " + temperatura + "°C");
                System.out.println("Cita previa: " + (tieneCita ? "Sí" : "No"));
                System.out.println("Latitud: " + latitud);
                System.out.println("Visitas registradas: " + totalVisitas);
                System.out.println("Tiempos de estadía: ");
                for (int i = 0; i < totalVisitas; i++) {
                    System.out.println("  Día " + (i + 1) + ": " + tiemposEstadia[i] + " min");
                }
                System.out.printf("Promedio de estadía: %.2f min\n", promedio);

            } else if (opcion == 3) {
                System.out.println("👋 Saliendo del programa. ¡Gracias!");
                break;
            } else {
                System.out.println("⚠️ Opción no válida. Intente nuevamente.");
            }
        }

        sc.close();
    }
}
