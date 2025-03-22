package com.example.mypetstore.persistence;

import com.example.mypetstore.domain.AccountManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountManageMapper {
    List<AccountManage> getAccountManageList();
    List<AccountManage> getAccountManageById(String userid);
    void addAccountManage(AccountManage account);
    void updateAccountManage(AccountManage account);
    void deleteAccountManage(String userid);
}
