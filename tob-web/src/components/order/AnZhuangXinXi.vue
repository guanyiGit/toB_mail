<template>
	<div class="pdt1">
		<div class="ww100 pdl15 pdr15 pdb96 set">
			<div class="w1200 c-b mm100 mgt30">
				<div class="ovh pdb10 bor-b-d">
					<p class="fl fs18 cor0">
						安装信息
					</p>
					<a href="javascript:;" @click.prevent="$router.go(-1)" class="db fr fs18 cor0">返回>></a>
				</div>
				<div class="mgt30">
					<table class="tab2">
						<tbody>
							<tr>
								<td>安装单号</td>
								<td>安装数量</td>
								<td>安装人员</td>
								<td>联系电话</td>
								<td>状态</td>
								<td>--|--</td>
							</tr>
							<tr v-for="(item,index) in list" :key="index">
								<td>{{item.installRecordDO.installNo?item.installRecordDO.installNo:"无"}}</td>
								<td>{{item.installNum?item.installNum:'无'}}</td>
								<td>{{item.deliInstManDO.name?item.deliInstManDO.name:"无"}}</td>
								<td>{{item.deliInstManDO.phoneNum?item.deliInstManDO.phoneNum:"无"}}</td>
								<td class="corFF0000">
									{{
										item.installRecordDO.status==0?"工单生成":
										item.installRecordDO.status==1?"已派单":
										item.installRecordDO.status==2?"安装完成":"其他"
									}}
								</td>
								<td><a href="javascript:;" @click.prevent="showWindow(item)" class="btn btn-xs btn-success tanChuang">详情</a></td>
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
			<a href="javascript:;" @click.prevent="hideWindow" class="db close"><img src="/static/images/32.png" alt=""></a>
			<p class="h60 lh60 bgf1 fs16 cor0 pdl30">送货详情</p>
			<div class="pdt20 pdl30 pdr30 rel mgt15">
				<p class="xian"></p>
				<ul class="ul-2" v-for="(item,index) in windsonData" :key="index">
					<li class="active">{{item.date}} {{item.statusStr}}</li>
				</ul>
			</div>
		</div>
		<!--弹窗-->
	</div>
</template>

<!-- 送货信息 -->
<script>
	export default {
		name: "AnZhuangXinXi",
		data(){
			return{
				list:[],
				params:{
					pageNum:1,
					limit:10,
					totalCount:0
				},
				windosItem:{},
			}
		},
		methods:{
			chekdpage(index){
				this.params.pageNum = index;
			},
			showWindow(item){
				$('.tc').show();
				$('.pope').show();
				this.windosItem = item;
			},
			hideWindow(e){
				$('.tc').hide();
				$('.pope').hide();
			},
		},
		created(){
			initData.apply(this)
		},
		computed:{
			pageData(){
				return{
					curPage:this.params.pageNum,
					pageSize:this.params.limit,
					totalCount:this.params.totalCount
				}
			},
			/*
				item.installRecordDO.status==0?"工单生成":
				item.installRecordDO.status==1?"已派单":
				item.installRecordDO.status==2?"安装完成":"其他"
			*/
			windsonData(){
				const statusStrs = [
					"工单生成",
					"已派单",
					"安装完成",
				]
				let arrays = new Array();
				const fdFilter = this.$options.filters["formatDate"];
				if(this.windosItem.installRecordDO && this.windosItem.installRecordDO.status){

					//状态  创建时间  安装时间
					const {status,createTime,installTime} = this.windosItem.installRecordDO;

					arrays.push({
							date:fdFilter(createTime),
							statusStr:statusStrs[0]
						});
					if(1 <= status){
						arrays.push({
							date:fdFilter(createTime),
							statusStr:statusStrs[1]
						});
					}
					if(2 <= status){
						arrays.push({
							date:fdFilter(installTime),
							statusStr:statusStrs[2]
						});
					}
				}else{
					arrays.push({
							date:fdFilter(new Date()),
							statusStr:"没有工单信息"
						});
				}
				return arrays;
			}
		},


	}

	function initData(){
		const orderId = this.$route.params.orderId;
		this.$axios.get("/biz/tob-order/order/details/az/"+orderId,this.params)
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
