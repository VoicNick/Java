/**
 * Project: A00884836Lab8
 * File: Lab8.java
 * Date: Nov 8, 2016
 * Time: 8:24:52 PM
 */
package a00884836;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
public class Lab8 implements Runnable {

	Thread tortoise, hare;
	static int positionTortoise;
	static int positionHare;
	static boolean bothStepped;
	static boolean gameOver;
	static boolean printedLine;
	final static int RACE_STEPS = 100;
	static final String HARE_NAME = "Hare";
	static final String TORTOISE_NAME = "Tortoise";
	static int PAUSE_IN_MS = 200;

	/**
	 * 
	 */
	public Lab8() {
		bothStepped = true;
		gameOver = false;
		printedLine = false;
		tortoise = new Thread(this);
		tortoise.setName(TORTOISE_NAME);
		hare = new Thread(this);
		hare.setName(HARE_NAME);

		tortoise.start();
		hare.start();

	}

	/**
	 * Reports the race progress.
	 * With each iteration the accumulated sum for each thread is displayed.
	 * When one of the Threads reaches or surpasses 100 the program reports who
	 * the winner is and then stops.
	 */
	public synchronized void report() {
		bothStepped = !bothStepped;
		while (!bothStepped) {
			try {
				wait();
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}

		if (!printedLine) {
			System.out.format("%s: %d, %s: %d%n", TORTOISE_NAME, positionTortoise, HARE_NAME, positionHare);

			printedLine = true;

			if ((positionTortoise >= RACE_STEPS) && (positionHare >= RACE_STEPS)) {
				System.out.println("It was a tie, try again.");
				gameOver = true;
			} else {
				if (positionTortoise >= RACE_STEPS) {
					System.out.format("%s WINS!!!", TORTOISE_NAME);
					gameOver = true;
				}
				if (positionHare >= RACE_STEPS) {
					System.out.format("%s WINS!!!", HARE_NAME);
					gameOver = true;
				}
			}
		}

		notifyAll();

	}

	/**
	 * Random numbers between 1 and 5 are generated and accumulated here.
	 * Each thread should also be made to sleep so we can follow the program flow.
	 * Progress is displayed with each iteration by calling the report() method.
	 */
	public void run() {

		while (!gameOver) {	

			if (Thread.currentThread().getName().equalsIgnoreCase(TORTOISE_NAME)) {
				positionTortoise += (Math.random() * 4) + 1;
				try {
					Thread.sleep(PAUSE_IN_MS);
				} catch (InterruptedException e) {
				}
			}

			if (Thread.currentThread().getName().equalsIgnoreCase(HARE_NAME)) {
				positionHare += (Math.random() * 4) + 1;
				try {
					Thread.sleep(PAUSE_IN_MS);
				} catch (InterruptedException e) {
				}
			}
			printedLine = false;
			report();
		}

	}

	public static void main(String args[]) {

		new Lab8();
	}
}
