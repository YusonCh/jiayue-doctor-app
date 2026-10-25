import requeset from "../../utils/request.js";

export function login(data){
    return requeset({
        url:"/login",
        method:"post",
        data:data
    })
}