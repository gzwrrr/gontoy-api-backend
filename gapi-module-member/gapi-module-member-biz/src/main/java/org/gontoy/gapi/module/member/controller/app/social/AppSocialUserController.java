package org.gontoy.gapi.module.member.controller.app.social;

import org.gontoy.gapi.framework.common.enums.UserTypeEnum;
import org.gontoy.gapi.framework.common.pojo.CommonResult;
import org.gontoy.gapi.module.member.controller.app.social.vo.AppSocialUserBindReqVO;
import org.gontoy.gapi.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import org.gontoy.gapi.module.member.convert.social.SocialUserConvert;
import org.gontoy.gapi.module.system.api.social.SocialUserApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.gontoy.gapi.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 App - 社交用户")
@RestController
@RequestMapping("/system/social-user")
@Validated
public class AppSocialUserController {

    @Resource
    private SocialUserApi socialUserApi;

    @PostMapping("/bind")
    @Operation(summary = "社交绑定，使用 code 授权码")
    public CommonResult<Boolean> socialBind(@RequestBody @Valid AppSocialUserBindReqVO reqVO) {
        socialUserApi.bindSocialUser(SocialUserConvert.INSTANCE.convert(getLoginUserId(), UserTypeEnum.MEMBER.getValue(), reqVO));
        return CommonResult.success(true);
    }

    @DeleteMapping("/unbind")
    @Operation(summary = "取消社交绑定")
    public CommonResult<Boolean> socialUnbind(@RequestBody AppSocialUserUnbindReqVO reqVO) {
        socialUserApi.unbindSocialUser(SocialUserConvert.INSTANCE.convert(getLoginUserId(), UserTypeEnum.MEMBER.getValue(), reqVO));
        return CommonResult.success(true);
    }

}
