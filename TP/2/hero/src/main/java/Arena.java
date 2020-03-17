import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Arena {

    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int width, int height)
    {
        hero = new Hero(10,10);
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height-1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width-1, r));
        }

        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return monsters;
    }

    private boolean canHeroMove(Position position)
    {
        for (Wall wall : walls)
        {
            if(!wall.getPosition().equals(position) &&
                    position.getx() < width -1  &&
                    position.getx() > 0 &&
                    position.gety() < height - 1 &&
                    position.gety() > 0
            )
                return true;
            else
                return false;
        }
        return true;
    }

    private void moveHero(Position position) {
        if(canHeroMove(position)){
            hero.setPosition(position);
        }

    }

    private void moveMonsters()
    {
        for(Monster monster : monsters)
        {
            Position pos = monster.move();
            while(wallCollision(pos) || pos.getx() >= width-1 || pos.getx()<1 ||pos.gety() >= height-1 || pos.gety() < 1) {
                pos = monster.move();
            }
            monster.setPosition(pos);
        }
    }

    public void processKey(KeyStroke key) {

        if (key.getKeyType() == KeyType.ArrowUp)
            moveHero(hero.moveUp());
        else if (key.getKeyType() == KeyType.ArrowDown)
            moveHero(hero.moveDown());
        else if (key.getKeyType() == KeyType.ArrowRight)
            moveHero(hero.moveRight());
        else if (key.getKeyType() == KeyType.ArrowLeft)
            moveHero(hero.moveLeft());
        moveMonsters();
    }

    public void draw(TextGraphics graphics){

        for (Wall wall : walls)
            wall.draw(graphics);
        for(Monster monster : monsters)
            monster.draw(graphics);
        for(Coin coin : coins)
            coin.draw(graphics);

        hero.draw(graphics);

        retrieveCoins();
    }

    private boolean wallCollision(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    private void retrieveCoins()
    {
        for(int i=0; i<coins.size(); i++){
            if(coins.get(i).getPosition().equals(hero.getPosition())){
                coins.remove(i);
                break;
            }
        }
    }
}
