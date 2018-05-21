package Reporting;

import Banking.IAccount;

public interface Element {
    public IAccount accept(Visitor visitor);
}
