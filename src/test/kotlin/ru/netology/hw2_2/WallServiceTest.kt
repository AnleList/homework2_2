package ru.netology.hw2_2

import org.junit.Test


import org.junit.Assert.*
import ru.netology.hw2_2.enumClasses.*
import ru.netology.hw2_2.geo.Geo

class WallServiceTest {

    private val testGeo = Geo (
        type = "",
        coordinates = "",
        place = null
            )
    private val testPostSource = PostSource(
        type = PostSource_type.vk,
        platform = PostSource_platform.android,
        data = PostSource_data.poll,
        url = ""
    )
    private val testComments = Comments (
        count = 0,
        canPost = false,
        groupsCanPost = false,
        canClose = false,
        canOpen = false
            )
    private val testCopyright = Copyright (
        id = 0,
        link = "",
        name = "",
        type = ""
            )
    private val testLikes = Likes (
        count = 0,
        userLikes = false,
        canLike = false,
        can_publish = false
            )
    private val testReposts = Reposts (
        count = 0,
        userReposted = false
            )
    private val testViews = Views (
        count = 0
            )
    private val testPlaceholder = 0
    private val testDonut = Donut(
        false,
        0,
        testPlaceholder,
        false,
        Donut_editMode.all
            )
    private val testPost = Post(
        id = 0,
        ownerId = 0,
        fromId = 0,
        createdBy = 0,
        date = 0,
        text = "",
        reply_owner_id = 0,
        replyPostId = 0,
        friendsOnly = true,
        comments = testComments,
        copyright = testCopyright,
        likes = testLikes,
        reposts = testReposts,
        views = testViews,
        postType = Post_postType.post, //post, copy, reply, postpone, suggest
        postSource = testPostSource,
        geo = testGeo,
        signerId = 0,
        copyHistory = null,
        canPin = false,
        canDelete = false,
        canEdit = false,
        isPinned = false,
        markedAsAds = false,
        isFavorite = false,
        donut = testDonut,
        postponedId = 0
    )


    @Test
    fun add() {
        assertEquals("just added post text",
            (WallService.add(testPost.copy(text = "just added post text"))).text)
    }

    @Test
    fun updateTrue() {
        WallService.add(testPost)
        assertTrue(WallService.update(testPost.copy(id = 0, text = "test text")))
    }

    @Test
    fun updateFalse() {
        WallService.add(testPost)
        assertFalse(WallService.update(testPost.copy(id = 0)))
    }
}