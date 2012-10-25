base(head, body) ::= <<
<html>
<head>
	<script type="text/javascript" src="/static/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="/static/js/base.js"></script>
	<title>uPaste | Alpha</title>
	$head$
	<link rel="stylesheet" type="text/css" href="/static/style.css"/>
</head>

<body>
<table>
    <tr>
        <td colspan=2 id="title-cell">
            <span id="title-text"><a href="/">
                <span id="the-u">u</span>Paste<span id="alpha">alpha</span></a>
            </span>
        </td>
    </tr>
    <tr>
        <td id="body-cell">
$body$
        </td>
    </tr>
</table>

</body>
</html>
>>