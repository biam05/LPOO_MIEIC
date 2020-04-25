package data;

public class ArenaModel {
    private int width;
    private int height;
    private Hero hero;

    public ArenaModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(new Position(width / 2, height / 2));
    }

    public Position getHeroPosition() {
        return hero.getPosition();
    }

    public void setHeroPosition(Position position) {
        hero.setPosition(position);
    }
}