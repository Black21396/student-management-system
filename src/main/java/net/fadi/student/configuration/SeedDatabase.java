/**
 * SeedDatabase.java
 *
 * add dummy data to database if it is empty
 * @author Fadi Salameh
 * @since 05.02.2025
 */

package net.fadi.student.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import net.fadi.student.entity.Student;
import net.fadi.student.service.StudentService;

@Component
public class SeedDatabase implements CommandLineRunner
{
  @Autowired
  private StudentService studentService;
  @Override
  public void run(String... args) throws Exception
  {
    List<Student> students = this.studentService.getStudents();
    if (students == null || students.isEmpty())
    {
      Student s1 = Student.builder()
          .firstName("Fadi")
          .lastName("salameh")
          .email("fadi@gmail.com")
          .build();
      Student s2 = Student.builder()
          .firstName("Iman")
          .lastName("salameh")
          .email("iman@gmail.com")
          .build();
      Student s3 = Student.builder()
          .firstName("Nour")
          .lastName("salim")
          .email("nour@gmail.com")
          .build();
      
      this.studentService.saveStudents(List.of(s1, s2, s3));
    }
    
  }

}
