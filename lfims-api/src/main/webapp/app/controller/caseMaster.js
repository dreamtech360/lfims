var rowEditing = Ext.create('Ext.grid.plugin.RowEditing',{
	clicksToEdit: 2
});
var gridheaderfilters = Ext.create('Ext.ux.grid.plugin.HeaderFilters');


Ext.define('lfims.controller.caseMaster', {
	extend: 'Ext.app.Controller',

	views: ['caseMaster.CaseDetails',
	        'caseMaster.CaseDetailsList',
	        /*'caseMaster.DefendentDetails',
	        'caseMaster.DefendentDetailsList',
	        'caseMaster.SecurityDetails',
	        'caseMaster.SecurityDetailsList',
	        'caseMaster.ImportantDocumentDetails',
	        'caseMaster.ImportantDocumentDetailsList',*/
	        'caseMaster.CaseManagementTabs'
	        
	        ],
	stores: ['caseMasterDetails'
	         //,'caseMasterDefendent','caseMasterDiary','caseMasterImportantDocuments','caseMasterSecurity'
	         ],
	models: ['caseMaster.caseMasterDetails'
	         //,'caseMaster.caseMasterDefendent','caseMaster.caseMasterDiary','caseMaster.caseMasterImportantDocuments','caseMaster.caseMasterSecurity'
	         ],


	//This method is called just before the Application's Launch function. 
	//The init function is a great place to set up how your controller interacts with the view, and is usually used in conjunction with 
	//another Controller function - control. 
	//The control function makes it easy to listen to events on your view classes and take some action with a handler function.

	init: function() {
		console.log('Initialized Case Management! This happens before the Application launch function is called');

		
		

		//control( String/Object selectors, Object listeners )
		//Adds listeners to components selected via Ext.ComponentQuery. Accepts an object containing component paths mapped to a hash of listener functions.
		this.control({
			/*'viewport > #rightPanel': {
				render: this.onPanelRendered
			},*/

			'CaseDetailsList': {
				select: this.recordselect,
				itemdblclick : this.loadCaseDetails
			},
			'#NewCase':{
				click: this.createNewRecord
			},
			'#DeleteCase':{
				click: this.deleteRecord
			},
			'CaseMasterDetails button[action=update]': {
				click: this.updateCaseMasterDetails
			}

		});
	},

	//This method is called when user clicks the Save button on the form
	updateCaseMasterDetails: function(button) {
		console.log('called save case details method method');
		var form    = button.up('form');
		console.log(form.getMode());
		values = form.getValues();
			
		if(form.getMode() == 'new'){
			this.getCaseMasterDetailsStore().add(values);
			this.getCaseMasterDetailsStore().save();
			
		}
		else{
			record = form.getRecord();
			var data=this.getCaseMasterDetailsStore().getById(record.get('id'));
			data.set(values);
			//this.getCaseMasterDetailsStore().update();
		}
			
	/*	Ext.getCmp('rightPanel').removeAll(true);
		Ext.getCmp('rightPanel').removeDocked(Ext.getCmp('pagination'),true);
		Ext.getCmp('rightPanel').add([{xtype: 'CaseDetailsList'}]);
		Ext.getCmp('rightPanel').addDocked([{
			id:'pagination',
			xtype: 'pagingtoolbar',
			store: 'caseMasterDetails',   // same store GridPanel is using
			dock: 'bottom',
			displayInfo: true,
			flex: 1
		},]);*/
	},
	/*onPanelRendered: function() {
		console.log('The panel was rendered');
		this.getCaseMasterDetailsStore().clearFilter(true);
		//this.getAdvocateMasterStore().load();
	},*/

	createNewRecord: function(){
		var view = Ext.widget('CaseDetails');
		var rightPanel=Ext.getCmp('rightPanel');
		view.show();
	},

	deleteRecord: function(){
		console.log('Delete Case Master button clicked');
		var view= Ext.getCmp('CaseDetailsList');
		var selection = view.getSelectionModel().getSelection();
		this.getCaseMasterDetailsStore().remove(selection);
		//this.getAdvocateMasterStore().sync();

	},
	
	//This is called when user double clicks the case record for editing
	
	loadCaseDetails: function ( view,  record, htmlElement,  index,  eventObject,  eOpts ){
		
		console.log(record.get('caseNo'));
		Ext.getCmp('rightPanel').removeAll(true);
		Ext.getCmp('rightPanel').removeDocked(Ext.getCmp('pagination'),true);
		Ext.getCmp('rightPanel').add([{xtype: 'CaseManagementTabs'}]);
		var rightPanel=Ext.getCmp('rightPanel');
		rightPanel.items.get(0).items.get(0).setMode('edit');
		Ext.ModelManager.getModel('lfims.model.caseMaster.caseMasterDetails').load(record.get('id'), {
		    success: function(data) {
		    	rightPanel.items.get(0).items.get(0).getForm().loadRecord(data);
		    }
		});
		
	},

	recordselect: function( component, record, index, eOpts ){
		console.log('grid activated');
		var grid= Ext.getCmp('CaseDetailsList');
		grid.getSelectionModel().on('selectionchange', function(selModel, selections){
			grid.down('#DeleteCase').setDisabled(selections.length === 0);
		});
	}


});
