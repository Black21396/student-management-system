/**
 * StudentController.java
 *
 * @author Fadi Salameh
 * @since 05.02.2025
 */

package net.fadi.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import net.fadi.student.entity.Student;
import net.fadi.student.service.StudentService;

@Controller
public class StudentController
{
  @Autowired
  private StudentService studentService;
  
  @GetMapping("/students")
  public ModelAndView getStudents(Model aModel)
  {
    aModel.addAttribute("students", this.studentService.getStudents());
    
    return new ModelAndView("students.html");
  }
  
  @GetMapping("/students/new")
  public ModelAndView addStudentForm(Model aModel)
  {
    aModel.addAttribute("student", new Student());
    
    return new ModelAndView("add_student.html");
  }
  
  @PostMapping("/students/new")
  public String addStudent(@ModelAttribute("student") Student aStudent)
  {
    try
    {
      this.studentService.saveStudent(aStudent);
    } catch (Exception e)
    {
      return "redirect:/students/new";
    }
    
     return "redirect:/students";
  }

}
