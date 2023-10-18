/*
Coded By: Charles Matthew Shaw 222451432
*/
package za.ac.cput.adp_group25;

import java.io.Serializable;

public class Course implements Serializable{
    int courseID;
    String courseTitle;
    String courseFaculty;
    String function;

    public Course(int courseID, String courseTitle, String courseFaculty) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseFaculty = courseFaculty;
    }

    public Course(int courseID, String courseTitle, String courseFaculty, String function) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseFaculty = courseFaculty;
        this.function = function;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseFaculty() {
        return courseFaculty;
    }

    public void setCourseFaculty(String courseFaculty) {
        this.courseFaculty = courseFaculty;
    }
    
    public String getFunction() {
        return function;
    }

    public void setFunction(String courseFaculty) {
        this.function = function;
    }
    
    

    @Override
    public String toString() {
        return "Course{" + "courseID=" + courseID + ", courseTitle=" + courseTitle + ", courseFaculty=" + courseFaculty + '}';
    }
    
    
}
