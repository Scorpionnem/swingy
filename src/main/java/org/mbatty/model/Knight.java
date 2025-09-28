package org.mbatty.model;

public class Knight extends Entity {
    public Knight(String name) {
        super(name, "Knight", 1, 0, 1, 1, 10);
        _weapon = new Artifact("Basic Sword", Artifact.Type.WEAPON, 2);
    }
}