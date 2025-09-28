package org.mbatty.model;

public class Map {
    private Entity [][]map;
    int size;

    public Map(int size) {
        this.size = size;
        map = new Entity[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (Math.random() < 0.5 && x != size / 2 && y != size / 2)
                    map[y][x] = new Goblin("enemy");
                else
                    map[y][x] = null;
            }
        }
    }

    public Entity[][] getMap() {
        return map;
    }
    public int getSize() {
        return size;
    }
    public void setTile(Entity val, int x, int y) {
        map[y][x] = val;
    }
}
