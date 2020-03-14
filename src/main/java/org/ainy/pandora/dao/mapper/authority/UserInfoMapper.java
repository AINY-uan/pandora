package org.ainy.pandora.dao.mapper.authority;

import org.ainy.pandora.dao.mapper.BaseDaoMapper;
import org.ainy.pandora.entity.authority.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-03-31 10:53
 * @description User映射Mapper
 */
@Mapper
@Repository
public interface UserInfoMapper extends BaseDaoMapper {

    /**
     * 根据唯一键查询
     *
     * @param o 唯一键
     * @return 用户信息
     * @throws DataAccessException 数据访问异常
     */
    UserInfo selectByUniqueKey(Object o) throws DataAccessException;
}