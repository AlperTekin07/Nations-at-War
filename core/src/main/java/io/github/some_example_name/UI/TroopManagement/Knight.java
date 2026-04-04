<<<<<<< Updated upstream:core/src/main/java/io/github/some_example_name/Knight.java
package io.github.some_example_name;
=======
package io.github.some_example_name.UI.TroopManagement;
>>>>>>> Stashed changes:core/src/main/java/io/github/some_example_name/UI/TroopManagement/Knight.java

public class Knight extends Troop {
    public Knight(float x, float y, int ownerID) {
        super(x, y, 180, 25, 40f, ownerID);
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