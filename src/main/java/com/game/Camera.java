package com.game;

public class Camera {
    
    GamePanel gp;
    Player player;
    
    // Camera Position in World-Koordinaten
    public int worldX;
    public int worldY;
    
    public Camera(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        
        // Starte Camera bei Player Position
        worldX = player.worldX;
        worldY = player.worldY;
    }
    
    public void update() {
        // Camera folgt dem Player
        worldX = player.worldX;
        worldY = player.worldY;
        
        // Optional: Camera-Begrenzung an Map-Grenzen
        // (wird später in TileManager implementiert)
    }
}

