<template>
	<view>
		<view class="main">
			<u--form :model="account" ref="uForm" class="form">
				<u-form-item label="用户名" prop="username" label-width="130">
					<u-input v-model="account.username" placeholder="请输入用户名" />
				</u-form-item>
			
				<u-form-item label="密码" prop="password" label-width="130">
					<u-input v-model="account.password" type="password" placeholder="请输入密码" />
				</u-form-item>
				
			</u--form>
				
				<view class="button">
					<u-form>
						<u-form-item>
							<u-button type="primary"  class="login" text="用户登录" @tap='login'></u-button>
							<u-button type="primary"  class="login" text="找回密码" @click="show0 = true"></u-button>
							<!-- <u-button type="primary"  class="login" text="医生登录" @tap='submit_doctor'></u-button> -->
						</u-form-item>
						<u-form-item>
							<u-popup :show="show" @close="close" @open="open" mode = "center">
							            <view>
							                <u--text size = "30" type = "info" text="请选择您的身份"></u--text>
		
											<u-button
												type="success"
												text="患者"
												:plain="true"
												customStyle="width: 100%"
												@click="signup_patient()"
											></u-button>
											<u-button
												type="success"
												text="医生"
												:plain="true"
												customStyle="width: 100%"
												@click="signup_doctor()"
											></u-button>
							            </view>
									</u-popup>
							<u-button type="primary" text="未有账号？点这里注册" @click="signup_patient()"></u-button>
						</u-form-item>
					</u-form>
				</view>
				
				
				<view >
						<u-modal :show="show0" :title="title" @confirm="confirm()">
							<view class="find">
								<u-input v-model="user" placeholder="请输入用户名" />
								<u-input v-model="id" placeholder="请输入注册身份证" />
							</view>
							
						</u-modal>
						
						<u-modal :show="show1" :title="title"  @confirm="confirm1()">
							<view class="find">
								<u-input v-model="password1" placeholder="请输入新密码" />
								<u-input v-model="password2" placeholder="请重复新密码" />
							</view>
							
						</u-modal>
						
					</view>
			</view>
			
			<view>
				<u-popup :show="show10" @close="close" mode="center" bgColor="red">
			        <view>
			            <u-alert title="验证失败" type = "error" effect="dark" :description = "description"></u-alert>
			        </view>
				</u-popup>
			</view>
			
			<view>
				<u-popup :show="show11" @close="close" mode="center" bgColor="red">
			        <view>
			            <u-alert title="验证失败" type = "error" effect="dark" description = "两次输入不一致"></u-alert>
			        </view>
				</u-popup>
			</view>
		
		</view>
	</view>
	
</template>

<script>
	import setting from '../../common/settings.js'
	import { mapMutations } from 'vuex';
	export default {
		data() {
			return {
				
				id:'',
				user:'',
				
				show10:false,
				show11:false,
				
				password1:'',
				password2:'',
				show0:false,
				show1:false,
				title:'找回密码',
				description:'身份证号不正确',
							
				account: {
					username: 'user1',
					password: '123456',
				},
				loading: true,
				show: false,
				msg: '123',
				
				
			}
		},
			
		methods: {
			...mapMutations(['setRoleId']),	
			
			find(){
				
			},
			
			confirm(){
				
				uni.request({
					url:'http://10.0.2.2:8080/user/SelectId',
					header: {
						"Content-Type": "application/json"
					},
					data:({
						username: this.user
					}),
					method: 'GET', 
					success: function(res) {
						//console.log(res.data)
						let id_sql = res.data
					//	console.log(id_sql)
					//	console.log(this.id)
						if(id_sql == this.id){
							this.show0 = false;
							this.show1 = true;
						}else{
							console.log("失败")
							this.show10 = true
						}
					}.bind(this)
				})
			},
			
			confirm1(){
				if(this.password1==this.password2){
					uni.request({
						url:'http://10.0.2.2:8080/user/UpdatePassword',
						header: {
							"Content-Type": "application/json"
						},
						data:({
							username :this.user,
							password :this.password1
						}),
						method: 'POST', 
						success: function(res) {
							//console.log(res.data)
							this.show1 = false;
						}.bind(this)
					})
				}else{
					this.show11 = true
				}
			},
			login(){
				
				uni.request({
					
				 	url:'http://10.0.2.2:8080/login',
					//安卓模拟器的本机地址，等同于127.0.0.1
				 	method:"POST",
					
				 	header: {
				 		"Content-Type": "application/json; charset=UTF-8"
				 	},

				 	data:JSON.stringify({
				 		username: this.account.username, 
				 		password: this.account.password,
				 	}),
				 	dataType: 'json',
				 	success: (res) => { //如果访问接口成功就会进入success
						uni.setStorageSync("username",this.account.username)
						console.log(uni.getStorageSync("username"))
				 		//console.log(res.data)
				 		// console.log("接口调用成功")	
						if(res.data.errno==0){
							
							var that = this;
							uni.request({
								url:'http://10.0.2.2:8080/user/getUserTypeByUserName'+`?username=${that.account.username}`,
								method:"GET",
								header: {
									"Content-Type": "application/json; charset=UTF-8"
								},
								data:'',
								dataType: 'json',
								success: (res1) => {
								//	console.log(that.account.username)
									uni.setStorageSync('usertype', res1.data);
									console.log(res1.data)
									if(res1.data==1){
										uni.switchTab({
										 	url:'/pages/main/index'
										 })
										// console.log(res1.data)
									}else if(res1.data==0){
										uni.switchTab({
										 	//url:'/pages/main/index_doctor'
											url:'/pages/system/system_doctor'
										 })
									}else{
										uni.switchTab({
										 	//url:'/pages/main/index_doctor'
											url:'/pages/main/index_doctor'
										 })
									}
								}
							})
						}
						else{
				 			console.log("登陆失败")
				 			console.log(this.account.username)
				 			console.log(this.account.password)
				 		}
						
				 	},
				 	fail:(err)=>{ //如果访问接口失败就会进入fail	
				 		console.log("调用接口失败"),
				 		this.msg = err
				 	}
				 })
				
				// uni.switchTab({
				// 	url:'/pages/main/index'
				// })
			},
			submit_doctor(){
				uni.navigateTo({
					url:"../main_doctor/index"
				})
			},
			signup_patient(){
				uni.navigateTo({
					url:"../signup/signup-patient"
				})
			},
			signup_doctor(){
				uni.navigateTo({
					url:"../signup/signup-doctor"
				})
			},
			open() {
			          // console.log('open');
			},
			close() {
			    this.show = false
				this.show10 = false
				this.show11 = false
			          // console.log('close');
		    }
		} 
	}
</script>

<style>
		.pop {
			width: 100%;
			background-color: #ffffffdb;
			display: flex;
			flex-direction: column;
			align-items: center;
		}
		.find{
			display: flex;
			flex-direction: column;
		}
		.popup-use {
			padding: 20px 20px;
			width: 200px;
			height: 50px;
			display: flex;
			justify-content: center;
			letter-spacing: 2px;
			color: #000000db;
		}
		
		.main{
			margin-top: 40%;
		}
		
		.form{
			margin-left: 150rpx;
			margin-right: 150rpx;
		}
		
		.button{
			margin-top: 50rpx;
			margin-left: 170rpx;
			margin-right: 170rpx;
		}
		
		
</style>
