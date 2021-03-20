# Thread using Guarded Block example

This example shows a [Guarded Blocks](https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html) example in Java using wait-notify.

The main thread creates a worker thread and waits for it to complete or for a timeout.
The worker thread sleeps instead of doing actual work!

The following are interesting lines of code to change to try different executions:

`Main.java`:

```
    myThread.wait(5*1000);
    // This means that the main thread will wait 5 seconds, at most, for the other thread.
    // 0 means it will wait forever.
```

`MyThread.java`:

```
    sleep(10*1000);
    // This means that the worker thread sleeps 10 seconds.
```

`Main.java`:

```
    myThread.setDaemon(true);
    // This means that the worker thread is a daemon-
    // The JVM continues to work until there are no more non-daemon threads alive
    // i.e. if only daemon threads are alive, the JVM quits anyway.
```

## Maven instructions

To compile and package the source code:

```
mvn install
```

To run using exec plug-in:

```
mvn exec:java
```

NOTE: using the `exec` plug-in to run the code uses the same process as Maven, and the code behavior is modified by Maven's own threads and settings.
To test this program without interference from Maven, use the `appassembler` plugin.

To run using `appassembler` plug-in on **Linux**:

```
./target/appassembler/bin/thread
```

On **Windows**:

```
target\appassembler\bin\thread
```

## To configure the Maven project in Eclipse

'File', 'Import...', 'Maven'-'Existing Maven Projects'

'Select root directory' and 'Browse' to the project base folder.

Check that the desired POM is selected and 'Finish'.

----

[SD Faculty](mailto:leic-sod@disciplinas.tecnico.ulisboa.pt)
