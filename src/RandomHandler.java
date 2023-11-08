import java.util.Random;

public class RandomHandler {
    private static final Random random = new Random();
    private static int getChance(){
        return random.nextInt() * 100;
    }
    public static boolean getRandomBoolean(int chance){
        return getChance() <= chance;
    }
}
