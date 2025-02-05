/**
 * Student.java
 *
 * @author Fadi Salameh
 * @since 05.02.2025
 */

package net.fadi.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student
{
  @Id
  @SequenceGenerator(name = "student_seq_id",
  initialValue = 1,
  allocationSize = 1,
  sequenceName = "student_seq_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq_id")
  private Long id;
  
  @Column(name = "first-name")
  private String firstName;
  
  @Column(name = "last-name")
  private String lastName;
  
  private String email;
}
