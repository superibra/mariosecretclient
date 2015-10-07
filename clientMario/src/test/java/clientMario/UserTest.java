package clientMario;

import javax.naming.InitialContext;

import tn.mario.moovtn.entities.*;

import org.junit.Before;
import org.junit.Test;

import tn.mario.moovtn.business.users.GestionUserRemote;

public class UserTest  {
	GestionUserRemote userService;
	@Before
	public void setUp() throws Exception{
		userService=  (GestionUserRemote) new InitialContext().lookup("persist/GestionUser!"+GestionUserRemote.class.getCanonicalName());
		
		
		
	}
	@Test
public void addUser() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		
		Users user=new Users();
		user.setLogin("user1");
		user.setNom("Bellar");
		//System.out.println(Users.class.getField("serialVersionUID").get(user));
		//userService.addUser(user);
		System.out.println(userService.getAllUsers().get(0).getNom());
		System.out.println("add ok");
		
	}
}
