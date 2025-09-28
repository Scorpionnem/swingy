package org.mbatty.model;

/*

    The model in the MVC design pattern is where the logic happens, it takes infos from the controller by exposing methods such as move(direction)

*/

import org.mbatty.model.entities.Entity;

import static java.lang.System.exit;

public class Model {
    private final GameState   gameState = new GameState();

    public void    startNewLevel() {
        Entity  player = gameState.getPlayer();

        int level = player.getLevel();
        int mapSize = (level - 1) * 5 + 10 -(level % 2);

        player.setPosX(mapSize / 2);
        player.setPosY(mapSize / 2);

        gameState.setMap(new Map(mapSize));

        gameState.getMap().setEntity(player, player.getPosX(), player.getPosY());
    }

    public void startFight(Entity e1, Entity e2) {

        // 50/50 chance for e1/e2 to start the fight.

        if (Math.random() > 0.5) {
            while (e1.alive() && e2.alive())
            {
                e1.attack(e2);
                e2.attack(e1);
            }
        }
        else {
            while (e1.alive() && e2.alive())
            {
                e2.attack(e1);
                e1.attack(e2);
            }
        }
    }

    public void moveTo(Entity entity, int dx, int dy) {
        Map map = gameState.getMap();
        int x = entity.getPosX();
        int y = entity.getPosY();

        // Set entity to new pos and remove it from old pos

        map.setEntity(entity, x + dx, y + dy);
        map.setEntity(null, x, y);

        entity.setPosX(x + dx);
        entity.setPosY(y + dy);
    }

    public Entity checkTile(Entity entity, int dx, int dy) {
        Map map = gameState.getMap();
        int x = entity.getPosX();
        int y = entity.getPosY();

        return (map.getTile(x + dx, y + dy));
    }

    public GameState getGameState() {
        return (gameState);
    }
}
