public class Unit extends Entity{
    public static final float DEFAULTHEALTH = 100;
    public static final float DEFAULTARMOR = 10;
    public static final float DEFAULTATTACK = 10;

    public enum Units{
         Fighter, Wizard, Archer, Nicolas
    }
    protected float health;
    protected float armor;
    protected float attack;
    protected float ultimateRate = 0;
    protected final float MAXULTIMATERATE = 100;
    protected boolean isDead = false;

    public boolean isDead() {
        return isDead;
    }

    public float getHealth() {
        return health;
    }

    public float getArmor() {
        return armor;
    }

    public float getAttack() {
        return attack;
    }
    public void useUltimate(Player offence, Player defence){}

    public static Unit getUnit(Units unit ){
        return switch (unit) {
            case Fighter -> new Unit("Fighter", DEFAULTHEALTH * 1.2f, DEFAULTARMOR, DEFAULTATTACK);
            case Wizard -> new Unit("Wizard", 0.7f * DEFAULTHEALTH, 0, 1.3f * DEFAULTATTACK);
            case Archer -> new Unit("Archer", DEFAULTHEALTH, 0.3f * DEFAULTARMOR, 1.1f * DEFAULTATTACK);
            case Nicolas -> new Unit("Nicolas Cage", 0.1f * DEFAULTHEALTH, 0, 10 * DEFAULTATTACK);
            default -> new Unit("Entity", 0, 0, 0);
        };
    }
    public static Unit GetUnit(char c){
        return switch (c) {
            case 'F' -> Unit.getUnit(Unit.Units.Fighter);
            case 'W' -> Unit.getUnit(Unit.Units.Wizard);
            case 'N' -> Unit.getUnit(Unit.Units.Nicolas);
            default -> Unit.getUnit(Unit.Units.Archer);
        };
    }
    public static String getAvailableUnitsString(){
        return
                getUnit(Units.Fighter) + "\n" +
                getUnit(Units.Wizard) + "\n" +
                getUnit(Units.Archer) + "\n" +
                getUnit(Units.Nicolas) + "\n";
    }

    public Unit(String name, float health, float armor, float attack) {
        super(name);
        this.health = health;
        this.armor = armor;
        this.attack = attack;
    }

    protected float getDamage() {return attack;}

    public final void attackEnemy(Unit enemy){
        if(!enemy.isDead())
            enemy.takeDamage(getDamage());
    }
    protected void takeDamage(float damage){
        if(armor >= damage){
            armor -= 0.7f * damage;
            return;
        }
        health -= (damage - armor);
        if(health <= 0)
            isDead = true;
    }

    @Override
    public String toString() {
        return Name + " " + (isDead ? "[Dead]" : "[Alive]") + " : " +
                String.format("Health: %.2f Armor: %.2f Attack: %.2f",
                health, armor, attack);
    }
}
