package ru.netology.hwKotlin

import org.junit.Test
import org.junit.Assert.*

class WallServiceTest {

    private val testGeo = Geo (
        type = "",
        coordinates = "",
        place = null
            )
    private val testPostSource = PostSource(
        type = PostSource.Type.VK,
        platform = PostSource.Platform.ANDROID,
        data = PostSource.Data.POLL,
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
        canPublish = false
            )
    private val testRepost = Repost (
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
        Donut.Mode.ALL
            )
    private val testPost = Post(
        id = 0,
        ownerId = 0,
        fromId = 0,
        createdBy = 0,
        date = 0,
        text = "",
        replyOwnerId = 0,
        replyPostId = 0,
        friendsOnly = true,
        comments = testComments,
        copyright = testCopyright,
        likes = testLikes,
        repost = testRepost,
        views = testViews,
        postType = Post.Type.POST,
        postSource = testPostSource,
        attachments = null,
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

    private val testComment = Comment (
        id = 1,
        fromID = 1,
        targetId = 1,
        date = 1,
        text = " ",
        donut = testDonut,
        replyToUser = 1,
        replyToComment = 1,
        attachments = null,
        parentsStack = null,
        thread = null,
        isCommentDeleted = false
            )


    @Test
    fun add() {
        val testPost = testPost

        val addTestResult = WallService.add(testPost)

        assertNotEquals(0, addTestResult.id)
    }

    @Test
    fun updateTrue() {
        val testPost = WallService.add(testPost)

        val updateTrueTestResult = WallService.update(testPost)

        assertTrue(updateTrueTestResult)
    }

    @Test
    fun updateFalse() {
        val testPost = WallService.add(testPost)

        val updateFalseTestResult =
            WallService.update(testPost.copy(id = 0))

        assertFalse(updateFalseTestResult)
    }

    @Test
    fun shouldAddComment() {
        WallService.createComment(testComment)
    }

    @Test(expected = TargetNotFoundException::class)
    fun shouldThrow() {
        WallService.createComment(testComment.copy(targetId = 0))
    }
}