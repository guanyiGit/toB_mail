package com.service.impl;

import com.pojo.Category;
import com.pojo.CategoryResult;
import com.vo.goodsCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.dao.GoodsCategoryDao;
import com.entities.GoodsCategoryDO;
import com.service.GoodsCategoryService;



@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
	@Autowired
	private GoodsCategoryDao goodsCategoryDao;
	
	@Override
	public GoodsCategoryDO get(Integer cid){
		return goodsCategoryDao.get(cid);
	}
	
	@Override
	public List<goodsCategoryVO> list(Map<String, Object> map){
		return goodsCategoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return goodsCategoryDao.count(map);
	}
	
	@Override
	public int save(GoodsCategoryDO goodsCategory){
        goodsCategory.setCreateTime(new Date());
        goodsCategory.setUpdateTime(new Date());
		return goodsCategoryDao.save(goodsCategory);
	}
	
	@Override
	public int update(GoodsCategoryDO goodsCategory){
		return goodsCategoryDao.update(goodsCategory);
	}
	
	@Override
	public int remove(Integer cid){
		return goodsCategoryDao.remove(cid);
	}
	
	@Override
	public int batchRemove(Integer[] cids){
		return goodsCategoryDao.batchRemove(cids);
	}


	@Override
	public CategoryResult findAll() {
        CategoryResult result = new CategoryResult();
        result.setOptions(findGoodsCateGory(0));
		return result;
	}

	@Override
	public List<?> findGoodsCateGory(Integer parentId) {
        Map<String, Object> map= new HashMap<>();
        map.put("parentCid",parentId);
        List<GoodsCategoryDO> GoodsCategory = goodsCategoryDao.getlist(map);
        List<Category> dataList = new ArrayList<>();
        for (GoodsCategoryDO gc:GoodsCategory) {
            Category category = new Category();
            if(gc.getHasLeaf()==2){
                category.setValue(gc.getCid());
                category.setLabel(gc.getCName());
                List<?> goodsCateGory = findGoodsCateGory(gc.getCid());
                if(goodsCateGory.size()==0){
                    goodsCateGory=null;
                }
                category.setChildren(goodsCateGory);
                dataList.add(category);
            }else {
                category.setValue(gc.getCid());
                category.setLabel(gc.getCName());
                category.setChildren(null);
                dataList.add(category);
            }
        }
        return dataList;
    }

/*	public List<Tree<GoodsCategoryDO>> listToTreeList(List<GoodsCategoryDO> goodsCategorys) {
		List<Tree<GoodsCategoryDO>> trees = new ArrayList<>();
//		List<OrgInfo> orgInfos = sysDeptMapper.initOrgSelect(map);
		for (GoodsCategoryDO gc : goodsCategorys) {
			Tree<GoodsCategoryDO> tree = new Tree<>();
			tree.setId(gc.getCid().toString());
			tree.setParentId(gc.getParentCid().toString());
			tree.setText(gc.getCName());
			Map<String, Object> attributes = new HashMap<>(16);
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		return trees;
	}

	@Override
	public List<Tree<GoodsCategoryDO>> initGoodsCategory(Map<String,Object> map) {
        List<GoodsCategoryVO> list = goodsCategoryDao.list(map);
        List<Tree<GoodsCategoryDO>> trees = listToTreeList(list);
        List<Tree<GoodsCategoryDO>> treeList = BuildTree.buildList(trees, "0");
        return treeList;
	}*/

}
