import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Execute {

    public static void main(String[] args) {
        CustomThread t1 = new CustomThread("1");
        CustomThread t2 = new CustomThread("2");
        CustomThread t3 = new CustomThread("3");
        CustomThread t4 = new CustomThread("4");
        CustomThread t5 = new CustomThread("5");
        List<CustomThread> list = Arrays.asList(t1, t2, t3, t4, t5);

        t1.setThreadsToBeExecutedBefore(t2);
        t1.setThreadsToBeExecutedBefore(t3);
        t1.setThreadsToBeExecutedBefore(t4);
        t2.setThreadsToBeExecutedBefore(t5);
        t3.setThreadsToBeExecutedBefore(t5);
        t4.setThreadsToBeExecutedBefore(t5);

        list.forEach(CustomThread::start);
    }
}
