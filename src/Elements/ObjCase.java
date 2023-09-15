package Elements;

import java.awt.Color;
import java.awt.Graphics;

public class ObjCase extends Obj{

    public ObjCase(int cx, int cy){
        super(cx, cy, 120, 80);
    }
    
    @Override
    public void paint(Graphics g){
        //System.out.println("Objcase: paint");
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
        g.setColor(Color.black);
        g.drawOval(x, y, width, height);
        g.drawString(objname, x+40 , y+40 );
        if(portOn){
            for(Port p: ports){
                p.turnON(portOn);
                p.paint(g);
            }
        }
    }
    
}
