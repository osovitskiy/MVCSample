package com.github.osovitskiy.dao;

import com.github.osovitskiy.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static List<Student> getAll() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT Id, FirstName, LastName FROM Students");

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

    public static Student getById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT Id, FirstName, LastName FROM Students WHERE Id = ?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                Student student = new Student();
                student.setId(result.getInt("Id"));
                student.setFirstName(result.getString("FirstName"));
                student.setLastName(result.getString("LastName"));

                return student;
            } else {
                return null;
            }
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

    public static Student insert(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("INSERT INTO Students (FirstName, LastName) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.executeUpdate();
            result = statement.getGeneratedKeys();

            if (result.next()) {
                Student copy = new Student();
                copy.setId(result.getInt("Id"));
                copy.setFirstName(student.getFirstName());
                copy.setLastName(student.getLastName());

                return copy;
            } else {
                return null;
            }
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

    public static Student update(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("UPDATE Students SET FirstName = ?, LastName = ? WHERE Id = ?");
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getId());
            statement.executeUpdate();
            result = statement.getGeneratedKeys();

            if (result.next()) {
                Student copy = new Student();
                copy.setId(student.getId());
                copy.setFirstName(student.getFirstName());
                copy.setLastName(student.getLastName());

                return copy;
            } else {
                return null;
            }
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

    public static void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("DELETE FROM Students WHERE Id = ?");
            statement.setInt(1, id);
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
