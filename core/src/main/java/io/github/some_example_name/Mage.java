public class Mage extends Troop {
    private int rangeAttr;
    private int AoE;

    public Mage(float x, float y, int ownerID, int range, int AoE) {
        this.x = x; 
        this.y = y; 
        this.ownerID = ownerID;
        this.rangeAttr = range; 
        this.AoE = AoE;
        this.health = 60; 
        this.damage = 30; 
        this.range = (float)range;
    }

    @Override
    public void update(float delta) {
        if (target != null && target.health > 0) {
            float dist = (float) Math.hypot(target.x - x, target.y - y);
            if (dist <= range) attack(target);
            else moveTowards(target.x, target.y, delta);
        }
    }
}