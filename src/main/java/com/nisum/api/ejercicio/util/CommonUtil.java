package com.nisum.api.ejercicio.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class CommonUtil {

	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";

	static final Pattern VALIDA_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean esValido(String emailStr) {
		Matcher matcher = VALIDA_EMAIL.matcher(emailStr);
		return matcher.find();
	}

	public static String getJWTToken(String username) {
		String secretKey = "nisumunKey";

//		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

//		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
//				.claim("authorities",
//						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 600000))
//				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		String token = Jwts.builder().setId("softtekJWT").setSubject(username).claim("authorities", secretKey)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();
		System.out.println(("Bearer " + token).length());
		return "Bearer " + token;
	}

	public static String obtenerJWTToken() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request.getHeader(HEADER).replace(PREFIX, "");
	}

}
