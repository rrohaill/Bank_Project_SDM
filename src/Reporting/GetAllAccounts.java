package Reporting;

import Banking.Account;
import Banking.DebitAccount;
import Banking.IAccount;

public class GetAllAccounts implements Visitor {
    @Override
    public IAccount visit(Account account) {
        return account;
    }

    @Override
    public IAccount visit(DebitAccount debitAccount) {
        return debitAccount.getAccount();
    }
}
