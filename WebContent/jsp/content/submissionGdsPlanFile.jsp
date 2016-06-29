<%@ taglib uri="/struts-tags" prefix="s"%>

<p></p>

<s:if test="%{gdsPlanFile.size > 0}">
	<table style="width: 80%;" cellpadding="0px" cellspacing="0"
		class="table table-bordered table-striped" style="margin-left: 10px;">
		<tr class="modalTheader">
			<th class="tableHeader" align="center" width="10%">Document Title</th>
			<th class="tableHeader" align="center" width="10%">File Name</th>
			<th class="tableHeader" align="center" width="10%">Date</th>
			<th class="tableHeader" align="center" width="10%">Uploaded By</th>
			<th class="tableHeader" align="center" width="5%">Action</th>
		</tr>
		<tr>
			<td><s:property value="%{gdsPlanFile[0].docTitle}" />
				<s:if test="%{gdsPlanFile[0].fileName == null || gdsPlanFile[0].fileName == ''}">
					<s:a href="javascript:openDocument(%{gdsPlanFile[0].id})">
					<i class="fa fa-eye fa-lg" aria-hidden="true"></i></s:a>
				</s:if></td>
			<td><s:if test="%{gdsPlanFile[0].fileName != null && gdsPlanFile[0].fileName != ''}">
					<s:a href="javascript:openDocument(%{gdsPlanFile[0].id})">
					<s:property value="%{gdsPlanFile[0].fileName}" /></s:a>
				</s:if></td>
			<td style="white-space: nowrap"><s:date
					name="%{gdsPlanFile[0].uploadedDate}"
					format="MMM dd yyyy hh:mm:ss a" /></td>
			<td><s:property value="%{gdsPlanFile[0].uploadedBy}" /></td>
			<td><s:a href="javascript:removeDocument(%{gdsPlanFile[0].id}, %{gdsPlanFile[0].projectId})">
				<span class="fa fa-trash fa-lg" aria-hidden="true"
				alt="delete icon" style="margin-bottom: 5px;"></span></s:a></td>
		</tr>
	</table>
</s:if>

<s:if test="%{gdsPlanFile.size > 1}">
	<div class="qSpacing">
		<p class="question">
			History of Uploaded Documents&nbsp;<a href="javascript:void"
				class="history"><i class="expand fa fa-plus-square" aria-hidden="true"></i></a>
		</p>
		<div class="uploadedHistory" style="display: none;">
			<table style="width: 80%;" cellpadding="0px" cellspacing="0"
				class="table table-bordered table-striped"
				style="margin-left: 10px;">
				<tr class="modalTheader">
					<th class="tableHeader" align="center" width="10%">Document Title</th>
					<th class="tableHeader" align="center" width="10%">File Name</th>
					<th class="tableHeader" align="center" width="10%">Date</th>
					<th class="tableHeader" align="center" width="10%">Uploaded By</th>
					<th class="tableHeader" align="center" width="5%">Action</th>
				</tr>
				<s:iterator value="%{gdsPlanFile}" var="file" status="stat">

					<s:if test="#stat.index != 0">
						<tr>
							<td><s:property value="%{#file.docTitle}" />
								<s:if test="%{#file.fileName == null || #file.fileName == ''}">
									<s:a href="javascript:openDocument(%{#file.id})">
										<i class="fa fa-eye fa-lg" aria-hidden="true"></i></s:a>
								</s:if></td>
							<td><s:if test="%{#file.fileName != null && #file.fileName != ''}">
									<s:a href="javascript:openDocument(%{#file.id})">
									<s:property value="%{#file.fileName}" /></s:a>
								</s:if></td>
							<td style="white-space: nowrap"><s:date
								name="%{#file.uploadedDate}"
								format="MMM dd yyyy hh:mm:ss a" /></td>
							<td><s:property value="%{#file.uploadedBy}" /></td>
							<td><s:a href="javascript:removeDocument(%{#file.id}, %{#file.projectId})">
								<span class="fa fa-trash fa-lg" aria-hidden="true"
								alt="delete icon" style="margin-bottom: 5px;"></span></s:a></td>
						</tr>
					</s:if>
				</s:iterator>
			</table>
		</div>
	</div>
</s:if>
