package code;

import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;

public class FrontRightHandler implements ActionListener {
    private FinchController _finch;
    
    public FrontRightHandler(FinchController f){
        _finch = f;
     }
    
    public void actionPerformed(ActionEvent e){
        _finch.incrementRight();
    }
}