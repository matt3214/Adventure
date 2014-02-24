import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

public class Adventure {
	private AudioSystem as;

	public Adventure() {
		init();

	}

	private void init() {
		as = new AudioSystem();
		as.playSound("res/test.wav");
		JOptionPane.showMessageDialog(null, "Did it work?");
	}

}
