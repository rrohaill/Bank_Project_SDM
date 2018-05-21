package Reporting;

import Banking.Account;
import Banking.DebitAccount;
import Banking.IAccount;

public class Over1000Report implements Visitor {
    @Override
    public IAccount visit(Account account) {
        if (account.getBalance() > 1000)
            return account;
        return null;
    }

    public IAccount visit(DebitAccount debitAccount) {
        if (debitAccount.getAccount().getDebitLimit() > 1000)
            return debitAccount;
        return null;
    }
}
