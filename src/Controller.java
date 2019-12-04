

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Controller implements SpotListener, ViewListener {

	private Model _model;
	private View _view;

	public Controller(View view, Model model) {
		_model = model;
		_view = view;

		view.addViewListener(this);
		view.getBoard().addSpotListener(this);
	}

	public void handleViewEvent(ViewEvent e) {

		switch (e.getEventType()) {
	//	case '1': 
	//		_model.start();
	//		break;
		case '2':
			_model.step();
			break;

		case '3':
			_model.clear();
			break;
		case '4': 
			_model.random();
			break;
		}

		_view.updateView(_model.getState());
	}

	@Override
	public void spotClicked(Spot spot) {
		spot.toggleSpot();
		_model.togglePoint(spot.getSpotX(), spot.getSpotY());
	}

	@Override
	public void spotEntered(Spot spot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spotExited(Spot spot) {
		// TODO Auto-generated method stub
		
	}
}