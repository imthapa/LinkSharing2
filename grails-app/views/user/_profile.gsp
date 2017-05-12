<g:each in="${users}" var="user">
    <div class="row">
        <div class="col-sm-3">
            <pic:userImage id="${user.userId}"/>
        </div>

        <div class="col-sm-9 ">
            <div>
                <label>${user.userFullName}</label>

                <div>@${user.userName}</div>
            </div>

            <div>
                <div class="col-md-5">
                    <div>subscription</div>

                    <div>${user.subscriptionCount}</div>
                </div>

                <div class="col-md-2">
                    <div>Topics</div>

                    <div>${user.topicCount}</div>
                </div>
            </div>
        </div>
    </div>
</g:each>

