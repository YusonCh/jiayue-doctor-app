<template>
	<view>
		<view class="tittle">
			<view>
				<u-text text="问诊报告" size="30"></u-text>
			</view>
			
		</view>
		<view>
				<view class="text3">
					<u-input  inputAlign="right">
						<u--text
							text="姓名"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
						></u--text>
					</u-input>
								
					<u-input  inputAlign="right">
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
					<u-input  inputAlign="right">
						<u--text
							text="身高"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
						></u--text>
					</u-input>
								
					<u-input  inputAlign="right">
						<u--text
							text="体重"
							slot="prefix"
							margin="0 3px 0 0"
							type="tips"
							bold:true
							size="30"
						></u--text>
					</u-input>
				</view>
								
				<u-input  inputAlign="right" v-model="ill">
					<u--text
						text="病因"
						slot="prefix"
						margin="0 3px 0 0"
						type="tips"
						bold:true
						size="30"
					></u--text>
				</u-input>
				
				<u-input  inputAlign="right"  v-model="medicine">
					<u--text
						text="用药"
						slot="prefix"
						margin="0 3px 0 0"
						type="tips"
						bold:true
						size="30"
					></u--text>
				</u-input>
							
							
							
				<u-input  inputAlign="right"  v-model="diet">
					<u--text
						text="忌口"
						slot="prefix"
						margin="0 3px 0 0"
						type="tips"
						bold:true
						size="30"
					></u--text>
				</u-input>
			
				<u-input  inputAlign="right"  v-model="cure">
					<u--text
						text="治疗方案"
						slot="prefix"
						margin="0 3px 0 0"
						type="tips"
						bold:true
						size="30"
					></u--text>
				</u-input>
		</view>
		
		<view class="button">
			<view class="button1">
				<u-button @tap='rate'>
					提交报告
				</u-button>
			</view>
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				ill:'',
				medicine:'',
				diet:'',
				cure:''
			}
		},
		methods: {
			rate(){
				
				
				uni.request({
							url:'http://10.0.2.2:8080/illness/UpdateIllness', 
							header: {
								"Content-Type": "application/json"
							},
							data:({
								reason:this.ill,
								medicine:this.medicine,
								diet:this.diet,
								cure:this.cure
							}),
							method: 'POST', 
							success: (res) => {
				//			console.log(res.data)
								uni.switchTab({
									url:"/pages/main/index"
								})
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

.text3{
		display: flex;
		flex-direction: row;
	}
</style>
