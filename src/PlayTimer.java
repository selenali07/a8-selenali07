

public class PlayTimer extends Thread {
	private View view;
	private boolean start;
	private int delayValue;

	public PlayTimer(View view, int delayValue) {
		this.view = view;
		this.delayValue = delayValue;
		start = true;
	}

	public void halt() {
		start = false;
	}

	public void run() {
		while (start) {
			try {
				view.event(new StepEvent());
				Thread.sleep(delayValue);
				view.updateUI();
			} catch (InterruptedException e) {

			}
		}

	}
}
