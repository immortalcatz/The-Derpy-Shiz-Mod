package net.wuerfel21.derpyshiz.client;

import static net.wuerfel21.derpyshiz.client.DerpyRenderHelper.wp;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.wuerfel21.derpyshiz.rotary.AxisChain;

import org.lwjgl.opengl.GL11;

public class RotaryRender {

	public static final boolean[][] axisFaces = new boolean[][] { { false, false, true, true, true, true }, { false, false, true, true, true, true }, { true, true, false, false, true, true }, { true, true, false, false, true, true }, { true, true, true, true, false, false }, { true, true, true, true, false, false } };

	public static final boolean[][] axisEnds = new boolean[][] { { true, false, true, true, true, true }, { false, true, true, true, true, true }, { true, true, true, false, true, true }, { true, true, false, true, true, true }, { true, true, true, true, true, false }, { true, true, true, true, false, true } };
	

	public static final double maxAxisWidth = wp * 6;
	public static final double minAxisWidth = wp * 10;
	
	public static final double wp2 = wp*2;
	
	public static final double minAxisLength = 0;

	public static final double minC = wp * 4;
	public static final double maxC = wp * 12;
	
	public static final ResourceLocation axisTexture = new ResourceLocation("minecraft:textures/blocks/planks_oak.png");
	public static final WholeTexture axisIIcon = new WholeTexture(axisTexture);

	public static void rotateAxis(double position, int dir) {
		switch (dir) {
		default:
		case 0:
			GL11.glRotated(position, 0, -1, 0);
			break;
		case 1:
			GL11.glRotated(position, 0, 1, 0);
			break;
		case 2:
			GL11.glRotated(position, 0, 0, -1);
			break;
		case 3:
			GL11.glRotated(position, 0, 0, 1);
			break;
		case 4:
			GL11.glRotated(position, -1, 0, 0);
			break;
		case 5:
			GL11.glRotated(position, 1, 0, 0);
			break;
		}
	}

	public static void renderAxisChain(Tessellator tessellator, AxisChain chain) {
		if (chain.length <= 0)
			return;
		GL11.glPushMatrix();
		GL11.glTranslated(0.5, 0.5, 0.5);
		rotateAxis(chain.position, chain.dir);
		GL11.glTranslated(-0.5, -0.5, -0.5);
		tessellator.startDrawingQuads();
		int x = 0, y = 0, z = 0;
		ForgeDirection fdir = ForgeDirection.getOrientation(chain.dir);
		switch (chain.dir) {
		default:
		case 0:
			DerpyRenderHelper.addBox(tessellator, axisIIcon, minAxisWidth, minAxisLength+(-chain.length), minAxisWidth, maxAxisWidth, minAxisLength, maxAxisWidth, axisEnds[0]);
			break;
		case 1:
			DerpyRenderHelper.addBox(tessellator, axisIIcon, minAxisWidth, 1, minAxisWidth, maxAxisWidth, 1+chain.length, maxAxisWidth, axisEnds[1]);
			break;
		case 2:
			DerpyRenderHelper.addBox(tessellator, axisIIcon, minAxisWidth, minAxisWidth, minAxisLength+(-chain.length), maxAxisWidth, maxAxisWidth, minAxisLength, axisEnds[2]);
			break;
		case 3:
			DerpyRenderHelper.addBox(tessellator, axisIIcon, minAxisWidth, minAxisWidth, 1, maxAxisWidth, maxAxisWidth, 1+chain.length, axisEnds[3]);
			break;
		case 4:
			DerpyRenderHelper.addBox(tessellator, axisIIcon, minAxisLength+(-chain.length), minAxisWidth, minAxisWidth, minAxisLength, maxAxisWidth, maxAxisWidth, axisEnds[4]);
			break;
		case 5:
			DerpyRenderHelper.addBox(tessellator, axisIIcon, 1, minAxisWidth, minAxisWidth, 1+chain.length, maxAxisWidth, maxAxisWidth, axisEnds[5]);
			break;
		}
		tessellator.draw();
		GL11.glPopMatrix();
	}

	public static void fancyConnection(Tessellator tessellator, IIcon texture, IIcon overlay, int dir) {
		switch (dir) {
		default:
		case 0:// bottom
			DerpyRenderHelper.addBox(tessellator, texture, minC, 0.005, minC, maxC, wp2, maxC, axisEnds[0]);
			tessellator.setNormal(0, -1, 0);
			tessellator.addVertexWithUV(maxC, 0, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, 0, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, 0, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, 0, minC, overlay.getMinU(), overlay.getMinV());
			break;
		case 1:// top
			DerpyRenderHelper.addBox(tessellator, texture, minC, 1-wp2, minC, maxC, 0.995, maxC, axisEnds[1]);
			tessellator.setNormal(0, 1, 0);
			tessellator.addVertexWithUV(maxC, 1, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, 1, maxC, overlay.getMaxU(), overlay.getMaxV());
			break;
		case 2:// north
			DerpyRenderHelper.addBox(tessellator, texture, minC, minC, 0.005, maxC, maxC, wp2, axisEnds[2]);
			tessellator.setNormal(0, 0, -1);
			tessellator.addVertexWithUV(maxC, minC, 0, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, minC, 0, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, maxC, 0, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, maxC, 0, overlay.getMinU(), overlay.getMinV());
			break;
		case 3:// south
			DerpyRenderHelper.addBox(tessellator, texture, minC, minC, 1-wp2, maxC, maxC, 0.995, axisEnds[3]);
			tessellator.setNormal(0, 0, 1);
			tessellator.addVertexWithUV(maxC, minC, 1, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, maxC, 1, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, maxC, 1, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, minC, 1, overlay.getMinU(), overlay.getMaxV());
			break;
		case 4:// west
			DerpyRenderHelper.addBox(tessellator, texture, 0.005, minC, minC, wp2, maxC, maxC, axisEnds[4]);
			tessellator.setNormal(-1, 0, 0);
			tessellator.addVertexWithUV(0, minC, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(0, maxC, maxC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(0, maxC, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(0, minC, minC, overlay.getMinU(), overlay.getMaxV());
			break;
		case 5:// east
			DerpyRenderHelper.addBox(tessellator, texture, 1-wp2, minC, minC, 0.995, maxC, maxC, axisEnds[5]);
			tessellator.setNormal(1, 0, 0);
			tessellator.addVertexWithUV(1, minC, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(1, minC, minC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(1, maxC, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(1, maxC, maxC, overlay.getMinU(), overlay.getMinV());
			break;
		}
	}

	public static void uglyConnection(Tessellator tessellator, IIcon overlay, int dir) {
		switch (dir) {
		default:
		case 0:// bottom
			tessellator.setNormal(0, -1, 0);
			tessellator.addVertexWithUV(maxC, -0.005, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, -0.005, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, -0.005, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, -0.005, minC, overlay.getMinU(), overlay.getMinV());
			break;
		case 1:// top
			tessellator.setNormal(0, 1, 0);
			tessellator.addVertexWithUV(maxC, 1.005, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1.005, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1.005, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, 1.005, maxC, overlay.getMaxU(), overlay.getMaxV());
			break;
		case 2:// north
			tessellator.setNormal(0, 0, -1);
			tessellator.addVertexWithUV(maxC, minC, -0.005, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, minC, -0.005, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, maxC, -0.005, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, maxC, -0.005, overlay.getMinU(), overlay.getMinV());
			break;
		case 3:// south
			tessellator.setNormal(0, 0, 1);
			tessellator.addVertexWithUV(maxC, minC, 1.005, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, maxC, 1.005, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, maxC, 1.005, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, minC, 1.005, overlay.getMinU(), overlay.getMaxV());
			break;
		case 4:// west
			tessellator.setNormal(-1, 0, 0);
			tessellator.addVertexWithUV(-0.005, minC, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(-0.005, maxC, maxC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(-0.005, maxC, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(-0.005, minC, minC, overlay.getMinU(), overlay.getMaxV());
			break;
		case 5:// east
			tessellator.setNormal(1, 0, 0);
			tessellator.addVertexWithUV(1.005, minC, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(1.005, minC, minC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(1.005, maxC, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(1.005, maxC, maxC, overlay.getMinU(), overlay.getMinV());
			break;
		}
	}

}
