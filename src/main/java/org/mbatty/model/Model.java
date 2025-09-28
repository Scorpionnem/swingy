package org.mbatty.model;

/*

    The model in the MVC design pattern is where the logic happens, it takes infos from the controller by exposing methods such as move(direction)

*/

import static java.lang.System.exit;

public class Model {
    private final GameState   gameState = new GameState();

    public void    startGame() {
        int level = gameState.getPlayerLevel();
        int mapSize = (level - 1) * 5 + 10 -(level % 2);

        gameState.setPlayerX(mapSize / 2);
        gameState.setPlayerY(mapSize / 2);

        gameState.createNewMap(mapSize);

        move(0, 0);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void win() {
        System.out.println("You won! Lets start a new game");
        startGame();
    }

    public void combat(Entity player, Entity enemy) {

        //Simulate fight to the death!!!
        while (enemy.getHealth() > 0 && player.getHealth() > 0)
        {
            player.attack(enemy);
            if (enemy.getHealth() <= 0)
                break ;
            enemy.attack(player);
            if (player.getHealth() <= 0)
                break ;
        }
        //Lost
        if (player.getHealth() <= 0) {
            System.out.println("You died");
            exit(1);
        }

        //Won
        player.addExperience(enemy.getAttack() * 100);
        System.out.println("You won the fight against that enemy!");
    }

    public void move(int dx, int dy) {
        //Check win when on the border of the map
        if (gameState.getPlayerX() + dx >= gameState.getMapSize() - 1 || gameState.getPlayerY() + dy >= gameState.getMapSize() - 1)
            win();

        //Check fight with enemy
        Entity enemy = this.gameState.getMap().getMap()[gameState.getPlayerY() + dy][gameState.getPlayerX() + dx];
        if (enemy != null) {
            combat(this.gameState.getPlayer(), enemy);
        }

        //Swap with old position
        this.gameState.getMap().setTile(null, gameState.getPlayerX(), gameState.getPlayerY());
        this.gameState.setPlayerY(gameState.getPlayerY() + dy);
        this.gameState.setPlayerX(gameState.getPlayerX() + dx);
        this.gameState.getMap().setTile(gameState.getPlayer(), gameState.getPlayerX(), gameState.getPlayerY());
    }
}
