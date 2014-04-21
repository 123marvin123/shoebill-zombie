package org.marvin.Zombies.events;

import net.gtaun.util.event.Event;
import org.marvin.Zombies.Zombie;

/**
 * Created by Marvin on 21.04.2014.
 */
public class ZombieEvent extends Event {
    private Zombie zombie;

    protected ZombieEvent(Zombie zombie) {
        this.zombie = zombie;
    }

    public Zombie getZombie() {
        return zombie;
    }
}
