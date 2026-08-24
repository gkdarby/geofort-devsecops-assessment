package com.geofort.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Tests the dynamic service-page servlet.
 */
final class PageServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private PageServlet servlet;

    /**
     * Creates test objects before each test.
     */
    @BeforeEach
    void setUp() {

        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        servlet = new PageServlet();

        when(request.getRequestDispatcher(
                "/WEB-INF/views/service-page.jsp"))
                .thenReturn(dispatcher);
    }

    /**
     * Tests the cloud security page.
     *
     * @throws Exception when servlet processing fails
     */
    @Test
    void shouldConfigureCloudSecurityPage() throws Exception {

        when(request.getServletPath())
                .thenReturn("/cloud-security");

        servlet.doGet(request, response);

        verify(request).setAttribute(
                "pageTitle",
                "Cloud Security");

        verify(dispatcher).forward(request, response);
    }

    /**
     * Tests the DevOps page.
     *
     * @throws Exception when servlet processing fails
     */
    @Test
    void shouldConfigureDevOpsPage() throws Exception {

        when(request.getServletPath())
                .thenReturn("/devops");

        servlet.doGet(request, response);

        verify(request).setAttribute(
                "pageTitle",
                "DevOps");

        verify(dispatcher).forward(request, response);
    }

    /**
     * Tests the application-security page.
     *
     * @throws Exception when servlet processing fails
     */
    @Test
    void shouldConfigureApplicationSecurityPage()
            throws Exception {

        when(request.getServletPath())
                .thenReturn("/application-security");

        servlet.doGet(request, response);

        verify(request).setAttribute(
                "pageTitle",
                "Application Security");

        verify(dispatcher).forward(request, response);
    }

    /**
     * Tests an unknown route.
     *
     * @throws Exception when servlet processing fails
     */
    @Test
    void shouldReturnNotFoundForUnknownPage()
            throws Exception {

        when(request.getServletPath())
                .thenReturn("/unknown");

        servlet.doGet(request, response);

        verify(response).sendError(
                HttpServletResponse.SC_NOT_FOUND);
    }
}
