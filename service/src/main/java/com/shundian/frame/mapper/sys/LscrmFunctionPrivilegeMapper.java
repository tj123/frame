package com.shundian.frame.mapper.sys;

import com.shundian.frame.api.entity.sys.LscrmFunctionPrivilegeEntity;
import com.shundian.frame.api.entity.sys.LscrmFunctionPrivilegeTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by TJ on 2016/12/30.
 */
public interface LscrmFunctionPrivilegeMapper {


    List<LscrmFunctionPrivilegeTree> readAllPrivileges(@Param("subSystemId")int subSystemId);

    List<LscrmFunctionPrivilegeEntity> selectSubPrivileges(@Param("id")int id);

}
