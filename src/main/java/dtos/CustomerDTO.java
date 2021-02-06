package dtos;

import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerDTO {
    private long id;
    private String fullName;
    private String accountNumber;
    private double balance;

    public static List<CustomerDTO> getByList(List<BankCustomer> bankCustomers){
        return bankCustomers.stream()
            .map(bc -> new CustomerDTO(bc))
            .collect(Collectors.toList());
    }

    public CustomerDTO(BankCustomer bc) {
        this.id = bc.getId();
        this.fullName = bc.getFirstName() + " " + bc.getLastName();
        this.accountNumber = bc.getAccountNumber();
        this.balance = bc.getBalance();
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
            "id=" + id +
            ", fullName='" + fullName + '\'' +
            ", accountNumber='" + accountNumber + '\'' +
            ", balance=" + balance +
            '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
