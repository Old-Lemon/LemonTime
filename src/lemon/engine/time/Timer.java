package lemon.engine.time;

public class Timer {
	private Timable timable;
	private TimerControl timerControl;
	private double lastSecond;
	private double currentTime;
	private double lastFrame;
	private double timePassed;
	private int currentFps;
	private int fpsCounter;
	
	public Timer(Timable timable, TimerControl timerControl){
		this.timerControl = timerControl;
		this.timable = timable;
		lastSecond = -1;
		currentTime = -1;
		timePassed = 0;
		currentFps = 0;
		fpsCounter = 0;
	}
	public void sync(int fps){
		if(fps<0){
			throw new IllegalArgumentException("Target Fps Cannot Be Lower Than 0");
		}
		timePassed = timable.getTime()-lastFrame;
		lastFrame = timable.getTime();
		fpsCounter++;
		if(lastSecond<0){
			lastSecond = timable.getTime();
		}
		if(timable.getTime()>lastSecond+timable.getTimeResolution()){
			lastSecond+=timable.getTimeResolution();
			currentFps = fpsCounter;
			fpsCounter = 0;
		}
		if(fps!=0){
			if(currentTime<0){
				currentTime = timable.getTime();
			}
			double targetTime = currentTime+(timable.getTimeResolution()/fps);
			timerControl.sleep(timable, targetTime);
			currentTime = targetTime;
		}
	}
	public int getCurrentFps(){
		return currentFps;
	}
	public double getTimePassed(){
		return timePassed;
	}
}
