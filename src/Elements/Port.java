package Elements;

import java.awt.Graphics;

public class Port {
    private int x, y;
    private boolean turnOn = false;
    public Port(int x1, int y1){
        x = x1;
        y = y1;
    }

    public void turnON(boolean setON){
        if(setON)this.turnOn = true;
        else this.turnOn = false;
    }
    
    public boolean checkPort(){
        return turnOn;
    }
    public int getPortX(){
        return x;
    }
    public int getPortY(){
        return y;
    }
    public void showPort(){
        System.out.println("(" + x + ", " + y + ")");
    }

    public void paint(Graphics g){
        if(this.turnOn){
            g.drawOval(x-3, y-3, 6, 6);
            //System.out.println("Port: paint");
        }else{
            //System.out.println("Port: paint, but turnOFF");
        }
    }

    public void trans(int dx, int dy){
        x += dx ;
        y += dy ;
    }

}
