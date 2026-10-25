<template>
	<view class="all">
		<view class = "userback">
			<view class="userName">
				<u--text :text="doctor" size="50"></u--text>
				<view class="job">
					<u--text :text="department" size="30"></u--text>
					<u--text :text="level" size="30"></u--text>
				</view>
			</view>
			<view class="job_img">
				<image  class="userimg" src="../../static/doctor2.png"></image>
			</view>
			
		</view>
		
		<view class="job_text">
			<view class="text1">
					<u--text text="保证医生真实" size="10"></u--text>
			</view>
			<view class="text2">
					<u--text text="医师响应及时" size="10"></u--text>
			</view>
			<view class="text3">
					<u--text text="不满意可申诉" size="10"></u--text>
			</view>
		</view>
		
		<view class="main">
			<view class="main_button1" @click='show = true' >
				<u-button class="custom-style" >图文就诊</u-button>
			</view>
			<view class="main_button2" @click='health'>
				<u-button class="custom-style" disabled='disabled'>电话就诊</u-button>
			</view>
			
			<view class="main_button4" @click='system_set'>
				<u-button class="custom-style" disabled='disabled'>视频就诊</u-button>
			</view>
			
			<view class="main_button3" @click='about_us' >
				<u-button class="custom-style" disabled='disabled'> 预约就诊(未开通)</u-button>
			</view>
			
		</view>
		
		<u-popup :show="show"  :round="10" mode="center" @close="close" @open="open">
		    <view  class="edit">
		       <u-input placeholder="请简单描述您的病症" v-model="ill"></u-input>
			   
		    </view>
			<u-button class="custom-style1" @tap='personal'>问诊</u-button> 
		</u-popup>
		
		<view class="main1">
			<view class="scription">
				<u--text text="擅长与简介" size="30"></u--text>
				<!-- <u-button class="custom-style1" >查看更多</u-button> -->
			</view>
			<view>
				<u--text text="腰椎病、腰椎椎管狭窄症、骨关节病、老年人骨关节炎、膝关节半月板损伤、膝关节骨性关节炎、膝关节炎、肩周炎、肩关节周围炎、关节损伤、滑膜炎、肩袖损伤、膝关节损伤、腰椎管狭窄、脊柱结核、股骨头坏死、创伤、颈椎病、骨质疏松、颈椎间盘突出
" size="30"></u--text>
			</view>
		</view>
		
		<view class="main_button" @click='goshoucang' plain = "flase">
			<u-button type="error" shape="circle"  text="换个医生"></u-button>
		</view>

	</view>
</template>
 
<script>
	export default {
		data() {
			return {
				show:false,
				ill:"",
				doctor:'',
				department:'',
				level:'',
				id:'',
			}
		},
		onLoad() {
			this.getdata()
		},
		methods: {
			getdata(){
				let temp = uni.getStorageSync("doctor")
				
				uni.request({
				 	url:'http://10.0.2.2:8080/register_doctor/SelectDoctorName'+`?name=${temp}`,
					//安卓模拟器的本机地址，等同于127.0.0.1
				 	method:"GET",
					header: {
						"Content-Type": "application/json"
					},
					data:'',
					success:function(res){
					//	console.log(res.data[0])
						let a = res.data[0]
						const self = this
						uni.request({
							url:'http://10.0.2.2:8080/register_doctor/SelectDoctor', 
							header: {
								"Content-Type": "application/json"
							},
							data:({
								username: a
							}),
							method: 'GET', 
							success:function(res1){
								console.log(res1.data[0].name)
								self.doctor = res1.data[0].name
								self.department = res1.data[0].department
								self.level = res1.data[0].level
							}.bind(this)
						})
						
					}.bind(this)
				})
				
				
			},
			open() {
			         // console.log('open');
			       },
			close() {
			         this.show = false
			         // console.log('close');
			       },
			personal(){
				let doctor = uni.getStorageSync("doctor")
				let patient = uni.getStorageSync("patient")
				console.log(doctor)
				console.log(patient)
				let date = new Date();
				// 使用Date对象的方法获取年月日
				let year = date.getFullYear(); //获取年份
				let month = date.getMonth() + 1; //获取月份，注意要加1
				let day = date.getDate(); //获取日期
				// 拼接成字符串，并补0
				let result = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
				let ill = this.ill
				uni.request({
					url:'http://10.0.2.2:8080/illness/SelectNumber',
					//安卓模拟器的本机地址，等同于127.0.0.1
					method:"GET",
					
					data:'',
					success:function(res){
						//console.log("sdasd"+res.data)
						let temp = res.data
						uni.setStorageSync("illid",temp)
					}
				})
				uni.request({
					url:'http://10.0.2.2:8080/illness/InsertIllness',
					//安卓模拟器的本机地址，等同于127.0.0.1
					method:"POST",
					header: {
						"Content-Type": "application/json"
					},
					data:({
						"illness":ill,
						"username":"user1",
						"name":patient,
						"doctor":doctor,
						"date":result
					})
				})
				uni.request({
					url:'http://10.0.2.2:8080/register_doctor/SelectDoctorName'+`?name=${doctor}`,
					//安卓模拟器的本机地址，等同于127.0.0.1
					method:"GET",
					header: {
						"Content-Type": "application/json"
					},
					data:'',
					success:function(res){
						console.log(res.data[0])
						let a = res.data[0]
						uni.setStorageSync("doctor_id",a)
					}
				})
				uni.navigateTo({
					 url:"../chat/index"
				})
			},
			health(){
				uni.navigateTo({
					 url:"../function/health"
				})
			},
			system_set(){
				uni.navigateTo({
					 url:"../function/system_set"
				})
			},
			about_us(){
				uni.navigateTo({
					 url:"../function/about"
				})
			},
			goshoucang(){
				uni.switchTab({
					 url:"../main/index"
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