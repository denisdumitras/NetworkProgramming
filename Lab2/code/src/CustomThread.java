import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomThread extends Thread {

    private List<CustomThread> threadsToBeExecutedBefore = new ArrayList();
    private boolean executed = false;
    private String name;

    public CustomThread(String name) {
        this.name = name;
    }

    public void setThreadsToBeExecutedBefore(CustomThread t) {
        this.threadsToBeExecutedBefore.add(t);
    }

    public void run() {
        if (this.threadsToBeExecutedBefore != null) {
            Iterator var1 = this.threadsToBeExecutedBefore.iterator();

            while(var1.hasNext()) {
                CustomThread CustomThread = (CustomThread) var1.next();
                if (!CustomThread.executed) {
                    synchronized(CustomThread) {
                        try {
                            CustomThread.wait();
                        } catch (InterruptedException var8) {
                            System.out.println("I wasn't done!");
                        }
                    }
                }
            }
        }
        System.out.println(this.name);
        this.executed = true;
        synchronized(this) {
            this.notifyAll();
        }
    }
}
