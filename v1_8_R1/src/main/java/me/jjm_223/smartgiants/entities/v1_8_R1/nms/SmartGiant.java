package me.jjm_223.smartgiants.entities.v1_8_R1.nms;

import me.jjm_223.smartgiants.api.entity.ISmartGiant;
import net.minecraft.server.v1_8_R1.*;
import org.bukkit.Bukkit;

/**
 * Created by Jacob on 3/15/2015.
 * SmartGiant for 1.8
 */
public class SmartGiant extends EntityGiantZombie implements ISmartGiant
{
    private static final double HEALTH = Bukkit.getPluginManager().getPlugin("SmartGiants").getConfig()
            .getDouble("maxHealth");

    public SmartGiant(World world)
    {
        super(world);

        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(HEALTH);
        setHealth((float) HEALTH);

        if (this instanceof SmartGiantHostile)
        {
            return;
        }

        width = 1;

        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalTempt(this, 0.5F, Items.APPLE, false));
        this.goalSelector.a(2, new PathfinderGoalRandomStroll(this, 0.5F));
        this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 16.0F));
        this.goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    public float a(BlockPosition position)
    {
        return 0.5F - world.o(position);
    }

    public boolean isHostile()
    {
        return (this instanceof SmartGiantHostile);
    }
}
