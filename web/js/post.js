var config = {
	"el": "#post-app",
	"data": {"postInfo":{},
			postList:[{c:false,
						collectNum:0,
						img:"(...)",
						l:false,
						likeNum:0,
						ownerName:"(...)",
						text:"(...)",
						time:"(...)",
						title:"(...)",
						type:"2",
                        state: false,
                        list: [{ui: "hh", coment:"kkkk"},{ui: "hh", coment:"kkkk"}]},
						{c:false,
						collectNum:0,
						img:"(...)",
						l:false,
						likeNum:0,
						ownerName:"(...)",
						text:"(...)",
						time:"(...)",
						title:"(...)",
						type:"2",
                        state: false,
                        list: [{ui: "hh", coment:"kkkk"},{ui: "hh", coment:"kkkk"}]}],
            // ,
            // state: [false],
            // slectedid: 0
			},
	"methods": {
					"getPostList": function(id) {
						var url = serverUrl + "/post/getPostList?type=2&typeId=" + id +"&userId=3"
						console.log(url)
						axios.get(url)
							.then(function(response) {
								var serverResult = response.data;
								var list = serverResult.data;
								console.log(list[0])
								//在then函数中访问config.data中变量,必须加this.vue
								this.vue.postList=list
							})
							.catch(function(e) {
								window.alert("联网出错了")
							})
					},
                    fold(p){
                        p.state = !p.state
                        // var c = document.querySelector(".comments")
                        // if(this.state == false){
                        //     c.style.display = "true"
                        // }else{
                        //     c.style.display = "none"
                        // }
                    },
					trans(p){
						p = !p
						console.log("kkk");
						console.log(p.l);
						console.log(this.postInfo[1].l);
					}

	},

	"mounted":function(){
		var p = location.search
		var array = p.split("=")
		var id = array[1]
		this.id = id
		console.log(id)
		
		this.getPostList(id)
		
		var url = serverUrl + "/post/getPostInfo?id=" + id
		console.log(url)
		axios.get(url)
		.then(function(response){
			var serverResult=response.data
			var postInfo=serverResult.data
			console.log(postInfo)
			this.vue.postInfo=postInfo
		})
		.catch()
		
	}
}

var vue = new Vue(config)