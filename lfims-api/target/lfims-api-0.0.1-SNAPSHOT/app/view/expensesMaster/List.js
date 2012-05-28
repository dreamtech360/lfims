Ext.define('lfims.view.expensesMaster.List' ,{
	extend: 'Ext.grid.Panel',
	id: 'expensesMasterList',
	frame: true,
	plugins: [rowEditing,gridheaderfilters],
	alias : 'widget.expensesMasterList',
	store: 'expensesMaster',
	stateful: false,
	multiSelect: true,
	tbar: [

{
	iconCls: 'icon-add',
	text:'Add',
	icon: '/lfims/extjs/resources/images/add.jpg',
	id:'NewExpensesMaster'
}, '-',
{

	text:'Delete',
	iconCls: 'icon-delete',
	icon: '/lfims/extjs/resources/images/delete.jpg',
	disabled: true,
	id:'DeleteExpensesMaster'
}],


init: function() {
	console.log('Initialized ExpensesMaster! This happens before the Application launch function is called');

},

initComponent: function() {

	// setup the state provider, all state information will be saved to a cookie
	Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

	this.columns = [
	                {header: 'Reason',  dataIndex: 'reason',  flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield", filterName:"reason"}},
	                {header: 'Description',  dataIndex: 'description',  flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield", filterName:"description"}}
	               
	               ];

	this.callParent(arguments);

	this.viewconfig = {
			stripeRows: true,
			enableTextSelection: true
	};

	this.store.load({
		params: {
			// specify params for the first page load if using paging
			start: 0,
			limit: itemsPerPage

		}
	});
	console.log('Initialized ExpensesMasterList! This happens before the Application launch function is called');
}
});