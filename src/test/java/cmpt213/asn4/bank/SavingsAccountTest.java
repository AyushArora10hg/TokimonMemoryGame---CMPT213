package cmpt213.asn4.bank;

import static org.junit.Assert.*;

/**
 * @author Ayush Arora <br><br>

 * The {@link SavingsAccountTest} is a class that tests the methods of {@link SavingsAccount}
 * and {@link BankAccount} classes. It has 13 tests in total, which include general tests as
 * well as tests for special/edge cases. <br><br>

 * {@code constructorTest()} : This test tests the constructor of the SavingsAccount class.
 * A SavingsAccount with a positve balance and interestRate is constructed.<br>
 * {@code negBalConstructorTest()} : A SavingsAccount with a negative balance is constructed. The
 * <b>expected IllegealArgumentException</b> is handled using a try-catch block. If there is
 * no exception thrown by the {@code SavingsAccount()}, the test fails. <br>
 * {@code negInterestConstructorTest()} : A SavingsAccount with a negative interestRate
 * is constructed. The <b>expected IllegealArgumentException</b> is handled using a try-catch block.
 * If there is no exception thrown by the {@code SavingsAccount()}, the test fails. <br><br>

 * {@code regularDeposit()} : It tests the deposit method of the SavingsAccount class. It checks
 * if the balnce and numOfDeposits fileds update correctly upon a successful deposit.<br>
 * {@code negDeposit()} : It tests for a negative deposit into the account. The <b>expected
 * IllegalArgumentException</b> is handled using a try-catch. This test also checks for the
 * values of balnce and numOfDeposits.<br>
 * {@code inactiveDeposit()} : This test verifies if the status of an inactive SavingsAccount becomes
 * active after a deposit that increases the SavingsAccount balance above $25.<br><br>

 * {@code regularWithdraw()} : It tests the withdraw method of the SavingsAccount class. It ensures if
 * the balance, numofWithdrawals and isActive fields are correctly updated after a withdrawal.<br>
 * {@code negWithdraw()} : It tests for a negative withdrawal from the account. The <b>expected
 * IllegalArgumentException</b> is handled using a try-catch. This test also checks for the
 * values of balnce and numOfWithdrawals.<br>
 * {@code invalidWithdraw()} : It tests the method with a withdrawal amount greater than the account
 * balance. The <b>expected IllegalArgumentException</b> is handled using a try-catch.<br>
 * {@code inactiveWithdraw()} : It tests the withdraw method for withdrawal from an inavtive account.<br><br>

 * {@code monthlyProcessNoServiceFee()} : This test tests the monthlyProcess method of the SavingsAccount
 * class. A series of withdrawls (less than 4) and deposits is performed. The serviceCharge is 0 in this
 * case. At the end, all the fields are compared with their expected values.<br>
 * {@code monthlyProcessWithServiceFee()} : This test tests the monthlyProcess method of the SavingsAccount
 * class. A series of withdrawls (greater than 4) and deposits is performed. In this case, the serviceCharge
 *  is not 0.<br>
 */

public class SavingsAccountTest {

    // Constructor Tests
    @org.junit.Test
    public void constructorTest() {

        SavingsAccount myAccount = new SavingsAccount(698.17,0.0312);
        assertEquals(698.17, myAccount.balance, 0.0001);
        assertEquals(0.0312,myAccount.interestRate, 0.0001);
        assertEquals(0,myAccount.numOfWithdrawals, 0.0001);
        assertEquals(0,myAccount.numOfDeposits, 0.0001);
        assertEquals(0,myAccount.serviceCharge, 0.0001);

    }

    @org.junit.Test
    public void negBalConstructor() {

        try{
            new SavingsAccount(-578.99, 0.03);
            fail("Expected IllegalArgumentException for negative balance.");
        }catch (IllegalArgumentException e){
            System.out.println("'negBalConstructor' Test Pass: " + e.getMessage());
        }

    }

    @org.junit.Test
    public void negInterestConstructor() {

        try{
            new SavingsAccount(587.37, -0.03);
            fail("Expected IllegalArgumentException for negative interest rate.");
        }catch (IllegalArgumentException e){
            System.out.println("'negInterestConstructor' Test Pass: " + e.getMessage());
        }
    }

    // Deposit Method Tests
    @org.junit.Test
    public void regularDeposit() {

        SavingsAccount myAccount = new SavingsAccount(522.69,0.02);
        myAccount.deposit(200);
        assertEquals(722.69, myAccount.balance, 0.0001);
        assertEquals(1, myAccount.numOfDeposits, 0.0001);
    }

    @org.junit.Test
    public void negDeposit() {

        SavingsAccount myAccount = new SavingsAccount(100.69,0.02);
        myAccount.deposit(100);
        try{
            myAccount.deposit(-200);
            fail("Expected IllegalArgumentExpression for negative deposit amount");
        }catch (IllegalArgumentException e){
            assertEquals(200.69, myAccount.balance, 0.0001);
            assertEquals(1,myAccount.numOfDeposits, 0.0001);
            System.out.println("'negDeposit' Test Pass: " + e.getMessage());
        }
    }

    @org.junit.Test
    public void inactiveDeposit() {

        SavingsAccount myAccount = new SavingsAccount(10.08,0.02);

        myAccount.deposit(30);

        assertEquals(40.08, myAccount.balance, 0.0001);

        if (!myAccount.getStatus())
            fail("Account inactive for balance greater than 25.");

    }

    // Withdraw Method Tests
    @org.junit.Test
    public void regularWithdraw() {

        SavingsAccount myAccount = new SavingsAccount(40.96,0.05);
        myAccount.withdraw(10.37);
        myAccount.withdraw(10.37);

        assertEquals(20.22, myAccount.balance, 0.0001);
        assertEquals(2, myAccount.numOfWithdrawals, 0.0001);

        if (myAccount.getStatus())
            fail("Account not inactive for balance below 25");

    }

    @org.junit.Test
    public void negWithdraw() {

        SavingsAccount myAccount = new SavingsAccount(100.69,0.02);
        try{
            myAccount.withdraw(-200);
            fail("Expected IllegalArgumentExpression for negative withdrawal amount");
        }catch (IllegalArgumentException e){
            assertEquals(100.69, myAccount.balance, 0.0001);
            assertEquals(0,myAccount.numOfWithdrawals, 0.0001);
            System.out.println("'negWithdraw' Test Pass: " + e.getMessage());
        }
    }

    @org.junit.Test
    public void invalidWithdraw() {

        SavingsAccount myAccount = new SavingsAccount(100.69,0.02);
        try{
            myAccount.withdraw(200);
            fail("Expected IllegalArgumentExpression for withdrawal amount greater than balance");
        }catch (IllegalArgumentException e){
            assertEquals(100.69, myAccount.balance, 0.0001);
            assertEquals(0,myAccount.numOfWithdrawals, 0.0001);
            System.out.println("'invalidWithdraw' Test Pass: " + e.getMessage());
        }
    }

    @org.junit.Test
    public void inactiveWithdraw() {

        SavingsAccount myAccount = new SavingsAccount(20.67,0.05);
        try{
            myAccount.withdraw(10);
            fail("Expected IllegalArgumentExpression for withdrawal in inactive SavingsAccount.");
        }catch (IllegalArgumentException e){
            assertEquals(20.67, myAccount.balance, 0.0001);
            assertEquals(0, myAccount.numOfWithdrawals, 0.0001);
            System.out.println("'inactiveWithdraw' Test Pass: " + e.getMessage());
        }

    }

    // Calculate Interest Method Test
    @org.junit.Test
    public void calcInterest() {

        SavingsAccount myAccount = new SavingsAccount(100,0.12);
        myAccount.calcInterest();
        assertEquals(101, myAccount.balance, 0.0001 );
    }

    // Monthly Process Method Tests
    @org.junit.Test
    public void monthlyProcessNoServiceFee() {

        SavingsAccount myAccount = new SavingsAccount(500, 0.12);
        myAccount.withdraw(167);
        myAccount.deposit(167);
        myAccount.withdraw(100);

        myAccount.monthlyProcess();

        assertEquals(404, myAccount.balance, 0.0001);
        assertEquals(0, myAccount.numOfDeposits, 0.0001);
        assertEquals(0, myAccount.numOfWithdrawals, 0.0001);
        assertEquals(0, myAccount.serviceCharge , 0.0001);

        if (!myAccount.getStatus())
            fail("Account inactive for a balance above 25");
    }

    @org.junit.Test
    public void monthlyProcessWithServiceFee() {

        SavingsAccount myAccount = new SavingsAccount(41, 0.12);

        for (int i = 0 ; i < 14 ; i++){
            myAccount.withdraw(1);
        }

        myAccount.monthlyProcess();

        assertEquals(17.17, myAccount.balance, 0.0001);
        assertEquals(0, myAccount.numOfDeposits, 0.0001);
        assertEquals(0, myAccount.numOfWithdrawals, 0.0001);
        assertEquals(0, myAccount.serviceCharge , 0.0001);

        if (myAccount.getStatus())
            fail ("Accouunt active at a balance below 25");

    }
}