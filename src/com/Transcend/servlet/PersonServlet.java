package com.Transcend.servlet;

import com.servlet.dao.PersonDAO;
import com.servlet.dao.mysql.PersonDAOImpl;
import com.servlet3.bo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import org.apache.log4j.Logger;


@WebServlet(name = "PersonServlet")
public class PersonServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(PersonServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("formName")) {

            case "choosePerson":
                choosePerson(request);
                break;

            case "updatePerson":
                updatePerson(request);
                break;

                default:
                    break;
        }

        request.getRequestDispatcher("./person.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        PersonDAO personDAO = new PersonDAOImpl();
//        Person person = personDAO.getPersonById(1);
//
//        request.setAttribute("fullName", person.GetFullName());
//        request.setAttribute("SSN", person.getSSN());
//        request.setAttribute("middleName", person.getMiddleName());
        request.getRequestDispatcher("./person.jsp").forward(request, response);
    }

    private static void choosePerson(HttpServletRequest request) {

        logger.info("Form #1 - Form Name=" + request.getParameter("formName"));
        logRequestParams(request);

    }

    private static void updatePerson(HttpServletRequest request) {

        logger.info("Form #2 - Form Name=" + request.getParameter("formName"));
        //notes: persist a value (firstName)
        request.setAttribute("firstName", request.getParameter("firstName"));
        request.setAttribute("middleName", request.getParameter("middleName"));
        request.setAttribute("lastName", request.getParameter("lastName"));
        request.setAttribute("birthDate", request.getParameter("birthDate"));
        request.setAttribute("socialSecurityNumber", request.getParameter("socialSecurityNumber"));

        logRequestParams(request);

    }

    private static void logRequestParams(HttpServletRequest request) {
        //notes: not pretty, needs refactoring and moved to the common module
        //todo: move to common module

        Enumeration<String> paramNameList = request.getParameterNames();
        while(paramNameList.hasMoreElements()) {
            String paramName = paramNameList.nextElement();
            String value = "";
            for(String str : request.getParameterValues(paramName)){
                value += str;
        }
            logger.info("Parameter Name = " + paramName + " - Value(s) = " + value);
    }
}
}

