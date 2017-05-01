package com.zixiken.dimdoors.client.controlpanel;

import com.flowpowered.math.vector.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;

import javax.annotation.Nonnull;

import static org.lwjgl.opengl.GL11.*;

public abstract class Component {
    protected static final Tessellator tessellator = Tessellator.getInstance();
    protected static final VertexBuffer vertexBuffer = tessellator.getBuffer();
    protected static final FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
    protected int height;
    protected int positionX;
    protected int positionY;
    protected int width;
    protected boolean pinned;

    protected static void renderText(@Nonnull final String text, float x, float y, Vector4f color) {
        glDisable(GL_BLEND);
        glEnable(GL_TEXTURE_2D);
        glColor4f(color.getX(), color.getY(), color.getZ(), color.getW());
        if (fontRendererObj == null) {
            System.err.println("illegal FontRenderObject reference!");
        }
        final boolean old = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
        fontRendererObj.drawStringWithShadow(text, x, y, ((((int) (255 * color.getX()))) << 24) + ((((int) (255 * color.getY()))) << 16) + ((((int) (255 * color.getZ()))) << 8) + (((int) (255 * color.getW()))));
        fontRendererObj.setUnicodeFlag(old);
        glEnable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
    }

    protected static int textWidth(@Nonnull final String text) {
        return fontRendererObj.getStringWidth(text);
    }

    public abstract void render();

    public int getPositionX() {
        return this.positionX;
    }

    public Component setPositionX(int positionX) {
        this.positionX = positionX;
        return this;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public Component setPositionY(int positionY) {
        this.positionY = positionY;
        return this;
    }

    public int getWidth() {
        return this.width;
    }

    public Component setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return this.height;
    }

    public Component setHeight(int height) {
        this.height = height;
        return this;
    }

    public boolean isPinned() {
        return this.pinned;
    }

    public Component setPinned(boolean pinned) {
        this.pinned = pinned;
        return this;
    }

    public abstract void mouseClicked(int x, int y, int buttonCode);

    public abstract void mouseReleased(int x, int y, int buttonCode);

    public abstract void mouseDrag(int x, int y, int buttonCode);

    public abstract Component revalidate();

    public abstract void mouseEntered(int x, int y);

    public abstract void mouseExited(int x, int y);

    public abstract void mouseMoved(int x, int y);

    public abstract boolean tryClick(int x, int y, int buttonCode);
}
