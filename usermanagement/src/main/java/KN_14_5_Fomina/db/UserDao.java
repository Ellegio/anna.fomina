package KN_14_5_Fomina.db;

import java.util.Collection;

import KN_14_5_Fomina.User;

public interface UserDao {
	User create(User user) throws DatabaseExcepion;
	
	void update(User user) throws DatabaseExcepion;
	
	void delete(User user) throws DatabaseExcepion;
	
	User find(Long id) throws DatabaseExcepion;
	
	Collection findAll() throws DatabaseExcepion;
	
	void setConnectionFactory(ConnectionFactory connectionFactory);
}
