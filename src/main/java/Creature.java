public abstract class Creature {
    private static final int MAX_ATTACK_AND_PROTECTION = 20;
    private static final int MAX_HEALTH = 50;
    private static final int MIN_DAMAGE = 2;
    private static final int MAX_DAMAGE = 15;

    private String name;
    private int attack;
    private int protection;
    private int health;
    private int maxHealth;
    private int damageMin;
    private int damageMax;

    public abstract String getCreatureType();

    public Creature(String name, int attack, int protection, int maxHealth, int damageMin, int damageMax) {
        setOptions(name, attack, protection, maxHealth, damageMin, damageMax);
        Message.display(Message.Type.CREATED, this);
    }

    private void setOptions(String name, int attack, int protection, int maxHealth, int damageMin, int damageMax) {
        this.name = (name == null || name.isEmpty()) ? ("Unknown " + getCreatureType()) : name;
        this.attack = Math.min(MAX_ATTACK_AND_PROTECTION, Math.max(Math.abs(attack), 1));
        this.protection = Math.min(MAX_ATTACK_AND_PROTECTION, Math.max(Math.abs(protection), 1));

        this.maxHealth = Math.min(MAX_HEALTH, Math.max(Math.abs(maxHealth), 1));
        this.health = this.maxHealth;

        this.damageMax = Math.min(MAX_DAMAGE, Math.abs(damageMax));
        this.damageMin = Math.min(this.damageMax, Math.max(MIN_DAMAGE, Math.abs(damageMin)));
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getProtection() {
        return protection;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isDead() {
        return health <= 0;
    }

    protected void setHealth(int newHealth) {
        if (!isDead() && newHealth > 0 && health < newHealth) {
            health = Math.min(maxHealth, newHealth);
        }
    }

    public void decreaseHealth(int delta) {
        boolean result = false;
        if (!isDead() && delta > 0) {
            health = Math.max(0, health - delta);
            Message.display(Message.Type.HEALTH_DECREASED, this);
            result = true;
        }
        if (result && isDead()) {
            Message.display(Message.Type.DESTROYED, this);
        }
    }

    public void attackTo(Creature targetCreature) {
        if (!isDead() && !targetCreature.isDead()) {
            Message.display(Message.Type.ATTACK_STARTED, this, targetCreature);
            int attackModifier = Math.max(1, attack - targetCreature.protection + 1);
            boolean attackSuccess = false;
            for (int i = 0; i < attackModifier; i++) {
                if (Random.get(1, 6) >= 5) {
                    attackSuccess = true;
                    break;
                }
            }
            if (attackSuccess) {
                Message.display(Message.Type.ATTACK_SUCCESSFUL, this, targetCreature);
                targetCreature.decreaseHealth(Random.get(damageMin, damageMax));
            } else {
                Message.display(Message.Type.ATTACK_FAILED, this, targetCreature);
            }
        }
    }
}
