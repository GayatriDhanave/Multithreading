package com.inmemorycache;

import com.entity.BankAccount;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    Map<Long, BankAccount> map = new HashMap<Long, BankAccount>();

    public BankAccount getBankAccount(long accNumber) {
        if(map.containsKey(accNumber)){
            return  map.get(accNumber);
        }
        return  null;
    }

    public void save (BankAccount account) {
        map.put(account.getAccNumber(), account);
    }
}
