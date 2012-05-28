Ext.define('lfims.view.courtMaster.List' ,{
	extend: 'Ext.grid.Panel',
	id: 'courtMasterList',
	frame: true,
	plugins: [rowEditing,gridheaderfilters],
	alias : 'widget.courtMasterList',
	store: 'courtMaster',
	stateful: false,
	multiSelect: true,
	tbar: [

{
	iconCls: 'icon-add',
	text:'Add',
	icon: '/lfims/extjs/resources/images/add.jpg',
	id:'NewCourtMaster'
}, '-',
{

	text:'Delete',
	iconCls: 'icon-delete',
	icon: '/lfims/extjs/resources/images/delete.jpg',
	disabled: true,
	id:'DeleteCourtMaster'
}],


init: function() {
	console.log('Initialized CourtMaster! This happens before the Application launch function is called');

},

initComponent: function() {

	// setup the state provider, all state information will be saved to a cookie
	Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

	this.columns = [
	                {header: 'Court Name',  dataIndex: 'name',  flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield", filterName:"name"}},
	                {header: 'Court Full Name',  dataIndex: 'fullName',  flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield", filterName:"fullName"}},
	                {header: 'Address 1', dataIndex: 'advAddress1', flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield", filterName:"advAddress1"}},
	                {header: 'Address 2',  dataIndex: 'advAddress2',  flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield",filterName:"advAddress2"}},
	                {header: 'City Pin', dataIndex: 'cityPin', flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield",filterName:"cityPin"}},
	                {header: 'Contact Phone', dataIndex: 'contactPhone', flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield",filterName:"contactPhone"}}
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
	console.log('Initialized CourtMasterList! This happens before the Application launch function is called');
}
});