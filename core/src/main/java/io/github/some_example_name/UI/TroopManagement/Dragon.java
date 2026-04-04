<<<<<<< Updated upstream:core/src/main/java/io/github/some_example_name/Dragon.java
package io.github.some_example_name;
=======
package io.github.some_example_name.UI.TroopManagement;
>>>>>>> Stashed changes:core/src/main/java/io/github/some_example_name/UI/TroopManagement/Dragon.java

public class Dragon extends Troop {
    private boolean isFlying;

    public Dragon(float x, float y, int ownerID) {
        super(x, y, 400, 50, 100f, ownerID);
        this.isFlying = true;
    }

    @Override
    public void update(float delta) {
        if (target != null && target.health > 0) {
            float dist = (float) Math.hypot(target.x - x, target.y - y);
            if (dist <= range) attack(target);
            else moveTo(target.x, target.y, delta);
        }
    }
}