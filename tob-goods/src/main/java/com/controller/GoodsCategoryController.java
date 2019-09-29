package com.controller;

import java.util.List;
import java.util.Map;

import com.pojo.CategoryResult;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import com.utils.mallQuery;
import com.vo.goodsCategoryVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.GoodsCategoryDO;
import com.service.GoodsCategoryService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:51:17
 */
 
@Controller
@RequestMapping("/biz/goodsCategory")
public class GoodsCategoryController {
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	
	@GetMapping()
	@RequiresPermissions("com:goodsCategory:goodsCategory")
	String GoodsCategory(){
	    return "com/goodsCategory/goodsCategory";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("com:goodsCategory:goodsCategory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        mallQuery query = new mallQuery(params);
		List<goodsCategoryVO> goodsCategoryList = goodsCategoryService.list(query);
		int total = goodsCategoryService.count(query);
		PageUtils pageUtils = new PageUtils(goodsCategoryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("com:goodsCategory:add")
	String add(){
	    return "com/goodsCategory/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("com:goodsCategory:edit")
	String edit(@PathVariable("cid") Integer cid,Model model){
		GoodsCategoryDO goodsCategory = goodsCategoryService.get(cid);
		model.addAttribute("goodsCategory", goodsCategory);
	    return "com/goodsCategory/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("com:goodsCategory:add")
	public R save(GoodsCategoryDO goodsCategory){
		if(goodsCategoryService.save(goodsCategory)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("com:goodsCategory:edit")
	public R update( GoodsCategoryDO goodsCategory){
		goodsCategoryService.update(goodsCategory);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("com:goodsCategory:remove")
	public R remove( Integer cid){
		if(goodsCategoryService.remove(cid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("com:goodsCategory:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] cids){
		goodsCategoryService.batchRemove(cids);
		return R.ok();
	}

	@GetMapping("/findAll")
	@ResponseBody
	public CategoryResult findAll(){
        CategoryResult result = goodsCategoryService.findAll();
        return result;
    }



	
}
