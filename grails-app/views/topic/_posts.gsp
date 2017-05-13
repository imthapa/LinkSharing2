<g:each in="${postsList}" var="posts">
    <div class="well">
        <div>%{--<a href="/">${posts.topicName}</a>--}%
            <g:link action="show" controller="topic" params='["id": "${posts.topicId}"]'>${posts.topicName}</g:link>
        </div>

        <p>${posts.resourceDescription}</p>

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
                %{--<a href="#" class="operations" style="margin-right:10px">View Post</a>--}%
                <g:link action="viewPost" controller="resource" params='["id": "${posts.resourceID}"]'><label>View post</label></g:link>

            </span>
        </div>
    </div>
</g:each>

