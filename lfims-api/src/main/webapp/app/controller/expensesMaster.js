var rowEditing = Ext.create('Ext.grid.plugin.RowEditing',{
	clicksToEdit: 2
});
var gridheaderfilters = Ext.create('Ext.ux.grid.plugin.HeaderFilters');

Ext.define('lfims.controller.expensesMaster', {
	extend: 'Ext.app.Controller',

	views: ['expensesMaster.List'],
	stores: ['expensesMaster'],
	models: ['expensesMaster'],


	//This method is called just before the Application's Launch function. 
	//The init function is a great place to set up how your controller interacts with the view, and is usually used in conjunction with 
	//another Controller function - control. 
	//The control function makes it easy to listen to events on your view classes and take some action with a handler function.

	init: function() {
		console.log('Initialized ExpensesMaster! This happens before the Application launch function is called');



		//control( String/Object selectors, Object listeners )
		//Adds listeners to components selected via Ext.ComponentQuery. Accepts an object containing component paths mapped to a hash of listener functions.
		this.control({
		//	'viewport > #mainPanel': {
		//		render: this.onPanelRendered
		//	},

			'expensesMasterList': {
				select: this.recordselect
			},
			'#NewExpensesMaster':{
				click: this.createNewRecord
			},
			'#DeleteExpensesMaster':{
				click: this.deleteRecord
			}

		});
	},

	onPanelRendered: function() {
		console.log('The panel was rendered');
		this.getExpensesMasterStore().load();
	},

	createNewRecord: function(){
		this.getExpensesMasterStore().insert(0, new lfims.model.expensesMaster());
		rowEditing.startEdit(0, 0);
	},

	deleteRecord: function(){
		console.log('Delete expenses master button clicked');
		var view= Ext.getCmp('expensesMasterList');
		var selection = view.getSelectionModel().getSelection();
		this.getExpensesMasterStore().remove(selection);
		//this.getAdvocateMasterStore().sync();

	},

	recordselect: function( component, record, index, eOpts ){
		console.log('grid activated');
		var grid= Ext.getCmp('expensesMasterList');
		grid.getSelectionModel().on('selectionchange', function(selModel, selections){
			grid.down('#DeleteExpensesMaster').setDisabled(selections.length === 0);
		});
	}


});
