/lc/addLc:用户对帖子进行点赞或收藏，likeandcollect表增，并对于帖子的点赞数或收藏数加1  
input-LcDTO{userId,postId,type}  
data-null  
错误码-104  
  
/lc/cancelLc:用户对帖子进行取消点赞或收藏，likeandcollect表删，并对于帖子的点赞数或收藏数减1  
input-LcDTO{userId,postId,type}  
data-null  
错误码-105  
  
/lc/userCollect:获取用户的收藏帖子列表  
input-userId  
data-List<PostVO>  
  
/post/insertPost:用户发布帖子，post表增  
input-PostInsertDTO{type,typeId,title,text,img,ownerId}  
data-null  
错误码-106  
  
/post/getPostInfo：获取当前帖子的信息  
input-postId  
data-PostVO{type,title,text,img,ownerName,likeNum,collectNum,time,isL,isC}  
错误码-107  
  
/post/getUserPost:获取用户发布的帖子列表  
input-userId  
data-List<PostVO>  
  
/post/getPostList：获取当前页面需要展示的帖子列表(userId用于判断帖子列表中每个帖子该用户是否点赞/收藏)  
input-PostDTO{type,typeId,userId}  
data-List<PostVO>  
详细解释：课程下的主题帖列表(0, courseId, userId)  
        主题帖下的1级帖子列表(1, 主题帖的postId, userId)  
        1级帖子下的评论列表(2, 1级帖子的postId, userId)  