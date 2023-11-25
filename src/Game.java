public class Game extends Entity{
    private final Player player1;
    private final Player player2;


    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
    public Player getPlayer(int num){
        return switch (num) {
            case 1 -> getPlayer1();
            case 2 -> getPlayer2();
            default -> null;
        };
    }
    public Game(String name, Player player1, Player player2) {
        super(name);
        this.player1 = player1;
        this.player2 = player2;
    }


    public void attack(Unit offense, Unit defence){
        offense.attackEnemy(defence);
        System.out.println();
    }
    public boolean isOver(){
        return player1.isAllUnitsDead() || player2.isAllUnitsDead();
    }
}
