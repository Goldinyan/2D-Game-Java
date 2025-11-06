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
        // Game logic hier
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Aktuelle Panel-Größe verwenden
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int cols = panelWidth / tileSize;
        int rows = panelHeight / tileSize;
        
        // Zeichne Grid für Debug (kannst du später entfernen)
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i <= cols; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, panelHeight);
        }
        for (int i = 0; i <= rows; i++) {
            g.drawLine(0, i * tileSize, panelWidth, i * tileSize);
        }
        
        // Hier deine coolen Grafiken zeichnen!
        // Beispiel: Ein paar farbige Tiles
        g.setColor(Color.CYAN);
        g.fillRect(5 * tileSize, 5 * tileSize, tileSize, tileSize);
        
        g.setColor(Color.MAGENTA);
        g.fillRect(10 * tileSize, 10 * tileSize, tileSize, tileSize);
        
        g.setColor(Color.YELLOW);
        g.fillRect(15 * tileSize, 15 * tileSize, tileSize, tileSize);
        
        // NICHT g.dispose() aufrufen! Swing verwaltet das Graphics-Objekt selbst
    }
}
