package gui.itineraries;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFunction;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;

import service.itineraries.DelegateStation;
import service.transport.DelegateTransport;
import tn.mario.moovtn.entities.Station;
import tn.mario.moovtn.entities.TransportMean;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddStation {
	   public static final int MIN_ZOOM = 0;
	   public static final int MAX_ZOOM = 21;

	   /**
	    * In map.html file default zoom value is set to 4.
	    */
	   private static int zoomValue = 4;
	   
	   private static JTextField stationNameTF;

	   public static void main(String[] args) {
		   Path currentRelativePath = Paths.get("");
	       String s = currentRelativePath.toAbsolutePath().toString();
	       
	       String stations= new DelegateStation().findAllToJSON();
		   
		   try {
			   PrintWriter out = new PrintWriter(s+"\\src\\main\\resources\\stations.json");
			   out.println(stations);
			   out.close();
			   
				   
			      
		    	} catch (IOException e) {
			      e.printStackTrace();
			}
		   
		   
	       final Browser browser = new Browser();
	       
	       
	       
	       BrowserView browserView = new BrowserView(browser);
	          	       			
	       
			
			
	       JFrame frame = new JFrame("Add a New Station");
	       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	       
	       frame.add(browserView, BorderLayout.CENTER);
	       frame.setSize(900, 500);
	       frame.setLocationRelativeTo(null);
	       frame.setVisible(true);
	       
	       browser.registerFunction("AddStation", new BrowserFunction() {
			    public JSValue invoke(JSValue... args) {
			        String name = args[0].getString();
			        System.out.println("Laaaaaaaaaat: "+args[1].getString());
			        Double lat = Double.parseDouble(args[1].getString());
			        Double lng = Double.parseDouble(args[2].getString());
			        String type = args[3].getString();
			        Station station = new Station();
			        station.setName(name);
			        station.setLatitude(lat);
			        station.setLongitude(lng);
			        station.setType(type);
			        DelegateStation delegate = new DelegateStation();
			        delegate.add(station);
			        
			        
			        return JSValue.create("ok");
			    }
			});

	       
	       
	       
	       browser.loadURL(s+"\\src\\main\\resources\\map.html");
	       
	       
	      
	       
	       
	   }
}
