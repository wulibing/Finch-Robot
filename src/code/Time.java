package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import edu.cmu.ri.createlab.terk.robot.finch.FinchInterface;

public class Time {
	FinchInterface _f;
	int _x;
	public Time(FinchInterface f){
		_f = f;
	}
	public void findX(){
		int x;
		Timer t = new Timer (100,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				_x = _x + 100;
			}
		});
		t.start();
		while(!_f.isObstacleLeftSide()&&!_f.isObstacleRightSide()){
			_f.setWheelVelocities(100, 100);
			if(_f.isObstacleLeftSide()&&!_f.isObstacleRightSide()){
				
			}
		}
		t.stop();
	}

}
