package ru.netology.hw2_2

import org.junit.Test


import org.junit.Assert.*
import ru.netology.hw2_2.donut.Donut
import ru.netology.hw2_2.donut.Placeholder

class WallServiceTest {
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
    private val testPlaceholder = Placeholder ()
    private val testDonut = Donut(
        false,
        0,
        testPlaceholder,
        false,
        "duration"
            )
    private val testPost = Post(
        id = null,
        ownerId = 0,
        fromId = 0,
        createdBy = 0,
        date = 0,
        text = "",
        replyPostId = 0,
        friendsOnly = true,
        comments = testComments,
        copyright = testCopyright,
        likes = testLikes,
        reposts = testReposts,
        views = testViews,
        postType = "post", //post, copy, reply, postpone, suggest
        signerId = 0,
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
        assertTrue((WallService.add(testPost).id) != null)
    }

    @Test
    fun updateTrue() {
        WallService.add(testPost)
        assertTrue(WallService.updateByID(testPost.copy(id = 0, text = "test text")))
    }

    @Test
    fun updateFalse() {
        WallService.add(testPost)
        assertFalse(WallService.updateByID(testPost.copy(id = null)))
    }
}