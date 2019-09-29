package com.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import com.utils.mallQuery;
import com.vo.goodsDetailVO;
import com.vo.goodsVO;
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

import com.entities.GoodsDO;
import com.service.GoodsService;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 15:01:05
 */
 
@Controller
@RequestMapping("/biz/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@GetMapping()
	//@RequiresPermissions("com:goods:goods")
	String Goods(){
	    return "com/goods/goods";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("com:goods:goods")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		mallQuery query = new mallQuery(params);
		List<goodsVO> goodsList = goodsService.list(query);
		int total = goodsService.count(query);
		PageUtils pageUtils = new PageUtils(goodsList, total);
		return pageUtils;
	}


	@GetMapping("/findGoodsDetail")
	//@RequiresPermissions("com:goods:edit")
	@ResponseBody
    goodsDetailVO findGoodsDetail(Long goodsId){
        goodsDetailVO goodsDetail = goodsService.findGoodsDetail(goodsId);
        return goodsDetail;
	}

	
	@GetMapping("/add")
	@RequiresPermissions("com:goods:add")
	String add(){
	    return "com/goods/add";
	}

	@GetMapping("/edit")
	//@RequiresPermissions("com:goods:edit")
	@ResponseBody
	GoodsDO edit(Long goodsId){
		GoodsDO goods = goodsService.get(goodsId);
	    return goods;
	}





	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("com:goods:add")
	public R save(GoodsDO goods){
		if(goodsService.save(goods)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	//@RequiresPermissions("com:goods:edit")
	public R update( GoodsDO goods){
		goodsService.update(goods);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("com:goods:remove")
	public R remove( Long goodsId){
		if(goodsService.remove(goodsId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("com:goods:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] goodsIds){
		goodsService.batchRemove(goodsIds);
		return R.ok();
	}

	/**
	 * 根据组织查供应商
	 */
	@GetMapping( "/findSupplierByOrg")
	@ResponseBody
	public List<Map<String,Object>> findSupplierByOrg(Integer orgId){
        List<Map<String,Object>> map = goodsService.findSupplierByOrg(0);
        return map;
	}

    /**
     * 根据组织查科目
     */
    @GetMapping( "/findSubjectByOrg")
    @ResponseBody
    public List<Map<String,Object>> findSubjectByOrg(Integer orgId){
        List<Map<String,Object>> map = goodsService.findSubjectByOrg(orgId);
        return map;
    }

	/**
	 * 查组织
	 */
	@GetMapping( "/findOrgByPid")
	@ResponseBody
	public List<Map<String,Object>> findOrgByPid(){
		List<Map<String,Object>> map = goodsService.findOrgByPid();
		return map;
	}

    /**
     * 上架
     */
    @ResponseBody
    @RequestMapping("/putaway")
    public R putaway( GoodsDO goods){
        goods.setListtingTime(new Date());
        goodsService.update(goods);
        return R.ok();
    }

    /**
     * 下架
     */
    @ResponseBody
    @RequestMapping("/soldout")
    public R soldout( GoodsDO goods){
        goods.setDelistingTime(new Date());
        goodsService.update(goods);
        return R.ok();
    }
}
