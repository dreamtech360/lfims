Ext.define('lfims.model.caseMaster.caseMasterDefendent', {
    extend: 'Ext.data.Model',
    
    fields: [  
            'id',
           	'uuid',
        	'defendentName',
        	'status',
        	'deadOrAlive',
        	'address1',
        	'address2',
        	'address3'
             ],
    idProperty: 'id'
});