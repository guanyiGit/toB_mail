<template>
  <!--轮播-->
  <div class="k1">
    <div class="swiper-container" id="swiper1">
      <div class="swiper-wrapper">
        <div class="swiper-slide" v-for="item in swrapImages"><a href="javascript:;"><img :src="item" alt=""></a></div>
      </div>
      <div class="swiper-pagination swiper-pagination1"></div>
    </div>
  </div>

</template>

<script>
  import Swiper from 'swiper';

  export default {
    name: "swrap",
    mounted: function () {
      var swiper1 = new Swiper('#swiper1', {
        loop: true,
        speed: 1300,
        autoplay: {
          delay: 2500,
          disableOnInteraction: false,
        },
        pagination: {
          el: '.swiper-pagination1',
          clickable: true,
        },
        observer: true,
        observeParents: true,
        keyboardControl:true
      });

    },
    data() {
      return {
        swrapImages: [//轮播图图片列表
          '/static/images/3.png',
          '/static/images/home/visitorHomePage1.png',
          '/static/images/home/visitorHomePage2.png',
          '/static/images/home/visitorHomePage3.png',
        ]
      }
    },
    methods: {}
  }
</script>

<style scoped>
  #swiper1 img {
    width: 100%;
  }

  .swiper-slide img {
    height: 400px;
  }

</style>
