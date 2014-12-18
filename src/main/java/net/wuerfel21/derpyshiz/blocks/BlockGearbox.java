package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;

public class BlockGearbox extends Block implements ITileEntityProvider {
	
	public BlockGearbox() {
		super(Material.wood);
		this.setBlockName("gearbox");
		this.setBlockTextureName("minecraft:planks_big_oak");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGearbox();
	}
	
	@Override
    public boolean hasTileEntity() {
        return true;
    }
	
	@Override
	public void onBlockPlacedBy(World world,int x,int y,int z,EntityLivingBase entity,ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityGearbox) {
			((TileEntityGearbox) te).dir = Blocks.piston.determineOrientation(world, x, y, z, entity);
		} else {
			System.out.println("WTF BOOM!!!11!!!!!");
		}
	}
	
	@Override
	public boolean isOpaqueCube() {return false;}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return !Main.fancyGearbox;
	}

}
