var rowEditing = Ext.create('Ext.grid.plugin.RowEditing',{
	clicksToEdit: 2
});
var gridheaderfilters = Ext.create('Ext.ux.grid.plugin.HeaderFilters');

Ext.define('lfims.controller.bankMaster', {
	extend: 'Ext.app.Controller',

	views: ['bankMaster.List'],
	stores: ['bankMaster'],
	models: ['bankMaster'],


	//This method is called just before the Application's Launch function. 
	//The init function is a great place to set up how your controller interacts with the view, and is usually used in conjunction with 
	//another Controller function - control. 
	//The control function makes it easy to listen to events on your view classes and take some action with a handler function.

	init: function() {
		console.log('Initialized BankMaster! This happens before the Application launch function is called');



		//control( String/Object selectors, Object listeners )
		//Adds listeners to components selected via Ext.ComponentQuery. Accepts an object containing component paths mapped to a hash of listener functions.
		this.control({
		//	'viewport > #mainPanel': {
		//		render: this.onPanelRendered
		//	},

			'bankMasterList': {
				select: this.recordselect
			},
			'#NewBankMaster':{
				click: this.createNewRecord
			},
			'#DeleteBankMaster':{
				click: this.deleteRecord
			}

		});
	},

	onPanelRendered: function() {
		console.log('The panel was rendered');
		this.getBankMasterStore().load();
	},

	createNewRecord: function(){
		this.getBankMasterStore().insert(0, new lfims.model.bankMaster());
		rowEditing.startEdit(0, 0);
	},

	deleteRecord: function(){
		console.log('Delete bank master button clicked');
		var view= Ext.getCmp('bankMasterList');
		var selection = view.getSelectionModel().getSelection();
		this.getBankMasterStore().remove(selection);
		//this.getBankMasterStore().sync();

	},

	recordselect: function( component, record, index, eOpts ){
		console.log('grid activated');
		var grid= Ext.getCmp('bankMasterList');
		grid.getSelectionModel().on('selectionchange', function(selModel, selections){
			grid.down('#DeleteBankMaster').setDisabled(selections.length === 0);
		});
	}


});
