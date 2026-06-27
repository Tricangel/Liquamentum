package bee.potions.effect.tick;

public abstract class CooldownOnTick extends OnTick{

    private float cooldown;

    public float getCooldown() {
        return cooldown;
    }

    public abstract float getDefaultCooldown();

    public boolean onCooldown() {
        return this.cooldown > 0f;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public void setCooldownSeconds(float cooldown) {
        this.cooldown = cooldown * 20;
    }

}
