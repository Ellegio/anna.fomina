package KN_14_5_Fomina.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
//import java.util.Date;

import KN_14_5_Fomina.User;

class HsqldbUserDao implements UserDao {

	private static final String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?,?,?)";
	private ConnectionFactory connectionFactory;
	
	public HsqldbUserDao() {
	}
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}



	public User create(User user) throws DatabaseExcepion {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirth().getTime())); /* ADDED (java.sql.Date) because of an error with setDate() */
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseExcepion("Number of the inserted rows: " + n);
			}
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if (keys.next()) {
				user.setId(new Long(keys.getLong(1)));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseExcepion e) { 
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExcepion(e);
		}
	}

	public void update(User user) throws DatabaseExcepion {
		// TODO Auto-generated method stub

	}

	public void delete(User user) throws DatabaseExcepion {
		// TODO Auto-generated method stub

	}

	public User find(Long id) throws DatabaseExcepion {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection findAll() throws DatabaseExcepion {
		Collection result = new LinkedList();
		
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while (resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
		} catch (DatabaseExcepion e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExcepion(e);
		}
		return result;
	}

}