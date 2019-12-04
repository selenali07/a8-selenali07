

abstract public class ViewEvent {


	public boolean isSizeEvent() {
		return false;
	}

	public boolean isStepEvent() {
		return false;
	}

	public boolean isClearEvent() {
		return false;
	}
	public boolean isStartEvent() {
		return false;
	}

	public boolean isRandomEvent() {
		return false;
	}
	

	public abstract char getEventType();
}
class StartEvent extends ViewEvent {

	public StartEvent() { }

	@Override
	public boolean isStartEvent() {
		return true;
	}

	public char getEventType() {
		return '1';
	}
}
class StepEvent extends ViewEvent {

	public StepEvent() { }

	@Override
	public boolean isStepEvent() {
		return true;
	}

	public char getEventType() {
		return '2';
	}
}

class ClearEvent extends ViewEvent {

	public ClearEvent() { }

	@Override
	public boolean isClearEvent() {
		return true;
	}

	public char getEventType() {
		return '3';
	}
}


class RandomEvent extends ViewEvent {

	public RandomEvent() { }

	@Override
	public boolean isRandomEvent() {
		return true;
	}

	public char getEventType() {
		return '4';
	}
}

