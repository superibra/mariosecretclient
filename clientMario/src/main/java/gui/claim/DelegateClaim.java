package gui.claim;

import java.util.List;

import service.connection.ConnectionLocator;
import servicelocator.ServiceLocator;
import tn.mario.moovtn.entities.Claim;
import tn.mario.moovtn.entities.TransportMean;
import tn.mario.moovtn.implementations.ClaimsImplementation;
import tn.mario.moovtn.remotes.TransportMeanService;
import tn.mario.moovtn.remotes.IClaimsImplementationRemote;

public class DelegateClaim {
	String jndi = "/persist/ClaimsImplementation!tn.mario.moovtn.remotes.IClaimsImplementationRemote";
	public static  IClaimsImplementationRemote getInstance(String jndi){
		return (IClaimsImplementationRemote)ServiceLocator .getinstance().getProxy(jndi);
	}
	public void delete(int  id){
		getInstance(jndi).deleteClaim(id);
	}
	public List<Claim> findAll() {
		return getInstance(jndi).findall();
	}
	public Claim findbyDate(String c){
		return getInstance(jndi).findbyDate(c);
	}
	public Claim findClaimById(int id){
		return getInstance(jndi).findClaimById(id);
	}
	public void update(Claim c){
		getInstance(jndi).update(c);
		
	}
}
