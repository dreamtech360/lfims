Ext.define('lfims.model.bankMaster', {
    extend: 'Ext.data.Model',
    fields: ['id','name', 'fullName','headOffice','contactPerson','contactPhone'],
    idProperty: 'id',
    hasMany: {model:'branchMaster', name:'branchMasters'}
});