<template>
	<view class="index">
		<view class="new_box" style="margin-top: 20upx;">
			<view class="bbox">
				<view class="list-box" v-for="(item,index) in list" :key="index">
					<view class="list-ed">
						<image class="list-img" :src="item.src" mode="aspectFill"></image>
						<view class="list-right">
							<view class="list-name">{{item.title}}</view>
							<view class="list-da">
								<view>简介：<text>{{item.course}}</text></view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>
 
<script>
	export default {
		data() {
			return {
				list: [{
						course_id: "001",
						title: "赵医生",
						course: "内科医生",
			
						src : "../../static/logo.png"
					},
					{
						course_id: "002",
						title: "张医生",
						course: "外科医生",
					
						src : "../../static/logo.png"
					},
					{
						course_id: "003",
						title: "王医生",
						course: "脑科医生",
				
						src : "../../static/logo.png"
					},
					{
						course_id: "004",
						title: "于医生",
						course: "脑科医生",
				
						src : "../../static/logo.png"
					},
					{
						course_id: "005",
						title: "陈医生",
						course: "脑科医生",
				
						src : "../../static/logo.png"
					},
				],
				selectId:[],
			};
		},
		methods: {
			
		
		}
	};
</script>
 
<style>
	page{
		background-color: #eee;
	}
	
 
	.selde-q {
		
		width: 18upx;
		height: 18upx;
		border-radius: 50%;
		background: #ffffff;
		margin-left: 6upx;
	}
 
	.noselde-q {
		
		border: 1px solid #959595;
		width: 16upx;
		height: 16upx;
		border-radius: 50%;
		background: #FFFFFF;
		margin-left: 6upx;
	}
	.list-box {
		
		display: flex;
		flex-direction: column;
		background-color: #fff;
		margin: 0upx 16upx 16upx 16upx;
		padding: 16upx;
		border-radius: 10upx;
	}
 
	.list-ed {
		font-size: 40rpx;
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}
 
	.list-left {
		margin-right: 16upx;
	}
 
	.list-img {
		
		width: 160upx;
		height: 160upx;
		margin: 0upx 16upx 0upx 0upx;
	}
 
	.list-right {
		
		display: flex;
		flex-direction: column;
		width: 510upx;
		height: 180upx;
	}
 
	.list-right-img {
		width: 140upx;
		height: 38upx;
	}
 
	.noadsop {
		width: 120upx;
		height: 32upx;
	}
 
	.list-head {
		
		display: flex;
		justify-content: flex-end;
		margin-bottom: 10upx;
	}
 
	.list-name {
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-box-orient: vertical;
		-webkit-line-clamp: 2;
		margin-bottom: 10upx;
	}
 
	.list-da {
		
		display: flex;
		flex-direction: row;
		font-size: 26upx;
	}
 
	.list-da view {
		
		width: 50%;
	}
 
	.list-da view text {
		
		color: f0f0f0;
	}
 
	.sure {
		
		background: #FF6000;
		color: #FFFFFF;
		width: 600upx;
		height: 70upx;
		display: flex;
		justify-content: center;
		align-items: center;
		border-radius: 40upx;
		margin: 60upx auto;
	}
</style>