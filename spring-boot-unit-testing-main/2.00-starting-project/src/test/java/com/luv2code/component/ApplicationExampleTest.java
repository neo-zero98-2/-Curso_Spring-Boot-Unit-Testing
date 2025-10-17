package com.luv2code.component;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.Student;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class ApplicationExampleTest {

    private static int count = 0;

    @Value("${info.school.name}")
    private String appInfo;

    @Value("${info.app.name}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    // El ApplicationContext es el contenedor principal de Spring, que gestiona los beans y su ciclo de vida
    // se usa para obtener beans definidos en el contexto de Spring durante las pruebas.
    @Autowired
    ApplicationContext context;

    @BeforeEach
    void beforeEach(){
        count++;
        System.out.println("Testing: " + appInfo + " wich is " + appDescription + " " +
                "Version: " + appVersion + ". Executing of test method: " + count);
        student.setFirstname("Eric");
        student.setLastname("Roby");
        student.setEmailAddress("eric.roby@luv2code_school.com");
        studentGrades.setMathGradeResults(new ArrayList<Double>(Arrays.asList(100.0, 85.5, 76.5, 91.75)));
        student.setStudentGrades(studentGrades);
        System.out.println("Running test case: " + count);
    }

    @Test
    @DisplayName("Add grade results for student grades")
    void addGradeResultsForStudentGrades(){
        System.out.println("Testing addGradeResultsForStudentGrades");
        Assertions.assertEquals(353.75, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));
    }

    @Test
    @DisplayName("Add grade results for student grades not equal")
    void addGradeResultsForStudentGradesNotEqual(){
        System.out.println("Testing addGradeResultsForStudentGradesNotEqual");
        Assertions.assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));
    }

    @Test
    @DisplayName("Is grade greater")
    void isGradeGreater(){
        System.out.println("Testing isGradeGreater");
        Assertions.assertTrue(studentGrades.isGradeGreater(90, 75));
    }

    @Test
    @DisplayName("Is grade greater false")
    void isGradeGreaterFalse(){
        System.out.println("Testing isGradeGreaterFalse");
        Assertions.assertFalse(studentGrades.isGradeGreater(65, 75));
    }

    @DisplayName("create student without grades init")
    @Test
    public void createStudentWithoutGradesInit(){
        System.out.println("Testing createStudentWithoutGradesInit");
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        studentTwo.setFirstname("Chad");
        studentTwo.setLastname("Darby");
        studentTwo.setEmailAddress("chad.darby@luv2code_school.com");

        Assertions.assertNotNull(studentTwo);
        Assertions.assertNotNull(studentTwo.getFirstname());
        Assertions.assertNotNull(studentTwo.getLastname());
        Assertions.assertNotNull(studentTwo.getEmailAddress());
        Assertions.assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()));
    }

    @DisplayName("Verify students are prototype")
    @Test
    public void verifyStudentsArePrototype() {
        System.out.println("Testing verifyStudentsArePrototype");
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        Assertions.assertNotSame(studentTwo, student);
    }

    @DisplayName("Find Grade Point Average")
    @Test
    public void findGradePointAverage(){
        System.out.println("Testing findGradePointAverage");
        Assertions.assertAll("Testing all assertionsEquals",
                () -> Assertions.assertEquals(353.75, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults())),
                () -> Assertions.assertEquals(88.44, studentGrades.findGradePointAverage(student.getStudentGrades().getMathGradeResults()))
        );
    }


}
