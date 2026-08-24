<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>GeoFort-Tech</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="home-page">

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

    <main class="content">

        <section class="about-card">

            <h2>About GeoFort-Tech</h2>

            <p>
                GeoFort-Tech provides secure cloud, DevOps,
                and application security solutions for organizations.
            </p>

        </section>

    </main>

    <footer>
        Created by Karl-Dee &copy; 2026
    </footer>

    <script src="${pageContext.request.contextPath}/js/script.js">
    </script>

</body>
</html>
