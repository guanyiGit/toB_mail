package com.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.dao.*;
import com.domain.OrgInfoDO;
import com.entities.*;
import com.util.SubUtils;
import com.utils.BuildTree;
import com.utils.R;
import com.vo.SubDefineVo;
import com.vo.SubVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.SubjectService;
import com.utils.PageUtils;
import com.utils.mallQuery;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.util.resources.LocaleData;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private TBudgetSubjectMapper buSubMapper;
	
	@Autowired
	private TSubjectMapper subMapper;
	
	@Autowired
	private TSubRefCategoryMapper scMapper;

	//新增科目
	@Autowired
	private TSubRefOrgMapper subRefOrgMapper;

	@Autowired
	private TSubRefCategoryMapper subRefCategoryMapper;


	@Autowired
	private TSubDefineMapper subDefineMapper;

	@Autowired
	private TOrgRefSubdefineMapper orgRefSubdefineMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public int addSub(SubVo subVo){
		try {
			TSubject subject = new TSubject();
			BeanUtils.copyProperties(subject,subVo);
			//插入科目
			int i = subMapper.insertSelective(subject);
			Integer orgId = subVo.getOrgId();
			//获取当前组织的所有下级组织
            R result = restTemplate.getForObject("http://tob-user/system/orgInfo/findOrgChild/{orgId}", R.class, orgId);
            if( Integer.parseInt(result.get("code").toString()) == 200){
                LinkedHashMap<String, Object> obj = (LinkedHashMap<String, Object>) result.get("obj");
                ArrayList<LinkedHashMap<String, Object>> orgInfoListMap = (ArrayList<LinkedHashMap<String, Object>>)obj.get("orgInfoList");
                ArrayList<OrgInfoDO> orgInfoList = new ArrayList<>();
                orgInfoListMap.forEach(item ->{
                    OrgInfoDO orgInfoDO = JSONObject.parseObject(JSONObject.toJSONString(item), OrgInfoDO.class);
                    orgInfoList.add(orgInfoDO);
                });
                ArrayList<TSubRefOrg> subRefOrgs = new ArrayList<>();
                orgInfoList.stream().forEach(item -> {
                    TSubRefOrg subRefOrg = new TSubRefOrg();
                    subRefOrg.setOrgId(item.getId());
                    subRefOrg.setSubId(subject.getId());
                    subRefOrg.setCreateDate(new Date());
                    subRefOrgs.add(subRefOrg);
                });
                //批量添加组织与科目关联表
                subRefOrgMapper.batchSave(subRefOrgs);
                //添加科目与商品类型关联表
                List<Integer> goodsTypeIds = subVo.getGoodsTypeIds();

                ArrayList<TSubRefCategory> subRefCategorys = new ArrayList<>();
                goodsTypeIds.stream().forEach(item ->{
                    TSubRefCategory subRefCategory = new TSubRefCategory();
                    subRefCategory.setOrgId(orgId);
                    subRefCategory.setSubId(subject.getId());
                    subRefCategory.setcId(item);
                    subRefCategory.setCreateDate(new Date());
                    subRefCategorys.add(subRefCategory);
                });
                subRefCategoryMapper.add(subRefCategorys);
                return i;
            }
            return 0;
			//完成
		} catch (Exception e) {
			return 0;
		}
	}



	//	@Override
//	@Transactional
//	public int addSub(Map<String,Object>map) {
//		Integer orgId=(Integer) map.get("orgId");
//		TSubject sub=(TSubject) map.get("sub");
//		TBudgetSubject buSub=new TBudgetSubject();
//
//		//1.新增预算科目(关联表),返回自增id
//		buSubMapper.insertSelective(buSub);
//		int budSubId=buSub.getId();
//		//2.插入科目信息，返回自增id
//		sub.setBudSubId(budSubId);
//		int a=subMapper.insertSelective(sub);
//			Integer subId=sub.getId();
//			//3.更改所有组织表中的预算科目id
//				subMapper.updateOrg(budSubId);
//			//4.插入科目和商品类别关联表
//				//4.1获得商品类别集合
//				List<TSubRefCategory>list=(List<TSubRefCategory>) map.get("refList");
//					for(TSubRefCategory s:list) {
//						s.setOrgId(orgId);
//						s.setSubId(subId);
//					}
//					scMapper.add(list);
//		return a;
//	}

	@Override
	public PageUtils subList(Map<String, Object> map) {
		mallQuery query=new mallQuery(map);
			//1.页面信息
		List<TSubject>list=subMapper.list(query);
			//2.总记录数
		int total=subMapper.count(query);
		return new PageUtils(list, total);
	}
	
//	private List<Integer> getChildrenIds(List<Integer>idList){
//		idList = subMapper.findOrgIdList(idList);
//		if(idList.size()>0) {
//			initList.addAll(idList);
//			getChildrenIds(idList);
//		}
//		return idList;
//	}
	
//	/**
//	 * 删除科目
//	 * @param id 科目主键
//	 * @return
//	 */
//	@Override
//	public int delSub(Integer id) {
//		return 0;
//	}
//
//	/**
//	 * 修改科目
//	 * @param id 科目主键
//	 * @return
//	 */
//	@Override
//	public int editSub(TSubject sub) {
//		return 0;
//	}
//


	@Override
	public List<TSubject> subListByOrgId(Integer orgId) {
		List<TSubject> subjects = subMapper.subListByOrgId(orgId);
		return subjects;
	}

	@Override
	public int batchSaveSubRefOrg(List<TSubRefOrg> subRefOrgs) {
		int i = subRefOrgMapper.batchSave(subRefOrgs);
		return  i;
	}


	@Override
	public R deleteById(Integer subId) {
        try {
            //删除科目
            subMapper.deleteById(subId);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
	}

	/**
	 * 加载sub树列表，默认从pid==0加载
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Tree<TSubject>> initSubTree(Integer orgId,Integer subId)  {
		List<TSubject> subjects = subMapper.subListByOrgId(orgId);
		//获取当前科目下的所有子科目
		if (subId == null){
			subId = 0;
		}
		List<Tree<TSubject>> trees = null;
		try {
			List<TSubject> childSubs = SubUtils.getSubById(subjects, subId, new ArrayList<>());
			List<Tree<TSubject>> treeList = BuildTree.changeListToTreeList(childSubs);
			trees = BuildTree.buildList(treeList, subId.toString());
			return trees;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return  trees;
		}
	}


	@Override
	public int addSubDefine(SubDefineVo subDefineVo) throws Exception{
		TSubDefine subDefine = new TSubDefine();
		BeanUtils.copyProperties(subDefine,subDefineVo);
		subDefine.setCreateDate(new Date());
		int insert = subDefineMapper.insertSelective(subDefine);
		Integer orgId = subDefineVo.getOrgId();
		//获取当前组织的所有下级组织
		R result = restTemplate.getForObject("http://tob-user/system/orgInfo/findOrgChild/{orgId}", R.class, orgId);
		if( Integer.parseInt(result.get("code").toString()) == 200){
			LinkedHashMap<String, Object> obj = (LinkedHashMap<String, Object>) result.get("obj");
			ArrayList<LinkedHashMap<String, Object>> orgInfoListMap = (ArrayList<LinkedHashMap<String, Object>>)obj.get("orgInfoList");
			ArrayList<OrgInfoDO> orgInfoList = new ArrayList<>();
			orgInfoListMap.forEach(item ->{
				OrgInfoDO orgInfoDO = JSONObject.parseObject(JSONObject.toJSONString(item), OrgInfoDO.class);
				orgInfoList.add(orgInfoDO);
			});
			//批量添加orgRefSubDefine
			ArrayList<TOrgRefSubdefine> orgRefSubdefines = new ArrayList<>();
			orgInfoList.forEach(x -> {
				TOrgRefSubdefine tOrgRefSubdefine = new TOrgRefSubdefine();
				tOrgRefSubdefine.setOrgId(x.getId());
				tOrgRefSubdefine.setSubDefineId(subDefine.getId());
				tOrgRefSubdefine.setCreateTime(new Date());
				orgRefSubdefines.add(tOrgRefSubdefine);
			});
			orgRefSubdefineMapper.batchSave(orgRefSubdefines);
		}
		return insert;
	}

	@Override
	public Integer addSub1(TSubject subject) throws Exception{
		subject.setCreateDate(new Date());
		int insert = subMapper.insertSelective(subject);
		return insert;
	}


	@Override
	public R deleteSubDefine(Integer subDefineId) {
		try {
			//删除科目计划
			subDefineMapper.deleteByPrimaryKey(subDefineId);
			//删除组织与科目计划关联
			orgRefSubdefineMapper.deleteBySubDefineId(subDefineId);
			//删除科目计划对应科目
			subMapper.deleteBySubDefineId(subDefineId);

			return  R.ok("删除成功");
		}catch (Exception e){
			e.printStackTrace();
			return R.error("删除失败");
		}
	}

	@Override
	public List<TSubDefine> getSubDefines(Map<String,Object> map) {
		List<TSubDefine> subDefines = subDefineMapper.getSubDefines(map);
		return subDefines;
	}

	@Override
	public List<TSubject> getSubs(Integer subDefineId) {
		List<TSubject> subs = subMapper.getSubs(subDefineId);
		return subs;
	}


	@Override
	public List<TSubject> getButtomSubs(Integer orgId, String year,Integer type) {
		if (year == null){
			LocalDate now = LocalDate.now();
			year = now.getYear() + "";
		}
		List<TSubject> subsByOrgIdAndYear = subMapper.getSubsByOrgIdAndYear(orgId, year);
		if (subsByOrgIdAndYear.size() == 0 || subsByOrgIdAndYear == null){
			return subsByOrgIdAndYear;
		}
		List<TSubject> buttomSubs = SubUtils.getSubChildById(subsByOrgIdAndYear, 0, new ArrayList<>());
		if (type == null){
			return  buttomSubs;
		}
		List<TSubject> collect = buttomSubs.stream().filter(x -> {
			return x.getType() == type;
		}).collect(Collectors.toList());
		return collect;
	}
}
