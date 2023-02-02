package eu.deltasource.internship.abankingsystem.model;

import eu.deltasource.internship.abankingsystem.enums.Currency;

import java.time.LocalDate;
import java.util.*;

public class BankAccount {
    // the owner, iban, currency, amount available and its type. The type can be a
    // ‘current account’ or a ‘savings account’.

    // private final BankInstitution bankInstitution;

    private final Owner owner;

    private final String iban;

    private final Currency currency;

    private Double amountAvailable;

    private final char accountKey;

    private String typeAccount;

    private LinkedList<Transaction> transferStatement;

    private List<BankAccount> ownerList = new ArrayList<>();

    private static final ArrayList<BankAccount> accounts = new ArrayList<>();

    private static final List<String> existingIbans = new ArrayList<>();

    public List<BankAccount> getOwnerList() {
        return Collections.unmodifiableList(ownerList);
//        return ownerList;
    }

    public BankAccount(Owner owner, String iban, Currency currency, double amountAvailable, char accountKey) {
        if (existingIbans.contains(iban)) {
            throw new IllegalArgumentException("IBAN already exists");
        }
//        this.bankInstitution = bankInstitution;
        this.owner = owner;
        this.iban = iban;
        this.currency = currency;
        this.amountAvailable = amountAvailable;
        this.accountKey = accountKey;
        this.transferStatement = new LinkedList<>();
        accounts.add(this);
        existingIbans.add(iban);
//        if(checkIfOwnerExists()) {
//            // add customer to bank
//            bankInstitution.getCustomers().add(owner);
//        }
    }

    public static void ownerAccountCheck(Owner owner) {
        List<BankAccount> newListOwner = new ArrayList<>();
        for(var singleOwner : accounts) {
            if(singleOwner.getOwner().getName().equals(owner.getName())) {
//                owner.getBankAccounts().add(bankAccount);
                newListOwner.add(singleOwner);
            }
        }
        System.out.println(newListOwner);
    }

    public Owner getOwner() {
        return owner;
    }

    public String getIban() {
        return iban;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getAmountAvailable() {
        return amountAvailable;
    }

    public String getTypeAccount() {
        if(accountKey == 'C') {
            typeAccount = "Current account";
        } else {
            typeAccount = "Savings account";
        }
        return typeAccount;
    }

    public char getAccountKey() {
        return accountKey;
    }

    public static List<BankAccount> getAccounts() {
        return Collections.unmodifiableList(accounts);
//        return accounts;
    }

    public void setAmountAvailable(double amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public LinkedList<Transaction> getTransferStatement() {
        return transferStatement;
    }

    public void setTransferStatement(LinkedList<Transaction> transferStatement) {
        this.transferStatement = transferStatement;
    }

    public List<Transaction> getTransferStatementLocal(LocalDate startDate, LocalDate endDate) {
        LinkedList<Transaction> statements = new LinkedList<>();
        for(Transaction transaction : transferStatement) {
            LocalDate datesFromTransaction = transaction.getTimestamp();
            if (!datesFromTransaction.isBefore(startDate) && !datesFromTransaction.isAfter(endDate)) {
                statements.add(transaction);
            }
        }
        return Collections.unmodifiableList(statements);
    }

    @Override
    public String toString() {
        return String.format("%nBank Account Details: %n " +
                "Owner: %s %n " +
                "IBAN: %s %n " +
                "Currency: %s %n " +
                "Amount Available: %s %n " +
                "Account Key: %s %n " +
                "Account Type: %s %n " +
                "All Transactions for this account: %s %n",
                getOwner().getName(),
            getIban(),
            getCurrency(),
            getAmountAvailable(),
            getAccountKey(),
            getTypeAccount(),
            transferStatement
        );
    }
}
