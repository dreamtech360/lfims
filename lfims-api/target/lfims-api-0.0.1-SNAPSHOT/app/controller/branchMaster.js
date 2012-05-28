var rowEditing = Ext.create('Ext.grid.plugin.RowEditing',{
	clicksToEdit: 2
});

var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
    groupHeaderTpl: 'Bank Name: {name} ({rows.length} Item{[values.rows.length > 1 ? "s" : ""]})',
    hideGroupedHeader: false
});

var gridheaderfilters = Ext.create('Ext.ux.grid.plugin.HeaderFilters');

Ext.define('lfims.controller.branchMaster', {
	extend: 'Ext.app.Controller',

	views: ['branchMaster.List','branchMaster.Edit'],
	stores: ['branchMaster','bankMaster'],
	models: ['branchMaster'],


	//This method is called just before the Application's Launch function. 
	//The init function is a great place to set up how your controller interacts with the view, and is usually used in conjunction with 
	//another Controller function - control. 
	//The control function makes it easy to listen to events on your view classes and take some action with a handler function.

	init: function() {
		console.log('Initialized BranchMaster! This happens before the Application launch function is called');



		//control( String/Object selectors, Object listeners )
		//Adds listeners to components selected via Ext.ComponentQuery. Accepts an object containing component paths mapped to a hash of listener functions.
		this.control({
		//	'viewport > #mainPanel': {
		//		render: this.onPanelRendered
		//	},

			'branchMasterList': {
				select: this.recordselect
			},
			'#NewBranchMaster':{
				click: this.createNewRecord
			},
			'#DeleteBranchMaster':{
				click: this.deleteRecord
			},
			'branchMasterEdit button[action=update]': {
				click: this.updateBranchMasterDetails
			}

		});
	},

	onPanelRendered: function() {
		console.log('The panel was rendered');
		this.getBranchMasterStore().load();
	},

	
	updateBranchMasterDetails: function(button) {
		console.log('called method');
		var win    = button.up('window'),
		form   = win.down('form'),
		values = form.getValues();
		this.getBranchMasterStore().add(values);
		this.getBranchMasterStore().save();
		win.close();
	},
	
	
	createNewRecord: function(){
		//this.getBranchMasterStore().insert(0, new lfims.model.branchMaster());
		//rowEditing.startEdit(0, 0);
		console.log('new branch master button clicked');
		var view = Ext.widget('branchMasterEdit');
		//view.setMode('new');
		view.show();
	},

	deleteRecord: function(){
		console.log('Delete bank master button clicked');
		var view= Ext.getCmp('branchMasterList');
		var selection = view.getSelectionModel().getSelection();
		this.getBranchMasterStore().remove(selection);
		this.getBranchMasterStore().sync();
		//this.getBranchMasterStore().load();

	},

	recordselect: function( component, record, index, eOpts ){
		console.log('grid activated');
		var grid= Ext.getCmp('branchMasterList');
		grid.getSelectionModel().on('selectionchange', function(selModel, selections){
			grid.down('#DeleteBranchMaster').setDisabled(selections.length === 0);
		});
	}


});
