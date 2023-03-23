package game.Controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;



public class View extends JFrame implements ActionListener {

	private JFrame game = new JFrame("Marvel");
	private ImageIcon image = new ImageIcon();
	private JLabel backGround = new JLabel();
	private static JButton play = new JButton("Play");
	private String getName1;
	private String getName2;
	
	private int Height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int Width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 
	
	private static JButton single = new JButton("SINGLE PLAYER");
	private static JButton multip = new JButton("MULTI-PLAYER");
	
	private File font_file = new File("MarvelRegular-Dj83.otf");
	private Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
	
	private Border emptyBorder = BorderFactory.createEmptyBorder();
	
	public View() throws FontFormatException, IOException {
	
		
		font = font.deriveFont(Font.CENTER_BASELINE,30);
		
		
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		game.setUndecorated(true);
	
		playSound();
	
		ImageIcon icon = new ImageIcon("marvelicon.png");
		game.setIconImage(icon.getImage());
		game.setBounds(0, 0,Width, Height);
		game.setTitle("Marvel");
		game.setResizable(true);

		image = new ImageIcon("Marvel.jpg");
		
		play = new JButton();
		play.setFocusable(false);
		play.setBackground(Color.BLACK);
		play.setForeground(Color.WHITE);
		play.setText("PLAY");
		play.setFont(font);
		play.setBorder(emptyBorder);
		play.setBounds(Width/2-150 ,500, 300, 50);
		play.addActionListener(this);
		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		
		backGround = new JLabel();
		backGround.setIcon(image);
		backGround.setBounds(0,0,Width, Height);
		backGround.add(play);
		
		game.add(backGround, BorderLayout.CENTER);
		game.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play) {
			game.getContentPane().removeAll();
			backGround.removeAll();
			
			single.setBounds(Width/2-250, 500, 500, 40);
			single.addActionListener( this);
			single.setFocusable(false);
			single.setBackground(Color.BLACK);
			single.setForeground(Color.WHITE);
			single.setFont(font);
			single.setBorder(emptyBorder);
			single.setOpaque(false);
			single.setContentAreaFilled(false);
			single.setBorderPainted(false);
			
			
			
			multip.setBounds(Width/2-250, 550, 500, 40);
			multip.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
			multip.addActionListener( this);
			multip.setFocusable(false);
			multip.setBackground(Color.BLACK);
			multip.setForeground(Color.WHITE);
			multip.setFont(font);
			multip.setBorder(emptyBorder);
			multip.setOpaque(false);
			multip.setContentAreaFilled(false);
			multip.setBorderPainted(false);
			
			
			backGround.add(single);
			backGround.add(multip);
			
			game.getContentPane().add(backGround);
			
			game.revalidate();
			game.repaint();
		}
		
		if (e.getSource() == single) {
			String getName = JOptionPane.showInputDialog(game, "Enter Your Name");
		}
		
		if (e.getSource() == multip) {
			 getName1 = JOptionPane.showInputDialog(game, "Player 1");
			 while(getName1.isBlank())
				getName1 = JOptionPane.showInputDialog(game, "Player 1");
			 getName2 = JOptionPane.showInputDialog(game, "Player 2");
			 while(getName2.isBlank())
				 getName2 = JOptionPane.showInputDialog(game, "Player 2");
		}
	}
	
	public void playSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("gamesound.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	
	public String getGetName1() {
		return getName1;
	}


	public String getGetName2() {
		return getName2;
	}


	public JFrame getGame() {
		return game;
	}


	public void setGame(JFrame game) {
		this.game = game;
	}


	public ImageIcon getImage() {
		return image;
	}


	public JLabel getBackGround() {
		return backGround;
	}


	public static JButton getPlay() {
		return play;
	}
	
	
	public int getHeight() {
		return Height;
	}

	public static JButton getSingle() {
		return single;
	}

	public static JButton getMultip() {
		return multip;
	}
	
	
	public int getWidth() {
		return Width;
	}



	
}
