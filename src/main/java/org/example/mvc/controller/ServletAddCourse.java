package org.example.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.example.mvc.model.DataBaseSQLite;
import org.example.mvc.model.Database;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add/course")
public class ServletAddCourse extends HttpServlet {
    private String docBase;
    private Database database;

    @Override
    public void init() {
        try {
            super.init();
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        docBase = getServletContext().getRealPath("/");
        database = Database.getInstance(docBase);
    }

    private String returnPath = "/WEB-INF/pages/add/course.jsp";

    private DataBaseSQLite dataBaseSQLite = DataBaseSQLite.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String courseId = request.getParameter("courseid");
        String courseName = request.getParameter("coursename");
        if (courseId.isEmpty() || courseName.isEmpty()) {
            request.setAttribute("message", "Ошибка: поля пустые!");
        } else {
            dataBase.addCourse(courseId, courseName);
            try {
                dataBaseSQLite.insertData("courses", courseName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("message", "Курс успешно добавлен!");
        }
        try {
            request.getRequestDispatcher(returnPath).forward(request, response);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(returnPath).forward(request, response);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
