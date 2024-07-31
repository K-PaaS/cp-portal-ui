package org.container.platform.web.ui.managements.secrets;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.container.platform.web.ui.common.ConstantsUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Secrets Controller 클래스
 *
 * @author jjy
 * @version 1.0
 * @since 2024.07.31
 **/
@Api(value = "SecretsController v1")
@Controller
public class SecretsController {

    private static final String BASE_URL = "secrets/";

    /**
     * Secrets 목록 페이지 이동(Go to the secrets list page)
     *
     * @return the view
     */
    @ApiOperation(value = "Secrets 목록 페이지 이동(Go to the secrets list page)", nickname = "getSecretsList")
    @GetMapping(value = ConstantsUrl.URI_CP_MANAGEMENTS_SECRETS )
    public String getSecretsList() {
        return BASE_URL + "secrets";
    }


    /**
     * Secrets 상세 페이지 이동(Go to the secrets details page)
     *
     * @return the view
     */
    @ApiOperation(value = "Secrets 상세 페이지 이동(Go to the secrets details page)", nickname = "getSecretsDetails")
    @GetMapping(value = ConstantsUrl.URI_CP_MANAGEMENTS_SECRETS + ConstantsUrl.URI_CP_DETAILS)
    public String getSecretsDetails() {
        return BASE_URL + "secretsDetail";
    }

}
