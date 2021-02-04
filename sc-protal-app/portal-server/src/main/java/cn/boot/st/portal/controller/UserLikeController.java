package cn.boot.st.portal.controller;

import cn.boot.st.portal.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-04
 **/
@RestController
@RequestMapping(value = "app/user")
public class UserLikeController {


    @Autowired
    private UserLikeService userLikeService;

    /**
     * 点赞
     *
     * @param likedUserId 被点赞人id
     * @param likedPostId 点赞人id
     */
    @GetMapping(value = "saveLiked2Redis")
    public void saveLiked2Redis(String likedUserId, String likedPostId) {
        userLikeService.saveLiked2Redis(likedUserId, likedPostId);
        userLikeService.incrementLikedCount(likedUserId);
    }

    /**
     * 取消点赞
     *
     * @param likedUserId 被点赞人id
     * @param likedPostId 点赞人id
     */
    @GetMapping(value = "unlikeFromRedis")
    public void unlikeFromRedis(String likedUserId, String likedPostId) {
        userLikeService.unlikeFromRedis(likedUserId, likedPostId);
        userLikeService.decrementLikedCount(likedUserId);
    }


}
