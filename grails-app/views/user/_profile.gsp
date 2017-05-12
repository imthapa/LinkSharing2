 <div class="panel panel-default">
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-3">
                    <pic:userImage id="${user.loggedInUserId}"/>
                </div>

                <div class="col-sm-9 ">
                    <div>
                        <label>${user.loggedInUsersFullName}</label>

                        <div>@${user.loggedInUsername}</div>
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
        </div>
    </div>