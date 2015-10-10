package service.connection;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionLocator {
	private static ConnectionLocator instance;
	public Map<String,Object> myMap;
	Context context;
	private ConnectionLocator(){
		try {
			context = new InitialContext();
			myMap = new HashMap<String, Object>();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  static ConnectionLocator getInstance(){
		if(instance == null){
			instance = new ConnectionLocator();
		}
		return instance;
	}
	
	public Object getProxy(String jndi){
		Object objet;
		objet = myMap.get(jndi);
		if(objet == null){
			try {
				objet = context.lookup(jndi);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return objet;
	}
}
