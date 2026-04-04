public class Dragon extends Troop {
    private boolean isFlying;

    public Dragon(float x, float y, int ownerID) {
        this.x = x; 
        this.y = y; 
        this.ownerID = ownerID;
        this.health = 400; 
        this.damage = 50; 
        this.range = 100f;
        this.isFlying = true;
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