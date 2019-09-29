<template>
  <div>
    <!--轮播-->
    <div class="banner">
      <div class="swiper-container" id="swiper1">
        <div class="swiper-wrapper">
          <div class="swiper-slide" v-for="(item,i) in swrapImages" :key="i">
            <a href="javascript:;">
              <img :src="item" alt="###">
            </a>
          </div>
        </div>
        <div class="swiper-pagination"></div>
      </div>
    </div>
    <!--轮播END-->
    <div class="h12 bgw"></div>

  </div>
</template>
<script>
  import Swiper from 'swiper';

  export default {
    name: "swiper",
    data() {
      return {
        swrapImages: [//轮播图图片列表
          // '/static/images/37.jpg',
          // '/static/images/38.jpg',
          // '/static/images/38.jpg',
          // '/static/images/38.jpg'
        ]
      }
    },
    created(){
      this.$axios.get("/biz/tob-mall/pruduct/carousel")
      .then(res=>{
        if(res.data.success){
          this.swrapImages = res.data.obj;
        }
      }).finally(_=>{
        initSwrap()
      })
    },
    methods: {},
    mounted() {
      

   

    }

  }

function initSwrap(){
  let swiper1 = new Swiper('#swiper1', {
      loop:true,
      speed:1300, 
      // slidesPerView : 'auto',
      // loopedSlides :8,
      autoplay: {
        delay: 2500,
        disableOnInteraction: false,
      },
      pagination: {
        el: '.swiper-pagination',
         clickable :true,
        },
      observer: true,
      observeParents: true,
    });

    // swiper1.el.onmouseover = function(){
    //  swiper1.autoplay.stop();
    // };
    // swiper11.el.onmouseout = function(){
    //  swiper1.autoplay.start();
    // };
}

</script>

<style scoped>
#swiper1 img {
    width: 100%;
  }

.swiper-slide img {
  /*height: 550px;*/
}
</style>
