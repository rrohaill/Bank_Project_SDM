package Mediator;

import Banking.Bank;

import java.util.ArrayList;
import java.util.List;

public class BanksMediator {
    private List<Bank> bankList = new ArrayList<>();

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

    public void eft(String bankName, int toAccNo, Double amount) throws Exception {
        for (Bank bank: bankList ) {
            if(bank.getName().equalsIgnoreCase(bankName)) {
                bank.deposit(toAccNo, amount);
            }
        }
    }
}
