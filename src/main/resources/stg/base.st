base(head, body) ::= <<
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/static/style.css"/>
	<script type="text/javascript" src="/static/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="/static/js/base.js"></script>
	<title>uPaste | Alpha</title>
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