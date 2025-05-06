<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<html>
<head>
    <title>Errore nella Creazione</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            background-color: #293133;
            color: white;
        }
        .content-wrapper {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .message-box {
            background-color: #e74c3c;
            padding: 20px;
            border-radius: 8px;
            color: white;
            font-size: 20px;
            text-align: center;
            width: 50%;
        }
    </style>
</head>
<body>

    <div class="content-wrapper">
        <div class="message-box">
            <h1>Si è verificato un errore durante la creazione del team!</h1>
            <p>Verrai reindirizzato all'area riservata in breve...</p>
        </div>
    </div>

    <script>
        // Impostiamo il reindirizzamento a Area Admin dopo 3 secondi
        setTimeout(function() {
            window.location.href = "areaRiservata.jsp"; 
        }, 3000);
    </script>

</body>
</html>
