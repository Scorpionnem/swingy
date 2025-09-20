import javax.swing.JFrame;
import javax.swing.JButton;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JButton	button = new JButton();

		frame.setTitle("swingy");
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(button);
	}
}
