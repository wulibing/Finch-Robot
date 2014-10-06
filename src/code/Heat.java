package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Heat implements ActionListener {
	
	private FinchController _finch;
	
	public Heat(FinchController f){
        _finch = f;
    }
    
    public void actionPerformed(ActionEvent e){
        _finch.feelHeat();
     }

}
