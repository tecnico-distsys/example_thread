package pt.tecnico.thread;

/** Main class. */
public class Main {

	/** The program starts executing in this method.  */
	public static void main(String[] args) {
		final String ME = Main.class.getSimpleName();

		try {
			System.out.println(ME + " starting...");

			// Create arguments for thread.
			Object argument = Integer.valueOf(22);

			// Create thread object.
			MyThread myThread = new MyThread(argument);
			// Set it as a daemon so the JVM does not wait for it when quitting.
			myThread.setDaemon(true);

			// Acquire lock on myThread object 
			// The argument object could also be used as the synchronization token.
			synchronized(myThread) {
				System.out.println(ME + " acquired lock");

				System.out.println(ME + " starting thread...");
				myThread.start();

				try {
					System.out.println(ME + " waiting for thread, lock released");
					myThread.wait(5*1000);
					System.out.println(ME + " after wait, lock reacquired");

					// The waiting thread should loop until the condition is satisfied.

					Exception exception = myThread.getException();
					if(exception != null) {
						System.out.println(ME + " received exception " + exception + " from thread");
					}

					Object result = myThread.getResult();
					if(result != null) {
						System.out.println(ME + " received result " + result + " from thread");
					}
				}
				catch (InterruptedException e) {
					// Thread was interrupted.
					throw e;
				}
			}
			System.out.println(ME + " released lock");

		} catch (Exception e) {
			// Print exception information.
			System.out.println(ME + " caught exception in main method: " + e);
		}

		System.out.println(ME + " finished.");
	}

}
