public class Monster extends Creature {
    public static final String CREATURE_TYPE = "monster";

    public Monster(String name, int attack, int protection, int healthMax, int damageMin, int damageMax) {
        super(name, attack, protection, healthMax, damageMin, damageMax);
    }

    @Override
    public String getCreatureType() {
        return CREATURE_TYPE;
    }
}
