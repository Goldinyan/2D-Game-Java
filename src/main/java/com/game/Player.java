package com.game;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    
    GamePanel gp;
    KeyHandler keyH;
    
    // Position in World-Koordinaten (Tile-basiert)
    public int worldX;
    public int worldY;
    public int speed;
    
    // Player-Größe
    public int width;
    public int height;
    
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        // Startposition (kannst du ändern)
        worldX = 10 * gp.tileSize;
        worldY = 10 * gp.tileSize;
        
        speed = 2; // Pixel pro Frame (kannst du anpassen)
        width = gp.tileSize;
        height = gp.tileSize;
    }
    
    public void update() {
        // Movement basierend auf Tastendruck
        if (keyH.upPressed) {
            worldY -= speed;
        }
        if (keyH.downPressed) {
            worldY += speed;
        }
        if (keyH.leftPressed) {
            worldX -= speed;
        }
        if (keyH.rightPressed) {
            worldX += speed;
        }
    }
    
    public void draw(Graphics g) {
        // Zeichne Player relativ zur Camera
        int screenX = worldX - gp.camera.worldX + gp.screenWidth / 2;
        int screenY = worldY - gp.camera.worldY + gp.screenHeight / 2;
        
        // Nur zeichnen wenn auf Screen sichtbar
        if (worldX + gp.tileSize > gp.camera.worldX - gp.screenWidth / 2 &&
            worldX - gp.tileSize < gp.camera.worldX + gp.screenWidth / 2 &&
            worldY + gp.tileSize > gp.camera.worldY - gp.screenHeight / 2 &&
            worldY - gp.tileSize < gp.camera.worldY + gp.screenHeight / 2) {
            
            g.setColor(Color.WHITE);
            g.fillRect(screenX, screenY, width, height);
            
            // Augen für besseres Aussehen
            g.setColor(Color.BLACK);
            g.fillRect(screenX + 4, screenY + 4, 3, 3);
            g.fillRect(screenX + 9, screenY + 4, 3, 3);
        }
    }
}

