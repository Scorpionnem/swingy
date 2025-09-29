package org.mbatty.model;

import java.io.BufferedWriter;
import java.io.IOException;

public class Artifact {
    String	_name;
    Type	_type;
    int		_boost;

    public enum Type {
        WEAPON,
        ARMOR,
        HELM
    }

    public Artifact(String name, Type type, int boost) {
        _name = name;
        _type = type;
        _boost = boost;
    }

    public void exportFile(BufferedWriter writer) throws IOException {
        writer.write(_name + " " + _type + " " + _boost);
    }

    public int	getBoost() {
        return (_boost);
    }
    public void	setBoost(int val) {
        _boost = val;
    }
    public Type	getType() {
        return (_type);
    }
}