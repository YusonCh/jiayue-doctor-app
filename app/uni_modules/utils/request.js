const request = (config) => {
	// 拼接完整的接口路径
	config.url = '127.0.0.1/api' + config.url;
	//判断是都携带参数
	if(!config.data){
		config.data = {};
	}
	let promise = new Promise(function(resolve, reject) {
		uni.request(config).then(responses => {
			// 异常
			if (responses[0]) {
				reject({message : "网络超时"});
			} else {
				let response = responses[1].data; // 如果返回的结果是data.data的，嫌麻烦可以用这个，return res,这样只返回一个data
				resolve(response);
			}
		}).catch(error => {
			reject(error);
		})
	})
	return promise;
};

export default request;
