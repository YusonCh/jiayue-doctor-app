
<template>
	<view class="websockets">
		<input type="text" class="message_text" v-model="message_itext"  />
		<button type="primary" @tap="clickRequest">点击发送请求</button>
		<text>{{response}}</text>
	</view>
</template>
 
<script>
	export default {
		onLoad() {
			// 进入这个页面的时候创建websocket连接,整个页面随时使用
			this.connectSocketInit();
		},
		data() {
			return {
				socketTask: null,
				// 确保websocket是打开状态,初始未打开
				is_open_socket: false,
				message_itext : "",
				response : ""
			}
		},
		// 关闭websocket,必须在实例销毁之前关闭,否则会是underfined错误
		beforeDestroy() {
			this.closeSocket();
		},
		methods: {
			// 创建websocket连接方法
			connectSocketInit() {
				// 创建一个this.socketTask对象,发送、接收、关闭socket都由这个对象操作
				this.socketTask = uni.connectSocket({
					// 【非常重要】必须确保你的服务器是成功的
					url: "ws://127.0.0.1:8804/ws",	// 此处地址最好不要天127.0.0.1,不然手机调试的时候忘记改就会出错
					success(data) {
						console.log("websocket连接成功");
					}
				});
 
				// 消息的发送和接收必须在正常连接打开中,才能发送或接收,否则会失败
				this.socketTask.onOpen((res) => {
					console.log("WebSocket连接正常打开中...");
					this.is_open_socket = true;
				})
				// 注：只有连接正常打开中 ，才能正常收到消息
				this.socketTask.onMessage((res) => {
					console.log("收到服务器内容：" + res.data);
					this.response = res.data
				});
				// 这里仅是事件监听,如果socket关闭了会执行
				this.socketTask.onClose(() => {
					console.log("已经被关闭了")
				})
				
			},
			// 关闭websocket,离开这个页面的时候执行关闭
			closeSocket() {
				this.socketTask.close({
					success(res) {
						this.is_open_socket = false;
						console.log("关闭成功", res)
					},
					fail(err) {
						console.log("关闭失败", err)
					}
				})
			},
			clickRequest() {
				if (this.is_open_socket) {
					// 发送消息,同时返回一组数据
					this.socketTask.send({
						data: this.message_itext,
						async success() {	// 同步接收
							console.log("消息发送成功");
						},
					});
				}
			}
		}
	}
</script>

<style>
	.message_text{
		border: #007AFF 2upx solid;
		margin: 5upx;
	}
</style>
