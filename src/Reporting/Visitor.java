package Reporting;

import Banking.Account;
import Banking.DebitAccount;
import Banking.IAccount;

public interface Visitor {
    public abstract IAccount visit(Account account);
    public abstract IAccount visit(DebitAccount debitAccount);
}
