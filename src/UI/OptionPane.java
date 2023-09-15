package UI;

import javax.swing.*;

public class OptionPane {
    private String name;
    public OptionPane(){  
        JFrame f = new JFrame();   
        name=JOptionPane.showInputDialog(f,"New Objname");
    }
    public String getString(){
        return name;
    }
}
