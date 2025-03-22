package com.example.mypetstore.persistence;

import com.example.mypetstore.domain.SignonManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SignonManageMapper {
    List<SignonManage> getSignonManageList();
    List<SignonManage> getSignonManageById(String username);
   void addSignonManage(SignonManage signonManage);
   void updateSignonManage(SignonManage signonManage);
   void deleteSignonManage(String username);
}
