package lemon.engine.time;

import java.util.HashMap;
import java.util.Map;

public class MultiThreads {
	private static MultiThreads instance;
	private Map<Runnable, Boolean> threads;
	private Runnable mainRunnable;
	private Timable timable;
	private TimerControl timerControl;
	public MultiThreads(Timable timable, TimerControl timerControl){
		threads = new HashMap<Runnable, Boolean>();
		this.timable = timable;
		this.timerControl = timerControl;
	}
	public void add(final Runnable runnable){
		threads.put(new Runnable(){
			public void run() {
				runnable.run();
				threads.put(this, true);
			}
		}, false);
	}
	public void addAndRun(final Runnable runnable){
		Runnable temp = new Runnable(){
			public void run() {
				runnable.run();
				threads.put(this, true);
			}
		};
		threads.put(temp, false);
		new Thread(temp).start();
	}
	private boolean allDone(){
		for(Runnable runnable: threads.keySet()){
			if(threads.get(runnable)==false){
				return false;
			}
		}
		return true;
	}
	public void start(){
		for(Runnable runnable: threads.keySet()){
			new Thread(runnable).start();
		}
		mainRunnable.run();
		while(!allDone()){
			timerControl.sleep(timable, timable.getTime()+(timable.getTimeResolution()/1000D));
		}
	}
	public Runnable getMainRunnable() {
		return mainRunnable;
	}
	public void setMainRunnable(Runnable mainRunnable) {
		this.mainRunnable = mainRunnable;
	}
	public static void setInstance(MultiThreads instance){
		MultiThreads.instance = instance;
	}
	public static MultiThreads getInstance(){
		return instance;
	}
}
