package UI;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.SelectableChannel;

import javax.swing.*;

public class MyFrame extends JFrame{
    private ArrayList<Buttons> buttonlist = new ArrayList<Buttons>();
    private ModeManager manager;
    private static MyPanel panel;

    public MyFrame(){
        this.setTitle("UML Editor");
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cpanel = this.getContentPane();
        //cpanel.setBackground(Color.BLACK);
        cpanel.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        addMenuBar(menuBar,"File");
        addMenuBar(menuBar,"Edit","Group","Ungroup","Change Object Name");
        
        addButtonToArrlist();
        for( Buttons mybutton: buttonlist){
            cpanel.add(mybutton);
        }

        manager = new ModeManager();
        
        panel = new MyPanel(this);
        cpanel.add(panel);
        
        this.setFocusable(true);
        this.setVisible(true);
    }
    
    private static void addMenuBar(JMenuBar bar,String... nameString){
        JMenu menu = new JMenu(nameString[0]);
        bar.add(menu);
        for(int i=1 ; i<nameString.length ; i++){
            String subMenuName = nameString[i];
            JMenuItem menuItem = new JMenuItem(subMenuName);
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(subMenuName.equals("Group")){
                        if(panel.countselect()>1){
                            panel.createGroup();
                            System.out.println("Group!");
                        }else{
                            System.out.println("Please select more objects.");
                        }
                    }else if(subMenuName.equals("Ungroup")){
                        System.out.println("Ungroup");
                        if(panel.countselect()==1 || 
                            panel.getObjType().equals("Elements.Group")){
                                panel.unGroup();
                        }else{
                            System.out.println("Please select Group");
                        }

                    }else if(subMenuName.equals("Change Object Name")){
                        if(panel.countselect()==1){
                            if(panel.getObjType().equals("Elements.Group")){
                                System.out.println("Cannot rename Group.");
                            }else{
                                OptionPane op = new OptionPane();
                                String objNewName = op.getString();
                                panel.setObjName(objNewName);
                            }
                        }else{
                            System.out.println("Too many objects seleted.");
                        }
                    }
                }
            });
            menu.add(menuItem);
        }
    }

    private void addButtonToArrlist(){
        Buttons b;
        b = new Buttons("image/select0.png","Select",0);
        buttonlist.add(b);
        b = new Buttons("image/assoc0.png","Association Line",60);
        buttonlist.add(b);
        b = new Buttons("image/gene0.png","Generalization Line",120);
        buttonlist.add(b);
        b = new Buttons("image/compo0.png","Composition Line",180);
        buttonlist.add(b);
        b = new Buttons("image/class0.png","Class",240);
        buttonlist.add(b);
        b = new Buttons("image/ucase0.png","User Case",300);
        buttonlist.add(b);
    }
    public ArrayList<Buttons> getButtonList(){
        return buttonlist;
    }
    
    public ModeManager getModeManager(){
        return manager;
    }


}