package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.entities.SupplierDO;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.SupplierService;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 13:52:13
 */
 
@Controller
@RequestMapping("/com/supplier")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping()
	@RequiresPermissions("com:supplier:supplier")
	String Supplier(){
	    return "com/supplier/supplier";
	}

	@GetMapping("/test")
	@ResponseBody R  test(){
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("com:supplier:supplier")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SupplierDO> supplierList = supplierService.list(query);
		int total = supplierService.count(query);
		PageUtils pageUtils = new PageUtils(supplierList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("com:supplier:add")
	String add(){
	    return "com/supplier/add";
	}

	@GetMapping("/edit/{supId}")
	@RequiresPermissions("com:supplier:edit")
	String edit(@PathVariable("supId") Integer supId,Model model){
		SupplierDO supplier = supplierService.get(supId);
		model.addAttribute("supplier", supplier);
	    return "com/supplier/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("com:supplier:add")
	public R save( SupplierDO supplier){
		if(supplierService.save(supplier)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("com:supplier:edit")
	public R update(SupplierDO supplier){
		supplierService.update(supplier);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("com:supplier:remove")
	public R remove( Integer supId){
		if(supplierService.remove(supId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("com:supplier:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] supIds){
		supplierService.batchRemove(supIds);
		return R.ok();
	}
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------------*/
	//查询所有供应商
	@RequestMapping("/getSupplierList")
	@ResponseBody
	public R getSupplierList(@RequestParam(value="input",required = false)String input
			,@RequestParam(value="userId",required = false)Integer userId
			,@RequestParam(value="start",required = false)Integer start
			,@RequestParam(value="size",required = false)Integer size){
		try {
			return R.ok().put("map",supplierService.getSupplierList(input,userId,start,size));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	//根据id得到供应商对象
	@RequestMapping("/getSupplierById")
	@ResponseBody
	public R getSupplierById(@RequestParam(value="id",required = false)Integer id){
		try {
			return R.ok().put("map",supplierService.getSupplierById(id));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	//删除供应商
	@RequestMapping("/delSupplier")
	@ResponseBody
	public R delSupplier(@RequestParam(value="id",required = false)Integer id){
		try {
			if(supplierService.delSupplier(id)>0){
				return R.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.error();
	}
	//添加供应商
	@RequestMapping("/addSupplier")
	@ResponseBody
	public R addSupplier(@RequestParam(value="supplier",required = false)String supplier){
		try {
			if(supplier!=null&&!supplier.equals("")){
				Map map=JSON.parseObject(supplier);
				if(supplierService.addSupplier(map)>0){
					return R.ok();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.error();
	}
	//修改供应商
	@GetMapping("/updSupplier")
	@ResponseBody
	public R updSupplier(@RequestParam Map map){
		try {
			if(supplierService.updSupplier(map)>0){
				return R.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.error();
	}
	//查询送货人员或者安装人员
	@RequestMapping("/getDelivery")
	@ResponseBody
	public R getDelivery(@RequestParam(value="input",required = false)String input
			,@RequestParam(value="supplier_id",required = false)Integer supplier_id
			,@RequestParam(value="type",required = false)Integer type
			,@RequestParam(value="start",required = false)Integer start
			,@RequestParam(value="size",required = false)Integer size) {
		try {
			return R.ok().put("map", supplierService.getDelivery(input, supplier_id, type, start, size));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	//添加送货员或者安装员
	@PostMapping("addDdelivery")
	@ResponseBody
//	public R addDelivery(@RequestParam(value="delivery",required = false)String delivery, HttpServletRequest request){
	public R addDelivery(@RequestParam Map map, HttpServletRequest request){
		try {
			//System.out.println(request.toString());
//			if(delivery!=null&&!delivery.equals("")){
//				Map map=JSON.parseObject(delivery);
				if(supplierService.addDelivery(map)>0){
					return R.ok();
				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.error();
	}
	//修改送货，安装人员状态
	@GetMapping("/updDelivery")
	@ResponseBody
	public R updDelivery(@RequestParam(value = "id",required = false)Integer id,
						 @RequestParam(value = "state",required = false)Integer state){
		try {
			if(supplierService.updDelivery(id,state)>0){
				return R.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.error();
	}
	//查看送货，安装人员状态
	@GetMapping("/getDeliveryDetails")
	@ResponseBody
	public R getDeliveryDetails(@RequestParam(value = "id",required = false)Integer id,
						 @RequestParam(value = "type",required = false)Integer type){
		try {
			return R.ok().put("delivery",supplierService.getDeliveryDetails(id,type));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	//修改送货，安装人员
	@GetMapping("/updDeliverys")
	@ResponseBody
	public R updDeliverys(@RequestParam Map map){
		try {
			if(supplierService.updDeliverys(map)>0){
				return R.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.error();
	}
	//查询所有供应商
	@RequestMapping("/getGoodsList")
	@ResponseBody
	public R getGoodsList(@RequestParam(value="input",required = false)String input
			,@RequestParam(value="orgId",required = false)Integer orgId
			,@RequestParam(value="start",required = false)Integer start
			,@RequestParam(value="size",required = false)Integer size){
		try {
			int sup_id=(int)(supplierService.getSupplierByOrgId(orgId).get("sup_id"));
			return R.ok().put("map",supplierService.getGoodsList(input,sup_id,start,size));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	//修改订单状态
	@RequestMapping("/updOrderState")
	@ResponseBody
	public R updOrderState(@RequestParam(value="id",required = false)Integer id
			,@RequestParam(value="state",required = false)Integer state){
		try {
			if(supplierService.updOrderState(state,id)>0){
				return R.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.error();
	}
	//拒绝订单
	@RequestMapping("/refuseOrder")
	@ResponseBody
	public R refuseOrder(@RequestParam(value="refuse",required = false)String refuse
			,@RequestParam(value="id",required = false)Integer id){
		try {
			if(supplierService.refuseOrder(refuse,id)>0){
				return R.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.error();
	}
	//根据id得到订单对象
	@RequestMapping("/getOrder")
	@ResponseBody
	public R getOrder(@RequestParam(value="id",required = false)Integer id){
		try {
			return R.ok().put("map",supplierService.getOrder(id));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	//根据机构id得到供应商
	@RequestMapping("getSupplierByOrgId")
	@ResponseBody
	public R getSupplierByOrgId(@RequestParam(value = "orgId",required = false) Integer orgId){
		try {
			return R.ok().put("id",supplierService.getSupplierByOrgId(orgId));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	//查看订单详情
	@RequestMapping("getOrderDetail")
	@ResponseBody
	public R getOrderDetail(@RequestParam(value = "id",required = false) Integer id){
		try {
			Map map=supplierService.getOrderDetail(id);
			if(map!=null){
				return R.ok("orderDetail",map);
			}else {
				return R.error();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
