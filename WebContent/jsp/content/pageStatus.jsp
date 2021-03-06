<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

 
    <div class="statusIcon"> 
      <a href="#" class="tooltip">
        <s:if test="%{pageStatusCode.equals(@gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants@PAGE_STATUS_CODE_IN_PROGRESS)}">	
          <img src="../images/inprogress.png" alt="In Progress" />
        </s:if>
        <s:elseif test="%{pageStatusCode.equals(@gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants@PAGE_STATUS_CODE_COMPLETED)}">
          <img src="../images/complete.png" alt="Completed" />
        </s:elseif>
        <s:else>
          <img src="../images/pending.png" alt="Not Started" />
        </s:else>
        <span>
          <img class="callout" src="../images/callout_black.gif" />
          <strong>Legend:</strong><br />  
          <img src="../images/legend.gif" /> 
        </span>
      </a>
    </div>
