Ext.define('lfims.view.bankMaster.List' ,{
	extend: 'Ext.grid.Panel',
	id: 'bankMasterList',
	frame: true,
	plugins: [rowEditing,gridheaderfilters],
	alias : 'widget.bankMasterList',
	store: 'bankMaster',
	stateful: false,
	multiSelect: true,
	tbar: [

{
	iconCls: 'icon-add',
	text:'Add',
	icon: '/lfims/extjs/resources/images/add.jpg',
	id:'NewBankMaster'
}, '-',
{

	text:'Delete',
	iconCls: 'icon-delete',
	icon: '/lfims/extjs/resources/images/delete.jpg',
	disabled: true,
	id:'DeleteBankMaster'
}],


init: function() {
	console.log('Initialized BankMaster! This happens before the Application launch function is called');

},

initComponent: function() {

	// setup the state provider, all state information will be saved to a cookie
	Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

	this.columns = [
	                {header: 'Bank Name',  dataIndex: 'name',  flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield", filterName:"name"}},
	                {header: 'Bank Full Name', dataIndex: 'fullName', flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield", filterName:"fullName"}},
	                {header: 'Head Office',  dataIndex: 'headOffice',  flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield",filterName:"headOffice"}},
	                {header: 'Contact Person', dataIndex: 'contactPerson', flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield",filterName:"contactPerson"}},
	                {header: 'Contact Phone', dataIndex: 'contactPhone', flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield",filterName:"contactPhone"}}
	                ];

	this.callParent(arguments);

	this.viewconfig = {
			stripeRows: true,
			enableTextSelection: true
	};
	this.store.pageSize=itemsPerPage;
	
	this.store.load({
		params: {
			// specify params for the first page load if using paging
			start: 0,
			limit: itemsPerPage

		}
	});
	console.log('Initialized BankMasterList! This happens before the Application launch function is called');
}
});