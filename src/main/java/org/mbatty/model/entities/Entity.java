package org.mbatty.model.entities;

import org.mbatty.model.Artifact;

public class Entity {
    private String	_name;
    private String	_classType;

    private int		_level = 1;
    private int		_experience = 0;

    private int     posY = 0;
    private int     posX = 0;

    private int		_attack = 1;
    private int		_defense = 0;
    private int		_health = 1;

    protected Artifact              _weapon;
    protected Artifact				_armor;
    protected Artifact				_helm;

    public Entity(String name, String classType, int level, int experience, int attack, int defense, int health) {
        _name = name;
        _classType = classType;
        _level = level;
        _experience = experience;
        _attack = attack;
        _defense = defense;
        _health = health;
    }

    public void	attack(Entity target) {
        if (!alive())
            return ;
        target.takeDamage(getAttack());
    }

    public void	takeDamage(int val) {
        if (_armor != null)
            val -= _armor.getBoost();
        val -= _defense;

        if (val <= 0)
            return ;

        if (_helm != null && _helm.getBoost() > 0) {
            int remainingBoost = _helm.getBoost() - val;

            if (remainingBoost >= 0) {
                _helm.setBoost(remainingBoost);
                val = 0;
            }
            else {
                _helm.setBoost(0);
                val = -remainingBoost;
            }
        }

        _health -= val;
    }

    public void	addExperience(int val) {
        _experience += val;

        while (_experience >= xpToLevelUp()) {
            _experience -= xpToLevelUp();
            _level++;
            System.out.println(_name + " leveled up!");
        }
    }

    public void	equipArtifact(Artifact artifact) {
        if (artifact == null)
            return ;
        if (artifact.getType() == Artifact.Type.WEAPON)
            _weapon = artifact;
        else if (artifact.getType() == Artifact.Type.ARMOR)
            _armor = artifact;
        else if (artifact.getType() == Artifact.Type.HELM)
            _helm = artifact;
    }

    public int	xpToLevelUp() {
        return (_level * 1000 + (int)Math.pow(_level - 1, 2) * 450);
    }

    public int	getExperience() {
        return (_experience);
    }
    public int	getLevel() {
        return (_level);
    }
    public int	getAttack() {
        if (_weapon != null)
            return (_attack + _weapon.getBoost());
        return (_attack);
    }
    public int	getDefense() {
        if (_armor != null)
            return (_defense + _armor.getBoost());
        return (_defense);
    }
    public int	getHealth() {
        if (_helm != null)
            return (_health + _helm.getBoost());
        return (_health);
    }
    public Boolean  alive() {
        return (getHealth() > 0);
    }
    public String	getName() {
        return (_name);
    }
    public void setName(String name) {
        this._name = name;
    }
    public String	getClassType() {
        return (_classType);
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
}