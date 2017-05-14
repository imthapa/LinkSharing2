<g:each in="${messages}" var="message">
    <div class="well">
        <div class="row">
            <div class="col-sm-2">
                <!--<span class="glyphicon glyphicon-user "></span>-->
                <!-- %{--<img src="../images/user_image.jpg"/>--}% -->
            </div>

            <div class="col-sm-10 ">
                <div class=" user_details">
                    <label>${message.createdBy.fullName}</label>&nbsp;&nbsp;&nbsp;<label>${message.createdBy.userName}</label>
                    <a href="#" class="pull-right">${message.topicName}</a>

                </div>

                <div class="">
                    ${message.description}
                </div>

            </div>
        </div>


        <div class="row">
            <a href="https://www.facebook.com">
                <i id="social-fb" class="fa fa-facebook-square fa-2x social glyphsize"></i></a>
            <a href="https://twitter.com">
                <i id="social-tw" class="fa fa-twitter-square fa-2x social glyphsize"></i></a>
            <a href="https://plus.google.com">
                <i id="social-gp" class="fa fa-google-plus-square fa-2x social glyphsize"></i></a>
            <a href="mailto:bootsnipp@gmail.com">
                <i id="social-em" class="fa fa-envelope-square fa-2x social glyphsize"></i></a>
            <span class="pull-right">
                <a href="#" class="operations">Download&nbsp;&nbsp;&nbsp;</a>
                <a href="#" class="operations">View Full Site &nbsp;&nbsp;&nbsp;</a>
                %{--<a href="#" class="operations">Mark as read &nbsp;&nbsp;&nbsp;</a>--}%
                %{--<a href="#" class="operations">View post &nbsp;&nbsp;&nbsp;</a>--}%
                <g:link action="viewPost" controller="resource"
                        params='["id": "${message.resourceID}"]'><label>View post</label></g:link>

            </span>
        </div>
    </div>
</g:each>
