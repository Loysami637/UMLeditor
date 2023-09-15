package UI;

public class ModeManager {
    private String[] modelist =  {"Slt", "Assl", "Genl", "Coml", "Cls", "Usc"};
    private String curMode;

    public ModeManager(){
        curMode = modelist[0];
    }

    public String getMode(){
        return curMode;
    }
    public void changeMode(int i){
        curMode = modelist[i];
        System.out.println("Mode changed to "+curMode);
    }

}
