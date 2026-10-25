import tabBer from '../../uni_modules/utils/tabBar.js'

// tabBar文件为我们创建的tabBer对象数组


// 逻辑判断处理
let type = uni.getStorageSync('user_type')  == 1 ? 'PatientUserList' : 'DoctorUserList'

// midBtn 为设置tabBer中间的凸起，false为不凸起
const state = {
	list: tabBer.PatientUserList,

}


export default {
	namespaced: true,
	state
}

