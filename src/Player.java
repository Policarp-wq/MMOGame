import java.util.Arrays;

public class Player extends Entity{
    private final Unit[] units;
    public Unit getUnit(int num) {
        if(num > 0 && num <= units.length)
            return units[num - 1];
        return null;
    }

    public Unit[] getUnits() {
        return units;
    }

    public Player(String name, Unit[] units) {
        super(name);
        this.units = units.clone();
    }
    public boolean isAllUnitsDead(){
        for (Unit unit : units) {
            if (!unit.isDead())
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(Name + "\nAvailable units:\n");
        int ind = 1;
        for (Unit unit : units) {
            sb.append(ind).append("- ").append(unit).append("\n");
            ++ind;
        }
        return sb.toString();
    }
}
