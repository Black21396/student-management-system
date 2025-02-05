/**
 * StudentRepository.java
 *
 * @author Fadi Salameh
 * @since 05.02.2025
 */

package net.fadi.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.fadi.student.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{

}
