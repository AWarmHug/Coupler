package com.bingo.coupler

/*
{
    "com.bingo.spadedemo.ui.UserUtils":{
    "logout":{
    "content":"android.util.Log.d(\"Track\", \"method: 登出\");"
},
    "login":{
    "content":"android.util.Log.d(\"Track\", \"method: 登录-id:$1\");"
}
},
    "com.bingo.spadedemo.ui.MainActivity":{
    "onCreate":{
    "content":"android.util.Log.d(\"Track\", \"method: onCreate\");"
},
    "play":{
    "content":"android.util.Log.d(\"Track\", \"method: 玩\");"
}
}
}
 */
data class TrackInfo(val trackContents:Map<String,TrackContent>)

data class TrackContent(val content:String)
