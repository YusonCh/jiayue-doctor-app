let PatientUserList = [
	// 首页
	{
		// 显示文字
		text: '首页',
		// 未点击图标
		iconPath: "../static/logo.png",
		// 点击后图标
		selectedIconPath: "../static/logo.png",
		// 页面路径
		pagePath: "/pages/main/index"
	},
	{
		// 未点击图标
		iconPath: "/static/logo.png",
		// 点击后图标
		selectedIconPath:"/static/logo.png",
		// 显示文字
		text: '健康档案',
	
		// 页面路径
		pagePath: "/pages/main/chat_information"
	},
	{
		// 未点击图标
		iconPath: "/static/logo.png",
		// 点击后图标
		selectedIconPath: "/static/logo.png",
		// 显示文字
		text: '用户',
		
		// 页面路径
		pagePath: "/pages/personal/index"
	}
	// 省略
]

let DoctorUserList = [
	// 首页
	{
		// 未点击图标
		iconPath: "../static/logo.png",
		// 点击后图标
		selectedIconPath: "../static/logo.png",
		// 显示文字
		text: '首页',
		
		// 页面路径
		pagePath: "/pages/main/index"
	},
	{
		// 未点击图标
		iconPath: "/static/logo.png",
		// 点击后图标
		selectedIconPath: "/static/logo.png",
		// 显示文字
		text: '首页',
		
		
		// 页面路径
		pagePath: "/pages/main/chat_information"
	},
	{
		// 未点击图标
		iconPath: "/static/logo.png",
		// 点击后图标
		selectedIconPath: "/static/logo.png",
		// 显示文字
		text: '首页',
		
		// 页面路径
		pagePath: "/pages/personal/doctor_index"
	}
	// 省略
]

export default [
	PatientUserList,
	DoctorUserList,
]