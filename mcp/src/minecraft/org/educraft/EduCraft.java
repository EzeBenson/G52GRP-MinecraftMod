package org.educraft;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

import org.educraft.dummy.DummyAttackHandler;
import org.educraft.dummy.MathsWand;
import org.educraft.number.AdditionOperator;
import org.educraft.number.DivisionOperator;
import org.educraft.number.MultiplicationOperator;
import org.educraft.number.Number15;
import org.educraft.number.Number15Zombie;
import org.educraft.number.Number2;
import org.educraft.number.Number2Zombie;
import org.educraft.number.Number30;
import org.educraft.number.Numbers;
import org.educraft.number.SubtractionOperator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "EduCraft", name = "EduCraft", version = "0.1.0")
@NetworkMod(clientSideRequired = true)
public class EduCraft {

	// instance of the maths wand
	public static final Item MATHS_WAND = new MathsWand(6000);
	// instances of the mathematical operators
	public static final Item ADD_OPR = new AdditionOperator().setTextureName("educraft:addition");
	public static final Item SUB_OPR = new SubtractionOperator().setTextureName("educraft:subtraction");
	public static final Item MUL_OPR = new MultiplicationOperator().setTextureName("educraft:multiplication");
	public static final Item DIV_OPR = new DivisionOperator().setTextureName("educraft:division");
	public static final Item NUMBER = new Numbers(6005);
	public static final Item NUMBER30 = new Number30(6006);
	public static final Item NUMBER2 = new Number2(6007);
	
	// The instance of your mod that Forge uses.
	@Instance(value = "EduCraft")
	public static EduCraft instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "org.educraft.client.ClientProxy", serverSide = "org.educraft.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void load(FMLInitializationEvent event) {
		// TODO implement with EduCraft files

		/* MATHS WAND */
		// localised name for maths wand
		LanguageRegistry.addName(MATHS_WAND, "Maths Wand");
		
		/* MATHEMATICAL OPERATORS */
		// localised names for mathematical operators
		LanguageRegistry.addName(NUMBER, "15");
		LanguageRegistry.addName(NUMBER30, "30");
		LanguageRegistry.addName(NUMBER2, "2");
		LanguageRegistry.addName(ADD_OPR, "Addition sign");
		LanguageRegistry.addName(SUB_OPR, "Subtraction sign");
		LanguageRegistry.addName(MUL_OPR, "Multiplication sign");
		LanguageRegistry.addName(DIV_OPR, "Division sign");
		
		// crafting recipes for mathematical operators
		ItemStack sticks = new ItemStack(Item.stick);
		GameRegistry.addRecipe(new ItemStack(NUMBER), "xyz", 'x', NUMBER,'y', MUL_OPR,'z', NUMBER);
		GameRegistry.addRecipe(new ItemStack(NUMBER), "xyz", 'x', NUMBER,'y', DIV_OPR,'z', NUMBER);
		
		GameRegistry.addRecipe(new ItemStack(ADD_OPR), " s ", "sss", " s ",
				's', sticks);
		GameRegistry.addRecipe(new ItemStack(SUB_OPR), "   ", "sss", "   ",
				's', sticks);
		GameRegistry.addRecipe(new ItemStack(MUL_OPR), "s s", " s ", "s s",
				's', sticks);
		GameRegistry.addRecipe(new ItemStack(DIV_OPR), "  s", " s ", "s  ",
				's', sticks);
		// recipes to break operators down into sticks
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 4),
				new ItemStack(ADD_OPR));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 2),
				new ItemStack(SUB_OPR));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 4),
				new ItemStack(MUL_OPR));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 2),
				new ItemStack(DIV_OPR));
		
		/* NUMBER ZOMBIES */
		// register the number 2 zombie
		EntityRegistry.registerGlobalEntityID(Number2Zombie.class,
				"Number 2 Zombie", EntityRegistry.findGlobalUniqueEntityId(),
				32324, 2243);
		EntityRegistry.registerModEntity(Number2Zombie.class, "Number 2 Zombie",
				EntityRegistry.findGlobalUniqueEntityId(), this, 60, 3, true);
		EntityRegistry.addSpawn(Number2Zombie.class, 10, 1, 2,
				EnumCreatureType.monster, BiomeGenBase.plains);
		// register the number 15 zombie
		EntityRegistry.registerGlobalEntityID(Number15Zombie.class,
				"Number 15 Zombie", EntityRegistry.findGlobalUniqueEntityId(),
				32324, 2243);
		EntityRegistry.registerModEntity(Number15Zombie.class, "Number 15 Zombie",
				EntityRegistry.findGlobalUniqueEntityId(), this, 60, 3, true);
		EntityRegistry.addSpawn(Number15Zombie.class, 10, 1, 2,
				EnumCreatureType.monster, BiomeGenBase.plains);
		// register the attack handler
		MinecraftForge.EVENT_BUS.register(new DummyAttackHandler());

		proxy.registerRenderers();
	}

}
