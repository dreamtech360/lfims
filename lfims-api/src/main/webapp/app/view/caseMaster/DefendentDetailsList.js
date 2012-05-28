Ext.define('lfims.view.caseMaster.DefendentDetailsList' ,{
	extend: 'Ext.grid.Panel',
	id: 'DefendentDetailsList',
	frame: true,
	plugins: [rowEditing,gridheaderfilters],
	features: [groupingFeature],
	alias : 'widget.DefendentDetailsList',
	store: 'caseManagement',
	stateful: false,
	multiSelect: true,
	tbar: [
{
	iconCls: 'icon-add',
	text:'Add',
	icon: '/lfims/extjs/resources/images/add.jpg',
	id:'NewCase'
}, '-',
{
	text:'Delete',
	iconCls: 'icon-delete',
	icon: '/lfims/extjs/resources/images/delete.jpg',
	disabled: true,
	id:'DeleteCase'
}],
init: function() {
	console.log('Initialized Case Managemet List! This happens before the Application launch function is called');
},
initComponent: function() {
	// setup the state provider, all state information will be saved to a cookie
	Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

	this.columns = [
{
    header: 'Bank Name',
    dataIndex: 'bankName',
    hidden: true,
    editor:{xtype:'combobox',
        typeAhead: true,
        triggerAction: 'all',
        selectOnTab: true,
        store: 'bankMaster',
        displayField:'name',
        valueField:'id'
    },
    filter: {xtype:"combobox", 
    	typeAhead: true,
        triggerAction: 'all',
        selectOnTab: true,
        store: 'bankMaster',
        filterName:"bankName",
        displayField:'name',
        valueField:'id',
       /* listeners: {
        	'focus': {
        		fn: function(obj,sample){ 
        			console.log('click el');
        			Ext.each(this.getStore().getRange(),function(model){
        				console.log(model.get('id'));});
        				
        		},
        		scope:this
        		
        	}
        }*/
        }
},
	                {header: 'Branch Name',  dataIndex: 'name',  flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield", filterName:"name"}},
	                {header: 'Region', dataIndex: 'region', flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield", filterName:"region"}},
	                {header: 'Address',  dataIndex: 'adddress',  flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield",filterName:"address"}},
	                {header: 'Contact Person', dataIndex: 'contactPerson', flex: 1,field: {xtype: 'textfield'},filter: {xtype:"textfield",filterName:"contactPerson"}},
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
	console.log('Initialized BranchMasterList! This happens before the Application launch function is called');
}
});