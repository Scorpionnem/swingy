package org.mbatty.model.entities;

import org.mbatty.model.Artifact;

public class Goblin extends Entity {
    public Goblin(String name) {
        super(name, "Goblin", 1, 0, 0, 0, 1);
        _weapon = new Artifact("Stick", Artifact.Type.WEAPON, 1);
    }
}
