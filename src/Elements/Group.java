package Elements;

import java.awt.Graphics;
import java.util.ArrayList;

public class Group extends Obj{
    private int x, y;
    private int width, height;
    private ArrayList<Obj> objContainer = new ArrayList<Obj>();

    public Group(Obj firstObj){
        super(firstObj.getX(), firstObj.getY(), firstObj.getWidth(), firstObj.getHeight());
        objContainer.add(firstObj);
    }
    public void addObj(Obj b){
        if(b.getX() < x) x = b.getX();
        if(b.getY() < y) y = b.getY();
        if(b.getX()+b.getWidth() > x+width) width = b.getX()+b.getWidth()-x;
        if(b.getY()+b.getHeight() > y+height) height = b.getY()+b.getHeight()-y;
        objContainer.add(b);
        System.out.println("Group: add");
    }
    @Override
    public void paint(Graphics g){
        for( Obj eachObj : objContainer){
            eachObj.paint(g);
        }
    }

    public int CountObjContainer(){
        return objContainer.size();
    }
    public Obj getGroupObj(int i){
        return objContainer.get(i);
    }

    @Override
    public void turnOnallPorts(boolean setON){
        for( Obj eachObj : objContainer){
            eachObj.turnOnallPorts(setON);
        }
    }
    @Override
    public boolean inObj(int tx, int ty){
        boolean ingroup = false;
        for( Obj eachObj : objContainer){
            if(eachObj.inObj(tx, ty))ingroup = true;
        }
        return ingroup;
    }
    @Override
    public void trans(int dx, int dy){
        for( Obj eachObj : objContainer){
            eachObj.trans(dx, dy);
        }
    }

    @Override
    public Port GetPort(int px, int py){
        return null;
    }
}
