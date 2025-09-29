package org.mbatty.model.entities;

import org.mbatty.model.Artifact;

public class Goblin extends Entity {
    public Goblin(String name, int level) throws EntityException {
        super(name, "Goblin", level, 0, 1, 0, level * 2);
        _weapon = new Artifact("Stick", Artifact.Type.WEAPON, 1 + level);
    }
}
