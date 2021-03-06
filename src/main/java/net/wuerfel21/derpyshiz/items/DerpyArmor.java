package net.wuerfel21.derpyshiz.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DerpyArmor extends ItemArmor {
	
	public String armorTexture;
	public Item repair;
	public int meta;
	
	public DerpyArmor(ArmorMaterial am, int type, Item i, int m, String at) {
		super(am,0,type);
		this.armorTexture = at;
		this.repair = i;
		this.meta = m;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack material) {
		return material.getItem() == repair && material.getItemDamage() == meta;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
	    return "derpyshiz:textures/armor/" + this.armorTexture + "_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
	
}
