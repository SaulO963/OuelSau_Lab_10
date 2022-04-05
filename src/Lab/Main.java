/**
 * Name: Saul Ouellet
 * Date: Apr 5th 2022
 * Description: Program implements a multithreaded Cook that uses semaphores to synchronize
 * the threads. Each task begins its chore immediately or after being told it can start
 * by other signaling tasks. When ready to go, each task begins its chore, waits 2-11 seconds,
 * then finishes its chore, then signals the appropriate task
 */
package Lab;

/**
 * Main class
 */
public class Main {

    /**
     * Main runs single instance of a Cook
     * @param args supplies args to main
     */
    public static void main(String[] args){

        Cook c = new Cook();
        c.start();

    }

}
