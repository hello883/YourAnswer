var config={
	"el":"#ca-app",
	//tag表，课程名，课程号，课程图片
	"data":{
		"tagList":[],
		"name":"",
		"number":"",
		"img":"",
		courseId:"",
		selectedTagId: 0
	},
	"methods":{
		"insert":function(){
			// debugger
			//插入课程
			var url=serverUrl+"/course/insert"
			+"?img="+this.img
			+"&name="+this.name
			+"&number="+this.number
			
			console.log(url)
			axios.get(url)
			.then(function(response){
				// window.alert(response.data.msg)

				//查到课程id
				var url=serverUrl+"/course/selectByN"
				+"?s="+this.name
				console.log(url)
				axios.get(url)
				.then(function(response){
					window.alert(response.data.msg)
					
				}).catch(function(e){
					window.alert(e)
				})
				this.vue.courseid = response.data.data[0].id
					//根据课程id添加tag-course表
					var url=serverUrl+"/tagandcourse/add"
					+"?courseId"+this.courseId
					+"&tagId"+this.selectedTagId
					
					console.log(url)
					axios.get(url)
					.then(function(response){
						// window.alert(response.data.msg)
						window.alert("课程添加成功！")
					}).catch(function(e){
						window.alert("该课程已存在，添加失败")
					})
			}).catch()
			
		}
	},
	mounted() {
		// debugger
		//查找id列表
		var url = serverUrl + "/tag/getAll"
		axios.get(url)
		.then(function(response){
			this.vue.tagList = response.data.data
			console.log(this.vue.tagList);
		})
		.catch()
	}
	
}
var vue = new Vue(config)