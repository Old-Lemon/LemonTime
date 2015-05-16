package lemon.engine.time;

public class SystemTime implements Timable {
	private static final SystemTime instance;
	static{
		instance = new SystemTime();
	}
	private SystemTime(){}
	@Override
	public double getTime() {
		return System.currentTimeMillis();
	}
	@Override
	public double getTimeResolution() {
		return 1000;
	}
	public static SystemTime getInstance(){
		return instance;
	}
}
