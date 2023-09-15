package Elements;

import java.awt.Graphics;

public class LineGene extends Line{
    public LineGene(Port pr1, Port pr2){
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
    
        int[] xpoints = {endx, (int) xm, (int) xn};
        int[] ypoints = {endy, (int) ym, (int) yn};
    
        g.drawLine(strx, stry, endx, endy);
        g.drawPolygon(xpoints, ypoints, 3);
    }
}
