package Deadlock.DeadLockAnotherExample;

public class Bank {
	public static final int MAX_ACCOUNT = 10;
	public static final int MAX_AMOUNT = 10;
	public static final int INITIAL_BALANCE = 100;

	private Account[] accounts = new Account[MAX_ACCOUNT];

	public Bank() {
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = new Account(INITIAL_BALANCE);
		}
	}

	/*
	 * The transfer() method withdraws a specified amount from an account and
	 * deposit that amount to the target account. The transfer will be processed
	 * if and only if the source account has enough balance. And after the
	 * transfer has been done, a log message is printed to let us know the
	 * transaction details.
	 */
	public synchronized void transfer(int from, int to, int amount) {
		try {
			while (accounts[from].getBalance() < amount) {
				wait();
			}

			accounts[from].withdraw(amount);
			accounts[to].deposit(amount);

			String message = "%s transfered %d from %s to %s. Total balance: %d\n";
			String threadName = Thread.currentThread().getName();
			System.out.printf(message, threadName, amount, from, to, getTotalBalance());

			notifyAll();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * The getTotalBalance() method returns the total amount of all accounts,
	 * which must be always 1000. We check this number after every transaction
	 * to make sure that the program runs correctly(see line 34).
	 */
	public synchronized int getTotalBalance() {
		int total = 0;

		for (int i = 0; i < accounts.length; i++) {
			total += accounts[i].getBalance();
		}

		return total;
	}
}