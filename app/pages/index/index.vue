<template>
	<view class="content">
		<image class="logo" src="/static//southeast.jpg"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
		<view style="padding: 20px;">
				 <!-- 以下形式在微信小程序会无效，APP和H5有效  -->
				<u-button class="login" text="登录" @click="login"></u-button>
				<!-- <u-button class="login" text="登录" @click="login2"></u-button> -->
				<!-- <u-button text="test" @click="login2"></u-button> -->
			</view>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				title: '家约医生问诊服务APP'
				
			}
		},
		onLoad() {

		},
		methods: {
			login() {
				uni.redirectTo({
					url:"../login/login"
				})
			},
			login2() {
				uni.redirectTo({
					url:"../personal/doctor"
				})
			},
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
	
	.login {
			color: #000000;
			width: 400rpx;
		}
		
	.signup {
			color: #000000;
			width: 400rpx;
		}
		
	
	
</style>
