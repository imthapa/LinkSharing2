<html>
<head>
    <meta name="layout" content="main"/>
    <title>Show topic</title>
</head>

<body>
%{--<g:render template="/topic/posts" model="[postsList: postsList]"/>--}%
%{--<g:render template="login"/>--}%
<div class="col-md-5">
    <g:render template="profile"/>
    <g:render template="/subscription/subscriptions"/>
    <ls:trendingTopic topicId="12"/>
</div>

<div class="col-md-7">
    <g:render template="inbox"/>
</div>
</body>
</html>


