package com.geofort.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.geofort.database.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handles the GeoFort contact page and contact form submission.
 */
@WebServlet("/contact")
public class ContactServlet extends HttpServlet {

    /**
     * Serialization version identifier.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Position of the name value in the SQL statement.
     */
    private static final int NAME_PARAMETER_INDEX = 1;

    /**
     * Position of the email value in the SQL statement.
     */
    private static final int EMAIL_PARAMETER_INDEX = 2;

    /**
     * Position of the message value in the SQL statement.
     */
    private static final int MESSAGE_PARAMETER_INDEX = 3;

    @Override
    protected final void doGet(
            final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/contact.jsp")
                .forward(request, response);
    }

    @Override
    protected final void doPost(
            final HttpServletRequest request,
            final HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        String sql = """
                INSERT INTO contact_messages
                    (name, email, message)
                VALUES
                    (?, ?, ?)
                """;

        try (
            Connection connection =
                    createConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {
            statement.setString(
                    NAME_PARAMETER_INDEX,
                    name);

            statement.setString(
                    EMAIL_PARAMETER_INDEX,
                    email);

            statement.setString(
                    MESSAGE_PARAMETER_INDEX,
                    message);

            statement.executeUpdate();

            request.setAttribute(
                    "successMessage",
                    "Your message was submitted successfully.");

            request.getRequestDispatcher(
                    "/WEB-INF/views/contact.jsp")
                    .forward(request, response);

        } catch (SQLException exception) {
            throw new ServletException(
                    "Unable to save contact message.",
                    exception);
        }
    }

    /**
     * Creates a database connection.
     *
     * @return database connection
     * @throws SQLException when the connection cannot be created
     */
    protected Connection createConnection()
            throws SQLException {

        return DatabaseConnection.getConnection();
    }
}
