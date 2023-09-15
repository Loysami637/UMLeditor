package UI;
import Elements.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyPanel extends JPanel{
    private ArrayList<Obj> objlist = new ArrayList<Obj>();
    private ArrayList<Obj> selectedObj = new ArrayList<Obj>();
    private ArrayList<Line> linelist = new ArrayList<Line>();
    private ModeManager mm ;
    private int x1, y1, x2, y2;
    private Port linePort1, linePort2;
    private boolean drag = false;
    
    public MyPanel(MyFrame upframe){
        this.setBackground(Color.white);
        this.setBounds(60, 0, upframe.getWidth(), upframe.getHeight());
        this.addMouseListener(new CustomMouseListener());
        this.addMouseMotionListener(new MyMouseMotionListener());
        mm = upframe.getModeManager();

    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=objlist.size()-1;i>=0;i--){
            objlist.get(i).paint(g);
        }
        for( Line eachLine : linelist){
            eachLine.paint(g);
        }
        if(drag){
            g.setColor(Color.blue);
            g.drawRect(x1, y1, abs(x2-x1), abs(y2-y1));
            g.setColor(Color.black);
        }
    }
    class MyMouseMotionListener implements MouseMotionListener{
        public void mouseMoved(MouseEvent e){}
        public void mouseDragged(MouseEvent e) {
            //System.out.println("drag");
            if(mm.getMode()=="Slt"){
                //drag = true;
                x2 = e.getX();
                y2 = e.getY();
                //repaint();            
            }
        }
    }

    class CustomMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {
            //System.out.println("Mouse Pressed: ("+e.getX()+", "+e.getY() +")");
            x1 = e.getX();
            y1 = e.getY();
            switch(mm.getMode()){
                case "Cls":
                    ObjClass newclass = new ObjClass(x1, y1);
                    objlist.add(0, newclass);
                    offAllPorts();
                    break;
                case "Usc":
                    ObjCase newcase = new ObjCase(x1, y1);
                    objlist.add(0, newcase);
                    offAllPorts();
                    break;
                case "Slt":
                    for( Obj eachObj : objlist){
                        if(eachObj.inObj(x1, y1)){
                            //eachObj.turnOnallPorts(true);
                        }else{
                            eachObj.turnOnallPorts(false);
                        }
                    }
                    break;
                case "Assl":
                    for( Obj eachObj : objlist){
                        if(eachObj.inObj(x1,y1)){
                            linePort1 = eachObj.GetPort(x1, y1);
                        }
                    }
                    break;
                case "Coml":
                    for( Obj eachObj : objlist){
                        if(eachObj.inObj(x1,y1)){
                            linePort1 = eachObj.GetPort(x1, y1);
                        }
                    }
                    break;
                case "Genl":
                    for( Obj eachObj : objlist){
                        if(eachObj.inObj(x1,y1)){
                            linePort1 = eachObj.GetPort(x1, y1);
                        }
                    }
                    break;
                default:
                    offAllPorts();
            }
        }
        public void mouseReleased(MouseEvent e) {
            //System.out.println("Mouse Released: ("+e.getX()+", "+e.getY() +")");
            countselect();
            drag = false;
            x2 = e.getX();
            y2 = e.getY();
            switch(mm.getMode()){
                case "Slt":
                    offAllPorts();
                    selectedObj.clear();
                    for( Obj eachObj : objlist){
                        if(eachObj.inRectangle(x1,y1,x2,y2)||eachObj.inObj(x1, y1)){
                            eachObj.turnOnallPorts(true);
                            selectedObj.add(0, eachObj);
                            //break;
                        }else{
                            eachObj.turnOnallPorts(false);
                        }
                    }
                    // move
                    for( Obj eachObj : selectedObj){
                        if(eachObj.inObj(x1, y1)){
                            //System.out.println(x1+" "+y1+" ,"+x2+" "+y2);
                            eachObj.trans(x2-x1, y2-y1);
                            repaint();
                        }
                    }
                    break;
                case "Assl":
                    for( Obj eachObj : objlist){
                        if(eachObj.inObj(x2,y2)){
                            linePort2 = eachObj.GetPort(x2, y2);
                            if(linePort1!=(null)){
                                linelist.add(new LineAssc(linePort1, linePort2));
                            }
                            linePort1 = null;
                            linePort2 = null;
                        }
                    }
                    break;
                case "Genl":
                    for( Obj eachObj : objlist){
                        if(eachObj.inObj(x2,y2)){
                            linePort2 = eachObj.GetPort(x2, y2);
                            if(linePort1!=(null)){
                                linelist.add(new LineGene(linePort1, linePort2));
                            }
                            linePort1 = null;
                            linePort2 = null;
                        }
                    }
                    break;
                case "Coml":
                    for( Obj eachObj : objlist){
                        if(eachObj.inObj(x2,y2)){
                            linePort2 = eachObj.GetPort(x2, y2);
                            if(linePort1!=(null)){
                                linelist.add(new LineComp(linePort1, linePort2));
                            }
                            linePort1 = null;
                            linePort2 = null;
                        }
                    }
                    break;
            }
            repaint();
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        private void offAllPorts(){
            for( Obj eachObj : objlist){
                eachObj.turnOnallPorts(false);
            }
        }
     }

    public int countselect(){
        //System.out.println("count "+selectedObj.size());
        return selectedObj.size();
    }

    public void setObjName(String nameString){
        System.out.println("setObjName: "+selectedObj.size());
        selectedObj.get(0).setObjName(nameString);
        repaint();
    }

    public String getObjType(){
        return selectedObj.get(0).getClass().getName();
    }

    public void createGroup(){
        Group gp = new Group(selectedObj.get(0));
        objlist.remove(selectedObj.get(0));
        for(int i=1;i<selectedObj.size();i++){
            gp.addObj(selectedObj.get(i));
            objlist.remove(selectedObj.get(i));
        }
        objlist.add(gp);
    }

    public void unGroup(){
        Group gp = (Group)selectedObj.get(0);
        objlist.remove(gp);
        for(int i=0;i<gp.CountObjContainer();i++){
            objlist.add(0, gp.getGroupObj(i));
        }
        gp = null;
    }

    int abs(int num){
        if(num<0) return num*(-1);
        else return num;
    }
}
