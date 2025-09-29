package org.mbatty.model.entities;

import org.mbatty.model.Artifact;

public class Knight extends Entity {
    public Knight(String name) {
        super(name, "Knight", 1, 0, 1, 1, 10);
        _weapon = new Artifact("BasicSword", Artifact.Type.WEAPON, 2);
    }
}