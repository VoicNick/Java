import java.util.HashMap;
/**
 * This is the Student class as required on the lab5b homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 5/31/2014
 */

public class Student extends Person 
{
    private HashMap<String,Double> courses;
    
    /**
     * Default constructor
     */
    public Student()
    {
        super();
        courses = new HashMap<String,Double>();
    }
    
    /**
     * Non-default constructor
     */
    public Student(String firstName, String lastName, String email)
    {
        super(firstName,lastName,email);
        courses = new HashMap<String,Double>();
    }
    
    /**
     * Mutator method for adding a course and a grade to the courses 
     * 
     * @param   courseName  the course name to add
     * @param   grade       the grade the student got for this course
     */
    public void addCourse(String courseName, double grade)
    {
        if(courseName!=null) {
            if(grade>0) {
                courses.put(courseName,grade);
            }
            else {
                System.out.println("Invalid grade.");
            }
        }
        else {
           System.out.println("Invalid couse name.");
        }
    }
    
    /**
     * Method that calculates and returns the student's average grade
     * 
     * @return  the student's average grade
     */
    public double getAverageGrade()
    {
        if(courses.isEmpty()) {
            System.out.println(getFirstName()+" "+getLastName()+" has no grades.");
            return 0;
        }
        else {           
            double total = 0;
            for(String key:courses.keySet()) {
                total+=courses.get(key);
            }
            return total/courses.size();
        }
    }
}
