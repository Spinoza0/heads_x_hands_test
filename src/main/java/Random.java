public class Random {
    public static int get(int first, int last) {
        return (int) (Math.random() * (last - first + 1) + first);
    }
}
