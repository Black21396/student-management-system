/**
 * StudentService.java
 *
 * @author Fadi Salameh
 * @since 05.02.2025
 */

package net.fadi.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fadi.student.entity.Student;
import net.fadi.student.repository.StudentRepository;

@Service
public class StudentService
{
  @Autowired
  private StudentRepository studentRepository;
  
  public List<Student> getStudents()
  {
    return this.studentRepository.findAll();
  }
  
  public void saveStudents(List<Student> aStudents)
  {
    this.studentRepository.saveAll(aStudents);
  }
  
  public void saveStudent(Student aStudent)
  {
    this.studentRepository.save(aStudent);
  }

  public Student findById(Long aId)
  {
    Optional<Student> tStudent = this.studentRepository.findById(aId);
    
    if (tStudent.isPresent())
      return tStudent.get();
    return null;
  }

  public void deleteById(Long aId)
  {
    Student tStudent = findById(aId);
    
    if (tStudent != null)
      this.studentRepository.delete(tStudent);
  }
  
}
