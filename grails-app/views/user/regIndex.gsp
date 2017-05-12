<html>
<head>
    <meta name="layout" content="main"/>
    <title>Link Sharing</title>
</head>

<body>
<div class="col-md-7 col-sm-7 pull-left">
    <div class="panel panel-default">
        <div class="panel-heading">Posts</div>

        <div class="panel-body">
            <g:render template="/topic/posts" model="[postsList: postsList]"/>
        </div>
    </div>
</div>
<g:render template="/user/login"/>
<g:render template="/user/register"/>
</body>
</html>


