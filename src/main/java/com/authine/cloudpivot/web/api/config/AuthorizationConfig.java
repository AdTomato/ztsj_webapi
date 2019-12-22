package com.authine.cloudpivot.web.api.config;

import com.authine.cloudpivot.web.sso.filter.CustomBasicAuthenticationFilter;
import com.authine.cloudpivot.web.sso.handler.CustomWebResponseExceptionTranslator;
import com.authine.cloudpivot.web.sso.security.SSOToken;
import com.authine.cloudpivot.web.sso.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private CustomBasicAuthenticationFilter filter;

    @Autowired
    private SSOToken sSOToken;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(sSOToken.authAccessTokenConverter()));
        endpoints
                .userDetailsService(userDetailsService)
                .tokenStore(sSOToken.authTokenStore())
                .tokenEnhancer(tokenEnhancerChain).reuseRefreshTokens(false)
                .authenticationManager(authenticationManager)
                .exceptionTranslator(customWebResponseExceptionTranslator);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
                .addTokenEndpointAuthenticationFilter(filter);
    }

}
