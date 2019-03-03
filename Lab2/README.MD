# Laboratory Work Nr. 2

### Objectives
 - Learn about concurrency and multi-threading;
 - Learn what a thread is along with its properties, states;
 - Learn what are the means of communications between threads;
 - Learn how is it possible to synchronize the threads;
 - Use the gained knowledge in order to create a simple concurrent and multi-threading program.

### Task
 * According to the oriented graph which is presented below. Create a program that would model the activities as they are represented. The events/activites are marked by an orage rectangular while the arrows show us the dependecies between them. 1 -> 2 means that the activity 1 is dependent on the acitivity 2. In other words the activity 2 can not take place until the activity 1 is done.

![Variant](https://github.com/denisdumitras/NetworkProgramming/blob/master/Lab2/screens/varianta.png)

### Implementation
After variant analisys, i concluded that I have 5 events with the following connection between them, 1st event is dependent on 2, 3 and 4th event while 2, 3 and 4th events are dependent on 5th event.

I created a class called 'CustomThread' that extends Thread class

I have represented each event by a thread which will print its name. Each object of thea earilier mentioned class has 3 fields. 'threadsToBeExecutedBefore' is an array of other objects of this type and is used to set up the dependencies between the threads. For the threads with no dependencies the earlier mentioned array will be empty. _executed_ is the next one it shows either the thread was executed or not. Initially it is set to false and after the execution happens it is set to true. The last is 'name' it is obviouse for what I need it.

After all the threads are created the dependecies should be added. It can be done by calling the 'setThreadsToBeExecutedBefore' on an object of 'CustomThread' type. It will take as an argument an object of 'CustomThread' type and will add it to the array mentioned above.

Next I start the threads. This means that the code written in the 'run' method will execute. Because there is no way of controlling the exectuion order of the threads a check is performed before the desired result occurs. By desired result I reffer to printing of the thread's name. First it checks if the array of the threads to be executed before is empty. In case that it is empty the execution of that thread will happen. After the printing is done the variable which shows the status of thread is set to true. And also the call to the 'notifyAll' method is executed. By calling it all the threads which are waiting on this thread are notified and further they will execute. In case that the array is not empty an additional check will happen. For every thread which is presented in that array its status will be checked. If there is at least one thread which have its executed status set to false, the running thread will be set to the waiting stage until the desired thread will send a notification.

Below are presented 2 versions of outputs.

![Variant](https://github.com/denisdumitras/NetworkProgramming/blob/master/Lab2/screens/screen1.png)

![Variant](https://github.com/denisdumitras/NetworkProgramming/blob/master/Lab2/screens/screen2.png)
