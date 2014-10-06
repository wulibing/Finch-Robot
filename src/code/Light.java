package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Light implements ActionListener {
	
	private FinchController _finch;
	
	public Light(FinchController f){
        _finch = f;
    }
    
    public void actionPerformed(ActionEvent e){
        _finch.seeLight();
     }
	
	

}
