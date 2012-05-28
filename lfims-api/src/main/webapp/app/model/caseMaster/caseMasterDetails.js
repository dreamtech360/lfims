Ext.define('lfims.model.caseMaster.caseMasterDetails', {
    extend: 'Ext.data.Model',
    fields: [	'id',
			    'uuid',
			    'respondentName',
			    'caseNo',
			    'rpmaNo',
			    'bankName',
			    'branchName',
			    'courtName',
			    'limitDate',
			    'caseNature',
			    'caseAmount',
			    'filingDate',
			    'judgementDate',
			    'certificateDate',
			    'closingDate',
			    'oldCourtName',
			    'oldCourtNumber',
			    'casePosition',
			    'caseForum',
			    'otherAdvocateName',
			    'otherAdvocatePhone',
			    'specialOfficerAppointed',
			    'caseTransferDate'
             ],
    idProperty: 'id',
    
    proxy: {
		type: 'rest',
		url: 'http://localhost:8080/lfims/webresources/casemanagement/cases',
	//	appendId: false,
		noCache: false,
		reader: {
			type: 'json',
			root: 'cases',
			totalProperty: 'results',
			successProperty: 'success'
		},
		writer:{
			type: 'json',
			writeAllFields:true

		}
    }
});