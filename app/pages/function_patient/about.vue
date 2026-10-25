<template>
<view>
	<!--关于我们-广告图-企业理念--> 
	<view class='ad01'>
	  <image src='/static/cover.jpg' mode='widthFix' class='ad01-img'></image>
	</view> 
	
	<view class='qukuai'>
	  <view class='qukuai-shuxian'></view>
	  <text class='qukuai-biaoti'>关于我</text>
	</view>
	
	
	<navigator class='zixun'>
	 
	  <text class='zixun_biaoti'>服务内容：在线健康咨询</text>
	  
	</navigator>
	<navigator class='zixun'>
	 
	  <text class='zixun_biaoti'>版本：1.0.0</text>
	  
	</navigator>

	
	
	
	
</view>
</template>


<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>
/*关于我们-广告图-企业理念*/ 
.ad01 {
  margin: 4px;  /*外边距*/ 
  box-shadow: 0px 2px 2px gainsboro; /*阴影*/ 
}
.ad01-img {
  width: 100%;/*宽度*/
}

/*区块标题，整体样式*/
.qukuai {
  display: flex;   /*Flex布局*/
  margin: 2px;     /*外边距*/
  background-color: aliceblue; /*背景颜色*/
  align-items: center; /*垂直居中*/
}
/*区块标题 左侧竖线样式*/
.qukuai-shuxian {
  width: 3px;   /*宽度*/
  height: 15px; /*高度*/
  background-color: green; /*背景颜色*/
  margin: 5px; /*外边距*/
  box-shadow: 2px 2px 2px gainsboro; /*阴影*/
}
/*区块标题 中间文字样式*/
.qukuai-biaoti {
  color: gray;/*字体颜色*/
  text-shadow: 2px 2px 2px gainsboro;/*字体阴影*/
  flex: 1;           /*占据所有剩余空间*/
  margin-left: 1px;  /*左侧外边距*/
  align-self: center;/*垂直居中*/
}
/*区块标题 右侧文字样式*/
.qukuai-gengduo {
  color: gray; /*字体颜色*/
  text-shadow: 2px 2px 2px gainsboro;/*字体阴影*/
}

/*关于我们*/
.zixun {
  display: flex; /*Flex布局*/
  border-bottom: 1px solid gainsboro;/*下划线*/
  align-items: center;/*垂直居中*/;
  padding: 10px;/*内边距*/
  
}

.zixun_biaoti {
  flex-grow: 1;/*占据剩余空间*/
  padding: 5px;/*内边距*/
}
.zixun_tubiao {
  width: 16px; /*宽度*/
  height: 16px;/*高度*/
  align-self: center;/*居中*/
}

</style>
