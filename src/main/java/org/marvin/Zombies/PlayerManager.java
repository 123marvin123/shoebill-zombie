package org.marvin.Zombies;

import net.gtaun.shoebill.constant.BulletHitType;
import net.gtaun.shoebill.event.player.PlayerWeaponShotEvent;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Timer;
import net.gtaun.util.event.EventManagerNode;
import org.marvin.Zombies.events.ZombieDeathEvent;
import org.marvin.Zombies.events.ZombieTakeDamageEvent;

/**
 * Created by Marvin on 21.04.2014.
 */
public class PlayerManager {
    private ZombiePlugin plugin;
    private EventManagerNode eventManager;
    private Timer checker;
    public PlayerManager(ZombiePlugin plugin) {
        this.plugin = plugin;
        this.eventManager = plugin.getEventManager().createChildNode();
        this.eventManager.registerHandler(PlayerWeaponShotEvent.class, (e) -> {
            if(e.getHitType() == BulletHitType.OBJECT) {
                if(plugin.getZombieList().containsKey(e.getHitObject())) {
                    Zombie hittedZombie = plugin.getZombieList().get(e.getHitObject());
                    hittedZombie.setHealth(hittedZombie.getHealth()-12f);
                    ZombieTakeDamageEvent zombieTakeDamageEvent = new ZombieTakeDamageEvent(hittedZombie, e.getPlayer(), 12f, e.getWeapon());
                    eventManager.dispatchEvent(zombieTakeDamageEvent, hittedZombie, e.getPlayer());
                    if(hittedZombie.getHealth() < 1) {
                        ZombieDeathEvent zombieDeathEvent = new ZombieDeathEvent(hittedZombie, e.getPlayer(), e.getWeapon());
                        eventManager.dispatchEvent(zombieDeathEvent, hittedZombie, e.getPlayer());
                        hittedZombie.destroy();
                        plugin.getZombieList().remove(e.getHitObject());
                    }
                }
            }
        });
        this.checker = Timer.create(2000, (e) -> {
            for(Player player : Player.getHumans()) {
                plugin.getZombieList().forEach((sampObject, zombie) -> {
                    if(!zombie.isDestroyed() && zombie.getLocation().distance(player.getLocation()) < 20) {
                        zombie.moveToLocation(player.getLocation(), 4);
                        if(zombie.getCurrentAttackingPlayer() != player)
                            zombie.setCurrentAttackingPlayer(player);
                        if(zombie.getLocation().distance(player.getLocation()) < 1){
                            player.setHealth(player.getHealth()-5);
                        }
                    } else if(!zombie.isDestroyed() && zombie.getCurrentAttackingPlayer() == player) {
                        zombie.setCurrentAttackingPlayer(null);
                    }
                });
            }
        });
        this.checker.start();
    }
    public void uninitialize() {
        this.eventManager.destroy();
        this.checker.stop();
        this.checker.destroy();
    }
}
