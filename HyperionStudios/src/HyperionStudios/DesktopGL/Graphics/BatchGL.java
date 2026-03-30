package HyperionStudios.DesktopGL.Graphics;

import org.lwjgl.opengl.GL11;

import HyperionStudios.Core.Graphics.Font.DrawFont;
import HyperionStudios.Core.Graphics.g2D.BatchBackend;
import HyperionStudios.Core.Graphics.g2D.Objects.Sprite;
import HyperionStudios.Core.Graphics.g3D.Object3D;
import HyperionStudios.Core.Maths.Color;

public class BatchGL implements BatchBackend {
    
	@Override
    public void begin() {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }
    
	@Override
    public void drawCube(int x, int y, int width, int height, Color color) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        
        GL11.glPushMatrix();
        
        GL11.glColor4f(color.getRf(), color.getGf(), color.getBf(), color.getAf());
        
        GL11.glTranslatef(x, y, 0);
        
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(0, 0);
            GL11.glVertex2f(width, 0);
            GL11.glVertex2f(width, height);
            GL11.glVertex2f(0, height);
        GL11.glEnd();
        
        GL11.glPopMatrix();
        
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
    }
    
    @Override
    public void end() {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    // ----- Render Font Bitmap ----- \\
    
    @Override
    public void drawText(DrawFont font) {
    	if (font.bitmap == null || font.bitmap.textureID == 0) {
        	System.out.println(
            		"\n\n SpriteBatch drawText(DrawFont font):" +
            		"\n     ID da Textura invalido ou não encontrada."
            );
    		return;
    	}
    	
    	GL11.glPushMatrix();
    	
    	float sizeX = font.size / font.bitmap.width;
    	float sizeY = font.size / font.bitmap.height;
    	
    	Color color = font.color;
        
        GL11.glTranslatef((int) font.x + sizeX, (int) font.y + sizeY, 0);
        GL11.glScalef(sizeX, sizeY, 1);
        
        GL11.glColor4f(color.getRf(), color.getGf(), color.getBf(), color.getAf());
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, font.bitmap.textureID);
        
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(font.bitmap.u1, font.bitmap.v1);
            GL11.glVertex2f(0, 0);
            
            GL11.glTexCoord2f(font.bitmap.u2, font.bitmap.v1);
            GL11.glVertex2f(font.bitmap.width, 0);
            
            GL11.glTexCoord2f(font.bitmap.u2, font.bitmap.v2);
            GL11.glVertex2f(font.bitmap.width, font.bitmap.height);
            
            GL11.glTexCoord2f(font.bitmap.u1, font.bitmap.v2);
            GL11.glVertex2f(0, font.bitmap.height);
        GL11.glEnd();
        
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    // ----- Render Object ----- \\
    
    public void drawObject(Object3D obj) {
    	if (obj.getModel() == null || obj.getModel().vertices == null || obj.getModel().vertexCount == 0) {
            return;
        }
    	
    	if (obj.getTextureID() == 0) {
        	System.out.println(
            		"\n\n SpriteBatch renderObject(Object3D obj):" +
            		"\n     ID da Textura: " + obj.getTextureID()
            );
        	
    		return;
    	}
    	
    	GL11.glEnable(GL11.GL_DEPTH_TEST);
    	
        GL11.glPushMatrix();
        
        float x = obj.getPosition().x;
        float y = obj.getPosition().y;
        float z = -y;
        
        GL11.glTranslatef((int) x, (int) y, (int) z);
        
        GL11.glScalef(obj.getScale().x, obj.getScale().y, obj.getScale().z);
        
        GL11.glRotatef(obj.getRotation().x, 1, 0, 0);
        GL11.glRotatef(obj.getRotation().y, 0, 1, 0);
        GL11.glRotatef(obj.getRotation().z, 0, 0, 1);
        
        GL11.glTranslatef(
        	    -obj.getModel().centerX,
        	    -obj.getModel().centerY,
        	    -obj.getModel().centerZ
        );
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, obj.getTextureID());
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBegin(GL11.GL_TRIANGLES);
        
        for (int i = 0; i < obj.getModel().vertexCount; i++) {
            GL11.glTexCoord2f(
            		obj.getModel().uvs[i * 2],
            		obj.getModel().uvs[i * 2 + 1]
            );
            
            GL11.glVertex3f(
            		obj.getModel().vertices[i * 3],
            		obj.getModel().vertices[i * 3 + 1],
            		obj.getModel().vertices[i * 3 + 2]
            );
        }
        
        GL11.glEnd();
        GL11.glPopMatrix();
        
        GL11.glDisable(GL11.GL_DEPTH_TEST);
    }
    
    // ----- Render Genérico de Sprites ----- \\
    
    public void drawSprite(Sprite render) {
        if (!render.isVisible() || render.getTextureID() == 0) {
        	System.out.println("Sprite não visível ou ID de textura 0: " + render);
        	return;
        }
        
        GL11.glPushMatrix();
        
        float pivotX = render.getWidth() * render.getPivotX();
        float pivotY = render.getHeight() * render.getPivotY();
        
        GL11.glTranslatef((int) render.getX(), (int) render.getY(), 0);
        GL11.glTranslatef((int) -pivotX, (int) -pivotY, 0);
        
        if (render.getRotation().a != 0) {
            GL11.glTranslatef((int) pivotX, (int) pivotY, 0);
            GL11.glRotatef(render.getRotation().a, 0, 0, 1);
            GL11.glTranslatef((int) -pivotX, (int) -pivotY, 0);
        }
        
        GL11.glScalef(render.getScaleX(), render.getScaleY(), 1);
        
        GL11.glColor4f(1.0f, 1.0f, 1.0f, render.getAlpha());
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, render.getTextureID());
        
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(render.getU(), render.getV());
            GL11.glVertex2f(0, 0);
            
            GL11.glTexCoord2f(render.getU() + render.getUVWidth(), render.getV());
            GL11.glVertex2f(render.getWidth(), 0);
            
            GL11.glTexCoord2f(render.getU() + render.getUVWidth(), render.getV() + render.getUVHeight());
            GL11.glVertex2f(render.getWidth(), render.getHeight());
            
            GL11.glTexCoord2f(render.getU(), render.getV() + render.getUVHeight());
            GL11.glVertex2f(0, render.getHeight());
        GL11.glEnd();

        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
}