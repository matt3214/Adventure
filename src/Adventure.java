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
