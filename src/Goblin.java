public class Goblin extends Entity {
	public Goblin(String name) {
		super(name, "Goblin", 1, 0, 0, 0, 3);
		_weapon = new Artifact("Spiky Stick", Artifact.Type.WEAPON, 1);
	}
}
