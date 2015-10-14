package service.itineraries;

import java.util.List;

import service.connection.ConnectionLocator;
import tn.mario.moovtn.entities.Station;
import tn.mario.moovtn.remotes.StationRemote;


public class DelegateStation {
	String jndi = "/persist/StationServiceImplementation!tn.mario.moovtn.remotes.StationRemote";
	public static StationRemote getInstance(String jndi){
		return (StationRemote)ConnectionLocator.getInstance().getProxy(jndi);
	}
	
	public boolean add(Station station){
		
		 getInstance(jndi).add(station);
		 return true;
	}
	
	public List<Station> findAll(){
		return getInstance(jndi).findAll();
	}
	
	public String findAllToJSON(){
		return getInstance(jndi).findAllToJSON();
	}
	
	public String findAllToJSON(List<Station> list){
		return getInstance(jndi).findAllToJSON(list);
	}
	
	public Station findById(int id){
		return getInstance(jndi).findById(id);
	}
	
	public List<Station> findAllByType(String type){
		
		return getInstance(jndi).findAllByType(type);
	}
	

}
