package com.game;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile (kein Scale!)
    final int scale = 1;
    final int tileSize = originalTileSize * scale; //16x16 tile
    
    // Fullscreen Dimensionen für Mac
    final int screenWidth;
    final int screenHeight;
    final int maxScreenCol;
    final int maxScreenRow;
    
    //WORLD SETTINGS
    Thread gameThread;
    
    // FPS
    int FPS = 60;
    
    // Game Objects
    public KeyHandler keyH = new KeyHandler();
    public Player player;
    public Camera camera;
    public TileManager tileM;

    public GamePanel(){
        //  Bildschirmgröße 
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        
        maxScreenCol = screenWidth / tileSize;
        maxScreenRow = screenHeight / tileSize;
        
        //  Screen-Größe an Tile-Grid anpassen
        int adjustedWidth = maxScreenCol * tileSize;
        int adjustedHeight = maxScreenRow * tileSize;

        this.setPreferredSize(new Dimension(adjustedWidth, adjustedHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        
        // Keyboard Input aktivieren
        this.setFocusable(true);
        this.addKeyListener(keyH);
        
        // Erstelle Game Objects
        tileM = new TileManager(this);
        player = new Player(this, keyH);
        camera = new Camera(this, player);
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS; // Nanosekunden pro Frame
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    
    public void update() {
        // Update Player (Movement)
        player.update();
        
        // Update Camera (folgt Player)
        camera.update();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Zeichne Map zuerst (Hintergrund)
        tileM.draw(g);
        
        // Zeichne Player darüber
        player.draw(g);
        
        // Optional: Debug Info
        // g.setColor(Color.WHITE);
        // g.drawString("Player X: " + player.worldX / tileSize + " Y: " + player.worldY / tileSize, 10, 20);
    }
}
