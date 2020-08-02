import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import java.awt.Color;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;

public class SlideShow extends JFrame {

	//Declare Variables
	private JPanel slidePane;
	private JPanel textPane;
	private JPanel buttonPane;
	private CardLayout card;
	private CardLayout cardText;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnReset;
	private JLabel lblSlide;
	private JLabel lblTextArea;
	private Timer timer;
	private int cardIndex;

	/**
	 * Create the application.
	 */
	public SlideShow() throws HeadlessException {
		initComponent();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponent() {
		//Initialize variables to empty objects
		card = new CardLayout();
		cardText = new CardLayout();
		slidePane = new JPanel();
		textPane = new JPanel();
		textPane.setBackground(Color.RED);
		textPane.setBounds(5, 470, 790, 50);
		textPane.setVisible(true);
		buttonPane = new JPanel();
		btnPrev = new JButton();
		btnNext = new JButton();
		btnReset = new JButton();
		lblSlide = new JLabel();
		lblTextArea = new JLabel();
		timer = null;
		cardIndex = 0;

		//Setup frame attributes
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("Top 10 Favorite IU Women's Basketball Moments");
		getContentPane().setLayout(new BorderLayout(10, 50));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Setting the layouts for the panels
		slidePane.setLayout(card);
		textPane.setLayout(cardText);
		
		//logic to add each of the slides and text
		for (int i = 0; i < 10; i++) {
			lblSlide = new JLabel();
			lblTextArea = new JLabel();

			try {
				Image newimg = getResizeIcon(i); // scale it the smooth way  
			    ImageIcon imageIcon = new ImageIcon(newimg);  // transform it back
			    lblSlide.setIcon(imageIcon);
			    lblTextArea.setText(getTextDescription(i));

			} catch (IOException e1) {
			  e1.printStackTrace();
			}

			
			slidePane.add(lblSlide, "card" + i);
			textPane.add(lblTextArea, "cardText" + i);
		}

		getContentPane().add(slidePane, BorderLayout.CENTER);
		getContentPane().add(textPane, BorderLayout.SOUTH);

		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

		btnPrev.setText("Previous");
		btnPrev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goPrevious();
			}
		});
		buttonPane.add(btnPrev);

		btnNext.setText("Next");
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goNext();
			}
		});
		buttonPane.add(btnNext);

		btnReset.setText("Reset");
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goReset();
			}
		});
		buttonPane.add(btnReset);
		
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		goReset();
	}

	/**
	 * Previous Button Functionality
	 */
	private void goPrevious() {
		card.previous(slidePane);
		cardText.previous(textPane);
		cardIndex=(cardIndex+9)%10;
	}
	
	/**
	 * Next Button Functionality
	 */
	private void goNext() {
		card.next(slidePane);
		cardText.next(textPane);
		cardIndex=(cardIndex+1)%10;
	}
	/**
	 * Reset Button Functionality
	 */
	private void goReset() {
		if (timer != null) {
			timer.stop();
		}
		card.first(slidePane);
		cardText.first(textPane);
		cardIndex = 0;
		timer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cardIndex < 10) {
					goNext();
					slidePane.revalidate();
					slidePane.repaint();
				}
				if (cardIndex == 9) {
					timer.stop();
				}
			}
		});
		timer.setRepeats(true);
		timer.start();	

		
	}
	/**
	 * Method to get the images
	 * @throws IOException 
	 */
	private Image getResizeIcon(int i) throws IOException {
		Image image;
		String[] url=new String[] {"Away_Games.jpg","Warmups.jpg", "Pink_Game.jpg", "Blowout.jpg", "Tournament_Win.jpg", "Beat_Purdue.jpg", "The_Fans.jpg", "Greatest_Timeout.jpg", "Upsets.jpg", "South_Carolina.jpg"};
		image = ImageIO.read(getClass().getResource("/resources/" + url[i]));
	    Image newimg = image.getScaledInstance(800, 500,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return newimg;
	}
	
	/**
	 * Method to get the text values
	 */
	private String getTextDescription(int i) {
		String text = ""; 
		if (i==0){
			text = "<html><body><font size='5' color='000000'>#10 Away Games.</font> <br><font color='000000'>One of my favorite moments was trying to watch an away game with my White German Shepherd wanting attention.</body></html>";
		} else if (i==1){
			text = "<html><body><font size='5' color='000000'>#9 Pre-Game Warmups</font> <br><font color='000000'>An hour before the game, hanging out, chatting with the players and the coaches.</body></html>";
		} else if (i==2){
			text = "<html><body><font size='5' color='000000'>#8 The Pink Game</font> <br><font color='000000'>My favorite team playing for a good cause.</body></html>";
		} else if (i==3){
			text = "<html><body><font size='5' color='000000'>#7 Blowouts</font> <br><font color='000000'>IU Basketball is great and sometimes they just click perfectly and are unstoppable.</body></html>";
		} else if (i==4){
			text = "<html><body><font size='5' color='000000'>#6 NCAA Tournament wins</font> <br><font color='000000'>The team is constantly getting better and has even won some NCAA Tournament games.</body></html>";
		} else if (i==5){
			text = "<html><body><font size='5' color='000000'>#5 Beating Purdue</font> <br><font color='000000'>There is nothing better than beating Purdue.</body></html>";
		} else if (i==6){
			text = "<html><body><font size='5' color='000000'>#4 The Fans</font> <br><font color='000000'>IU Basketball fans are the best.  We have the highest attendance in the BigTen.</body></html>";
		} else if (i==7){
			text = "<html><body><font size='5' color='000000'>#3 The William Tell Overture</font> <br><font color='000000'>This is the greatest time out in college basketball.</body></html>";
		} else if (i==8){
			text = "<html><body><font size='5' color='000000'>#2 Upsets</font> <br><font color='000000'>When you plan IU at home, IU is never the underdog.  But, sometimes a team will come in that is higher ranked.</body></html>";
		} else if (i==9){
			text = "<html><body><font size='5' color='000000'>#1 Beating South Carolina</font> <br><font color='000000'>#1 South Carolina had one loss last year, it was given to them by IU on a nuetral court.</body></html>";
		}
		return text;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SlideShow ss = new SlideShow();
				ss.setVisible(true);
			}
		});
	}
}