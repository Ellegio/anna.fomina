package web;

//import static org.junit.Assert.*;

import java.time.LocalDate;

//import org.junit.Before;
//import org.junit.Test;

import KN_14_5_Fomina.User;
import KN_14_5_Fomina.web.AddServlet;

public class AddServletTest extends MockServletTestCase {

    public void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    public void testAdd() {
        LocalDate date = LocalDate.now();
        User user = new User(null, "James", "Spader", date);
        User expectedUser = new User(666L, "James", "Spader", date);
        getMockUserDao().expectAndReturn("create", user,expectedUser);
        addRequestParameter("firstName", "James");
        addRequestParameter("lastName", "Spader");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
    }
    
    public void testAddEmptyFirstName() {
        LocalDate date = LocalDate.now();
        addRequestParameter("lastName", "Spader");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyLastName() {
        LocalDate date = LocalDate.now();
        addRequestParameter("firstName", "James");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    public void testAddEmptyDate() {
        addRequestParameter("lastName", "Spader");
        addRequestParameter("firstName", "James");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    public void testAddInvalidDate() {
        addRequestParameter("firstName", "James");
        addRequestParameter("lastName", "Spader");
        addRequestParameter("date", "54");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

}
