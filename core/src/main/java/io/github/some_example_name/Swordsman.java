public class Swordsman extends Troop {
    public Swordsman(float x, float y, int ownerID) {
        this.x = x; 
        this.y = y; 
        this.ownerID = ownerID;
        this.health = 100; 
        this.damage = 15; 
        this.range = 30f;
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