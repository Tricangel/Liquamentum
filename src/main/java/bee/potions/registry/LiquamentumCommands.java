package bee.potions.registry;

import bee.potions.effect.Effect;
import bee.potions.effect.EffectInstance;
import bee.potions.effect.EffectTrigger;
import bee.potions.effect.shouldtrigger.ShouldTrigger;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.core.Holder;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.Collection;
import java.util.List;

public class LiquamentumCommands {

    public static void registerCommands() {

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("liquamentumeffects").requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_ADMIN))
                    .then(Commands.literal("give")
                            .then(Commands.argument("targets", EntityArgument.entities())
                                    .then(Commands.argument("should_trigger", ResourceArgument.resource(registryAccess, LiquamentumRegistries.SHOULD_TRIGGER_KEY))
                                                    .then(Commands.argument("effect_trigger", ResourceArgument.resource(registryAccess, LiquamentumRegistries.EFFECT_TRIGGER_KEY))
                                            .then(Commands.argument("duration", IntegerArgumentType.integer()).executes(context -> {
                                                List<Entity> entities = (List<Entity>) EntityArgument.getEntities(context, "targets").stream().toList();
                                                Holder<ShouldTrigger> shouldTrigger = ResourceArgument.getResource(context, "should_trigger", LiquamentumRegistries.SHOULD_TRIGGER_KEY);
                                                Holder<EffectTrigger> effectTrigger = ResourceArgument.getResource(context, "effect_trigger", LiquamentumRegistries.EFFECT_TRIGGER_KEY);
                                                int duration = IntegerArgumentType.getInteger(context, "duration");

                                                entities.forEach(entity -> {
                                                    EffectInstance effect = new EffectInstance(new Effect(shouldTrigger, effectTrigger), duration);
                                                    effect.applyToLivingEntity((LivingEntity) entity);
                                                });

                                                return 1;
                                            }))

                                    )
                                    )
                                    )
                    )



            );
        });
    }

}
