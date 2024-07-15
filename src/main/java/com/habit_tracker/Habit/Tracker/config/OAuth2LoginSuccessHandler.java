package com.habit_tracker.Habit.Tracker.config;

import com.habit_tracker.Habit.Tracker.entities.User;
import com.habit_tracker.Habit.Tracker.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component
@RestController
@RequestMapping("/api/auth")
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Value("${frontend.url}")
    private String frontendUrl;

    @Override
    @GetMapping("success")
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        //String email = attributes.getOrDefault("email", "").toString();
        //String name = attributes.getOrDefault("name", "").toString();
        String email = attributes.get("email") != null ? attributes.get("email").toString() : "";
        String name = attributes.get("name") != null ? attributes.get("name").toString() : "";


        Optional<User> userOptional = userService.findByEmail(email);
        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user = userService.saveUser(user);
        }

        Long user_id = user.getUser_id();

        //this.setAlwaysUseDefaultTargetUrl(true);
        //this.setDefaultTargetUrl(frontendUrl);
        //super.onAuthenticationSuccess(request, response, authentication);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(String.valueOf(user_id));
        response.getWriter().flush();
    }
}
