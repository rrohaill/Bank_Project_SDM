package Banking;

import java.util.List;

public class DeductTaxCommand implements Operations {

    private Double taxPercentage;
    private IAccount account;

    public DeductTaxCommand(Double taxPercentage, IAccount account) {
        this.taxPercentage = taxPercentage;
        this.account = account;
    }

    @Override
    public void execute(List<IAccount> accounts) throws Exception{
        for (IAccount acc : accounts) {
            if(acc.getID() == account.getID()) {
                acc.setBalance(account.getBalance() - (account.getBalance() * (taxPercentage/100)));
            }
        }
    }
}
