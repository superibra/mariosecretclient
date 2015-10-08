package servicelocator;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

	public static ServiceLocator instance;
	public Map<String, Object> myMap ;
	Context context;
private ServiceLocator()
{
	try {
		 context =  new InitialContext();
		myMap = new HashMap<String, Object>();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static ServiceLocator getinstance()
{
	if(instance==null)
	{
		instance= new ServiceLocator();
	}
	 
		return instance;
}
public Object getProxy(String jndi)
{
	Object objet ;
	objet=myMap.get(jndi);
	if(objet == null)
	{
		 try {
			objet=context.lookup(jndi);
			myMap.put(jndi, objet);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return objet;
	
}	
	
	
}
