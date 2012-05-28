Ext.define('lfims.view.ndpMaster.List' ,{
	extend: 'Ext.grid.Panel',
	id: 'ndpMasterList',
	frame: true,
	plugins: [rowEditing,gridheaderfilters],
	alias : 'widget.ndpMasterList',
	store: 'ndpMaster',
	stateful: false,
	multiSelect: true,
	tbar: [

{
	iconCls: 'icon-add',
	text:'Add',
	icon: '/lfims/extjs/resources/images/add.jpg',
	id:'NewNdpMaster'
}, '-',
{

	text:'Delete',
	iconCls: 'icon-delete',
	icon: '/lfims/extjs/resources/images/delete.jpg',
	disabled: true,
	id:'DeleteNdpMaster'
}],


init: function() {
	console.log('Initialized Next Date Purpose Master! This happens before the Application launch function is called');

},

initComponent: function() {

	// setup the state provider, all state information will be saved to a cookie
	Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

	this.columns = [
	                {header: 'Purpose',  dataIndex: 'purpose',  flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield", filterName:"purpose"}},
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
	console.log('Initialized Next Date Purpose MasterList! This happens before the Application launch function is called');
}
});