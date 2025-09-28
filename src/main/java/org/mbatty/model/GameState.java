package org.mbatty.model;

import org.mbatty.model.entities.Entity;

public class GameState {
    private Entity  player;
    private Map     map;

    public void setPlayer(Entity player) {
        this.player = player;
    }

    public Entity getPlayer() {
        return (this.player);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map  getMap() {
        return (this.map);
    }
}
