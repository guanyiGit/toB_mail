package com.service;

import java.util.List;
import java.util.Map;

import com.entities.TSubDefine;
import com.entities.TSubRefOrg;
import com.entities.TSubject;
import com.entities.Tree;
import com.utils.PageUtils;
import com.utils.R;
import com.vo.SubDefineVo;
import com.vo.SubVo;
import org.springframework.transaction.annotation.Transactional;

public interface SubjectService {
	/**
	 * 新增科目
	 * @param sub
	 * @return
	 */
	@Transactional
	int addSub(SubVo subVo);



//	
//	/**
//	 * 删除科目
//	 * @param id 科目主键
//	 * @return
//	 */
//	int delSub(Integer id);
//	
//	/**
//	 * 修改科目
//	 * @param sub
//	 * @return
//	 */
//	int editSub(TSubject sub);
//
	/**
	 * 科目列表
	 * @param pageNum,
	 * @param pageSize,
	 * @param searchKey
	 * @return
	 */
	PageUtils subList(Map<String, Object> map);


	List<TSubject> subListByOrgId(Integer orgId);


	int batchSaveSubRefOrg(List<TSubRefOrg> subRefOrgs);

	@Transactional
	R deleteById(Integer subId);

	List<Tree<TSubject>> initSubTree(Integer orgId, Integer subId);

	@Transactional
	int addSubDefine(SubDefineVo subDefineVo)throws Exception;

	/**
	 * 新增科目(改)
	 * @param subject
	 * @return
	 */
	@Transactional
	Integer addSub1(TSubject subject) throws Exception;

	@Transactional
	R deleteSubDefine(Integer subDefineId);


	List<TSubDefine> getSubDefines(Map<String,Object> map);

	List<TSubject> getSubs(Integer subDefineId);

	/**
	 * 获取最底层科目列表
	 * @param orgId
	 * @return
	 */
	List<TSubject> getButtomSubs(Integer orgId,String year,Integer type);
}
