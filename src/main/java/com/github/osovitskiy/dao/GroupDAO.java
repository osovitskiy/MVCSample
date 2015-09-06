package com.github.osovitskiy.dao;

import com.github.osovitskiy.model.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO {
    public static List<Group> getAll() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT Id, Name FROM Groups");

            List<Group> list = new ArrayList<Group>();

            while (result.next()) {
                Group group = new Group();
                group.setId(result.getInt("Id"));
                group.setName(result.getString("Name"));
                list.add(group);
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

    public static Group getById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT Id, Name FROM Groups WHERE Id = ?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                Group group = new Group();
                group.setId(result.getInt("Id"));
                group.setName(result.getString("Name"));

                return group;
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

    public static Group insert(Group group) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("INSERT INTO Groups (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            result = statement.getGeneratedKeys();

            if (result.next()) {
                Group copy = new Group();
                copy.setId(result.getInt("Id"));
                copy.setName(group.getName());

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

    public static Group update(Group group) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("UPDATE Groups SET Name = ? WHERE Id = ?");
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
            result = statement.getGeneratedKeys();

            if (result.next()) {
                Group copy = new Group();
                copy.setId(result.getInt("Id"));
                copy.setName(group.getName());

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
            statement = connection.prepareStatement("DELETE FROM Groups WHERE Id = ?");
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

    public static void addStudent(int groupId, int studentId) {

    }

    public static void deleteStudent(int groupId, int studentId) {

    }
}
