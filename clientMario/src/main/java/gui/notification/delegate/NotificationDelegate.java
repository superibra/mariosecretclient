package gui.notification.delegate;

import java.util.List;

import servicelocator.ServiceLocator;
import tn.mario.moovtn.entities.Notification;
import tn.mario.moovtn.remotes.NotificationManagerRemote;

public class NotificationDelegate {
	static String jndi = "persist/NotificationManager!"+NotificationManagerRemote.class.getCanonicalName();

	public static NotificationManagerRemote getInstance() {
		return (NotificationManagerRemote) ServiceLocator.getinstance().getProxy(jndi);

	}
	public void doAdd(Notification a) {
		getInstance().add(a);
	}
	public List<Notification> doGetAll() {
		return getInstance().getAllNotification();
	}
	public void doUpdate(Notification a){
		
		getInstance().update(a);
		
		
	}
	public void doDelete(Notification a){
		
		getInstance().delete(a);
		
		
	}
	public List<Notification> doFindByLevel(int level) {
		return getInstance().findByLevel(level);
	}
	
}
