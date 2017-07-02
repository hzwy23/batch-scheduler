package com.asofdate.hauth.authentication;

import com.asofdate.hauth.dto.RequestUserDTO;
import com.asofdate.hauth.entity.UserDetailsEntity;
import com.asofdate.hauth.service.UserDetailsService;
import com.asofdate.utils.Hret;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by hzwy23 on 2017/5/18.
 */
@Component
public class JwtService {
    static final long EXPIRATIONTIME = 432_000_000;     // 5天
    static final String SECRET = "hzwy23@163.com-jwt";  // JWT密码
    static final String TOKEN_PREFIX = "hzwy23";        // Token前缀
    static final String HEADER_STRING = "Authorization";// 存放Token的Header Key
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
    private static JwtService jwtService;

    private static String JWT_ROLES = "ROLE_ADMIN,AUTH_WRITE,ACTUATOR";

    @Autowired
    public UserDetailsService userDetailsService;

    private static String getTokenFromCookis(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        if (cookie != null) {
            for (int i = 0; i < cookie.length; i++) {
                if (cookie[i].getName().equals(HEADER_STRING)) {
                    return cookie[i].getValue();
                }
            }
        }
        return null;
    }

    public static void addAuthentication(HttpServletResponse response, String username) throws IOException {
        UserDetailsEntity userDetailsEntity = jwtService.userDetailsService.findById(username);

        if (userDetailsEntity == null) {
            logger.info("用户{}不存在:", username);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        // 生成JWT
        String JWT = Jwts.builder()
                // 保存权限（角色）
                .claim("authorities", JWT_ROLES)
                .claim("DomainId", userDetailsEntity.getDomain_id())
                .claim("OrgUnitId", userDetailsEntity.getOrg_unit_id())
                .claim("UserId", userDetailsEntity.getUser_id())
                // 用户名写入标题
                .setSubject(username)
                .setIssuer("hzwy23")
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();


        // 将 JWT 写入 body
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader(HEADER_STRING, JWT);
            response.addCookie(new Cookie(HEADER_STRING, JWT));
            response.getOutputStream().println(Hret.success(200, "success", JWT));
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            e.printStackTrace();
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) {

        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);
        if (token == null) {
            token = getTokenFromCookis(request);
        }

        if (token != null && !token.isEmpty()) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            // 拿用户名
            String user = claims.getSubject();

            // 得到 权限（角色）
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

            // 返回验证令牌
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, authorities) :
                    null;
        }
        return null;
    }

    public static RequestUserDTO getConnUser(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token == null) {
            token = getTokenFromCookis(request);
        }
        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            return new RequestUserDTO(
                    claims.get("DomainId", String.class),
                    claims.get("UserId", String.class),
                    claims.get("OrgUnitId", String.class));
        }
        return new RequestUserDTO();
    }

    @PostConstruct
    public void init() {
        jwtService = this;
        jwtService.userDetailsService = this.userDetailsService;
    }
}