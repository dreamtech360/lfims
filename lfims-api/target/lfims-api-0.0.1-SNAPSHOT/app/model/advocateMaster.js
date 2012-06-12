Ext.define('lfims.model.advocateMaster', {
    extend: 'Ext.data.Model',
    fields: ['id','advName', 'advAddress1','advAddress2','cityPin','contactPhone','email'],
    idProperty: 'id'
});