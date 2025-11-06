package com.game;

import java.awt.Color;
import java.awt.Graphics;

public class TileManager {
    
    GamePanel gp;
    
    // Map-Daten: 0 = Gras, 1 = Erde, 2 = Wasser, 3 = Stein
    // Du kannst diese Map beliebig groß machen!
    int[][] mapTileNum;
    
    // Map-Größe (in Tiles)
    public final int maxWorldCol = 100;  // 100 Tiles breit
    public final int maxWorldRow = 100;  // 100 Tiles hoch
    
    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[maxWorldCol][maxWorldRow];
        
        // Erstelle eine Beispiel-Map
        loadMap();
    }
    
    public void loadMap() {
        // Erstelle eine einfache Map
        // Du kannst das später durch ein File-Loading ersetzen
        
        
    }
    
    public void draw(Graphics g) {
        int worldCol = 0;
        int worldRow = 0;
        
        // Berechne welche Tiles sichtbar sind (nur diese zeichnen für Performance)
        int leftEdge = (gp.camera.worldX - gp.screenWidth / 2) / gp.tileSize - 1;
        int rightEdge = (gp.camera.worldX + gp.screenWidth / 2) / gp.tileSize + 1;
        int topEdge = (gp.camera.worldY - gp.screenHeight / 2) / gp.tileSize - 1;
        int bottomEdge = (gp.camera.worldY + gp.screenHeight / 2) / gp.tileSize + 1;
        
        // Begrenze auf Map-Größe
        leftEdge = Math.max(0, leftEdge);
        rightEdge = Math.min(maxWorldCol, rightEdge);
        topEdge = Math.max(0, topEdge);
        bottomEdge = Math.min(maxWorldRow, bottomEdge);
        
        // Zeichne nur sichtbare Tiles
        for (worldRow = topEdge; worldRow < bottomEdge; worldRow++) {
            for (worldCol = leftEdge; worldCol < rightEdge; worldCol++) {
                
                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                
                // Konvertiere World-Koordinaten zu Screen-Koordinaten
                int screenX = worldX - gp.camera.worldX + gp.screenWidth / 2;
                int screenY = worldY - gp.camera.worldY + gp.screenHeight / 2;
                
                // Zeichne Tile basierend auf Typ
                int tileNum = mapTileNum[worldCol][worldRow];
                
                switch (tileNum) {
                    case 0: // Gras
                        g.setColor(new Color(34, 139, 34)); // Forest Green
                        g.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
                        // Gras-Muster
                        g.setColor(new Color(0, 100, 0));
                        g.fillRect(screenX + 2, screenY + 2, gp.tileSize - 4, gp.tileSize - 4);
                        break;
                    case 1: // Erde
                        g.setColor(new Color(139, 69, 19)); // Saddle Brown
                        g.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
                        break;
                    case 2: // Wasser
                        g.setColor(new Color(0, 119, 190)); // Blue
                        g.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
                        // Wasser-Wellen
                        g.setColor(new Color(0, 100, 200));
                        g.drawLine(screenX, screenY + gp.tileSize / 2, screenX + gp.tileSize, screenY + gp.tileSize / 2);
                        break;
                    case 3: // Stein
                        g.setColor(new Color(105, 105, 105)); // Dim Gray
                        g.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
                        // Stein-Textur
                        g.setColor(new Color(70, 70, 70));
                        g.fillRect(screenX + 2, screenY + 2, gp.tileSize - 4, gp.tileSize - 4);
                        break;
                }
                
                
            }
        }
    }
}

