package com.github.osovitskiy.dao;

import com.github.osovitskiy.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupStudentDAO {
    public static List<Student> getGroupStudents(int groupId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT Id, FirstName, LastName FROM Students INNER JOIN GroupStudents ON GroupStudents.StudentId = Students.Id WHERE GroupId = ?");
            statement.setInt(1, groupId);
            result = statement.executeQuery();

            List<Student> list = new ArrayList<Student>();

            while (result.next()) {
                Student student = new Student();
                student.setId(result.getInt("Id"));
                student.setFirstName(result.getString("FirstName"));
                student.setLastName(result.getString("LastName"));
                list.add(student);
            }

            return list;
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static List<Student> getAvailableStudents(int groupId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT Id, FirstName, LastName FROM Students LEFT JOIN GroupStudents ON GroupStudents.GroupId = ? AND GroupStudents.StudentId = Students.Id WHERE GroupId IS NULL");
            statement.setInt(1, groupId);
            result = statement.executeQuery();

            List<Student> list = new ArrayList<Student>();

            while (result.next()) {
                Student student = new Student();
                student.setId(result.getInt("Id"));
                student.setFirstName(result.getString("FirstName"));
                student.setLastName(result.getString("LastName"));
                list.add(student);
            }

            return list;
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void addStudent(int groupId, int studentId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("INSERT INTO GroupStudents (GroupId, StudentId) VALUES (?, ?)");
            statement.setInt(1, groupId);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void removeStudent(int groupId, int studentId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("DELETE FROM GroupStudents WHERE GroupId = ? AND StudentId = ?");
            statement.setInt(1, groupId);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
