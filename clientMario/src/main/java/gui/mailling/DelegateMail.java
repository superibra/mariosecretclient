package gui.mailling;

import servicelocator.ServiceLocator;
import tn.mario.moovtn.remotes.GwMessageRemote;


public class DelegateMail {
	String jndi = "/persist/GwMessage!tn.mario.moovtn.remotes.GwMessageRemote";
	public static  GwMessageRemote getInstance(String jndi){
		return (GwMessageRemote)ServiceLocator .getinstance().getProxy(jndi);
	}
	public void sendEmail(String to, String from, String subject, String content){
		getInstance(jndi).sendEmail(to, from, subject, content);
	}

}
