<%@ page import="gov.nih.nci.cbiit.scimgmt.gds.util.GdsSubmissionActionHelper" %>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:hidden id="icIds" name="icIds"/>
	
<!--Begin Form -->
    <s:form id="ic_dashboard_form" name="ic_dashboard_form" namespace="manage" method="post"
       action="listIc"  role="form">
      
      <div class="pageNav">
          <s:submit action="saveIcList" value=" Save " class="saved btn btn-default"/>
          <s:submit type="button" action="saveIcListAndNext" class="saved btn btn-project-primary">
          Save &amp; Next &nbsp;&nbsp;<i class="fa fa-caret-right" style="color:#ffffff;"></i></s:submit>	
      </div>
      
      <s:hidden name="projectId" id="projectId" value="%{project.id}"/>
      <s:hidden name="project.subprojectFlag" id="subprojectFlag" value="%{project.subprojectFlag}"/>
      <s:hidden name="project.parentProjectId" value="%{project.parentProjectId}"/>
      <s:hidden name="project.createdBy" value="%{project.createdBy}"/>
      
      
      <!-- Begin Panel -->
      <div class="col-md-12">
        <div class="panel  project-panel-primary">
          
          <div class="panel-heading">
            <div class="pheader"><h4>Institutional Certification Status</h4></div>
            <div class="statusWrapper">
              <s:if test="%{!pageStatusCode.equals(@gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants@PAGE_STATUS_CODE_COMPLETED)}">         		           		      
    		    <div class="status">
    		      <a href="#" onclick="openMissingDataReport(${project.id}, '/gds/manage/viewMissingIcListData.action?')" class="statusLink">Generate Missing Data Report</a> &nbsp; &nbsp;
    		    </div>
    		  </s:if>
              <s:include value="/jsp/content/pageStatus.jsp"/>           	
            </div>
             
          </div><!--end header-->   
          
          <div class="panel-body">
           
            <div style="float: right;" class="question">
              <a href="https://gds.nih.gov/Institutional_Certifications.html" target="_blank">Institutional Certifications&nbsp;
                <i class="fa fa-external-link" aria-hidden="true"></i>
              </a>
            </div><br/>
          
          <div style="display:none" id="showSpan">
               <span>You will be able to add/edit Institutional Certification and/or DUL only at the parent project level. Changes will then be reflected in this sub-project. </span>
             <br/><br/></div> 
            <p class="question" style="display:inline;">Have all final (not provisional) institutional certifications for this project been received and reviewed by the GPA?
            <s:if test= "%{subprojectFlag} == 'N'">
            &nbsp; <a href="#" class="hoverOver" data-toggle="tooltip" data-placement="right"  data-html="true"
						 style="font-size: 12px;"><s:hidden id="IC_REVIEWED_KEY" value="%{getHelpText('IC_REVIEWED_KEY')}"/> <i class="fa fa-question-circle fa-1x" aria-hidden="true"></i></a>
		     </s:if>
		     <s:else>
		     &nbsp; <a href="#" class="hoverOver" data-toggle="tooltip" data-placement="right"  data-html="true"
						 style="font-size: 12px;"><s:hidden id="IC_REVIEWED_KEY" value="%{getHelpText('IC_REVIEWED_KEY')}"/> <i class="fa fa-question-circle fa-1x" aria-hidden="true"></i></a>
		     </s:else>
               &nbsp;
              <div style="display:none" id="addICBtn">
                <s:submit action="editIc" id="addIC" value=" Add Another Institutional Certification " class="saved btn btn-project-primary"/>
              </div>
            </p>
             
           <div class="radio form-group">   
              <s:radio id="radioCertComplete" list="#{'Y':'Yes','N':'No'}"
				name="project.certificationCompleteFlag" value="project.certificationCompleteFlag"
				template="radiomap-div.ftl" />
			</div> 
          <s:hidden id="certFlag" name="certFlag" />
            <p>&nbsp;</p>
              
              <div>
				<p class="question">Studies awaiting ICs (2000 Characters):</p>
				<s:textarea class="form-control input_other" rows="3" maxlength="2000" id="icComments" name="icComments" placeholder="List Studies awaiting Institutional Certifications to be received"></s:textarea>
			    <div id="charNum" style="text-align: right; font-style: italic;">
				<span style="color: #990000;">2000</span> Character limits
			   </div>
			</div>
			
			 <div>
				<p class="question">Additional Comments (2000 Characters):</p>
				<s:textarea class="form-control input_other" rows="3" maxlength="2000" id="additionalComments" name="additionalComments" placeholder=""></s:textarea>
			    <div id="charNum2" style="text-align: right; font-style: italic;">
				<span style="color: #990000;">2000</span> Character limits
			    </div>
			</div>
			<br>
            <table style="width: 100%;" cellpadding="0px" cellspacing="0" class="table table-bordered">
              <tr class="modalTheader">
               <!--  Show this column header only for subproject -->
                <th id="subprojectColumn" class="tableHeader" style="display:none;" align="center" width="10%">Select 
                 &nbsp; <a href="#" id="popover" style="font-size: 12px;">
                 <i class="helpfileSubProject fa fa-question-circle fa-1x"
		          aria-hidden="true"></i></a>
                </th>                      
                <th class="tableHeader" align="center" width="40%">Institutional Certification Document</th>
                <th  class="tableHeader projectColumn" align="center" width="10%">Status</th>
                <th  class="tableHeader projectColumn" align="center" width="10%">Missing Data</th>
                <th class="tableHeader" align="center" width="20%">Date Uploaded</th>
                <th class="tableHeader" align="center" width="20%">Uploaded By</th>                 
                <th id="actionColumn" class="tableHeader" style="display:none;" align="center" width="10%">Actions</th>
              </tr>
               
              <s:hidden id="icListSize" value="%{project.institutionalCertifications.size}"/>     
              <s:iterator status="icStat" var="cert" value="project.institutionalCertifications">
               <div class="icCountList">
              
                <s:set name="icIdx" value="#icStat.index" />
                
                <!--  FILE DISPLAY AND ICONS ROW -->    
                <tr  data-id="${cert.id}">
                 
                 <!--  Show this column only for subproject -->
                    <td class="subprojectSelect" style="white-space: nowrap;display:none;">                 
		                <input class="icSelect" type="checkbox" 
	                      name="ic-selected" id="ic${cert.id}" value="${cert.id}">	
	                      <s:hidden id="selectIcs" name="ifIcSelected"/>		 
                    </td>

                
                  <td style="white-space: nowrap">
                    <a href="#" class="icDetails" id="icDetails${cert.id}">
                      <i class="expand fa fa-plus-square fa-lg" id="${cert.id}expand" aria-hidden="true" alt="Details" title="Details"></i>
                    </a>&nbsp;&nbsp;&nbsp;<s:a href="javascript:openDocument(%{#cert.documents[0].id})">
                      <s:property value="%{#cert.documents[0].fileName}" />
                     </s:a>
                  </td>
                  
                    
                <td class= "projectColumn" style="white-space: nowrap">
                <s:hidden id="icReg%{#icStat.index}" value="%{#cert.status}"/>            	
              	<div id="icDiv${icStat.index}" class="searchProgess">
        		  <img src="../images/inprogress.png" alt="In Progress" width="18px" height="18px" title="In Progress" />
        	  	</div>
                  </td>
                  
                  <td class="projectColumn" style="white-space: nowrap">
                   <s:if test="%{!(#cert.status.equals(@gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants@PAGE_STATUS_CODE_COMPLETED))}">
                   <a href="#" onclick="openMissingDataReport(${project.id}, '/gds/manage/viewMissingIcData.action?instCertId=${cert.id}&')"><i class="fa fa-file-text fa-lg" aria-hidden="true" alt="view" title="view"></i></a> &nbsp; &nbsp;
                  </s:if>
                  </td>
                
                  <td style="white-space: nowrap"> 
                    <s:date name="%{#cert.documents[0].uploadedDate}" format="MMM dd yyyy hh:mm:ss a" />
                  </td>
                  
                  <td style="white-space: nowrap"> 
                     <s:property value="%{#cert.documents[0].uploadedBy}" />
                  </td>
                      
                  <td class="editDeleteBtns" style="white-space: nowrap; display:none;">
                    
                    <!--  Do not show edit and delete for sub-project -->
                      <a class="btnEdit"  href="/gds/manage/editIc.action?instCertId=${cert.id}&projectId=${project.id}">
                        <i class="fa fa-pencil-square fa-lg" aria-hidden="true" alt="edit" title="Edit"></i>&nbsp;
                      </a>&nbsp;&nbsp;&nbsp;
                      <a class="btnDelete" href="#" >
                        <i class="fa fa-trash fa-lg" aria-hidden="true" alt="delete" title="Delete"></i>
                      </a>                   
                  </td>
                </tr>
                </div>    
                <!--Begin view details-->
               <tr class="remove${cert.id}">
			      <td colspan="6">
                    <div id="contentDivImg${cert.id}" style="display: none">
                      <table width="100%" class="tBorder2" cellspacing="3">
                        <tr>
                          <td><span class="question">Provisional or Final? </span><s:property value="%{getLookupDisplayNamebyId(#cert.provisionalFinalCode)}"/></td>
                          <td><span class="question">Approved by GPA: </span><s:property value="%{getLookupDisplayNamebyId(#cert.gpaApprovalCode)}"/></td>
						  <td><span class="question">Study for use in Future Projects? </span><s:property value="%{getLookupDisplayNamebyId(#cert.futureProjectUseCode)}"/></td>
                        </tr>
                        
                        <s:if test="%{#cert.comments != null}">
                          <tr><td colspan="6">&nbsp;</td></tr>
                          <tr><td colspan="6" class="question">Comments:</td></tr>
                          <tr><td colspan="6">${cert.comments}</td></tr>
			            </s:if>
			            
                        <tr>
                          <td colspan="3" align="left" valign="top">&nbsp;</td>
                          <td colspan="3">&nbsp;</td>
                        </tr>
                        
                        <tr>
                          <td colspan="6">           
                              <s:iterator status="studiesStat" var="study" value="project.institutionalCertifications[#icStat.index].studies">
                                <s:set name="studyIdx" value="#studiesStat.index" />
                                <table width="100%">
                                  <tr>
                                    <td valign="top" class="question" style="width: 35px;"><p class="number">${studyIdx+1}</p></td>
                                    <td>
                                      <table class="table table-bordered" width="100%" class="study">
                                        <tr>
                                          <td>
                                            <table width="100%" cellspacing="5">
                                              <tr>
                                                <td><span class="question">Study Name: </span>${study.studyName}</td>
					                         <!--    <td align="left" valign="top">&nbsp;</td> -->
					                            <td><span class="question">Institution: </span>${study.institution}</td>
                                                <td><span class="question">DUL(s) Verified? </span><s:property value="%{getLookupDisplayNamebyId(#study.dulVerificationId)}"/></td>
                                              </tr>
                                                                                    
                                              <s:if test="%{#study.comments != null}">
                                                <tr><td colspan="4" align="left" valign="top">&nbsp;</td></tr>
                                                <tr><td colspan="6" class="question">Comments:</td></tr>
                                                <tr><td colspan="6">${study.comments}</td></tr>
                                              </s:if>
                                              
                                              <s:if test="%{project.institutionalCertifications[#icStat.index].studies[#studiesStat.index].studiesDulSets.size > 0}">
                                              
                                              <tr><td colspan="4" align="left" valign="top">&nbsp;</td></tr>
                                              <tr>
                                                <td colspan="4" align="left" valign="top" class="question" valign="bottom">Data Use Limitation(s)</td>
                                              </tr>       
                                              
                                              <tr>
                                                <td colspan="4">
                                                  <table class="table table-striped">
                                                    <s:iterator status="dulSetStat" var="studiesDulSet" value="project.institutionalCertifications[#icStat.index].studies[#studiesStat.index].studiesDulSets">
                                                      <s:set name="dulSetIdx" value="#dulSetStat.index" />
                                                      <tr>
                                                        <td>
                                                          <span class="question">
                                                            ${dulSetStat.index + 1}. ${studiesDulSet.parentDulChecklist.displayText}
                                                            <s:if test="%{#studiesDulSet.additionalText != null}">
                                                              - ${studiesDulSet.additionalText}
                                                            </s:if>
                                                          </span>
                                                          <s:if test="%{#studiesDulSet.dulChecklistSelections.size > 0 && 
                                                          			(#studiesDulSet.dulChecklistSelections.size != 1 || 
                                                          			#studiesDulSet.dulChecklistSelections[0].dulChecklist.parentDulId != null)}">
                                                            :&nbsp
                                                          </s:if>
                                                          <s:iterator status="dulStat" var="dul" value="%{#studiesDulSet.dulChecklistSelections}">
                                                            <!-- Dont show the parent DUL in the bullet list -->
                                                            <s:if test="%{#dul.dulChecklist.parentDulId != null}">
                                                              ${dul.dulChecklist.displayText}
                                                              <s:if test="%{#dulStat.index < (#studiesDulSet.dulChecklistSelections.size - 1)}">
                                                                ;
                                                              </s:if>
                                                            </s:if>
                                                          </s:iterator><br>
                                                          <s:if test="%{#studiesDulSet.comments != null}">
                                                            &nbsp;&nbsp;&nbsp; DUL Appendix : ${studiesDulSet.comments}
                                                          </s:if>
                                                        </td>
                                                      </tr>
                                                    </s:iterator> 
                                                  </table>
                                                </td>
                                              </tr>
                                              </s:if> <!-- check for DULs present-->   
                                                                                                                                                                            
                                            </table>
                                          </td>
                                        </tr>
                                      </table> <!-- study class -->
                                    </td>
                                  </tr>
                                </table> <!--study end-->
                             <!--    <p>&nbsp;</p> -->
                              </s:iterator> <!-- for studies -->                      	        
                          </td> <!-- for colspan 6-->
					    </tr>
                      </table> <!-- for class tBorder2 -->
				    </div> <!-- for contentDivImg -->
                  </td> <!-- for colspan 3 -->
                </tr>  <!--end view H view details-->
                         
                
                
                
                
                
                    
                
              </s:iterator> <!-- ics -->
                      
            </table>
          </div> <!--end panel body-->
        </div> <!--end panel-->
      </div> 
              
              <!--SAVE & NEXT BUTTONS-->
        <div class="pageNav">
          <s:submit action="saveIcList" value=" Save " class="saved btn btn-default"/>
          <s:submit type="button" action="saveIcListAndNext" class="saved btn btn-project-primary">
          Save &amp; Next &nbsp;&nbsp;<i class="fa fa-caret-right" style="color:#ffffff;"></i></s:submit>	  
        </div>
              
            
 <!-- start: Delete Coupon Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                 <i class="fa fa-exclamation-triangle fa-3x" aria-hidden="true" title="warning sign"></i>&nbsp;&nbsp;
                 <h3 class="modal-title" id="myModalLabel">Are You Sure You Want to Delete?</h3>

            </div>
            <div class="modal-body">
                 <h5>Deleting the IC will remove all data from the project and any sub-projects, including all data on associated studies and DULs.</h5>
            </div>
            <!--/modal-body-collapse -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="btnDelteYes" href="#">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
            <!--/modal-footer-collapse -->
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

    </s:form>

<script type="text/javascript" src="<s:url value="/controllers/gds.js" />"></script>
<script type="text/javascript" src="<s:url value="/controllers/institutional_dashboard.js" />"></script> 
<script type="text/javascript">
$(function($){
	$('[data-toggle="tooltip"]').tooltip({
	    container : 'body'
	  });
});
</script> 

