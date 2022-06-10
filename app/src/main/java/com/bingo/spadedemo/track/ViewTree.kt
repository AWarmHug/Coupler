package com.bingo.spadedemo.track

class ViewTree(val type: String) {
    var id: String? = null
    var index: Int? = null
    var extra: String? = null

    var parent: ViewTree? = null

    var child: ViewTree? = null

    fun generatePath(): String {
        val sb = StringBuilder(type)
        id?.let {
            sb.append("-id:${it}")
        }
        index?.let {
            sb.append("-index:${it}")
        }
        extra?.let {
            sb.append("-extra:${it}")
        }
        return sb.toString()
    }

    fun isActivity(): Boolean {
        return type.endsWith("Activity")
    }

    fun isFragment(): Boolean {
        return extra?.let {
            return@let it.endsWith("Fragment")
        } ?: false
    }


    fun generateAllPath(): String {
        var topParent = parent
        while (topParent?.parent != null) {
            topParent = topParent.parent
        }
        val sb = StringBuilder()

        var viewTree = topParent

        //拼接当前viewTree外部的所有
        while (viewTree?.child != null) {
            if (viewTree.isActivity()){
                sb.append("$")
                sb.append(viewTree.type)
            }else if (viewTree.isFragment()){
                sb.append("$")
                sb.append(viewTree.extra)
            }else {
                if (viewTree.id!=null){
                    sb.append("$")
                    sb.append(viewTree.type)
                    sb.append("-id:${viewTree.id}")
                }
            }
            viewTree = viewTree.child
        }

        //拼接当前ViewTree
        sb.append("$")
        sb.append(type)
        id?.let {
            sb.append("-id:${it}")

        }

        return sb.toString()


    }

}