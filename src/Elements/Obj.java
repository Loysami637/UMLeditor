package Elements;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.*;

public class Obj extends JComponent{
    protected int x, y;
    protected String objname = "(default)";
    protected int cenx, ceny;
    protected int width, height;
    protected ArrayList<Port> ports = new ArrayList<Port>();
    protected boolean portOn = false;
    
    public Obj(int ox, int oy, int w, int h){
        x = ox;
        y = oy;
        width = w;
        height = h;
        cenx = x + width/2;
        ceny = y + height/2;
        for(int i=0;i<4;i++){
            ports.add(setPort(i));
        }
        //System.out.println("create Obj");
    }

    public void paint(Graphics g){
        System.out.println("Obj: paint");
    }

    // portal d : N 0, E 1, S 2, W 3
    private Port setPort(int d){
        int[] pair = {0,0};
        if( d%2 == 0 ){
            pair[0] = x + width/2;
            if(d==0){
                pair[1] = y;
            }else{
                pair[1] = y + height;
            }
        }else{
            pair[1] = y + height/2;
            if(d==1){
                pair[0] = x + width;
            }else{
                pair[0] = x;
            }
        }
        Port p = new Port(pair[0], pair[1]);
        return p;
    }

    public void turnOnallPorts(boolean setON){
        portOn = setON;
        for(Port p: ports){
            p.turnON(setON);
        }
    }
    public boolean inObj(int tx, int ty){
        int dx = tx - x;
        int dy = ty - y;
        if(dx>=0 && dx<=width && dy>=0 && dy<=height){
            return true;
        }else{
            return false;
        }
    }
    public boolean inRectangle(int x1, int y1, int x2, int y2){
        if(x1>x2){ int t=x2;x2=x1;x1=t; }
        if(y1>y2){ int t=y2;y2=y1;y1=t; }
        if(x>=x1 && x+width<=x2 && y>=y1 && y+height <y2){
            return true;
        }else{
            return false;
        }
    }
    
    public Port GetPort(int px, int py){
        //System.out.println("center("+cenx+","+ceny+"), point("+px+","+py+")");
        Polygon pg = new Polygon();
        pg.addPoint(cenx, ceny);
        pg.addPoint(x, y);
        pg.addPoint(x+width, y);
        if(pg.contains(px,py)){
            return ports.get(0);
        }
        pg.reset();
        pg.addPoint(cenx, ceny);
        pg.addPoint(x+width, y);
        pg.addPoint(x+width, y+height);
        if(pg.contains(px,py)){
            return ports.get(1);
        }
        pg.reset();
        pg.addPoint(cenx, ceny);
        pg.addPoint(x+width, y+height);
        pg.addPoint(x, y+height);
        if(pg.contains(px,py)){
            return ports.get(2);
        }
        pg.reset();
        pg.addPoint(cenx, ceny);
        pg.addPoint(x, y);
        pg.addPoint(x, y+height);
        if(pg.contains(px,py)){
            return ports.get(3);
        }
        return null;
    }

    public void setObjName(String name){
        this.objname = name;
    }
    public void showObjName(){
        System.out.println(objname);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    
    public void trans(int dx, int dy){
        x += dx ;
        y += dy ;
        for(Port p: ports){
            p.trans(dx, dy);
        }
        cenx = x + width/2;
        ceny = y + height/2;
    }

}
