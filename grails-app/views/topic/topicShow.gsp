<html>
<head>
    <title>Topic Show</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="col-md-5">
    <div class="panel panel-default">
        <div class="panel-heading">Topic : ${topics.name}</div>

        <div class="panel-body">
            <g:render template="show" model="[topics: topics]"/>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">User : ${topics.name}</div>

        <div class="panel-body">
            %{--<g:each in="${subscribedUsers}" var="user">--}%
            <g:render template="/user/profile" model="[users: subscribedUsers]"/>
            %{--</g:each>--}%
        </div>
    </div>
</div>

<div class="col-md-7 col-sm-7 pull-right">
    <div class="panel panel-default">
        <div class="panel-heading">
            <span>Posts : ${topics.name}</span>
            <form role="search" class="col-md-4 pull-right">
                <div class="input-group add-on">
                    <input class="form-control" placeholder="Search" name="srch-term" id="srch-term" type="text">

                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <div class="panel-body">
            <g:render template="posts" model="[postsList: posts]"/>
        </div>
    </div>
</div>

</body>
</html>