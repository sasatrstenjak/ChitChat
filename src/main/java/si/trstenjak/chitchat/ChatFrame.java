package si.trstenjak.chitchat;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.http.client.ClientProtocolException;

import javax.swing.JScrollPane;

import java.awt.Insets;
import javax.swing.JButton;

public class ChatFrame extends JFrame implements ActionListener, KeyListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7051796494181752680L;
	//podrazred od JFrame
	//odgovarja na dve vrsti dogodkov

	private JTextArea output;// glavno z vsemi sporočili
	private JTextField input; // okno za vpisovanje sporočila
	private JTextField ime_input;
	private JLabel ime_label;
	private JTextField prejemnik_input;
	
	public JButton gumb_prijava;
	public JButton gumb_odjava;
	
	public String ime;
	public String prejemnik;
	public Boolean javno;
	public Boolean online;
	//public JTextArea uporabniki_izpis;
	public JTextArea uporabniki;
	
	public ChatFrame() {
		super(); //pokliče konstruktor od JFrame
		this.ime = System.getProperty("user.name");
		this.online = false;
		
		Container pane = this.getContentPane();
		setTitle("Klepetalnik");
		pane.setLayout(new GridBagLayout());
		pane.setBackground(Color.lightGray);
		//this.setMinimumSize(new Dimension(500, 500));
		
	
		///// OUTPUT ///////
		
		this.output = new JTextArea(20,40);
		this.output.setEditable(false);
		JScrollPane drsnik = new JScrollPane(output);
		
		GridBagConstraints output_gbc = new GridBagConstraints();
		output_gbc.fill = GridBagConstraints.BOTH;
		output_gbc.weightx = 1.0;
		output_gbc.weighty = 1.0;// če povečamo okno, bo output okno zavzelo več prostora v x in y smeri
		output_gbc.gridx = 2;
		output_gbc.gridy = 1;
		output_gbc.insets = new Insets(10,10,10,10);

		pane.add(drsnik, output_gbc);

		////// INPUT //////
		
		this.input = new JTextField(40);
		GridBagConstraints input_gbc = new GridBagConstraints();
		input_gbc.fill = GridBagConstraints.BOTH;
		input_gbc.weightx = 1.0;
		input_gbc.gridx = 2;
		input_gbc.gridy = 2;
		input_gbc.insets = new Insets(10, 7, 10, 7);
		pane.add(input, input_gbc);
		input.addKeyListener(this);

		
		////// GUMBI //////
		
		this.gumb_prijava = new JButton("Prijava");
		gumb_prijava.addActionListener(this);
		
		GridBagConstraints gumb_prijava_gbc = new GridBagConstraints();
		gumb_prijava_gbc.gridx = 0;
		gumb_prijava_gbc.anchor =GridBagConstraints.NORTH;
		gumb_prijava_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(gumb_prijava, gumb_prijava_gbc);
		
		
		this.gumb_odjava = new JButton("Odjava");
		gumb_odjava.addActionListener(this);
		this.gumb_odjava.setEnabled(false);
		
		GridBagConstraints gumb_odjava_gbc = new GridBagConstraints();
		gumb_odjava_gbc.gridx = 1;
		gumb_odjava_gbc.anchor = GridBagConstraints.NORTH;
		gumb_odjava_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(gumb_odjava, gumb_odjava_gbc);
		
		////// IME IN PREJEMNIK //////
		
		this.ime_input = new JTextField(10);
		GridBagConstraints ime_input_gbc = new GridBagConstraints();
		ime_input_gbc.gridx = 1;
		ime_input_gbc.gridy = 2;
		ime_input_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(ime_input, ime_input_gbc);
		ime_input.addKeyListener(this);
		
		this.ime_label = new JLabel("Ime:");
		GridBagConstraints ime_label_gbc = new GridBagConstraints();
		ime_label_gbc.gridx = 0;
		ime_label_gbc.gridy = 2;
		ime_label_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(ime_label,  ime_label_gbc);
		
		this.prejemnik_input = new JTextField(10);
		GridBagConstraints posiljatelj_input_gbc = new GridBagConstraints();
		posiljatelj_input_gbc.gridx = 1;
		posiljatelj_input_gbc.gridy = 3;
		posiljatelj_input_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(prejemnik_input, posiljatelj_input_gbc);
		prejemnik_input.addKeyListener(this);
		
		JLabel prejemnik_label = new JLabel("Prejemnik:");
		GridBagConstraints prejemnik_label_gbc = new GridBagConstraints();
		prejemnik_label_gbc.gridx = 0;
		prejemnik_label_gbc.gridy = 3;
		prejemnik_label_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(prejemnik_label, prejemnik_label_gbc);
		
		
		///// UPORABNIKI /////
		this.uporabniki = new JTextArea(20,20);
		GridBagConstraints uporabniki_gbc = new GridBagConstraints();
		uporabniki_gbc.fill = GridBagConstraints.BOTH;
		uporabniki_gbc.weightx = 1.0;
		uporabniki_gbc.weighty = 1.0;
		uporabniki_gbc.gridx = 3;
		uporabniki_gbc.gridy = 1;
		uporabniki_gbc.insets = new Insets(10,10,10,10);
		
		JScrollPane drsnik_uporabniki = new JScrollPane(uporabniki);
		
		pane.add(drsnik_uporabniki, uporabniki_gbc);
		
		JLabel uporabniki_label = new JLabel("Prisotni uporabniki:");
		GridBagConstraints uporabniki_label_gbc = new GridBagConstraints();
		uporabniki_label_gbc.gridx = 3;
		uporabniki_label_gbc.gridy = 0;
		uporabniki_label_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(uporabniki_label, uporabniki_label_gbc);
	}
	
	
	public void izpisiPrijavljene () throws ClientProtocolException, IOException, URISyntaxException {
		ArrayList<Uporabnik> uporabniki = Povezava.uporabniki();
		for (Uporabnik x : uporabniki) {
			String izpisani = this.uporabniki.getText();
			this.uporabniki.setText(izpisani + x.toString() + "/n");
			
		}
	}
	public void addMessage(Sporocilo s)
			throws ClientProtocolException, URISyntaxException, IOException {
		String chat = this.output.getText();
		String prejemnik = (s.isJavno() ? "ALL" : s.getPrejemnik());
		this.output.setText(chat + s.getPosiljatelj() + " -> " + prejemnik + ": " + s.getBesedilo() + "\n");
	}
	
	
	public void receiveMessage() throws ClientProtocolException, URISyntaxException, IOException {
		for (Sporocilo s : Povezava.prejmi(this.ime)){
				addMessage(s);
		}
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Robot robot = new Robot(this);
		if (e.getSource() == this.gumb_prijava && this.ime_input.getText().equals("")){
			Povezava.prijavi(this.ime);
			//this.gumb_prijava.setEnabled(false);
			//this.gumb_odjava.setEnabled(true);
			this.online = true;
			robot.activate();
		} else if (e.getSource() == this.gumb_prijava){
			this.ime= this.ime_input.getText();
			Povezava.prijavi(this.ime);
			//this.gumb_prijava.setEnabled(false);
			//this.gumb_odjava.setEnabled(true);
			this.online = true;
			robot.activate();
		} else if (e.getSource() == this.gumb_odjava){
			Povezava.odjavi(this.ime);
			//this.gumb_odjava.setEnabled(false);
			//this.gumb_prijava.setEnabled(true);
			this.online = false;
		}
	}
	
	
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == this.ime_input) {
			if (e.getKeyChar() == '\n') {
				this.ime = this.ime_input.getText();
			}
		}
		
		else if (e.getSource() == this.input){
			String besedilo = this.input.getText();
			if (e.getKeyChar() == '\n'){
				if (this.prejemnik_input.getText().equals("")) {
					try {
						addMessage(new Sporocilo(this.ime, besedilo));
						Povezava.poslji_javno(this.ime, besedilo);
						
					} catch (ClientProtocolException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					String prejemnik = prejemnik_input.getText();
					try {
						addMessage(new Sporocilo(this.ime, prejemnik, besedilo));
						Povezava.poslji_zasebno(this.ime, prejemnik, besedilo);
					} catch (ClientProtocolException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
				
				this.input.setText("");
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}


	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowClosing(WindowEvent e) {
		if (this.ime != null) {
			Povezava.odjavi(this.ime);
			e.getWindow().dispose();
		}
		
		
	}


		
	

}