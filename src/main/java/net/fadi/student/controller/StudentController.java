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
import org.springframework.web.servlet.ModelAndView;

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

}
