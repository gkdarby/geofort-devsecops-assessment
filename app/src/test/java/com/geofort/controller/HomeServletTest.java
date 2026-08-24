package com.geofort.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Tests the home servlet.
 */
final class HomeServletTest {

    /**
     * Verifies that the home page is forwarded correctly.
     *
     * @throws Exception when servlet processing fails
     */
    @Test
    void shouldForwardToHomePage() throws Exception {

        HttpServletRequest request =
                Mockito.mock(HttpServletRequest.class);

        HttpServletResponse response =
                Mockito.mock(HttpServletResponse.class);

        RequestDispatcher dispatcher =
                Mockito.mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(
                "/WEB-INF/views/index.jsp"))
                .thenReturn(dispatcher);

        HomeServlet servlet = new HomeServlet();

        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }
}
