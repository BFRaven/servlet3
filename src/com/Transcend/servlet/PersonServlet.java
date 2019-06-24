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

import common.helpers.DateHelper;
import common.helpers.ServletHelper;
import org.apache.log4j.Logger;

import static common.helpers.ServletHelper.logRequestParams;

//TODO: Fix the personDAO interface syntax.

@WebServlet(name = "PersonServlet")
public class PersonServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(PersonServlet.class);
    private static PersonDAO personDAO = new PersonDAOImpl();


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


        request.setAttribute("selectPerson", generatePerDropDownHTML(0));

        request.getRequestDispatcher("./person.jsp").forward(request, response);
    }

    private static void choosePerson(HttpServletRequest request) {
      logger.info("Form #1 - Form Name = " + request.getParameter("formName"));
      logRequestParams(request);

        //notes: everything comes back from the request as a STRING

        String selectedPersonId = request.getParameter("selectPerson");

        Person selectedPerson = personDAO.getPersonById(Integer.parseInt(selectedPersonId));

        logger.info("Selected Person Details: " + selectedPerson.ToString());

        personToRequest(request, selectedPerson);

        request.setAttribute("selectPerson", generatePerDropDownHTML(selectedPerson.getPersonID()));





    }

    private static void updatePerson(HttpServletRequest request) {
        logger.info("Form #2 - Form Name = " + request.getParameter("formName"));
        logRequestParams(request);

        Person updatePerson = new Person();
        requestToPerson(request, updatePerson);

        logger.info(updatePerson.ToString());
        if (personDAO.updatePerson(updatePerson))
            request.setAttribute("updateStatus", "Person Updated in Database Successfully");
        else
            request.setAttribute("updateStatus", "Person Updated FAILED!!");


        personToRequest(request, updatePerson);

        //notes: inefficient, extra call to DB.
        updatePerson = personDAO.getPersonById(updatePerson.getPersonID());
        personToRequest(request, updatePerson);

        String personIdString = request.getParameter("personId");
        request.setAttribute("selectPerson", generatePerDropDownHTML(Integer.parseInt(personIdString)));


    }

    private static String generatePerDropDownHTML(int selectedPersonId) {

//      <select name="selectPerson">
//        <option value=" [1] ">  [Bipin]  </option>
//        <option value="2">Dan</option>
//        <option value="3">Sean</option>
//        <option value="4">Adrian</option>
//        <option value="5">James</option>
//      </select>

        StringBuilder strBld = new StringBuilder();
        strBld.append("<select name='selectPerson'>");
        strBld.append("<option value='0'>(Select Person)</option>");

        for(Person person: personDAO.getPersonList()) {
            if(person.getPersonID() == selectedPersonId)
            strBld.append("<option selected value='").append(person.getPersonID()).append("'>").append(person.GetFullName()).append("</option>");
            else
                strBld.append("<option value='").append(person.getPersonID()).append("'>").append(person.GetFullName()).append("</option>");

        }
        strBld.append("</select>");

        return strBld.toString();
    }

    private static void requestToPerson(HttpServletRequest request, Person person) {
        //notes: Persist all values, everything comes back from the request as a STRING!

        person.setPersonID(Integer.parseInt(request.getParameter("personId")));
        person.setFirstName(request.getParameter("firstName"));
        person.setMiddleName(request.getParameter("middleName"));
        person.setLastName(request.getParameter("lastName"));
        person.setBirthDate(DateHelper.stringToUtilDate(request.getParameter("birthDate"), "yyyy-MM-dd"));
        person.setSSN(request.getParameter("socialSecurityNumber"));

    }

    private static void personToRequest(HttpServletRequest request, Person person) {

        request.setAttribute("personId", person.getPersonID());
        request.setAttribute("firstName", person.getFirstName());
        request.setAttribute("middleName", person.getMiddleName());
        request.setAttribute("lastName", person.getLastName());
        request.setAttribute("birthDate", person.getBirthDate());
        request.setAttribute("socialSecurityNumber", person.getSSN());
    }

}


