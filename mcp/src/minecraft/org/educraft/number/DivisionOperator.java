package org.educraft.number;

import net.minecraft.item.Item;


public class DivisionOperator extends Item implements MathematicalOperator {

	public DivisionOperator() {
		super(5003);
		setUnlocalizedName("divOperator");
		setMaxStackSize(4);
	}
	
	@Override
	public OperatorType getOperator() {
		return OperatorType.DIVIDE;
	}

}
