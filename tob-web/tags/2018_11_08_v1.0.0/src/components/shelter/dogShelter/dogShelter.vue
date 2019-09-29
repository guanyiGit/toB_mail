<template>
    <!--犬只收容发-->
  <div>
    <!--内容区-->
    <div class="w980 fr">
      <div class="ovh div1 h50 rel">
        <p class="abs ww100 left0 bottom0"></p>
        <a style="cursor:pointer;" class="fl db fs16 cor3c3c3c w160 h50 lh50 t-c active">收容中</a>
        <a style="cursor:pointer;" class="fl db fs16 cor3c3c3c w160 h50 lh50 t-c">发布中</a>
        <a style="cursor:pointer;" class="fl db fs16 cor3c3c3c w160 h50 lh50 t-c">待确认领养</a>
        <a style="cursor:pointer;" class="fl db fs16 cor3c3c3c w160 h50 lh50 t-c">已认领</a>
        <a style="cursor:pointer;" class="fl db fs16 cor3c3c3c w160 h50 lh50 t-c">已领养</a>
        <a style="cursor:pointer;" class="fl db fs16 cor3c3c3c w160 h50 lh50 t-c">已处理</a>
      </div>

      <div class="div2 db">
        <div class="ovh mgt20">
          <div class="fl cor6 h40 lh40 mgl10">
            <router-link :to="{name:'forRegistration'}" class="db fl hov8 corffa472 mgr35 pc9 pdl36">流浪犬收容登记</router-link>
            <a style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pc14 pdl36" @click="publishBatch">发布到收容平台</a>
          </div>
          <form class="fr db w380 h40 borbfbfbf ras20 rel form" action="#">
            <input id="input1" type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="犬只编号/性别/品种/收容原因" v-model="data1.string"
                   @keyup.enter="search">
            <input value="" class="in1 db abs z100 top0 bort right0" @click.prevent="search">
          </form>
        </div>

        <div class="mgt10 k3 t-c">
          <table class="table5" width="100%" border="1">
            <tbody>
            <tr>
              <td class="cor0 h60 bgf1"><label class="lab3 mgr40"><input type="checkbox"><i></i></label></td>
              <td class="cor0 h60 bgf1 t-l pdl100 ww30">犬只信息</td>
              <td class="cor0 h60 bgf1 t-c">收容原因</td>
              <td class="cor0 h60 bgf1 t-c">收容时间</td>
              <td class="cor0 h60 bgf1 t-c">操作</td>
            </tr>
            <tr v-for="(item,index) in dogList" :key="index">
              <td>
                <label class="lab3 mgr40"><input type="checkbox" v-model="item.isCheckd"><i></i></label>
              </td>
              <td class="pdt20 pdb20 ww30">
                <div class="ovh t-l">
                  <div class="fl w76"><img v-bind:src="item.thumbnailUrl" alt=""></div>
                  <div class="mgl98">
                    <div class="ovh">
                      <p class="fl cor0 h22 lh22 mgr10">{{item.dogName}}</p>
                      <p class="fl cor9 h22 lh22 mgr10">性别：{{item.dogGender=0?"雄":"雌"}}</p>
                      <p class="fl cor9 h22 lh22">犬领：{{item.dogAge}}岁</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30">品种：{{item.dogBreed}}</p>
                      <p class="fl">毛色：{{item.dogColor}}</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30" style="width: 110%">犬只/犬舍编号：{{item.dogCode}}/{{item.doghouseNum}}</p>
                    </div>
                  </div>
                </div>
              </td>
              <td class="t-c">{{item.collDogReason}}</td>
              <td class="t-c">{{item.collectionTime | formatDate("yyyy-MM-dd")}}</td>
              <td class="t-c">
                <router-link style="cursor:pointer;" class="dinb cor009cff hov8 mgr14" :to="{name:'claimToRegister',params:{collectionId:item.collectionId,dogId:item.dogId}}">认领登记</router-link>
                <router-link style="cursor:pointer;" class="dinb cor009cff hov8" :to="{name:'handleRegistration',params:{collectionId:item.collectionId,dogId:item.dogId}}">处理登记</router-link><br>
                <router-link style="cursor:pointer;" class="dinb cor009cff hov8 mgr14" :to="{name:'adoptionRegistration',params:{collectionId:item.collectionId,dogId:item.dogId}}">领养登记</router-link>
                <router-link style="cursor:pointer;" class="dinb cor009cff hov8" :to="{name:'theShelterDetail',params:{collectionId:item.collectionId}}">查看详情</router-link>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        <!--分页-->
        <page-vue :pageData="pageData1" @chekdpageCallcak="chekdpage"></page-vue>
        <!--分页-->
      </div>
      <div class="div2 dn">
        <div class="ovh mgt20">
          <div class="fl cor6 h40 lh40 mgl10">
            <!--<a style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pc9 pdl36">流浪犬收容登记</a>-->
            <a style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pc14 pdl36" @click="revocationBatch(null,null,0)">撤回到收容库</a>
          </div>
          <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
            <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="犬只编号/性别/品种/收容原因" v-model="data2.string"
                   @keyup.enter="search1">
            <input type="submit" value="" class="in1 db abs z100 top0 bort right0" @click.prevent="search1">
          </form>
        </div>

        <div class="mgt10 k3">
          <table class="table5" width="100%" border="1">
            <tbody>
            <tr>
              <td class="cor0 h60 bgf1"><label class="lab3 mgr40"><input type="checkbox"><i></i></label></td>
              <td class="cor0 h60 bgf1 t-l pdl100 ww30">犬只信息</td>
              <td class="cor0 h60 bgf1 t-c">发布时间</td>
              <td class="cor0 h60 bgf1 t-c">已发布时长</td>
              <td class="cor0 h60 bgf1 t-c">操作</td>
            </tr>
            <tr v-for="(item,index) in dogList1" :key="index">
              <td>
                <label class="lab3 mgr40"><input type="checkbox" v-model="item.isCheckd"><i></i></label>
              </td>
              <td class="pdt20 pdb20 ww30">
                <div class="ovh t-l">
                  <div class="fl w76"><img v-bind:src="item.thumbnailUrl" alt=""></div>
                  <div class="mgl98">
                    <div class="ovh">
                      <p class="fl cor0 h22 lh22 mgr10">{{item.dogName}}</p>
                      <p class="fl cor9 h22 lh22 mgr10">性别：{{item.dogGender=0?"雄":"雌"}}</p>
                      <p class="fl cor9 h22 lh22">犬领：{{item.dogAge}}岁</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30">品种：{{item.dogBreed}}</p>
                      <p class="fl">毛色：{{item.dogColor}}</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30" style="width: 110%">犬只/犬舍编号：{{item.dogCode}}/{{item.doghouseNum}}</p>
                    </div>
                  </div>
                </div>
              </td>
              <td class="t-c">{{item.publishDate | formatDate('yyyy-MM-dd')}}</td>
              <td class="t-c">{{item.days}}天</td>
              <td class="t-c">
                <a style="cursor:pointer;" class="db cor009cff hov8 t-c" @click="revocationBatch(item.collectionId,item.adoptionPublicityId,0)">撤回到收容库</a>
                <router-link style="cursor:pointer;" class="db cor009cff hov8 t-c mgt10" :to="{name:'releaseDetails',params:{collectionId:item.collectionId,adoptionPublicityId:item.adoptionPublicityId}}">查看详情</router-link>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        <!--分页-->
        <page-vue :pageData="pageData2" @chekdpageCallcak="chekdpage1"></page-vue>
        <!--分页-->
      </div>
      <div class="div2 dn">
        <div class="ovh mgt20">
          <div class="fl cor6 h40 lh40 mgl10">
            <a style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pc14 pdl36" @click="revocationBatch(null,null,1)">撤回到收容库</a>
            <a style="cursor:pointer;" class="db fl hov8 corffa472 mgr35 pc14 pdl36" @click="againPublish(null,null)">重新发布</a>
          </div>
          <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
            <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="犬只编号/性别/品种/收容原因" v-model="data3.string"
                   @keyup.enter="search2">
            <input type="submit" value="" class="in1 db abs z100 top0 bort right0" @click.prevent="search2">
          </form>
        </div>

        <div class="mgt10 k3">
          <table class="table5" width="100%" border="1">
            <tbody>
            <tr>
              <td class="cor0 h60 bgf1"><label class="lab3 mgr40"><input type="checkbox"><i></i></label></td>
              <td class="cor0 h60 bgf1 t-l pdl100 ww30">犬只信息</td>
              <td class="cor0 h60 bgf1 t-c">领养申请时间</td>
              <td class="cor0 h60 bgf1 t-c">申请人</td>
              <td class="cor0 h60 bgf1 t-c">操作</td>
            </tr>
            <tr v-for="(item,index) in dogList2" :key="index">
              <td>
                <label class="lab3 mgr40"><input type="checkbox" v-model="item.isCheckd"><i></i></label>
              </td>
              <td class="pdt20 pdb20 ww30">
                <div class="ovh t-l">
                  <div class="fl w76"><img v-bind:src="item.thumbnailUrl" alt=""></div>
                  <div class="mgl98">
                    <div class="ovh">
                      <p class="fl cor0 h22 lh22 mgr10">{{item.dogName}}</p>
                      <p class="fl cor9 h22 lh22 mgr10">性别：{{item.dogGender=0?"雄":"雌"}}</p>
                      <p class="fl cor9 h22 lh22">犬领：{{item.dogAge}}岁</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30">品种：{{item.dogBreed}}</p>
                      <p class="fl">毛色：{{item.dogColor}}</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30" style="width: 110%">犬只/犬舍编号：{{item.dogCode}}/{{item.doghouseNum}}</p>
                    </div>
                  </div>
                </div>
              </td>
              <td class="t-c">{{item.applyTime | formatDate('yyyy-MM-dd')}}</td>
              <td class="t-c">
                <p>{{item.applyerName}}</p>
                <p class="cor9">{{item.applyerPhone}}</p>
              </td>
              <td class="t-c">
                <router-link style="cursor:pointer;" class="dinb cor009cff hov8 t-c mgr14" :to="{name:'confirmToAdopt',params:{adoptionPublicityId:item.adoptionPublicityId}}">确认领养</router-link>
                <a style="cursor:pointer;margin-left: 20px" class="dinb cor009cff hov8 t-c" @click="againPublish(item.adoptionRecordsId,item.adoptionPublicityId)">重新发布</a> <br>
                <a style="cursor:pointer;" class="dinb cor009cff hov8 t-c mgr14" @click="revocationBatch(item.collectionId,item.adoptionPublicityId,1)">撤回到收容库</a>
                <router-link style="cursor:pointer;" class="dinb cor009cff hov8 t-c" :to="{name:'pendingAdoptionDetails',params:{collectionId:item.collectionId,adoptionPublicityId:item.adoptionPublicityId,adoptionRecordsId:item.adoptionRecordsId}}">查看详情</router-link>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        <!--分页-->
        <page-vue :pageData="pageData3" @chekdpageCallcak="chekdpage2"></page-vue>
        <!--分页-->
      </div>
      <div class="div2 dn">
        <div class="ovh mgt20">
          <form class="fr db w380 h40 borbfbfbf ras20 rel mgl10" action="#">
            <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="犬只编号/性别/品种/收容原因" v-model="data4.string"
                   @keyup.enter="search3">
            <input type="submit" value="" class="in1 db abs z100 top0 bort right0" @click.prevent="search3">
          </form>
        </div>

        <div class="mgt10 k3">
          <table class="table5" width="100%" border="1">
            <tbody>
            <tr>
              <td class="cor0 h60 bgf1"><label class="lab3 mgr40"><input type="checkbox"><i></i></label></td>
              <td class="cor0 h60 bgf1 t-l pdl100 ww30">犬只信息</td>
              <td class="cor0 h60 bgf1 t-c">认领人</td>
              <td class="cor0 h60 bgf1 t-c">认领时间</td>
              <td class="cor0 h60 bgf1 t-c">操作</td>
            </tr>
            <tr v-for="item in dogList3">
              <td>
                <label class="lab3 mgr40"><input type="checkbox"><i></i></label>
              </td>
              <td class="pdt20 pdb20 ww30">
                <div class="ovh t-l">
                  <div class="fl w76"><img v-bind:src="item.thumbnailUrl" alt=""></div>
                  <div class="mgl98">
                    <div class="ovh">
                      <p class="fl cor0 h22 lh22 mgr10">{{item.dogName}}</p>
                      <p class="fl cor9 h22 lh22 mgr10">性别：{{item.dogGender=0?"雄":"雌"}}</p>
                      <p class="fl cor9 h22 lh22">犬领：{{item.dogAge}}岁</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30">品种：{{item.dogBreed}}</p>
                      <p class="fl">毛色：{{item.dogColor}}</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30" style="width: 110%">犬只/犬舍编号：{{item.dogCode}}/{{item.doghouseNum}}</p>
                    </div>
                  </div>
                </div>
              </td>
              <td class="t-c">
                <p>{{item.claimName}}</p>
                <p class="cor9">{{item.phone}}</p>
              </td>
              <td class="t-c">{{item.handleTime | formatDate('yyyy-MM-dd')}}</td>
              <td class="t-c">
                <router-link style="cursor:pointer;" class="db cor009cff hov8 t-c" :to="{name:'claimedDetails',params:{collectionId:item.collectionId,handleId:item.handleId}}">查看详情</router-link>
              </td>
            </tr>

            </tbody>
          </table>
        </div>
        <!--分页-->
        <page-vue :pageData="pageData4" @chekdpageCallcak="chekdpage3"></page-vue>
        <!--分页-->
      </div>
      <div class="div2 dn">
        <div class="ovh mgt20">
          <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
            <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="犬只编号/性别/品种/收容原因" v-model="data5.string"
                   @keyup.enter="search4">
            <input type="submit" value="" class="in1 db abs z100 top0 bort right0" @click.prevent="search4">
          </form>
        </div>

        <div class="mgt10 k3">
          <table class="table5" width="100%" border="1">
            <tbody>
            <tr>
              <td class="cor0 h60 bgf1"><label class="lab3 mgr40"><input type="checkbox"><i></i></label></td>
              <td class="cor0 h60 bgf1 t-l pdl100 ww30">犬只信息</td>
              <td class="cor0 h60 bgf1 t-c">领养人</td>
              <td class="cor0 h60 bgf1 t-c">领养时间</td>
              <td class="cor0 h60 bgf1 t-c">操作</td>
            </tr>
            <tr v-for="item in dogList4">
              <td>
                <label class="lab3 mgr40"><input type="checkbox"><i></i></label>
              </td>
              <td class="pdt20 pdb20 ww30">
                <div class="ovh t-l">
                  <div class="fl w76"><img v-bind:src="item.thumbnailUrl" alt=""></div>
                  <div class="mgl98">
                    <div class="ovh">
                      <p class="fl cor0 h22 lh22 mgr10">{{item.dogName}}</p>
                      <p class="fl cor9 h22 lh22 mgr10">性别：{{item.dogGender=0?"雄":"雌"}}</p>
                      <p class="fl cor9 h22 lh22">犬领：{{item.dogAge}}岁</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30">品种：{{item.dogBreed}}</p>
                      <p class="fl">毛色：{{item.dogColor}}</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30" style="width: 110%">犬只/犬舍编号：{{item.dogCode}}/{{item.doghouseNum}}</p>
                    </div>
                  </div>
                </div>
              </td>
              <td class="t-c">
                <p>{{item.applyerName}}</p>
                <p class="cor9">{{item.applyerPhone}}</p>
              </td>
              <td class="t-c">{{item.adoptionTime | formatDate('yyyy-MM-dd')}}</td>
              <td class="t-c">
                <router-link style="cursor:pointer;" class="db cor009cff hov8" :to="{name:'beenAdoptedDetails',params:{collectionId:item.collectionId,adoptionRecordsId:item.adoptionRecordsId}}">查看详情</router-link>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        <!--分页-->
        <page-vue :pageData="pageData5" @chekdpageCallcak="chekdpage4"></page-vue>
        <!--分页-->
      </div>
      <div class="div2 dn">
        <div class="ovh mgt20">
          <form class="fr db w380 h40 borbfbfbf ras20 rel" action="#">
            <input type="text" class="db ww100 h38 lh38 pdl15 cor3c3c3c bort" placeholder="犬只编号/性别/品种/收容原因" v-model="data6.string"
                   @keyup.enter="search5">
            <input type="submit" value="" class="in1 db abs z100 top0 bort right0" @click.prevent="search5">
          </form>
        </div>

        <div class="mgt10 k3">
          <table class="table5" width="100%" border="1">
            <tbody>
            <tr>
              <td class="cor0 h60 bgf1"><label class="lab3 mgr40"><input type="checkbox"><i></i></label></td>
              <td class="cor0 h60 bgf1 t-l pdl100 ww30">犬只信息</td>
              <td class="cor0 h60 bgf1 t-c">处理原因</td>
              <td class="cor0 h60 bgf1 t-c">处理时间</td>
              <td class="cor0 h60 bgf1 t-c">操作</td>
            </tr>
            <tr v-for="item in dogList5">
              <td>
                <label class="lab3 mgr40"><input type="checkbox"><i></i></label>
              </td>
              <td class="pdt20 pdb20 ww30">
                <div class="ovh t-l">
                  <div class="fl w76"><img v-bind:src="item.thumbnailUrl" alt=""></div>
                  <div class="mgl98">
                    <div class="ovh">
                      <p class="fl cor0 h22 lh22 mgr10">{{item.dogName}}</p>
                      <p class="fl cor9 h22 lh22 mgr10">性别：{{item.dogGender=0?"雄":"雌"}}</p>
                      <p class="fl cor9 h22 lh22">犬领：{{item.dogAge}}岁</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30">品种：{{item.dogBreed}}</p>
                      <p class="fl">毛色：{{item.dogColor}}</p>
                    </div>
                    <div class="cor9 mgt10 ovh">
                      <p class="fl mgr30" style="width: 110%">犬只/犬舍编号：{{item.dogCode}}/{{item.doghouseNum}}</p>
                    </div>
                  </div>
                </div>
              </td>
              <td class="t-c">{{item.handleReason}}</td>
              <td class="t-c">{{item.handleTime | formatDate('yyyy-MM-dd')}}</td>
              <td class="t-c">
                <router-link style="cursor:pointer;" class="db cor009cff hov8" :to="{name:'hasDealwith',params:{collectionId:item.collectionId,handleId:item.handleId}}">查看详情</router-link>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        <!--分页-->
        <page-vue :pageData="pageData6" @chekdpageCallcak="chekdpage5"></page-vue>
        <!--分页-->
      </div>
    </div>
    <!--内容区END-->
  </div>
</template>

<script>
  let params = {
    collectionId:null,
    dogId:null,
    operatorId:null
  }
  import $ from 'jquery'
    export default {
        name: "dogShelter",
      data() {
        return {
          data1:{pageSize: 10,   //页面大小
            pn: 1,  //页码
            status:0,
            string:null//搜索内容
          },
          data2:{pageSize:10,
            pn:1,
            string:null},
          data3:{pageSize: 10,   //页面大小
            pn: 1,  //页码
            status:1,
            string:null//搜索内容
          },
          data4:{pageSize: 10,   //页面大小
            pn: 1,  //页码
            type:0,
            string:null//搜索内容
          },
          data5:{pageSize: 10,   //页面大小
            pn: 1,  //页码
            status:2,
            string:null//搜索内容
          },
          data6:{pageSize: 10,   //页面大小
            pn: 1,  //页码
            type:1,
            string:null//搜索内容
          },
          pageData1:{
            curPage:1,//当前页吗
            totalCount:1,//总条数
            pageSize:1//页面大小
          },
          pageData2:{
            curPage:1,//当前页吗
            totalCount:1,//总条数
            pageSize:1//页面大小
          },
          pageData3:{
            curPage:1,//当前页吗
            totalCount:1,//总条数
            pageSize:1//页面大小
          },
          pageData4:{
            curPage:1,//当前页吗
            totalCount:1,//总条数
            pageSize:1//页面大小
          },
          pageData5:{
            curPage:1,//当前页吗
            totalCount:1,//总条数
            pageSize:1//页面大小
          },
          pageData6:{
            curPage:1,//当前页吗
            totalCount:1,//总条数
            pageSize:1//页面大小
          },





          // totalPage: 0,//总页数
          // totalPage1: 0,//总页数
          // totalPage2: 0,//总页数
          // totalPage3: 0,//总页数
          // totalPage4: 0,//总页数
          // totalPage5: 0,//总页数
          // keyWord: '',//搜索关键字
          // keyWord1: '',//搜索关键字
          // keyWord2: '',//搜索关键字
          // keyWord3: '',//搜索关键字
          // keyWord4: '',//搜索关键字
          // keyWord5: '',//搜索关键字
          dogList: [],//犬列表1
          dogList1: [],//犬列表2
          dogList2: [],//犬列表3
          dogList3: [],//犬列表4
          dogList4: [],//犬列表5
          dogList5: [],//犬列表6
        }
      },
      created() {
        inShelter(this);
        release(this);
        endingpAdoption(this);
        claimed(this);
        hasBeenAdopted(this);
        haveToDeal(this);
      },
      mounted() {
        console.log(this.$data)
      },
      computed: {
        // pageData() {
        //   return {
        //     curPage: this.data1.pn ? this.data1.pn : 1,//当前页吗
        //     totalCount: this.data1.totalCount ? this.totalPage * params.pageSize : 1,//总条数
        //     pageSize: this.data1.pageSize ? this.data1.pageSize : 1//页面大小
        //   }
        // },
        // pageData1() {
        //   return {
        //     curPage: params.pn ? params.pn : 1,//当前页吗
        //     totalCount: this.totalPage ? this.totalPage * params.pageSize : 1,//总条数
        //     pageSize: params.pageSize ? params.pageSize : 1//页面大小
        //   }
        // },
        // pageData2() {
        //   return {
        //     curPage: params.pn ? params.pn : 1,//当前页吗
        //     totalCount: this.totalPage ? this.totalPage * params.pageSize : 1,//总条数
        //     pageSize: params.pageSize ? params.pageSize : 1//页面大小
        //   }
        // },
        // pageData3() {
        //   return {
        //     curPage: params.pn ? params.pn : 1,//当前页吗
        //     totalCount: this.totalPage ? this.totalPage * params.pageSize : 1,//总条数
        //     pageSize: params.pageSize ? params.pageSize : 1//页面大小
        //   }
        // },
        // pageData4() {
        //   return {
        //     curPage: params.pn ? params.pn : 1,//当前页吗
        //     totalCount: this.totalPage ? this.totalPage * params.pageSize : 1,//总条数
        //     pageSize: params.pageSize ? params.pageSize : 1//页面大小
        //   }
        // },
        // pageData5() {
        //   return {
        //     curPage: params.pn ? params.pn : 1,//当前页吗
        //     totalCount: this.totalPage ? this.totalPage * params.pageSize : 1,//总条数
        //     pageSize: params.pageSize ? params.pageSize : 1//页面大小
        //   }
        // }
      },
      mounted(){
        var $tab2= $('.div1>a');
        $tab2.mouseover(function(){
          $(this).addClass('active').siblings().removeClass('active');
          var i= $tab2.index(this);
          $('.div2').eq(i).show().siblings('.div2').hide();
        });
      },
      methods: {
        chekdpage(pageNum) {
          this.data1.pn = pageNum
          inShelter(this)
        },
        chekdpage1(pageNum) {
          this.data2.pn = pageNum
          release(this)
        },
        chekdpage2(pageNum) {
          this.data3.pn = pageNum
          endingpAdoption(this)
        },
        chekdpage3(pageNum) {
          this.data4.pn = pageNum
          claimed(this)
        },
        chekdpage4(pageNum) {
          this.data5.pn = pageNum
          hasBeenAdopted(this)
        },
        chekdpage5(pageNum) {
          this.data6.pn = pageNum
          haveToDeal(this)
        },
        search() {//搜索
          inShelter(this)
        },
        search1() {//搜索
          release(this)
        },
        search2() {//搜索
          endingpAdoption(this)
        },
        search3() {//搜索
          claimed(this)
        },
        search4() {//搜索
          hasBeenAdopted(this)
        },
        search5() {//搜索
          haveToDeal(this)
        },
        publishBatch() {//批量发布到领养平台
          let blackIdList = [];//需要删除的id集合
          this.dogList.map(item => {
            if (item && item.isCheckd) {//所有选中的
              blackIdList.push(item.collectionId)
            }
          })
          if (blackIdList.length > 0) {
            this.$confirm('确定发布吗？').then(_ => {
              this.$axios({
                url: '/biz/adoption/publishBatch',
                method: 'post',
                data: blackIdList,
              }).then((res) => {
                // console.log(res)
                if (res.data.status == 200) {
                  inShelter(this);
                  release(this);
                }
              }).catch((res) => {
                console.log(res)
              })
            }).catch(_ => {
            });
          }else{
            this.$alert("请选择犬只！")
          }
        },
        //批量撤回到收容库
        revocationBatch(collectionId,adoptionPublicityId,type){
          let blackIdList = [];//需要删除的id集合
          if (collectionId!=null) {
            var obj={
              collectionId:null,
              adoptionPublicityId:null
            }
            obj.collectionId=collectionId;
            obj.adoptionPublicityId=adoptionPublicityId;
            blackIdList.push(obj);
          }else {
            if (type==0) {
              this.dogList1.map(item => {
                var obj={
                  collectionId:null,
                  adoptionPublicityId:null
                }
                if (item && item.isCheckd) {//所有选中的
                  obj.collectionId=item.collectionId;
                  obj.adoptionPublicityId=item.adoptionPublicityId;
                  blackIdList.push(obj);
                }
              })
            }else if (type==1) {
              this.dogList2.map(item => {
                var obj={
                  collectionId:null,
                  adoptionPublicityId:null
                }
                if (item && item.isCheckd) {//所有选中的
                  obj.collectionId=item.collectionId;
                  obj.adoptionPublicityId=item.adoptionPublicityId;
                  blackIdList.push(obj)
                }
              })
            }
          }
          if (blackIdList.length > 0) {
            this.$confirm('确定撤回吗？').then(_ => {
              this.$axios({
                url: '/biz/adoption/revocation',
                method: 'get',
                traditional: true,
                params: {"AdoptionCollectionStr":JSON.stringify(blackIdList)},
              }).then((res) => {
                if (res.data.status == 200) {
                  inShelter(this);
                  release(this);
                  endingpAdoption(this);
                }
              }).catch((res) => {
                console.log(res)
              })
            }).catch(_ => {
            });
            // this.$confirm("确定发布吗?", function(ok){
            //   if (ok) {

            //   }
            // })
          }else {
            this.$alert("请选择犬只信息")
          }
        },
        //重新发布
        againPublish(adoptionRecordsId,dogPublicityId){
          let blackIdList = [];//需要发布的id集合
          var obj={
            adoptionRecordsId:adoptionRecordsId,
            dogPublicityId:dogPublicityId
          }
          if (adoptionRecordsId!=null) {
            blackIdList.push(obj)
          }else {
              this.dogList2.map(item => {
                var obj={
                  adoptionRecordsId:adoptionRecordsId,
                  dogPublicityId:dogPublicityId
                }
                if (item && item.isCheckd) {//所有选中的
                  blackIdList.push(obj)
                }
              })
          }
          if (blackIdList.length > 0) {
            this.$confirm('确定重新发布吗？').then(_ => {
              this.$axios({
                url: '/biz/adoption/againPublish',
                method: 'post',
                traditional: true,
                params: {"AdoptionRecordListStr":JSON.stringify(blackIdList)},
              }).then((res) => {
                if (res.data.status == 200) {
                  release(this);
                  endingpAdoption(this);
                }
              }).catch((res) => {
                console.log(res)
              })
            }).catch(_ => {
            });
          }else {
            this.$alert('请选择犬只')
          }
        }
      },
      watch: {
        keyWord(newVal, oldVal) {
          this.data1.string = newVal;
        },
        keyWord1(newVal, oldVal) {
          this.data2.string = newVal;
        },
        keyWord2(newVal, oldVal) {
          this.data3.string = newVal;
        },
        keyWord3(newVal, oldVal) {
          this.data4.string = newVal;
        },
        keyWord4(newVal, oldVal) {
          this.data5.string = newVal;
        },
        keyWord5(newVal, oldVal) {
          this.data6.string = newVal;
        },
      }
    }

    //收容中
  function inShelter(_this) {
    if (!_this.data1.string) {
      delete _this.data1.string
    }
    _this.$axios({
      url: '/biz/collection/findCollectionList',
      method: 'get',
      params: _this.data1
    }).then((res) => {
      if (res.data.status == 200) {
        if (res.data.data == null) {
          _this.dogList = []
        } else {
          res.data.data.lists.map(item => {
            item.isCheckd = false;//默认未选中
          })
          _this.pageData1.curPage= res.data.data.pageNumber//当前页吗
          _this.pageData1.totalCount= res.data.data.totalRecord//总条数
          _this.pageData1.pageSize= _this.data1.pageSize//页面大小
          _this.dogList = res.data.data.lists
        }
      }
    }).catch((res) => {
      console.log(res)
    })
  }
//发布中
  function release(_this) {
    if (!_this.data2.string) {
      delete _this.data2.string
    }
    _this.$axios({
      url: '/biz/adoption/findPublishingList',
      method: 'get',
      params: _this.data2
    }).then((res) => {
      if (res.data.status == 200) {
        if (res.data.data == null) {
          _this.dogList1 = []
        } else {
          res.data.data.lists.map(item => {
            item.isCheckd = false;//默认未选中
          })
          _this.dogList1 = res.data.data.lists
          _this.pageData2.curPage= res.data.data.pageNumber//当前页吗
          _this.pageData2.totalCount= res.data.data.totalRecord//总条数
          _this.pageData2.pageSize= _this.data2.pageSize//页面大小
        }
      }
    }).catch((res) => {
      console.log(res)
    })
  }
//待确认领养
  function endingpAdoption(_this) {
    if (!_this.data3.string) {
      delete _this.data3.string
    }
    _this.$axios({
      url: '/biz/adoption/findAdoptabledList',
      method: 'get',
      params: _this.data3
    }).then((res) => {
      if (res.data.status == 200) {
        if (res.data.data == null) {
          _this.dogList2 = []
        } else {
          res.data.data.lists.map(item => {
            item.isCheckd = false;//默认未选中
          })
          _this.dogList2 = res.data.data.lists
          _this.pageData3.curPage= res.data.data.pageNumber//当前页吗
          _this.pageData3.totalCount= res.data.data.totalRecord//总条数
          _this.pageData3.pageSize= _this.data3.pageSize//页面大小
        }
      }
    }).catch((res) => {
      console.log(res)
    })
  }
  //已认领
  function claimed(_this) {
    if (!_this.data4.string) {
      delete _this.data4.string
    }
    _this.$axios({
      url: '/biz/collection/findHandleCollectionList',
      method: 'get',
      params: _this.data4
    }).then((res) => {
      if (res.data.status == 200) {
        if (res.data.data == null) {
          _this.dogList3 = []
        } else {
          res.data.data.lists.map(item => {
            item.isCheckd = false;//默认未选中
          })
          _this.dogList3 = res.data.data.lists
          _this.pageData4.curPage= res.data.data.pageNumber//当前页吗
          _this.pageData4.totalCount= res.data.data.totalRecord//总条数
          _this.pageData4.pageSize= _this.data4.pageSize//页面大小
        }
      }
    }).catch((res) => {
      console.log(res)
    })
  }
//已领养
  function hasBeenAdopted(_this) {
    if (!_this.data5.string) {
      delete _this.data5.string
    }
    _this.$axios({
      url: '/biz/adoption/findAdoptabledList',
      method: 'get',
      params: _this.data5
    }).then((res) => {
      console.log(res.data)
      if (res.data.status == 200) {
        if (res.data.data == null) {
          _this.dogList4 = []
        } else {
          res.data.data.lists.map(item => {
            item.isCheckd = false;//默认未选中
          })
          _this.dogList4 = res.data.data.lists
          _this.pageData5.curPage= res.data.data.pageNumber//当前页吗
          _this.pageData5.totalCount= res.data.data.totalRecord//总条数
          _this.pageData5.pageSize= _this.data5.pageSize//页面大小
        }
      }
    }).catch((res) => {
      console.log(res)
    })
  }
//已处理
  function haveToDeal(_this) {
    if (!_this.data6.string) {
      delete _this.data6.string
    }
    _this.$axios({
      url: '/biz/collection/findHandleCollectionList',
      method: 'get',
      params: _this.data6
    }).then((res) => {
      console.log(res.data)
      if (res.data.status == 200) {
        if (res.data.data == null) {
          _this.dogList5 = []
        } else {
          res.data.data.lists.map(item => {
            item.isCheckd = false;//默认未选中
          })
          _this.dogList5 = res.data.data.lists
          _this.pageData6.curPage= res.data.data.pageNumber//当前页吗
          _this.pageData6.totalCount= res.data.data.totalRecord//总条数
          _this.pageData6.pageSize= _this.data6.pageSize//页面大小
        }
      }
    }).catch((res) => {
      console.log(res)
    })
  }

</script>

<style scoped>

</style>
