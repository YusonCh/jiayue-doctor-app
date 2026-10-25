<template>
	<view class="all">
		<view class = "userback">
			<view class="userName">
				<u--text text="陈山  第一人民医院" size="45"></u--text>
				<view class="job">
					<u--text text="骨伤科" size="30"></u--text>
					<u--text text="副主任医师" size="30"></u--text>
				</view>
			</view>
			<view class="job_img">
				<image  class="userimg" src="../../static/doctor2.png"></image>
			</view>
			
		</view>
		
		<view class="main1">
			<view class="scription">
				<u--text text="擅长与简介" size="30"></u--text>
				<u-button class="custom-style1" @tap='introduce'>编辑</u-button> 
			</view>
			<view>
				<u--text :text="text" size="25"></u--text>
			</view>
		</view>
		
		<view class="main">
			<view class="main_button1" @click='personal' >
				<u-button class="custom-style" >个人信息</u-button>
			</view>
			
			<view class="main_button2" @click='about_us' >
				<u-button class="custom-style" >关于我们</u-button>
			</view>
			
			<view class="main_button3" @click='system_set' >
				<u-button class="custom-style" >系统设置</u-button>
			</view>
			
		</view>
		<view>
			<u-popup :show="show"  :round="10" mode="center" @close="close" @open="open">
			    <view  class="edit">
			       <u-input v-model="text"></u-input>
			    </view>
				<u-button class="custom-style1" @tap='edit'>编辑</u-button> 
			</u-popup>
		</view>
		<view class="main_button" @click='goshoucang' plain = "flase">
			<u-button type="error" shape="circle"  text="退出"></u-button>
		</view>
		
		<u-tabbar
			:value="value"
			
			:fixed="true"
			:placeholder="false"
			activeColor="#d81e06"
			:safeAreaInsetBottom="false"
		>
			<u-tabbar-item text="问诊记录" icon="home" @click = 'click' ></u-tabbar-item>
			<u-tabbar-item text="健康档案" icon="photo" @click = 'click'></u-tabbar-item>
			<u-tabbar-item text="我的" icon="account" @click = 'click'></u-tabbar-item>
		</u-tabbar>
		
	</view>
</template>
 
<script>
	export default {
		data() {
			return {
				value:2,
				usertype:2,
				
				show:false,
				
				username : '',
				
				text:''
			}
		},
		onLoad(){
			uni.hideTabBar()
		},
		onReady() {
			this.getData()
		},
		methods: {
			getData(){
				this.username = uni.getStorageSync("username")
				const self = this;
				uni.request({
				 	url:'http://10.0.2.2:8080/register_doctor/SelectDoctor'+`?username=${this.username}`,
					//安卓模拟器的本机地址，等同于127.0.0.1
				 	method:"GET",
					header: {
						"Content-Type": "application/json"
					},
					data:'',
					success:function(res){
						//console.log(res.data)
						self.text = res.data[0].resume
			//			console.log(this.text)
					}
				})
			},
			edit(){
				this.show = false
				let user_name = uni.getStorageSync("username")
				uni.request({
					url:'http://10.0.2.2:8080/register_doctor/UpdateResume',
					method:"POST",
					header: {
						"Content-Type": "application/json"
					},
					data:{
						username:user_name,
						resume : this.text
					},
					
				})
			},
			 open() {
			          // console.log('open');
			        },
			        close() {
			          this.show = false
			          // console.log('close');
			        },
			click(e) {
				//console.log('click1', e);
				let type = uni.getStorageSync('usertype')
				console.log(type)
				if(e==0){
					if(type==0){
						uni.switchTab({
							url:'/pages/system/system_doctor',
						}),
						getApp().globalData.value = 0
					}else if(type==1){
						uni.switchTab({
							url:'/pages/main/index',
						}),
						getApp().globalData.value = 0
					}else{
						uni.switchTab({
							url:'/pages/main/index_doctor',
						}),
						getApp().globalData.value = 0
					}
				}
				else if(e==1){
					if(type==0){
						uni.switchTab({
							url:'/pages/system/system_data',
						}),
						getApp().globalData.value = 1
					}else if(type==1){
						uni.switchTab({
							url:'/pages/main/health'
						}),
						getApp().globalData.value = 1
					}else{
						uni.switchTab({
							url:'/pages/main/health_doctor'
						}),
						getApp().globalData.value = 1
					}
					
				}
				else{
					if(type==0){
						uni.switchTab({
							url:'/pages/system/system_user'
						}),
						getApp().globalData.value = 2
					}else if(type==2){
						uni.switchTab({
							url:'/pages/main/personal_doctor',
						}),
						getApp().globalData.value = 2
					}else{
						uni.switchTab({
							url:'/pages/main/personal_patient',
							
						}),
						getApp().globalData.value = 2
					}
					
				}
				
			},
			introduce(){
				this.show = true
			},
			personal(){
				uni.navigateTo({
					 url:"/pages/function_doctor/personal_information"
				})
			},
			system_set(){
				uni.navigateTo({
					 url:"../function_doctor/system_set"
				})
			},
			about_us(){
				uni.navigateTo({
					 url:"../function_doctor/about"
				})
			},
			goshoucang(){
				uni.navigateTo({
					 url:"../index/index"
				})
			},
			
		}
	}
</script>
 
<style lang="scss" scoped>
	.custom-style {
			height: 70px;
			width: 400rpx;
	}
	.custom-style1{
		width: 200rpx;
	}
	.job_text{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		margin-left: 50px;
		margin-right: 50px;
		margin-top: 10px;
	}
	
	.scription{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		border-bottom: 1rpx solid;
	}
	
	.userback {
		display: flex;
		height: 150rpx;
		margin-left: 20px;
		margin-right: 20px;
		margin-top: 100rpx;
		border: 1rpx solid;

		
	}
	
	.job{
		display: flex;
		flex-direction: row;
		
	}
	
	.job_img{
		margin-right: 30px;
	}
	
	.userimg{
		display: flex;
		flex-direction: row;
		height: 100rpx;
		width: 100rpx;
		margin-top: 10rpx;
		margin-left: 50rpx;
		border-radius: 100%;
	}
	
	.userName{
		display: flex;
		flex-direction: row;
		font-size: 50rpx;
		width: 100%;
		height: 70px;
	    border: 0px solid #000;
		flex: 1;
		margin-left: 50rpx;
		margin-top: 10px;
		justify-content: center;
		flex-direction: column;
	}
	
	.main1{
		display: flex;
		flex-direction: column;
		margin: 50rpx;
		border: 1rpx solid;
		height: 200px;
	}
	
	.edit{
		width: 300px;
		border: 1px;
		margin: 10px;
	}
	
	.btn1{
		display: flex;
		flex-direction: column;
		background-color: #e0e0e0;
		padding: 24rpx;
		margin: 48rpx;
		border: 1 px solid #666;
		text-align: center;
		
	}
	.btn2{
		display: flex;
		flex-direction: column;
		background-color: #e0e0e0;
		padding: 24rpx;
		margin: 48rpx;
		border: 1 px solid #666;
		text-align: center;
		
	}
	
	.main{
		margin-top: 30rpx;
		margin-left: 50rpx;
		margin-right: 50rpx;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	
	.main_button1{
		display: flex;
		flex-direction: row;
		width: 100px;
	}
	
	.main_button2{
		display: flex;
		flex-direction: row;
		width: 100px;
	}
	
	.main_button3{
		display: flex;
		flex-direction: row;
		width: 100px;
	}
	
	.main_button4{
		display: flex;
		flex-direction: row;
		width: 100px;
	}
	
	.main_button{
		margin-left: 100rpx;
		margin-right: 100rpx;
		margin-top: 100rpx;
		color: #ff0000;
	}
	
 
</style>