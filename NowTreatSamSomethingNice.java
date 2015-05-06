import java.awt.AWTException;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class NowTreatSamSomethingNice {

	public static void main(String[] args) {
		caffaine = 0;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui();
			}
		});
	}

	final static JFrame frame = new JFrame("Don't work too hard!");
	final static JLabel guide = new JLabel("Give this machine a shot of espresso!",SwingConstants.CENTER);
	final static JLabel machine = new JLabel("Machine : Hmm... I think I'll fall asleep after a while..zzzz",SwingConstants.CENTER);
	final static JLabel info = new JLabel("Created by Sam Wong (@samwong1990, s@mwong.hk), \u00a9 2015");
	final static JPanel panel = new JPanel(new GridLayout(4, 1));
	final static JPanel panel2 = new JPanel(new GridLayout(2, 1));
	final static JPanel panelinit = new JPanel(new FlowLayout());
	final static JButton run = new JButton("Offer caffeine!");
	final static JLabel directions = new JLabel("How many mins before this machine falls alsleep?",SwingConstants.CENTER);
	final static JTextField time = new JTextField("15");
	private static int caffaine;
	public static void gui(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//create buttons
		ButtonListener listener = new ButtonListener();
		run.addActionListener(listener);
		panel.add(machine);
		panel.add(guide);
		panelinit.add(directions);
		panelinit.add(time);
		panelinit.add(run);
		panel.add(panelinit);
		panel.add(info);
		frame.add(panel);
		frame.setSize(550, 150);
		frame.setVisible(true);
	}

	//Manages buttons at the bottom
	private static class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			final JButton sent = (JButton)event.getSource();
			final String label = sent.getText();
			if(label.compareTo("Offer caffeine!") == 0 || label.compareTo("More caffeine!") == 0){
				if(caffaine == 0){
					try{
						run(Integer.parseInt(time.getText()));
						run.setText("More caffeine!");
						machine.setText("I'm full of caffeine! I won't fall asleep!!");
						caffaine++;
						frame.remove(panel);
						panel2.add(machine);
						panel2.add(run);
						frame.add(panel2);
					}catch(Exception e){
						JOptionPane.showMessageDialog(frame, "Let's keep it in the set of Natural Number...", "Oops!", JOptionPane.INFORMATION_MESSAGE);
					}
				}else if(caffaine >= 5){
					machine.setText("Overdosed !!!");
					machine.setBackground(Color.RED);
					machine.setForeground(Color.RED);
					caffaine = 1;
					Timer timer = new Timer();
					timer.schedule(new TimerTask(){

						public void run(){
							for(int i=0;i<50000;i++)
								move(robot);
							machine.setText("I'm full of caffeine! I won't fall asleep!!");
							machine.setBackground(Color.BLACK);
							machine.setForeground(Color.BLACK);
						}
					}
						, 0);
					

					}else{
						caffaine++;
					}
				}

			}

			private TimerTask task;
			private Timer timer = new Timer();
			private Robot robot;
			private Random random = new Random();

			public void run(int interval) {
				try {
					robot = new Robot();
				} catch (AWTException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Something went wrong! Please try again.... If problem persist, contact Sam!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
					System.exit(1);
				}
				task = new TimerTask() {
					public void run() {
						move(robot);
					}
				};

				timer.scheduleAtFixedRate(task, 0, interval*1000*60);

			}

			private void move(Robot robot) {

				int xmov, ymov;
				Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
				do{
					xmov = random.nextInt(3) - 1;
					ymov = random.nextInt(3) - 1;
				}while(xmov == ymov && ymov == 0);
				robot.mouseMove((mouseLocation.x + xmov), (mouseLocation.y + ymov));
				frame.validate();
			}


		}
	}

