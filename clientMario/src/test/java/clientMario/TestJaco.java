package clientMario;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TestJaco extends JFrame {

	private JPanel contentPane;
	 private jaco.mp3.player.MP3Player mP3Player1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestJaco frame = new TestJaco();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestJaco() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		mP3Player1 = new jaco.mp3.player.MP3Player();
		 File SoundsFolder;
	        SoundsFolder = new File("C:/Users/Mimo/Desktop/musique");
	        File[] f;
	        f = SoundsFolder.listFiles();

	        for (File f1 : f) {
	            if (f1.getName().contains(".mp3")) {
	                mP3Player1.addToPlayList(f1);
	            }
	        }
	        mP3Player1.setRepeat(true);
	        mP3Player1.play();
	        getContentPane().add(mP3Player1);
	        mP3Player1.setBounds(1220, 10, 90, 18);
	}

}
