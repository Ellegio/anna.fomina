package KN_14_5_Fomina;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {

	private User user;
	private LocalDate dateOfBirthd; 
	
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
	
		dateOfBirthd = LocalDate.of(1915, 12, 12);
	}
	
	public void testGetFullName() {
		user.setFirstName("Frank");
		user.setLastName("Sinatra");
		assertEquals("Sinatra, Frank", user.getFullName());
	}
	
	public void testGetAge() {
		user.setDateOfBirthd(dateOfBirthd);
		
		int correctAnswer = LocalDate.now().getYear() - dateOfBirthd.getYear();
		
		assertEquals(correctAnswer, user.getAge());
	}

}
