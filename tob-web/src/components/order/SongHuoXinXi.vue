<template>
	<div class="pdt1">
		<div class="ww100 pdl15 pdr15 pdb96 set">
			<div class="w1200 c-b mm100 mgt30">
				<div class="ovh pdb10 bor-b-d">
					<p class="fl fs18 cor0">
						送货信息
						<!--安装信息-->
						<!--收货信息-->
					</p>
					<a href="javascript:;" @click.prevent="$router.go(-1)" class="db fr fs18 cor0">返回>></a>
				</div>
				<div class="mgt30">
					<table class="tab2">
						<tbody>
							<tr>
								<td>送货单号</td>
								<td>配送数量</td>
								<td>送货人</td>
								<td>联系电话</td>
								<td>状态</td>
								<td>--|--</td>
							</tr>
							<tr v-for="(item,index) in list" :key="index">
								<td>{{item.deliveryRecordDO.sendNo?item.deliveryRecordDO.sendNo:"无"}}</td>
								<td>{{item.realNum?item.realNum:'无'}}</td>
								<td>{{item.deliInstManDO.name?item.deliInstManDO.name:"无"}}</td>
								<td>{{item.deliInstManDO.phoneNum?item.deliInstManDO.phoneNum:"无"}}</td>
								<td class="corFF0000">
									{{
										item.deliveryRecordDO.status==0?"已派单":
										item.deliveryRecordDO.status==1?"商品已出库":
										item.deliveryRecordDO.status==2?"运送中":
										item.deliveryRecordDO.status==3?"已签收":"其他"
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
      <p class="pdt15 pdb15 pdl30 cor0 bgf1">附件信息</p>
      <div class="pdl30 pdr30 pdl80">
        <ul class="ovh" v-if="imgs&&imgs.length>0">
          <li class="fl mgr20" v-for="item in imgs"><img :src="item.thumbnailUrl" alt=""></li>
        </ul>
      </div>
		</div>
		<!--弹窗-->



	</div>
</template>

<!-- 送货信息 -->
<script>
	export default {
		name: "SongHuoXinXi",
		data(){
			return{
				list:[],
				params:{
					pageNum:1,
					limit:10,
					totalCount:0
				},
				windosItem:{},
        imgs:[],//附件图片
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
        //查询送货单的照片
        this.$axios({
          method: 'get',
          url: '/biz/tob-order/biz/order/selectOrderPhotos',
          params:  {deliveryId:item.deliveryRecordDO.id}
        }).then((res) => {
          if (res.data.code == 200 && res.data.list!=null) {
            this.imgs=res.data.list
          }
        }).catch((err)=>{
          console.log(err);
        });
			},
			hideWindow(e){
				$('.tc').hide();
				$('.pope').hide();
				this.windosItem = {}
			},
		},
		created(){
			initData.apply(this)
		},
		mounted(){
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
				item.deliveryRecordDO.status==0?"已派单":
				item.deliveryRecordDO.status==1?"商品已出库":
				item.deliveryRecordDO.status==2?"运送中":
				item.deliveryRecordDO.status==3?"已签收":"其他"
			*/
			windsonData(){
				const statusStrs = [
					"准备发货",
					"商品已派单",
					"商品订单派送中",
					"商品已签收",
				]
				let arrays = new Array();
				const fdFilter = this.$options.filters["formatDate"];
				if(this.windosItem.deliveryRecordDO && this.windosItem.deliveryRecordDO.status){

					//状态  创建时间  发货时间  签收时间
					const {status,createTime,deliveryTime,signClosedTime} = this.windosItem.deliveryRecordDO;

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
							date:fdFilter(deliveryTime),
							statusStr:statusStrs[3]
						});
					}
					if(3 <= status){
						arrays.push({
							date:fdFilter(signClosedTime),
							statusStr:statusStrs[3]
						});
					}
				}else{
					arrays.push({
							date:fdFilter(new Date()),
							statusStr:"没有发货信息"
						});
				}
				return arrays;
			}
		}
	}

	function initData(){
		const orderId = this.$route.params.orderId;
		this.$axios.get("/biz/tob-order/order/details/sh/"+orderId,this.params)
		.then(res=>{
			if(res.data.success){
				this.list = res.data.obj.list;
				console.log(this.list)
				this.params.totalCount = res.data.obj.total;
			}
		})
	}
</script>

<style scoped>

</style>
