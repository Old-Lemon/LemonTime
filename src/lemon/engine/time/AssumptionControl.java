package lemon.engine.time;

public class AssumptionControl implements TimerControl {
	private static final AssumptionControl instance;
	static{
		instance = new AssumptionControl();
	}
	private AssumptionControl(){}
	@Override
	public void sleep(Timable timable, double targetTime) {
		while(timable.getTime()+(timable.getTimeResolution()/1000)<targetTime){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while(timable.getTime()<targetTime){
			Thread.yield();
		}
	}
	public static AssumptionControl getInstance(){
		return instance;
	}
}
