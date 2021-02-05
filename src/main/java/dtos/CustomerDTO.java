/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {
    private long id;
    private String fullName;
    private String accountNumber;
    private double balance;

    public static List<CustomerDTO> getDtos(List<BankCustomer> bankCustomers){
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        bankCustomers.forEach(bc -> customerDTOS.add(new CustomerDTO(bc)));
        return customerDTOS;
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
