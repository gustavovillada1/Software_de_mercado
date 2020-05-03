package model;

import java.util.Observable;

public class MessaggeGerenteThread extends Observable implements Runnable{

	private int screenWidth;
	
	Thread hilo;
	public MessaggeGerenteThread() {
		this.screenWidth=900;

	}
	
	public void startThread() {
		hilo=new Thread(this);
		hilo.start();	
	}
	
	@Override
	public void run() {

		double times=2;
		int uno=1;
		double rec=0;
		while(uno==1){
			rec+=times;

		if(rec>screenWidth) {
			rec=11;
		}
		this.setChanged();
		this.notifyObservers(rec);
		this.clearChanged();
		System.out.println(rec+" "+times);
		
		}
		
	}

}
