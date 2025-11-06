package com.game;
import javax.swing.JFrame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("2D Game");
        
        // Fullscreen für Mac
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        
        // Zentriere das Fenster
        frame.setLocationRelativeTo(null);
        
        // Fullscreen aktivieren (vor setVisible!)
        if (gd.isFullScreenSupported()) {
            frame.setUndecorated(true);
            gd.setFullScreenWindow(frame);
        } else {
            // Fallback: Maximize
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        
        // Pack nach Fullscreen, damit Dimensionen stimmen
        frame.pack();
        frame.setVisible(true);
        
        // Starte Game Loop
        gamePanel.startGameThread();
    }
}

