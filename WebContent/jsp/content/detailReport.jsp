<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <title>Submission Details Report</title>
    
<script src="<s:url value="/scripts/jquery-1.12.3.min.js" />"></script>
<script src="<s:url value="/scripts/bootstrap-3.3.6.min.js" />"></script>
<script src="<s:url value="/scripts/bootstrap-datepicker-1.6.1.min.js" />"></script>
<script src="<s:url value="/scripts/jquery.validate-1.15.0.min.js" />"></script>
<script src="<s:url value="/scripts/theme.js" />"></script>

<script src="<s:url value="/controllers/gds.js" />"></script>

<link href="<s:url value="/stylesheets/bootstrap-3.3.6.min.css" />" rel="stylesheet" type="text/css" media="screen" />
<link href="<s:url value="/stylesheets/bootstrap-datepicker-1.6.1.min.css" />" rel="stylesheet" type="text/css" media="screen" />
<link href="<s:url value="/stylesheets/datatables-1.10.12.min.css" />" rel="stylesheet" type="text/css" media="screen" />
<link href="<s:url value="/stylesheets/font-awesome-4.6.3.min.css" />" rel="stylesheet" type="text/css" media="screen" />
<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,600italic,700,700italic,900,900italic,400italic' rel='stylesheet' type='text/css'>
<link href="<s:url value="/stylesheets/custom.css" />" rel="stylesheet" type="text/css" media="screen" />
<link href="<s:url value="/stylesheets/datatable.css" />" rel="stylesheet" type="text/css" media="screen" />
<link href="<s:url value="/stylesheets/styles.css" />" rel="stylesheet" type="text/css" media="screen">
    <s:head />

</head>

    <body class="popPage">
      <div style="width: 750px; margin: 20px 20px 20px 20px;">

        <body class="noSubNav">
       <!-- header-->
     <div class="logo-header"> </div>

<!-- Fixed navbar -->

    <div id="wrap">
<div class="navbar  navbar-fixed-top" style="min-width: 750px;">
    <div class="row">
    <div class="container">
      <div style="margin-left: 740px; margin-top: 10px; margin-bottom: 0px;"><a href="" id="close"><i class="fa fa-times-circle fa-2x" aria-hidden="true"></i></a></div>
      <div id="logo" class="logoImage" style="margin-left: 2px;">
        <img src="images/nci-logo-full.svg" width="450px;"  alt="National Cancer Institute" style="display:inline;">
        
        <div class="GDS"><h3 style="padding: 0px; margin:0px;">Genomic Data Sharing Tracking System</h3></div>

        
      </div>
  </div>


 <!--Main Navigation -->
      <div id="primNav" class="row">
          <div  class="container" >
          <nav>
            
          </nav>
          </div>
      </div>
   
  
      <div class="pageHeader" id="pageHeader"><div class="titleWrapper container">
      <h3>Project Submission Details Report: ${project.submissionTitle}</h3>
      </div>
      </div>
  

 

  </div>      
   
</div>

   
        <div class="panel panel-default" id="searchGrant" style="margin-top: 200px;">
          <div class="panel-heading"><span class="clickable panel-collapsed"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i></span>
            <div class="pheader" style="display:inline;"><h5>General Information</h5></div>
            </div> <!--end panel header-->
            <div class="panel-body" style="display:none;">
              <p>
                <span class="reportLabel">Title of Project/Sub-project:</span>  ${project.submissionTitle}</p>
                <p><span class="reportLabel">Reason for being submitted:</span> <s:property value="%{projectSubmissionReason}" /> </p>
                <p><span class="reportLabel">Division/Office/Center:</span> ${project.docAbbreviation}</br>
                <span class="reportLabel">Program Branch:</span> ${project.programBranch}</p>
                <p><span class="reportLabel"> Intramural (Z01)/Grant/Contract #:</span> ${project.applicationNum}</br>
                <span class="reportLabel">Intramural/Grant/Contract Project Title:</span> ${project.projectTitle}</p>
               </p>
                <p><span class="reportLabel">Principal Investigator:</span> ${project.piFirstName} ${project.piLastName} &nbsp;&nbsp;&nbsp; <span class="reportLabel">Email:</span> <s:a href="mailto:%{project.piEmailAddress}?">${project.piEmailAddress}</s:a></br>
                <span class="reportLabel"> Institution:</span> ${project.piInstitution}</p>
              
                <p><span class="reportLabel">Primary Contact:</span> ${project.pocFirstName} ${project.pocLastName} &nbsp;&nbsp;&nbsp; <span class="reportLabel">Email:</span> <s:a href="mailto:%{project.pocEmailAddress}?">${project.pocEmailAddress}</s:a></p>
                
                 </p>
                <p><span class="reportLabel">Program Director:</span> ${project.pdFirstName} ${project.pdLastName}</p>
                
                <p><span class="reportLabel">Start Date:</span> <s:property value="%{projectStartDate}" /> &nbsp;&nbsp;&nbsp; <span class="reportLabel">End Date:</span> <s:property value="%{projectEndDate}" /></p>
                
                 
                 <p><span class="reportLabel">Comments</span> ${project.comments}</p>
              </div><!--end panel body-->
              </div><!--end panel-->


              <div class="panel panel-default" id="searchGrant" style="margin-top: 20px;">
          <div class="panel-heading"><span class="clickable panel-collapsed"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>
            <div class="pheader" style="display:inline;"><h5>Genomic Data Sharing Plan</h5></div>
            </div> <!--end panel header-->
            <div class="panel-body" style="display:none;">
              <p>
                <span class="reportLabel">Data sharing exception requested for this project?</span>  Yes</p>
                <p><span class="reportLabel">Exception approved?</span> Yes</p>
                <p><span class="reportLabel">Uploaded Exception Memo:</span></br>
                  <table style="width: 95%;" cellpadding="0px" cellspacing="0" class="table table-bordered table-striped">
                    <tbody><tr class="modalTheader">
                      <th class="tableHeader" align="center" width="10%">File Name</th>
                      <th class="tableHeader" align="center" width="10%">Date</th>
                      <th class="tableHeader" align="center" width="10%">Uploaded By</th>
                    </tr>
                    <tr>
                      <td><i class="fa fa-file-word-o" aria-hidden="true"></i> &nbsp;<a href="#" data-original-title="" title="">DataSharingExceptionMemo_approved.doc</a></td>
                      <td style="white-space: nowrap">Feb 08 2015 06:47:12 PM</td>
                      <td><a href="mailto: jonesm@mail.nih.gov" data-original-title="" title="">Mary Jones</a>
                      </td></tr>
                    </tbody></table>
                <span class="reportLabel">Will there be any data submitted?</span> Yes</p>
                <p><span class="reportLabel">Types of specimens the data submission pertain to:</span> Human; Non-human</p>
                <p><span class="reportLabel">Type of data that will be submitted:</span> Individual</p>
               </p>
                <p><span class="reportLabel">Type of access the data will be made available through:</span> Mary Jones &nbsp;&nbsp;&nbsp; </p>
                <p><span class="reportLabel"> Repository(ies) the data will be submitted to:</span> Database of Genotypes and Phenotypes (dbGaP); NCI Genomic Data Commons (GDC); Other: XYZ Repository</p>
              
                <p><span class="reportLabel">Has the GPA reviewed the Data Sharing Plan?</span> No</p>
                
                 </p>
                <p><span class="reportLabel">Uploaded Data Sharing Plan:</span><br/>
                  <table style="width: 95%;  margin-top: 10px;" cellpadding="0px" cellspacing="0" class="table table-bordered table-striped">
                    <tbody><tr class="modalTheader">
                      <th class="tableHeader" align="center" width="10%">Documnent Title</th>
                      <th class="tableHeader" align="center" width="10%">File Name</th>
                      <th class="tableHeader" align="center" width="10%">Date</th>
                      <th class="tableHeader" align="center" width="10%">Uploaded By</th>
                    </tr>
                    <tr>
                       <td><a href="#" data-original-title="" title="">DSP Version 4</a></td>
                      <td>DataSharingExceptionMemo_approved.doc</td>
                      <td style="white-space: nowrap">Feb 15 2015 06:47:12 PM</td>
                      <td><a href="mailto: jonesm@mail.nih.gov" data-original-title="" title="">Mary Jones</a>
                      </td></tr>
                    </tbody></table></p>
                
               
                
                 
                 <p><span class="reportLabel">Comments:</span> Lorem ipsum dolor sit amet, consectetur adipiscing elit. In id sem in leo vehicula faucibus ut sed nibh. Nam lobortis nunc at velit tincidunt consequat. Vestibulum rhoncus diam sit amet nisl feugiat, in cursus tortor aliquet. In venenatis eleifend purus, in suscipit nisi venenatis in. Donec et nulla molestie, tincidunt mi ac, finibus arcu. In mattis, nibh eu posuere gravida, lorem lorem consequat orci, at efficitur ligula turpis ac augue. Etiam faucibus ullamcorper neque ac pulvinar.</p>
              </div><!--end panel body-->
              </div><!--end panel-->



              <div class="panel panel-default" id="searchGrant" style="margin-top: 20px;">
          <div class="panel-heading"><span class="clickable panel-collapsed"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>
            <div class="pheader" style="display:inline;"><h5>Institutional Certification(s)</h5></div>
            </div> <!--end panel header-->
            <div class="panel-body" style="display:none;">
              <p>
                <span class="reportLabel">All Institutional Certifications recieved?</span>  Yes</p>
                <table style="width: 100%;" cellpadding="0px" cellspacing="0" class="table table-bordered">
              <tbody><tr class="modalTheader">
                <th class="tableHeader" align="center" width="60%">Institutional Certification Document</th>
                <th class="tableHeader" align="center" width="30%">Date Uploaded</th>
                <th class="tableHeader" align="center" width="10%">Actions</th>
              </tr>
                    
              
                
                
                <!--  FILE DISPLAY AND ICONS ROW -->    
                <tr data-id="51">
                  <td style="white-space: nowrap">
                    <a href="javascript:openDocument(195)">CRCHD Portfolio Management (PNRP) Meeting Minutes (9-24-07).doc</a>
                  </td>
                
                  <td style="white-space: nowrap"> 
                    Jul 20 2016 03:25:09 PM
                  </td>
                      
                  <td style="white-space: nowrap">
                    <a href="#" class="icDetails" id="icDetails51">
                      <i class="expand fa fa-lg fa-minus-square" id="51expand" aria-hidden="true" alt="view" title="view"></i></a>
                    
                  </td>
                </tr>
                        
                             
                <!--Begin view details-->
                <tr>
            <td colspan="3">
                    <div id="contentDivImg51" style="display: block;">
                      <table width="100%" class="tBorder2" cellspacing="3">
                        <tbody><tr>
                          <td><span class="question">Approved by GPA: </span>No</td>
              <td><span class="question">Provisional or Final? </span>Final</td>
              <td><span class="question">Study for use in Future Projects? </span></td>
                        </tr>
                        
                        
                  
                        <tr>
                          <td colspan="3" align="left" valign="top">&nbsp;</td>
                          <td colspan="3">&nbsp;</td>
                        </tr>
                        
                        <tr>
                          <td colspan="6">           
                              
                                
                                <table width="100%">
                                  <tbody><tr>
                                    <td valign="top" class="question" style="width: 35px;"><p class="number">1</p></td>
                                    <td>
                                      <table class="table table-bordered" width="100%">
                                        <tbody><tr>
                                          <td>
                                            <table width="100%" cellspacing="5">
                                              <tbody><tr>
                                                <td><span class="question">Study Name: </span>Test Study 0</td>
                                   <!--    <td align="left" valign="top">&nbsp;</td> -->
                                      <td><span class="question">Institution: </span></td>
                                                <td><span class="question">Data Use Limitation(s) Verified? </span></td>
                                              </tr>
                                                                                    
                                              
                                                <tr><td colspan="4" align="left" valign="top">&nbsp;</td></tr>
                                                <tr><td colspan="6" class="question">Comments:</td></tr>
                                                <tr><td colspan="6">Test comments</td></tr>
                                              
                                              
                                              
                                              
                                              <tr><td colspan="4" align="left" valign="top">&nbsp;</td></tr>
                                              <tr>
                                                <td colspan="4" align="left" valign="top" class="question">Data Use Limitation(s)</td>
                                              </tr>       
                                              
                                              <tr>
                                                <td colspan="4">
                                                  <table class="table table-striped">
                                                    
                                                      
                                                      <tbody><tr>
                                                        <td>
                                                          <span class="question">
                                                            1. Health/Medical/Biomedical
                                                            
                                                          </span>
                                                          
                                                            :&nbsp;
                                                          
                                                          
                                                            <!-- Dont show the parent DUL in the bullet list -->
                                                            
                                                              IRB approval required
                                                              
                                                                ;
                                                              
                                                            
                                                          
                                                            <!-- Dont show the parent DUL in the bullet list -->
                                                            
                                                              Collaboration required
                                                              
                                                                ;
                                                              
                                                            
                                                          
                                                            <!-- Dont show the parent DUL in the bullet list -->
                                                            
                                                              Publication required
                                                              
                                                                ;
                                                              
                                                            
                                                          
                                                            <!-- Dont show the parent DUL in the bullet list -->
                                                            
                                                              Not-for-profit use only
                                                              
                                                                ;
                                                              
                                                            
                                                          
                                                            <!-- Dont show the parent DUL in the bullet list -->
                                                            
                                                              Methods
                                                              
                                                                ;
                                                              
                                                            
                                                          
                                                            <!-- Dont show the parent DUL in the bullet list -->
                                                            
                                                              Genetic studies only
                                                              
                                                            
                                                          
                                                        </td>
                                                      </tr>
                                                     
                                                  </tbody></table>
                                                </td>
                                              </tr>
                                               <!-- check for DULs present-->   
                                                                                                                                                                            
                                            </tbody></table>
                                          </td>
                                        </tr>
                                      </tbody></table> <!-- study class -->
                                    </td>
                                  </tr>
                                </tbody></table> <!--study end-->
                             <!--    <p>&nbsp;</p> -->
                              
                                
                                <table width="100%">
                                  <tbody><tr>
                                    <td valign="top" class="question" style="width: 35px;"><p class="number">2</p></td>
                                    <td>
                                      <table class="table table-bordered" width="100%">
                                        <tbody><tr>
                                          <td>
                                            <table width="100%" cellspacing="5">
                                              <tbody><tr>
                                                <td><span class="question">Study Name: </span>Study Name 2 as Test data for DUL</td>
                                   <!--    <td align="left" valign="top">&nbsp;</td> -->
                                      <td><span class="question">Institution: </span>Test Institution for Study Name 2</td>
                                                <td><span class="question">Data Use Limitation(s) Verified? </span></td>
                                              </tr>
                                                                                    
                                              
                                                <tr><td colspan="4" align="left" valign="top">&nbsp;</td></tr>
                                                <tr><td colspan="6" class="question">Comments:</td></tr>
                                                <tr><td colspan="6">Test Data 2</td></tr>
                                              
                                              
                                              
                                              
                                              <tr><td colspan="4" align="left" valign="top">&nbsp;</td></tr>
                                              <tr>
                                                <td colspan="4" align="left" valign="top" class="question">Data Use Limitation(s)</td>
                                              </tr>       
                                              
                                              <tr>
                                                <td colspan="4">
                                                  <table class="table table-striped">
                                                    
                                                      
                                                      <tbody><tr>
                                                        <td>
                                                          <span class="question">
                                                            1. General Research Use
                                                            
                                                          </span>
                                                          
                                                            :&nbsp;
                                                          
                                                          
                                                            <!-- Dont show the parent DUL in the bullet list -->
                                                            
                                                              Collaboration required
                                                              
                                                            
                                                          
                                                        </td>
                                                      </tr>
                                                     
                                                  </tbody></table>
                                                </td>
                                              </tr>
                                               <!-- check for DULs present-->   
                                                                                                                                                                            
                                            </tbody></table>
                                          </td>
                                        </tr>
                                      </tbody></table> <!-- study class -->
                                    </td>
                                  </tr>
                                </tbody></table> <!--study end-->
                             <!--    <p>&nbsp;</p> -->
                              
                                
                                <table width="100%">
                                  <tbody><tr>
                                    <td valign="top" class="question" style="width: 35px;"><p class="number">3</p></td>
                                    <td>
                                      <table class="table table-bordered" width="100%">
                                        <tbody><tr>
                                          <td>
                                            <table width="100%" cellspacing="5">
                                              <tbody><tr>
                                                <td><span class="question">Study Name: </span>Study Name 1</td>
                                   <!--    <td align="left" valign="top">&nbsp;</td> -->
                                      <td><span class="question">Institution: </span></td>
                                                <td><span class="question">Data Use Limitation(s) Verified? </span></td>
                                              </tr>
                                                                                    
                                              
                                              
                                               <!-- check for DULs present-->   
                                                                                                                                                                            
                                            </tbody></table>
                                          </td>
                                        </tr>
                                      </tbody></table> <!-- study class -->
                                    </td>
                                  </tr>
                                </tbody></table> <!--study end-->
                             <!--    <p>&nbsp;</p> -->
                               <!-- for studies -->                               
                          </td> <!-- for colspan 6-->
              </tr>
                      </tbody></table> <!-- for class tBorder2 -->
            </div> <!-- for contentDivImg -->
                  </td> <!-- for colspan 3 -->
                </tr>  <!--end view H view details-->   
                
               <!-- ics -->
                      
            </tbody></table>
              </div><!--end panel body-->
              </div><!--end panel-->


              <div class="panel panel-default" id="searchGrant" style="margin-top: 20px;">
          <div class="panel-heading"><span class="clickable panel-collapsed"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>
            <div class="pheader" style="display:inline;"><h5>Basic Study Information</h5>
            </div></div> <!--end panel header-->
            <div class="panel-body" style="display:none;">
              <p>
                <span class="reportLabel">Has the GPA reviewed the Basic Study Information?</span>  Yes</p>
                
                <p><span class="reportLabel">Uploaded Basic Study Infomation Form:</span></br>
                  <table style="width: 95%;" cellpadding="0px" cellspacing="0" class="table table-bordered table-striped">
                    <tbody><tr class="modalTheader">
                      <th class="tableHeader" align="center" width="10%">File Name</th>
                      <th class="tableHeader" align="center" width="10%">Date</th>
                      <th class="tableHeader" align="center" width="10%">Uploaded By</th>
                    </tr>
                    <tr>
                      <td><i class="fa fa-file-word-o" aria-hidden="true"></i> &nbsp;<a href="#" data-original-title="" title="">BasicStudyInformationForm.doc</a></td>
                      <td style="white-space: nowrap">Feb 08 2015 06:47:12 PM</td>
                      <td><a href="mailto: jonesm@mail.nih.gov" data-original-title="" title="">Mary Jones</a>
                      </td></tr>
                    </tbody></table>
                
                 <p><span class="reportLabel">Comments:</span> Lorem ipsum dolor sit amet, consectetur adipiscing elit. In id sem in leo vehicula faucibus ut sed nibh. Nam lobortis nunc at velit tincidunt consequat. Vestibulum rhoncus diam sit amet nisl feugiat, in cursus tortor aliquet. In venenatis eleifend purus, in suscipit nisi venenatis in. Donec et nulla molestie, tincidunt mi ac, finibus arcu. In mattis, nibh eu posuere gravida, lorem lorem consequat orci, at efficitur ligula turpis ac augue. Etiam faucibus ullamcorper neque ac pulvinar.</p>
              </div><!--end panel body-->
              </div><!--end panel-->

              </div><!--end container-->
         	 <script src="<s:url value="/controllers/grantSearch.js" />"></script>
            </body>
          </html>