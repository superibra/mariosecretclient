package clientMario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

import com.sun.speech.freetts.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestSpeech extends JFrame {

	private JPanel contentPane;
	private static final String VOICENAME="kevin";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestSpeech frame = new TestSpeech();
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
	public TestSpeech() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 0, 170, 253);
		contentPane.add(textArea);
		
		JButton btnSpeak = new JButton("Speak");
		btnSpeak.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Voice voice;
				VoiceManager vm = VoiceManager.getInstance();
				voice=vm.getVoice(VOICENAME);
				voice.allocate();
				
					
					voice.speak("Speak");
			}
		});
		btnSpeak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Voice voice;
				VoiceManager vm = VoiceManager.getInstance();
				voice=vm.getVoice(VOICENAME);
				voice.allocate();
				
					
					voice.speak(textArea.getText());
					
			
				
			}
		});
		btnSpeak.setBounds(252, 92, 97, 25);
		contentPane.add(btnSpeak);
	}
}
