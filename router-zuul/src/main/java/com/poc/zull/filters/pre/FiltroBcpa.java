package com.poc.zull.filters.pre;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.poc.zull.exception.TokenException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/**
 * 
 * @author julio.cesar.maciel
 *
 */
public class FiltroBcpa extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(FiltroBcpa.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		final String token = request.getHeader("token");
		
			try {
				if (token == null) {
					throw new SignatureException("Token null");
				} else {
					Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
				}
			} catch (final SignatureException e) {
				RequestContext.getCurrentContext().setResponseStatusCode(500);
				RequestContext.getCurrentContext().setResponseBody("Token invalido.");
				throw new TokenException();
			}


		log.info(String.format("%s Req %s", request.getMethod(), request.getRequestURL().toString()));
		return null;
	}
}
