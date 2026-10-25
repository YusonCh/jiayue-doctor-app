<template>
	<view>
		<view class="title">
			<u--text text="数据管理" size="40" align="center"></u--text>
			
		</view>
		
		<view class="u-page">
				<view
					v-for="(item, index) in indexList"
					:key="index"
					class="main"
				>
				<view class="list-up">
					<u--input
						border="surround"
						v-model="item.receiveName"
						inputAlign="center"
					></u--input>
					<u--input
						border="surround"
						v-model="item.sendName"
						inputAlign="center"
					></u--input>
					<u--input
						border="surround"
						v-model="item.createTime"
						inputAlign="center"
					></u--input>
				</view>
				<view class="list-up">
					<u--input
						border="surround"
						v-model="item.sendUserName"
						inputAlign="center"
					></u--input>
					
					<u--input
						border="surround"
						v-model="item.receiveUserName"
						inputAlign="center"
					></u--input>
					
					<u--input
						border="surround"
						v-model="item.sendText"
						inputAlign="center"
					></u--input>
				</view>
				
				
				
				</view>
				
			</view>
			
			
		<view>
				<u-tabbar
					:value="value"
					:fixed="true"
					:placeholder="false"
					activeColor="#d81e06"
					:safeAreaInsetBottom="false"
				>
					
					<u-tabbar-item :text="text1" :icon="icon1" @click = 'click' ></u-tabbar-item>
					<u-tabbar-item :text="text2" :icon="icon1" @click = 'click'></u-tabbar-item>
					<u-tabbar-item :text="text3" :icon="icon1" @click = 'click'></u-tabbar-item>
					
				</u-tabbar>
		</view>
	</view>
</template>

<script>
	export default {
		onLoad(){
			uni.hideTabBar()
		},
		data() {
			return {
				show: false,
				columns: [
				    ['姓名','医院','科室']
				],
				
				name:'',
				hospital:'',
				department:'',
				username:'',
				level:'',
				
				indexList:[
					{
						"receiveName":"接收方",
						"sendName":"发送方",
						"createTime":"发送时间",
						"sendUserName":"发送id",
						"sendText":"消息内容",
						"receiveUserName":"接收id"
					}
				],
				indexList1: [
					{
						"id":"user1",
						"name":"123456",
						"hospital":"1",
						"department":"骨伤科",
					},
					{	"id":"user2",
						"name":"123456",
						"hospital":"1",
					},{
						"id":"user3",
						"name":"123456",
						"hospital":"1",
					},{
						"id":"user4",
						"name":"123456",
						"hospital":"1",
					},{
						"id":"user5",
						"name":"123456",
						"hospital":"1",
					},{
						"id":"user6",
						"name":"123456",
						"hospital":"1",
					},{
						"id":"user7",
						"name":"123456",
						"hospital":"1",
					},{
						"id":"doctor01",
						"name":"123456",
						"hospital":"2",
					},{
						"id":"doctor02",
						"name":"123456",
						"hospital":"2",
					},
					{
						"id":"doctor03",
						"name":"123456",
						"hospital":"2",
					},
					{
						"id":"doctor04",
						"name":"123456",
						"hospital":"1",
					},{
						"id":"doctor05",
						"name":"123456",
						"hospital":"1",
					},{
						"name":"陈山",
						"hospital":"第一人民医院",
						"department":"骨伤科",
					},{
						"name":"陈山",
						"hospital":"第一人民医院",
						"department":"骨伤科",
					}
				],
				urls: [
					'../../static/doctor2.png',
					
				],
								
				icon1:"home",
				icon2:"list-dot",
				icon3:"account",
				
				text1:"医生管理",
				text2:"数据管理",
				text3:"账户管理",
				
				value:1,
			}
		},
		onShow() {
			this.getdata();
		},
		methods: {
			save(){
				this.show = false
				
				uni.request({
					url:'http://10.0.2.2:8080/register_doctor/InsertDoctor',
					method:"POST",
					header: {
						"Content-Type": "application/json"
					},
					data:({
						name:this.name,
						hospital:this.hospital,
						department:this.department,
						level:this.level,
						username:this.username,
						password:"123456"
					}),
					success:function(res){
						console.log(res)
					},
				})
				
				uni.request({
					url:'http://10.0.2.2:8080/register_patient/signupByDoctorName',
					method:"POST",
					header: {
						"Content-Type": "application/json"
					},
					data:({
						
						department:this.department,
						level:this.level,
						user_name:this.username,
						usertype:2,
						password:"123456",
						connect:''
					}),
					success:function(res){
						console.log(res)
					},
				})
			},
			edit(username,password,user_type){
					console.log(username)
					uni.request({
						url:'http://10.0.2.2:8080/user/UpdateUser',
						method:"POST",
						header: {
							"Content-Type": "application/json"
						},
						data:({
							password:password,
							username:username,
							user_type:user_type
						}),
						success:function(res){
							console.log(res)
							
						}.bind(this)
					})
			},
			open1() {
			         // console.log('open');
			},
			close1() {
				this.show1 = false
			// console.log('close');
			},
			getdata(){
				uni.request({
					url:'http://10.0.2.2:8080/msg/SelectMsg',
					method:"GET",
					data:'',
					success:function(res){
						console.log(res)
						let length = res.data.length
						console.log(length)
						for(let i=0;i<length;i++){
							this.indexList.push(res.data[i])
						}
					}.bind(this)
				})
			},
			cancel(){
				this.show=false
			},
			shanchu(username,usertype){
				uni.request({
					url:'http://10.0.2.2:8080/user/DeleteUser',
					method:'POST',
					header: {
						"Content-Type": "application/json"
					},
					data:({
						username:username,
						user_type:usertype
					}),
					success:function(res){
						console.log(res)
						
					}.bind(this)
				})
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
		}
	}
</script>

<style>
	.button{
		display: flex;
		flex-direction: row;
	}
	.main{
		display: flex;
		flex-direction: column;
		margin-top: 10px;
		
	}
	
	.title{
		display: flex;
		flex-direction: column;
		justify-content: center;
	}
	
	.u-page{
		border: 1px solid;
	}
	
	.list-up{
		display: flex;
		flex-direction: row;
	}
	
	.list-down{
		display: flex;
		flex-direction: row;
	}
</style>
