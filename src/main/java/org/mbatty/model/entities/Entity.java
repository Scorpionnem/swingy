package org.mbatty.model.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.mbatty.model.Artifact;

import java.io.*;
import java.util.Scanner;
import java.util.Set;

import jakarta.validation.constraints.Min;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

public class Entity {
    private String	_name;
    private String	_classType;

    @Min(value = 1, message = "Level has to be at least 1")
    private int		_level = 1;
    @Min(value = 0)
    private int		_experience = 0;

    private int     posY = 0;
    private int     posX = 0;

    @Min(value = 1)
    private int		_attack = 1;
    @Min(value = 0)
    private int		_defense = 0;
    @Min(value = 1)
    private int		_health = 1;

    protected Artifact              _weapon;
    protected Artifact				_armor;
    protected Artifact				_helm;

    public Entity(String name, String classType, int level, int experience, int attack, int defense, int health) throws EntityException {
        _name = name;
        _classType = classType;
        _level = level;
        _experience = experience;
        _attack = attack;
        _defense = defense;
        _health = health;

        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Entity>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Entity> violation : violations) {
                System.out.println(violation.getMessage());
            }
            throw new EntityException("Invalid entity created!");
        }
    }

    private void parseLine(String line) {
        if (line.isEmpty())
            return ;

        String[] args = line.split(" ");
        if (args.length < 1)
            return ;

        if (args[0].equals("name")) {
            this._name = args[1];
        }
        else if (args[0].equals("class")) {
            this._classType = args[1];
        }
        else if (args[0].equals("level")) {
            this._level = Integer.parseInt(args[1]);
        }
        else if (args[0].equals("experience")) {
            this._experience = Integer.parseInt(args[1]);
        }
        else if (args[0].equals("attack")) {
            this._attack = Integer.parseInt(args[1]);
        }
        else if (args[0].equals("defense")) {
            this._level = Integer.parseInt(args[1]);
        }
        else if (args[0].equals("health")) {
            this._health = Integer.parseInt(args[1]);
        }
        else if (args[0].equals("weapon") || args[0].equals("armor") || args[0].equals("helm")) {
            System.out.println(args[2].toUpperCase());
            this._weapon = new Artifact(args[1], Artifact.Type.valueOf(args[2].toUpperCase()), Integer.parseInt(args[3]));
        }
    }

    public Entity(String file) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File(file));

        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            parseLine(data);
        }
    }

    public void exportFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this._name + ".txt"));
        writer.write("name " + this._name + "\n");
        writer.write("class " + this._classType + "\n");
        writer.write("level " + this._level + "\n");
        writer.write("experience " + this._experience + "\n");
        writer.write("attack " + this._attack + "\n");
        writer.write("defense " + this._defense + "\n");
        writer.write("health " + this._health + "\n");

        if (_weapon != null) {
            writer.write("weapon ");
            _weapon.exportFile(writer);
        }
        if (_armor != null) {
            writer.write("armor ");
            _armor.exportFile(writer);
        }
        if (_helm != null) {
            writer.write("helm ");
            _helm.exportFile(writer);
        }

        writer.close();
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
            _health += _level * 2;
            _attack += _level;
            _defense += _level / 2;
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