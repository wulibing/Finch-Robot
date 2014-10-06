package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackLeftHandler implements ActionListener {

private FinchController _finch;
    
    public BackLeftHandler(FinchController f){
        _finch = f;
    }
    
    public void actionPerformed(ActionEvent e){
        _finch.decrementLeft();
     }
}