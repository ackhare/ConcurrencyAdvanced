package Deadlock.NestedLocksDeadLock;

public class SimultaneousLocksDeadLock {
	public static void main(String[] args) {
        Business business = new Business();
 
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    business.foo();
                }
            }).start();
        }
 
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    business.bar();
                }
            }).start();
        }
    }
}
