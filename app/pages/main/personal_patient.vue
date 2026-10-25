<template>
	<view>
		<view class="user">
			<view class = "userback">
				<image  class="userimg" src="../../static/QQ图片20230407115216.jpg"></image>
				<view class="userName">一袭青衫</view>
			</view>
			<view class="userbutton">
					<u-button type="normal" shape="circle"  text="退出" @click="goshoucang"></u-button>
			</view>
		</view>
		
		<view class="main1">
			<view class="btn2">
				<view class="text1">
					<u-text text="患者信息" size="30" align="center"></u-text>
				</view>
				<view class="button" @click="show = true">
					<u-button>
						新建
					</u-button>
					<u-popup :show="show"  :round="10" mode="center" @close="close" @open="open">
						<view class="text3">
							<u-input  inputAlign="right" v-model="name" >
								<u--text
									text="姓名"
									slot="prefix"
									margin="0 3px 0 0"
									type="tips"
									bold:true
									size="30"
								></u--text>
								
							</u-input>
										
							<u-input  inputAlign="right" v-model="relationshp" >
								<u--text
									text="与用户关系"
									slot="prefix"
									margin="0 3px 0 0"
									type="tips"
									bold:true
									size="30"
								></u--text>
							</u-input>
						</view>
						
						<view class="text3">
							<u-input  inputAlign="right" v-model="age">
								<u--text
									text="年龄"
									slot="prefix"
									margin="0 3px 0 0"
									type="tips"
									bold:true
									size="30"
								></u--text>
								
							</u-input>
										
							<u-input  inputAlign="right" v-model="sex">
								<u--text
									text="性别"
									slot="prefix"
									margin="0 3px 0 0"
									type="tips"
									bold:true
									size="30"
								></u--text>
							</u-input>
						</view>
									
						<view class="text3">
							<u-input  inputAlign="right" v-model="height" >
								<u--text
									text="身高/cm"
									slot="prefix"
									margin="0 3px 0 0"
									type="tips"
									bold:true
									size="30"
								></u--text>
							</u-input>
										
							<u-input  inputAlign="right"  v-model="weight">
								<u--text
									text="体重/kg"
									slot="prefix"
									margin="0 3px 0 0"
									type="tips"
									bold:true
									size="30"
								></u--text>
							</u-input>
						</view>
									
									
						<u-input  inputAlign="right" v-model="allergy">
							<u--text
								text="过敏史"
								slot="prefix"
								margin="0 3px 0 0"
								type="tips"
								bold:true
								size="30"
							></u--text>
						</u-input>
										
						<u-input  inputAlign="right" v-model="medicine">
							<u--text
								text="用药史"
								slot="prefix"
								margin="0 3px 0 0"
								type="tips"
								bold:true
								size="30"
							></u--text>
						</u-input>
									
									
									
						<u-input  inputAlign="right" v-model="diet" >
							<u--text
								text="忌口"
								slot="prefix"
								margin="0 3px 0 0"
								type="tips"
								bold:true
								size="30"
							></u--text>
						</u-input>
										
						<u-input  inputAlign="right" v-model="illness">
							<u--text
								text="病史"
								slot="prefix"
								margin="0 3px 0 0"
								type="tips"
								bold:true
								size="30"
							></u--text>
						</u-input>
						
						<u-button @click="upDate()">新建</u-button>
					</u-popup>
				</view>
				
			</view>
			<view class="patient">
				<view  v-for="(item, index) in list" :key="index">
					<u-collapse
						@change="change"
						@close="close"
						@open="open"
					>
						{{item.name}}  {{item.relationship}}
						<u-collapse-item
							title="查看信息"
							name="item.tittle"
						>	
						<view class="text3">
							
							<u-input  inputAlign="right" v-model="item.age" >
								<u--text
									text="年龄"
									slot="prefix"
									margin="0 3px 0 0"
									type="tips"
									bold:true
									size="30"
								></u--text>
								
							</u-input>
										
							<u-input  inputAlign="right" v-model="item.sex">
								<u--text
									text="性别"
									slot="prefix"
									margin="0 3px 0 0"
									type="tips"
									bold:true
									size="30"
								></u--text>
							</u-input>
						</view>
									
						<view class="text3">
							<u-input  inputAlign="right" v-model="item.height">
								<u--text
									text="身高/cm"
									slot="prefix"
									margin="0 3px 0 0"
									type="tips"
									bold:true
									size="30"
								></u--text>
							</u-input>
										
							<u-input  inputAlign="right" v-model="item.weight">
								<u--text
									text="体重/kg"
									slot="prefix"
									margin="0 3px 0 0"
									type="tips"
									bold:true
									size="30"
								></u--text>
							</u-input>
						</view>
									
									
						<u-input  inputAlign="right" v-model="item.allergy">
							<u--text
								text="过敏史"
								slot="prefix"
								margin="0 3px 0 0"
								type="tips"
								bold:true
								size="30"
							></u--text>
						</u-input>
										
						<u-input  inputAlign="right" v-model="item.medicine">
							<u--text
								text="用药史"
								slot="prefix"
								margin="0 3px 0 0"
								type="tips"
								bold:true
								size="30"
							></u--text>
						</u-input>
									
									
									
						<u-input  inputAlign="right" v-model="item.diet">
							<u--text
								text="忌口"
								slot="prefix"
								margin="0 3px 0 0"
								type="tips"
								bold:true
								size="30"
							></u--text>
						</u-input>
										
						<u-input  inputAlign="right" v-model="item.illness">
							<u--text
								text="病史"
								slot="prefix"
								margin="0 3px 0 0"
								type="tips"
								bold:true
								size="30"
							></u--text>
						</u-input>
				
						
						<u-button @click="update(item.name,item.sex,item.weight,item.height,item.allergy,item.medicine,item.diet,item.illness)">编辑</u-button>
					</u-collapse-item>
									   
				</u-collapse>  
				</view>
				
			</view>
		</view>
		
		<view class="main">
			<view class="main_button1" @click='personal' >
				<u-button> 个人信息</u-button>
			</view>
			<view class="main_button3" @click='about_us'>
				<u-button> 关于我们</u-button>
			</view>
			<view class="main_button4" @click='system_set'>
				<u-button> 系统设置</u-button>
			</view>
			
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
			<u-tabbar-item text="首页" icon="home" @click = 'click' ></u-tabbar-item>
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
				
				type:0,
				disabled: true,
				show: false,
				list:[],
				show:false,
				
				name:'',
				relationshp:'',
				age:'',
				sex:'',
				height:'',
				weight:'',
				allergy:'',
				medicine:'',
				diet:'',
				illness:''
			}
		},
		onLoad(){
			uni.hideTabBar(),
			this.getdata()
		},
		methods: {
			open() {
			         // console.log('open');
			},
			close() {
			    this.show = false
			    // console.log('close');
			},
			upDate(){
				let user = uni.getStorageSync("username")
				console.log(user)
				uni.request({
					url:'http://10.0.2.2:8080/Health/InsertPatient',
					header: {
					 	"Content-Type": "application/json"
					},
					data:({
						user_name:user,
						name:this.name,
						sex:this.sex,
						height:this.height,
						weight:this.weight,
						allergy:this.allergy,
						diet:this.diet,
						medicine:this.medicine,
						age:this.age,
						relationship:this.relationshp
					}),
					method: 'POST', 
					success: function(res){
						this.list = []
						this.getdata()
					}.bind(this)
				})
				 this.show = false
			},
			getdata(){
				let name_temp = uni.getStorageSync("username")
				
				//console.log(name)
				
				uni.request({
						url:'http://10.0.2.2:8080/Health/SelectPatientByUserName',
						header: {
					 		"Content-Type": "application/json"
					 	},
					data:({
						username: name_temp
				  	}),
				  	method: 'GET', 
				  	success: function(res) {
				  		//console.log(res.data)
						let length = res.data.length
						for(let i=0;i<length;i++){
							let temp = res.data[i]
							
							uni.request({
								url:'http://10.0.2.2:8080/Health/GetPatient',
								header: {
								 	"Content-Type": "application/json"
								},
								data:({
									username:name_temp,
									name:temp
								}),
								method: 'GET', 
								success: function(res){
									console.log(res.data)
									this.list.push(res.data[0])
									this.list[i].index = i
									//this.list[i].name = res.data[i]
								}.bind(this)
							})
						}
				  		
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
			update(name,sex,height,weight,allergy,medicine,diet,illness){
				//console.log(illness)
				let name_temp = uni.getStorageSync("username")
				uni.request({
					url:'http://10.0.2.2:8080/Health/UpdatePatient',
				 	header: {
				 	 	"Content-Type": "application/json"
				 	},
				 	data:({
				 		username:name_temp,
				 		name :name,
				 		sex:sex,
				 		height:height,
				 		weight:weight,
				 		allergy:allergy,
						medicine:medicine,
						illness:illness,
				 		diet:diet
				 	}),
				 	method: 'POST', 
				 })
			},
			open1(){
			    // console.log('open');
			},
			close1() {
			    this.show = false
			    // console.log('close');
			},
			open(e) {
			    // console.log('open', e)
			},
			close(e) {
			    // console.log('close', e)
	        },
	        change(e) {
		      // console.log('change', e)
			},
			information(){
				uni.navigateTo({
					 url:"../function_patient/inquiry_record"
				})
			},
			patient_information(){
				uni.navigateTo({
					 url:"../function_patient/patient_information"
				})
			},
			personal(){
				uni.navigateTo({
					 url:"../function_patient/personal_information"
				})
			},
			health(){
				uni.navigateTo({
					 url:"../function_paatient/health"
				})
			},
			about_us(){
				uni.navigateTo({
					 url:"../function_patient/about"
				})
			},
			system_set(){
				uni.navigateTo({
					 url:"../function_patient/system_set"
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
 
<style>
	.user{
		display: flex;
		flex-direction: row;
		
	}
	.text3{
		display: flex;
		flex-direction: row;
	}
	.userback {
		display: flex;
		height: 150rpx;
		width: 100%;
		margin-top: 70rpx;
		
	}
	.patient{
		
	}
	
	.userimg{
		display: flex;
		flex-direction: row;
		height: 100rpx;
		width: 100rpx;
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
		justify-content: center;
		flex-direction: column;
	}
	.text1{
		text-align: center;
	}
	.main1{
		margin-left: 10px;
		margin-right: 10px;
		display: flex;
		flex-direction: column;
		border: 1px solid ;
	}
	
	.btn2{
		display: flex;
		flex-direction: row;
	/* 	background-color: #e0e0e0; */
		padding: 10rpx;
		margin: 20rpx;
		justify-content: space-between;
		
	}
	
	.main{
		margin-top: 30rpx;
		margin-left: 50rpx;
		margin-right: 50rpx;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	.userbutton{
		margin-top: 60px;
		margin-right: 40px;
	}
	.main_button1{
		display: flex;
		flex-direction: row;
	}
	
	.main_button2{
		display: flex;
		flex-direction: row;
	}
	
	.main_button3{
		display: flex;
		flex-direction: row;
	}
	
	.main_button4{
		display: flex;
		flex-direction: row;
	}
	
	.main_button{
		margin-left: 100rpx;
		margin-right: 100rpx;
		margin-top: 100rpx;
		color: #ff0000;
	}
	
 
</style>