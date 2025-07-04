package org.example.mvc.model;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class DataBaseSQLite {

    private static volatile DataBaseSQLite instance;
    private String DATABASE_PATH = "jdbc:sqlite:WEB-INF/db/registry.db";

    public static DataBaseSQLite getInstance() {
        if (instance == null) {
            instance = new DataBaseSQLite();
        }
        return instance;
    }

    private DataBaseSQLite(){
        File DATABASE_FILE = new File(DATABASE_PATH);

        if (DATABASE_FILE.getParentFile() != null) {
            DATABASE_FILE.getParentFile().mkdirs();
        }
        try {
            DATABASE_FILE.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH)) {
            createTableStudents(connection);
            createTableCourses(connection);
        }
        catch (SQLException e) {
           e.printStackTrace();
        }
    }

    private void createTableStudents(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL);
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableCourses(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS courses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL);
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String table,String name) throws SQLException {
        String sql = "INSERT INTO " +
                table +
                "(name) VALUES(?)";

        try (PreparedStatement pstmt = DriverManager.getConnection(DATABASE_PATH).prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public void readData(Connection connection) throws SQLException {
        String sql = "SELECT id, name FROM students";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("\nСписок пользователей:");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id") + "\t" +
                                resultSet.getString("name")
                );
            }
        }
    }
}