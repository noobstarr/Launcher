import javax.swing.JFrame;
import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel;


public class Main {

	/**
	 * Main method used to start the launcher.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new SubstanceRavenGraphiteGlassLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		GUI.openLauncher();
	}

}