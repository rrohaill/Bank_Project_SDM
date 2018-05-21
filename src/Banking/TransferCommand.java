package Banking;

import java.util.List;

public class TransferCommand implements Operations{

	private IAccount toAccount;
	private IAccount fromAccount;
	private Double amount;

	public TransferCommand(IAccount toAccount, IAccount fromAccount, Double amount) {
		super();
		this.toAccount = toAccount;
		this.fromAccount = fromAccount;
		this.amount = amount;
	}

	@Override
	public void execute(List<IAccount> accounts) throws Exception{

		if (fromAccount.getBalance()<amount) {
			throw  new Exception("Insufficient sender balance");
		}

		for (IAccount acc : accounts) {
			if(acc.getID() == fromAccount.getID()) {
				acc.setBalance(acc.getBalance() - amount);
			}
			else if (acc.getID() == toAccount.getID()) {
				acc.setBalance(acc.getBalance() + amount);
			}
		}
		
	}
}
