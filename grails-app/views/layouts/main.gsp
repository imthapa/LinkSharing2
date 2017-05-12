<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Linksharing"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    %{--<asset:stylesheet src="myCustom.css"/>--}%
    %{--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--}%
    %{--<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">--}%
    %{--<link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css"--}%
    %{--rel="stylesheet"  type='text/css'>--}%
    %{--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--}%
    %{--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--}%

    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-default" style="background-color: #5bc0de">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle"
                    data-toggle="collapse" data-target="#myNavbar">
                <span class="glyphicon glyphicon-menu-hamburger"></span>
            </button>
            <a class="navbar-brand" href="#" style="color:black">Link Sharing</a>
        </div>

        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">

                %{--<span class="glyphicon glyphicon-file" data-toggle="modal" data-target="#docCreate"></span>--}%
                <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                        data-target="#topicCreate">Create Topic</button>
                <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                        data-target="#linkCreate">Create Link</button>
                <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                        data-target="#docCreate">Create Doc</button>

                <div class="dropdown"> <g:if test="${session.user}">
                    <span class="dropdown-toggle glyphicon glyphicon-music" id="menu1" data-toggle="dropdown">

                            ${session.user.userName}


                    </span>
                    %{-- <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Tutorials
                         <span class="caret"></span></button>--}%
                    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Profile</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Users</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Topics</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Posts</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="/login/logout">Logout
                            %{--<g:if test="${session.user}">
                                Logout
                            </g:if>
                            <g:else>
                                Sign in
                            </g:else>--}%
                        </a></li>

                    </ul>
                </g:if>
                </div>
            </ul>

            <div class="col-sm-3 col-md-3 pull-right">
                <form class="navbar-form" role="search">
                    <div class="input-group">
                        <div class="input-group-btn">
                            <button class="btn btn-default searcher" type="submit"><span
                                    class="glyphicon glyphicon-search"></span></button>
                        </div>

                        <div class="input-group"><input type="text" class="form-control"
                                                        placeholder="Search"
                                                        name="srch-term"
                                                        id="srch-term"/>
                            <span id="searchclear" class="input-group-addon glyphicon glyphicon-remove-circle"></span>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</nav>
<g:layoutBody/>
<g:render template="/resource/linkCreate" model="[resource: resource]"/>
<g:render template="/resource/docResource" model="[resource: resource]"/>
<g:render template="/topic/create" model="[topic: topic]"/>

<g:include controller="home" action="showMessage"/>
<asset:javascript src="application.js"/>
<asset:deferredScripts/>
</body>
</html>
