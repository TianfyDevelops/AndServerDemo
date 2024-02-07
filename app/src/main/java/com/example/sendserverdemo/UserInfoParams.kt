package com.example.sendserverdemo

data class UserInfoParams constructor(
    @JvmField
    public var id: Int = 1,
    @JvmField
    public var mUserId: String = "",
    @JvmField
    public var mUserName: String = ""
){
    @JvmOverloads public constructor():this(1,"","")
}
