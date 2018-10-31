package ReentrantLock;

public class Transaction implements Runnable {
	private Bank bank;
	private int fromAccount;

	public Transaction(Bank bank, int fromAccount) {
		this.bank = bank;

		this.fromAccount = fromAccount;
	}
/*

The source account is passed from the constructor and the target account is chosen randomly, 
and both source and target cannot be the same.

 Also the amount to be transferred is chosen randomly 
but always less than 10. 

After the transaction has been done, the current thread goes to sleep for a very 
short time 
(50 milliseconds), and then it continues doing the same steps repeatedly until the thread is terminated.
 */
	public void run() {

		while (true) {
			int toAccount = (int) (Math.random() * Bank.MAX_ACCOUNT);

			if (toAccount == fromAccount)
				continue;

			int amount = (int) (Math.random() * Bank.MAX_AMOUNT);

			if (amount == 0)
				continue;

			bank.transfer(fromAccount, toAccount, amount);

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
