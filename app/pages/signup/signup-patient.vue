<template>
	<view class="main">
		<u--form :model="form" ref="uForm" >
			<u-form-item label="用户名" prop="username" label-width="150">
				<u-input v-model="form.username" placeholder="请输入用户名" />
			</u-form-item>
		
			<u-form-item label="密码" prop="password" label-width="150">
				<u-input v-model="form.password" type="password" placeholder="请输入密码" />
			</u-form-item>
			
			<u-form-item label="密码" prop="password" label-width="150">
				<u-input v-model="form.password0" type="password" placeholder="请确认密码" />
				
			</u-form-item>
			
			<u-form-item label="联系方式" label-width="150">
				<u-input v-model="form.connect" placeholder="请输入联系方式" />
			</u-form-item>
			
			<u-form-item label="身份证后六位" label-width="150">
				<u-input v-model="form.id" placeholder="请输入居民身份证" />
			</u-form-item>		
		</u--form>
		
		<view>
				<u-button type="primary" text="注册" @click="submit"></u-button>
		</view>
		
		<u-popup :show="show" mode="center"  @close="close" >
		       <view class="u-alert-item">
		       	<u-alert
		       		description="两次密码不一致,请重新输入"
		       		type="error"
		       		showIcon
		       	></u-alert>
		       </view>
			</u-popup>
		
		

	</view>
</template>

<script>
	export default {
		data() {
			return {
				hospital:'医院',
				department:'科室',
				level:'职称',
				
				show:false,
				
				show_hospital: false,
				show_department:false,
				show_level:false,
				
				columns_hospital: [
				    []
				],
				columns_department: [
				    []
				],
				columns_level: [
				    []
				],
				form: {
					username: '',
					password: '',
					id:'',
					password0:'',
					connect:'',
					hospital:'',
					department:'',
					level:''
				},
				loading: true,
			}
		},
		onLoad(){
		},
		
		methods: {
			submit(){
				if(this.form.password==this.form.password0){
					uni.request({
					 url:'http://10.0.2.2:8080/register_patient/signupByUserName',
					  data: {
					    user_name: this.form.username, 
						password:this.form.password,
						usertype:1,
						id:this.form.id,
						connect:this.form.connect,
						department:this.form.department,
						level:this.form.level,
						hospital:this.form.hospital
					  },
					  method: 'POST',
					  header: {
					    'Content-Type': 'application/json' //设置请求头，根据你的后端要求
					  },
					  success: (res) => {
					    console.log(res.data); //打印后端返回的数据
					  },
					  fail: (err) => {
					    console.error(err); //打印错误信息
					  }
					});
				}else{
					console.log("密码不一致");
					this.show = true
				}
				// uni.redirectTo({
				// 	url:"../index/index"
				// }),
				

			},
			close() {
				this.show = false
				// console.log('close');
			}
		}
	}
</script>

<style>
	
	.main{
		margin-top: 100px;
		margin-left: 80px;
		margin-right: 80px;
	}
	
	.regigster{
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	
	.content{
		display: flex;
		flex-direction: column;
		justify-content: center;
	}
	
	.hospital_button{
		border: 1px;
	}
</style>
