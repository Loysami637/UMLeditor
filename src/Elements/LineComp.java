package Elements;

import java.awt.Graphics;

public class LineComp extends Line{
    public LineComp(Port pr1, Port pr2){
        super(pr1,pr2);
    }
    public void paint(Graphics g){
        int strx = str.getPortX(), stry = str.getPortY(),
        endx = end.getPortX(), endy = end.getPortY();
        int dx = endx - strx, dy = endy - stry;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - 8, xn = xm, ym = 8, yn = -8, x;
        double xk, yk;
        double sin = dy / D, cos = dx / D;
    
        x = xm*cos - ym*sin + strx;
        ym = xm*sin + ym*cos + stry;
        xm = x;
    
        x = xn*cos - yn*sin + strx;
        yn = xn*sin + yn*cos + stry;
        xn = x;
    
        xk = xn + xm - endx;
        yk = yn + ym - endy;

        int[] xpoints = {endx, (int) xm, (int) xk, (int) xn};
        int[] ypoints = {endy, (int) ym, (int) yk, (int) yn};
    
        g.drawLine(strx, stry, (int)xk, (int)yk);
        g.drawPolygon(xpoints, ypoints, 4);
    }
}
