package com.github.osovitskiy.web;

import com.github.osovitskiy.dao.GroupDAO;
import com.github.osovitskiy.dao.GroupStudentDAO;
import com.github.osovitskiy.dao.StudentDAO;
import com.github.osovitskiy.model.Group;
import com.github.osovitskiy.model.Student;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@javax.servlet.annotation.WebServlet(name = "MainServlet")
public class MainServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            if ("/student".equals(request.getServletPath())) {
                if ("/save".equals(request.getPathInfo())) {
                    Student student = new Student();

                    student.setId(Integer.parseInt(request.getParameter("id")));
                    student.setFirstName(request.getParameter("firstName"));
                    student.setLastName(request.getParameter("lastName"));

                    if (student.getId() > 0) {
                        StudentDAO.update(student);
                    } else {
                        StudentDAO.insert(student);
                    }

                    response.sendRedirect(getServletContext().getContextPath());
                }

                if ("/delete".equals(request.getPathInfo())) {
                    StudentDAO.delete(Integer.parseInt(request.getParameter("id")));

                    response.sendRedirect(getServletContext().getContextPath());
                }
            }

            if ("/group".equals(request.getServletPath())) {
                if ("/save".equals(request.getPathInfo())) {
                    Group group = new Group();

                    group.setId(Integer.parseInt(request.getParameter("id")));
                    group.setName(request.getParameter("name"));

                    if (group.getId() > 0) {
                        GroupDAO.update(group);
                    } else {
                        GroupDAO.insert(group);
                    }

                    response.sendRedirect(getServletContext().getContextPath());
                }

                if ("/delete".equals(request.getPathInfo())) {
                    GroupDAO.delete(Integer.parseInt(request.getParameter("id")));

                    response.sendRedirect(getServletContext().getContextPath());
                }

                if ("/addstudent".equals(request.getPathInfo())) {
                    int groupId = Integer.parseInt(request.getParameter("groupId"));
                    int studentId = Integer.parseInt(request.getParameter("studentId"));

                    GroupStudentDAO.addStudent(groupId, studentId);

                    response.sendRedirect(getServletContext().getContextPath() + "/group/edit/" + groupId);
                }

                if ("/removestudent".equals(request.getPathInfo())) {
                    int groupId = Integer.parseInt(request.getParameter("groupId"));
                    int studentId = Integer.parseInt(request.getParameter("studentId"));

                    GroupStudentDAO.removeStudent(groupId, studentId);

                    response.sendRedirect(getServletContext().getContextPath() + "/group/edit/" + groupId);
                }
            }
        }
        catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            if ("/".equals(request.getServletPath())) {
                List<Student> students = StudentDAO.getAll();
                List<Group> groups = GroupDAO.getAll();

                request.setAttribute("students", students);
                request.setAttribute("groups", groups);

                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }

            if ("/student".equals(request.getServletPath())) {
                if ("/add".equals(request.getPathInfo())) {
                    Student student = new Student();

                    request.setAttribute("title", "Add Student");
                    request.setAttribute("student", student);

                    getServletContext().getRequestDispatcher("/student.jsp").forward(request, response);
                } else if (request.getPathInfo() != null){
                    Pattern pattern = Pattern.compile("/edit/(?<id>\\d+)");
                    Matcher matcher = pattern.matcher(request.getPathInfo());

                    if (matcher.matches()) {
                        Student student = StudentDAO.getById(Integer.parseInt(matcher.group("id")));

                        if (student != null) {
                            request.setAttribute("title", "Edit Student #" + student.getId());
                            request.setAttribute("student", student);

                            getServletContext().getRequestDispatcher("/student.jsp").forward(request, response);
                        }
                    }
                }
            }

            if ("/group".equals(request.getServletPath())) {
                if ("/add".equals(request.getPathInfo())) {
                    Group group = new Group();

                    request.setAttribute("title", "Add Group");
                    request.setAttribute("group", group);

                    getServletContext().getRequestDispatcher("/group.jsp").forward(request, response);
                } else if (request.getPathInfo() != null){
                    Pattern pattern = Pattern.compile("/edit/(?<id>\\d+)");
                    Matcher matcher = pattern.matcher(request.getPathInfo());

                    if (matcher.matches()) {
                        Group group = GroupDAO.getById(Integer.parseInt(matcher.group("id")));

                        if (group != null) {
                            List<Student> groupStudents = GroupStudentDAO.getGroupStudents(group.getId());
                            List<Student> availableStudents = GroupStudentDAO.getAvailableStudents(group.getId());

                            request.setAttribute("title", "Edit Group #" + group.getId());
                            request.setAttribute("group", group);
                            request.setAttribute("groupStudents", groupStudents);
                            request.setAttribute("availableStudents", availableStudents);

                            getServletContext().getRequestDispatcher("/group.jsp").forward(request, response);
                        }
                    }
                }
            }
        }
        catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
