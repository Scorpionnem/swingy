package org.mbatty.model;

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