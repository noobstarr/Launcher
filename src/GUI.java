import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GUI extends JFrame implements ActionListener {

	
	/* Launcher menu bar. */
	private JMenuBar launchBar;
	
	/* Launcher menu items. */
	private JMenu file;
	
	/* Item dropdowns from menu item. */
	private JMenuItem website;
	private JMenuItem vote;
	private JMenuItem hiscore;
	
	/* Launcher buttons. */
	private JButton play;
	private JButton update;

	/* Creates a new instance and begins the launch. */
	public static void openLauncher() {
		GUI app = new GUI();
		app.setSize(300, 200);
		app.setResizable(false);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setLocationRelativeTo(null);
		app.setLayout(null);
		app.setVisible(true);
	}
	
	/* Constructor */
	public GUI() {
		super("Miscellania-PS Launcher");
		
		launchBar = new JMenuBar();
		setJMenuBar(launchBar);
		
		file = new JMenu("Links");
		launchBar.add(file);
		
		website = new JMenuItem("Miscellania-ps.com");
		file.add(website);
		website.addActionListener(this);
		
		vote = new JMenuItem("Vote");
		file.add(vote);
		vote.addActionListener(this);
		
		hiscore = new JMenuItem("Hiscores");
		file.add(hiscore);
		hiscore.addActionListener(this);
		
		play = new JButton("Play");
		add(play);
		play.setBounds(110, 25, 80, 30);
		play.addActionListener(this);
		
		update = new JButton("Update Game");
		add(update);
		update.setBounds(101, 75, 100, 30);
		update.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == website) {
			try {
	            Desktop.getDesktop().browse(new URL("http://www.google.com").toURI());
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		} else if (event.getSource() == vote) {
			try {
	            Desktop.getDesktop().browse(new URL("http://www.google.com").toURI());
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		} else if (event.getSource() == hiscore) {
			try {
	            Desktop.getDesktop().browse(new URL("http://www.google.com").toURI());
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		} else if (event.getSource() == update) {
			try {
				downloadFile("http://miscellania-ps.com/client.jar", System.getProperty("user.home")+"/Miscellania.jar");
				downloadFile("http://miscellania-ps.com/version.dat", System.getProperty("user.home")+"/317version.dat");
				JOptionPane.showMessageDialog(null, "The game is is done updating, Click play!",
						"Orion", JOptionPane.PLAIN_MESSAGE);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		} else if (event.getSource() == play) {
			
			File execute = new File(System.getProperty("user.home")+"/Miscellania.jar");
			if (execute.exists()) {
				checkUpdates();
			try {
				Desktop.getDesktop().open(execute);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.exit(0);
		} else {
			JOptionPane.showMessageDialog(null, "The game files need to be downloaded, click ok to begin.",
				"Download", JOptionPane.PLAIN_MESSAGE);
			downloadFile("http://miscellania-ps.com/client.jar", System.getProperty("user.home")+"/Miscellania.jar");
			downloadFile("http://miscellania-ps.com/version.dat", System.getProperty("user.home")+"/317version.dat");
			try {
				Desktop.getDesktop().open(execute);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}
	}
	
	private void checkUpdates() {
		BufferedReader br = null;
		String i = null;
        String i2 = null;
		try {
		URL url = new URL("http://miscellania-ps.com/version.dat");
		br = new BufferedReader(new FileReader(System.getProperty("user.home")+"/317version.dat"));	
        BufferedReader read = new BufferedReader(
        new InputStreamReader(url.openStream()));
        
        i = read.readLine();
        i2 = br.readLine();
        double value = Double.parseDouble(i);
        double value2 = Double.parseDouble(i);
        if (value != value2) {
			JOptionPane.showMessageDialog(null, "Updates found! Click ok to download them.",
					"Download", JOptionPane.PLAIN_MESSAGE);
				downloadFile("http://miscellania-ps.com/client.jar", System.getProperty("user.home")+"/Miscellania.jar");
				downloadFile("http://miscellania-ps.com/version.dat", System.getProperty("user.home")+"/317version.dat");
		}
        read.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		

	}
	
	private void downloadFile(String adress, String localFileName) {
		OutputStream out = null;
		URLConnection conn;
		InputStream in = null;
		
		
		
		
		try {

			URL url = new URL(adress);
			out = new BufferedOutputStream(
				new FileOutputStream(localFileName)); 

			conn = url.openConnection();
			in = conn.getInputStream(); 
		
			byte[] data = new byte[1024]; 
	
			int numRead;
			long numWritten = 0;
			int length = conn.getContentLength();

	
			while((numRead = in.read(data)) != -1) {
				out.write(data, 0, numRead);
				numWritten += numRead;

			}

			//finished downloading
			

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ioe) {
			}
		}

	}

}