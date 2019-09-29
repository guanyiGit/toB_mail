<template>
	<div class="pdt1">
		<div class="ww100 pdl15 pdr15 pdb96 set">
			<div class="w1200 c-b mm100 mgt30">
				<div class="ovh pdb10 bor-b-d">
					<p class="fl fs18 cor0">
						附加費用
						<!--安装信息-->
						<!--收货信息-->
					</p>
					<a href="javascript:;" @click.prevent="$router.go(-1)" class="db fr fs18 cor0">返回>></a>
				</div>
				<div class="mgt30">
					<table class="tab2">
						<tbody>
							<tr >
								<td>记录编号</td>
								<td>费用类别</td>
								<td>费用类型</td>
								<td>订单编号</td>
								<td>费用</td>
							</tr>
							<tr v-for="(item,index) in list" :key="index">
								<td>{{item.orderNo}}</td>
								<td>
									{{
										item.refType == 1 ?"安装费用":
										item.refType == 2 ?"送货/物流费用":"其他费用"
									}}
								</td>
								<td>
									{{
										item.feeType == 1?"安装费":
										item.feeType == 1?"运费":
										item.feeType == 1?"耗材费":
										item.feeType == 1?"高空作业费":"其他费用"
									}}
								</td>
								<td>{{item.recordNum?item.recordNum:"--"}}</td>
								<td class="corFF0000">{{"￥"+item.price}}</td>
							</tr>
						</tbody>
					</table>
				</div>

				<!--分页-->
		        <div class="fen c-b ovh mgt30 t-c w410">
		          <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
		        </div>
		        <!--分页 end-->

			</div>
		</div>

		<!--遮罩-->
		<div class="pope fixed dn"></div>
		<!--遮罩-->

		<!--弹窗-->
		<div class="tc fixed bgw pdb100 dn">
			<a href="###" class="db close"><img src="images/32.png" alt=""></a>
			<p class="h60 lh60 bgf1 fs16 cor0 pdl30">送货详情</p>
			<div class="pdt20 pdl30 pdr30 rel mgt15">
				<p class="xian"></p>
				<ul class="ul-2">
					<li class="active">2018-09-21 12:45 您的订单已开始配送</li>
					<li>2018-09-21  12:45  供应商已派单</li>
				</ul>
			</div>
		</div>
		<!--弹窗-->
	</div>
</template>

<!-- 送货信息 -->
<script>
	export default {
		name: "FuJiaFeiYong",
		data(){
			return{
				list:[],
				params:{
					pageNum:1,
					limit:10,
					totalCount:0
				}
			}
		},
		computed:{
			pageData(){
				return{
					curPage:this.params.pageNum,
					pageSize:this.params.limit,
					totalCount:this.params.totalCount
				}
			}
		},
		methods:{
			chekdpage(index){
				this.params.pageNum = index;
			}
		},
		created(){
			initData.apply(this)
		}
	}

	function initData(){
		const orderId = this.$route.params.orderId;
		this.$axios.get("/biz/tob-order/order/details/fy/"+orderId,this.params)
		.then(res=>{
			if(res.data.success){
				this.list = res.data.obj.list;
				this.params.totalCount = res.data.obj.total;
			}
		})
	}

</script>

<style scoped>

</style>
