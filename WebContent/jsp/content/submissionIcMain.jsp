<%@ page import="gov.nih.nci.cbiit.scimgmt.gds.util.GdsSubmissionActionHelper" %>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!--Begin Form -->
<s:form id="ic_dashboard_form" name="ic_dashboard_form" namespace="manage" method="post"
  action="navigateToIcMain"  role="form">
  
  <s:hidden name="projectId" id="projectId" value="%{project.id}"/>
  <s:hidden name="project.subprojectFlag" id="subprojectFlag" value="%{project.subprojectFlag}"/>
  
    <div class="pageNav">
    <s:submit action="saveIcList" value="Save" class="saved btn btn-default"/>
    <s:submit type="button" action="saveIcListAndNext" class="saved btn btn-project-primary">
      Save &amp; Next &nbsp;&nbsp;<i class="fa fa-caret-right" style="color:#ffffff;"></i>
    </s:submit>	  
  </div>
  
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
            </div>
      <s:if test="%{project.subprojectFlag.equals(@gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants@FLAG_NO)}">
                  <br>
        <div style="display:inline" id="addStudyBtn">
           <s:submit action="addEditStudy" type="button" id="addStudy"  class="saved btn btn-project-primary">
            Add Study &nbsp;<a href="#" class="pop" data-container="body" data-toggle="popover" data-placement="right" 
            data-content="Studies that are providing samples (i.e. expecting an institutional certification). &lt;br&gt;
            More information at:&lt;a href=&quot; https://www.cancer.gov/grants-training/grants-management/nci-policies/genomic-data/submission#institutional-certification &quot;target=&quot;_blank &quot;&gt;
            https://www.cancer.gov/grants-training/grants-management/nci-policies/genomic-data/submission#institutional-certification.
            &lt;/a&gt;" data-html="true" style="font-size: 12px;">
                            <i class="fa fa-question-circle fa-1x" aria-hidden="true"></i></a>
                            </s:submit>
        </div>
        <s:if test="!studiesForSelection.isEmpty">
         <div style="display: inline;" id="addICBtn">
          <s:submit action="addIc" id="addIC" value=" Add Institutional Certification " class="saved btn btn-project-primary"/>
         </div>
        </s:if>
       
        <br>
        </s:if>
        <br/>&nbsp; 

        <div class="container" style="width: 100%; padding-left: 0px;">
          <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#studies">Studies</a></li>
            <li><a data-toggle="tab"  href="#IC">Institutional Certifications</a></li>
          </ul>


          <div class="tab-content">
            
            <div id="studies" class="tab-pane fade in active" >
            
            
<!----------------------------------------------------------------------------------------->
			  <!--  REPLACE THE BELOW SECTION WITH NEW STUDIES TABLE by including submissionStudyList.jsp 
<!----------------------------------------------------------------------------------------------->
             <s:include value="/jsp/content/submissionStudyList.jsp" />
 <!--------------------------------END REPLACE TABLE------------------------------------------------------ -->            
              
            </div> <!--  end studies div -->


            <div id="IC" class="tab-pane fade in">
     
     
 <!-- ------------------------------------------------------------------------------------------------>
     <!-- REPLACE WITH IC Table by including the table in submissionIcList.jsp -->
 <!-- -------------------------------------------------------------------------------------------------->  
  				<s:include value="/jsp/content/submissionIcList.jsp" />
<!-- ----------------------------END REPLACE TABLE----------------------------------------------------------------------------------- -->

            </div>  <!-- END IC div-->
    
          </div> <!--  end tab content -->
        </div><!--end tabs container-->
      	
	    <div>
		  <p class="question">Additional Comments (2000 Characters):</p>
		  <s:textarea class="form-control input_other commentsClass" style="overflow-y: scroll;" rows="3" maxlength="2000" id="additionalComments" name="additionalComments" placeholder=""></s:textarea>
		  <div id="charNum2" style="text-align: right; font-style: italic;">
		    <span style="color: #990000;">2000</span> Character limits
		  </div>
	    </div>
            
      </div> <!--end panel body-->
    </div> <!--end panel-->

  </div> <!-- end Content -->

  <!--SAVE & NEXT BUTTONS-->
  <div class="pageNav">
    <s:submit action="saveIcList" value=" Save " class="saved btn btn-default"/>
    <s:submit type="button" action="saveIcListAndNext" class="saved btn btn-project-primary">
      Save &amp; Next &nbsp;&nbsp;<i class="fa fa-caret-right" style="color:#ffffff;"></i>
    </s:submit>	  
  </div>
 
</s:form>

<script type="text/javascript" src="<s:url value="/controllers/gds.js" />"></script>
<script type="text/javascript" src="<s:url value="/controllers/institutional_dashboard.js" />"></script> 
<link href="<s:url value="/stylesheets/demo.css" />" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript">
$(function($){
	$('[data-toggle="tooltip"]').tooltip({
	    container : 'body'
	  });
});
</script> 
