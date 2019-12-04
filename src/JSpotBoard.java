

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JPanel;

public class JSpotBoard extends JPanel implements SpotBoard {

	private static final int DEFAULT_SCREEN_WIDTH = 500;
	private static final int DEFAULT_SCREEN_HEIGHT = 500;
	private static final Color DEFAULT_BACKGROUND_LIGHT = new Color(0.6f, 0.6f, 0.6f);
	private static final Color DEFAULT_HIGHLIGHT_COLOR = new Color(0.5f, 0.5f, 0.5f);
	private static final Color DEFAULT_SPOT_COLOR = Color.BLACK;
	private Spot[][] _spots;

	public JSpotBoard(int width, int height) {
		if (width < 1 || height < 1 || width > 500 || height > 500) {
			throw new IllegalArgumentException("Illegal spot board geometry");
		}
		setLayout(new GridLayout(height, width));
		_spots = new Spot[width][height];

		Dimension preferred_size = new Dimension(DEFAULT_SCREEN_WIDTH / width, DEFAULT_SCREEN_HEIGHT / height);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				_spots[x][y] = new JSpot(DEFAULT_BACKGROUND_LIGHT, DEFAULT_SPOT_COLOR, DEFAULT_HIGHLIGHT_COLOR, this, x,
						y);
				((JSpot) _spots[x][y]).setPreferredSize(preferred_size);
				((JSpot) _spots[x][y]).highlightSpot();
				add(((JSpot) _spots[x][y]));
			}
		}
	}

	@Override
	public int getSpotWidth() {
		return _spots.length;
	}

	@Override
	public int getSpotHeight() {
		return _spots[0].length;
	}

	@Override
	public Spot getSpotAt(int x, int y) {
		if (x < 0 || x >= getSpotWidth() || y < 0 || y >= getSpotHeight()) {
			throw new IllegalArgumentException("Illegal spot coordinates: (" + x + ", " + y + ")");
		}

		return _spots[x][y];
	}

	@Override
	public void addSpotListener(SpotListener spot_listener) {
		for (int x = 0; x < getSpotWidth(); x++) {
			for (int y = 0; y < getSpotHeight(); y++) {
				_spots[x][y].addSpotListener(spot_listener);
			}
		}
	}

	@Override
	public void removeSpotListener(SpotListener spot_listener) {
		for (int x = 0; x < getSpotWidth(); x++) {
			for (int y = 0; y < getSpotHeight(); y++) {
				_spots[x][y].removeSpotListener(spot_listener);
			}
		}
	}

	@Override
	public Iterator<Spot> iterator() {
		return new SpotBoardIterator(this);
	}
}