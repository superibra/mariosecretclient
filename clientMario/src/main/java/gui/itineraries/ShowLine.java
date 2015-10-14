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

public class ShowLine {
	   public  final int MIN_ZOOM = 0;
	   public  final int MAX_ZOOM = 21;

	   /**
	    * In map.html file default zoom value is set to 4.
	    */
	   private  int zoomValue = 4;
	   
	   private  JTextField stationNameTF;
	   private  Line line;
	   
	   

	   public ShowLine(Line line) {
		   this.line = line;
		   Path currentRelativePath = Paths.get("");
	       String s = currentRelativePath.toAbsolutePath().toString();
	       
	       String stations= new DelegateLineService().findAllStationsByLineIdToJSON(line.getId());
		   
		   try {
			   PrintWriter out = new PrintWriter(s+"\\src\\main\\resources\\stations.json");
			   out.println(stations);
			   out.close();
			   
				   
			      
		    	} catch (IOException e) {
			      e.printStackTrace();
			}
		   
		   try {
			   PrintWriter out = new PrintWriter(s+"\\src\\main\\resources\\polyline.json");
			   out.println(line.getPath());
			   out.close();
			   
				   
			      
		    	} catch (IOException e) {
			      e.printStackTrace();
			}
		   
		   
	       final Browser browser = new Browser();
	       
	       
	       
	       BrowserView browserView = new BrowserView(browser);
	          	       			
	       
			
			
	       JFrame frame = new JFrame("Line: "+line.getName());
	       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	       
	       frame.add(browserView, BorderLayout.CENTER);
	       frame.setSize(900, 500);
	       frame.setLocationRelativeTo(null);
	       frame.setVisible(true);
	       
	       browser.registerFunction("Previous", new BrowserFunction() {
			    public JSValue invoke(JSValue... args) {
			    	
			        frame.setVisible(false);
			        new LinesList();
			        
			        return JSValue.create("ok");
			    }
			});
	       
	       
	      
	       
	       
	       
	       browser.loadURL(s+"\\src\\main\\resources\\map-show-line.html");
	       
	       
	      
	       
	       
	   }
}
