package game.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class Controller implements ActionListener {

	private Game model;
	private View view;

	private int firstCounter = 0;
	private int secondCounter = 0;
	private int leaderCounter1 = 0;
	private int leaderCounter2 = 0;

	private ArrayList<JButton> buttons1 = new ArrayList<JButton>();
	private ArrayList<JButton> buttons2 = new ArrayList<JButton>();
	private ArrayList<Champion> Champions;
	private ArrayList<JButton> Team1 = new ArrayList<JButton>();
	private ArrayList<JButton> Team2 = new ArrayList<JButton>();
	private ArrayList<Champion> p1team = new ArrayList<Champion>();
	private ArrayList<Champion> p2team = new ArrayList<Champion>();

	private Border emptyBorder = BorderFactory.createEmptyBorder();
	private Border lineBorder;
	private TitledBorder titleEmptyBorder;
	private File font_file = new File("MarvelRegular-Dj83.otf");
	private Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
	
	private File font_filee = new File("marvelFont.ttf");
	private Font marvelFont = Font.createFont(Font.TRUETYPE_FONT, font_filee);
	
	private JButton ability1;
	private JButton ability2;
	private JButton ability3;
	private JButton left;
	private JButton right;
	private JButton up;
	private JButton down;


	private JPanel d;

	private Color player1Color = new Color(217, 184, 67);
	private Color player2Color = new Color(158, 156, 150);

	private ImageIcon icon;

	private JButton[][] grid = new JButton[5][5];
	private JButton ch1 = new JButton();
	private JButton ch2 = new JButton();
	private JButton start = new JButton("START");
	private JButton move = new JButton("MOVE");
	private JButton attack = new JButton("ATTACK");
	private JButton castAbility = new JButton("CAST ABILITY");
	private JButton useLeaderAbility = new JButton("LEAD ABILITY");
	private JButton endTurn = new JButton("END TURN");

	private JLabel name1;
	private JLabel name2;
	private JLabel choose1;
	private JLabel choose2;
	private JLabel chooseLeader1;
	private JLabel chooseLeader2;
	private JLabel name = new JLabel();

	private ImagePanel ChoosePanel;
	private ImagePanel gameBack;

	private JPanel g = new JPanel(new GridLayout(5, 5));;
	private JPanel f = new JPanel();
	private JPanel s = new JPanel();
	private JPanel abs;

	private JPanel availableChampions1;
	private JPanel p1Team;

	private JPanel availableChampions2;
	private JPanel p2Team;

	private JPanel pq;

	public Controller() throws IOException, FontFormatException {
		view = new View();
		Player p1 = new Player(view.getGetName1());
		Player p2 = new Player(view.getGetName2());
		model = new Game(p1, p2);
		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");

		font = font.deriveFont(Font.CENTER_BASELINE, 30);

		UIManager.put("ToolTip.background", Color.BLACK);
		UIManager.put("ToolTip.foreground", Color.WHITE);
		UIManager.put("ToolTip.border", Color.BLACK);
		UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 14));

		View.getSingle().addActionListener(this);
		View.getMultip().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == View.getPlay()) {
//			view.getGame().getContentPane().removeAll();
//			view.getBackGround().removeAll();
//			
//			single.setBounds(255, 540, 200, 40);
//			single.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
//			
//			multip.setBounds(255, 590, 200, 40);
//			multip.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
//			
//			single.addActionListener(this);
//			multip.addActionListener(this);
//			
//			view.getBackGround().add(single);
//			view.getBackGround().add(multip);
//			view.getGame().getContentPane().add(view.getBackGround(), BorderLayout.CENTER);
//			
//			view.getGame().revalidate();
//			view.getGame().repaint();
//		}
//		
//		if (e.getSource() == single) {
//			String getName = JOptionPane.showInputDialog(view.getGame(), "Enter Your Name");
//			view.getGame().getContentPane().removeAll();
//			view.getBackGround().removeAll();
//			view.getGame().setLayout(new BorderLayout());
//			
//			firstPlayer = new JPanel();
//			firstPlayer.setLayout(null);
//			firstPlayer.setPreferredSize(new Dimension(view.getWidth(), view.getHeight()/2));
//			
//			name1 = new JLabel();
//			name1.setText(getName);
//			name1.setBounds(10,10,150,30);
//			name1.setHorizontalAlignment(JTextField.CENTER);
//			
//			choose1 = new JLabel();
//			choose1.setText("Choose 3 Champions");
//			choose1.setBounds(260,25, 200 ,50);
//		
//			availableChampions1 = new JPanel();
//			p1Team = new JPanel();
//			
//			availableChampions1.setBounds(50,70,view.getGame().getWidth()-100,200);
//			availableChampions1.setLayout(new GridLayout(0,5));
//			
//			p1Team.setBounds(150, 290, 400, 90);
//			p1Team.setLayout(new GridLayout(1,3));
//		
//			
//			firstPlayer.add(name1);
//			firstPlayer.add(choose1);
//			firstPlayer.add(p1Team);
//			firstPlayer.add(availableChampions1);
//			
//			Champions = Game.getAvailableChampions();
//			
//			
//			for (int i = 0; i < Champions.size(); i++) {
//				ch1 = new JButton();
//				
//				ch1.setToolTipText(Champions.get(i).toNDString());
//				buttons1.add(ch1);
//				ch1.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						JButton b = (JButton)e.getSource();
//						int b1 = buttons1.indexOf(b);
//						
//						
//						buttons1.get(b1).setEnabled(false);
//						buttons2.get(b1).setEnabled(false);
//						
//						firstCounter += 1;	
//						if (firstCounter == 3) {
//							for(int i = 0; i <buttons1.size(); i++) {
//								buttons1.get(i).setEnabled(false);
//							}
//						}
//						
//					}
//					
//				});
//				
//				icon = name(Game.getAvailableChampions().get(i).getName());
//				Image img = icon.getImage() ;  
//				Image newimg = img.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH ) ;  
//				icon = new ImageIcon( newimg );
//				ch1.setIcon(icon);
//				availableChampions1.add(ch1);
//			}
//			
//			availableChampions1.revalidate();
//			availableChampions1.repaint();
//			
//		
//			
//			view.getGame().getContentPane().add(firstPlayer, BorderLayout.CENTER);
//			
//			
//			view.getGame().revalidate();
//			view.getGame().repaint();
//		}

		if (e.getSource() == View.getMultip()) {

			view.getGame().getContentPane().removeAll();
			view.getBackGround().removeAll();

			view.getGame().setLayout(new BorderLayout());

			name1 = new JLabel(view.getGetName1().toUpperCase());
			name1.setForeground(Color.WHITE);
			name1.setFont(font);
			name2 = new JLabel(view.getGetName2().toUpperCase());
			name2.setForeground(Color.WHITE);
			name2.setFont(font);

			name1.setHorizontalAlignment(JTextField.CENTER);
			name2.setHorizontalAlignment(JTextField.CENTER);

			name1.setBounds((view.getWidth() / 2) / 2 - 75, 5, 150, 50);
			name2.setBounds(885, 5, 150, 50);

			choose1 = new JLabel();
			choose1.setOpaque(false);
			choose1.setText("CHOOSE 3 CHAMPIONS");
			choose1.setForeground(Color.WHITE);
			choose1.setFont(font);
			choose1.setBounds(170, 50, 300, 50);

			choose2 = new JLabel();
			choose2.setText("CHOOSE 3 CHAMPIONS");
			choose2.setForeground(Color.WHITE);
			choose2.setFont(font);
			choose2.setBounds(810, 50, 300, 50);

			ChoosePanel = new ImagePanel("back.jpg");

			ChoosePanel.setLayout(null);

			ChoosePanel.setPreferredSize(new Dimension(view.getWidth(), view.getHeight()));

			availableChampions1 = new JPanel();
			availableChampions1.setOpaque(false);
			p1Team = new JPanel();
			p1Team.setOpaque(false);

			availableChampions2 = new JPanel();
			availableChampions2.setOpaque(false);
			p2Team = new JPanel();
			p2Team.setOpaque(false);

			availableChampions1.setBounds(120, 100, 400, 450);
			availableChampions1.setLayout(new GridLayout(0, 3));

			availableChampions2.setBounds(view.getWidth() / 2 + 120, 100, 400, 450);
			availableChampions2.setLayout(new GridLayout(0, 3));

			p1Team.setBounds(view.getWidth() / 2 / 2 - 200, 600, 400, 100);
			p1Team.setLayout(new GridLayout(0, 3));

			p2Team.setBounds(760, 600, 400, 100);
			p2Team.setLayout(new GridLayout(0, 3));

			ChoosePanel.add(name1);
			ChoosePanel.add(choose1);
			ChoosePanel.add(p1Team);
			ChoosePanel.add(availableChampions1);

			ChoosePanel.add(name2);
			ChoosePanel.add(choose2);
			ChoosePanel.add(p2Team);
			ChoosePanel.add(availableChampions2);

			Champions = Game.getAvailableChampions();

			start.setBounds(view.getWidth() / 2 - 45, view.getHeight() - 100, 90, 30);
			ChoosePanel.add(start);
			start.setBackground(Color.BLACK);
			start.setForeground(Color.WHITE);
			start.setFocusable(false);
			start.setBorder(emptyBorder);
			start.addActionListener(this);
			start.setOpaque(false);
			start.setContentAreaFilled(false);
			start.setBorderPainted(false);
			start.setFont(font);

			for (int i = 0; i < Champions.size(); i++) {
				ch1 = new JButton();
				ch1.setIcon(name(Game.getAvailableChampions().get(i).getName()));
				ch1.setToolTipText(Champions.get(i).toString());
				ch1.setBackground(Color.BLACK);
				ch1.setForeground(Color.WHITE);
				ch1.setBorder(emptyBorder);
				ch1.setFocusable(false);
				buttons1.add(ch1);
				ch1.setActionCommand("Chosen1");
				ch1.addActionListener(this);
				ch1.setOpaque(false);
				ch1.setContentAreaFilled(false);
				ch1.setBorderPainted(false);

				ch2 = new JButton();
				ch2.setBackground(Color.BLACK);
				ch2.setForeground(Color.WHITE);
				ch2.setBorder(emptyBorder);
				ch2.setFocusable(false);
				ch2.setIcon(name(Game.getAvailableChampions().get(i).getName()));
				ch2.setToolTipText(Champions.get(i).toString());
				buttons2.add(ch2);
				ch2.setActionCommand("Chosen2");
				ch2.addActionListener(this);
				ch2.setOpaque(false);
				ch2.setContentAreaFilled(false);
				ch2.setBorderPainted(false);

				availableChampions1.add(ch1);
				availableChampions2.add(ch2);

			}

			chooseLeader1 = new JLabel();
			chooseLeader1.setHorizontalTextPosition(JLabel.CENTER);
			chooseLeader1.setText("CHOOSE YOUR TEAM LEADER");
			chooseLeader1.setForeground(Color.WHITE);
			chooseLeader1.setFont(font);
			chooseLeader1.setBounds(140, 550, 400, 50);

			ChoosePanel.add(chooseLeader1);

			chooseLeader2 = new JLabel();
			chooseLeader2.setHorizontalTextPosition(JLabel.CENTER);
			chooseLeader2.setForeground(Color.WHITE);
			chooseLeader2.setText("CHOOSE YOUR TEAM LEADER");
			chooseLeader2.setFont(font);
			chooseLeader2.setBounds(780, 550, 400, 50);

			ChoosePanel.add(chooseLeader2);

			availableChampions1.revalidate();
			availableChampions1.repaint();

			availableChampions2.revalidate();
			availableChampions2.repaint();

			view.getGame().getContentPane().add(ChoosePanel, BorderLayout.CENTER);

			view.getGame().revalidate();
			view.getGame().repaint();
		}

		if (e.getActionCommand().equals("Chosen1")) {
			JButton b = (JButton) e.getSource();
			int b1 = buttons1.indexOf(b);
			p1team.add(Game.getAvailableChampions().get(b1));

			model.getFirstPlayer().getTeam().add(Game.getAvailableChampions().get(b1));
			buttons1.get(b1).setBorder(new LineBorder(player1Color));
			buttons1.get(b1).setEnabled(false);
			buttons2.get(b1).setEnabled(false);

			firstCounter += 1;
			if (firstCounter == 3) {
				for (int i = 0; i < buttons1.size(); i++) {
					buttons1.get(i).setEnabled(false);
				}
			}

			JButton x = new JButton();
			x.setIcon(name(Game.getAvailableChampions().get(b1).getName()));
			x.setFocusable(false);
			x.setBackground(Color.BLACK);
			x.setForeground(Color.WHITE);
			x.setBorder(emptyBorder);
			x.setOpaque(false);
			p1Team.add(x);
			Team1.add(x);
			x.setActionCommand("chosenLeader1");
			x.addActionListener(this);

			p1Team.revalidate();
			p1Team.repaint();

		}

		if (e.getActionCommand().equals("Chosen2")) {
			JButton b = (JButton) e.getSource();
			int b2 = buttons2.indexOf(b);
			p2team.add(Game.getAvailableChampions().get(b2));

			model.getSecondPlayer().getTeam().add(Game.getAvailableChampions().get(b2));

			buttons2.get(b2).setBorder(new LineBorder(player2Color));
			buttons1.get(b2).setEnabled(false);
			buttons2.get(b2).setEnabled(false);

			secondCounter += 1;
			if (secondCounter == 3) {
				for (int i = 0; i < buttons2.size(); i++) {
					buttons2.get(i).setEnabled(false);
				}
			}

			JButton x = new JButton();
			x.setIcon(name(Game.getAvailableChampions().get(b2).getName()));
			x.setFocusable(false);
			x.setBackground(Color.BLACK);
			x.setForeground(Color.WHITE);
			x.setBorder(emptyBorder);
			x.setOpaque(false);
			p2Team.add(x);
			Team2.add(x);
			x.setActionCommand("chosenLeader2");
			x.addActionListener(this);

			p2Team.revalidate();
			p2Team.repaint();
		}

		if (e.getActionCommand().equals("chosenLeader1")) {
			JButton b = (JButton) e.getSource();
			int b1 = Team1.indexOf(b);
			Team1.get(b1).setEnabled(false);
			Team1.get(b1).setBorder(new LineBorder(player1Color,5));
			model.getFirstPlayer().setLeader(p1team.get(b1));

			leaderCounter1 += 1;
			if (leaderCounter1 == 1) {
				for (int i = 0; i < Team1.size(); i++) {
					Team1.get(i).setEnabled(false);
				}
			}
		}

		if (e.getActionCommand().equals("chosenLeader2")) {
			JButton b = (JButton) e.getSource();
			int b2 = Team2.indexOf(b);

			Team2.get(b2).setEnabled(false);
			Team2.get(b2).setBorder(new LineBorder(player2Color,5));
			model.getSecondPlayer().setLeader(p2team.get(b2));

			leaderCounter2 += 1;
			if (leaderCounter2 == 1) {
				for (int i = 0; i < Team2.size(); i++) {
					Team2.get(i).setEnabled(false);
				}
			}
		}

		if (e.getSource() == start) {
			if(firstCounter!= 3 )
				JOptionPane.showMessageDialog(gameBack, view.getGetName1()+ " Please Select 3 Champions");
			else if(secondCounter!=3)
				JOptionPane.showMessageDialog(gameBack, view.getGetName2()+ " Please Select 3 Champions");
			else if(leaderCounter1!=1)
				JOptionPane.showMessageDialog(gameBack, view.getGetName1()+ " Please Select Leader");
			else if(leaderCounter2 !=1)
				JOptionPane.showMessageDialog(gameBack, view.getGetName2()+ " Please Select Leader");
			
			else {
				
			
			model.placeChampions();
			model.prepareChampionTurns();
			view.getGame().getContentPane().removeAll();
			view.getBackGround().removeAll();
			gameBack = new ImagePanel("back.jpg");
			gameBack.setLayout(null);
			view.getGame().add(gameBack);

			view.getGame().repaint();
			view.getGame().revalidate();

			font = font.deriveFont(Font.CENTER_BASELINE, 30);

			JLabel n1 = new JLabel(view.getGetName1().toUpperCase());
			n1.setFont(font);
			n1.setForeground(Color.WHITE);
			n1.setBounds(20, 40, 200, 30);
			gameBack.add(n1);

			

			JLabel n2 = new JLabel(view.getGetName2().toUpperCase());
			n2.setBounds(20, view.getHeight() / 2 + 40, 200, 30);
			n2.setFont(font);
			n2.setForeground(Color.WHITE);
			gameBack.add(n2);

			font = font.deriveFont(Font.CENTER_BASELINE, 20);
			marvelFont = marvelFont.deriveFont(Font.CENTER_BASELINE, 20);

			JLabel lau1 = new JLabel();
			if (model.isFirstLeaderAbilityUsed())
				lau1.setText("LEADER ABILITY USED");
			if (!model.isFirstLeaderAbilityUsed())
				lau1.setText("LEADER ABILITY NOT USED");
			lau1.setBounds(20, 75, 200, 30);
			lau1.setFont(marvelFont);
			lau1.setForeground(Color.WHITE);
			gameBack.add(lau1);

			createFirstPlayerPanel(model.getFirstPlayer());

			JLabel lau2 = new JLabel();
			if (model.isSecondLeaderAbilityUsed())
				lau2.setText("LEADER ABILITY USED");
			if (!model.isSecondLeaderAbilityUsed())
				lau2.setText("LEADER ABILITY NOT USED");
			lau2.setBounds(20, view.getHeight() / 2 + 75, 200, 30);
			font = font.deriveFont(Font.CENTER_BASELINE, 20);
			lau2.setFont(marvelFont);
			lau2.setForeground(Color.WHITE);
			gameBack.add(lau2);

			createSecondPlayerPanel(model.getSecondPlayer());

			Grid();

			pq = new JPanel(new GridLayout(0, model.getTurnOrder().size()));

			priorityQueueUpdate(model);

			createCurrentPanel();
			font = font.deriveFont(Font.CENTER_BASELINE, 15);
			JPanel actions = new JPanel(new GridLayout(2, 0));
			actions.setOpaque(false);

			move.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					addDirections();
					left.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							try {
								model.move(Direction.LEFT);
								playMoveSound();
								gameBack.remove(d);
								updateView();
								checkGameOver(model.checkGameOver());
							} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
								JOptionPane.showMessageDialog(gameBack, e1.getMessage());
								gameBack.remove(d);
								gameBack.repaint();
								gameBack.revalidate();
							}
						}
					});

					right.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							try {
								model.move(Direction.RIGHT);
								playMoveSound();
								gameBack.remove(d);
								updateView();
								checkGameOver(model.checkGameOver());
							} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
								JOptionPane.showMessageDialog(gameBack, e1.getMessage());
								gameBack.remove(d);
								gameBack.repaint();
								gameBack.revalidate();
							}
						}

					});
					up.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							try {
								model.move(Direction.DOWN);
								playMoveSound();
								gameBack.remove(d);
								updateView();
								checkGameOver(model.checkGameOver());
							} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
								JOptionPane.showMessageDialog(gameBack, e1.getMessage());
								gameBack.remove(d);
								gameBack.repaint();
								gameBack.revalidate();
							}
						}

					});
					down.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							try {
								model.move(Direction.UP);
								playMoveSound();
								gameBack.remove(d);
								updateView();
								checkGameOver(model.checkGameOver());
							} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
								JOptionPane.showMessageDialog(gameBack, e1.getMessage());
								gameBack.remove(d);
								gameBack.repaint();
								gameBack.revalidate();
							}
						}

					});

				}

			});
			move.setContentAreaFilled(false);
			move.setOpaque(false);
			move.setFont(marvelFont);
			move.setFocusable(false);
			move.setForeground(Color.WHITE);
			actions.add(move);

			attack.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					addDirections();
					left.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							try {
								model.attack(Direction.LEFT);
								playSound();
								gameBack.remove(d);
								updateView();
								checkGameOver(model.checkGameOver());
							} catch (NotEnoughResourcesException | ChampionDisarmedException
									| InvalidTargetException e1) {
								JOptionPane.showMessageDialog(gameBack, e1.getMessage());
								gameBack.remove(d);
								gameBack.repaint();
								gameBack.revalidate();
							}

						}

					});

					right.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							try {
								model.attack(Direction.RIGHT);
								playSound();
								gameBack.remove(d);
								updateView();
								checkGameOver(model.checkGameOver());
							} catch (NotEnoughResourcesException | ChampionDisarmedException
									| InvalidTargetException e1) {
								JOptionPane.showMessageDialog(gameBack, e1.getMessage());
								gameBack.remove(d);
								gameBack.repaint();
								gameBack.revalidate();
							}

						}

					});

					up.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							try {
								model.attack(Direction.DOWN);
								playSound();
								gameBack.remove(d);
								updateView();
								checkGameOver(model.checkGameOver());
							} catch (NotEnoughResourcesException | ChampionDisarmedException
									| InvalidTargetException e1) {
								JOptionPane.showMessageDialog(gameBack, e1.getMessage());
								gameBack.remove(d);
								gameBack.repaint();
								gameBack.revalidate();
							}

						}

					});

					down.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							try {
								model.attack(Direction.UP);
								playSound();
								gameBack.remove(d);
								updateView();
								checkGameOver(model.checkGameOver());
								view.getGame().repaint();
								view.getGame().revalidate();
							} catch (NotEnoughResourcesException | ChampionDisarmedException
									| InvalidTargetException e1) {
								JOptionPane.showMessageDialog(gameBack, e1.getMessage());
								gameBack.remove(d);
								gameBack.repaint();
								gameBack.revalidate();
							}

						}

					});
				}

			});
			attack.setContentAreaFilled(false);
			attack.setOpaque(false);
			attack.setFocusable(false);
			attack.setFont(marvelFont);
			attack.setForeground(Color.WHITE);

			actions.add(attack);
			castAbility.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					abs = new JPanel(new GridLayout(3, 0));
					abs.setOpaque(false);
					abs.setBounds(view.getWidth() - 275, view.getHeight() - 250, 250, 120);
					gameBack.add(abs);

					ability1 = new JButton();
					ability1.setText(model.getCurrentChampion().getAbilities().get(0).getName());
					ability1.setFont(font);
					ability1.setForeground(Color.WHITE);
					ability1.setOpaque(false);
					ability1.setFocusable(false);
					ability1.setContentAreaFilled(false);
					abs.add(ability1);
					ability1.setToolTipText(model.getCurrentChampion().getAbilities().get(0).toString());
			
					if (model.getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.DIRECTIONAL) {
						ability1.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								gameBack.remove(abs);
								gameBack.revalidate();
								gameBack.repaint();
								JOptionPane.showMessageDialog(gameBack, "Please Select a Direction");
								addDirections();
								left.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(0),
													Direction.LEFT);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});

								right.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(0),
													Direction.RIGHT);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});

								up.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(0),
													Direction.DOWN);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});

								down.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(0),
													Direction.UP);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});
							}

						});
					} else if (model.getCurrentChampion().getAbilities().get(0)
							.getCastArea() == AreaOfEffect.SINGLETARGET) {
						
						ability1.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {

								gameBack.remove(abs);
								gameBack.repaint();
								gameBack.revalidate();
								JOptionPane.showMessageDialog(gameBack, "Please Select a Target");
								for (int i = 0; i < Game.getBoardheight(); i++) {
									for ( int j = 0; j < Game.getBoardwidth(); j++) {
										int x = i;
										int z = j;
										grid[i][j].addActionListener(new ActionListener() {
											
											public void actionPerformed(ActionEvent e) {
												try {
													model.castAbility(model.getCurrentChampion().getAbilities().get(0),x,z);
													playSound();
													updateView();
													checkGameOver(model.checkGameOver());
												} catch (NotEnoughResourcesException | AbilityUseException
														| InvalidTargetException | CloneNotSupportedException e1) {
													JOptionPane.showMessageDialog(gameBack, e1.getMessage());
												}												
											}
											
										});
									}
								}
							}
							
						});

					}

					else {
						ability1.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								gameBack.remove(abs);
								gameBack.repaint();
								gameBack.revalidate();
								try {
									model.castAbility(model.getCurrentChampion().getAbilities().get(0));
			
									playSound();
									updateView();
									checkGameOver(model.checkGameOver());
								} catch (NotEnoughResourcesException | AbilityUseException
										| CloneNotSupportedException e1) {
									JOptionPane.showMessageDialog(gameBack, e1.getMessage());

								}
								updateView();
							}

						});
					}

					ability2 = new JButton();
					ability2.setText(model.getCurrentChampion().getAbilities().get(1).getName().toUpperCase());
					ability2.setFont(font);
					ability2.setForeground(Color.WHITE);
					ability2.setOpaque(false);
					ability2.setFocusable(false);
					ability2.setContentAreaFilled(false);
					abs.add(ability2);
					ability2.setToolTipText(model.getCurrentChampion().getAbilities().get(1).toString());

					if (model.getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.DIRECTIONAL) {
						ability2.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								gameBack.remove(abs);
								gameBack.revalidate();
								gameBack.repaint();
								JOptionPane.showMessageDialog(gameBack, "Please Select a Direction");
								addDirections();
								left.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(1),
													Direction.LEFT);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});

								right.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(1),
													Direction.RIGHT);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});

								up.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(1),
													Direction.DOWN);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});

								down.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(1),
													Direction.UP);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});

							}

						});
					} else if (model.getCurrentChampion().getAbilities().get(1)
							.getCastArea() == AreaOfEffect.SINGLETARGET) {
						
						ability2.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {

								gameBack.remove(abs);
								gameBack.repaint();
								gameBack.revalidate();
								
								JOptionPane.showMessageDialog(gameBack, "Please Select a Target");
								for (int i = 0; i < Game.getBoardheight(); i++) {
									for ( int j = 0; j < Game.getBoardwidth(); j++) {
										int x = i;
										int z = j;
										grid[i][j].addActionListener(new ActionListener() {
											
											public void actionPerformed(ActionEvent e) {
												try {
													model.castAbility(model.getCurrentChampion().getAbilities().get(1),x,z);
													playSound();
													updateView();
													checkGameOver(model.checkGameOver());
												} catch (NotEnoughResourcesException | AbilityUseException
														| InvalidTargetException | CloneNotSupportedException e1) {
													JOptionPane.showMessageDialog(gameBack, e1.getMessage());
												}												
											}
											
										});
									}
								}
							}
							
						});

					

					}

					else {
						ability2.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								gameBack.remove(abs);
								gameBack.repaint();
								gameBack.revalidate();
								try {
									model.castAbility(model.getCurrentChampion().getAbilities().get(1));
									playSound();
									updateView();
									checkGameOver(model.checkGameOver());
								} catch (NotEnoughResourcesException | AbilityUseException
										| CloneNotSupportedException e1) {
									JOptionPane.showMessageDialog(gameBack, e1.getMessage());
									
								}
								updateView();
							}

						});

					}

					ability3 = new JButton();
					ability3.setText(model.getCurrentChampion().getAbilities().get(2).getName().toUpperCase());
					ability3.setFont(font);
					ability3.setForeground(Color.WHITE);
					ability3.setOpaque(false);
					ability3.setFocusable(false);
					ability3.setContentAreaFilled(false);
					abs.add(ability3);
					ability3.setToolTipText(model.getCurrentChampion().getAbilities().get(2).toString());

					if (model.getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.DIRECTIONAL) {
						ability3.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								gameBack.remove(abs);
								gameBack.revalidate();
								gameBack.repaint();
								JOptionPane.showMessageDialog(gameBack, "Please Select a Direction");
								addDirections();
								left.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(2),
													Direction.LEFT);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});

								right.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(2),
													Direction.RIGHT);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});

								up.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
										
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(2),
													Direction.DOWN);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});

								down.addActionListener(new ActionListener() {

									public void actionPerformed(ActionEvent e) {
									
										try {
											model.castAbility(model.getCurrentChampion().getAbilities().get(2),
													Direction.UP);
											gameBack.remove(d);
											playSound();
											updateView();
											checkGameOver(model.checkGameOver());
										} catch (NotEnoughResourcesException | AbilityUseException
												| CloneNotSupportedException e1) {
											JOptionPane.showMessageDialog(gameBack, e1.getMessage());
											gameBack.remove(d);
											gameBack.repaint();
											gameBack.revalidate();
										}

									}

								});
							}

						});
					} else if (model.getCurrentChampion().getAbilities().get(2)
							.getCastArea() == AreaOfEffect.SINGLETARGET) {
						
						ability3.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {

								gameBack.remove(abs);
								gameBack.repaint();
								gameBack.revalidate();
								JOptionPane.showMessageDialog(gameBack, "Please Select a Target");
								for (int i = 0; i < Game.getBoardheight(); i++) {
									for ( int j = 0; j < Game.getBoardwidth(); j++) {
										int x = i;
										int z = j;
										grid[i][j].addActionListener(new ActionListener() {
											
											public void actionPerformed(ActionEvent e) {
												try {
													model.castAbility(model.getCurrentChampion().getAbilities().get(2),x,z);
													playSound();
													updateView();
													checkGameOver(model.checkGameOver());
												} catch (NotEnoughResourcesException | AbilityUseException
														| InvalidTargetException | CloneNotSupportedException e1) {
													JOptionPane.showMessageDialog(gameBack, e1.getMessage());
												}												
											}
											
										});
									}
								}
							}
							
						});

					
					}

					else {
						ability3.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								gameBack.remove(abs);
								gameBack.repaint();
								gameBack.revalidate();

								try {
									model.castAbility(model.getCurrentChampion().getAbilities().get(2));
									playSound();
									updateView();
									checkGameOver(model.checkGameOver());
								} catch (NotEnoughResourcesException | AbilityUseException
										| CloneNotSupportedException e1) {
									JOptionPane.showMessageDialog(gameBack, e1.getMessage());

								}
								updateView();
							}

						});

					}

					updateView();
				}

			});
			castAbility.setContentAreaFilled(false);
			castAbility.setOpaque(false);
			castAbility.setFocusable(false);
			castAbility.setFont(marvelFont);
			castAbility.setForeground(Color.WHITE);
			actions.add(castAbility);
			useLeaderAbility.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {
						model.useLeaderAbility();
						playSound();
						checkGameOver(model.checkGameOver());
					} catch (LeaderNotCurrentException e1) {
						JOptionPane.showMessageDialog(gameBack, e1.getMessage());
					} catch (LeaderAbilityAlreadyUsedException e1) {
						JOptionPane.showMessageDialog(gameBack, e1.getMessage());
					}
					Grid();

					createFirstPlayerPanel(model.getFirstPlayer());

					createSecondPlayerPanel(model.getSecondPlayer());

					priorityQueueUpdate(model);

					if (model.isFirstLeaderAbilityUsed())
						lau1.setText("LEADER ABILITY USED");
					if (!model.isFirstLeaderAbilityUsed())
						lau1.setText("LEADER ABILITY NOT USED");

					if (model.isSecondLeaderAbilityUsed())
						lau2.setText("LEADER ABILITY USED");
					if (!model.isSecondLeaderAbilityUsed())
						lau2.setText("LEADER ABILITY NOT USED");

					updateView();
				}

			});
			useLeaderAbility.setContentAreaFilled(false);
			useLeaderAbility.setOpaque(false);
			useLeaderAbility.setFont(marvelFont);
			useLeaderAbility.setFocusable(false);
			useLeaderAbility.setForeground(Color.WHITE);
			actions.add(useLeaderAbility);

			gameBack.add(actions);
			actions.setBounds(1010, 400, 250, 50);

			updateView();

			endTurn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					model.endTurn();
//					gameBack.remove(d);
//					gameBack.remove(abs);
					updateView();

					gameBack.repaint();
					gameBack.revalidate();

					

				}

			});
			endTurn.setContentAreaFilled(false);
			endTurn.setOpaque(false);
			font = font.deriveFont(Font.CENTER_BASELINE, 30);
			endTurn.setFont(font);
			endTurn.setBorder(emptyBorder);
			endTurn.setFocusable(false);
			endTurn.setForeground(Color.WHITE);
			endTurn.setBounds(30, view.getHeight() - 100, 200, 50);
			gameBack.add(endTurn);
		}
		}
	}

	public void checkGameOver(Player p) {

		if (p == model.getFirstPlayer()) {
			gameBack.removeAll();
//			ImagePanel win = new ImagePanel("win.jpeg");
//			view.getGame().add(win);
			JLabel winner = new JLabel();
			winner.setBounds(view.getWidth() / 2 - 250, view.getHeight() / 2 - 100, 500, 200);
			gameBack.add(winner);
			font = font.deriveFont(Font.CENTER_BASELINE, 60);
			winner.setBorder(emptyBorder);
			winner.setFont(font);
			winner.setForeground(Color.WHITE);
			winner.setText(view.getGetName1().toUpperCase() + " WINS");
			playWinSound();
			gameBack.repaint();
			gameBack.revalidate();
		} else if (p == model.getSecondPlayer()) {
			gameBack.removeAll();
//			ImagePanel win = new ImagePanel("win.jpeg");
//			view.getGame().add(win);
			JLabel winner = new JLabel();
			winner.setBounds(view.getWidth() / 2 - 250, view.getHeight() / 2 - 100, 500, 200);
			gameBack.add(winner);
			font = font.deriveFont(Font.CENTER_BASELINE, 60);
			winner.setBorder(emptyBorder);
			winner.setFont(font);
			winner.setForeground(Color.WHITE);
			winner.setText(view.getGetName1().toUpperCase() + " WINS");
			playWinSound();
			gameBack.repaint();
			gameBack.revalidate();
		} else
			return;
	}

	public void createCurrentPanel() {
		JLabel current = new JLabel("CURRENT CHAMPION");
		font = font.deriveFont(Font.CENTER_BASELINE, 18);
		current.setFont(font);
		current.setForeground(Color.WHITE);
		current.setBounds(view.getWidth() / 2 + 375, 50, 200, 50);
		gameBack.add(current);

		String s = "<html><p Height=\"500\">" + "Name: " + model.getCurrentChampion().getName() + "<br>" + "Type: ";
		if (model.getCurrentChampion() instanceof Hero)
			s += "Hero";
		else if (model.getCurrentChampion() instanceof Villain)
			s += "Villain";
		else if (model.getCurrentChampion() instanceof AntiHero)
			s += "AntiHero";
		s += "<br>" + "CurrentHP: " + model.getCurrentChampion().getCurrentHP() + "<br>" + "Mana: "
				+ model.getCurrentChampion().getMana() + "<br>" + "CurrentActionPoints: "
				+ model.getCurrentChampion().getCurrentActionPoints() + "<br>" + "Attack Damage: " + model.getCurrentChampion().getAttackDamage()+
				"<br>" + "Atack Range: " + model.getCurrentChampion().getAttackRange();
		s+="<br>" + "Effects: " + model.getCurrentChampion().getAppliedEffects().toString();
		name.setText(s);
		name.setFont(new Font("Times New Roman", 15, 15));
		name.setForeground(Color.WHITE);
		name.setBounds(view.getWidth() / 2 + 400, 30, 300, 400);
		gameBack.add(name);
		
	}

	public void createFirstPlayerPanel(Player c) {
		gameBack.remove(f);
		f = new JPanel(new GridLayout(0, 3));
		font = font.deriveFont(Font.CENTER_BASELINE, 20);
		marvelFont = marvelFont.deriveFont(Font.CENTER_BASELINE, 20);
		lineBorder = BorderFactory.createLineBorder(player1Color, 2);
		titleEmptyBorder = BorderFactory.createTitledBorder(lineBorder, "REMAINING CHAMPION");
		titleEmptyBorder.setTitleColor(Color.WHITE);
		titleEmptyBorder.setTitleFont(marvelFont);

		f.setBorder(titleEmptyBorder);
		f.setOpaque(false);
		f.setBounds(20, 100, 210, 80);
		gameBack.add(f);
		for (int i = 0; i < c.getTeam().size(); i++) {
			JLabel nn = new JLabel();
			nn.setOpaque(false);
			nn.setIcon(namepq(c.getTeam().get(i).getName()));
			nn.setToolTipText(c.getTeam().get(i).remToString());
			if (c.getTeam().get(i) == model.getFirstPlayer().getLeader())
				nn.setBorder(lineBorder);
			f.add(nn);
		}

		f.revalidate();
		f.repaint();
	}

	public void createSecondPlayerPanel(Player c) {
		gameBack.remove(s);
		s = new JPanel(new GridLayout(0, 3));
		font = font.deriveFont(Font.CENTER_BASELINE, 20);
		marvelFont = marvelFont.deriveFont(Font.CENTER_BASELINE, 20);
		lineBorder = BorderFactory.createLineBorder(player2Color, 2);
		titleEmptyBorder = BorderFactory.createTitledBorder(lineBorder, "REMAINING CHAMPION");
		titleEmptyBorder.setTitleColor(Color.WHITE);
		titleEmptyBorder.setTitleFont(marvelFont);
		s.setBorder(titleEmptyBorder);
		s.setOpaque(false);
		s.setBounds(20, view.getHeight() / 2 + 100, 210, 80);
		gameBack.add(s);
		for (int i = 0; i < c.getTeam().size(); i++) {
			JLabel nn = new JLabel();
			nn.setOpaque(false);
			nn.setIcon(namepq(c.getTeam().get(i).getName()));
			nn.setToolTipText(c.getTeam().get(i).remToString());
			if (c.getTeam().get(i) == model.getSecondPlayer().getLeader())
				nn.setBorder(lineBorder);
			s.add(nn);
		}

		s.revalidate();
		s.repaint();
	}

	public void priorityQueueUpdate(Game game) {
		gameBack.remove(pq);
		pq = new JPanel(new GridLayout(0, game.getTurnOrder().size()));
		pq.setOpaque(false);
		pq.setBounds(view.getWidth() / 2 - 185, view.getHeight() - 80, 370, 50);
		gameBack.add(pq);
		lineBorder = BorderFactory.createLineBorder(Color.WHITE, 2);
		PriorityQueue p = new PriorityQueue(game.getTurnOrder().size());
		int count = 1;
		while (!game.getTurnOrder().isEmpty()) {
			JLabel nn = new JLabel();
			if(count == 1)
				nn.setBorder(lineBorder);
			nn.setOpaque(false);
			Champion c = (Champion) game.getTurnOrder().peekMin();
			nn.setIcon(namepq(c.getName()));
			pq.add(nn);
			p.insert(game.getTurnOrder().remove());
			count++;
		}
		while (!p.isEmpty())
			game.getTurnOrder().insert(p.remove());
		pq.repaint();
		pq.revalidate();
	}

	public void Grid() {
		lineBorder = BorderFactory.createLineBorder(Color.WHITE, 3);
		g.removeAll();
		g.setLayout(new GridLayout(5, 5));
		g.setBorder(lineBorder);
		g.setOpaque(false);
		for (int i = 0; i < Game.getBoardheight(); i++) {
			for (int j = 0; j < Game.getBoardwidth(); j++) {
				JButton x =  new JButton();
				grid[i][j] = x;
				grid[i][j].setOpaque(false);
				grid[i][j].setBorder(new LineBorder(Color.BLACK,30));
				grid[i][j].setContentAreaFilled(false);
				grid[i][j].setFocusable(false);
				g.add(grid[i][j]);
				grid[i][j].setBorder(lineBorder);
				if (model.getBoard()[i][j] instanceof Champion) {
					Champion champ = (Champion) model.getBoard()[i][j];
					if (model.getFirstPlayer().getTeam().contains(champ))
						grid[i][j].setBorder(new LineBorder(player1Color, 5));
					else
						grid[i][j].setBorder(new LineBorder(player2Color, 5));
					grid[i][j].setIcon(name(champ.getName()));
				} else if (model.getBoard()[i][j] instanceof Cover) {
					grid[i][j].setIcon(name("Cover"));
					grid[i][j].setToolTipText(model.getBoard()[i][j].toString());
				}

				g.revalidate();
				g.repaint();
			}
			g.setBounds(view.getWidth() / 2 - 350, 20, 700, 600);
			gameBack.add(g);
			g.revalidate();
			g.repaint();
		}

	}

	public void addDirections() {
		d = new JPanel();
		d.setOpaque(false);
		d.setLayout(null);

		ImageIcon l = new ImageIcon("arrowleft.png");
		Image img = l.getImage();
		Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		l = new ImageIcon(newimg);
		left = new JButton();
		left.setOpaque(false);
		left.setBorder(emptyBorder);
		left.setContentAreaFilled(false);
		left.setIcon(l);
		left.setBounds(0, 50, 50, 50);
		d.add(left);

		ImageIcon r = new ImageIcon("arrowright.png");
		img = r.getImage();
		newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		r = new ImageIcon(newimg);
		right = new JButton();
		right.setIcon(r);
		right.setBorder(emptyBorder);
		right.setOpaque(false);
		right.setContentAreaFilled(false);
		right.setBounds(100, 50, 50, 50);
		d.add(right);

		ImageIcon u = new ImageIcon("arrowup.png");
		img = u.getImage();
		newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		u = new ImageIcon(newimg);
		up = new JButton();
		up.setIcon(u);
		up.setBorder(emptyBorder);
		up.setOpaque(false);
		up.setContentAreaFilled(false);
		up.setBounds(50, 0, 50, 50);
		d.add(up);

		ImageIcon dow = new ImageIcon("arrowdown.png");
		img = dow.getImage();
		newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		dow = new ImageIcon(newimg);
		down = new JButton();
		down.setIcon(dow);
		down.setBorder(emptyBorder);
		down.setOpaque(false);
		down.setContentAreaFilled(false);
		down.setBounds(50, 100, 50, 50);
		d.add(down);
		d.setBounds(1100, 550, 150, 150);
		gameBack.add(d);
		gameBack.repaint();
		gameBack.revalidate();
//		left.setOpaque(false);
//		left.setContentAreaFilled(false);
//		ImageIcon imp = new ImageIcon("arrowleft.png");
//		left.setIcon(imp);

		// view.getWidth() / 2 + 400, 50, 250, 50
	}

	public void playSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("slash.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public void playMoveSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("move.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public void playWinSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("win.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	

	public void updateView() {
		Grid();

		createCurrentPanel();

		createFirstPlayerPanel(model.getFirstPlayer());

		createSecondPlayerPanel(model.getSecondPlayer());

		priorityQueueUpdate(model);

		g.repaint();
		g.revalidate();
		s.repaint();
		s.revalidate();
		f.repaint();
		f.revalidate();
		gameBack.revalidate();
		gameBack.repaint();
	}

	public ImageIcon name(String s) {
		switch (s) {
		case "Captain America":
			icon = new ImageIcon("CaptainAmerica.png");
			break;
		case "Deadpool":
			icon = new ImageIcon("Deadpool.png");
			break;
		case "Dr Strange":
			icon = new ImageIcon("DrStrange.png");
			break;
		case "Electro":
			icon = new ImageIcon("Electro.png");
			break;
		case "Ghost Rider":
			icon = new ImageIcon("GhostRider.png");
			break;
		case "Hela":
			icon = new ImageIcon("Hela.png");
			break;
		case "Hulk":
			icon = new ImageIcon("Hulk.png");
			break;
		case "Iceman":
			icon = new ImageIcon("Iceman.png");
			break;
		case "Ironman":
			icon = new ImageIcon("Ironman.png");
			break;
		case "Loki":
			icon = new ImageIcon("Loki.png");
			break;
		case "Quicksilver":
			icon = new ImageIcon("Quicksilver.png");
			break;
		case "Spiderman":
			icon = new ImageIcon("Spiderman.png");
			break;
		case "Thor":
			icon = new ImageIcon("Thor.png");
			break;
		case "Venom":
			icon = new ImageIcon("Venom.png");
			break;
		case "Yellow Jacket":
			icon = new ImageIcon("YellowJacket.png");
			break;
		case "Cover":
			icon = new ImageIcon("Cover.png");
			break;
		}
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		return icon;
	}

	public ImageIcon namepq(String s) {
		switch (s) {
		case "Captain America":
			icon = new ImageIcon("CaptainAmerica.png");
			break;
		case "Deadpool":
			icon = new ImageIcon("Deadpool.png");
			break;
		case "Dr Strange":
			icon = new ImageIcon("DrStrange.png");
			break;
		case "Electro":
			icon = new ImageIcon("Electro.png");
			break;
		case "Ghost Rider":
			icon = new ImageIcon("GhostRider.png");
			break;
		case "Hela":
			icon = new ImageIcon("Hela.png");
			break;
		case "Hulk":
			icon = new ImageIcon("Hulk.png");
			break;
		case "Iceman":
			icon = new ImageIcon("Iceman.png");
			break;
		case "Ironman":
			icon = new ImageIcon("Ironman.png");
			break;
		case "Loki":
			icon = new ImageIcon("Loki.png");
			break;
		case "Quicksilver":
			icon = new ImageIcon("Quicksilver.png");
			break;
		case "Spiderman":
			icon = new ImageIcon("Spiderman.png");
			break;
		case "Thor":
			icon = new ImageIcon("Thor.png");
			break;
		case "Venom":
			icon = new ImageIcon("Venom.png");
			break;
		case "Yellow Jacket":
			icon = new ImageIcon("YellowJacket.png");
			break;
		case "Cover":
			icon = new ImageIcon("Cover.png");
			break;
		}
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		return icon;
	}

	public static void main(String[] args) throws IOException, FontFormatException {
		new Controller();

	}
}
