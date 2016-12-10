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
        User user = new User(null, "Ozzy", "Osbourne", date);
        User expectedUser = new User(666L, "Ozzy", "Osbourne", date);
        getMockUserDao().expectAndReturn("create", user,expectedUser);
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
    }
    
    public void testAddEmptyFirstName() {
        LocalDate date = LocalDate.now();
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyLastName() {
        LocalDate date = LocalDate.now();
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    public void testAddEmptyDate() {
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    public void testAddInvalidDate() {
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("date", "42");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

}
