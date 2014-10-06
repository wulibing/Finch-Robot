package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontLeftHandler implements ActionListener {
    
    private FinchController _finch;
    
    public FrontLeftHandler(FinchController f){
         _finch = f;
    }
    
    public void actionPerformed(ActionEvent e){
        _finch.incrementLeft();
    }

}