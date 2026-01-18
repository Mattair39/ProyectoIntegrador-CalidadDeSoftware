package com.studentmanager.ui;

import com.studentmanager.StudentManager;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interfaz de usuario de consola para el Sistema de Gestión de Estudiantes.
 * Esta clase sigue el SRP
 * (Principio de Responsabilidad Única) manejando solo la interacción de
 * consola. Delega la lógica
 * de negocio a StudentManager.
 */
public class ConsoleApp {
  private final StudentManager studentManager;
  private final Scanner scanner;

  /**
   * Crea una nueva aplicación de consola.
   *
   * @param studentManager el gestor de estudiantes a usar
   */
  public ConsoleApp(StudentManager studentManager) {
    this.studentManager = studentManager;
    this.scanner = new Scanner(System.in);
  }

  /** Inicia la aplicación de consola. */
  public void start() {
    displayWelcome();
    boolean running = true;

    while (running) {
      displayMenu();
      int option = getMenuOption();

      switch (option) {
        case 1:
          registerStudent();
          break;
        case 2:
          listStudents();
          break;
        case 3:
          removeStudent();
          break;
        case 4:
          running = false;
          displayGoodbye();
          break;
        default:
          displayError("Opción inválida. Por favor seleccione 1, 2, 3 o 4.");
          break;
      }
    }

    scanner.close();
  }

  /** Muestra el mensaje de bienvenida. */
  private void displayWelcome() {
    System.out.println("\n╔════════════════════════════════════════════╗");
    System.out.println("║   Sistema de Gestión de Estudiantes      ║");
    System.out.println("╚════════════════════════════════════════════╝");
  }

  /** Muestra el menú principal. */
  private void displayMenu() {
    System.out.println("\n┌─────────────────────────────────┐");
    System.out.println("│ MENÚ PRINCIPAL                  │");
    System.out.println("├─────────────────────────────────┤");
    System.out.println("│ 1. Registrar estudiante         │");
    System.out.println("│ 2. Listar estudiantes           │");
    System.out.println("│ 3. Eliminar estudiante          │");
    System.out.println("│ 4. Salir                        │");
    System.out.println("└─────────────────────────────────┘");
    System.out.print("Seleccione una opción: ");
  }

  /**
   * Obtiene la opción del menú desde la entrada del usuario.
   *
   * @return la opción seleccionada
   */
  private int getMenuOption() {
    try {
      int option = scanner.nextInt();
      scanner.nextLine(); // Consumir salto de línea
      return option;
    } catch (InputMismatchException e) {
      scanner.nextLine(); // Limpiar entrada inválida
      return -1; // Opción inválida
    }
  }

  /** Registra un nuevo estudiante. */
  private void registerStudent() {
    System.out.println("\n═══ Registrar Estudiante ═══");
    displayInputHelp();

    try {
      // Obtener nombre del estudiante
      System.out.print("\nIngrese el nombre del estudiante: ");
      String name = scanner.nextLine().trim();

      if (name.isEmpty()) {
        displayError("El nombre no puede estar vacío.");
        return;
      }

      // Obtener calificación del estudiante
      System.out.print("Ingrese la calificación (0-100): ");
      double grade = getValidGrade();

      // Agregar estudiante
      studentManager.addStudent(name, grade);
      displaySuccess(
          "✓ Estudiante registrado exitosamente: " + name + " (Calificación: " + grade + ")");

    } catch (InputMismatchException e) {
      scanner.nextLine(); // Clear invalid input
      displayError("La calificación debe ser un número válido.");
    } catch (IllegalArgumentException e) {
      displayError(e.getMessage());
    } catch (Exception e) {
      displayError("Error inesperado: " + e.getMessage());
    }
  }

  /** Muestra ayuda de entrada para el usuario. */
  private void displayInputHelp() {
    System.out.println("\n┌─────────────────────────────────────────┐");
    System.out.println("│ REGLAS DE VALIDACIÓN:                   │");
    System.out.println("├─────────────────────────────────────────┤");
    System.out.println("│ Nombre:                                 │");
    System.out.println("│  ✓ Solo letras y espacios              │");
    System.out.println("│  ✗ NO números (ejemplo: Juan123)       │");
    System.out.println("│  ✗ NO caracteres especiales (@, #, etc)│");
    System.out.println("│  ✓ Se permiten nombres duplicados      │");
    System.out.println("│                                         │");
    System.out.println("│ Calificación:                           │");
    System.out.println("│  ✓ Número entre 0 y 100                │");
    System.out.println("│  ✗ NO valores negativos                │");
    System.out.println("│  ✗ NO valores mayores a 100            │");
    System.out.println("└─────────────────────────────────────────┘");
  }

  /**
   * Obtiene y valida la calificación desde la entrada del usuario.
   *
   * @return la calificación validada
   * @throws IllegalArgumentException si la calificación está fuera de rango
   */
  private double getValidGrade() {
    double grade = scanner.nextDouble();
    scanner.nextLine(); // Consumir salto de línea

    if (grade < 0 || grade > 100) {
      throw new IllegalArgumentException(
          "La calificación debe estar entre 0 y 100. Valor ingresado: " + grade);
    }

    return grade;
  }

  /** Lista todos los estudiantes registrados. */
  private void listStudents() {
    System.out.println("\n═══ Lista de Estudiantes ===");
    try {
      studentManager.listStudents();
    } catch (Exception e) {
      displayError("Error al listar estudiantes: " + e.getMessage());
    }
  }

  /** Elimina un estudiante por nombre y calificación. */
  private void removeStudent() {
    System.out.println("\n═══ Eliminar Estudiante ═══");
    System.out.println("ℹ  Si hay estudiantes con el mismo nombre,");
    System.out.println("   especifique también la calificación para identificarlo.\n");

    try {
      // Obtener nombre del estudiante
      System.out.print("Ingrese el nombre del estudiante: ");
      String name = scanner.nextLine().trim();

      if (name.isEmpty()) {
        displayError("El nombre no puede estar vacío.");
        return;
      }

      // Obtener calificación del estudiante
      System.out.print("Ingrese la calificación del estudiante: ");
      double grade = getValidGrade();

      // Eliminar estudiante
      boolean removed = studentManager.removeStudent(name, grade);

      if (removed) {
        displaySuccess(
            "✓ Estudiante eliminado exitosamente: " + name + " (Calificación: " + grade + ")");
      } else {
        displayError(
            "⚠ No se encontró ningún estudiante con el nombre '"
                + name
                + "' y calificación "
                + grade);
      }

    } catch (InputMismatchException e) {
      scanner.nextLine(); // Clear invalid input
      displayError("La calificación debe ser un número válido.");
    } catch (Exception e) {
      displayError("Error al eliminar estudiante: " + e.getMessage());
    }
  }

  /**
   * Muestra un mensaje de éxito.
   *
   * @param message el mensaje a mostrar
   */
  private void displaySuccess(String message) {
    System.out.println("\n" + message);
  }

  /**
   * Muestra un mensaje de error.
   *
   * @param message el mensaje de error a mostrar
   */
  private void displayError(String message) {
    System.out.println("\n✗ ERROR: " + message);
  }

  /** Muestra el mensaje de despedida. */
  private void displayGoodbye() {
    System.out.println("\n╔════════════════════════════════════════════╗");
    System.out.println("║  ¡Gracias por usar el sistema!            ║");
    System.out.println("║  Hasta pronto.                            ║");
    System.out.println("╚════════════════════════════════════════════╝\n");
  }

  /**
   * Punto de entrada principal de la aplicación.
   *
   * @param args argumentos de línea de comandos (no utilizados)
   */
  public static void main(String[] args) {
    com.studentmanager.repository.StudentRepository repository =
        new com.studentmanager.repository.InMemoryStudentRepository();
    com.studentmanager.report.ReportGenerator reportGenerator =
        new com.studentmanager.report.ConsoleReportGenerator();
    StudentManager manager = new StudentManager(repository, reportGenerator);

    ConsoleApp app = new ConsoleApp(manager);
    app.start();
  }
}
