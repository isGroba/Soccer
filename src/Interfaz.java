import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;

public class Interfaz {

	private JFrame frame;
	private JTable resulsTable;
	private JComboBox<String> countryComboBox;
	private JComboBox<League> leagueComboBox;
	private JSlider sliderRound;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
//http://apiclient.resultados-futbol.com/scripts/api/api.php?tz=Europe/Madrid&format=xml&req=leagues&key=857d690c2faae0679da7766573cb258d&top=1&year=2017&country=ES
		//String s = readUrl("http://apiclient.resultados-futbol.com/scripts/api/api.php?tz=Europe/Madrid&format=xml&req=leagues&key=857d690c2faae0679da7766573cb258d&top=1&year=2017&country=IT");
		//String s2 = readUrl("http://apiclient.resultados-futbol.com/scripts/api/api.php?key=857d690c2faae0679da7766573cb258d&format=xml&req=matchs&category=1&league=1&round=6");
		
		//System.out.println(s);
		//extractResults(s2);
		//extractLeagues(s);
		
		/*String xml = Extractor.readUrl("http://apiclient.resultados-futbol.com/scripts/api/api.php?tz=Europe/Madrid&format=xml&req=leagues&key=857d690c2faae0679da7766573cb258d&top=1&year=2017&country=IT");
		String xml2 = Extractor.readUrl("http://apiclient.resultados-futbol.com/scripts/api/api.php?key=857d690c2faae0679da7766573cb258d&format=xml&req=matchs&category=1&league=1&round=6");		
		Extractor e= new League();
		e.readUrl(xml);
		
		Extractor e2= new Score();
		e2.readUrl(xml2);*/
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("Country");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		countryComboBox = new JComboBox<String>();
		countryComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					onCountryChange();
				}
			}
		});

		countryComboBox.setModel(new DefaultComboBoxModel(new String[] {"IT", "ES", "EN"}));
		GridBagConstraints gbc_countryComboBox = new GridBagConstraints();
		gbc_countryComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_countryComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_countryComboBox.gridx = 1;
		gbc_countryComboBox.gridy = 0;
		panel.add(countryComboBox, gbc_countryComboBox);

		JLabel lblNewLabel_1 = new JLabel("League");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		leagueComboBox = new JComboBox<League>();
		leagueComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				//de este modo solo hace la eticion a la API cuando seleccionamos
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					onLeagueChange();
				}	
			}
		});
		GridBagConstraints gbc_leagueComboBox = new GridBagConstraints();
		gbc_leagueComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_leagueComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_leagueComboBox.gridx = 1;
		gbc_leagueComboBox.gridy = 1;
		panel.add(leagueComboBox, gbc_leagueComboBox);

		JLabel lblNewLabel_2 = new JLabel("Week");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		sliderRound = new JSlider();
		sliderRound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				JSlider slider = (JSlider) arg0.getSource();
				if (!slider.getValueIsAdjusting()){
					onRoundChangue();
				}	
			}
		});
		GridBagConstraints gbc_sliderRound = new GridBagConstraints();
		gbc_sliderRound.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderRound.insets = new Insets(0, 0, 5, 0);
		gbc_sliderRound.gridx = 1;
		gbc_sliderRound.gridy = 2;
		panel.add(sliderRound, gbc_sliderRound);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		panel.add(scrollPane, gbc_scrollPane);

		resulsTable = new JTable();
		scrollPane.setViewportView(resulsTable);
		
		DefaultTableModel dtm = (DefaultTableModel) resulsTable.getModel();
		dtm.addColumn("Local");
		dtm.addColumn(" ");
		dtm.addColumn(" ");
		dtm.addColumn("Visitante");
	}

	public JComboBox<String> getCountryComboBox() {
		return countryComboBox;
	}

	public JComboBox<League> getLeagueComboBox() {
		return leagueComboBox;
	}

	public JSlider getWeekSlider() {
		return sliderRound;
	}

/*METODO DE SELECCION DEL PAIS DEL QUE QUEREMOS VER LAS LIGAS*/	
	private void onCountryChange(){
		String country = (String) countryComboBox.getSelectedItem();
		String s = Extractor.readUrl("http://apiclient.resultados-futbol.com/scripts/api/api.php?tz=Europe/Madrid&format=xml&req=leagues&key=857d690c2faae0679da7766573cb258d&top=1&year=2017&country="+country);
		System.out.println(s);
		
		leagueComboBox.removeAllItems();
		
		ArrayList<League> leagues = new League().extract(s);
		for(League l:leagues){
			leagueComboBox.addItem(l);
		}	
	}

	private void onRoundChangue(){
		fillTableResults();
	}
	
	private void onLeagueChange(){
		League league = (League)leagueComboBox.getSelectedItem();
			sliderRound.setMaximum(league.getTotalRounds());
			sliderRound.setValue(league.getCurrentRounds());
			fillTableResults();
	}
	private void fillTableResults(){
		League l = (League)leagueComboBox.getSelectedItem();
		int league =l.getId();
		int round = sliderRound.getValue();
		 
		String path = "http://apiclient.resultados-futbol.com/scripts/api/api.php?key=857d690c2faae0679da7766573cb258d&format=xml&req=matchs&category=1&league="+league+"&round="+round;
		String xml = Extractor.readUrl(path);
		ArrayList<Score> list = new Score().extract(xml);
		
		//vacio de la tabla
		DefaultTableModel dtm = (DefaultTableModel) resulsTable.getModel();
		while(dtm.getRowCount() >0){
			dtm.removeRow(0);
		}
		
		for(Score s:list){
			Object[] row = {s.getLocalName(), s.getLocalGoals(), s.getVisitorsGoals(), s.getVisitorName()};
			dtm.addRow(row);
		}
		
	}
	
	private void results(){
		String s = Extractor.readUrl("");
		
		
	}
}
