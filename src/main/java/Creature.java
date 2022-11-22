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
        this.name = name;
        this.attack = Math.abs(attack);
        this.protection = Math.abs(protection);
        this.maxHealth = Math.abs(maxHealth);
        this.health = maxHealth;
        this.damageMin = Math.abs(damageMin);
        this.damageMax = Math.abs(damageMax);
        checkOptions();
        Message.display(Message.Type.CREATED, this);
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

    private void checkOptions() {
        if (name == null || name.isEmpty()) {
            name = "Unknown " + getCreatureType();
        }

        if (attack == 0) {
            attack = 1;
        } else if (attack > MAX_ATTACK_AND_PROTECTION) {
            attack = MAX_ATTACK_AND_PROTECTION;
        }
        if (protection == 0) {
            protection = 1;
        } else if (protection > MAX_ATTACK_AND_PROTECTION) {
            protection = MAX_ATTACK_AND_PROTECTION;
        }


        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
            maxHealth = MAX_HEALTH;
        }

        damageMin = Math.max(MIN_DAMAGE, damageMin);
        damageMax = Math.min(MAX_DAMAGE, damageMax);
        if (damageMin > damageMax) {
            damageMin = damageMax;
        }
    }

    public void setHealth(int newHealth) {
        if (!isDead() && newHealth > 0 && health < newHealth) {
            health = newHealth;
            if (health > maxHealth) {
                health = maxHealth;
            }
        }
    }

    public void decreaseHealth(int delta) {
        boolean result = false;
        if (!isDead() && delta > 0) {
            health = health - delta;
            if (health < 0) {
                health = 0;
            }
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
            int attackModifier = attack - targetCreature.protection + 1;
            if (attackModifier <= 0) {
                attackModifier = 1;
            }
            boolean attackSuccess = false;
            for (int i = 0; i < attackModifier; i++) {
                if (Main.getRandom(1, 6) >= 5) {
                    attackSuccess = true;
                    break;
                }
            }
            if (attackSuccess) {
                Message.display(Message.Type.ATTACK_SUCCESSFUL, this, targetCreature);
                targetCreature.decreaseHealth(Main.getRandom(damageMin, damageMax));
            } else {
                Message.display(Message.Type.ATTACK_FAILED, this, targetCreature);
            }
        }
    }
}
