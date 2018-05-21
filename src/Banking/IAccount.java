package Banking;

import Interest.InterestManager;
import Reporting.Visitor;

import java.util.Date;


public interface IAccount {
	
	public IAccount withdraw(Double amount, IAccount account) throws Exception;
	public int getID();
	public void setID(int iD);
	public String getOwner();
	public void setOwner(String owner);
	public Date getOpening_date();
	public void setOpening_date(Date opening_date);
	public Double getBalance();
	public void setBalance(Double balance);

	public Boolean getAccountType();

	public void setAccountType(Boolean accountType);
	
	public Double getDebitLimit();

	public void setDebitLimit(Double debitLimit);

	public Account getAccount();

	public void setAccount(Account account);

    IAccount accept(Visitor visitor);

    void deposit(Double amount, IAccount account);

	public InterestManager getInteresState();

	public void setInteresState(InterestManager interesState);
}
