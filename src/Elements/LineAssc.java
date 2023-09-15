package Elements;

import java.awt.Graphics;

public class LineAssc extends Line{
    public LineAssc(Port pr1, Port pr2){
        super(pr1,pr2);

    }
    public void paint(Graphics g){
        int strx = str.getPortX(), stry = str.getPortY(),
            endx = end.getPortX(), endy = end.getPortY();
        int dx = endx - strx, dy = endy - stry;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - 10, xn = xm, ym = 5, yn = -5, x;
        double sin = dy / D, cos = dx / D;
    
        x = xm*cos - ym*sin + strx;
        ym = xm*sin + ym*cos + stry;
        xm = x;
    
        x = xn*cos - yn*sin + strx;
        yn = xn*sin + yn*cos + stry;
        xn = x;
    
        g.drawLine(strx, stry, endx, endy);
        g.drawLine(endx, endy, (int)xm, (int)ym);
        g.drawLine(endx, endy, (int)xn, (int)yn);
    }
}
