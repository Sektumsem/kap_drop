package com.metanit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameWindow extends JFrame {

    private static GameWindow game_window;
    private static long last_frame_time;
    private static Image sky;
    private static Image gameover;
    private static Image kap;
    private static float kap_left=200;
    private static float kap_top=-100;
    private static float kap_v=200;


    public static void main(String[] args) throws IOException {
        sky = ImageIO.read(GameWindow.class.getResourceAsStream("sky.jpg"));
        gameover = ImageIO.read(GameWindow.class.getResourceAsStream("gameover.png"));
        kap= ImageIO.read(GameWindow.class.getResourceAsStream("kap.png"));
	    game_window =new GameWindow();
	    game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//после закрытия окна будет завершаться программа
        game_window.setLocation(20,100);
        game_window.setSize(906,478);
        game_window.setResizable(false);
        last_frame_time=System.nanoTime();
        GameField game_field=new GameField();
        game_window.add(game_field);
        game_window.setVisible(true);
    }
    private static  void onRepaint(Graphics g){
        long current_time=System.nanoTime();
        float delta_time=(current_time-last_frame_time)*0.000000001f;
        last_frame_time=current_time;
        kap_top=kap_top+kap_v*delta_time;
        kap_left=kap_left+kap_v*delta_time;

        g.drawImage(sky, 0,0,null);
        g.drawImage(kap, (int) kap_left,(int) kap_top,null);
        //g.drawImage(gameover, 280,120,null);


/*        g.setColor(Color.CYAN);
        g.fillOval(10,10,200,100);
        g.drawLine(10,10,500,500);*/
    }
    private static class GameField extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}
