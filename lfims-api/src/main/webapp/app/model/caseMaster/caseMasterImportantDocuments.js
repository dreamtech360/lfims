Ext.define('lfims.model.caseMaster.caseMasterImportantDocuments', {
    extend: 'Ext.data.Model',
    	
    fields: [
         	'id',
        	'uuid',
        	'documentName',
        	'originalYN',
        	'receivedFrom',
        	'receivedDate',
        	'receivedBy',
        	'keptIn',
        	'otherDetailsReceipt',
        	'returnDate',
        	'returnTo',
        	'returnBy',
        	'otherDetailsReturn'
        	
             ],
    idProperty: 'id'
});