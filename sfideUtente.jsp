<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Vector" %>
<%@ page import="it.unirc.nba.model.Sfida" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Lista Sfide Utente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <style>
        body { background-color: #222; color: white; }
        table { width: 100%; border: 2px solid #333; margin-top: 30px; }
        th, td { border: 2px solid #333; padding: 10px; text-align: center; }
        thead th { color: orange; }
        tbody tr:hover { background-color: orange; }
        .btn-action { background-color: orange; border: none; padding: 5px 10px; color: white; cursor: pointer; }
    </style>
</head>
<body>
<%@ include file="frame_nav.html" %>

<%
    Vector<Sfida> sfide = (Vector<Sfida>) request.getAttribute("sfide");
%>

<section id="sfide" class="page-section">
    <div class="container text-center">
        <h2 class="section-heading text-uppercase"><span style="color: orange;">Sfide Disponibili</span></h2>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Titolo</th>
                            <th>Descrizione</th>
                            <th>Partecipa</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (sfide != null && !sfide.isEmpty()) {
                            for (Sfida sfida : sfide) { %>
                                <tr>
                                    <td><%= sfida.getId() %></td>
                                    <td><%= sfida.getTitolo() %></td>
                                    <td><%= sfida.getDescrizione() %></td>
                                    <td>
                                        <form action="/privato/utente/richiediCreazioneTeam" method="get">
                                            <input type="hidden" name="idSfida" value="<%= sfida.getId() %>">
                                            <button type="submit" class="btn-action">PARTECIPA</button>
                                        </form>
                                    </td>
                                </tr>
                        <% } } else { %>
                            <tr>
                                <td colspan="4" style="color: red;">Nessuna sfida disponibile al momento.</td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
</body>
</html>