package clientMario;

import javax.naming.InitialContext;

import tn.mario.moovtn.entities.*;

import org.junit.Before;
import org.junit.Test;


import tn.mario.moovtn.entities.*;
import tn.mario.moovtn.remotes.*;
import tn.mario.moovtn.implementations.*;

public class NotifTest  {
	NotificationManagerRemote userService;
	@Before
	public void setUp() throws Exception{
		userService=  (NotificationManagerRemote) new InitialContext().lookup("persist/NotificationManager!"+NotificationManagerRemote.class.getCanonicalName());
		
		
		
	}
	@Test
public void addUser() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
	 Notification notif = new Notification();
	notif.setDescription("Salut");
	notif.setId(1);
		//System.out.println(Users.class.getField("serialVersionUID").get(user));
		userService.add(notif);
		//System.out.println(userService.getAllUsers().get(0).getNom());
		System.out.println("add ok");
		
	}
}
