package org.container.platform.web.ui.global.federation;

import org.container.platform.web.ui.common.ConstantsUrl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Federation Controller 클래스
 *
 * @author Luna
 * @version 1.0
 * @since 2025.04.07
 */

@PreAuthorize("@authSecurity.checkIsGlobal()")
@Controller
public class FederationController {
    private static final String BASE_URL = "global/federation/";

    /**
     * Federation 페이지 이동(Go to the federation page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_GLOBAL_FEDERATION)
    public String getFederation() {
        return BASE_URL + "federation";
    }


}