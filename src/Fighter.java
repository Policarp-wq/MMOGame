public class Fighter extends Unit{
    public static final float DefaultHealth = DEFAULTHEALTH;
    private static final float DefaultArmor = DEFAULTARMOR;
    private static final float DefaultAttack = DEFAULTARMOR;
    private final int criticalChance = 10;
    private final float criticalFactor = 1.5f;
    public boolean isUltimateReady(){return ultimateRate >= MAXULTIMATERATE;}
    public Fighter(String name) {
        super(name, DefaultHealth, DefaultArmor, DefaultAttack);
    }
    @Override
     protected float getDamage(){
        float damage = attack * (RandomHandler.getRandomBoolean(criticalChance) ? criticalFactor : 1);
        ultimateRate += damage * 0.5f;
        return damage;
     }

    @Override
    protected void takeDamage(float damage) {
        super.takeDamage(damage);
        ultimateRate += 0.8f * damage;
    }

    @Override
    public void useUltimate(Player offence, Player defence) {
        if(!isUltimateReady())
            return;
        var units = defence.getUnits();
        int mx = 0;
        for(int i = 1; i < units.length; ++i){
            if(!units[i].isDead && units[i].health > units[mx].health)
                mx = i;
        }
        if(!units[mx].isDead){
            var prev = attack;
            attack = DefaultAttack * 2;
            attackEnemy(units[mx]);
            attack = prev;
        }
    }
}
