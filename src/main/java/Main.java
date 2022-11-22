public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(3, 3);
        simulation.startBattle();
    }

    public static int getRandom(int first, int last) {
        return (int) (Math.random() * (last - first + 1) + first);
    }
}
