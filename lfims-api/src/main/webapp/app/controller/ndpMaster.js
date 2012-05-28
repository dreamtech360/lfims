var rowEditing = Ext.create('Ext.grid.plugin.RowEditing',{
	clicksToEdit: 2
});
var gridheaderfilters = Ext.create('Ext.ux.grid.plugin.HeaderFilters');

Ext.define('lfims.controller.ndpMaster', {
	extend: 'Ext.app.Controller',

	views: ['ndpMaster.List'],
	stores: ['ndpMaster'],
	models: ['ndpMaster'],


	//This method is called just before the Application's Launch function. 
	//The init function is a great place to set up how your controller interacts with the view, and is usually used in conjunction with 
	//another Controller function - control. 
	//The control function makes it easy to listen to events on your view classes and take some action with a handler function.

	init: function() {
		console.log('Initialized Next Date Purpose Master! This happens before the Application launch function is called');



		//control( String/Object selectors, Object listeners )
		//Adds listeners to components selected via Ext.ComponentQuery. Accepts an object containing component paths mapped to a hash of listener functions.
		this.control({
		//	'viewport > #mainPanel': {
		//		render: this.onPanelRendered
		//	},

			'ndpMasterList': {
				select: this.recordselect
			},
			'#NewNdpMaster':{
				click: this.createNewRecord
			},
			'#DeleteNdpMaster':{
				click: this.deleteRecord
			}

		});
	},

	onPanelRendered: function() {
		console.log('The panel was rendered');
		this.getNdpMasterStore().load();
	},

	createNewRecord: function(){
		this.getNdpMasterStore().insert(0, new lfims.model.ndpMaster());
		rowEditing.startEdit(0, 0);
	},

	deleteRecord: function(){
		console.log('Delete ndp master button clicked');
		var view= Ext.getCmp('ndpMasterList');
		var selection = view.getSelectionModel().getSelection();
		this.getNdpMasterStore().remove(selection);
		//this.getAdvocateMasterStore().sync();

	},

	recordselect: function( component, record, index, eOpts ){
		console.log('grid activated');
		var grid= Ext.getCmp('ndpMasterList');
		grid.getSelectionModel().on('selectionchange', function(selModel, selections){
			grid.down('#DeleteNdpMaster').setDisabled(selections.length === 0);
		});
	}


});
