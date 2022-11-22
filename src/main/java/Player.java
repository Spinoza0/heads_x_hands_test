public class Player extends Creature {
    public static final String CREATURE_TYPE = "human";

    private int restoreHealthCounter;

    public Player(String name, int attack, int protection, int healthMax, int damageMin, int damageMax) {
        super(name, attack, protection, healthMax, damageMin, damageMax);
        restoreHealthCounter = 3;
    }

    @Override
    public String getCreatureType() {
        return CREATURE_TYPE;
    }

    @Override
    public void decreaseHealth(int delta) {
        super.decreaseHealth(delta);
        if (!isDead())
            restoreHealth();
    }

    private void restoreHealth() {
        if (restoreHealthCounter > 0 && getHealth() <= getMaxHealth() / 2) {
            restoreHealthCounter--;
            setHealth(getHealth() + getMaxHealth() / 2);
            Message.display(Message.Type.HEALTH_RESTORED, this);
        }
    }

}
