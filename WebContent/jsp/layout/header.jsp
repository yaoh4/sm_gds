<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:useAttribute id="navtab" name="navtab" />
<tiles:useAttribute id="subnavtab" name="subnavtab" />

<div class="logo-header"> </div>



<div id="wrap"> <%-- This will be closed after the footer --%>
	<div class="navbar ">
		<div class="row">
			<div class="container">
				<div id="logo" class="logoImage">
				    <img src="<s:url value="/images/nci-logo-full.svg" />" width="450px;"  alt="National Cancer Institute">
				    <div
				  	    style="float: right; padding-top: 15px; padding-bottom: 15px;"
					    >
					    <div style="display:inline-block;">
					  	    Welcome:
						    <div id="loginID" class="login">
							    <s:if test="loggedOnUser.lastName != null">
								    <s:property value="%{loggedOnUser.fullName}" />
							    </s:if>
						    </div>
					    </div><br/>  
					    <div style="display:inline-block; padding-right: 10px;">
					        
					        Env.: <span class="question" style="font-size: 14px;">${environment}</span>
					    </div>
					    <div style="display:inline-block; margin-right: 10px;">
					        <img src="<s:url value="/images/version-divider.gif" />" alt="">
					        &nbsp;&nbsp;&nbsp;Version: <span class="question" style="font-size: 14px;">${version}</span>
					    	&nbsp;&nbsp;&nbsp;<img src="<s:url value="/images/version-divider.gif" />" alt="">
					    </div>
				        <div style="display:inline-block; margin-right: 10px;">
					        &nbsp;&nbsp;<a href="/documentation/application/GDSTrackingSystemQuickReferenceGuide.pdf" target="_blank">Quick Reference Guide</a>
					        &nbsp;&nbsp;&nbsp;<img src="<s:url value="/images/version-divider.gif" />" alt="">
					    </div>
				 <div id="popup_nav" style="clear:right; display:inline-block; margin-left: 10px;">
				   	
				    <ul>
				      <li>
				        <a href="#">Send Comments&nbsp;  <i class="fa fa-caret-down" aria-hidden="true"></i>&nbsp;&nbsp;</a>
		                <ul>
	               			<li><a href="${businessPolicyLink}" target="_blank">Business Policy Questions</a></li>
	               			<li><a href="mailto:${technicalIssuesEmail}?subject=${technicalIssuesDisplay}" >Technical Issues</a></li>												
	               		</ul>
	               	  </li>
	               	</ul>
				  </div>
				   </div>
				    <div class="GDS"><h3 style="padding: 0px; margin:0px; display:inline;">Genomic Data Sharing Tracking System</h3></div>
				</div>
			</div>
			<!-- Fixed navbar -->
			<div class="stickyDiv" style="background: #fff;">
			<s:include value="/jsp/layout/navbar.jsp" />

		
		 

    		<!--Page Header -->
    		
    		<s:if test="%{'newSubmission' eq #attr['navtab']}">
    			<s:if test="%{project == null || project.id == null}">
    				<div class="pageHeader" id="pageHeader"><div class="titleWrapper container"><h3>Create New Submission</h3></div></div>
    			</s:if>
    			<s:else>
      				<div class="pageHeader" id="pageHeader"><div class="titleWrapper container">
      				<s:if test="%{project.parentProjectId == null}">
      					<h3>Project Submission</h3>
      				</s:if>
      				<s:else>
      					<h3>Sub-project Submission</h3>
      				</s:else>
      				<div style="display:block;word-wrap: break-word; margin-left:12px;">(&nbsp;<s:property value="%{project.submissionTitle}"/>&nbsp;) Version# <s:property value="%{project.versionNum}"/></div>
      				</div></div>
      				<s:include value="/jsp/layout/subnavbar.jsp" />
				</s:else>
      		</s:if>
      		<s:elseif test="%{'search' eq #attr['navtab']}">
      			<div class="pageHeader" id="pageHeader"><div class="titleWrapper container"><h3>Find Submissions</h3></div></div>
      		</s:elseif>
      		<s:else>
      			<div class="pageHeader" id="pageHeader"><div class="titleWrapper container"><h3>Administration</h3></div></div>
      		</s:else>
			
		</div>
	</div>
	</div>

