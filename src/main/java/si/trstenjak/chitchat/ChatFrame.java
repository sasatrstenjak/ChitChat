package si.trstenjak.chitchat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.apache.http.client.ClientProtocolException;

import javax.swing.JScrollPane;

import java.awt.Insets;
import javax.swing.JButton;
import si.trstenjak.chitchat.*;

public class ChatFrame extends JFrame implements ActionListener, KeyListener {

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
	
	private JButton gumb_prijava;
	private JButton gumb_odjava;
	
	public String ime;
	public String prejemnik;
	public Boolean javno;
	public Boolean online;
	//public JTextArea uporabniki_izpis;
	public JList<Uporabnik> uporabniki;
	
	public ChatFrame() {
		super(); //pokliče konstruktor od JFrame
		this.ime = System.getProperty("user.name");
		this.online = false;
		
		Container pane = this.getContentPane();
		setTitle("Klepetalnik");
		pane.setLayout(new GridBagLayout());
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
		output_gbc.gridy = 0;
		output_gbc.insets = new Insets(10,10,10,10);

		pane.add(drsnik, output_gbc);

		////// INPUT //////
		
		this.input = new JTextField(40);
		GridBagConstraints input_gbc = new GridBagConstraints();
		input_gbc.fill = GridBagConstraints.BOTH;
		input_gbc.weightx = 1.0;
		input_gbc.gridx = 2;
		input_gbc.gridy = 1;
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
		
		GridBagConstraints gumb_odjava_gbc = new GridBagConstraints();
		gumb_odjava_gbc.gridx = 0;
		gumb_odjava_gbc.gridy = 0;
		//gumb_odjava_gbc.anchor =GridBagConstraints.NORTH;
		pane.add(gumb_odjava, gumb_odjava_gbc);
		
		////// IME IN PREJEMNIK //////
		
		this.ime_input = new JTextField(10);
		GridBagConstraints ime_input_gbc = new GridBagConstraints();
		ime_input_gbc.gridx = 1;
		ime_input_gbc.gridy = 1;
		ime_input_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(ime_input, ime_input_gbc);
		ime_input.addKeyListener(this);
		
		this.ime_label = new JLabel("Ime:");
		GridBagConstraints ime_label_gbc = new GridBagConstraints();
		ime_label_gbc.gridx = 0;
		ime_label_gbc.gridy =1;
		ime_label_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(ime_label,  ime_label_gbc);
		
		this.prejemnik_input = new JTextField(10);
		GridBagConstraints posiljatelj_input_gbc = new GridBagConstraints();
		posiljatelj_input_gbc.gridx = 1;
		posiljatelj_input_gbc.gridy = 2;
		posiljatelj_input_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(prejemnik_input, posiljatelj_input_gbc);
		prejemnik_input.addKeyListener(this);
		
		JLabel prejemnik_label = new JLabel("Prejemnik:");
		GridBagConstraints prejemnik_label_gbc = new GridBagConstraints();
		prejemnik_label_gbc.gridx = 0;
		prejemnik_label_gbc.gridy = 2;
		prejemnik_label_gbc.insets = new Insets (10, 10, 10, 10);
		pane.add(prejemnik_label, prejemnik_label_gbc);
		
		
		///// UPORABNIKI /////
		uporabniki = new JList<Uporabnik>();
		
	}

	
	public void addMessage(Boolean javno, String posiljatelj, String besedilo)
			throws ClientProtocolException, URISyntaxException, IOException {
		String chat = this.output.getText();
		Povezava.poslji(javno, this.prejemnik_input.getText(),  posiljatelj, besedilo);
		this.output.setText(chat + posiljatelj + ": " + besedilo + "\n");
	}
	
	
	public void receiveMessage(String posiljatelj, String besedilo){
		String chat = this.output.getText();
		this.output.setText(chat + posiljatelj + ":" + besedilo + "\n");
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.gumb_prijava && this.ime_input.getText().equals("")){
			Povezava.prijavi(this.ime);
			this.online = true;
		} else if (e.getSource() == this.gumb_prijava){
			this.ime= this.ime_input.getText();
			Povezava.prijavi(this.ime);
			this.online = true;
		} else if (e.getSource() == this.gumb_odjava){
			Povezava.odjavi(this.ime);
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
			if (e.getKeyChar() == '\n'){
				if (this.prejemnik_input.getText().equals("")) {
					try {
						this.addMessage(true, this.ime, this.input.getText());
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
					this.input.setText("");
				}
				else {
					try {
						this.addMessage(false, this.ime, this.input.getText());
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
					this.input.setText("");
				}
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


		
	

}