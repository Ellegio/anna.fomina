package KN_14_5_Fomina;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {

	private User user;
	private Date dateOfBirth; 
	
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1982, Calendar.AUGUST, 21);
		dateOfBirth = calendar.getTime();
	}
	
	public void testGetFullName() {
		user.setFirstName("Tyrell");
		user.setLastName("Wellik");
		assertEquals("Wellik, Tyrell", user.getFullName());
	}
	
	public void testGetAge() {
		user.setdateOfBirth(dateOfBirth);
		assertEquals(2016 - 1982, user.getAge());
	}

}
