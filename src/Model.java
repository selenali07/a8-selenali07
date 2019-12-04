

import java.util.Random;

import javax.swing.SwingUtilities;

public class Model {
	private double randomNum;
	private boolean[][] spotsArray;
	private int _size;
	private boolean torus;
	public Model(int size) {
		_size = size;
		this.spotsArray = new boolean[size][size];
		torus = false;
	}

	public boolean[][] getState() {
		return spotsArray;
	}

	public void clear() {
		spotsArray = new boolean[_size][_size];
	}

	public void random() {
		this.clear();
		randomNum = Math.random();
		Random r = new Random();
		for (int i = 0; i < _size * _size * randomNum; i++) {
			spotsArray[r.nextInt(_size)][r.nextInt(_size)] = true;
		}
	}
	public void step() {
		boolean[][] filledSpot = new boolean[_size][_size];

		for (int i = 0; i < _size; i++) {
			for (int j = 0; j < _size; j++) {
				int liveNeighborCount = getLiveNeighbors(i, j);

				if (spotsArray[i][j]) {
					if (liveNeighborCount < 2 || liveNeighborCount > 3) {
						filledSpot[i][j] = false;
					} else {
						filledSpot[i][j] = true;
					}
				} else {
					if (liveNeighborCount == 3) {
						filledSpot[i][j] = true;
					} else {
						filledSpot[i][j] = false;
					}
				}
			}
		}

		spotsArray = filledSpot;

	}

	public void togglePoint(int x, int y) {
		if (spotsArray[x][y]) {
			spotsArray[x][y] = false;
		} else {
			spotsArray[x][y] = true;
		}
	}

	private int getLiveNeighbors(int x, int y) {
		int liveNeighborCount = 0;
		int xm = x - 1;
		int xp = x + 1;
		int ym = y - 1;
		int yp = y + 1;

		if (xm >= 0) {
			if (ym >= 0 && spotsArray[xm][ym]) {
				liveNeighborCount++;
			}
			if (spotsArray[xm][y]) {
				liveNeighborCount++;
			}
			if (yp < _size && spotsArray[xm][yp]) {
				liveNeighborCount++;
			}
		}
		if (xp < _size) {
			if (ym >= 0 && spotsArray[xp][ym]) {
				liveNeighborCount++;
			}
			if (spotsArray[xp][y]) {
				liveNeighborCount++;
			}
			if (yp < _size && spotsArray[xp][yp]) {
				liveNeighborCount++;
			}
		}
		if (yp < _size && spotsArray[x][yp]) {
			liveNeighborCount++;
		}
		if (ym >= 0 && spotsArray[x][ym]) {
			liveNeighborCount++;
		}

		return liveNeighborCount;
	}
}