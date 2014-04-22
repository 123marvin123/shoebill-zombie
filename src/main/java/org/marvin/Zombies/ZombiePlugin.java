package org.marvin.Zombies;

import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.data.Vector3D;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.SampObject;
import net.gtaun.shoebill.resource.Plugin;

import java.util.WeakHashMap;

/**
 * Created by Marvin on 21.04.2014.
 */
public class ZombiePlugin extends Plugin {
    private PlayerManager playerManager;
    private WeakHashMap<SampObject, Zombie> zombieList;
    @Override
    protected void onEnable() throws Throwable {
        this.playerManager = new PlayerManager(this);
        this.zombieList = new WeakHashMap<>();
        getLogger().info("Zombie Plugin Loaded!");
    }

    @Override
    protected void onDisable() throws Throwable {
        this.playerManager.uninitialize();
        this.zombieList.clear();
        getLogger().info("Zombie Plugin Unloaded!");
    }

    public WeakHashMap<SampObject, Zombie> getZombieList() {
        return zombieList;
    }

    public Zombie createZombie(Location loc) {
        Zombie zombie = new Zombie(loc);
        zombieList.put(zombie.getObject(), zombie);
        return zombie;
    }
}
