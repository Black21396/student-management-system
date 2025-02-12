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
import org.springframework.web.bind.annotation.PathVariable;
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
  
  @GetMapping("/students/edit/{id}")
  public ModelAndView editStudentForm(@PathVariable("id") Long aId, Model aModel)
  {
    Student aStudent = this.studentService.findById(aId);
    aModel.addAttribute("student", aStudent);
    
    return new ModelAndView("edit_student.html");
  }
  
  @PostMapping("/students/edit/{id}")
  public String editStudent(@PathVariable("id") Long aId, @ModelAttribute("student") Student aStudent)
  {
    Student tStudent = this.studentService.findById(aId);
    
    if (tStudent != null)
    {
      tStudent.setFirstName(aStudent.getFirstName());
      tStudent.setLastName(aStudent.getLastName());
      tStudent.setEmail(aStudent.getEmail());
      
      this.studentService.saveStudent(aStudent);
    }
    
    return "redirect:/students";
  }
  
  @GetMapping("/students/delete/{id}")
  public ModelAndView deleteStudentForm(@PathVariable("id") Long aId, Model aModel)
  {
    Student tStudent = this.studentService.findById(aId);
    aModel.addAttribute("student", tStudent);
    
    if (tStudent != null)
      return new ModelAndView("delete_student.html");
    
    return null;
  }
  
  @PostMapping("/students/delete/{id}")
  public String deleteStudent(@PathVariable("id") Long aId)
  {
    this.studentService.deleteById(aId);
    
    return "redirect:/students";
  }
  
}
