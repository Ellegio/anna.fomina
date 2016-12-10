package web;

//import static org.junit.Assert.*;

import java.time.LocalDate;

//import org.junit.Before;
//import org.junit.Test;

import KN_14_5_Fomina.User;
import KN_14_5_Fomina.web.EditServlet;

public class EditServletTest extends MockServletTestCase {

    public void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }

    public void testEdit() {
        LocalDate date = LocalDate.now();
        User user = new User(666L, "Ozzy", "Osbourne", date);
        
        getMockUserDao().expect("update", user);
        
        addRequestParameter("id", "666");
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        
        doPost();
    }
    
    public void testEditEmptyFirstName() {
        
        LocalDate date = LocalDate.now();
        addRequestParameter("id", "666");
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
        
    }

    public void testEditEmptyLastName() {
        
        LocalDate date = LocalDate.now();
        addRequestParameter("id", "666");
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("date", date.toString());
        addRequestParameter("okButton", "Ok");
        doPost();
        
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
        
    }

    public void testEditEmptyDate() {
        
        addRequestParameter("id", "666");
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("okButton", "Ok");
        doPost();
        
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
        
    }
    
    public void testEditInvalidDate() {
        
        addRequestParameter("id", "666");
        addRequestParameter("firstName", "Ozzy");
        addRequestParameter("lastName", "Osbourne");
        addRequestParameter("date", "42");
        addRequestParameter("okButton", "Ok");
        doPost();
        
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
        
    }
}