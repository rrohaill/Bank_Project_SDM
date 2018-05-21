package Banking;

import Interest.InterestManager;
import Reporting.Visitor;

import java.util.Date;


public class DebitAccount implements IAccount {

	public DebitAccount() {
		super();
	}

	public DebitAccount(Account account) {
		this.account = account;
	}

	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public IAccount accept(Visitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public void deposit(Double amount, IAccount account) {
		account.deposit(amount, account);
	}

	@Override
	public InterestManager getInteresState() {
		return null;
	}

	@Override
	public void setInteresState(InterestManager interesState) {

	}

	@Override
	public IAccount withdraw(Double amount, IAccount account) throws Exception {
		Double balance = account.getBalance();
		if (!balance.equals(0.0) && balance >= amount) {
			account.setBalance(balance-amount);
		}
		else if(account.getDebitLimit().equals(0.0)){
			throw new Exception("Your debit limit is exhausted");
		}
		else if (balance < amount) {
			Double toWithdrawfromDebit = amount-balance;
			Double newDebitLImit = account.getDebitLimit() - toWithdrawfromDebit;
			if(newDebitLImit<0.0) {
				throw new Exception("Your debit limit is not enought to proceed with this withdraw" +
						"you are trying to withdraw = " + amount + "" +
						"available balance = " + balance + "" +
						"debit limit = " + account.getDebitLimit());
			}
			account.setDebitLimit(newDebitLImit);
			//debitLimit -= toWithdrawfromDebit;
			
			Double toWithdrawfromAcc = amount - toWithdrawfromDebit;
			account.setBalance(balance - toWithdrawfromAcc);
			
		}
		return account;
		
	}

	@Override
	public int getID() {
		return account.getID();
	}

	@Override
	public void setID(int iD) {
		account.setID(iD);
	}

	@Override
	public String getOwner() {
		return account.getOwner();
	}

	@Override
	public void setOwner(String owner) {
		account.setOwner(owner);
	}

	@Override
	public Date getOpening_date() {
		return account.getOpening_date();
	}

	@Override
	public void setOpening_date(Date opening_date) {
		account.setOpening_date(opening_date);
	}

	@Override
	public Double getBalance() {
		return account.getBalance();
	}

	@Override
	public void setBalance(Double balance) {
		account.setBalance(balance);
	}

	@Override
	public Boolean getAccountType() {
		return account.getAccountType();
	}

	@Override
	public void setAccountType(Boolean accountType) {
		account.setAccountType(accountType);
	}

	@Override
	public Double getDebitLimit() {
		return account.getDebitLimit();
	}

	@Override
	public void setDebitLimit(Double debitLimit) {
		account.setDebitLimit(debitLimit);
	}

}
