import { createRouter, createWebHistory} from 'vue-router'
import login from "../views/login/index.vue"
import home from "../layout/index.vue"

const routes = [
    {
        path:"/login",
        name:"login",
        component:login
    },

    {
        path:"/",
        name:"home",
        component:home,
        children:[
            {
                path:"/list",
                name:"list",
                component:()=>import("../views/user/userList.vue"),
                children:[
                    {
                        path:"/chat",
                        name:"chat",
                        component:()=>import("../views/chat/index.vue")
                    }
                ]
            },

            {
                path:"/add",
                name:"add",
                component:()=>import("../views/user/userAdd.vue"),
                children: [
                    {
                        path:"/userAdd",
                        name:"userAdd",
                        component:()=>import("../views/user/add.vue")

                    }
                ]
            },
            {
                path:"/conversion",
                name:"conversion",
                component:()=>import("../views/conversion/index.vue")
            }

        ]
    }
]

const router = createRouter({

    history: createWebHistory(),

    routes //上面的路由数组

})

let beforeEach = router.beforeEach(((to, from, next) => {
    let token = localStorage.getItem("token");
    if(to.name != 'login'){
        if(!token){
            next({name:"login"})
        }else{
            next()
        }
    }else{
        next()
    }
}))

export default router;