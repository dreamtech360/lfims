Ext.define('lfims.model.branchMaster', {
    extend: 'Ext.data.Model',
    fields: ['id','bankName','name', 'region','address','contactPerson','contactPhone'],
    idProperty: 'id',
    belongsTo : 'bankMaster'
});