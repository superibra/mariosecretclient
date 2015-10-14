package service.itineraries;

import java.util.List;

import service.connection.ConnectionLocator;
import tn.mario.moovtn.entities.Line;
import tn.mario.moovtn.entities.Station;
import tn.mario.moovtn.remotes.LineRemote;


public class DelegateLineService {
	String jndi = "/persist/LineServiceImplementation!tn.mario.moovtn.remotes.LineRemote";
	
	public static LineRemote getInstance(String jndi){
		return (LineRemote)ConnectionLocator.getInstance().getProxy(jndi);
	}
	
	public boolean add(Line line){
		
		return getInstance(jndi).add(line);
	}
	
	public Line findByName(String name){
		return getInstance(jndi).findByName(name);
	}
	
	public List<Line> findAll(){
		return getInstance(jndi).findAll();
	}
	
	public Line findById(int id){
		return getInstance(jndi).findById(id);
	}
	
	public boolean delete(Line line){
		return getInstance(jndi).delete(line);
	}
	
	public List<Station> findAllStationsByLineId(int lineId){
		return getInstance(jndi).findAllStationsByLineId(lineId);
	}
	
	public String findAllStationsByLineIdToJSON(int lineId ){
		return getInstance(jndi).findAllStationsByLineIdtoJSON(lineId);
	}
}
