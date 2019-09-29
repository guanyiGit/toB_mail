package com.dao.details;

import com.entities.TOtherFee;
import com.entities.TOtherFeeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TOtherFeeMapper {
    int countByExample(TOtherFeeExample example);

    int deleteByExample(TOtherFeeExample example);

    int deleteByPrimaryKey(Long feeId);

    int insert(TOtherFee record);

    int insertSelective(TOtherFee record);

    List<TOtherFee> selectByExample(TOtherFeeExample example);

    TOtherFee selectByPrimaryKey(Long feeId);

    int updateByExampleSelective(@Param("record") TOtherFee record, @Param("example") TOtherFeeExample example);

    int updateByExample(@Param("record") TOtherFee record, @Param("example") TOtherFeeExample example);

    int updateByPrimaryKeySelective(TOtherFee record);

    int updateByPrimaryKey(TOtherFee record);
}