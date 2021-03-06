package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.wuerfel21.derpyshiz.GuiIds;
import net.wuerfel21.derpyshiz.ISmashable;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCompactEngine;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class BlockCompactEngine extends BlockContainer implements ISmashable {

	public BlockCompactEngine() {
		super(Main.machineMaterial);
		this.setHardness(5f);
		this.setBlockName("derpyshiz.compact_engine");
		this.setCreativeTab(Main.tabRotary);
		this.setHarvestLevel("ds_hammer", 0);
		this.setStepSound(soundTypeMetal);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("minecraft:iron_block");
		} else {
			this.setBlockTextureName("derpyshiz:compact_engine_ugly");
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCompactEngine();
	}

	@Override
	public boolean smashed(World world, int x, int y, int z, int dir, boolean sneaky) {
		TileEntityCompactEngine t = (TileEntityCompactEngine) world.getTileEntity(x, y, z);
		if (t.dir == dir)
			return true;
		t.rotate(dir);
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return !Main.fancyGearbox;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
		TileEntity t = world.getTileEntity(x, y, z);
		if (t instanceof TileEntityCompactEngine) {
			TileEntityCompactEngine tce = (TileEntityCompactEngine) t;
			tce.cleanup();
			tce.dropInv(world, x, y, z);
		}
		super.breakBlock(world, x, y, z, block, p_149749_6_);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offsetX, float offsetY, float offsetZ) {

		TileEntity te = world.getTileEntity(x, y, z);
		if (te == null || te instanceof TileEntityCompactEngine == false) {
			return false;
		}

		if (!world.isRemote) {
			player.openGui(Main.instance, GuiIds.COMPACT_ENGINE, world, x, y, z);
		}

		return true;
	}

	public void dropBlockAsItem(World world, int x, int y, int z,ItemStack stack) {
		//Because visibility :-/
		super.dropBlockAsItem(world, x, y, z, stack);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityCompactEngine) {
			((TileEntityCompactEngine) te).rotate(Blocks.piston.determineOrientation(world, x, y, z, entity));
			if (stack.hasDisplayName()) {
				((TileEntityCompactEngine)world.getTileEntity(x, y, z)).name = stack.getDisplayName();
			}
		} else {
			System.out.println("WTF BOOM!!!11!!!!!");
		}
	}

}
