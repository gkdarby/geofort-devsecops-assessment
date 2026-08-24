<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Contact | GeoFort-Tech</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="contact-page">

    <header class="hero">
        <div class="hero-overlay">
            <h1>Welcome to GeoFort-Tech</h1>

            <p>
                Cloud Security, DevOps, and
                Application Security Solutions
            </p>
        </div>
    </header>

    <%@ include file="navigation.jsp" %>

    <main class="content contact-content">

        <section class="contact-card">

            <h2>Contact GeoFort-Tech</h2>

            <p class="contact-introduction">
                Complete the form below.
            </p>

            <%
                String successMessage =
                        (String) request.getAttribute(
                                "successMessage");

                if (successMessage != null) {
            %>

            <div class="success-message">
                <%= successMessage %>
            </div>

            <%
                }
            %>

            <form
                class="contact-form"
                action="${pageContext.request.contextPath}/contact"
                method="post">

                <div class="form-group">
                    <label for="name">
                        Name
                    </label>

                    <input
                        type="text"
                        id="name"
                        name="name"
                        maxlength="100"
                        required>
                </div>

                <div class="form-group">
                    <label for="email">
                        Email
                    </label>

                    <input
                        type="email"
                        id="email"
                        name="email"
                        maxlength="254"
                        required>
                </div>

                <div class="form-group">
                    <label for="message">
                        Message
                    </label>

                    <textarea
                        id="message"
                        name="message"
                        rows="6"
                        required></textarea>
                </div>

                <button
                    class="submit-button"
                    type="submit">

                    Send Message
                </button>

            </form>

        </section>

    </main>

    <footer>
        Created by Karl-Dee &copy; 2026
    </footer>

    <script src="${pageContext.request.contextPath}/js/script.js">
    </script>

</body>
</html>
