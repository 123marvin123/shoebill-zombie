package org.marvin.Zombies;

import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.data.Vector3D;
import net.gtaun.shoebill.object.Destroyable;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.SampObject;

/**
 * Created by Marvin on 21.04.2014.
 */
public class Zombie implements Destroyable {
    private float health;
    private SampObject object;
    private Location location;
    private Player currentAttackingPlayer;
    public Zombie(Location loc) {
        this.health = 100.0f;
        this.location = loc;
        this.object = SampObject.create(0, loc, new Vector3D());
    }

    public SampObject getObject() {
        return object;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public Location getLocation() {
        return location;
    }
    public void moveToLocation(Location loc, int speed) {
        if(!this.object.isDestroyed())
            this.object.move(new Vector3D(loc), speed);
    }

    public Player getCurrentAttackingPlayer() {
        return currentAttackingPlayer;
    }

    public void setCurrentAttackingPlayer(Player currentAttackingPlayer) {
        this.currentAttackingPlayer = currentAttackingPlayer;
    }

    @Override
    public void destroy() {
        this.object.destroy();
    }

    @Override
    public boolean isDestroyed() {
        return this.object.isDestroyed();
    }
}
