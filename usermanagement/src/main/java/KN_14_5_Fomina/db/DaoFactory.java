package KN_14_5_Fomina.db;
import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {

	protected static final String USER_DAO = "dao.KN_14_5_Fomina.db.UserDao";
	protected static Properties properties; 
	
	private static DaoFactory instance;
	private static final String DAO_FACTORY = "dao.factory"; 
	
	public abstract UserDao getUserDao();
	
	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			Class<?> factoryClass;
			try {
				factoryClass = Class.forName(properties
						.getProperty(DAO_FACTORY));
				instance = (DaoFactory) factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}
	
	static {
		properties = new Properties();
		try {
			properties.load(DaoFactory.class.getClassLoader()
					.getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected DaoFactory() {
	}
	
	public static void init(Properties prop) {
		properties = prop;
		instance = null;
	}
	
	protected ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl(properties);
	}
	
}
