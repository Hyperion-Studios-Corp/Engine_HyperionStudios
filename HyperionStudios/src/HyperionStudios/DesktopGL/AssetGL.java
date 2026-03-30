package HyperionStudios.DesktopGL;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import HyperionStudios.Core.Graphics.Texture;
import HyperionStudios.Core.Graphics.g3D.Models.ObjModel;
import HyperionStudios.Core.Resource.AssetBackend;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class AssetGL implements AssetBackend {
	
    private Map<String, Texture> textures = new HashMap<>();
    private Map<String, Document> XML = new HashMap<>();
    private Map<String, ObjModel> OBJ = new HashMap<>();
    
    @Override
    public ObjModel loadOBJ(String path) {
    	if (OBJ.containsKey(path)) {
            return OBJ.get(path);
        }
    	
        ArrayList<float[]> verts = new ArrayList<>();
        ArrayList<float[]> texs = new ArrayList<>();
        ArrayList<Float> finalVerts = new ArrayList<>();
        ArrayList<Float> finalTexs = new ArrayList<>();
        
        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE, minZ = Float.MAX_VALUE;
        float maxX = -Float.MAX_VALUE, maxY = -Float.MAX_VALUE, maxZ = -Float.MAX_VALUE;
        
        try {
            InputStream is = AssetGL.class.getResourceAsStream("/assets/" + path + ".obj");
            
            if (is == null) {
                throw new RuntimeException("Arquivo OBJ não encontrado: " + path);
            }
            
            Scanner sc = new Scanner(is);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                
                if (line.startsWith("v ")) {
                    String[] s = line.split("\\s+");

                    float vx = Float.parseFloat(s[1]);
                    float vy = Float.parseFloat(s[2]);
                    float vz = Float.parseFloat(s[3]);
                    
                    verts.add(new float[] { vx, vy, vz });
                    
                    if (vx < minX) { minX = vx; }
                    if (vy < minY) { minY = vy; }
                    if (vz < minZ) { minZ = vz; }
                    
                    if (vx > maxX) { maxX = vx; }
                    if (vy > maxY) { maxY = vy; }
                    if (vz > maxZ) { maxZ = vz; }
                    
                } else if (line.startsWith("vt ")) {
                    String[] s = line.split("\\s+");
                    
                    texs.add(new float[] {
                        Float.parseFloat(s[1]),
                        1.0f - Float.parseFloat(s[2])
                    });
                } else if (line.startsWith("f ")) {
                    String[] s = line.split("\\s+");
                    
                    for (int i = 1; i <= 3; i++) {
                        String[] parts = s[i].split("/");
                        
                        int vIndex = Integer.parseInt(parts[0]) - 1;
                        int tIndex = Integer.parseInt(parts[1]) - 1;
                        
                        float[] v = verts.get(vIndex);
                        float[] t = texs.get(tIndex);
                        
                        finalVerts.add(v[0]);
                        finalVerts.add(v[1]);
                        finalVerts.add(v[2]);
                        
                        finalTexs.add(t[0]);
                        finalTexs.add(t[1]);
                    }
                }
            }
            
            sc.close();
            is.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        ObjModel model = new ObjModel();
        
        model.vertices = new float[finalVerts.size()];
        
        for (int i = 0; i < finalVerts.size(); i++) {
            model.vertices[i] = finalVerts.get(i);
        }
        
        model.uvs = new float[finalTexs.size()];
        
        for (int i = 0; i < finalTexs.size(); i++) {
            model.uvs[i] = finalTexs.get(i);
        }
        
        model.vertexCount = model.vertices.length / 3;
        
        model.minX = minX;
        model.minY = minY;
        model.minZ = minZ;
        
        model.maxX = maxX;
        model.maxY = maxY;
        model.maxZ = maxZ;
        
        model.centerX = (minX + maxX) / 2;
        model.centerY = (minY + maxY) / 2;
        model.centerZ = (minZ + maxZ) / 2;
        
        OBJ.put(path, model);
        
        return model;
    }
    
    @Override
    public Document getXml(String path) throws Exception {
        if (XML.containsKey(path)) {
            return XML.get(path);
        }
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document tmx = null;
        
        try {
            InputStream stream = AssetGL.class.getResourceAsStream("/assets/" + path + ".tmx");
            
            if (stream == null) {
                stream = AssetGL.class.getResourceAsStream("/assets/" + path + ".xml");
                
                if (stream == null) {
                	stream = AssetGL.class.getResourceAsStream("/assets/" + path);
                    
                    if (stream == null) {
                        throw new RuntimeException("XML não encontrado: " + path);
                    }
                }
            }
            
            tmx = builder.parse(stream);
            
            XML.put(path, tmx);
        } catch (Exception e) {
            System.err.println("Erro ao carregar mapa TMX: " + path);
            e.printStackTrace();
            throw e;
        }
        
        return tmx;
    }
    
    @Override
    public Texture getTexture(String path) {
        if (textures.containsKey(path)) {
            return textures.get(path);
        }
        
        Texture texture = loadTextureFromPath(path);
        
        if (texture != null) {
            textures.put(path, texture);
        }
        
        return texture;
    }
    
    private int nextPowerOfTwo(int value) {
        int result = 1;
        
        while (result < value) {
            result <<= 1;
        }
        
        return result;
    }
    
    private Texture loadTextureFromPath(String path) {
        try {
        	BufferedImage original;
            
            try (InputStream is = AssetGL.class.getResourceAsStream("/assets/" + path + ".png")) {
            	original = ImageIO.read(is);
            }
            
            if (original == null) {
                try (InputStream is = AssetGL.class.getResourceAsStream("/assets/" + path + ".jpg")) {
                	original = ImageIO.read(is);
                }
            }
            
            if (original == null) {
                System.err.println("Erro: Nenhum arquivo .png ou .jpg encontrado para: " + path);
                return null;
            }
            
            int realWidth = original.getWidth();
            int realHeight = original.getHeight();
            
            int potWidth = nextPowerOfTwo(realWidth);
            int potHeight = nextPowerOfTwo(realHeight);
            
            BufferedImage image = new BufferedImage(
                potWidth,
                potHeight,
                BufferedImage.TYPE_INT_ARGB
            );
            
            image.getGraphics().drawImage(original, 0, 0, null);
            
            int height = image.getHeight();
            int width = image.getWidth();
            
            int[] pixels = new int[width * height];
            
            image.getRGB(0, 0, width, height, pixels, 0, width);
            
            ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);
            
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = pixels[y * width + x];
                    
                    buffer.put((byte) ((pixel >> 16) & 0xFF)); // R
                    buffer.put((byte) ((pixel >> 8) & 0xFF));  // G
                    buffer.put((byte) (pixel & 0xFF));         // B
                    buffer.put((byte) ((pixel >> 24) & 0xFF)); // A
                }
            }
            
            buffer.flip();
            
            int textureID = GL11.glGenTextures();
            
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
            
            GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL12.GL_TEXTURE_3D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
            
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
            
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
            
            return new Texture(textureID, potWidth, potHeight, realWidth, realHeight, pixels);
        } catch (IOException e) {
            System.err.println("Erro de IO ao carregar textura: " + path + " - " + e.getMessage());
        }
        
        return null;
    }
    
    @Override
    public void dispose() {
        for (Texture texture : textures.values()) {
            GL11.glDeleteTextures(texture.getTextureID());
        }
        
        textures.clear();
        OBJ.clear();
        XML.clear();
        
        System.out.println("Todos os assets e texturas foram liberados.");
    }
}