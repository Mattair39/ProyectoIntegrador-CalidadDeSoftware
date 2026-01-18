package com.studentmanager.repository;

import com.studentmanager.model.Student;
import java.util.List;

/**
 * Interfaz de repositorio para operaciones de almacenamiento de Student. Esta
 * interfaz sigue OCP permitiendo
 * diferentes implementaciones sin modificar el código cliente.
 */
public interface StudentRepository {
  /**
   * Agrega un estudiante al repositorio.
   *
   * @param student el estudiante a agregar
   */
  void add(Student student);

  /**
   * Recupera todos los estudiantes.
   *
   * @return lista de todos los estudiantes
   */
  List<Student> findAll();

  /**
   * Busca un estudiante por nombre.
   *
   * @param name el nombre del estudiante
   * @return el estudiante, o null si no se encuentra
   */
  Student findByName(String name);

  /**
   * Retorna el número total de estudiantes.
   *
   * @return conteo de estudiantes
   */
  int count();

  /**
   * Remueve un estudiante por nombre y calificación.
   *
   * @param name  el nombre del estudiante
   * @param grade la calificación del estudiante
   * @return true si fue removido, false en caso contrario
   */
  boolean remove(String name, double grade);
}
