<%@ page import="gov.nih.nci.cbiit.scimgmt.gds.util.GdsSubmissionActionHelper" %>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!--Begin Form -->
<s:form id="ic_dashboard_form" name="ic_dashboard_form" namespace="manage" method="post"
  action="navigateToIcMain"  role="form">
  
  <s:hidden name="projectId" id="projectId" value="%{project.id}"/>
      
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
        <div style="display:inline" id="addICBtn">
          <button type="button" value=" Add Study " class="saved btn btn-project-primary" onclick="location.href = 'studydemo.htm';">Add Study</button>
        </div>
        <div style="display: inline;" id="addICBtn">
          <button type="button" id="addIC" name="action:editIc" value=" Add Institutional Certification " class="saved btn btn-project-primary" onclick="location.href = 'ICdemo.htm';">Add Institutional Certification</button>
        </div>
       
        <br>
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
              <table style="width: 100%; font-size: 14px" cellpadding="0px" cellspacing="0" class="table table-bordered table-striped">
                <tbody>
                <tr class="modalTheader">
                  <!--  Show this column header only for subproject -->
                  <th  class="tableHeader"  align="center" width="25%">Study Name</th>                      
                  <th class="tableHeader" align="center" width="25%">Institution</th>
                  <th>Received</th>
                  <th>DULs</th>
                  <th>Document</th>
                  <th align="center">Comments</th>         
                  <th id="actionColumn" class="tableHeader" style="" align="center" width="1%">Actions</th>
                </tr> 
                <tr>
                  <td >PLCO</td>
                  <td >NCI</td> 
                  <td >Yes</td>
                  <td>
                    <table class="table table-striped table-bordered DUL">
                      <th>Type</th>
                      <th>Appendix</th>
                      <tr>
                        <td>Disease-specific Cancer (MDS)</td>
                        <td>
                          <div style="position: relative;"><a href="#" class="hvrlink" target="_blank">View</a>
                            <div class="details-pane">
                              <h3 class="title">Appendix: Disease-specific Cancer (MDS)</h3>
                              <p class="desc">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque purus lorem, sagittis vitae lacus id, dictum malesuada magna. Donec a sem et arcu imperdiet ullamcorper vitae ut est. Sed sapien eros, pulvinar fringilla ex eu, vehicula pellentesque velit. Mauris auctor enim vel tincidunt dictum. Maecenas elementum tempus velit vel facilisis. </p>
                            </div><!-- @end .details-pane -->
                          </div>
                        </td> 
                      </tr>
                      <tr><td>General Research Use (IRB, PUB)</td>     
                        <td>None</td> 
                      </tr>
                    </table>
                  </td>
                  <td  style="text-align: center;"><a href="#" onclick="openMissingDataReport(415, '/gds/manage/viewMissingIcData.action?instCertId=432&amp;')"><i class="fa fa-file-text fa-lg" aria-hidden="true" alt="view" title="view"></i></a> </td> 
                  <td style="text-align: center;">
                    <div style="position: relative;"><a href="#" class="hvrlink" target="_blank">View</a>
                      <div class="details-pane">
                        <h4 class="title">Comments</h4>
                        <p class="desc">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque purus lorem, sagittis vitae lacus id, dictum malesuada magna. Donec a sem et arcu imperdiet ullamcorper vitae ut est. Sed sapien eros, pulvinar fringilla ex eu, vehicula pellentesque velit. Mauris auctor enim vel tincidunt dictum. Maecenas elementum tempus velit vel facilisis. </p>
                      </div><!-- @end .details-pane -->
                    </div>
                  </td> 
                  <td width="2%"  class="editDeleteBtns" style="white-space: nowrap;">
                    <!--  Do not show edit and delete for sub-project -->
                    <a class="btnEdit" href="editstudydemo.htm">
                      <i class="fa fa-pencil-square fa-lg" aria-hidden="true" alt="edit" title="Edit"></i>&nbsp;
                    </a>&nbsp;&nbsp;&nbsp;                
                  </td>
                </tr>
              </table>
 <!--------------------------------END REPLACE TABLE------------------------------------------------------ -->            
              
            </div> <!--  end studies div -->


            <div id="IC" class="tab-pane fade in">
     
     
 <!-- ------------------------------------------------------------------------------------------------>
     <!-- REPLACE WITH IC Table by including the table in submissionIcList.jsp -->
 <!-- -------------------------------------------------------------------------------------------------->  
              <table style="width: 100%; font-size: 14px" cellpadding="0px" cellspacing="0" class="table table-bordered">
                <tbody>
                <tr class="modalTheader">
                  <!--  Show this column header only for subproject -->
                  <th id="subprojectColumn" class="tableHeader" style="display:none;" align="center" width="10%">Select 
                    &nbsp; <a href="#" id="popover" style="font-size: 12px;">
                    <i class="helpfileSubProject fa fa-question-circle fa-1x" aria-hidden="true"></i></a>
                  </th>                      
                  <th class="tableHeader" align="center" width="40%">Institutional Certification Document</th>
                  <th class="tableHeader projectColumn" align="center" width="10%">Status</th>
                  <th class="tableHeader projectColumn" align="center" width="10%">Missing Data</th>
                  <th class="tableHeader" align="center" width="20%">Date Uploaded</th>
                  <th class="tableHeader" align="center" width="20%">Uploaded By</th>                 
                  <th id="actionColumn" class="tableHeader" style="" align="center" width="10%">Actions</th>
                </tr>
                  
                <tr data-id="433">
                  <!--  Show this column only for subproject -->
                  <td class="subprojectSelect" style="white-space: nowrap;display:none;">                 
                    <input class="icSelect" type="checkbox" name="ic-selected" id="ic433" value="433">  
                    <input type="hidden" name="ifIcSelected" value="false" id="selectIcs">     
                  </td>
                  <td style="white-space: nowrap">
                    <a href="#" class="icDetails" id="icDetails433">
                      <i class="expand fa fa-lg fa-plus-square" id="433expand" aria-hidden="true" alt="Details" title="Details"></i>
                    </a>&nbsp;&nbsp;&nbsp;<a href="javascript:openDocument(947)">GDS notifications templates (1).docx</a>
                  </td>                   
                  <td class="projectColumn" style="white-space: nowrap">
                    <input type="hidden" name="" value="INPROGRESS" id="icReg0">              
                    <div id="icDiv0" class="searchProgess"><img src="../images/inprogress.png" alt="In Progress" width="18px" height="18px" title="In Progress"></div>
                  </td>                
                  <td class="projectColumn" style="white-space: nowrap">
                    <a href="#" onclick="openMissingDataReport(415, '/gds/manage/viewMissingIcData.action?instCertId=433&amp;')"><i class="fa fa-file-text fa-lg" aria-hidden="true" alt="view" title="view"></i></a> &nbsp; &nbsp;
                  </td>              
                  <td style="white-space: nowrap"> 
                    Dec 22 2016 11:21:49 AM
                  </td>                
                  <td style="white-space: nowrap"> 
                    Yuri Dinh
                  </td>                     
                  <td class="editDeleteBtns" style="white-space: nowrap;">
                    <!--  Do not show edit and delete for sub-project -->
                    <a class="btnEdit" href="/gds/manage/editIc.action?instCertId=433&amp;projectId=415">
                      <i class="fa fa-pencil-square fa-lg" aria-hidden="true" alt="edit" title="Edit"></i>&nbsp;
                    </a>&nbsp;&nbsp;&nbsp;                    
                  </td>
                </tr>                    
                </tbody>
              </table>
<!-- ----------------------------END REPLACE TABLE----------------------------------------------------------------------------------- -->

            </div>  <!-- END IC div-->
    
          </div> <!--  end tab content -->
        </div><!--end tabs container-->
      
        <div>
		  <p class="question">Studies awaiting ICs (2000 Characters):</p>
		  <s:textarea class="form-control input_other commentsClass" style="overflow-y: scroll;" rows="3" maxlength="2000" id="icComments" name="icComments" placeholder="List Studies awaiting Institutional Certifications to be received"></s:textarea>
		  <div id="charNum" style="text-align: right; font-style: italic;">
		    <span style="color: #990000;">2000</span> Character limits
		  </div>
        </div>
			
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
<script type="text/javascript">
$(function($){
	$('[data-toggle="tooltip"]').tooltip({
	    container : 'body'
	  });
});
</script> 

