public class Message {
    enum Type {
        CREATED,
        DESTROYED,
        HEALTH_RESTORED,
        HEALTH_DECREASED,
        ATTACK_STARTED,
        ATTACK_SUCCESSFUL,
        ATTACK_FAILED,
        BATTLE_STARTED,
        BATTLE_FINISHED
    }

    public static void display(Type type, String playersTypeName,
                               String monstersTypeName,
                               int playersSum,
                               int monstersSum) {
        display(type, null, null, playersTypeName, monstersTypeName, playersSum, monstersSum);
    }

    public static void display(Type type, Creature creature) {
        display(type, creature, null, "", "", 0, 0);
    }

    public static void display(Type type, Creature creature, Creature otherCreature) {
        display(type, creature, otherCreature, "", "", 0, 0);
    }

    public static void display(Type type,
                               Creature creature,
                               Creature otherCreature,
                               String playersTypeName,
                               String monstersTypeName,
                               int playersSum,
                               int monstersSum) {
        String text = switch (type) {
            case CREATED -> String.format(
                    "%sThe %s %s was created!%s Attack=%s, protection=%s, health=%s",
                    Color.BLUE,
                    creature.getCreatureType(),
                    creature.getName(),
                    Color.RESET,
                    creature.getAttack(),
                    creature.getProtection(),
                    creature.getHealth()
            );
            case DESTROYED -> String.format(
                    "%sThe %s %s was killed!%s",
                    Color.RED,
                    creature.getCreatureType(),
                    creature.getName(),
                    Color.RESET
            );
            case HEALTH_RESTORED -> String.format(
                    "%sThe %s %s health restored to %s!%s",
                    Color.GREEN,
                    creature.getCreatureType(),
                    creature.getName(),
                    creature.getHealth(),
                    Color.RESET
            );
            case HEALTH_DECREASED -> String.format(
                    "%sThe %s %s health decreased to %s!%s",
                    Color.CYAN,
                    creature.getCreatureType(),
                    creature.getName(),
                    creature.getHealth(),
                    Color.RESET
            );
            case ATTACK_STARTED -> String.format(
                    "BATTLE: %s %s (attack=%s, protection=%s, health=%s) attacks -> " +
                            "%s %s (attack=%s, protection=%s, health=%s)",
                    creature.getCreatureType(),
                    creature.getName(),
                    creature.getAttack(),
                    creature.getProtection(),
                    creature.getHealth(),
                    otherCreature.getCreatureType(),
                    otherCreature.getName(),
                    otherCreature.getAttack(),
                    otherCreature.getProtection(),
                    otherCreature.getHealth()
            );
            case ATTACK_SUCCESSFUL -> String.format(
                    "BATTLE: RESULT of attack %s %s -> %s %s -> %ssuccessful%s!",
                    creature.getCreatureType(),
                    creature.getName(),
                    otherCreature.getCreatureType(),
                    otherCreature.getName(),
                    Color.GREEN,
                    Color.RESET
            );
            case ATTACK_FAILED -> String.format(
                    "BATTLE: RESULT of attack %s %s -> %s %s -> %sfailed%s!",
                    creature.getCreatureType(),
                    creature.getName(),
                    otherCreature.getCreatureType(),
                    otherCreature.getName(),
                    Color.RED,
                    Color.RESET
            );
            case BATTLE_STARTED -> String.format(
                    "BATTLE STARTED: %s=%s, %s=%s",
                    playersTypeName,
                    playersSum,
                    monstersTypeName,
                    monstersSum
            );
            case BATTLE_FINISHED -> String.format(
                    "BATTLE FINISHED: %s=%s, %s=%s",
                    playersTypeName,
                    playersSum,
                    monstersTypeName,
                    monstersSum
            );
        };
        if (!text.isEmpty()) {
            System.out.println(text);
        }
    }

    private static class Color {
        public static final String RESET = "\033[0m";  // Text Reset

        public static final String BLACK = "\033[0;30m";   // BLACK
        public static final String RED = "\033[0;31m";     // RED
        public static final String GREEN = "\033[0;32m";   // GREEN
        public static final String YELLOW = "\033[0;33m";  // YELLOW
        public static final String BLUE = "\033[0;34m";    // BLUE
        public static final String PURPLE = "\033[0;35m";  // PURPLE
        public static final String CYAN = "\033[0;36m";    // CYAN
        public static final String WHITE = "\033[0;37m";   // WHITE
    }
}
