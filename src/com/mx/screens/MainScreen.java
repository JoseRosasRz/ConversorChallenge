package com.mx.screens;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class MainScreen extends JFrame {

	private JPanel contenedor;
	private JPanel bgContenedorR;
	private JPanel bgContenedorL;
	protected int xMouse, yMouse;
	private CardLayout cardLayout;
	private CardLayout cardLayoutIBgR;
	private CardLayout cardLayoutIBgL;
	private JTextField inputTxtC;
	private JTextField inputTxtD;
	private JTextField inputTxtM;
	private JTextField inputTxtT;
	private JTextField outputTxtD;
	private JTextField outputTxtM;
	private JTextField outputTxtC;
	private JTextField outputTxtT;
	private ModelConverter conversion = new ModelConverter();
	private CurrencyModelConverter currencyConversion = new CurrencyModelConverter();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setLocationRelativeTo(null);
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
	public MainScreen() {
		
		setUndecorated(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainScreen.class.getResource("/com/mx/icons/converterIcon.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 400);
		contenedor = new JPanel();
		contenedor.setBorder(null);

		setContentPane(contenedor);
		contenedor.setLayout(new CardLayout(0, 0));
		cardLayout = (CardLayout) contenedor.getLayout();
		
		final JPanel mainScreen = new JPanel();
		mainScreen.setBackground(new Color(50, 50, 50));
		contenedor.add(mainScreen, "panel1");
		mainScreen.setLayout(null);
		
		
		//Panel que funcionará como barra de encabezado para añadir boton de cierre y movimiento de ventana
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(50, 50, 50));
		headerPanel.setBounds(0, 0, 600, 38);
		mainScreen.add(headerPanel);
		headerPanel.setLayout(null);
		
		//Capturar la posicion del mouse cuando se hace click en la barra del header
		headerPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		//Recolocaremos la ventana con respecto a la posicion del mouse en la pantalla del usuario
		headerPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
						
				setLocation(x - xMouse, y - yMouse);
			}
		});
		
				
		//Panel y etiqueta que funcionan como botón de cierre de la aplicacion
		final JPanel exitBtn = new JPanel();
		exitBtn.setBackground(new Color(50, 50, 50));
		exitBtn.setBounds(550, 0, 50, 38);
		headerPanel.add(exitBtn);
		exitBtn.setLayout(null);
		
		final JLabel exitLabelBtn = new JLabel("X");
		exitLabelBtn.setForeground(new Color(255, 255, 255));
		exitLabelBtn.addMouseListener(new MouseAdapter() {
			//Se cierra el programa al clickear sobre la etiquta
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			//Eventos decorativos del boton de cierre
				@Override
				public void mouseEntered(MouseEvent e) {
					exitBtn.setBackground(Color.RED);
					exitLabelBtn.setForeground(Color.BLACK);
					exitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					exitBtn.setBackground(new Color(50,50,50));
					exitLabelBtn.setForeground(Color.WHITE);
				}
		});
		exitLabelBtn.setFont(new Font("Roboto Black", Font.BOLD, 20));
		exitLabelBtn.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabelBtn.setBounds(0, 0, 50, 38);
		exitBtn.add(exitLabelBtn);
		
		
		//Titulo principal de la ventana principal
		JLabel mainTitle = new JLabel("Converter");
		mainTitle.setForeground(new Color(255, 255, 255));
		mainTitle.setFont(new Font("Roboto", Font.BOLD, 42));
		mainTitle.setBounds(204, 38, 192, 50);
		mainScreen.add(mainTitle);
		
		//Inicia panel que actuara como botón que al clickear nos lleva al panel de conversion de divisas
		final JPanel currenciesPanel = new JPanel();
		currenciesPanel.setBackground(new Color(255, 255, 255));
		currenciesPanel.setBounds(217, 110, 150, 38);
		mainScreen.add(currenciesPanel);
		currenciesPanel.setLayout(null);
		
		final JLabel currencyLbl = new JLabel("Currencies");
		currencyLbl.setForeground(new Color(0, 0, 0));
		currencyLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		currencyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		currencyLbl.setBounds(0, 0, 150, 38);
		currenciesPanel.add(currencyLbl);
		currencyLbl.addMouseListener(new MouseAdapter() {
			//Eventos con fines esteticos al momento de que el mouse ingrese a la JLabel de Currencies
				@Override
				public void mouseEntered(MouseEvent e) {
					currenciesPanel.setBackground(new Color(130,205,71));
					currenciesPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					cardLayoutIBgR.show(bgContenedorR, "iconBg1");
				}
				@Override
				public void mouseExited(MouseEvent e) {
					currenciesPanel.setBackground(Color.WHITE);
					cardLayoutIBgR.show(bgContenedorR, "iconBg0");
				}
			//Al clickear sobre la etiqueta "currencyLbl" nos lleva al panel de conversion de divisas
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contenedor, "panel2");
			}
		});
		
		
		//Inicia panel que actuara como botón que al clickear nos lleva al panel de conversion de temperatura
		final JPanel temperaturePanel = new JPanel();
		temperaturePanel.setBackground(new Color(255, 255, 255));
		temperaturePanel.setBounds(217, 170, 150, 38);
		mainScreen.add(temperaturePanel);
		temperaturePanel.setLayout(null);
		
		JLabel temperatureLbl = new JLabel("Temperature");
		temperatureLbl.setForeground(new Color(0, 0, 0));
		temperatureLbl.addMouseListener(new MouseAdapter() {
			//Eventos con fines esteticos al momento de que el mouse ingrese a la JLabel de Temperature
				@Override
				public void mouseEntered(MouseEvent e) {
					temperaturePanel.setBackground(new Color(39,158,255));
					temperaturePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					cardLayoutIBgL.show(bgContenedorL, "iconBg11");
				}
				@Override
				public void mouseExited(MouseEvent e) {
					temperaturePanel.setBackground(Color.WHITE);
					cardLayoutIBgL.show(bgContenedorL, "iconBg01");
				}
			//Al clickear sobre la etiqueta "temperatureLbl" nos lleva al panel de conversion de temperaturas
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contenedor, "panel3");
			}
		});
		temperatureLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		temperatureLbl.setBounds(0, 0, 150, 38);
		temperatureLbl.setHorizontalAlignment(SwingConstants.CENTER);
		temperaturePanel.add(temperatureLbl);
		
		
		//Inicia panel que actuara como botón que al clickear nos lleva al panel de conversion de masa
		final JPanel massPanel = new JPanel();
		massPanel.setBackground(new Color(255, 255, 255));
		massPanel.setBounds(217, 230, 150, 38);
		mainScreen.add(massPanel);
		massPanel.setLayout(null);
		
		JLabel massLbl = new JLabel("Mass");
		massLbl.setForeground(new Color(0, 0, 0));
		massLbl.addMouseListener(new MouseAdapter() {
			//Eventos con fines esteticos al momento de que el mouse ingrese a la JLabel de Mass
				@Override
				public void mouseEntered(MouseEvent e) {
					massPanel.setBackground(new Color(242,190,34));
					massPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					cardLayoutIBgR.show(bgContenedorR, "iconBg3");
				}
				@Override
				public void mouseExited(MouseEvent e) {
					massPanel.setBackground(Color.WHITE);
					cardLayoutIBgR.show(bgContenedorR, "iconBg0");
				}
			//Al clickear sobre la etiqueta "massLbl" nos lleva al panel de conversion de Masa
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contenedor, "panel4");
			}
		});
		massLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		massLbl.setHorizontalAlignment(SwingConstants.CENTER);
		massLbl.setBounds(0, 0, 150, 38);
		massPanel.add(massLbl);
		
		
		//Inicia panel que actuara como botón que al clickear nos lleva al panel de conversion de distancia
		final JPanel distancePanel = new JPanel();
		distancePanel.setBackground(new Color(255, 255, 255));
		distancePanel.setBounds(217, 290, 150, 38);
		mainScreen.add(distancePanel);
		distancePanel.setLayout(null);
		
		JLabel distanceLbl = new JLabel("Distance");
		distanceLbl.setForeground(new Color(0, 0, 0));
		distanceLbl.addMouseListener(new MouseAdapter() {
			//Eventos con fines esteticos al momento de que el mouse ingrese a la JLabel de Distance
				@Override
				public void mouseEntered(MouseEvent e) {
					distancePanel.setBackground(new Color(207,48,48));
					distancePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					cardLayoutIBgL.show(bgContenedorL, "iconBg12");
				}
				@Override
				public void mouseExited(MouseEvent e) {
					distancePanel.setBackground(Color.WHITE);
					cardLayoutIBgL.show(bgContenedorL, "iconBg01");
				}
			//Al clickear sobre la etiqueta "distanceLbl" nos lleva al panel de conversion de Distancia
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contenedor, "panel5");
			}
		});
		distanceLbl.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		distanceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		distanceLbl.setBounds(0, 0, 150, 38);
		distancePanel.add(distanceLbl);
		
		//Panel derecho que contiene los iconos de Currencies y Masa
		bgContenedorR = new JPanel();
		bgContenedorR.setBackground(new Color(50, 50, 50));
		bgContenedorR.setBounds(395, 38, 200, 311);
		mainScreen.add(bgContenedorR);
		bgContenedorR.setLayout(new CardLayout(0, 0));
		cardLayoutIBgR = (CardLayout) bgContenedorR.getLayout();
		
		//JLbael vacia cuando el mouse no se encuentre sobre ninguna opcion
		JLabel emptyIconRight = new JLabel("");
		emptyIconRight.setIcon(null);
		emptyIconRight.setHorizontalAlignment(SwingConstants.CENTER);
		bgContenedorR.add(emptyIconRight, "iconBg0");
		
		//JLabel con el icono de Divisas a ser mostrado cuando el mouse este sobre el panel de Currencies
		JLabel currencyIcon = new JLabel("");
		currencyIcon.setHorizontalAlignment(SwingConstants.CENTER);
		currencyIcon.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/bgTest2.png")));
		bgContenedorR.add(currencyIcon, "iconBg1");
		
		//JLabel con el icono de Masa a ser mostrado cuando el mouse este sobre el panel de Mass
		JLabel massIcon = new JLabel("");
		massIcon.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/massBgIcon.png")));
		massIcon.setHorizontalAlignment(SwingConstants.CENTER);
		bgContenedorR.add(massIcon, "iconBg3");
		
		//Panel izquierdo que contiene los iconos de Temperature y Distance
		bgContenedorL = new JPanel();
		bgContenedorL.setBackground(new Color(50, 50, 50));
		bgContenedorL.setBounds(5, 38, 200, 311);
		mainScreen.add(bgContenedorL);
		bgContenedorL.setLayout(new CardLayout(0, 0));
		cardLayoutIBgL = (CardLayout) bgContenedorL.getLayout();
		
		//JLbl vacia 
		JLabel emptyIconLeft = new JLabel("");
		bgContenedorL.add(emptyIconLeft, "iconBg01");
		
		//JLbl con el icono de Temperature
		JLabel tempIcon = new JLabel("");
		tempIcon.setHorizontalAlignment(SwingConstants.CENTER);
		tempIcon.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/temptBgIcon.png")));
		bgContenedorL.add(tempIcon, "iconBg11");
		
		//JLbl con el icono de Distance
		JLabel distanceIcon = new JLabel("");
		distanceIcon.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/distBgIcon.png")));
		distanceIcon.setHorizontalAlignment(SwingConstants.CENTER);
		bgContenedorL.add(distanceIcon, "iconBg12");
		
		//Panel que actua como fondo del boton que lleva al panel About
		 final RoundPanel aboutPanelBttn = new RoundPanel();
         aboutPanelBttn.setBackground(new Color(70, 70, 70));
         aboutPanelBttn.setBounds(5, 350, 45, 45);
         mainScreen.add(aboutPanelBttn);
         aboutPanelBttn.setLayout(null);
         
         JLabel aboutBttnLbl = new JLabel("");
         aboutBttnLbl.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mouseEntered(MouseEvent e) {
         		
         		aboutPanelBttn.setBackground(new Color(110,110,110));
         		aboutPanelBttn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         		
         	}
         	@Override
         	public void mouseExited(MouseEvent e) {
         		
         		aboutPanelBttn.setBackground(new Color(70,70,70));
         	}
         	@Override
         	public void mouseClicked(MouseEvent e) {
         		
         		cardLayout.show(contenedor, "panel6");
         	}
         });
         aboutBttnLbl.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/about1.png")));
         aboutBttnLbl.setHorizontalAlignment(SwingConstants.CENTER);
         aboutBttnLbl.setBounds(0, 0, 45, 45);
         aboutPanelBttn.add(aboutBttnLbl);
         
		
		
		//Inicia primer pantalla secundaria, muestra la interfaz para la conversion de divisas
		JPanel currenciesPanelScreen = new JPanel();
		currenciesPanelScreen.setBackground(new Color(50, 50, 50));
		contenedor.add(currenciesPanelScreen, "panel2");
		currenciesPanelScreen.setLayout(null);
		
		//Cabecera que contiene el boton de cierre de la app y boton de retroceso para regresar al menu principal
		JPanel headerCurrencies = new JPanel();
		//Capturar la posicion del mouse cuando se hace click en la barra del header
		headerCurrencies.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		//Recolocaremos la ventana con respecto a la posicion del mouse en la pantalla del usuario
		headerCurrencies.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
						
				setLocation(x - xMouse, y - yMouse);
			}
		});
		headerCurrencies.setBackground(new Color(50, 50, 50));
		headerCurrencies.setBounds(0, 0, 600, 38);
		currenciesPanelScreen.add(headerCurrencies);
		headerCurrencies.setLayout(null);
		
		final JPanel exitBtnC = new JPanel();
		exitBtnC.setBackground(new Color(50, 50, 50));
		exitBtnC.setBounds(550, 0, 50, 38);
		headerCurrencies.add(exitBtnC);
		exitBtnC.setLayout(null);
		
		final JLabel exitLabelBtnC = new JLabel("X");
		exitLabelBtnC.setForeground(new Color(255, 255, 255));
		exitLabelBtnC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exitBtnC.setBackground(Color.RED);
				exitLabelBtnC.setForeground(Color.BLACK);
				exitBtnC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitBtnC.setBackground(new Color(50,50,50));
				exitLabelBtnC.setForeground(Color.WHITE);
			}
		});
		exitLabelBtnC.setFont(new Font("Roboto Black", Font.BOLD, 20));
		exitLabelBtnC.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabelBtnC.setBounds(0, 0, 50, 38);
		exitBtnC.add(exitLabelBtnC);
		
		//Boton que regresa al menu principal
		final JPanel backBttnC = new JPanel();
		backBttnC.setLayout(null);
		backBttnC.setBackground(new Color(50, 50, 50));
		backBttnC.setBounds(0, 0, 50, 38);
		headerCurrencies.add(backBttnC);
		
		JLabel backLblC = new JLabel("");
		backLblC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backBttnC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				backBttnC.setBackground(new Color(70,70,70));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backBttnC.setBackground(new Color(50,50,50));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contenedor, "panel1");
				
				inputTxtC.setText("");
				outputTxtC.setText("");
			}
		});
		
		backLblC.setIconTextGap(3);
		backLblC.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/left-arrow1.png")));
		backLblC.setHorizontalAlignment(SwingConstants.CENTER);
		backLblC.setForeground(Color.WHITE);
		backLblC.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 22));
		backLblC.setBounds(0, 0, 50, 38);
		backBttnC.add(backLblC);
		
		JLabel subTitleCurrency = new JLabel("Currency converter");
		subTitleCurrency.setFont(new Font("Roboto Black", Font.PLAIN, 35));
		subTitleCurrency.setForeground(new Color(255, 255, 255));
		subTitleCurrency.setBounds(143, 61, 314, 49);
		currenciesPanelScreen.add(subTitleCurrency);
		
		
		final JComboBox<String> inputComboBoxC = new JComboBox<String>();
		inputComboBoxC.setFont(new Font("Roboto", Font.PLAIN, 12));
		inputComboBoxC.setBorder(null);
		inputComboBoxC.setBackground(new Color(255, 255, 255));
		inputComboBoxC.setBounds(50, 150, 205, 50);
		
		Hashtable<Object, ImageIcon> elementsList;
		elementsList = new Hashtable<>();
		
		inputComboBoxC.addItem(" American Dollar (USD)");
		inputComboBoxC.addItem(" Mexican Peso (MXN)");
		inputComboBoxC.addItem("Canadian Dollar (CAD)");
		inputComboBoxC.addItem("Brazilian Real (BRL)");
		inputComboBoxC.addItem("Peruvian Sol (PEN)");
		inputComboBoxC.addItem("Colombian Peso (COP)");
		inputComboBoxC.addItem("Euro (EUR)");
		inputComboBoxC.addItem("Japanese Yen (JPY)");
		inputComboBoxC.addItem("Chinese Yuan (CNY)");
		inputComboBoxC.addItem("Russian Ruble (RUB)");
		
		elementsList.put(" American Dollar (USD)", getIcono("/com/mx/icons/usaIcon.png"));
		elementsList.put(" Mexican Peso (MXN)", getIcono("/com/mx/icons/mexIcon.png"));
		elementsList.put("Canadian Dollar (CAD)", getIcono("/com/mx/icons/canIcon.png"));
		elementsList.put("Brazilian Real (BRL)", getIcono("/com/mx/icons/brIcon.png"));
		elementsList.put("Peruvian Sol (PEN)", getIcono("/com/mx/icons/peruIcon.png"));
		elementsList.put("Colombian Peso (COP)", getIcono("/com/mx/icons/coloIcon.png"));
		elementsList.put("Euro (EUR)", getIcono("/com/mx/icons/eurIcon.png"));
		elementsList.put("Japanese Yen (JPY)", getIcono("/com/mx/icons/japIcon.png"));
		elementsList.put("Chinese Yuan (CNY)", getIcono("/com/mx/icons/chinIcon.png"));
		elementsList.put("Russian Ruble (RUB)", getIcono("/com/mx/icons/rusIcon.png"));
		
		JComboBoxRenderer comboRender = new JComboBoxRenderer(elementsList);
		inputComboBoxC.setRenderer(comboRender);
		
		currenciesPanelScreen.add(inputComboBoxC);
		
		inputTxtC = new JTextField();
		inputTxtC.setFont(new Font("Roboto", Font.PLAIN, 16));
		inputTxtC.setHorizontalAlignment(SwingConstants.CENTER);
		inputTxtC.setBorder(null);
		inputTxtC.setBounds(50, 210, 205, 50);
		currenciesPanelScreen.add(inputTxtC);
		inputTxtC.setColumns(10);
		
		
		
		final JComboBox<String> outputComboBoxC = new JComboBox<String>();
		outputComboBoxC.setFont(new Font("Roboto", Font.PLAIN, 12));
		outputComboBoxC.setBorder(null);
		outputComboBoxC.setBackground(Color.WHITE);
		outputComboBoxC.setBounds(345, 150, 205, 50);
		
		Hashtable<Object, ImageIcon> elementsListO;
		elementsListO = new Hashtable<>();
		
		outputComboBoxC.addItem(" Mexican Peso (MXN)");
		outputComboBoxC.addItem(" American Dollar (USD)");
		outputComboBoxC.addItem("Canadian Dollar (CAD)");
		outputComboBoxC.addItem("Brazilian Real (BRL)");
		outputComboBoxC.addItem("Peruvian Sol (PEN)");
		outputComboBoxC.addItem("Colombian Peso (COP)");
		outputComboBoxC.addItem("Euro (EUR)");
		outputComboBoxC.addItem("Japanese Yen (JPY)");
		outputComboBoxC.addItem("Chinese Yuan (CNY)");
		outputComboBoxC.addItem("Russian Ruble (RUB)");
		
		elementsListO.put(" Mexican Peso (MXN)", getIcono("/com/mx/icons/mexIcon.png"));
		elementsListO.put(" American Dollar (USD)", getIcono("/com/mx/icons/usaIcon.png"));
		elementsListO.put("Canadian Dollar (CAD)", getIcono("/com/mx/icons/canIcon.png"));
		elementsListO.put("Brazilian Real (BRL)", getIcono("/com/mx/icons/brIcon.png"));
		elementsListO.put("Peruvian Sol (PEN)", getIcono("/com/mx/icons/peruIcon.png"));
		elementsListO.put("Colombian Peso (COP)", getIcono("/com/mx/icons/coloIcon.png"));
		elementsListO.put("Euro (EUR)", getIcono("/com/mx/icons/eurIcon.png"));
		elementsListO.put("Japanese Yen (JPY)", getIcono("/com/mx/icons/japIcon.png"));
		elementsListO.put("Chinese Yuan (CNY)", getIcono("/com/mx/icons/chinIcon.png"));
		elementsListO.put("Russian Ruble (RUB)", getIcono("/com/mx/icons/rusIcon.png"));
		
		JComboBoxRenderer comboRenderO = new JComboBoxRenderer(elementsList);
		outputComboBoxC.setRenderer(comboRenderO);
		
		currenciesPanelScreen.add(outputComboBoxC);
		
		
		
		outputTxtC = new JTextField();
		outputTxtC.setDisabledTextColor(new Color(255, 255, 255));
		outputTxtC.setEditable(false);
		outputTxtC.setFont(new Font("Roboto", Font.PLAIN, 16));
		outputTxtC.setHorizontalAlignment(SwingConstants.CENTER);
		outputTxtC.setBorder(null);
		outputTxtC.setColumns(10);
		outputTxtC.setBounds(345, 210, 205, 50);
		currenciesPanelScreen.add(outputTxtC);
		
		final JPanel convertBttnC = new JPanel();
		convertBttnC.setBackground(new Color(130, 205, 71));
		convertBttnC.setBounds(222, 315, 155, 58);
		currenciesPanelScreen.add(convertBttnC);
		convertBttnC.setLayout(null);
		
		final JLabel convertLblBttnC = new JLabel("Convert");
		convertLblBttnC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				convertBttnC.setBackground(new Color(90,165,31));
				convertLblBttnC.setForeground(Color.BLACK);
				convertBttnC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				convertBttnC.setBackground(new Color(130, 205, 71));
				convertLblBttnC.setForeground(new Color(50,50,50));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String txt = currencyConversion.toConvertCurrency((String)inputComboBoxC.getSelectedItem(), (String)outputComboBoxC.getSelectedItem(), 
						(Double)Double.parseDouble(inputTxtC.getText()));
				outputTxtC.setText(txt);
				
			}
		});
		convertLblBttnC.setHorizontalAlignment(SwingConstants.CENTER);
		convertLblBttnC.setFont(new Font("Roboto Medium", Font.PLAIN, 23));
		convertLblBttnC.setBackground(Color.WHITE);
		convertLblBttnC.setBounds(0, 0, 155, 58);
		convertBttnC.add(convertLblBttnC);
		
		final JPanel swapBttnC = new JPanel();
		swapBttnC.setBackground(new Color(40, 40, 40));
		swapBttnC.setForeground(new Color(0, 0, 0));
		swapBttnC.setBounds(278, 181, 43, 37);
		currenciesPanelScreen.add(swapBttnC);
		swapBttnC.setLayout(null);
		
		JLabel swapLblC = new JLabel("");
		swapLblC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				swapBttnC.setBackground(new Color(70,70,70));
				swapBttnC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				swapBttnC.setBackground(new Color(40,40,40));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
                String selectedValueCombo1 = (String) inputComboBoxC.getSelectedItem();
                String selectedValueCombo2 = (String) outputComboBoxC.getSelectedItem();

                inputComboBoxC.removeItem(selectedValueCombo1);
                outputComboBoxC.removeItem(selectedValueCombo2);

                inputComboBoxC.addItem(selectedValueCombo2);
                outputComboBoxC.addItem(selectedValueCombo1);

                inputComboBoxC.setSelectedItem(selectedValueCombo2);
                outputComboBoxC.setSelectedItem(selectedValueCombo1);
				
			}
		});
		swapLblC.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/exchange (2).png")));
		swapLblC.setHorizontalAlignment(SwingConstants.CENTER);
		swapLblC.setBounds(0, 0, 44, 36);
		swapBttnC.add(swapLblC);
		
		//Inicia segunda pantalla secundaria, muestra la interfaz para la conversion de temperatura
		JPanel temperaturePanelScreen = new JPanel();
		temperaturePanelScreen.setBackground(new Color(50, 50, 50));
		contenedor.add(temperaturePanelScreen, "panel3");
		temperaturePanelScreen.setLayout(null);
		
		
		//Cabecera que contiene el boton de cierre de la app y boton de retroceso para regresar al menu principal
		JPanel headerTemperature = new JPanel();
		//Capturar la posicion del mouse cuando se hace click en la barra del header
		headerTemperature.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		//Recolocaremos la ventana con respecto a la posicion del mouse en la pantalla del usuario
		headerTemperature.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
						
				setLocation(x - xMouse, y - yMouse);
			}
		});
		headerTemperature.setBackground(new Color(50, 50, 50));
		headerTemperature.setBounds(0, 0, 600, 38);
		temperaturePanelScreen.add(headerTemperature);
		headerTemperature.setLayout(null);
		
		final JPanel exitBtnT = new JPanel();
		exitBtnT.setBackground(new Color(50, 50, 50));
		exitBtnT.setBounds(550, 0, 50, 38);
		headerTemperature.add(exitBtnT);
		exitBtnT.setLayout(null);
		
		final JLabel exitLabelBtnT = new JLabel("X");
		exitLabelBtnT.setForeground(new Color(255, 255, 255));
		exitLabelBtnT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exitBtnT.setBackground(Color.RED);
				exitLabelBtnT.setForeground(Color.BLACK);
				exitBtnT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitBtnT.setBackground(new Color(50,50,50));
				exitLabelBtnT.setForeground(Color.WHITE);
			}
		});
		exitLabelBtnT.setFont(new Font("Roboto Black", Font.BOLD, 20));
		exitLabelBtnT.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabelBtnT.setBounds(0, 0, 50, 38);
		exitBtnT.add(exitLabelBtnT);
		
		//Boton que regresa al menu principal
		final JPanel backBttnT = new JPanel();
		backBttnT.setLayout(null);
		backBttnT.setBackground(new Color(50, 50, 50));
		backBttnT.setBounds(0, 0, 50, 38);
		headerTemperature.add(backBttnT);
		
		JLabel backLblT = new JLabel("");
		backLblT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backBttnT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				backBttnT.setBackground(new Color(70,70,70));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backBttnT.setBackground(new Color(50,50,50));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contenedor, "panel1");
				inputTxtT.setText("");
				outputTxtT.setText("");
			}
		});
		
		backLblT.setIconTextGap(3);
		backLblT.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/left-arrow1.png")));
		backLblT.setHorizontalAlignment(SwingConstants.CENTER);
		backLblT.setForeground(Color.WHITE);
		backLblT.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 22));
		backLblT.setBounds(0, 0, 50, 38);
		backBttnT.add(backLblT);
		
		JLabel subTitleTemperature = new JLabel("Temperature converter");
		subTitleTemperature.setFont(new Font("Roboto Black", Font.PLAIN, 35));
		subTitleTemperature.setForeground(new Color(255, 255, 255));
		subTitleTemperature.setBounds(113, 61, 374, 49);
		temperaturePanelScreen.add(subTitleTemperature);
		
		final JComboBox<String> inputComboBoxT = new JComboBox<String>();
		inputComboBoxT.setFont(new Font("Roboto", Font.PLAIN, 12));
		inputComboBoxT.setBorder(null);
		inputComboBoxT.setBackground(new Color(255, 255, 255));
		inputComboBoxT.setBounds(50, 150, 205, 50);
		
		Hashtable<Object, ImageIcon> elementsListT;
		elementsListT = new Hashtable<>();
		
		inputComboBoxT.addItem(" Celsius Degrees");
		inputComboBoxT.addItem(" Farenheit Degrees");
		inputComboBoxT.addItem(" Kelvin Degrees");

		
		elementsListT.put(" Celsius Degrees", getIcono("/com/mx/icons/celsius.png"));
		elementsListT.put(" Farenheit Degrees", getIcono("/com/mx/icons/farenheit.png"));
		elementsListT.put(" Kelvin Degrees", getIcono("/com/mx/icons/kelvin.png"));

		
		JComboBoxRenderer comboRenderT = new JComboBoxRenderer(elementsListT);
		inputComboBoxT.setRenderer(comboRenderT);
		
		temperaturePanelScreen.add(inputComboBoxT);
		
		inputTxtT = new JTextField();
		inputTxtT.setFont(new Font("Roboto", Font.PLAIN, 16));
		inputTxtT.setHorizontalAlignment(SwingConstants.CENTER);
		inputTxtT.setBorder(null);
		inputTxtT.setBounds(50, 210, 205, 50);
		inputTxtT.setColumns(10);
		temperaturePanelScreen.add(inputTxtT);
		
		
		final JComboBox<String> outputComboBoxT = new JComboBox<String>();
		outputComboBoxT.setFont(new Font("Roboto", Font.PLAIN, 12));
		outputComboBoxT.setBorder(null);
		outputComboBoxT.setBackground(Color.WHITE);
		outputComboBoxT.setBounds(345, 150, 205, 50);
		
		Hashtable<Object, ImageIcon> elementsListOT;
		elementsListOT = new Hashtable<>();
		
		outputComboBoxT.addItem(" Farenheit Degrees");
		outputComboBoxT.addItem(" Celsius Degrees");
		outputComboBoxT.addItem(" Kelvin Degrees");

		
		elementsListOT.put(" Farenheit Degrees", getIcono("/com/mx/icons/farenheit.png"));
		elementsListOT.put(" Celsius Degrees", getIcono("/com/mx/icons/celsius.png"));
		elementsListOT.put(" Kelvin Degrees", getIcono("/com/mx/icons/kelvin.png"));

		
		JComboBoxRenderer comboRenderOT = new JComboBoxRenderer(elementsListOT);
		outputComboBoxT.setRenderer(comboRenderOT);
		
		temperaturePanelScreen.add(outputComboBoxT);
		
		
		
		outputTxtT = new JTextField();
		outputTxtT.setEditable(false);
		outputTxtT.setDisabledTextColor(new Color(255, 255, 255));
		outputTxtT.setFont(new Font("Roboto", Font.PLAIN, 16));
		outputTxtT.setHorizontalAlignment(SwingConstants.CENTER);
		outputTxtT.setBorder(null);
		outputTxtT.setColumns(10);
		outputTxtT.setBounds(345, 210, 205, 50);
		temperaturePanelScreen.add(outputTxtT);
		
		final JPanel convertBttnT = new JPanel();
		convertBttnT.setBackground(new Color(39, 158, 255));
		convertBttnT.setBounds(222, 315, 155, 58);
		temperaturePanelScreen.add(convertBttnT);
		convertBttnT.setLayout(null);
		
		final JLabel convertLblBttnT = new JLabel("Convert");
		convertLblBttnT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				convertBttnT.setBackground(new Color(9,138,235));
				convertLblBttnT.setForeground(Color.BLACK);
				convertBttnT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				convertBttnT.setBackground(new Color(39,158,255));
				convertLblBttnT.setForeground(new Color(50,50,50));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
					
					String txt = conversion.toConvertTemp((String)inputComboBoxT.getSelectedItem(), (String)outputComboBoxT.getSelectedItem(), 
							(Double)Double.parseDouble(inputTxtT.getText()));
					outputTxtT.setText(txt);
				
			}
		});
		convertLblBttnT.setHorizontalAlignment(SwingConstants.CENTER);
		convertLblBttnT.setFont(new Font("Roboto Medium", Font.PLAIN, 23));
		convertLblBttnT.setBackground(Color.WHITE);
		convertLblBttnT.setBounds(0, 0, 155, 58);
		convertBttnT.add(convertLblBttnT);
		
		final JPanel swapBttnT = new JPanel();
		swapBttnT.setBackground(new Color(40, 40, 40));
		swapBttnT.setForeground(new Color(0, 0, 0));
		swapBttnT.setBounds(278, 181, 43, 37);
		temperaturePanelScreen.add(swapBttnT);
		swapBttnT.setLayout(null);
		
		JLabel swapLblT = new JLabel("");
		swapLblT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				swapBttnT.setBackground(new Color(70,70,70));
				swapBttnT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				swapBttnT.setBackground(new Color(40,40,40));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
                String selectedValueCombo1 = (String) inputComboBoxT.getSelectedItem();
                String selectedValueCombo2 = (String) outputComboBoxT.getSelectedItem();

                inputComboBoxT.removeItem(selectedValueCombo1);
                outputComboBoxT.removeItem(selectedValueCombo2);

                inputComboBoxT.addItem(selectedValueCombo2);
                outputComboBoxT.addItem(selectedValueCombo1);

                inputComboBoxT.setSelectedItem(selectedValueCombo2);
                outputComboBoxT.setSelectedItem(selectedValueCombo1);
				
			}
		});
		swapLblT.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/exchange (2).png")));
		swapLblT.setHorizontalAlignment(SwingConstants.CENTER);
		swapLblT.setBounds(0, 0, 44, 36);
		swapBttnT.add(swapLblT);
		
		
		//Inicia tercer pantalla secundaria, muestra la interfaz para la conversion de masa
		JPanel massPanelScreen = new JPanel();
		massPanelScreen.setBackground(new Color(50, 50, 50));
		contenedor.add(massPanelScreen, "panel4");
		massPanelScreen.setLayout(null);
		
		//Cabecera que contiene el boton de cierre de la app y boton de retroceso para regresar al menu principal
		JPanel headerMass = new JPanel();
		//Capturar la posicion del mouse cuando se hace click en la barra del header
		headerMass.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		//Recolocaremos la ventana con respecto a la posicion del mouse en la pantalla del usuario
		headerMass.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
						
				setLocation(x - xMouse, y - yMouse);
			}
		});
		headerMass.setBackground(new Color(50, 50, 50));
		headerMass.setBounds(0, 0, 600, 38);
		massPanelScreen.add(headerMass);
		headerMass.setLayout(null);
		
		final JPanel exitBtnM = new JPanel();
		exitBtnM.setBackground(new Color(50, 50, 50));
		exitBtnM.setBounds(550, 0, 50, 38);
		headerMass.add(exitBtnM);
		exitBtnM.setLayout(null);
		
		final JLabel exitLabelBtnM = new JLabel("X");
		exitLabelBtnM.setForeground(new Color(255, 255, 255));
		exitLabelBtnM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exitBtnM.setBackground(Color.RED);
				exitLabelBtnM.setForeground(Color.BLACK);
				exitBtnM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitBtnM.setBackground(new Color(50,50,50));
				exitLabelBtnM.setForeground(Color.WHITE);
			}
		});
		exitLabelBtnM.setFont(new Font("Roboto Black", Font.BOLD, 20));
		exitLabelBtnM.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabelBtnM.setBounds(0, 0, 50, 38);
		exitBtnM.add(exitLabelBtnM);
		
		//Boton que regresa al menu principal
		final JPanel backBttnM = new JPanel();
		backBttnM.setLayout(null);
		backBttnM.setBackground(new Color(50, 50, 50));
		backBttnM.setBounds(0, 0, 50, 38);
		headerMass.add(backBttnM);
		
		JLabel backLblM = new JLabel("");
		backLblM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backBttnM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				backBttnM.setBackground(new Color(70,70,70));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backBttnM.setBackground(new Color(50,50,50));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contenedor, "panel1");
				
				inputTxtM.setText("");
				outputTxtM.setText("");
			}
		});
		
		backLblM.setIconTextGap(3);
		backLblM.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/left-arrow1.png")));
		backLblM.setHorizontalAlignment(SwingConstants.CENTER);
		backLblM.setForeground(Color.WHITE);
		backLblM.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 22));
		backLblM.setBounds(0, 0, 50, 38);
		backBttnM.add(backLblM);
		
		JLabel subTitleMass = new JLabel("Mass converter");
		subTitleMass.setFont(new Font("Roboto Black", Font.PLAIN, 35));
		subTitleMass.setForeground(new Color(255, 255, 255));
		subTitleMass.setBounds(176, 61, 247, 49);
		massPanelScreen.add(subTitleMass);
		
		final JComboBox<String> inputComboBoxM = new JComboBox<String>();
		inputComboBoxM.setModel(new DefaultComboBoxModel<String>(new String[] {" Gram (g)", " Kilogram (Kg)", " Ton (T)", " Pound (lb)", " Ounce (oz)"}));
		inputComboBoxM.setFont(new Font("Roboto", Font.PLAIN, 14));
		inputComboBoxM.setBorder(null);
		inputComboBoxM.setBackground(new Color(255, 255, 255));
		inputComboBoxM.setBounds(50, 150, 205, 50);
		massPanelScreen.add(inputComboBoxM);
		
		inputTxtM = new JTextField();
		inputTxtM.setFont(new Font("Roboto", Font.PLAIN, 16));
		inputTxtM.setHorizontalAlignment(SwingConstants.CENTER);
		inputTxtM.setBorder(null);
		inputTxtM.setBounds(50, 210, 205, 50);
		massPanelScreen.add(inputTxtM);
		inputTxtM.setColumns(10);
		
		
		
		final JComboBox<String> outputComboBoxM = new JComboBox<String>();
		outputComboBoxM.setModel(new DefaultComboBoxModel<String>(new String[] {" Kilogram (Kg)", " Gram (g)", " Ton (T)", " Pound (lb)", " Ounce (oz)"}));
		outputComboBoxM.setFont(new Font("Roboto", Font.PLAIN, 14));
		outputComboBoxM.setBorder(null);
		outputComboBoxM.setBackground(Color.WHITE);
		outputComboBoxM.setBounds(345, 150, 205, 50);
		
		massPanelScreen.add(outputComboBoxM);
		
		
		
		outputTxtM = new JTextField();
		outputTxtM.setDisabledTextColor(new Color(255, 255, 255));
		outputTxtM.setEditable(false);
		outputTxtM.setFont(new Font("Roboto", Font.PLAIN, 16));
		outputTxtM.setHorizontalAlignment(SwingConstants.CENTER);
		outputTxtM.setBorder(null);
		outputTxtM.setColumns(10);
		outputTxtM.setBounds(345, 210, 205, 50);
		massPanelScreen.add(outputTxtM);
		
		final JPanel convertBttnM = new JPanel();
		convertBttnM.setBackground(new Color(242, 190, 34));
		convertBttnM.setBounds(222, 315, 155, 58);
		massPanelScreen.add(convertBttnM);
		convertBttnM.setLayout(null);
		
		final JLabel convertLblBttnM = new JLabel("Convert");
		convertLblBttnM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				convertBttnM.setBackground(new Color(212,160,4));
				convertLblBttnM.setForeground(Color.BLACK);
				convertBttnM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				convertBttnM.setBackground(new Color(242,190,34));
				convertLblBttnM.setForeground(new Color(50,50,50));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				String txt = conversion.toConvertMass((String)inputComboBoxM.getSelectedItem(), (String)outputComboBoxM.getSelectedItem(), 
						(Double)Double.parseDouble(inputTxtM.getText()));
				outputTxtM.setText(txt);
				
			}
		});
		convertLblBttnM.setHorizontalAlignment(SwingConstants.CENTER);
		convertLblBttnM.setFont(new Font("Roboto Medium", Font.PLAIN, 23));
		convertLblBttnM.setBackground(Color.WHITE);
		convertLblBttnM.setBounds(0, 0, 155, 58);
		convertBttnM.add(convertLblBttnM);
		
		final JPanel swapBttnM = new JPanel();
		swapBttnM.setBackground(new Color(40, 40, 40));
		swapBttnM.setForeground(new Color(0, 0, 0));
		swapBttnM.setBounds(278, 181, 43, 37);
		massPanelScreen.add(swapBttnM);
		swapBttnM.setLayout(null);
		
		JLabel swapLblM = new JLabel("");
		swapLblM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				swapBttnM.setBackground(new Color(70,70,70));
				swapBttnM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				swapBttnM.setBackground(new Color(40,40,40));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
                String selectedValueCombo1 = (String) inputComboBoxM.getSelectedItem();
                String selectedValueCombo2 = (String) outputComboBoxM.getSelectedItem();

                inputComboBoxM.removeItem(selectedValueCombo1);
                outputComboBoxM.removeItem(selectedValueCombo2);

                inputComboBoxM.addItem(selectedValueCombo2);
                outputComboBoxM.addItem(selectedValueCombo1);

                inputComboBoxM.setSelectedItem(selectedValueCombo2);
                outputComboBoxM.setSelectedItem(selectedValueCombo1);
				
			}
		});
		swapLblM.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/exchange (2).png")));
		swapLblM.setHorizontalAlignment(SwingConstants.CENTER);
		swapLblM.setBounds(0, 0, 44, 36);
		swapBttnM.add(swapLblM);
		
		
		
		//Inicia cuarta pantalla secundaria, muestra la interfaz para la conversion de distancia
		JPanel distancePanelScreen = new JPanel();
		distancePanelScreen.setBackground(new Color(50, 50, 50));
		contenedor.add(distancePanelScreen, "panel5");
		distancePanelScreen.setLayout(null);
		
		//Cabecera que contiene el boton de cierre de la app y boton de retroceso para regresar al menu principal
		JPanel headerDistance = new JPanel();
		//Capturar la posicion del mouse cuando se hace click en la barra del header
		headerDistance.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		//Recolocaremos la ventana con respecto a la posicion del mouse en la pantalla del usuario
		headerDistance.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
						
				setLocation(x - xMouse, y - yMouse);
			}
		});
		headerDistance.setBackground(new Color(50, 50, 50));
		headerDistance.setBounds(0, 0, 600, 38);
		distancePanelScreen.add(headerDistance);
		headerDistance.setLayout(null);
		
		final JPanel exitBtnD = new JPanel();
		exitBtnD.setBackground(new Color(50, 50, 50));
		exitBtnD.setBounds(550, 0, 50, 38);
		headerDistance.add(exitBtnD);
		exitBtnD.setLayout(null);
		
		final JLabel exitLabelBtnD = new JLabel("X");
		exitLabelBtnD.setForeground(new Color(255, 255, 255));
		exitLabelBtnD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exitBtnD.setBackground(Color.RED);
				exitLabelBtnD.setForeground(Color.BLACK);
				exitBtnD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitBtnD.setBackground(new Color(50,50,50));
				exitLabelBtnD.setForeground(Color.WHITE);
			}
		});
		exitLabelBtnD.setFont(new Font("Roboto Black", Font.BOLD, 20));
		exitLabelBtnD.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabelBtnD.setBounds(0, 0, 50, 38);
		exitBtnD.add(exitLabelBtnD);
		
		//Boton que regresa al menu principal
		final JPanel backBttnD = new JPanel();
		backBttnD.setLayout(null);
		backBttnD.setBackground(new Color(50, 50, 50));
		backBttnD.setBounds(0, 0, 50, 38);
		headerDistance.add(backBttnD);
		
		JLabel backLblD = new JLabel("");
		backLblD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backBttnD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				backBttnD.setBackground(new Color(70,70,70));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backBttnD.setBackground(new Color(50,50,50));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contenedor, "panel1");
				
				inputTxtD.setText("");
				outputTxtD.setText("");
			}
		});
		
		backLblD.setIconTextGap(3);
		backLblD.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/left-arrow1.png")));
		backLblD.setHorizontalAlignment(SwingConstants.CENTER);
		backLblD.setForeground(Color.WHITE);
		backLblD.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 22));
		backLblD.setBounds(0, 0, 50, 38);
		backBttnD.add(backLblD);
		
		JLabel subTitleDistance = new JLabel("Distance converter");
		subTitleDistance.setFont(new Font("Roboto Black", Font.PLAIN, 35));
		subTitleDistance.setForeground(new Color(255, 255, 255));
		subTitleDistance.setBounds(147, 61, 305, 49);
		distancePanelScreen.add(subTitleDistance);
		
		final JComboBox<String> inputComboBoxD = new JComboBox<String>();
		inputComboBoxD.setMaximumRowCount(6);
		inputComboBoxD.setModel(new DefaultComboBoxModel<String>(new String[] {" Centimeter (cm)", " Meter (m)", " Kilometer (Km)", " Inch (in)", " Foot (ft)", " Mile (M)"}));
		inputComboBoxD.setFont(new Font("Roboto", Font.PLAIN, 14));
		inputComboBoxD.setBorder(null);
		inputComboBoxD.setBackground(new Color(255, 255, 255));
		inputComboBoxD.setBounds(50, 150, 205, 50);
		distancePanelScreen.add(inputComboBoxD);
		
		inputTxtD = new JTextField();
		inputTxtD.setFont(new Font("Roboto", Font.PLAIN, 16));
		inputTxtD.setHorizontalAlignment(SwingConstants.CENTER);
		inputTxtD.setBorder(null);
		inputTxtD.setBounds(50, 210, 205, 50);
		distancePanelScreen.add(inputTxtD);
		inputTxtD.setColumns(10);
		
		
		
		final JComboBox<String> outputComboBoxD = new JComboBox<String>();
		outputComboBoxD.setMaximumRowCount(6);
		outputComboBoxD.setModel(new DefaultComboBoxModel<String>(new String[] {" Meter (m)", " Kilometer (Km)", " Inch (in)", " Foot (ft)", " Mile (M)", " Centimeter (cm)"}));
		outputComboBoxD.setFont(new Font("Roboto", Font.PLAIN, 14));
		outputComboBoxD.setBorder(null);
		outputComboBoxD.setBackground(Color.WHITE);
		outputComboBoxD.setBounds(345, 150, 205, 50);
		
		distancePanelScreen.add(outputComboBoxD);
		
		
		
		outputTxtD = new JTextField();
		outputTxtD.setDisabledTextColor(new Color(255, 255, 255));
		outputTxtD.setEditable(false);
		outputTxtD.setFont(new Font("Roboto", Font.PLAIN, 16));
		outputTxtD.setHorizontalAlignment(SwingConstants.CENTER);
		outputTxtD.setBorder(null);
		outputTxtD.setColumns(10);
		outputTxtD.setBounds(345, 210, 205, 50);
		distancePanelScreen.add(outputTxtD);
		
		final JPanel convertBttnD = new JPanel();
		convertBttnD.setBackground(new Color(207, 48, 48));
		convertBttnD.setBounds(222, 315, 155, 58);
		distancePanelScreen.add(convertBttnD);
		convertBttnD.setLayout(null);
		
		final JLabel convertLblBttnD = new JLabel("Convert");
		convertLblBttnD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				convertBttnD.setBackground(new Color(177,18,18));
				convertLblBttnD.setForeground(Color.BLACK);
				convertBttnD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				convertBttnD.setBackground(new Color(207,48,48));
				convertLblBttnD.setForeground(new Color(50,50,50));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				String txt = conversion.toConvertDistance((String)inputComboBoxD.getSelectedItem(), (String)outputComboBoxD.getSelectedItem(), 
						(Double)Double.parseDouble(inputTxtD.getText()));
				outputTxtD.setText(txt);
				
			}
		});
		convertLblBttnD.setHorizontalAlignment(SwingConstants.CENTER);
		convertLblBttnD.setFont(new Font("Roboto Medium", Font.PLAIN, 23));
		convertLblBttnD.setBackground(new Color(207, 48, 48));
		convertLblBttnD.setBounds(0, 0, 155, 58);
		convertBttnD.add(convertLblBttnD);
		
		final JPanel swapBttnD = new JPanel();
		swapBttnD.setBackground(new Color(40, 40, 40));
		swapBttnD.setForeground(new Color(0, 0, 0));
		swapBttnD.setBounds(278, 181, 43, 37);
		distancePanelScreen.add(swapBttnD);
		swapBttnD.setLayout(null);
		
		JLabel swapLblD = new JLabel("");
		swapLblD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				swapBttnD.setBackground(new Color(70,70,70));
				swapBttnD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				swapBttnD.setBackground(new Color(40,40,40));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
                String selectedValueCombo1 = (String) inputComboBoxD.getSelectedItem();
                String selectedValueCombo2 = (String) outputComboBoxD.getSelectedItem();

                inputComboBoxD.removeItem(selectedValueCombo1);
                outputComboBoxD.removeItem(selectedValueCombo2);

                inputComboBoxD.addItem(selectedValueCombo2);
                outputComboBoxD.addItem(selectedValueCombo1);

                inputComboBoxD.setSelectedItem(selectedValueCombo2);
                outputComboBoxD.setSelectedItem(selectedValueCombo1);
				
			}
		});
		swapLblD.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/exchange (2).png")));
		swapLblD.setHorizontalAlignment(SwingConstants.CENTER);
		swapLblD.setBounds(0, 0, 44, 36);
		swapBttnD.add(swapLblD);
		
		JPanel aboutScreen = new JPanel();
		aboutScreen.setBackground(new Color(70, 70, 70));
		contenedor.add(aboutScreen, "panel6");
		aboutScreen.setLayout(null);
		
		JPanel headerAbout = new JPanel();
		//Capturar la posicion del mouse cuando se hace click en la barra del header
		headerAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		//Recolocaremos la ventana con respecto a la posicion del mouse en la pantalla del usuario
		headerAbout.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
						
				setLocation(x - xMouse, y - yMouse);
			}
		});
		headerAbout.setLayout(null);
		headerAbout.setBackground(new Color(50, 50, 50));
		headerAbout.setBounds(0, 0, 600, 38);
		aboutScreen.add(headerAbout);
		
		final JPanel exitBtnAb = new JPanel();
		exitBtnAb.setLayout(null);
		exitBtnAb.setBackground(new Color(50, 50, 50));
		exitBtnAb.setBounds(550, 0, 50, 38);
		headerAbout.add(exitBtnAb);
		
		final JLabel exitLabelAb = new JLabel("X");
		exitLabelAb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exitBtnAb.setBackground(Color.RED);
				exitLabelAb.setForeground(Color.BLACK);
				exitBtnAb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitBtnAb.setBackground(new Color(50,50,50));
				exitLabelAb.setForeground(Color.WHITE);
			}
		});
		exitLabelAb.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabelAb.setForeground(Color.WHITE);
		exitLabelAb.setFont(new Font("Roboto Black", Font.BOLD, 20));
		exitLabelAb.setBounds(0, 0, 50, 38);
		exitBtnAb.add(exitLabelAb);
		
		final JPanel backBttnAb = new JPanel();
		backBttnAb.setLayout(null);
		backBttnAb.setBackground(new Color(50, 50, 50));
		backBttnAb.setBounds(0, 0, 50, 38);
		headerAbout.add(backBttnAb);
		
		JLabel backLblAb = new JLabel("");
		backLblAb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backBttnAb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				backBttnAb.setBackground(new Color(70,70,70));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backBttnAb.setBackground(new Color(50,50,50));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(contenedor, "panel1");
				
				inputTxtD.setText("");
				outputTxtD.setText("");
			}
		});
		backLblAb.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/left-arrow1.png")));
		backLblAb.setIconTextGap(3);
		backLblAb.setHorizontalAlignment(SwingConstants.CENTER);
		backLblAb.setForeground(Color.WHITE);
		backLblAb.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 22));
		backLblAb.setBounds(0, 0, 50, 38);
		backBttnAb.add(backLblAb);
		
		JLabel creditLbl = new JLabel(" Proyecto realizado por:");
		creditLbl.setForeground(new Color(255, 255, 255));
		creditLbl.setBackground(new Color(255, 255, 255));
		creditLbl.setFont(new Font("Arial", Font.BOLD, 16));
		creditLbl.setBounds(51, 106, 211, 31);
		aboutScreen.add(creditLbl);
		
		JLabel devLbl = new JLabel("Rosas Ruiz Jose Israel");
		devLbl.setForeground(new Color(200, 200, 200));
		devLbl.setFont(new Font("Arial", Font.BOLD, 24));
		devLbl.setBounds(166, 132, 270, 38);
		aboutScreen.add(devLbl);
		
		JLabel messageLbl = new JLabel("¡ Contáctame y contrátame !");
		messageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		messageLbl.setForeground(new Color(255, 255, 255));
		messageLbl.setFont(new Font("Arial", Font.BOLD, 24));
		messageLbl.setBackground(Color.WHITE);
		messageLbl.setBounds(137, 228, 325, 31);
		aboutScreen.add(messageLbl);
		
		final JPanel linkedInPannel = new JPanel();
		linkedInPannel.setBackground(new Color(70, 70, 70));
		linkedInPannel.setBounds(198, 283, 64, 64);
		aboutScreen.add(linkedInPannel);
		linkedInPannel.setLayout(null);
		
		final JLabel linkedInIconLbl = new JLabel("");
		linkedInIconLbl.setBounds(0, 0, 64, 64);
		linkedInPannel.add(linkedInIconLbl);
		linkedInIconLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				linkedInIconLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				linkedInPannel.setBackground(new Color(50,50,50));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				linkedInPannel.setBackground(new Color(70,70,70));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				openWebPage("https://www.linkedin.com/in/jos%C3%A9-israel-rosas-ruiz/");
				
			}
		});
		linkedInIconLbl.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/linkedInIcon.png")));
		linkedInIconLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		final RoundPanel githubPannel = new RoundPanel();
		githubPannel.setBackground(new Color(70, 70, 70));
		githubPannel.setBounds(326, 277, 72, 72);
		aboutScreen.add(githubPannel);
		githubPannel.setLayout(null);
		
		final JLabel githubIconLbl = new JLabel("");
		githubIconLbl.setBounds(0, 0, 72, 72);
		githubPannel.add(githubIconLbl);
		githubIconLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				githubIconLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				githubPannel.setBackground(new Color(50,50,50));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				githubPannel.setBackground(new Color(70,70,70));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				openWebPage("https://github.com/JoseRosasRz");
				
			}
		});
		githubIconLbl.setIcon(new ImageIcon(MainScreen.class.getResource("/com/mx/icons/githubIcon.png")));
		githubIconLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	
	public ImageIcon getIcono(String path) {
		return 	new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage()
				.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
	}
	
    public static void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}
