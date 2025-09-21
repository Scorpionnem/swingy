import javax.swing.JFrame;
import javax.swing.JButton;

import java.util.Scanner;

/* Code to read input in terminal
	public void	createEntity() {
		Scanner input = new Scanner(System.in);

		System.out.print("Name: ");
		_name = input.nextLine();

		System.out.print("Class: ");
		_classType = input.nextLine();
	}
*/

public class Main {
	private static Entity hero;

	public static void main(String[] args) {
		hero = new Knight("Bob");
		Entity goblin = new Goblin("Glordmuk");
		Artifact art = new Artifact("Helm of health", Artifact.Type.HELM, 0);

		hero.equipArtifact(art);
		System.out.println("xp: " + hero.getExperience() + "/" + hero.xpToLevelUp());
		hero.addExperience(10000);
		System.out.println("xp: " + hero.getExperience() + "/" + hero.xpToLevelUp());
		System.out.println(hero.getLevel());

		hero.attack(goblin);
	}
}
