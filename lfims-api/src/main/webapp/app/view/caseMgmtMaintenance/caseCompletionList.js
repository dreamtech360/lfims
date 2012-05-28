Ext.define('lfims.view.caseMgmtMaintenance.caseCompletionList' ,{
	extend: 'Ext.grid.Panel',
	id: 'caseCompletionList',
	frame: true,
	plugins: [rowEditing,gridheaderfilters],
	alias : 'widget.caseCompletionList',
	store: 'caseCompletion',
	stateful: false,
	multiSelect: true,
	tbar: [

{
	iconCls: 'icon-add',
	text:'Add',
	icon: '/lfims/extjs/resources/images/add.jpg',
	id:'NewCaseCompletion'
}, '-',
{

	text:'Delete',
	iconCls: 'icon-delete',
	icon: '/lfims/extjs/resources/images/delete.jpg',
	disabled: true,
	id:'DeleteCaseCompletion'
}],


init: function() {
	console.log('Initialized Case Completion Master List! This happens before the Application launch function is called');

},

initComponent: function() {

	// setup the state provider, all state information will be saved to a cookie
	Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

	this.columns = [
	                {header: 'Name',  dataIndex: 'name',  flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield", filterName:"name"}},
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
	console.log('Initialized Case Completion List! This happens before the Application launch function is called');
}
});