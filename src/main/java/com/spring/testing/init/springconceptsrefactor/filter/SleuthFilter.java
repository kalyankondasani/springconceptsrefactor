package com.spring.testing.init.springconceptsrefactor.filter;

import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SleuthFilter extends OncePerRequestFilter {

        private Tracer tracer;

        @Autowired
        public SleuthFilter(Tracer tracer) {
            this.tracer = tracer;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            if (!response.containsHeader("X-B3-SpanId")) {
                Span currentSpan = this.tracer.currentSpan();
                if (currentSpan != null) {
                    response.addHeader("X-B3-TraceId", getTraceId(currentSpan));
                    response.addHeader("X-B3-SpanId", getSpanId(currentSpan));
                }
            }
            filterChain.doFilter(request, response);
        }

        String getSpanId(Span currentSpan) {
            return currentSpan.context().spanIdString();
        }

        String getTraceId(Span currentSpan) {
            return currentSpan.context().traceIdString();
        }
    }
