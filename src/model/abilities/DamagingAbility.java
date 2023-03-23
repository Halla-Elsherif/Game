package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public class DamagingAbility extends Ability {

	private int damageAmount;

	public DamagingAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			int damageAmount) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public void setDamageAmount(int damageAmount) {
		this.damageAmount = damageAmount;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() - damageAmount);

	}
	public String toString() {
		return "<html><p Height=\"500\">" + "Name: " + this.getName()+ "<br>" + "Type: Damaging Ability"+ "<br>" + "ManaCost: " + this.getManaCost()+ "<br>" + "BaseCooldown: " + this.getBaseCooldown()+ "<br>" + "CurrentCoolDown: " + this.getCurrentCooldown()+ "<br>" + "CastRange: " + this.getCastRange()+ "<br>" + "CastArea: " + this.getCastArea()+ "<br>" + "RequiredActionPoints: " + this.getRequiredActionPoints() + "<br>" + "Damage Amount: " + this.damageAmount;

	}
}

