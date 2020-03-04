/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krohne
 */

public class MyCircle {
    
    private double radius;
    
    public MyCircle(int radius){
        this.radius = radius;
    }
    
    public String toString(){
        return radius+"";
    }
    
    public double getRadius(){
        return radius;
    }
    
	public boolean equals(Object other){
		MyCircle x = (MyCircle)other;
		if(radius == x.radius){
			return true;
		}
		return false;
	}
}

