import java.util.Scanner;

public class Scenario {
    private final Scanner in = new Scanner(System.in);
    private int round = 0;
    private Game game;
    public void welcome(){
        System.out.println(StringRes.YELLOW + "Welcome to the game! Please write player's build. Available units:\n" + StringRes.RESET);
        System.out.println(Unit.getAvailableUnitsString());
        System.out.println("First:");
        Player player1 = getPlayer();
        System.out.println("Second:");
        Player player2 = getPlayer();
        game = new Game("Kek", player1, player2);
        startGame();
    }
    private Player getPlayer(){
        System.out.println("Enter name");
        String name = in.next();
        System.out.println("Enter 3 units you want to use(Type first letter of the class)");
        System.out.println("For example: N N F");
        char a = in.next().charAt(0);
        char b = in.next().charAt(0);
        char c = in.next().charAt(0);
        return new Player(name, new Unit[]{Unit.GetUnit(a), Unit.GetUnit(b), Unit.GetUnit(c)});
    }
    private void startGame(){
        while(!game.isOver()){
            playRound();
        }
        gameIsOver();
    }


    private void playRound() {
        ++round;
        System.out.println(game.getPlayer1().Name + " Select your offence unit and enemy's defence unit");
        System.out.println(StringRes.GREEN + "Offence unit:\n");
        var offence = selectUnit(game.getPlayer(round % 2 == 0 ? 2 : 1));
        System.out.println(StringRes.RESET);
        System.out.println(StringRes.RED + "Enemy unit:\n");
        var defence = selectUnit(game.getPlayer(round % 2 == 0 ? 1 : 2));
        System.out.println(StringRes.RESET);
        game.attack(offence, defence);
    }
    private void gameIsOver() {
        if(game.getPlayer1().isAllUnitsDead() && game.getPlayer2().isAllUnitsDead()){
            System.out.println(StringRes.YELLOW + "Stalemate!" + StringRes.RESET);
        }
        else if(game.getPlayer1().isAllUnitsDead()){
            System.out.println(game.getPlayer2().Name + " won!");
        }
        else System.out.println(game.getPlayer1().Name + " won!");
    }
    private Unit selectUnit(Player player){
        System.out.println("Select unit by typing unit's number");
        System.out.println(player);
        int number = in.nextInt();
        while(player.getUnit(number) == null || player.getUnit(number).isDead()){
            System.out.println(StringRes.RED + "Please select alive unit in bounds" + StringRes.RESET);
            number = in.nextInt();
        }
        return player.getUnit(number);
    }
}
