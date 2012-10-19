basebase(head, body) ::= <<
<html>
<head>
$head$
</head>

<body>
<table>
    <tr>
        <td colspan=2 id="title-cell">
            <div id="title-text"><a href="/">uPaste<span id="alpha">alpha</span></a></div>
            <hr />
        </td>
    </tr>
    <tr>
        <td>
        $body$
        </td>
    </tr>
</table>

</body>
</html>
>>