%{--<html>--}%

%{--<head>--}%
    %{--<title>Posts</title>--}%
    %{--<meta name="layout" content="main"/>--}%
%{--</head>--}%

%{--<body>--}%
<div class="col-md-7 col-sm-7 pull-left">
    <div class="panel panel-default">
        <div class="panel-heading">Posts</div>
        <div class="panel-body">
            %{--<div class="well">--}%
                <g:each in="${postsList}" var="posts">
                    <div class="well">
                    <div><a href="/">${posts.topicName}</a></div>
                    <p>%{--Grails is a powerful web framework, for the Java platform aimed at multiplying developers’
                    productivity thanks to a Convention-over-Configuration, sensible defaults and opinionated APIs.
                        --}%${posts.resourceDescription}</p>
                    <div>
                        <a href="https://www.facebook.com" style="margin-right:10px">
                            <i id="social-fb" class="fa fa-facebook-square fa-2x social glyphsize"></i></a>
                        <a href="https://twitter.com" style="margin-right:10px">
                            <i id="social-tw" class="fa fa-twitter-square fa-2x social glyphsize"></i></a>
                        <a href="https://plus.google.com" style="margin-right:10px">
                            <i id="social-gp" class="fa fa-google-plus-square fa-2x social glyphsize"></i></a>
                        <a href="mailto:bootsnipp@gmail.com" style="margin-right:10px">
                            <i id="social-em" class="fa fa-envelope-square fa-2x social glyphsize"></i></a>
                        <span class="pull-right">
                            <a href="#" class="operations" style="margin-right:10px">Delete</a>
                            <a href="#" class="operations" style="margin-right:10px">Edit</a>
                            <a href="#" class="operations" style="margin-right:10px">Download</a>
                            <a href="#" class="operations" style="margin-right:10px">View Full Site</a>
                        </span>
                    </div>%{--<hr style="border: 1px solid black">--}%
                    </div>
                </g:each>
            %{--</div>--}%
        </div>
    </div>
</div>
%{--</body>--}%
%{--</html>--}%
