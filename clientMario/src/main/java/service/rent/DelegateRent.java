package service.rent;

import servicelocator.ServiceLocator;
import tn.mario.moovtn.remotes.RentService;

import java.util.List;

 


import javax.persistence.Query;

import tn.mario.moovtn.entities.Rent;

public class DelegateRent {

	String jndi="/persist/RentImplementation!"+RentService.class.getCanonicalName();
	public static RentService getInstance(String jndi){
		
		return (RentService) ServiceLocator.getinstance().getProxy(jndi);
	} 
	
	public List<Rent> findByMembers(int members) {
		 
		return getInstance(jndi).findByMembers(members);
		 
	}
	public List <Rent> findAll(){
		return getInstance(jndi).findAll();
	}
	
	 
	public void delete(Rent rent){
		getInstance(jndi).delete(rent);
	}
public Rent findById(int id) {
		
		return getInstance(jndi).findById(id);
	}

}
