package org.marvin.Zombies.events;

import net.gtaun.shoebill.constant.WeaponModel;
import net.gtaun.shoebill.object.Player;
import org.marvin.Zombies.Zombie;

/**
 * Created by Marvin on 21.04.2014.
 */
public class ZombieTakeDamageEvent extends ZombieEvent {
    private Player issuer;
    private float amount;
    private WeaponModel model;
    public ZombieTakeDamageEvent(Zombie zombie, Player issuer, float amount, WeaponModel weapon) {
        super(zombie);
        this.amount = amount;
        this.issuer = issuer;
        this.model = weapon;
    }

    public Player getIssuer() {
        return issuer;
    }

    public float getAmount() {
        return amount;
    }

    public WeaponModel getWeapon() {
        return model;
    }
}
