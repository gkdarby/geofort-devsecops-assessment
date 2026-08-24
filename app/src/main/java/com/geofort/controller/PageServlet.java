package com.geofort.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handles dynamic GeoFort service pages.
 */
@WebServlet(urlPatterns = {
        "/cloud-security",
        "/devops",
        "/application-security"
})
public final class PageServlet extends HttpServlet {

    /**
     * Serialization version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Request attribute containing the page title.
     */
    private static final String PAGE_TITLE =
            "pageTitle";

    /**
     * Request attribute containing the page description.
     */
    private static final String PAGE_DESCRIPTION =
            "pageDescription";

    /**
     * Request attribute containing the section title.
     */
    private static final String SECTION_TITLE =
            "sectionTitle";

    /**
     * Request attribute containing the section content.
     */
    private static final String SECTION_CONTENT =
            "sectionContent";

    /**
     * Request attribute containing the background image.
     */
    private static final String BACKGROUND_IMAGE =
            "backgroundImage";

    /**
     * JSP view used to display service pages.
     */
    private static final String SERVICE_PAGE =
            "/WEB-INF/views/service-page.jsp";

    @Override
    protected void doGet(
            final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/cloud-security":
                configureCloudSecurityPage(request);
                break;

            case "/devops":
                configureDevOpsPage(request);
                break;

            case "/application-security":
                configureApplicationSecurityPage(request);
                break;

            default:
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND);
                return;
        }

        request.getRequestDispatcher(SERVICE_PAGE)
                .forward(request, response);
    }

    private void configureCloudSecurityPage(
            final HttpServletRequest request) {

        request.setAttribute(
                PAGE_TITLE,
                "Cloud Security");

        request.setAttribute(
                PAGE_DESCRIPTION,
                "Secure cloud architecture, identity, "
                + "networking, monitoring, and "
                + "compliance solutions.");

        request.setAttribute(
                SECTION_TITLE,
                "Cloud Security Services");

        request.setAttribute(
                SECTION_CONTENT,
                "GeoFort-Tech helps organizations "
                + "protect AWS accounts, networks, "
                + "workloads, data, and identities.");

        request.setAttribute(
                BACKGROUND_IMAGE,
                "cloud-security-background.png");
    }

    private void configureDevOpsPage(
            final HttpServletRequest request) {

        request.setAttribute(
                PAGE_TITLE,
                "DevOps");

        request.setAttribute(
                PAGE_DESCRIPTION,
                "Automation, CI/CD, containers, "
                + "infrastructure as code, and "
                + "reliable software delivery.");

        request.setAttribute(
                SECTION_TITLE,
                "DevOps Engineering");

        request.setAttribute(
                SECTION_CONTENT,
                "GeoFort-Tech builds automated delivery "
                + "pipelines using Jenkins, Maven, Docker, "
                + "Terraform, Kubernetes, SonarQube, "
                + "and Nexus.");

        request.setAttribute(
                BACKGROUND_IMAGE,
                "devops-background.png");
    }

    private void configureApplicationSecurityPage(
            final HttpServletRequest request) {

        request.setAttribute(
                PAGE_TITLE,
                "Application Security");

        request.setAttribute(
                PAGE_DESCRIPTION,
                "Secure coding, vulnerability scanning, "
                + "static analysis, and "
                + "application protection.");

        request.setAttribute(
                SECTION_TITLE,
                "Application Security Services");

        request.setAttribute(
                SECTION_CONTENT,
                "GeoFort-Tech integrates SAST, DAST, "
                + "dependency scanning, container scanning, "
                + "and quality gates into the software "
                + "development lifecycle.");

        request.setAttribute(
                BACKGROUND_IMAGE,
                "application-security-background.png");
    }
}
