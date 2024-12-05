package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.util.List;

@Dao
public interface AccountDAO {
    @Insert
    void insertAccount(Account account);

    @Query("SELECT * FROM Account")
    List<Account> getAllAccounts();

    @Query("SELECT * FROM Account WHERE account_id = :id")
    Account getAccountById(int id);

}
