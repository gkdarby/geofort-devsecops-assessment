<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>${pageTitle} | GeoFort-Tech</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body
    class="service-page"
    style="
        background-image:
            linear-gradient(
                rgba(0, 20, 45, 0.72),
                rgba(0, 20, 45, 0.86)
            ),
            url('${pageContext.request.contextPath}/images/${backgroundImage}');
    ">

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
            <h2>${pageTitle}</h2>

            <p>${pageDescription}</p>

            <h3>${sectionTitle}</h3>

            <p>${sectionContent}</p>
        </section>
    </main>

    <footer>
        Created by Karl-Dee &copy; 2026
    </footer>

    <script src="${pageContext.request.contextPath}/js/script.js">
    </script>

</body>
</html>
