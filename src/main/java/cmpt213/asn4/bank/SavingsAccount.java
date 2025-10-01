package cmpt213.asn4.bank;

/**
 * @author Ayush Arora <br><br>
 * <p>
 * The {@link SavingsAccount} class <b><u>extends</b></u> the {@link BankAccount} class. It has
 * one additional parameter; <br><br>
 * {@code isActive} : a boolean field determining the status of an account. If the account balance
 * falls below $25, the account becomes inactive. <br><br>
 * This class overrides the following three methods of its super class: <br><br>
 * {@code withdraw()} : This method calls the super class version of this method after validating
 * the account status of being active. In case of account being inactive, the method throws an
 * IllegalArgumentException. The method also verifies the account status post withdrawal.<br>
 * {@code deposit} : This method also calls its super class version. If an account was inactive at
 * the time of deposit, this method checks whether a deposit has brought the account balance over $25. <br>
 * {@code monthlyProcess()} : This method computes the service charge entitled to the account holder
 * by checking the number of withdrawals made by them. It then calls its super class version and
 * at the end, verifies the account status of being active or inactive.
 */

public class SavingsAccount extends BankAccount {

    private boolean isActive;

    public SavingsAccount(double balance, double interestRate) {
        super(balance, interestRate);
        if (balance >= 25)
            isActive = true;
    }

    @Override
    public void withdraw(double amount) {

        if (!isActive)
            throw new IllegalArgumentException("This account is not active. Withdrawals not permitted.");
        else {
            super.withdraw(amount);
            if (balance < 25)
                isActive = false;
        }
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        if (!isActive && balance >= 25)
            isActive = true;
    }

    @Override
    public void monthlyProcess() {

        if (numOfWithdrawals > 4)
            serviceCharge += numOfWithdrawals - 4;

        super.monthlyProcess();

        if (balance < 25)
            isActive = false;

    }

    public boolean getStatus() {

        return isActive;
    }
}
