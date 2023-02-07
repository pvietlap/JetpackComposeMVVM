package com.bautoidem.jetpackcomposemvvm.data.model


import com.bautoidem.jetpackcomposemvvm.domain.model.User
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class UserDTO(
    @SerializedName("avatar_url")
    @Expose
    val avatarUrl: String?,
    @SerializedName("events_url")
    @Expose
    val eventsUrl: String?,
    @SerializedName("followers_url")
    @Expose
    val followersUrl: String?,
    @SerializedName("following_url")
    @Expose
    val followingUrl: String?,
    @SerializedName("gists_url")
    @Expose
    val gistsUrl: String?,
    @SerializedName("gravatar_id")
    @Expose
    val gravatarId: String?,
    @SerializedName("html_url")
    @Expose
    val htmlUrl: String?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("login")
    @Expose
    val login: String?,
    @SerializedName("node_id")
    @Expose
    val nodeId: String?,
    @SerializedName("organizations_url")
    @Expose
    val organizationsUrl: String?,
    @SerializedName("received_events_url")
    @Expose
    val receivedEventsUrl: String?,
    @SerializedName("repos_url")
    @Expose
    val reposUrl: String?,
    @SerializedName("score")
    @Expose
    val score: Double?,
    @SerializedName("site_admin")
    @Expose
    val siteAdmin: Boolean?,
    @SerializedName("starred_url")
    @Expose
    val starredUrl: String?,
    @SerializedName("subscriptions_url")
    @Expose
    val subscriptionsUrl: String?,
    @SerializedName("type")
    @Expose
    val type: String?,
    @SerializedName("url")
    @Expose
    val url: String?
)

fun UserDTO.toUser() = User(avatarUrl, eventsUrl, followersUrl, followingUrl, gistsUrl, gravatarId, htmlUrl, id, login, nodeId, organizationsUrl, receivedEventsUrl, reposUrl, score, siteAdmin, starredUrl, subscriptionsUrl, type, url)