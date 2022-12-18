import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private static final int PLAYERS_MAX = 5;
    private static final int MONSTERS_MAX = 5;

    List<Player> players;
    List<Monster> monsters;

    public Simulation(int playersSum, int monstersSum) {
        players = new ArrayList<>();
        monsters = new ArrayList<>();
        prepareCommands(playersSum, monstersSum);
    }

    public void prepareCommands(int playersSum, int monstersSum) {
        players.clear();
        monsters.clear();
        playersSum = checkCreaturesSum(playersSum, PLAYERS_MAX);
        monstersSum = checkCreaturesSum(monstersSum, MONSTERS_MAX);
        for (int i = 0; i < playersSum; i++) {
            players.add(new Player(
                    "Man" + (i + 1),
                    Random.get(10, 20),
                    Random.get(10, 20),
                    Random.get(30, 60),
                    Random.get(1, 20),
                    Random.get(1, 20))
            );
        }

        for (int i = 0; i < monstersSum; i++) {
            monsters.add(new Monster(
                    "Monster" + (i + 1),
                    Random.get(10, 20),
                    Random.get(10, 20),
                    Random.get(30, 60),
                    Random.get(1, 20),
                    Random.get(1, 20))
            );
        }
    }

    public void startBattle() {
        Creature creature1;
        Creature creature2;
        Message.display(
                Message.Type.BATTLE_STARTED,
                Player.CREATURE_TYPE,
                Monster.CREATURE_TYPE,
                players.size(),
                monsters.size()
        );
        while (!players.isEmpty() && !monsters.isEmpty()) {
            int playersAttack = Random.get(0, 1);
            if (playersAttack == 1) {
                creature1 = players.get(Random.get(0, players.size() - 1));
                creature2 = monsters.get(Random.get(0, monsters.size() - 1));

            } else {
                creature1 = monsters.get(Random.get(0, monsters.size() - 1));
                creature2 = players.get(Random.get(0, players.size() - 1));
            }
            creature1.attackTo(creature2);
            if (creature2.isDead()) {
                if (playersAttack == 1) {
                    monsters.remove(creature2);
                } else {
                    players.remove(creature2);
                }
            }
        }
        Message.display(
                Message.Type.BATTLE_FINISHED,
                Player.CREATURE_TYPE,
                Monster.CREATURE_TYPE,
                players.size(),
                monsters.size()
        );
    }

    private int checkCreaturesSum(int sum, int maxSum) {
        return (sum <= 0 || sum > maxSum) ? maxSum : sum;
    }
}
