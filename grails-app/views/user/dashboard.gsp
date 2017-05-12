<html>
<head>
    <meta name="layout" content="main"/>
    <title>Show topic</title>
</head>

<body>
<div class="col-md-5">
    <div class="panel panel-default">
        <div class="panel-body">
            <g:render template="profile"/>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Subscriptions <a href="" class="pull-right">view All</a></div>

        <div class="panel-body">
            <g:render template="/topic/show" model="[topics: subscriptionList]"/>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Trending Topics</div>

        <div class="panel-body">
            <ls:trendingTopic/>
        </div>
    </div>
</div>

<div class="col-md-7">
    <g:render template="inbox"/>
</div>
</body>
</html>


