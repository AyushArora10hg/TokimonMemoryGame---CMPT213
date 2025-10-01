package cmpt213.asn4.bank;

/**
 * @author Ayush Arora <br><br>
 * <p>
 * The {@link BankAccount} class is an <b><u>abstract class</b></u> representing the basic
 * structure of a bank account. It has five fields with protected access: <br><br>
 * {@code balance} : a double field representing total money in the account <br>
 * {@code numOfDeposits} : an integer field holding the number of deposits made per month<br>
 * {@code numOfWithdrawals} : an integer field holding the number of withdrawals made per month<br>
 * {@code interestRate} : a double field representing the annual interest rate <br>
 * {@code serviceCharge} : a double field recording the monthly service charge associated with the account <br>
 * <p>
 * The class has five methods with a public access : <br><br>
 * {@code BankAccount()} : A non-default constructor that accepts two arguments for account balance
 * and annual interest rate. If any of these arguments is non-negative, the method throws an
 * IllegalArgumentException. It initializes the other fields with a default value of 0. <br>
 * {@code deposit()} : This method accepts an argument for amount to be deposited in the account.
 * The method throws an IllegalArgumentException for a non-positive argument. <br>
 * {@code withdraw()} : This method accepts an argument for amount to be withdrawn from the
 * account. If the amount is non-positive, or greater than the account balance, an exception is thrown. <br>
 * {@code calcInterest()} : This function computes the interest earned by the account holder and
 * adds it to the current balance. <br>
 * {@code monthlyProcess()} : This method deducts the service charge for the account activity, calculates
 * the interest earned, and finally resets numOfDeposits, numOfWithdrawals and seviceCharge to 0.
 */

public abstract class BankAccount {

    protected double balance;
    protected int numOfDeposits;
    protected int numOfWithdrawals;
    protected double interestRate;
    protected double serviceCharge;



    public BankAccount(double balance, double interestRate) {

        if (balance < 0) {
            throw new IllegalArgumentException("Balance can not be negative.");
        } else if (interestRate < 0) {
            throw new IllegalArgumentException("Annual interest rate can not be negative.");
        } else {
            this.balance = balance;
            this.interestRate = interestRate;
        }

        this.numOfDeposits = 0;
        this.numOfWithdrawals = 0;
        this.serviceCharge = 0;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative or zero.");
        } else {
            numOfDeposits++;
            balance += amount;
        }
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative or zero.");
        } else if (amount > balance) {
            throw new IllegalArgumentException("Withdrawal amount cannot be greater than balance.");
        } else {
            numOfWithdrawals++;
            balance -= amount;
        }
    }

    public final void calcInterest() {

        balance = balance + (balance * (interestRate / 12));
    }

    public void monthlyProcess() {

        balance -= serviceCharge;
        this.calcInterest();
        this.serviceCharge = 0;
        this.numOfDeposits = 0;
        this.numOfWithdrawals = 0;
    }

}
