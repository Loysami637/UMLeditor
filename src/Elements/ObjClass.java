package Elements;

import java.awt.Color;
import java.awt.Graphics;

public class ObjClass extends Obj{
    

    public ObjClass(int cx, int cy){
        super(cx, cy, 80, 120);
    }

    @Override
    public void paint(Graphics g){
        //System.out.println("Objclass: paint");
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        g.drawString(objname, x+20 , y+40 );
        if(portOn){
            for(Port p: ports){
                p.turnON(portOn);
                p.paint(g);
            }
        }
    }

}
