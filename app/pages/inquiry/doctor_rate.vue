<template>
	<view>
		<view class="tittle">
			<view>
				<u-text text="问诊评价" size="30"></u-text>
			</view>
			
		</view>
		
		<view class="table">
				<u-cell-group>
				    <u-cell title="医生响应速度">
				    	<u-rate slot="value" active-color="#FA3534" inactive-color="#b2b2b2" gutter="20" size="30" v-model="value1"></u-rate>
				    </u-cell>
					<u-cell title="医生服务态度">
						<u-rate slot="value" active-color="#FA3534" inactive-color="#b2b2b2" gutter="20" size="30" v-model="value2"></u-rate>
					</u-cell>
					<u-cell title="医生问诊准确度">
						<u-rate slot="value" active-color="#FA3534" inactive-color="#b2b2b2" gutter="20" size="30" v-model="value3"></u-rate>
					</u-cell>
					<u-cell title="所用药品是否合适">
						<u-rate slot="value" active-color="#FA3534" inactive-color="#b2b2b2" gutter="20" size="30" v-model="value4"></u-rate>
					</u-cell>
					<u-cell title="医生满意度">
						<u-rate slot="value" active-color="#FA3534" inactive-color="#b2b2b2" gutter="20" size="30" v-model="value5"></u-rate>
					</u-cell>
				</u-cell-group>

		</view>
		
		<view class="button">
			<view class="button1">
				<u-button @tap='rate'>
					提交评价
				</u-button>
			</view>
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				value1:0,
				value2:0,
				value3:0,
				value4:0,
				value5:0,
				
			}
		},
		methods: {
			rate(){
				uni.switchTab({
					url:"/pages/main/index"
				})
			//	console.log(this.value1)
		//		console.log(this.value2)
	//			console.log(this.value3)
//				console.log(this.value4)
			//	console.log(this.value5)
				
				let send_id = uni.getStorageSync("username")
				let receive_id = uni.getStorageSync("doctor_id")
				let doctor = uni.getStorageSync("doctor")
				console.log(doctor)
				//console.log(send_id)
				//console.log(receive_id)
				
				 uni.request({
				 	url:'http://10.0.2.2:8080/rate/doctorRate',
				 	//安卓模拟器的本机地址，等同于127.0.0.1
				 	method:"POST",
				 	header: {
				 		"Content-Type": "application/json"
				 	},
					data:({
						"doctor_user":receive_id,
						"patient_user":send_id,
						"name":"陈山",
						"answer_quality":1, 
						"answer_speed":this.value1, 
						"answer_attitude":this.value2, 
						"answer_quantity":this.value3, 
						"answer_influence":this.value4, 
						"answer_satisfaction":this.value5
					}),
				 	success:function(res){
				 		
				 	}
				 })
				
				
			}
		}
	}
</script>

<style>
	
.tittle{
	margin-top: 150px;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.table{
	
	margin-left: 50px;
	margin-right: 50px;
}

.button{
	margin-top: 20px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	
	width: 100%;
}

.button1{
	width: 100px;
}
</style>
