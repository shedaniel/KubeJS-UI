package dev.latvian.kubejs.ui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * @author LatvianModder
 */
public class Button extends Widget
{
	public static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation("textures/gui/widgets.png");

	public Button()
	{
		z = 10;
		setWidth(200);
		setHeight(20);
	}

	@Override
	public void setName(Object o)
	{
		super.setName(o);
		setWidth(getUi().screen.getUiFont().width(cachedComponent) + 10);
	}

	@Override
	public void renderBackground(MatrixStack matrixStack)
	{
		Minecraft minecraft = Minecraft.getInstance();
		FontRenderer fontrenderer = getUi().screen.getUiFont();
		minecraft.getTextureManager().bind(WIDGETS_LOCATION);
		RenderSystem.color4f(255, 255, 255, alpha);
		int i;
		int j;

		if (!enabled || getAction() == null)
		{
			i = 0;
			j = 0xCCCCCC;
		}
		else if (isMouseOver)
		{
			i = 2;
			j = hoverColor;
		}
		else
		{
			i = 1;
			j = color;
		}

		int w = getWidth();
		int h = getHeight();

		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		matrixStack.pushPose();
		matrixStack.translate(actualX, actualY, z);
		blit(matrixStack, 0, 0, 0, 46 + i * 20, w / 2, h);
		blit(matrixStack, w / 2, 0, 200 - w / 2, 46 + i * 20, w / 2, h);
		//AbstractGui.renderBg(p_230431_1_, minecraft, p_230431_2_, p_230431_3_);
		drawCenteredString(matrixStack, fontrenderer, cachedComponent, w / 2, (h - 8) / 2, j | (alpha << 24));
		matrixStack.popPose();
	}
}