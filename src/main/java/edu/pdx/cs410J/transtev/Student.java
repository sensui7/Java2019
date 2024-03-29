package edu.pdx.cs410J.transtev;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
import java.util.List;

/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {                                                
  private final List<String> classes;
  private final double gpa;
  private final String gender;

  /**                                                                               
   * Creates a new <code>Student</code>                                             
   *                                                                                
   * @param name                                                                    
   *        The student's name                                                      
   * @param classes                                                                 
   *        The names of the classes the student is taking.  A student              
   *        may take zero or more classes.                                          
   * @param gpa                                                                     
   *        The student's grade point average                                       
   * @param gender                                                                  
   *        The student's gender ("male" or "female", case insensitive)             
   */                                                                               
  public Student(String name, List<String> classes, double gpa, String gender) {
    super(name);

    validateGPA(gpa);

    this.gpa = gpa;
    this.gender = gender;
    this.classes = classes;
  }

  private void validateGPA(double gpa) {
    if (gpa > 4.0) {
      throw new IllegalArgumentException("GPA cannot be greater than 4.0");
    }

    if (gpa < 0.0) {
      throw new IllegalArgumentException("GPA cannot be less than 0.0");
    }
  }

  /**                                                                               
   * All students say "This class is too much work"
   */
  @Override
  public String says() {
    return "This class is too much work";
  }
                                                                                    
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */                                                                               
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.name);
    sb.append(" has a GPA of ").append(this.gpa);

    int numberOfClasses = this.classes.size();
    sb.append(" and is taking ").append(numberOfClasses)
            .append(" class").append(numberOfClasses == 1 ? "" : "es");

    if (numberOfClasses > 0) {
      sb.append(": ");

      for (int i = 0; i < numberOfClasses; i++) {
        sb.append(this.classes.get(i));

        if (i < numberOfClasses - 1) {
          if (numberOfClasses > 2) {
            sb.append(",");
          }
          sb.append(" ");
        }

        if (i == numberOfClasses - 2) {
          sb.append("and ");
        }
      }
    }

    sb.append(".  ");
    sb.append(getGenderPronoun()).append(" says \"").append(says()).append("\".");

    return sb.toString();
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    String name = null;
    String gender = null;
    String gpa = null;
    List<String> classes = new ArrayList<>();

    for (String arg : args) {
      if (name == null) {
        name = arg;

      } else if (gender == null) {
        gender = arg;

      } else if (gpa == null) {
        gpa = arg;

      } else {
        classes.add(arg);
      }
    }

    double gpaValue;

    if (name == null) {
      printErrorMessageAndExit("Missing command line arguments");
      return;

    } else if (gender == null) {
      printErrorMessageAndExit("Missing gender");
      return;

    } else if (gpa == null) {
      printErrorMessageAndExit("Missing GPA");
      return;
    }

    try {
      gpaValue = Double.parseDouble(gpa);

    } catch (NumberFormatException ex) {
      printErrorMessageAndExit("Invalid GPA: " + gpa);
      gpaValue = Double.NaN;
    }

    Student student = new Student(name, classes, gpaValue, gender);
    System.out.println(student);
    System.exit(0);
  }

  public static void printErrorMessageAndExit(String message) {
    System.err.println(message);
    System.exit(1);
  }

  @VisibleForTesting
  String getGenderPronoun() {
    switch (this.gender) {
      case "male":
        return "He";
      case "female":
        return "She";
      default:
        return "They";
    }
  }
}