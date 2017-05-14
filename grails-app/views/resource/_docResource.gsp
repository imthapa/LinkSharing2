<%@ page import="linksharing.Topic" %>

<div class="modal fade" id="docCreate" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Document</h4>
            </div>

            <div class="modal-body" style="margin-left: 15px;margin-right:15px">
                <g:form method="post" class="form-horizontal" enctype="multipart/form-data" controller="resource"
                        action="upload">
                    <div class="form-group">
                        <div class="control-label col-sm-3"><label class="pull-left">Document*</label>
                        </div>

                        <div class="col-sm-9">
                            %{--<g:form name="upload" enctype="multipart/form-data" method="post" class="pull-right"--}%
                            %{--controller="resource" action="upload" >--}%
                            <input type="file" name="myFile"/>
                            %{--</g:form>--}%
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="control-label col-sm-3">
                            <label class="pull-left">Description*</label>
                        </div>

                        <div class="col-sm-9">
                            <g:textArea rows="4" class="form-control pull-right" name="description"/></div>
                    </div>

                    <div class="form-group">
                        <div class="control-label col-sm-3"><label class="pull-left">Topic*</label></div>

                        <div class="col-sm-9">
                            <g:select name="topicId" from="${Topic.findAllByCreatedBy(session.user)}" optionKey="id" optionValue="name" class="form-control pull-right"
                                      defaultLabel="default topic" id="id"/>
                            %{--<ls:topiCreated/>--}%
                            %{--<g:select name="topic" from="" class="form-control pull-right" defaultLabel="default topic"--}%
                            %{--id="topic"/>--}%
                            %{-- <option value="">Service Lines</option>
                             <option value="2">Analytics</option>
                             <option value="12">Analytics-SEA</option>
                             <option value="9">Digital Marketing</option>
                             <option value="20">Digital Marketing India</option>
                             <option value="13">Digital Marketing-SEA</option>--}%

                        </div>
                    </div>

                    <div class="form-group" style="padding-left:15px;padding-right:15px">
                        <button type="submit" class="btn btn-info ">share</button>
                        <button type="button" class="btn btn-danger pull-right" data-dismiss="modal">cancel</button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>
