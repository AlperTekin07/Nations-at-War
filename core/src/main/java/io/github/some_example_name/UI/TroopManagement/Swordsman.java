<<<<<<< Updated upstream:core/src/main/java/io/github/some_example_name/Swordsman.java
package io.github.some_example_name;
=======
package io.github.some_example_name.UI.TroopManagement;
>>>>>>> Stashed changes:core/src/main/java/io/github/some_example_name/UI/TroopManagement/Swordsman.java

public class Swordsman extends Troop {
    public Swordsman(float x, float y, int ownerID) {
        super(x, y, 100, 15, 30f, ownerID);
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