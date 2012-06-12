Ext.define('lfims.model.courtMaster', {
    extend: 'Ext.data.Model',
    fields: ['id','name', 'fullName','advAddress1','advAddress2','cityPin','contactPhone'],
    idProperty: 'id'
});