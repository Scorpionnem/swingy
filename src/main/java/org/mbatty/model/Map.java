package org.mbatty.model;

import org.mbatty.model.entities.Entity;
import org.mbatty.model.entities.Goblin;

public class Map {
    private final Entity[][]map;
    int size;

    public Map(int size) {
        this.size = size;
        map = new Entity[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                    map[y][x] = null;
            }
        }
    }

    public Entity   [][]getMap() {
        return map;
    }

    public Entity   getTile(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size)
            return (null);
        return (map[y][x]);
    }

    public Boolean onEdge(int x, int y) {
        return (x == 0 || y == 0 || x == size - 1 || y == size - 1);
    }

    public int  getSize() {
        return size;
    }

    public void setEntity(Entity entity, int x, int y) {
        map[y][x] = entity;
    }
}
