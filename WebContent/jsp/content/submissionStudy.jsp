<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
 <!--Begin Form -->
    <s:form id="study_form" name="study_form" cssClass="dirty-check" namespace="manage"
    enctype="multipart/form-data" data-toggle="validator" action="saveStudy" method="post" role="form">  
    
    <s:hidden name="projectId" value="%{project.id}"/>
    <s:hidden name="study.id" value="%{study.id}"/>
    <s:hidden name="study.createdBy" value="%{study.createdBy}"/>

  <div id="messages" class="container">
    
  </div>

  <!-- Content start -->
  <div class="container">
    
         <div class="pageNav">
          <s:submit action="navigateToIcMain" value=" Cancel " class="saved btn btn-default"/>	
          <s:submit action="saveAndAddStudy" value="Save And Add Another Study " class="saved btn btn-default add_field_button"/>
           <s:submit type="button" action="saveStudy" class="saved btn btn-project-primary">Save Study &nbsp;&nbsp;<i class="fa fa-caret-right" style="color:#ffffff;"></i>
            </s:submit>
        </div>   
      
      <!-- Begin Panel -->
      <div class="col-md-12">

        <div class="panel  project-panel-primary">
          
          <div class="panel-heading">
           <s:if test="%{study.id != null}">
          <div class="pheader"><h4>Edit Study</h4></div>
          </s:if>
          <s:else>
            <div class="pheader"><h4>Add Study</h4></div>
             </s:else>
          </div><!--end header-->  

          <div class="panel-body" >

           <div class="panel panel-default">
              
              <!--  STUDY SECTION HEADER  -->
            
                        <div class="studyHeadingPanel panel-heading header">
                          <h4 class="panel-title ">
                        
                              Study</h4>
                        </div>                            
                        
            <!--  STUDY SECTION BODY -->
                  
                       
          <div class="panel-body" >
                                  
            <div style="display: block;" class="form-group row">
                              <div class="col-xs-12">
                                <label class="label_sn" for="Study Name">
                                  <i class="fa fa-asterisk asterisk" aria-hidden="true">&nbsp;</i>
                                  Study Name &nbsp; &nbsp; <a href="#" class="pop" data-container="body" data-toggle="popover" data-placement="right" data-content="Name of the individual study or protocol under which institutional IRB approval was granted (e.g. The Nurses Health Study)" data-html="true" style="font-size: 12px;">
                            <i class="fa fa-question-circle fa-1x" aria-hidden="true"></i></a>
                                </label>
                               <div class="input-group col-xs-12">
                           <s:textfield name="study.studyName" style="width: 78.7%" class="form-control2" placeholder="Enter Study Name" id="studyname" value="%{study.studyName}" maxlength="150">
                           </s:textfield>
                </div>
                              </div>

                            </div>
                               <div style="display: block;" class="form-group row">         
                       <div class="col-xs-12">
                                <label class="label_in" for="Institution">Institution &nbsp; &nbsp; <a href="#" class="pop" data-container="body" data-toggle="popover" data-placement="right" data-content="Institution that has purview over the study or protocol and under which the IRB review occurred (e.g. Johns Hopkins School of Public Health)" data-html="true" style="font-size: 12px;">
                            <i class="fa fa-question-circle fa-1x" aria-hidden="true"></i></a></label>
                                        <div class="col-xs-12">
                                      <s:textfield name="study.institution" style="margin-left: -15px; width: 81%" class="form-control2" placeholder="Full Name of Institution" id="studyInsitution" value="%{study.institution}" maxlength="150">
                           </s:textfield>
                                      </div> 
                              </div>
                            </div>

                  </div> <!-- end panel body --> 
                  </div> <!--end panel-->           
          </div> <!--end panel body-->

          <div class="input_fields_wrap">
          </div>
        </div> <!--end panel-->
        </div>
    
              
              <!--SAVE & NEXT BUTTONS-->
        <div class="pageNav">
          <s:submit action="navigateToIcMain" value=" Cancel " class="saved btn btn-default"/>	
          <s:submit action="saveAndAddStudy" value="Save And Add Another Study " class="saved btn btn-default add_field_button"/>
           <s:submit type="button" action="saveStudy" class="saved btn btn-project-primary">Save Study &nbsp;&nbsp;<i class="fa fa-caret-right" style="color:#ffffff;"></i>
            </s:submit>

        </div>            
  </div>
  </s:form>
  <!-- end Content -->