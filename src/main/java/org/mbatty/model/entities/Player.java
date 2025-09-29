package org.mbatty.model.entities;

import org.mbatty.model.Artifact;

public class Player extends Entity {
    public Player(String name, String classType) throws EntityException {
        super(name, classType, 1, 0, 1, 1, 10);
        _weapon = new Artifact("BasicSword", Artifact.Type.WEAPON, 2);
    }
}