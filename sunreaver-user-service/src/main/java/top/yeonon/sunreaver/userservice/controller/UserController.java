package top.yeonon.sunreaver.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yeonon.sunreaver.userservice.auth.TokenCheck;
import top.yeonon.sunreaver.userservice.service.IUserService;
import top.yeonon.sunreaver.userservice.vo.requestvo.QueryUserInfoRequestVo;
import top.yeonon.sunreaver.userservice.vo.requestvo.UpdateUserInfoRequestVo;
import top.yeonon.sunreaver.userservice.vo.requestvo.UserRegistrationByPasswordRequestVo;
import top.yeonon.sunreaver.userservice.vo.responsevo.QueryUserInfoResponseVo;
import top.yeonon.sunreaver.userservice.vo.responsevo.UpdateUserInfoResponseVo;
import top.yeonon.sunreaver.userservice.vo.responsevo.UserRegistrationByPasswordResponseVo;

/**
 * @Author yeonon
 * @date 2019/10/11 0011 23:34
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public UserRegistrationByPasswordResponseVo userRegistrationByPassword(@RequestBody UserRegistrationByPasswordRequestVo requestVo) {
        return userService.userRegistrationByPassword(requestVo);
    }

    @TokenCheck
    @GetMapping("/{id}")
    public QueryUserInfoResponseVo queryUserInfo(@PathVariable("id") Long id) {
        QueryUserInfoRequestVo requestVo = new QueryUserInfoRequestVo(id);
        return userService.queryUserInfo(requestVo);
    }

    @GetMapping("/{id}/other")
    public QueryUserInfoResponseVo queryOtherUserInfo(@PathVariable("id") Long id) {
        QueryUserInfoRequestVo requestVo = new QueryUserInfoRequestVo(id);
        return userService.queryOtherUserInfo(requestVo);
    }

    @TokenCheck
    @PutMapping("/{id}")
    public UpdateUserInfoResponseVo updateUserInfo(@PathVariable("id") Long id,
                                                   @RequestBody UpdateUserInfoRequestVo requestVo) {
        requestVo.setId(id);
        return userService.updateUserInfo(requestVo);
    }

}
