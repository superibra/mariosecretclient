package gui.mailling;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import tn.mario.moovtn.remotes.GwMessageRemote;



public class testmail {
	Context context;

	@Test
	public void test() {
		try {
			context = new InitialContext();
			GwMessageRemote mailRemote=(GwMessageRemote) context.lookup("/persist/GwMessage!tn.mario.moovtn.remotes.GwMessageRemote");
			mailRemote.sendEmail("nourelhouda.benahmed@esprit.tn","loudaesamou7a", "nourti", "5aryouna");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
