package org.ainy.pandora.dao.mapper.authority;

import org.ainy.pandora.dao.mapper.BaseDaoMapper;
import org.ainy.pandora.entity.authority.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoMapper extends BaseDaoMapper {

    UserInfo selectByUniqueKey(Object o) throws DataAccessException;
}