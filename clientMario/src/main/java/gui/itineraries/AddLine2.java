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

import service.itineraries.DelegateLineService;
import service.itineraries.DelegateStation;
import service.transport.DelegateTransport;
import tn.mario.moovtn.entities.Line;
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

public class AddLine2 {
	   public  final int MIN_ZOOM = 0;
	   public  final int MAX_ZOOM = 21;

	   /**
	    * In map.html file default zoom value is set to 4.
	    */
	   private  int zoomValue = 4;
	   
	   private  JTextField stationNameTF;
	   private  Line line;
	   
	   

	   public AddLine2(Line line) {
		   this.line = line;
		   Path currentRelativePath = Paths.get("");
	       String s = currentRelativePath.toAbsolutePath().toString();
	       
	       String stations= new DelegateStation().findAllToJSON(line.getStations());
		   
		   try {
			   PrintWriter out = new PrintWriter(s+"\\src\\main\\resources\\stations.json");
			   out.println(stations);
			   out.close();
			   
				   
			      
		    	} catch (IOException e) {
			      e.printStackTrace();
			}
		   
		   
	       final Browser browser = new Browser();
	       
	       
	       
	       BrowserView browserView = new BrowserView(browser);
	          	       			
	       
			
			
	       JFrame frame = new JFrame("Add a New Line Path");
	       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	       
	       frame.add(browserView, BorderLayout.CENTER);
	       frame.setSize(900, 500);
	       frame.setLocationRelativeTo(null);
	       frame.setVisible(true);
	       
	       
	       browser.registerFunction("AddLine", new BrowserFunction() {
			    public JSValue invoke(JSValue... args) {
			    	
			        line.setPath("{points = {points:["+args[0].getString()+"]}}");
			        new DelegateLineService().add(line);
			        
			        return JSValue.create("ok");
			    }
			});
	       
	       
	       
	       browser.loadURL(s+"\\src\\main\\resources\\map-add-line.html");
	       
	       
	      
	       
	       
	   }
}
