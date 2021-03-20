package pt.tecnico.thread;

/** This is the thread class. 
 * 	A thread is an independent, parallel execution path.
 */
public class MyThread extends Thread {

	/** The result of the computation. */
	Object result;
	/** An exception to report, if necessary. */
	Exception exception;
	/** An input argument. */
	Object argument;

	/** Construct thread instance with specified argument. */
	public MyThread(Object argument) {
		this.argument = argument;
	}

	public Object getResult() {
		synchronized(this) {
			return this.result;
		}
	}

	public Exception getException() {
		synchronized(this) {
			return this.exception;
		}
	}

	public void run() {
		final String ME = this.getClass().getSimpleName();
		try {
			System.out.println(ME + " running...");

			// read argument (any object)
			Integer arg = (Integer) this.argument;

			// Do something...
			// (in this example, sleep)
			final int SLEEP_SECONDS = 10;
			System.out.println(ME + " sleeping " + SLEEP_SECONDS + " seconds...");
			sleep(SLEEP_SECONDS*1000);

			// return result (any object)
			this.result = Integer.valueOf(arg + 1);

			// Acquire lock and notify waiting thread
			synchronized(this) {
				System.out.println(ME + " acquired lock");

				System.out.println(ME + " notifying all waiting threads...");
				this.notifyAll();
			}
			System.out.println(ME + " released lock");

		} catch (Exception e) {
			System.out.println(ME + " caught exception: " + e.toString());
			this.exception = e;

		} finally {
			System.out.println(ME + " stopping.");
		}
	}

}
