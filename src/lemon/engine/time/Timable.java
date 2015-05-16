package lemon.engine.time;

public interface Timable {
	public double getTime();
	/**
	 * 
	 * @return How many of '1's in a second.. Return 1000 if in milliseconds
	 */
	public double getTimeResolution();
}
