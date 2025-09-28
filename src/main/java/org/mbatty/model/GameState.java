package org.mbatty.model;

public class GameState {
    private Entity  player = new Entity("default", "default", 1, 0, 1, 0, 1);

    Map map;

    public void createNewMap(int size) {
        map = new Map(size);
    }
    public Map getMap() {
        return this.map;
    }
    public int getMapSize() {
        return this.map.getSize();
    }

    public void setPlayerX(int posX) {
        this.player.setPosX(posX);
    }
    public void setPlayerY(int posY) {
        this.player.setPosY(posY);
    }
    public void setPlayerName(String name) {
        this.player.setName(name);
    }

    public int getPlayerX() {
        return (this.player.getPosX());
    }
    public int getPlayerY() {
        return (this.player.getPosY());
    }
    public int getPlayerLevel() {
        return (this.player.getLevel());
    }
    public int getPlayerXPToLevelUp() {
        return (this.player.xpToLevelUp());
    }
    public int getPlayerXP() {
        return (this.player.getExperience());
    }
    public int getPlayerHP() {
        return (this.player.getHealth());
    }
    public Entity getPlayer() {
        return (this.player);
    }
    public String getPlayerName() {
        return (this.player.getName());
    }
    public String getPlayerClass() {
        return (this.player.getClassType());
    }
    public int getPlayerAttackDamage() {
        return (this.player.getAttack());
    }
    public int getPlayerDefense() {
        return (this.player.getDefense());
    }
}
