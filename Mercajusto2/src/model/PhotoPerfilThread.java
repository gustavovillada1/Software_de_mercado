package model;


import java.io.Serializable;
import java.util.Observable;

public class PhotoPerfilThread extends Observable implements Runnable,Serializable{

	
	public void startThread() {
		Thread hilo=new Thread(this);
		hilo.start();	
	}
	
	@Override
	public void run() {

		double rotation=10;
		int uno=1;
		double rec=0;
		while(uno==1){
			rec+=rotation;

		this.setChanged();
		this.notifyObservers(rec);
		this.clearChanged();
		
		}
		
	}

}

