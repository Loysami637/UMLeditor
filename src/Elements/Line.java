package Elements;

import java.awt.Graphics;

public class Line {
    protected Port str, end;
    public Line(Port pr1, Port pr2){
        str = pr1;
        end = pr2;
    }
    public void paint(Graphics g){
        g.drawLine(str.getPortX(), str.getPortY(),
                    end.getPortX(), end.getPortY());
    }
}
