<template>
	<view>
		<view class="zh">消息通知</view>
		<view class="play0">
			<view class="play" v-for="(user,i) in listObj" :key="i">
				<view class="play1">
					{{user}}
				</view>
				<switch checked />
			</view>
			<view class="zh">账号和隐私</view>
			<view class="play2">账号和绑定设置</view>
			<view class="play2">会员登录设备管理</view>
			<view class="play2">消息和隐私设置</view>
			<view class="play2">登录保护</view>
			<view class="play2">系统权限设置</view>
 
		</view>
 
	</view>
</template>
 
 
<script>
	export default {
		data() {
			return {
				listObj: [
					"咨询更新回复",
					"关注医生消息",
					"活动消息",
					"复诊提醒消息"
				
				],
				
			}
 
		},
		methods: {
				
			}
		}
 
	
</script>
 
<style>
	.zh{
			 margin-top: 16rpx;
			text-indent: 16rpx;
		}
		
		
	.play {
		display: flex;
		background-color: #ffffff;
		justify-content: space-between;
		align-items: center;
		padding: 0 32rpx;
		border-bottom: 1rpx solid #e0e0e0;
	}
 
	.play1 {
		margin: 1upx 10rpx;
		line-height: 140rpx;
		text-align: left;
		color: #777;
		font-size: 32rpx;
 
	}
	
	.play2 {
		margin: 1upx 10rpx;
		line-height: 140rpx;
		text-align: left;
		color: #777;
		font-size: 32rpx;
		text-indent: 24rpx;
		background-color: #ffffff;
		border-bottom: 1rpx solid #e0e0e0;
	
	}
 
	.background {
		display: flex;
	}
 
	.uni-list-cell-db {
		width: 500px;
	}
 
	.ll1 {
		align-items: flex-end;
	}
</style>