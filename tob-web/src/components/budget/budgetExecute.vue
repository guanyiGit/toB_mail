<template>
	<div class="ww100 pdl15 pdr15 pdb130 pdt1">
	<div class="w1200 c-b mm100 mgt30">
		<div class="ovh pdb10 bor-b-d">
			<p class="fl fs18 cor0">执行进度设定</p>
			<a style="cursor:pointer;" class="db fr fs18 cor0" @click="returnn">返回>></a>
		</div>
		<form action="#">
			<div class="mgt30 w630 c-b w700">
				<p class="cor0">选择执行计划：</p>
				<div class="mgt20 ovh" v-if="defaultObj && defaultObj.id">
					<div class="fl">
						<div class="lab2">
							<input type="checkbox" :checked="selectObj && selectObj.id == defaultObj.id">
							<i @click="selectObj = defaultObj"></i>上级设定的执行计划[{{defaultObj.planName}}]  <span class="mgl30">默认计划</span>
            </div>
					</div>
					<p class="fr">
						<router-link :to="{'name': 'deliveryPlanDetail',query:{'planId':selectObj.id,type:selectObj.type}}" class="db btn btn-xs btn-primary" >查看详情</router-link>
					</p>
				</div>
				<div class="mgt20 ovh" v-for="(item,index) in list" :key="index" v-if="!defaultObj || (defaultObj && item.id != defaultObj.id)">
					<!--  v-if="defaultObj && item.id != defaultObj.id" -->
					<div class="fl">
						<div class="lab2">
							<!-- ="selectObj.id == item.id" -->
							<input type="checkbox" :checked="selectObj && selectObj.id == item.id">
							<i @click="selectObj = item"></i> {{item.planName}}
						</div>
					</div>
					<p class="fr">
						<router-link :to="{'name': 'deliveryPlanDetail',query:{'planId':item.id,type:item.type}}" class="db btn btn-xs btn-primary" >查看详情</router-link>
					</p>
				</div>

				<router-link :to="{name:'addDeliveryPlan'}" class="db corED145B ios2 pdl29 mgt20">新增执行计划</router-link>
			</div>

			<div class="mgt120 t-c">
				<a href="javascript:;" @click.prevent="save" class="dinb w120 btn btn-primary mgr4">保存</a>
				<a style="cursor:pointer;"  @click="returnn" class="dinb w120 btn btn-danger">取消</a>
			</div>
		</form>
	</div>

	<!--分页-->
    <div class="fen c-b ovh mgt30 t-c w410">
      <page-vue :pageData="pageData" @chekdpageCallcak="chekdpage"></page-vue>
    </div>
    <!--分页 end-->

</div>
</template>
<script>
	export default {
		name:"budgetExecute",
		data(){
			return{
				params:{
					pageNum:1,
					limit:10,
					totalCount:0
				},
				list:[],
				defaultObj:{},
				selectObj:{},
        myTab:null
			}
		},
		computed:{
			pageData(){
				return{
					curPage:this.params.pageNum,
					pageSize:this.params.limit,
					totalCount:this.params.totalCount
				}
			},
		},
		methods:{
			chekdpage(index){
				this.params.pageNum = index;
			},
			save(){
				const {orgId,id:planId} = this.selectObj;
				var orgId1=this.$route.query.orgId;
				var year=this.$route.query.year;
				//subjectId传参的时候代表给自己组织分 orgId代表自己的组织id
				//subjectId 不传的时候代表给子级部门分，orgID代表自己部门id
				var subjectId=this.$route.query.subjectId;
				//代表给自己的组织的部门分
				this.$axios({
					method:"put",
					url:"/biz/tob-budget/biz/budget/plan/" + orgId1,
					params:{
            subjectId:subjectId,
            year:year,
            planId:planId,
					},
					isloading:true
				})
				.then(res=>{
					if(res.data.success){
						this.$alert("保存成功！")
						.then(res=>{
							this.$router.push({name: 'bm_budgetManage',params:{myTab:this.myTab}})
						})

					}else{
						this.$alert("保存失败！")
					}
				})
			},
      returnn(){
        // this.$router.back(-1);
        this.$router.push({name:'bm_budgetManage',params:{myTab:this.myTab}});
      }
		},
		created(){
			initData.apply(this);
      this.myTab=this.$route.query.myTab;
		}
	}

	function initData(){
		const orgId = this.configJS.userInfo.orgId;//当前登录的组织id
    var subjectId=this.$route.query.subjectId;
    var year=this.$route.query.year;
    var orgId1=this.$route.query.orgId;//分配的组织id
    this.$axios({
      method:"get",
      url:"/biz/tob-budget/biz/budget/plans/" + orgId,
      params:{
        subjectId:subjectId,
        year:year,
        orgId1:orgId1,
      },
      isloading:true
    })
		// this.$axios.get("/biz/tob-budget/biz/budget/plans/"+ orgId)
		.then(res=>{
			if(res.data.success){
				this.list = res.data.obj.list;
				this.defaultObj = res.data.default;
				this.selectObj = res.data.selected;
				this.params.totalCount = res.data.obj.total;

				if(this.defaultObj){
					this.params.totalCount = this.params.totalCount + 1;
				}

			}
		})

	}
</script>

<style scoped>

</style>
