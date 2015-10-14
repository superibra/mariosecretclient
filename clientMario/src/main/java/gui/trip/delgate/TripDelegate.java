package gui.trip.delgate;

import java.util.List;

import servicelocator.ServiceLocator;
import tn.mario.moovtn.entities.Notification;
import tn.mario.moovtn.entities.Trip;
import tn.mario.moovtn.remotes.NotificationManagerRemote;
import tn.mario.moovtn.remotes.TripManagerRemote;

public class TripDelegate {

	static String jndi = "persist/TripManager!"+TripManagerRemote.class.getCanonicalName();

	public static TripManagerRemote getInstance() {
		return (TripManagerRemote) ServiceLocator.getinstance().getProxy(jndi);

	}
	public void doAdd(Trip a) {
		getInstance().add(a);
	}
	public List<Trip> doGetAll() {
		return getInstance().getAllTrips();
	}
	public void doUpdate(Trip a){
		
		getInstance().update(a);
		
		
	}
	public void doDelete(Trip a){
		
		getInstance().delete(a);
		
		
	}
	
}
