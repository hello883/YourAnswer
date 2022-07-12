var config = {
    el: "#app",
    data: {
        // 前面要传id和,课程所有信息
        id: 1,  //帖子id
        type: 0,  //0，1，2
        userId: 1,
        courseId: 25, //课程id
        // s: 2,
        courseInfo: {name: "离散数学",number: 0,courseId: 25},
        postInfo: [],
        // postInfo: [{type:"A",name: 11,title: "kk",likeNum:333,collectNum:44,time:"2022"},
        //             {type:"Q",name: 12,title: "kk",likeNum:33,collectNum:24,time:"2029"},
        //             {type:"Q",name: 12,title: "kk",likeNum:563,collectNum:24,time:"2021"}],
        tag: [],
        sortState: 1,
        test: 1
    },
    methods: {
        enterPost() {
            // *******************传id
            location.href = "../html/post.html?id=" + this.id
        },
        plus(){
            location.href = "../html/write.html?userId=" +this.userId
        },
        sortway(){
            var compare = function(){
                // debugger
                var state = this.sortState
                if(state==1){
                    state = "time"
                }
                else if(state==2){
                    state = "likeNum"
                }
                return function(obj1, obj2){
                    let v1 = obj1[state]
                    let v2 = obj2[state]
                    return v2 - v1
                }
            }
            
            this.postInfo = this.postInfo.sort(compare())
        }
    },
    mounted() {
        // debugger
        // 显示帖子
        var url = serverUrl + "/post/getPostList"
        +"?type=0"
        +"&typeId=25"
        +"&userId=1"
        axios.get(url)
        .then(function(response){
            // debugger
            this.vue.postInfo = response.data.data
            this.vue.test = !this.vue.test
            console.log(this.vue.postInfo);
        })
        .catch()


        // // 显示标题和课程号
        // var url2 = serverUrl + "/course/selectByN?s=" + this.s
        // axios.get(url2)
        // .then(function(response){
        //     console.log(response);
        //     this.vue.courseInfo = response.data.data
        //     console.log(this.vue.courseInfo);
        // })
        // .catch()

        // 显示标签
        var url3 = serverUrl + "/course/getTag?id=" + this.id
        axios.get(url3)
        .then(function(response){
            // debugger
            this.vue.tag = response.data.data
            console.log(this.vue.tag);
        })
        .catch()

        //排序
        var compare = function(){
            // debugger
            var state = this.sortState
            if(state==1){
                state = "time"
            }
            else if(state==2){
                state = "likeNum"
            }
            return function(obj1, obj2){
                let v1 = obj1[state]
                let v2 = obj2[state]
                return v2 - v1
            }
        }
        
        this.postInfo = this.postInfo.sort(compare())
        console.log("11");
        console.log(this.postInfo);
        console.log(this.postInfo[1]);
        

    }
}

var vue = new Vue(config)