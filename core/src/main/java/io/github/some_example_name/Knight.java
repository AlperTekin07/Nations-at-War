public class Knight extends Troop {
    public Knight(float x, float y, int ownerID) {
        this.x = x; 
        this.y = y; 
        this.ownerID = ownerID;
        this.health = 180; 
        this.damage = 25; 
        this.range = 40f;
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