import Banking.*;
import Interest.BasicInterest;
import Interest.SpecialInterest;
import MainPackage.MainClass;
import Reporting.Over1000Report;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Date;
import java.util.List;
//import static org.junit.jupiter.api


public class TestBank extends TestCase {

    //Tests for State Design Pattern
    public void testForInterestCalculationPass() {
        try {
            MainClass.prepareBanks();
            Bank bank = MainClass.getBank(2);
            Double interest = bank.calculateInterest(bank.getAccounts().get(0).getID());
            assertEquals(bank.getAccounts().get(0).getBalance() * 0.02, interest);

            interest = bank.calculateInterest(bank.getAccounts().get(1).getID());
            assertEquals(bank.getAccounts().get(1).getBalance() * 0.05, interest);
        } catch (Exception e) {
        }
    }

    @Test(expected = Exception.class)
    public void testForInterestCalculationFail() {
        MainClass.prepareBanks();
        Bank bank = MainClass.getBank(2);
        try {
            bank.calculateInterest(8);
        } catch (Exception e) {
        }
    }

    //Tests for Command Design Pattern
    public void testForTransferCmd() {
        MainClass.prepareBanks();
        Bank bank = MainClass.getBank(2);
        IAccount recAcc = bank.getAccounts().get(0);
        Double recBalance = recAcc.getBalance();
        IAccount senderAcc = bank.getAccounts().get(1);
        Double senderBalance = senderAcc.getBalance();
        try {
            TransferCommand transferCommand = new TransferCommand(recAcc, senderAcc, 500.0);
            bank.doOperation(transferCommand);
            assertEquals((recBalance + 500.00), recAcc.getBalance());
            assertEquals((senderBalance - 500.00), senderAcc.getBalance());
        } catch (Exception e) {
        }
    }

    public void testForDeductTaxCmd() {
        MainClass.prepareBanks();
        Bank bank = MainClass.getBank(2);
        IAccount acc = bank.getAccounts().get(0);
        Double balance = acc.getBalance();
        try {
            DeductTaxCommand deductTaxCommand = new DeductTaxCommand(5.0, acc);
            bank.doOperation(deductTaxCommand);
            assertEquals(balance - (balance * 0.05), acc.getBalance());
        } catch (Exception e) {
        }
    }

    /////////////////////////////////
    public void testOpenAccount() {

        Bank bankA = new Bank(1, "WBK");
        bankA.getAccounts().add(new Account(1, "Hamza", new Date(), 8500.00, false, new BasicInterest()));
        bankA.getAccounts().add(new Account(2, "Rohail", new Date(), 1200.00, false, new SpecialInterest()));
        bankA.getAccounts().add(new DebitAccount(new Account(3, "Omer", new Date(), 1500.00, true, new BasicInterest(), 500.00)));

        String name = "Faheem";
        Double balance = 900.0;
        assertTrue(bankA.openAccount(name, balance, false, new BasicInterest()));
    }

    public void testCreditAccount() {

        Bank bankA = new Bank(1, "WBK");
        bankA.getAccounts().add(new Account(1, "Hamza", new Date(), 8500.00, false, new BasicInterest()));
        bankA.getAccounts().add(new Account(2, "Rohail", new Date(), 1200.00, false, new SpecialInterest()));
        bankA.getAccounts().add(new DebitAccount(new Account(3, "Omer", new Date(), 1500.00, true, new BasicInterest(), 500.00)));


        String name = "Hamza";
        Double balance = 900.0;
        bankA.openAccount(name, balance, false, new BasicInterest());
        assertEquals(true, bankA.creditAccount(name, 900.0));
    }

    // Tests for decorator design pattern
    public void testWithdrawAccount() throws Exception {


        MainClass.prepareBanks();
        Bank bank = MainClass.getBank(1);
        IAccount acc = bank.getAccounts().get(1);
        double actualBalance = acc.getBalance();
        double amount = 500.0;

        bank.withdraw(acc.getID(), amount);

        assertEquals(actualBalance - amount, acc.getBalance());


    }

    public void testWithdrawAccountLimit() throws Exception {


        MainClass.prepareBanks();
        Bank bank = MainClass.getBank(1);
        IAccount acc = bank.getAccounts().get(2);
        double actualBalance = acc.getBalance();
        double amount = 2000.0;
        double debitLimit = acc.getDebitLimit();

        bank.withdraw(acc.getID(), amount);

        assertEquals((actualBalance - amount) + debitLimit, acc.getBalance());


    }
    /////////////////////////////////

    //Test for Visitor Design Pattern
    public void testOver1000Report() {
        MainClass.prepareBanks();
        Bank bank = MainClass.getBank(1);
        List<IAccount> resultList = bank.doReport(new Over1000Report());
        for (IAccount acc : resultList) {
            if (acc.getClass().equals(Account.class)) {
                assertTrue(acc.getBalance() > 1000.00);
            } else if (acc.getClass().equals(DebitAccount.class)) {
                assertTrue(acc.getDebitLimit() > 1000);
            }
        }
    }

    //Test for Mediator Design Pattern
    public void testForMediator() {
        MainClass.prepareBanks();
        Bank bankA = MainClass.getBank(1);
        Bank bankB = MainClass.getBank(2);

        IAccount recAcc = bankB.getAccounts().get(1);
        Double recBalance = recAcc.getBalance();
        IAccount senderAcc = bankA.getAccounts().get(0);
        Double senderBalance = senderAcc.getBalance();
        Double amount = 500.00;
        try {

            //First deduct money from sender's account
            bankA.withdraw(senderAcc.getID(), amount);

            //then forward call to moderator with bank name, acc no and amount to add money to that account
            try {
                MainClass.banksMediator.eft(bankB.getName(), bankB.getAccounts().get(1).getID(), amount);
            } catch (Exception e) {
                bankA.deposit(senderAcc.getID(), amount);
                throw e;
            }

            assertEquals((recBalance + 500.00), recAcc.getBalance());
            assertEquals((senderBalance - 500.00), senderAcc.getBalance());
        } catch (Exception e) {
        }
    }

    public void testForMediatorFail() {
        MainClass.prepareBanks();
        Bank bankA = MainClass.getBank(1);
        Bank bankB = MainClass.getBank(2);

        IAccount recAcc = bankB.getAccounts().get(1);
        Double recBalance = recAcc.getBalance();
        IAccount senderAcc = bankA.getAccounts().get(0);
        Double senderBalance = senderAcc.getBalance();
        Double amount = 500.00;
        try {

            //First deduct money from sender's account
            bankA.withdraw(senderAcc.getID(), amount);

            //then forward call to moderator with bank name, acc no and amount to add money to that account
            try {
                MainClass.banksMediator.eft(bankB.getName(), 9, amount);
            } catch (Exception e) {
                bankA.deposit(senderAcc.getID(), amount);
                throw e;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            assertEquals((recBalance), recAcc.getBalance());
            assertEquals((senderBalance), senderAcc.getBalance());
        }
    }
}

