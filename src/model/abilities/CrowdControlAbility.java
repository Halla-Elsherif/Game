package model.abilities;

import java.util.ArrayList;

import model.effects.Effect;
import model.world.Champion;
import model.world.Damageable;

public class CrowdControlAbility extends Ability {
	private Effect effect;

	public CrowdControlAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			Effect effect) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.effect = effect;

	}

	public Effect getEffect() {
		return effect;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
		for(Damageable d: targets)
		{
			Champion c =(Champion) d;
			c.getAppliedEffects().add((Effect) effect.clone());
			effect.apply(c);
		}
		
	}
	public String toString() {
		return "<html><p Height=\"500\">" + "Name: " + this.getName()+"<br>" + "Type: Crowd Control Ability"+ "<br>" + "ManaCost: " + this.getManaCost()+ "<br>" + "BaseCooldown: " + this.getBaseCooldown()+ "<br>" + "CurrentCoolDown: " + this.getCurrentCooldown()+ "<br>" + "CastRange: " + this.getCastRange()+ "<br>" + "CastArea: " + this.getCastArea()+ "<br>" + "RequiredActionPoints: " + this.getRequiredActionPoints() + "<br>" + "Effect: " + this.effect.toString();

	}
}
