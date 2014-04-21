package org.marvin.Zombies.events;

import net.gtaun.shoebill.constant.WeaponModel;
import net.gtaun.shoebill.object.Player;
import org.marvin.Zombies.Zombie;

/**
 * Created by Marvin on 21.04.2014.
 */
public class ZombieDeathEvent extends ZombieEvent {
    private Player killer;
    private WeaponModel weaponModel;
    public ZombieDeathEvent(Zombie zombie, Player killer, WeaponModel weaponModel) {
        super(zombie);
        this.killer = killer;
        this.weaponModel = weaponModel;
    }

    public Player getKiller() {
        return killer;
    }

    public WeaponModel getWeapon() {
        return weaponModel;
    }
}
