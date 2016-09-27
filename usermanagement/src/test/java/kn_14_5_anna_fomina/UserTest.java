package kn_14_5_anna_fomina;

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
		calendar.set(1986, Calendar.MARCH, 26);
		dateOfBirth = calendar.getTime();
	}

	public void testGetFullName() {
		user.setFirstName("Tyrell");
		user.setLastName("Wellek");
		assertEquals("Tyrell, Wellek", user.getFullName());
	}
	
	public void testGetAge() {
		user.setDateOfBirth(dateOfBirth);
		assertEquals(2016-1986, user.getAge());
	}
}
