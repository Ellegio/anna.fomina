package KN_14_5_Fomina;

import java.util.Date;
import java.util.Calendar;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getdateOfBirth() {
		return dateOfBirth;
	}
	public void setdateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Object getFullName() {
		return getLastName() + ", " + getFirstName();
	}
	public int getAge(){ 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.setTime(getdateOfBirth());
		int year = calendar.get(Calendar.YEAR);
			
		return currentYear - year;
	}
	
	
}
