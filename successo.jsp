<%@ page session="false" %>
<html>
<head>
    <title>Operazione Team</title>
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
            background-color: #27ae60;
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
            <h1>Operazione completata con successo!</h1>
            <p>Verrai reindirizzato all'area riservata in breve...</p>
        </div>
    </div>

    <script>
        //reindirizzamento dopo 3 secondi
        setTimeout(function() {
            window.location.href = "areaRiservata.jsp"; 
        }, 3000);
    </script>

</body>
</html>

