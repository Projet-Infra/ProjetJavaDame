package model.core;

public class Timer implements Runnable{

	@Override
	public void run() {
        while (true) {
		
			Main.temps++;
			System.out.println(""+Main.temps);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();	
			}
			
        }
	}

}
