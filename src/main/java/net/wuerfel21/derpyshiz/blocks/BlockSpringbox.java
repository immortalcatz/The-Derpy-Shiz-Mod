package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySpringbox;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockSpringbox extends BlockAbstractGearbox {

	public IIcon[] icons = new IIcon[2];

	public BlockSpringbox() {
		super("springbox",TileEntitySpringbox.class);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("minecraft:planks_jungle");
		} else {
			this.setBlockTextureName("derpyshiz:springbox_ugly");
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (Main.fancyGearbox) {
			switch (meta) {
			default:
			case 0:
				return Blocks.planks.getIcon(0, 3);
			case 1:
				return GameRegistry.findBlock("derpyshiz", "block").getIcon(0, 11);
			}
		}
		return this.icons[meta];
	}
	
	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int p_149736_5_) {
		TileEntitySpringbox t = (TileEntitySpringbox) world.getTileEntity(x, y, z);
		return t.compOut;
	}
	
	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

}
