package com.studentmanager.report;

import com.studentmanager.model.Student;
import java.util.List;

/**
 * Interfaz para generar reportes de estudiantes. Esta interfaz sigue OCP
 * permitiendo diferentes formatos
 * de reportes sin modificar el código existente.
 */
public interface ReportGenerator {
  /**
   * Genera un reporte desde una lista de estudiantes.
   *
   * @param students la lista de estudiantes a reportar
   */
  void generate(List<Student> students);
}
