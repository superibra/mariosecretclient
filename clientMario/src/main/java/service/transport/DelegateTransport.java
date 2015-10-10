package service.transport;

import java.util.List;

import javax.persistence.Query;

import service.connection.ConnectionLocator;
import tn.mario.moovtn.entities.TransportMean;
import tn.mario.moovtn.remotes.TransportMeanService;




public class DelegateTransport {
String jndi = "/persist/TransportMeanImplementation!tn.mario.moovtn.remotes.TransportMeanService";
	
	public static  TransportMeanService getInstance(String jndi){
		return (TransportMeanService)ConnectionLocator.getInstance().getProxy(jndi);
	}
	
	public boolean add(TransportMean transport){
		return getInstance(jndi).add(transport);
	}
	
	public void delete(TransportMean transport){
		getInstance(jndi).delete(transport);
	}
	
	public void update(TransportMean transport) {
		getInstance(jndi).update(transport);
		
	}
	
	public List<TransportMean> findAll() {
		return getInstance(jndi).findAll();
	}
	
	public TransportMean findBySerial(String serial) {
		
		return getInstance(jndi).findBySerial(serial);
	}
}
