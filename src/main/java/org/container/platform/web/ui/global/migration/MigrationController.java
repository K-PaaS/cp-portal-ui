package org.container.platform.web.ui.global.migration;

import jakarta.servlet.http.HttpServletRequest;
import org.container.platform.web.ui.common.ConstantsUrl;
import org.container.platform.web.ui.security.SecurityUtils;
import org.container.platform.web.ui.security.model.OAuthTokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Migration Controller 클래스
 *
 * @author ljw
 * @version 1.0
 * @since 2025.11.11
 */


@PreAuthorize("@authSecurity.checkIsGlobal()")
@Controller
public class MigrationController {
    private static final String BASE_URL = "global/migration/";

    @Autowired
    private SecurityUtils securityUtils;

    /**
     * Migration 페이지 이동(Go to the Migration page)
     *
     * @return the view
     */
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @GetMapping(value = ConstantsUrl.URI_CP_GLOBAL_MIGRATION)
    public String getMigration(Model model, HttpServletRequest request) {

        try {
            OAuthTokens tokens = securityUtils.getTokens();

            if (tokens != null) {
                model.addAttribute("accessToken", tokens.getAccessToken());
            } else {
                model.addAttribute("accessToken", "");
            }

            String currentLang = LocaleContextHolder.getLocale().getLanguage();
            model.addAttribute("lang", currentLang);

        } catch (Exception e) {
            model.addAttribute("accessToken", "");
            model.addAttribute("lang", "ko");
        }

        return BASE_URL + "migration";
    }
}