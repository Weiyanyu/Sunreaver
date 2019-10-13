package top.yeonon.yclouddisk.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yeonon.yclouddisk.common.exception.YCDException;
import top.yeonon.yclouddisk.common.response.ResponseStatus;
import top.yeonon.yclouddisk.common.response.ServiceResponse;
import top.yeonon.yclouddisk.entity.User;
import top.yeonon.yclouddisk.repository.UserRepository;
import top.yeonon.yclouddisk.service.IUserService;
import top.yeonon.yclouddisk.vo.requestvo.UserRegistrationByPasswordRequestVo;
import top.yeonon.yclouddisk.vo.responsevo.UserRegistrationByPasswordResponseVo;

/**
 * @Author yeonon
 * @date 2019/10/11 0011 23:33
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRegistrationByPasswordResponseVo userRegistration(UserRegistrationByPasswordRequestVo userRequestVo) throws YCDException {
        if (!userRequestVo.validate()) {
            throw new YCDException(ResponseStatus.REQUEST_PARAM_ERROR.getCode(),
                                    ResponseStatus.REQUEST_PARAM_ERROR.getDescription());
        }

        User user = new User(
                userRequestVo.getUsername(),
                DigestUtils.md5Hex(userRequestVo.getPassword()),
                userRequestVo.getSex()
        );

        user = userRepository.saveAndFlush(user);
        return new UserRegistrationByPasswordResponseVo(
                user.getId()
        );
    }
}