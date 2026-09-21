import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class GestorImpresiones {
    
    public static void main(String[] args) {
        // Deque para pendientes (actúa como Cola FIFO usando offerLast y pollFirst)
        Deque<String> pendientes = new ArrayDeque<>();
        
        // Deque para historial (actúa como Pila LIFO usando push y pop)
        Deque<String> historial = new ArrayDeque<>();
        
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n========================================");
            System.out.println("       GESTOR DE IMPRESIONES (MENU)      ");
            System.out.println("========================================");
            System.out.println("1. Registrar documento (Entra al final)");
            System.out.println("2. Imprimir siguiente (Sale el más antiguo)");
            System.out.println("3. Guardar impresión (Queda en la cima)");
            System.out.println("4. Recuperar última (Vuelve al frente)");
            System.out.println("5. Mostrar estado actual");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nombre del documento a registrar: ");
                        String docRegistrar = scanner.nextLine();
                        if (!docRegistrar.trim().isEmpty()) {
                            pendientes.offerLast(docRegistrar);
                            System.out.println("-> Documento registrado exitosamente.");
                        } else {
                            System.out.println("-> El nombre no puede estar vacío.");
                        }
                        break;

                    case 2:
                        if (pendientes.isEmpty()) {
                            System.out.println("-> No hay documentos pendientes para imprimir.");
                        } else {
                            String docImprimir = pendientes.pollFirst();
                            historial.push(docImprimir);
                            System.out.println("-> Imprimiendo y guardando en historial: " + docImprimir);
                        }
                        break;

                    case 3:
                        System.out.print("Ingrese el nombre de la impresión a guardar en historial: ");
                        String docGuardar = scanner.nextLine();
                        if (!docGuardar.trim().isEmpty()) {
                            historial.push(docGuardar);
                            System.out.println("-> Documento guardado en la cima del historial.");
                        } else {
                            System.out.println("-> El nombre no puede estar vacío.");
                        }
                        break;

                    case 4:
                        if (historial.isEmpty()) {
                            System.out.println("-> El historial está vacío, no se puede recuperar nada.");
                        } else {
                            String docRecuperar = historial.pop();
                            pendientes.addFirst(docRecuperar);
                            System.out.println("-> Documento recuperado del historial y devuelto al frente de pendientes: " + docRecuperar);
                        }
                        break;

                    case 5:
                        System.out.println("\n--- ESTADO ACTUAL DE LAS ESTRUCTURAS ---");
                        System.out.println("Pendientes (Cola): " + pendientes);
                        System.out.println("Historial  (Pila): " + historial);
                        break;

                    case 6:
                        System.out.println("Saliendo del sistema de impresión...");
                        break;

                    default:
                        System.out.println("Opción inválida. Por favor, elija un número entre 1 y 6.");
                }
            } else {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                scanner.next(); // Limpiar entrada incorrecta
            }

        } while (opcion != 6);

        scanner.close();
    }
}