package UI;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// may import event library here, and implements ActionsListener

public class Buttons extends JButton implements ActionListener{

    public Buttons(String path, String name, int y){
        ImageIcon buttonIcon = creatImageIcon(path);
        String path2 = path.substring(0,path.length()-5)+'1'+path.substring(path.length()-4);
        ImageIcon pressedIcon = creatImageIcon(path2);
        this.setIcon(buttonIcon);
        this.setPressedIcon(pressedIcon);
        
        this.setName(name);
        this.setToolTipText(name);
        this.setBounds(0,y,60,60);
        this.addActionListener(this);
    }
    
    private static ImageIcon creatImageIcon(String path){
        java.net.URL imgUrl = Buttons.class.getResource(path);
        if(imgUrl != null){
            return new ImageIcon(imgUrl);
        }else{
            System.out.println("Couldn't find file: "+path);
            return null;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        ArrayList<Buttons> allbutton = new ArrayList<Buttons>();
        Object src = e.getSource();
        String bString = "Wrong";
        ModeManager mm;
        int bindex = 0;
        if(src instanceof JButton){
            bString = ((JButton)src).getName();
        }else{
            bString = "Wrong";
        }
        if(getTopLevelAncestor() instanceof MyFrame){
            allbutton = ((MyFrame)getTopLevelAncestor()).getButtonList();
        }else{
            System.out.println("Wrong Upper Level(setting ButtonList)");
        }
        
        for(int i=0;i<allbutton.size();i++){
            Buttons b  =  allbutton.get(i);
            if(b.getName().equals(bString)){
                b.setEnabled(false);
                bindex = i;
            }else{
                b.setEnabled(true);
            }
        }
        if(getTopLevelAncestor() instanceof MyFrame){
            mm = ((MyFrame)getTopLevelAncestor()).getModeManager();
            mm.changeMode(bindex);
        }else{
            System.out.println("Wrong Upper Level(setting ModeManager)");
        }
        //System.out.println(bString + " detected");
    }

}
