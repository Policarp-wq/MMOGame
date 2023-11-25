public class Fighter extends Unit{
    public static final float MaxHealth = DEFAULTHEALTH;
    private static final float MaxArmor = DEFAULTARMOR;
    private static final float MaxAttack = DEFAULTATTACK;

    public boolean isUltimateReady(){return ultimateRate >= MAXULTIMATERATE;}
    public Fighter(String name) {
        super(name, MaxHealth, MaxArmor, MaxAttack);
    }
    @Override
     protected float getDamage(){
        float criticalFactor = 1.5f;
        int criticalChance = 10;
        float damage = attack * (RandomHandler.getRandomBoolean(criticalChance) ? criticalFactor : 1);
        if(health < 0.2f * MaxHealth)
            damage += MaxAttack * 0.1f;
        ultimateRate += damage * 0.5f;
        return damage;
     }

    @Override
    protected void takeDamage(float damage) {
        super.takeDamage(damage);
        ultimateRate += 0.7f * damage;
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
            attack = MaxAttack * 2;
            attackEnemy(units[mx]);
            attack = prev;
        }
    }
}
