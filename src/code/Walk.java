package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Walk implements ActionListener {
	
	private FinchController _finch;
	private Time _t;
	private Heat _h;
	
	
	public Walk(FinchController f){
        _finch = f;
    }
    
    public void actionPerformed(ActionEvent e){
        _finch.walkWalk();  
    }
}
