/*
Coded By: Imaan Sadien 221752838
Slight Edits By: Matthew Shaw 222451432
*/
package za.ac.cput.adp_group25;

import java.sql.Connection;
import java.util.*;
import java.sql.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ServerDAO {
    private Connection connection;
    User user;
    Course course;
    UserCourse userCourse;
    
    
public ServerDAO(){
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
}

public User Login(User user){
    String query = "SELECT * FROM Admin WHERE userID = ? ";
    try(PreparedStatement stmt = connection.prepareStatement(query)){
        stmt.setInt(1, user.getUserID());
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()){
            String userName = resultSet.getString("uName");
            String userSurname = resultSet.getString("uSurname");
            String userPassword = resultSet.getString("uPassword");
            boolean Admin = resultSet.getBoolean("Admin");
            
            if((user.getPass()).equals(userPassword)){
                user = new User(user.getUserID(), userName, userSurname, userPassword, Admin);
                return user;
            } else {
                JOptionPane.showMessageDialog(null, "3");
                return null;
            }
        }
    } catch (SQLException SQLe){
        System.out.println("Error");
    }
    return null;
}

public void addUser(User user){
    String query = "INSERT INTO Admin (UserID, UName, USurname, UPassword, IsAdmin) VALUES (?, ?, ?, ?, ?)";
    try(PreparedStatement stmt = connection.prepareStatement(query)){
        stmt.setInt(1, user.getUserID());
        stmt.setString(2, user.getUserFName());
        stmt.setString(3, user.getUserLName());
        stmt.setString(4, user.getPass());
        stmt.setBoolean(5, user.isAdmin());
        stmt.execute();
        connection.close();
        JOptionPane.showMessageDialog(null, "User successfully registered");
    } catch (SQLException SQLe){
        System.out.println("Error");
    }
}

public void addCourse(Course course){
    String query = "INSERT INTO Courses(Course_Code, Course_Title, Course_Faculty) VALUES (?, ?, ?)";
    try(PreparedStatement stmt = connection.prepareStatement(query)){
        stmt.setInt(1, course.getCourseID());
        stmt.setString(2, course.getCourseTitle());
        stmt.setString(3, course.getCourseFaculty());
        stmt.execute();
        connection.close();
        JOptionPane.showMessageDialog(null, "Course successfully added");
    } catch(SQLException e){
        e.printStackTrace();
    }
}

public void enrollUser(UserCourse userCourse){ //Change the Arguments from (User user, Course course) -> (UserCourse userCourse) {Matthew Shaw}
    String query = "INSERT INTO UserCourse(ucID, UserID, CourseID) VALUES (?, ?, ?)";
    int userID = userCourse.getUserID();
    int courseID = userCourse.getCourseID();
    
    String userIDString = Integer.toString(userID);
    String courseIDString = Integer.toString(courseID);
    String ucIDString = userIDString + courseIDString;
    int userCourseID = Integer.parseInt(ucIDString);
    try(PreparedStatement stmt = connection.prepareStatement(query)){
        stmt.setInt(1, userCourseID);
        stmt.setInt(2, userID);
        stmt.setInt(3, courseID);
        stmt.execute();
        connection.close();
        JOptionPane.showMessageDialog(null, "Student successfully enrolled in course!");
    } catch(SQLException e){
        e.printStackTrace();
    }
}

public void unenrollUser(UserCourse userCurse){
    String query = "DELETE FROM UserCourse WHERE ucID = ?";
    try(PreparedStatement stmt = connection.prepareStatement(query)){
        stmt.setInt(1, userCourse.getUserCourseID());
        stmt.setInt(2, userCourse.getUserID());
        stmt.setInt(3, userCourse.getCourseID());
        int rowCount = stmt.executeUpdate();
        
        if(rowCount>0){
            JOptionPane.showMessageDialog(null, "Student has unenrolled");
        } else{
            JOptionPane.showMessageDialog(null, "Unenrollment was unsuccessful");
        }
    } catch(SQLException e){
        e.printStackTrace();
    }
}

public User getUser(User user){
    String query = "SELECT * FROM  Admin WHERE UserID = ?";
    try(PreparedStatement stmt = connection.prepareStatement(query)){
        stmt.setInt(1, user.getUserID());
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()){
            String userName = resultSet.getString("userName");
            String userSurname = resultSet.getString("userSurname");
            String userPassword = resultSet.getString("userPasssword");
            boolean isAdmin = resultSet.getBoolean("isAdmin");
            
            user = new User(user.getUserID(), userName, userSurname,userPassword, isAdmin);
        }
    } catch (SQLException SQLException){
        
    }
     return user;
}
public ArrayList<User> getAllUsers(){
    ArrayList<User> students = new ArrayList<>();
    String query = "SELECT * FROM Users WHERE Admin = false";
    try(PreparedStatement stmt = connection.prepareStatement(query)){
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next()){
            /* Imaans Code: Had Errors.
            user = new User();
            int userID = resultSet.getInt("userID");
            String UserName = resultSet.getString("userName");
            String UserSurname = resultSet.getString("userSurname");
            String UserPassword = resultSet.getString("userPassword");
            boolean isAdmin = resultSet.getBoolean("isAdmin");
            students.add(user);
            */
            //Slightly Altered Code by Matthew Shaw:
            int userID = resultSet.getInt("userID");
            String UserName = resultSet.getString("userName");
            String UserSurname = resultSet.getString("userSurname");
            String UserPassword = resultSet.getString("userPassword");
            boolean isAdmin = resultSet.getBoolean("isAdmin");
            user = new User(userID, UserName, UserSurname, UserPassword, isAdmin);
            students.add(user);
            
        } 
    } catch(SQLException e){
                e.printStackTrace();
                }
    return students;
}

public ArrayList<Course> getAllCourses(){
    ArrayList<Course> courses = new ArrayList<>();
    String query = "SELECT * FROM Courses";
    try(PreparedStatement stmt = connection.prepareStatement(query)){
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next()){
            /* Imaans Code: Had Errors.
            course = new Course();
            int courseID = resultSet.getInt("courseID");
            String courseTitle = resultSet.getString("courseTitle");
            String courseFaculty = resultSet.getString("courseFaculty");
            courses.add(course);
            */
            //Slightly Altered Code, by Matthew Shaw:
            int courseID = resultSet.getInt("courseID");
            String courseTitle = resultSet.getString("courseTitle");
            String courseFaculty = resultSet.getString("courseFaculty");
            course = new Course(courseID, courseTitle, courseFaculty);
            courses.add(course);
            
        }
    } catch(SQLException e){
        e.printStackTrace();
    }
    return courses;
}

public Course getCourse(Course course){
    String query = "SELECT * FROM Courses WHERE Course_Code = ?";
    try(PreparedStatement stmt = connection.prepareStatement(query)){
        stmt.setInt(1, course.getCourseID());
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()){
           String courseTitle = resultSet.getString("Course_Title");
           String courseFaculty = resultSet.getString("Course_Faculty");
           //Added By Matthew Shaw:
           course = new Course(course.getCourseID(), courseTitle, courseFaculty);
        }
    } catch(SQLException e){
        e.printStackTrace();
    }
    return course;
}

public List<Course> searchStudentEnrolledCourses(int userID) {
    List<Course> enrolledCourses = new ArrayList<>();
    String query = "SELECT Courses.* FROM Courses " + "INNER JOIN UserCourse ON Courses.courseID = UserCourse.CourseID " + "WHERE UserCourse.UserID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, userID);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            int courseID = resultSet.getInt("courseID");
            String courseTitle = resultSet.getString("courseTitle");
            String courseFaculty = resultSet.getString("courseFaculty");

            Course course = new Course(courseID, courseTitle, courseFaculty);
            enrolledCourses.add(course);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return enrolledCourses;
}

public List<User> getCourseEnrolledStudents(int courseID) {
    List<User> enrolledStudents = new ArrayList<>();

    String query = "SELECT Users.* FROM Users " +
                   "INNER JOIN UserCourse ON Users.userID = UserCourse.UserID " +
                   "WHERE UserCourse.CourseID = ?";

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, courseID);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            int userID = resultSet.getInt("userID");
            String userName = resultSet.getString("userName");
            String userSurname = resultSet.getString("userSurname");
            String userPassword = resultSet.getString("userPassword");
            boolean isAdmin = resultSet.getBoolean("isAdmin");

            User student = new User(userID, userName, userSurname, userPassword, isAdmin);
            enrolledStudents.add(student);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return enrolledStudents;
}


public void deleteUser(User user){
    String query = "DELETE FROM  Admin WHERE UserID = ?";
    try(PreparedStatement stmt = connection.prepareStatement(query)){
        stmt.setInt(1, user.getUserID());
        stmt.executeUpdate();
    } catch(SQLException e){
        e.printStackTrace();
    }
    
}

public void deleteCourse(Course course){
    String query = "DELETE FROM Courses WHERE Course_Code = ?";
    try(PreparedStatement stmt = connection.prepareStatement(query)){
        stmt.setInt(1, course.getCourseID());
        stmt.executeUpdate();
    } catch(SQLException e){
        e.printStackTrace();
    }
    
}

}
