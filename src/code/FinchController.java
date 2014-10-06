package code;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class FinchController {
    
    private MockFinch _f;
    private JLabel _left;
    private JLabel _right;
    private int _leftSpeed;
    private int _rightSpeed;
    
    
    public FinchController(MockFinch f){
        
         _f = f;
        _leftSpeed = 0;
        _rightSpeed = 0;
        
        JFrame frame = new JFrame();
        JButton increaseLeft = new JButton("+5");
        JButton increaseRight = new JButton("+5");
        JButton decreaseLeft = new JButton("-5");
        JButton decreaseRight = new JButton("-5");
        JButton walk = new JButton("Walking the walk");
        JButton light = new JButton("Seeing the Light");
        JButton heat = new JButton("Feeling the Heat");
        
        _left = new JLabel("L:0");
        _right = new JLabel("R:0");
         
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2));
        
        increaseLeft.addActionListener(new FrontLeftHandler(this));
        increaseRight.addActionListener(new FrontRightHandler(this));
        decreaseLeft.addActionListener(new BackLeftHandler(this));
        decreaseRight.addActionListener(new BackRightHandler(this));
        walk.addActionListener(new Walk(this));
        light.addActionListener(new Light(this));
        heat.addActionListener(new Heat(this));
        
        panel.add(increaseLeft);
        panel.add(increaseRight);
        panel.add(_left);
        panel.add(_right);
        panel.add(decreaseLeft);
        panel.add(decreaseRight);
        panel.add(walk);
        panel.add(light);
        panel.add(heat);
        
        frame.add(panel);
        
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public void incrementLeft(){
        if(_leftSpeed >= 255){
        }
        else{
        _leftSpeed+=5;
         _left.setText("L: "+_leftSpeed);
        _f.setWheelVelocities(_leftSpeed, _rightSpeed);
        }
    
    }
    
    public void incrementRight(){
        if(_rightSpeed >= 255){
         }
        else{
        _rightSpeed+=5;
        _right.setText("R: "+_rightSpeed);
        _f.setWheelVelocities(_leftSpeed, _rightSpeed);
        }
    
    }

    public void decrementLeft(){
         if(_leftSpeed <= -255){
        }
        else{
        _leftSpeed-=5;
        _left.setText("L: "+_leftSpeed);
        _f.setWheelVelocities(_leftSpeed, _rightSpeed);
        }
    
     }
    
    public void decrementRight(){
        if(_rightSpeed <= -255){
        }
        else{
        _rightSpeed-=5;
        _right.setText("R: "+_rightSpeed);
        _f.setWheelVelocities(_leftSpeed, _rightSpeed);
         }
    }
    
    public void checkObstacle(){

        while(true){
            if(_f.isObstacleLeftSide()){
                _f.setLED(Color.RED);
                _f.setWheelVelocities(0,0);
             }
            else{
                if(_f.isObstacleRightSide()){
                    _f.setLED(Color.BLUE);
                    _f.setWheelVelocities(0,0);
                }
                else{
                     if(_f.isObstacleLeftSide()&&_f.isObstacleRightSide()){
                        _f.setLED(Color.MAGENTA);
                        _f.setWheelVelocities(0,0);
                    }
                     
                    else{
                         if(!_f.isObstacleLeftSide()&&!_f.isObstacleRightSide()){
                            _f.setLED(Color.GREEN);
                        }
                    }
                }
            }
        }
    }
    
    public void walkWalk() {
        int corner = 0;
        long timeA = 0;
        long timeB = 0;
        long timeC = 0;
        long timeD = 0;
        int timeduration1 = 0;
        int timeduration2 = 0;
        while (corner <= 4) {
            if (_f.isObstacleLeftSide() && _f.isObstacleRightSide()) {
                corner++;
                if (corner == 1) {
                    timeB = System.currentTimeMillis();
                 }
                else if (corner == 3) {
                    timeD = System.currentTimeMillis();
                }
                    _f.setWheelVelocities(-250, -250, 1000);
                    _leftSpeed = 0;
                    _rightSpeed = 250;
                    _f.setWheelVelocities(_leftSpeed, _rightSpeed,1000);
                }
                else if (corner == 2) {
                    timeC = System.currentTimeMillis();
                 }
                else if (corner==4){
                        timeduration1 =  (int) (timeB - timeA);
                        System.out.println(timeB - timeA);
                        System.out.println(timeduration1);
                         timeduration2 = (int) (timeD - timeC);
                        _f.setWheelVelocities(250, 250,timeduration2 - timeduration1);
                        _f.setWheelVelocities(0,0);
                        break;
                 }
            
            else if (_f.isObstacleLeftSide() ) {
                    _f.setWheelVelocities(-250, -250, 1000);
                    _leftSpeed = 0;
                    _rightSpeed = 250;
                     _f.setWheelVelocities(_leftSpeed, _rightSpeed,1000);
                
                timeA = System.currentTimeMillis();
            }
            else if (_f.isObstacleRightSide() ) {
                     _f.setWheelVelocities(-250, -250, 1000);
                    _leftSpeed = 0;
                    _rightSpeed = 250;
                    _f.setWheelVelocities(_leftSpeed, _rightSpeed,1000);
                    timeA = System.currentTimeMillis();
            }
            else if (!_f.isObstacleLeftSide() && !_f.isObstacleRightSide()) {
                _leftSpeed = 250;
                _rightSpeed = 250;
                 _f.setWheelVelocities(_leftSpeed, _rightSpeed);   
            }
        }
    }
    public void seeLight() {
        double sensorLeft =  0;
        double sensorRight =  0;
         boolean testing =true;
        while (testing) {
            sensorLeft =  _f.getLeftLightSensor();
            sensorRight =  _f.getRightLightSensor();
            System.out.println(sensorLeft);
            System.out.println(sensorRight);
             if((sensorLeft>230) &&(sensorRight>230)){
                    _f.setWheelVelocities(0, 0);
                    testing = false;
                }
            else if (_f.isObstacleLeftSide() && _f.isObstacleRightSide()) {
                 _f.setWheelVelocities(-250, -250, 500);
                _leftSpeed = 0;
                _rightSpeed = 250;
                _f.setWheelVelocities(_leftSpeed, _rightSpeed,1000);
            }
             
            else if (_f.isObstacleLeftSide() ) {
                    _f.setWheelVelocities(-250, -250, 1000);
                    _leftSpeed = 0;
                    _rightSpeed = 250;
                    _f.setWheelVelocities(_leftSpeed, _rightSpeed,1000);
             }
            else if (_f.isObstacleRightSide() ) {
                    _f.setWheelVelocities(-250, -250, 1000);
                    _leftSpeed = 0;
                    _rightSpeed = 250;
                    _f.setWheelVelocities(_leftSpeed, _rightSpeed,1000);
             }
            else if (!_f.isObstacleLeftSide() && !_f.isObstacleRightSide()) {
                _leftSpeed = 250;
                _rightSpeed = 250;
                _f.setWheelVelocities(_leftSpeed, _rightSpeed);
            }    
        }
    }
    
    public void feelHeat() {
        
        boolean testing = true;
        while (testing) {
             double tem=_f.getTemperature();
            System.out.println(tem);
            if(tem>27){
                testing=false;
                _f.setWheelVelocities(0, 0);
            }
            else if (_f.isObstacleLeftSide() && _f.isObstacleRightSide()) {
                 _f.setWheelVelocities(-250, -250, 500);
                _leftSpeed = 0;
                _rightSpeed = 250;
                _f.setWheelVelocities(_leftSpeed, _rightSpeed,1000);
            }
             
            else if (_f.isObstacleLeftSide() ) {
                    _f.setWheelVelocities(-250, -250, 1000);
                    _leftSpeed = 0;
                    _rightSpeed = 250;
                    _f.setWheelVelocities(_leftSpeed, _rightSpeed,1000);
             }
            else if (_f.isObstacleRightSide() ) {
                    _f.setWheelVelocities(-250, -250, 1000);
                    _leftSpeed = 0;
                    _rightSpeed = 250;
                    _f.setWheelVelocities(_leftSpeed, _rightSpeed,1000);
             }
            
            
            else if (!_f.isObstacleLeftSide() && !_f.isObstacleRightSide()) {
                _leftSpeed = 250;
                _rightSpeed = 250;
                _f.setWheelVelocities(_leftSpeed, _rightSpeed); 
            }
        }
    }
}

