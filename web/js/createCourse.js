var config={
	"el":"#course-app",
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
				window.alert(response.data.msg)
			}).catch()
			
			//查到课程id
			var url=serverUrl+"/course/selectByN"
			+"?s="+this.name
			console.log(url)
			axios.get(url)
			.then(function(response){
				// window.alert(response.data.msg)
				this.vue.courseid = response.data.data[0].id
			}).catch()
			

			//根据课程id添加tag-course表
			var url=serverUrl+"/tagandcourse/add"
			+"?courseId"+this.courseId
			+"&tagId"+this.tagId
			
			console.log(url)
			axios.get(url)
			.then(function(response){
				// window.alert(response.data.msg)
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