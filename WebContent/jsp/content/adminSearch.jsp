<%@ taglib uri="/struts-tags" prefix="s"%>
	
	
		<!--Begin Form -->
		<form id="admin_form" name="admin_form" action="searchGdsUsers.action" method="post" data-toggle="validator" role="form">
			<!-- Page navbar -->
			
			
			<div id="adminSearch" style="">
				
			<s:hidden id="userId" name="userId"/>			
				
				
				<!-- Begin Panel -->
				<div class="col-md-12">
					<div class="panel project-panel-primary" id="searchGrant">
						
						<div class="panel-heading">
							<div class="pheader">
								<h4>Manage GDS User Accounts</h4>
							</div>
						</div>
						<!--end panel header-->
						<div class="panel-body">
							<h4>Search Criteria</h4>
							<div class="form-group row">
								<div class="checkbox"><label><input type="checkbox" id="gdsUsersOnly" name="gdsUsersOnly" style="margin-left: -40px;">&nbsp;&nbsp;Only users with GDS Roles</label>
							</div>
							<div class="col-xs-5">
								<label for="First Name">First Name:</label>
								<input type="text" name="criteria.firstName" maxlength="30" value="" id="firstName" class="form-control">
							</div>
							<div class="col-xs-5">
								<label for="Last Name">Last Name:</label>
								<input type="text" name="criteria.lastName" maxlength="30" value="" id="lastName" class="form-control">
							</div>
							
							
							
							<div class="col-xs-5">
								<label for="NCI Division/Office/Center">NCI Division/Office/Center:</label>
								
								<s:select name="criteria.doc" id="doc"								
								cssClass="c-select form-control" 
								list="%{@gov.nih.nci.cbiit.scimgmt.gds.util.GdsSubmissionActionHelper@getDocDropDownList()}"                   			 
								listKey="optionKey" listValue="optionValue" 
								headerKey="" headerValue="All"
								emptyOption="false"/>
						
							</div>
							
							<div class="col-xs-5">
								<label for="User Role">GDS User Role:</label>
								
								<s:select name="criteria.roleCode" id="role"
                    			value="Select User Role"
                    			class="c-select form-control"
                    			list="%{@gov.nih.nci.cbiit.scimgmt.gds.util.GdsSubmissionActionHelper@getLookupDropDownCodeList(@gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants@GDS_ROLE_LIST)}"
                    			listKey="optionKey" listValue="optionValue"
                    			headerKey="" headerValue="Select GDS User"
                    			emptyOption="false"/>							
							</div>
							
							
							
							
							
							
							
							<div class="searchFormat col-xs-10" style="float:right; margin-top: 10px; padding-left: 70px;">
								<button type="button" class="btn btn-primary has-spinner" id="search" onclick="searchUsers()"><i class="fa fa-spinner fa-spin"></i> Search</button>
								<button type="button" class="btn btn-default" id="reset" onclick="resetData()">Reset</button>
								<p>&nbsp;</p>
							</div>
						</div>
						
						<!--Begin Search Results-->
						
						
						
						
						<div id="searchResults" style="margin-left: 10px;">
							<h4>&nbsp;</h4>
							<h4>Search Results</h4><br/>&nbsp;
							
							<table style="width: 95%;" cellpadding="0px" cellspacing="0" class="table table-bordered table-striped">
								<tbody><tr class="modalTheader">
									
									<th width="20%"  align="left" style="vertical-align:bottom;" scope="col">User Name</th>
									<th width="20%"  align="left"   style="whitespace: nowrap; vertical-align:bottom;" scope="col">Email Address</th>
									
									<th width="8%"  align="left" style="vertical-align:bottom;" scope="col">NCI DOC</th>
								
                                    
                                    <th width="12%"  align="left" style="vertical-align:bottom;" scope="col">Current Role(s)</th>
                                    <th width="20%"  align="left" style="vertical-align:bottom;" scope="col">Created/Updated by:</th>
									
									<th width="5%"  align="left" style="vertical-align:bottom;" scope="col">Actions</th>
								</tr>
								
								
								<s:if test="%{userRoles.size > 0}">
				  				<s:iterator value="userRoles" var="userRole" status="stat">
				    			<s:if test="#stat.index /2 == 0">
					  			<tr class="tableContent">
								</s:if>
								<s:else>
					  			<tr class="tableContentOdd">
								</s:else>					    	
																
								<td>${userRole.nedPerson.fullName}</td>
									<td><a href="mailto:${userRole.nedPerson.email}">${userRole.nedPerson.email}</a></td>
									<td>${userRole.nedPerson.orgpath} </td>							
									<td><s:property value="%{getLookupDisplayNamebyCode(@gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants@GDS_ROLE_LIST, #userRole.gdsRoleCode)}"/></td>
                                    <td>${userRole.updatedByFullName} on ${userRole.updatedDate}</td>
                                    
                                    <td><div style="white-space: nowrap; font-size: 14px;"><a data-toggle="modal" href="#myModal"><i class="fa fa-pencil-square fa-lg" aria-hidden="true" alt="Edit" title="Edit"></i></a>&nbsp;&nbsp;&nbsp;<a onclick="deletePersonRole('${userRole.nihNetworkId}')" href="javascript: void(0)"><i class="fa fa-trash fa-lg" aria-hidden="true" alt="Delete" title="Delete"></i></a></div></td>
								 
									</tr>
								
								</s:iterator>
								</s:if>
								
								
								<s:elseif test="%{nedPersons.size > 0}">
				  				<s:iterator value="nedPersons" var="nedPerson" status="stat">
				    			<s:if test="#stat.index /2 == 0">
					  			<tr class="tableContent">
								</s:if>
								<s:else>
					  			<tr class="tableContentOdd">
								</s:else>					    	
																
								<td>${nedPerson.fullName}</td>
									<td><a href="mailto:${nedPerson.email}">${nedPerson.email}</a></td>
									<td>${nedPerson.orgpath} </td>
									<s:if test="%{#nedPerson.userRole != null && #nedPerson.userRole.gdsRoleCode != null}">
									<td><s:property value="%{getLookupDisplayNamebyCode(@gov.nih.nci.cbiit.scimgmt.gds.constants.ApplicationConstants@GDS_ROLE_LIST, #nedPerson.userRole.gdsRoleCode)}"/></td>
                                    <td>${nedPerson.userRole.updatedByFullName} on ${nedPerson.userRole.updatedDate}</td>
                                    </s:if>
                                    <s:else><td></td><td></td></s:else>
                                    
									<td><div style="white-space: nowrap; font-size: 14px;">
									  <a data-toggle="modal" href="#myModal"><i class="fa fa-pencil-square fa-lg" aria-hidden="true" alt="Edit" title="Edit"></i></a>&nbsp;&nbsp;&nbsp;									 
									  <s:if test="%{#nedPerson.userRole != null && #nedPerson.userRole.gdsRoleCode != null}">
									    <a onclick="deletePersonRole('${nedPerson.userRole.nihNetworkId}')" href="javascript: void(0)"><i class="fa fa-trash fa-lg" aria-hidden="true" alt="Delete" title="Delete"></i></a>
									  </s:if>
									</div></td>
								
								 
									</tr>
								
								</s:iterator>
								</s:elseif>
								
								
								
								<s:else>
				  					<tr class="tableContent">
				    					<td colspan="4">Nothing found to display.</td>
				  					</tr>
								</s:else>
								
							
						</tbody></table>
						
						</div>	<!--end search results-->
						
						</div> <!--end panel body-->
						</div> <!--end panel-->
						
						
						
						</div> <!--  end Panel  -->
						
					</div>
					
					
					
					
					
					</form>
				
			
			<p>&nbsp;</p>
			<!-- end Content -->
			<!-- Modal for Roles -->
			<div id="myModal" class="modal fade" role="dialog">
				<div class="modal-dialog modal-lg">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Edit/Add User Role</h4>
						</div>
						<div class="modal-body">
							
							<p>&nbsp;Only one selection per user can be made.</p>
							<table style="width: 95%;" cellpadding="0px" cellspacing="0" class="table table-bordered table-striped">
								<tbody><tr class="modalTheader">
									<th class="tableHeader" scope="col" colspan="3">Select for:</th>
									<td colspan="4" align="left" bgcolor="#FFFFFF" style="vertical-align:bottom; border-color: #FFF; " scope="col">&nbsp;</td>
								</tr>
								<tr>
									
									<td width="4%" align="center" style="vertical-align:bottom; font-size: 11px;">GPA</td>
									<td width="5%" align="center" style="vertical-align:bottom; font-size: 11px;">GDS User Edit</td>
									<td width="5%" align="center" style="vertical-align:bottom; font-size: 11px;">GDS User Read Only</td>
									<th width="28%" align="left" style="vertical-align:bottom; background-color: #14819b; color: #FFF; " scope="col">User Name</th>
									<th width="31%" align="left" style="whitespace: nowrap; vertical-align:bottom; background-color: #14819b; color: #FFF;" scope="col">Email Address</th>
									<th width="8%" align="left" style="vertical-align:bottom; background-color: #14819b; color: #FFF;" scope="col">DOC</th>
									<th width="13%" align="left" style="vertical-align:bottom; background-color: #14819b; color: #FFF;" scope="col">Active PD Role?</th>
								</tr>
								<tr>
									
										<td align="center" valign="middle"><input type="radio" name="userRole" id="GPA" value="GPA"></td>
										<td align="center" valign="middle"><input type="radio" name="userRole" id="GDS User Edit" value="GDS User Edit"></td>
										<td align="center" valign="middle"><input type="radio" name="userRole" id="GDS User Read Only" value="GDS User Read Only"></td>
										<td>Catherine Fishman</td>
										<td><a href="mailto:Fishmanc@mail.nih.gov">Fishmanc@mail.nih.gov</a></td>
										<td>DEA</td>
										<td>Yes</td>
									</tr>
									
									
									
									
									
									
									
								</tbody></table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Save</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
							</div>
						</div>
					</div>
				</div>
				
			

<script type="text/javascript" src="<s:url value="/controllers/gds.js"/>"></script>
<script type="text/javascript" src="<s:url value="/controllers/admin.js"/>"></script>
			