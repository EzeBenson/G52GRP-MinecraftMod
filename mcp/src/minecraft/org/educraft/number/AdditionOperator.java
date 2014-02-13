package org.educraft.number;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AdditionOperator extends Item implements MathematicalOperator {
	
	public AdditionOperator() {
		super(5000);
		setUnlocalizedName("addOperator");
		setMaxStackSize(4);
		setCreativeTab(CreativeTabs.tabMisc);
		setTextureName("educraft:addition");
	}

	@Override
	public OperatorType getOperator() {
		// TODO Auto-generated method stub
		return OperatorType.PLUS;
	}

}
