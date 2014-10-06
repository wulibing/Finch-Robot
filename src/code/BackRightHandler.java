package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackRightHandler implements ActionListener {

private FinchController _finch;
     
    public BackRightHandler(FinchController f){
        _finch = f;
    }
    
    public void actionPerformed(ActionEvent e){
        _finch.decrementRight();
    }
}
