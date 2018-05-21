package Banking;

import Interest.InterestManager;
import Reporting.Visitor;

import java.util.Date;


public class Account implements IAccount {
	
	private int ID;
	private String owner;
	private Date opening_date;
	private Double balance;
	private Boolean accountType;
	private Double debitLimit;
	private InterestManager interesState;
	
	public Account(int iD, String owner, Date opening_date, Double balance, Boolean debit, InterestManager interestManager) {
		super();
		ID = iD;
		this.owner = owner;
		this.opening_date = opening_date;
		this.balance = balance;
		this.accountType = debit;
		this.interesState = interestManager;
	}

	public Account(int ID, String owner, Date opening_date, Double balance, Boolean accountType, InterestManager interestManager, Double debitLimit) {
		this.ID = ID;
		this.owner = owner;
		this.opening_date = opening_date;
		this.balance = balance;
		this.accountType = accountType;
		this.interesState = interestManager;
		this.debitLimit = debitLimit;
	}

	@Override
	public int getID() {
		return ID;
	}
	@Override
	public void setID(int iD) {
		ID = iD;
	}
	@Override
	public String getOwner() {
		return owner;
	}
	@Override
	public void setOwner(String owner) {
		this.owner = owner;
	}
	@Override
	public Date getOpening_date() {
		return opening_date;
	}
	@Override
	public void setOpening_date(Date opening_date) {
		this.opening_date = opening_date;
	}
	@Override
	public Double getBalance() { return balance;	}
	@Override
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@Override
	public Boolean getAccountType() {
		return accountType;
	}
	@Override
	public void setAccountType(Boolean accountType) {
		this.accountType = accountType;
	}

	@Override
	public Double getDebitLimit() { return debitLimit;	}

	@Override
	public void setDebitLimit(Double debitLimit) {	this.debitLimit = debitLimit;	}

	@Override
	public Account getAccount() {
		return this;
	}

	@Override
	public void setAccount(Account account) { this.getClass();	}

	@Override
	public InterestManager getInteresState() {
		return interesState;
	}

	@Override
	public void setInteresState(InterestManager interesState) {
		this.interesState = interesState;
	}

	@Override
	public IAccount withdraw(Double amount, IAccount account) throws Exception{
		if(account.getBalance().equals(0.0) || account.getBalance() < amount)
			throw new Exception("You don't have enough balance");
		account.setBalance(account.getBalance() - amount);
		//balance=balance-amount;
		return this;
	}

	public IAccount accept(Visitor visitor) {

		return visitor.visit(this);
	}

	@Override
	public void deposit(Double amount, IAccount account) {

		account.setBalance(account.getBalance() + amount);
	}
}
