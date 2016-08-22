<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<title>Missing Data Report</title>

<script type="text/javascript" src="/scripts/main.js"></script>
  <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link href="/stylesheets/table.css" rel="stylesheet" type="text/css" media="screen">
<link href="/stylesheets/styles.css" rel="stylesheet" type="text/css" media="screen">
<link href="/stylesheets/displaytag.css" rel="stylesheet" type="text/css" media="screen">
<link href="/stylesheets/custom.css" rel="stylesheet" type="text/css" media="screen">
<link href="<s:url value="/stylesheets/font-awesome-4.6.3.min.css" />" rel="stylesheet" type="text/css" media="screen" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

</head>

<body>
<div class="panel panel-danger" id="missing data">          
      
      <div class="panel-heading">
        <div class="pheader"><h4>Missing/Incomplete Data Report</h4>/div>
        <div class="panel-body">
    <div align="right">
      <a href="#" onclick="window.print()">
        <i class="fa fa-print" aria-hidden="true"></i>&nbsp;Print Report
      </a>
    </div>
    <p><i class="fa fa-exclamation-triangle fa-lg" aria-hidden="true"></i><strong>In order for the ${page.displayName} to be moved to the "Completed" status, the following data needs to be provided or updated:</strong></p>
 
 

  	<s:iterator status="missingLevel0Stat" var="missingLevel0Data" value="missingDataList">	  
  	  <p>${missingLevel0Stat.index + 1}.&nbsp;&nbsp;${missingLevel0Data.displayText}</p>
  	  <s:if test="%{#missingLevel0Data.childList.size > 0}">
  	    <ol style="list-style-type: square;"> 	    
  	    <s:iterator status="missingLevel1Stat" var="missingLevel1Data" value="#missingLevel0Data.childList">
  	    <li>${missingLevel1Data.displayText}</li>
  	    <s:if test="%{#missingLevel1Data.childList.size > 0}">
  	        <ul class="indent" style="list-style-type: disc">
  	          <s:iterator status="missingLevel2Stat" var="missingLevel2Data" value="#missingLevel1Data.childList">
  		        <li>${missingLevel2Data.displayText}</li>
  		        <s:if test="%{#missingLevel2Data.childList.size > 0}">
  		          <ol style="list-style-type: circle;">
  		            <s:iterator status="missingLevel3Stat" var="missingLevel3Data" value="#missingLevel2Data.childList">		     
  		              <li class="indent2">${missingLevel3Data.displayText}</li>
  		            </s:iterator>
  		          </ol>
  	            </s:if>
  	          </s:iterator> <!-- level2 iteration end -->
  	        </ul>
  	      </s:if>
  	      <br/>
        </s:iterator><!--  level1 iteration end -->
        </ol>
      </s:if>
    </s:iterator> <!--  level0 iteration end -->	
  </div> <!--  end panel body -->
</div> <!--  end panel -->
</body>
</html>