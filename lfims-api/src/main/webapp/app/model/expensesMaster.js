Ext.define('lfims.model.expensesMaster', {
    extend: 'Ext.data.Model',
    fields: ['id','reason', 'description'],
    idProperty: 'id'
});