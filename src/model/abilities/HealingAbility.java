package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public  class HealingAbility extends Ability {
	private int healAmount;
	
	public HealingAbility(String name,int cost, int baseCoolDown, int castRadius, AreaOfEffect area,int required, int healingAmount) {
		super(name,cost, baseCoolDown, castRadius, area,required);
		this.healAmount = healingAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	
	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() + healAmount);

	}
	
	public String toString() {
		return "<html><p Height=\"500\">" + "Name: " + this.getName()+ "<br>" + "Type: Healing Ability"+ "<br>" + "ManaCost: " + this.getManaCost() + "<br>" + "BaseCooldown: " + this.getBaseCooldown()+ "<br>" + "CurrentCoolDown: " + this.getCurrentCooldown()+ "<br>" + "CastRange: " + this.getCastRange()+ "<br>" + "CastArea: " + this.getCastArea()+ "<br>" + "RequiredActionPoints: " + this.getRequiredActionPoints() + "<br>" + "Healing Amount: " + this.healAmount;

	}
	

}
