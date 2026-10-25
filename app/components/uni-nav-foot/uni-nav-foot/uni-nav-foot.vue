<template>
  <view class="bottombox">
    <view class="middlebox" @tap="toRectify">
      <i class="iconfont icon-saoma"></i>
    </view>
    <view class="tableft" :class="type == 'userhome' ? 'activebtn' : ''" @tap="toHome">
      <i class="iconfont icon-biaoqianlan_shouye"></i>
      <view>首页</view>
    </view>
    <view class="tabright" :class="type == 'usermy' ? 'activebtn' : ''" @tap="toMy">
      <i class="iconfont icon-wodedamaijihuo"></i>
      <view>我的</view>
    </view>
  </view>
</template>

<script>
export default {
  props: ['type'],
  data() {
    return {
    };
  },
  methods: {
    // 中间的
    toRectify() {
      uni.navigateTo({
        url: '/pages/tabbar/profit/profit',
      });
    },
    // 首页
    toHome() {
      uni.reLaunch({
        url: '/pages/tabbar/home/userhome',
      });
    },
    // 我的
    toMy() {
      uni.reLaunch({
        url: '/pages/tabbar/my/usermy',
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.bottombox {
  flex: none;
  height: 100rpx;
  background: #fff;
  display: flex;
  position: relative;

  .tableft,
  .tabright {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    border-top: 1rpx solid rgba(0, 0, 0, 0.05);
    box-sizing: border-box;
    > i {
      font-size: 48rpx;
      color: #bfbdbe;
    }

    > view {
      color: #939194;
      font-size: 24rpx;
      font-weight: 500;
      line-height: 1em;
      margin-top: 6rpx;
    }
  }

  .activebtn {
    > i {
      color: #384ef3;
    }

    > view {
      color: #384ef3;
    }
  }

  .middlebox {
    position: absolute;
    bottom: 0;
    left: 50%;
    transform: translate(-50%, -36rpx);
    width: 120rpx;
    height: 120rpx;
    border-radius: 50%;
    background: #384ef3;
    // background: linear-gradient(180deg, #2276e4 0%, #294fbf 100%);
    box-shadow: 0px 6px 12px 2px rgba(0, 0, 0, 0.16);
    z-index: 10;
    display: flex;
    justify-content: center;
    align-items: center;
    > image {
      width: 130rpx;
      height: 130rpx;
    }
    > i {
      font-size: 73rpx;
      color: #fff;
    }
  }
}
</style>

